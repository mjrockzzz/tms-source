package com.ge.tms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.audit.UserRevEntity;
import com.ge.tms.dao.AccountHistoryRepository;
import com.ge.tms.dao.CustomerMeterHistoryRepository;
import com.ge.tms.dao.UserRevisionEntityRepository;
import com.ge.tms.dto.CustomerBillHistory;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerMeterHistory;
import com.ge.tms.service.AccountHistoryService;

/**
 * Service class containing methods to get customer meter, bill and account history.
 * @author Nitin K.
 */
@Service
public class AccountHistoryServiceImpl implements AccountHistoryService {
	private static final Logger logger = LoggerFactory.getLogger(AccountHistoryServiceImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserRevisionEntityRepository userRevisionEntityRepository;

	@Autowired
	private AccountHistoryRepository accountHistoryRepository;
	
	@Autowired
	private CustomerMeterHistoryRepository customerMeterHistoryRepository;

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Account History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>
	 * @date 2017-08-17
	 */
	@Override
	public ResponseEntity<List<CustomerAccount>> getCustomerAccountHistoryByAcctId(Integer accountId) {
		logger.info("Entered into getCustomerAccountHistoryByAcctId service method.......");
		AuditReader reader = AuditReaderFactory.get(entityManager);
		List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
		List<Number> revisions = reader.getRevisions(CustomerAccount.class, accountId);

		// Check list is empty or not
		if (revisions.isEmpty() && revisions.size() == 0) {
			return new ResponseEntity<List<CustomerAccount>>(customerAccounts, HttpStatus.NOT_FOUND);
		}

		for (Number n : revisions) {
			CustomerAccount customerAccount = reader.find(CustomerAccount.class, accountId, n);
			logger.info("\t[Rev #%1$s] > %2$s\n", n, customerAccount);
			UserRevEntity revEntity = userRevisionEntityRepository.findById(n.intValue());
			long time = revEntity.getTimestamp();
			logger.info("Modified At" + new Date(time) + "Modified By" + revEntity.getUsername());
			customerAccounts.add(customerAccount);
		}
		return new ResponseEntity<List<CustomerAccount>>(customerAccounts, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Bill History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerBillHistory>>
	 * @date 2017-08-17
	 */
	@Override
	public ResponseEntity<List<CustomerBillHistory>> getCustomerBillHistoryByAcctId(Integer accountId) {
		logger.info("Entered into getCustomerBillHistoryByAcctId service method.......");
		List<CustomerBillHistory> customerBillHistories = new ArrayList<CustomerBillHistory>();
		List<Object[]> usageObjects = accountHistoryRepository.findCustomerBillHistoryByAccountId(accountId);

		// Check list is empty or not
		if (usageObjects.isEmpty() && usageObjects.size() == 0) {
			return new ResponseEntity<List<CustomerBillHistory>>(customerBillHistories, HttpStatus.NOT_FOUND);
		}

		// Set the required parameters to CustomerBillHistory DTO
		for (Object[] usageObject : usageObjects) {
			// Create CustomerBillHistory DTO Object
			CustomerBillHistory customerBillHistory = new CustomerBillHistory();
			Integer customerBillId = (Integer) usageObject[0];
			customerBillHistory.setCustomerBillId(customerBillId);
			String billTemplate = (String) usageObject[1];
			customerBillHistory.setBillTempalte(billTemplate);
			String customerClass = (String) usageObject[2];
			customerBillHistory.setCustomerClass(customerClass);
			String ratePlan = (String) usageObject[3];
			customerBillHistory.setCurrentRatePlan(ratePlan);
			Timestamp effectiveTimeStamp = (Timestamp) usageObject[4];
			customerBillHistory.setRateEffective(effectiveTimeStamp);
			customerBillHistories.add(customerBillHistory);
		}
		return new ResponseEntity<List<CustomerBillHistory>>(customerBillHistories, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Meter History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerMeterHistory>>
	 * @date 2017-08-21
	 */
	@Override
	public ResponseEntity<List<CustomerMeterHistory>> getCustomerMeterHistoryByAcctId(Integer accountId) {
		logger.info("Entered into getCustomerMeterHistoryByAcctId service method.......");

		// Set Account Id to CustomerAccount
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setAccountId(accountId);
		List<CustomerMeterHistory> customerMetersHistory = customerMeterHistoryRepository
				.findByCustomerAccount(customerAccount);

		// Check list is empty or not
		if (customerMetersHistory.isEmpty() && customerMetersHistory.size() == 0) {
			return new ResponseEntity<List<CustomerMeterHistory>>(customerMetersHistory, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CustomerMeterHistory>>(customerMetersHistory, HttpStatus.OK);
	}
}