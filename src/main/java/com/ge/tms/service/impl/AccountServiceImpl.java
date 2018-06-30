package com.ge.tms.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ge.tms.common.consts.CommonMessages;
import com.ge.tms.common.consts.Const;
import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.dao.BillAdjustmentHistoryRepository;
import com.ge.tms.dao.BillAdjustmentRepository;
import com.ge.tms.dao.BillChargeConfigurationRespository;
import com.ge.tms.dao.BillingPeakHourRepository;
import com.ge.tms.dao.CustomerAccountRepository;
import com.ge.tms.dao.CustomerBillRepository;
import com.ge.tms.dao.CustomerMeterIntervalDataRepository;
import com.ge.tms.dao.CustomerMeterRepository;
import com.ge.tms.dao.CustomerPaymentRepository;
import com.ge.tms.dto.BillAdjustmentHistory;
import com.ge.tms.dto.BillChargeConfigurationDTO;
import com.ge.tms.dto.BillChargeRequestDTO;
import com.ge.tms.dto.BillChargeResponse;
import com.ge.tms.dto.BillingChargesDTO;
import com.ge.tms.dto.FinancialDetails;
import com.ge.tms.dto.IntervalDataHistory;
import com.ge.tms.dto.IntervalDataHistoryResponse;
import com.ge.tms.dto.IntervalDataManagement;
import com.ge.tms.dto.IntervalDataManagementMonthly;
import com.ge.tms.dto.IntervalDataManagementResponse;
import com.ge.tms.dto.UsageHistory;
import com.ge.tms.dto.WSCustomerAccount;
import com.ge.tms.entity.BillAdjustment;
import com.ge.tms.entity.BillAdjustmentHistoryTMS;
import com.ge.tms.entity.BillChargeConfiguration;
import com.ge.tms.entity.BillingPeakHour;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerBill;
import com.ge.tms.entity.CustomerMeter;
import com.ge.tms.entity.CustomerPayment;
import com.ge.tms.service.AccountService;
import com.ge.tms.util.DateUtility;

/**
 * Service class containing methods to get customer details, bill, bill charge, bill adjustment details, mail service etc.
 * @author Nitin K.
 */
@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
    DozerBeanMapper dozerBeanMapper;

    static Mapper mapper = new DozerBeanMapper();
    
	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	@Autowired
	private CustomerMeterRepository customerMeterRepository;

	@Autowired
	private CustomerBillRepository customerBillRepository;

	@Autowired
	private CustomerPaymentRepository customerPaymentRepository;

	@Autowired
	private CustomerMeterIntervalDataRepository customerMeterIntervalDataRepository;

	@Autowired
	private BillAdjustmentRepository billAdjustmentRepository;

	@Autowired
	private BillingPeakHourRepository billingPeakHourRepository;

	@Autowired
	private BillChargeConfigurationRespository billChargeConfigurationRespository;

	@Autowired
	private BillAdjustmentHistoryRepository billAdjustmentHistoryRepository;
	
	/**
	 * @author Nitin K.
	 * @purpose to get top 10 customer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	@Override
	public ResponseEntity<List<WSCustomerAccount>> searchAllCustomer() {
		logger.info("Entered into searchAllCustomer service method.......");

		/*Pageable limit = new PageRequest(0, 10);
		Page<CustomerAccount> customerAccounts = customerAccountRepository.findAll(limit);
		List<CustomerAccount> customerAccountsList = customerAccounts.getContent();*/
		
		List<CustomerAccount> customerAccounts = customerAccountRepository.findAll();
		logger.info("List size is" + customerAccounts.size());
		List<WSCustomerAccount> list = new ArrayList<WSCustomerAccount>();
		if(customerAccounts.size()>0 && !customerAccounts.isEmpty()){
			for(CustomerAccount customerAccount: customerAccounts){
				list.add(mapper.map(customerAccount, WSCustomerAccount.class));
			}
		}
		return new ResponseEntity<List<WSCustomerAccount>>(list, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @param activeAccount String
	 * @param inActiveAccount String
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	@Override
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAcctId(Integer accountId, String activeAccount,
			String inActiveAccount) {
		logger.info("Entered into searchCustomerByAcctId service method.......");
		logger.debug("Active account value" + activeAccount);
		logger.debug("Inactive account value" + inActiveAccount);

		List<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		if (!activeAccount.isEmpty() && !inActiveAccount.isEmpty()) {
			// Return CustomerAccount by ignoring account status
			customerList = customerAccountRepository.findByAccountIdWithoutStatus(accountId);
		}
		if (!activeAccount.isEmpty() && inActiveAccount.isEmpty()) {
			// Return account only if it is active
			customerList = customerAccountRepository.findByAccountIdAndAccountStatus(accountId, activeAccount);
		}
		if (activeAccount.isEmpty() && !inActiveAccount.isEmpty()) {
			// Return account only if inactive
			customerList = customerAccountRepository.findByAccountIdAndAccountStatus(accountId, inActiveAccount);
		}

		// Check list size and list is empty or not
		if (customerList.size() != 0 && !customerList.isEmpty()) {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by meter number
	 * @param meterNumber Long
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	@Override
	public ResponseEntity<List<CustomerAccount>> searchCustomerByMeterNumber(Long meterNumber) {
		logger.info("Entered into searchCustomerByMeterNumber service method.......");

		CustomerAccount customerAccount = null;
		List<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		// BigDecimal customerMeterNumber = new BigDecimal(meterNumber);

		List<BigDecimal> customerMeters = customerMeterRepository.findCustomerMeterByMeterNumberLike(meterNumber);
		for (BigDecimal customerMeter : customerMeters) {
			logger.info("Meter number is" + customerMeter);
			BigDecimal accountId = customerMeterRepository.findAccountIdByMeterNumberBoth(customerMeter);
			Integer accountIdNew = accountId.intValueExact();
			customerAccount = customerAccountRepository.findByAccountId(accountIdNew);
			customerList.add(customerAccount);
		}
		// Check list size and list is empty or not
		if (customerList.size() != 0 && !customerList.isEmpty()) {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id without account status
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-20
	 */
	@Override
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAccountIdNoStatus(Integer accountId) {
		logger.info("Entered into searchCustomerByAccountIdNoStatus service method.......");

		CustomerAccount customerAccount = null;
		List<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		customerAccount = customerAccountRepository.findByAccountId(accountId);

		// Check customerAccount is not null before adding to list
		if (customerAccount != null) {
			customerList.add(customerAccount);
		}

		// Check list size and list is empty or not
		if (customerList.size() != 0 && !customerList.isEmpty()) {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<CustomerAccount>>(customerList, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the CustomerDetails
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	@Override
	public ResponseEntity<String> updateCustomerDetails(CustomerAccount customerAccount) {
		logger.info("Entered into updateCustomerDetails service method.......");
		// Get same object from database using accountId;
		CustomerAccount account = customerAccountRepository.findByAccountId(customerAccount.getAccountId());
		if (account == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
		// Set the fields to be updated to database CustomerAccount from
		// received CustomerAccount
		account.setLatitude(customerAccount.getLatitude());
		account.setLongitude(customerAccount.getLongitude());
		account.setLocationCode(customerAccount.getLocationCode());
		account.setLocationDescription(customerAccount.getLocationDescription());
		account.setServiceAddress1(customerAccount.getServiceAddress1());
		account.setServiceAddress2(customerAccount.getServiceAddress2());
		account.setServiceCity(customerAccount.getServiceCity());
		account.setServiceZip(customerAccount.getServiceZip());
		account.setPhoneNumber(customerAccount.getPhoneNumber());
		account.setEmailAddress(customerAccount.getEmailAddress());
		account.setAddressPoBox(customerAccount.getAddressPoBox());
		account.setBillingAddress1(customerAccount.getBillingAddress1());
		account.setBillingAddress2(customerAccount.getBillingAddress2());
		account.setBillingCity(customerAccount.getBillingCity());
		account.setBillingZip(customerAccount.getBillingZip());
		account.setServiceAddressState(customerAccount.getServiceAddressState());
		account.setBillingAddressState(customerAccount.getBillingAddressState());

		CustomerAccount accountUpdated = customerAccountRepository.save(account);
		if (accountUpdated != null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Account Details
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	@Override
	@Transactional
	public ResponseEntity<String> updateCustomerAccountDetails(CustomerAccount customerAccount) {
		CustomerBill oldCustomerBill = null;
		CustomerBill newCustomerBill = null;
		// CustomerMeter oldCustomerMeter = null;
		// CustomerMeter newCustomerMeter = null;
		CustomerBill updateCustomerBill = null;

		logger.info("Entered into updateCustomerAccountDetails service method.......");

		// Get same object from database using accountId;
		CustomerAccount oldCustomeraccount = customerAccountRepository.findByAccountId(customerAccount.getAccountId());
		if (oldCustomeraccount == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}

		// Get Old CustomerBill from database
		List<CustomerBill> oldCustomerBills = oldCustomeraccount.getCustomerBills();

		/*
		 * // Check list size and list is empty or not if
		 * (oldCustomerBills.size() == 0 && oldCustomerBills.isEmpty()) { return
		 * new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE,
		 * HttpStatus.NOT_FOUND); }
		 */
		if (!oldCustomerBills.isEmpty() && oldCustomerBills.size() > 0) {
			oldCustomerBill = oldCustomerBills.get(0);
		}

		// Update the objects individually
		// Update CustomerBill object first
		List<CustomerBill> newCustomerBills = customerAccount.getCustomerBills();
		if (!newCustomerBills.isEmpty() && newCustomerBills.size() > 0) {
			newCustomerBill = newCustomerBills.get(0);
		}

		if (oldCustomerBill != null) {
			// Update Old CustomerBill with New CustomerBill
			oldCustomerBill.setRatePlan(newCustomerBill.getRatePlan());
			
			if(newCustomerBill.getRateEffective()!=null){
				oldCustomerBill.setRateEffective(newCustomerBill.getRateEffective());
			}
			
			oldCustomerBill.setBillTemplate(newCustomerBill.getBillTemplate());
			oldCustomerBill.setCustomerClass(newCustomerBill.getCustomerClass());
			
			if(newCustomerBill.getRatePlanTerimantionTimestamp()!=null){
				oldCustomerBill.setRatePlanTerimantionTimestamp(newCustomerBill.getRatePlanTerimantionTimestamp());
			}
			updateCustomerBill = customerBillRepository.save(oldCustomerBill);
		}

		// Check whether it is updated or not
		if (oldCustomerBill != null) {
			if (updateCustomerBill == null) {
				return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
			}
		}
		/*
		 * // Update the CustomerMeter Details // Get New CustomerMeter
		 * List<CustomerMeter> newCustomerMeters =
		 * customerAccount.getCustomerMeters(); if (!newCustomerMeters.isEmpty()
		 * && newCustomerMeters.size() > 0) { newCustomerMeter =
		 * newCustomerMeters.get(0); }
		 */

		/*
		 * // Get Old CustomerMeter from database List<CustomerMeter>
		 * oldCustomerMeters = oldCustomeraccount.getCustomerMeters(); if
		 * (!oldCustomerMeters.isEmpty() && oldCustomerMeters.size() > 0) {
		 * oldCustomerMeter = oldCustomerMeters.get(0); }
		 */

		/*
		 * // Update Old CustomerMeter with New CustomerMeter
		 * oldCustomerMeter.setFeeder(newCustomerMeter.getFeeder());
		 * oldCustomerMeter.setSvcDeliveryPt(newCustomerMeter.getSvcDeliveryPt()
		 * ); oldCustomerMeter.setCurrentTransformerRatio(newCustomerMeter.
		 * getCurrentTransformerRatio());
		 * oldCustomerMeter.setVoltageTransformerRatio(newCustomerMeter.
		 * getVoltageTransformerRatio());
		 * oldCustomerMeter.setMeterMultiplier(newCustomerMeter.
		 * getMeterMultiplier());
		 * oldCustomerMeter.setInstalationDate(newCustomerMeter.
		 * getInstalationDate());
		 * oldCustomerMeter.setRemovalDate(newCustomerMeter.getRemovalDate());
		 * oldCustomerMeter.setDeemandMultiplier(newCustomerMeter.
		 * getDeemandMultiplier()); CustomerMeter updatedCustomerMeter =
		 * customerMeterRepository.save(oldCustomerMeter);
		 * 
		 * // Check whether it is updated or not if (updatedCustomerMeter ==
		 * null) { return new
		 * ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE,
		 * HttpStatus.NOT_FOUND); }
		 */
		// Update CustomerAccount Details
		oldCustomeraccount.setAccountStatus(customerAccount.getAccountStatus());
		oldCustomeraccount.setStartDate(customerAccount.getStartDate());
		
		if(customerAccount.getEndDate()!=null){
			oldCustomeraccount.setEndDate(customerAccount.getEndDate());
		}
				
		oldCustomeraccount.setScId(customerAccount.getScId());
		oldCustomeraccount.setInvoiceDeliveryMethod(customerAccount.getInvoiceDeliveryMethod());
		CustomerAccount updateCustomerAccount = customerAccountRepository.save(oldCustomeraccount);

		// Check whether it is updated or not
		if (updateCustomerAccount == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(UrlPathConstants.UPDATE_SUCCESS, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Financial Details
	 * @param financialDetails FinancialDetails
	 * @return ResponseEntity<String>
	 * @date 2017-07-24
	 */
	@Override
	public ResponseEntity<String> updateCustomerFinancial(FinancialDetails financialDetails) {
		logger.info("Entered into updateCustomerFinancial service method.......");
		CustomerPayment customerPayment = null;
		CustomerBill customerBill = null;
		CustomerAccount customerAccount = null;
		Integer customerPaymentId = 0;
		Integer customerBillId = 0;		
		
		// Get CustomerBill and CustomerPayment from FinancialDetails
		if (financialDetails != null) {
			customerPayment = financialDetails.getCustomerPayment();
			customerBill = financialDetails.getCustomerBill();
			customerAccount = financialDetails.getCustomerAccount();
		}
		
		//Get customer account details
		CustomerAccount oldCustomerAccount = customerAccountRepository.findByAccountId(customerAccount.getAccountId());
		if (oldCustomerAccount == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
		oldCustomerAccount.setRecuringAmount(customerAccount.getRecuringAmount());
		CustomerAccount updatedCustomerAccount = customerAccountRepository.save(oldCustomerAccount);

		// Check whether it is updated or not
		if (updatedCustomerAccount == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
		
		// Get customerPaymentId
		if (customerPayment != null) {
			customerPaymentId = customerPayment.getCustomerPaymentId();
		}

		// Get the old CustomerPayment
		CustomerPayment oldCustomerPayment = customerPaymentRepository.findByCustomerPaymentId(customerPaymentId);
		if (oldCustomerPayment == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}

		// Update old CustomerPayment with new CustomerPayment fields
		oldCustomerPayment.setCardOrChkHoldername(customerPayment.getCardOrChkHoldername());
		oldCustomerPayment.setCardOrChkNumber(customerPayment.getCardOrChkNumber());
		oldCustomerPayment.setPaymentMethod(customerPayment.getPaymentMethod());
		oldCustomerPayment.setRoutingOrCvvnumber(customerPayment.getRoutingOrCvvnumber());
		oldCustomerPayment.setCardExpiryDate(customerPayment.getCardExpiryDate());
		oldCustomerPayment.setNickName(customerPayment.getNickName());
		CustomerPayment updatedCustomerPayment = customerPaymentRepository.save(oldCustomerPayment);

		// Check whether it is updated or not
		if (updatedCustomerPayment == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}

		// Now Update billDueDate of CustomerBill
		if (customerBill != null) {
			customerBillId = customerBill.getCustomerBillId();
		}

		// Get old CustomerBill
		CustomerBill oldCustomerBill = customerBillRepository.findByCustomerBillId(customerBillId);
		if (oldCustomerBill == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}

		// Update old CustomerBill bill due date with new bill due date
		oldCustomerBill.setBillDueDate(customerBill.getBillDueDate());
		CustomerBill updatedCustomerBill = customerBillRepository.save(oldCustomerBill);

		// Check whether it is updated or not
		if (updatedCustomerBill == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(UrlPathConstants.UPDATE_SUCCESS, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get usage history
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @param isCustom boolean
	 * @return ResponseEntity<List<UsageHistory>>
	 * @throws ParseException
	 * @date 2017-07-24
	 */
	@Override
	public ResponseEntity<List<UsageHistory>> getBillHistory(Integer accountId, String startDate, String endDate,
			Long meterNumber, boolean isCustom) throws ParseException {
		logger.info("Entered into getBillHistory service method.......");
		java.sql.Date sqlStartDate = null;
		java.sql.Date sqlEndDate = null;
		List<CustomerBill> customerBills = null;
		List<UsageHistory> usageHistories = new ArrayList<UsageHistory>();

		if (isCustom == true) {
			// Set billing cycle start date and end date
			sqlStartDate = DateUtility.getSqlDateFromStringDate(startDate);
			sqlEndDate = DateUtility.getSqlDateFromStringDate(endDate);
		}

		// Set meter number to CustomerMeter
		CustomerMeter customerMeter = new CustomerMeter();
		BigDecimal newMeterNumber = new BigDecimal(meterNumber);
		customerMeter.setMeterNumber(newMeterNumber);

		// Set account id to CustomerAccount
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setAccountId(accountId);

		// Call CustomerPayment with given accountId
		CustomerPayment customerPayment = customerPaymentRepository.findByCustomerAccount(customerAccount);

		// Check whether it is Custom Bill History or not
		if (isCustom == true) {
			// Call the CustomerBill with given bill cycle dates
			customerBills = customerBillRepository.findCustomerBillByCustomerAccountAndCustomDates(customerAccount,
					sqlStartDate, sqlEndDate);
		} else {
			customerBills = customerBillRepository.findByCustomerAccountOrderByBillGenerationDateDesc(customerAccount);
		}

		// Check list is empty or not
		if (customerBills.isEmpty() && customerBills.size() == 0) {
			return new ResponseEntity<List<UsageHistory>>(usageHistories, HttpStatus.NOT_FOUND);
		}

		for (CustomerBill customerBill : customerBills) {

			// Call usage history with start date and end date
			List<Object[]> usageObjects = customerMeterIntervalDataRepository.findBillHistory(
					customerBill.getBillCycleStartDate(), customerBill.getBillCycleEndDate(), customerMeter);

			/*
			 * // Check list is empty or not if (usageObjects.isEmpty() &&
			 * usageObjects.size() == 0) { return new
			 * ResponseEntity<List<UsageHistory>>(usageHistories,
			 * HttpStatus.NOT_FOUND); }
			 */

			// Create UsageHistory DTO Object
			UsageHistory usageHistory = new UsageHistory();

			// Set the required parameters to UsageHistory DTO
			for (Object[] usageObject : usageObjects) {
				BigDecimal meternumber = (BigDecimal) usageObject[0];
				usageHistory.setMeternumber(meternumber);
				logger.debug("Meter no is" + meternumber);
				Date meterDate = (Date) usageObject[1];
				usageHistory.setMeterDate(meterDate);
				logger.debug("Meter date is" + meterDate);
				BigDecimal meterReading = (BigDecimal) usageObject[2];
				usageHistory.setMeterReading(meterReading);
				logger.debug("Meeter reading  is" + meterReading);
				BigDecimal temperature = (BigDecimal) usageObject[3];
				usageHistory.setDeemand(temperature);
				logger.debug("tempearture is" + temperature);
			}

			usageHistory.setBillAmount(customerBill.getBillAmount());
			usageHistory.setBillGrenerationDate(customerBill.getBillGenerationDate());
			usageHistory.setPaymentPostedDate(customerBill.getPaymentDate());
			usageHistory.setApplicableRate(customerBill.getRatePlan());
			usageHistory.setBillSentDate(customerBill.getBillSentDate());
			usageHistory.setPaymentDueDate(customerBill.getBillDueDate());
			usageHistory.setPaymentStatus(customerBill.getPaymentStatus());
			usageHistory.setPaymentMethod(customerPayment.getPaymentMethod());
			usageHistory.setPaidAmount(customerBill.getPaidAmount());
			usageHistories.add(usageHistory);
		}
		return new ResponseEntity<List<UsageHistory>>(usageHistories, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get interval data usage history
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @param previous boolean
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-07-25
	 */
	@Override
	public ResponseEntity<IntervalDataHistoryResponse> getIntervalDataUsageHistory(Integer accountId, String startDate,
			String endDate, Long meterNumber, boolean previous) throws ParseException {
		logger.info("Entered into getIntervalDataUsageHistory service method.......");

		Date billCycleStartDate = null;
		Date billCycleEndDate = null;
		boolean daysExceeded = false;
		BigDecimal aggregatedPeakDemandKwh = null;
		List<Object[]> usageObjects = null;
		IntervalDataHistoryResponse intervalDataHistoryResponse = new IntervalDataHistoryResponse();
		List<IntervalDataHistory> intervalDataHistories = new ArrayList<IntervalDataHistory>();

		// Check startDate and endDate is null or not(For Custom Bill Cycle
		// Check)
		if (startDate == null && endDate == null) {
			CustomerAccount customerAccount = new CustomerAccount();
			customerAccount.setAccountId(accountId);
			List<CustomerBill> customerBills = customerBillRepository
					.findByCustomerAccountOrderByBillCycleEndDateDesc(customerAccount);
			logger.debug("List size is" + customerBills.size());

			// Check list is empty or not
			if (customerBills.isEmpty() && customerBills.size() == 0) {
				return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse,
						HttpStatus.NOT_FOUND);
			}

			// This is for current bill cycle
			CustomerBill customerBill = customerBills.get(0);
			logger.debug("Bill id is" + customerBill.getCustomerBillId());
			billCycleStartDate = customerBill.getBillCycleStartDate();
			billCycleEndDate = customerBill.getBillCycleEndDate();
			logger.debug("Bill cycle start date is" + customerBill.getBillCycleStartDate());
			logger.debug("Bill cycle end date is" + customerBill.getBillCycleEndDate());

			// Check for previous bill cycle
			if (previous == true) {
				logger.info("Entered into Previous bill cycle service method.......");
				CustomerBill customerBillPrevious = customerBills.get(1);

				// Check previous bill cycle bill exists or not
				if (customerBillPrevious == null) {
					return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse,
							HttpStatus.NOT_FOUND);
				}
				logger.debug("Bill id is" + customerBillPrevious.getCustomerBillId());
				billCycleStartDate = customerBillPrevious.getBillCycleStartDate();
				billCycleEndDate = customerBillPrevious.getBillCycleEndDate();
				logger.debug(" Previous Bill cycle start date  is" + customerBillPrevious.getBillCycleStartDate());
				logger.debug("Previous cycle payment date is" + customerBillPrevious.getBillCycleEndDate());
			}
		}
		else {
			logger.info("Entered into custom bill cycle service method......");
			billCycleStartDate = DateUtility.getSqlDateFromStringDate(startDate);
			billCycleEndDate = DateUtility.getSqlDateFromStringDate(endDate);
			int noOfdays = DateUtility.getDaysDifference(startDate, endDate);
			
			// Only applied for 1 year
			if (noOfdays > 365) {
				return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse,
						HttpStatus.UNPROCESSABLE_ENTITY);
			}
			
			// Check no of days are exceeded than 60 or not
			if (noOfdays > 60) {
				logger.debug("Days are exceeded than 60......");
				daysExceeded = true;
			}
		}

		// Set meter number to CustomerMeter
		CustomerMeter customerMeter = new CustomerMeter();
		BigDecimal newMeterNumber = new BigDecimal(meterNumber);
		customerMeter.setMeterNumber(newMeterNumber);

		// If days are not exceeded call usage history daily else call monthly
		if (daysExceeded == false) {
			intervalDataHistoryResponse.setTypeOfGraph("Daily");
			usageObjects = customerMeterIntervalDataRepository.findUsageHistoryDaily(billCycleStartDate,
					billCycleEndDate, customerMeter);
		} else {
			intervalDataHistoryResponse.setTypeOfGraph("Monthly");
			usageObjects = customerMeterIntervalDataRepository.findUsageHistoryMonthly(billCycleStartDate,
					billCycleEndDate, customerMeter);
		}

		// Check list is empty or not
		if (usageObjects.isEmpty() && usageObjects.size() == 0) {
			return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse, HttpStatus.NOT_FOUND);
		}

		// Set the required parameters to UsageHistory DTO
		for (Object[] usageObject : usageObjects) {
			// Create IntervalDataHistory DTO Object
			IntervalDataHistory intervalDataHistory = new IntervalDataHistory();
			BigDecimal meternumber = (BigDecimal) usageObject[0];
			intervalDataHistory.setMeternumber(meternumber);
			logger.debug("Meter no is" + meternumber);
			Date meterDate = (Date) usageObject[1];
			intervalDataHistory.setMeterDate(meterDate);
			logger.debug("Meter date is" + meterDate);
			BigDecimal meterReading = (BigDecimal) usageObject[2];
			intervalDataHistory.setMeterReading(meterReading);
			logger.debug("Meeter reading  is" + meterReading);
			BigDecimal temperature = (BigDecimal) usageObject[3];
			intervalDataHistory.setTemprature(temperature);
			logger.debug("tempearture is" + temperature);
			intervalDataHistory.setBillingCycleStartdate(billCycleStartDate);
			logger.debug("Billing cycle start date is" + billCycleStartDate);
			intervalDataHistory.setBillingCycleEnddate(billCycleEndDate);
			logger.debug("Billing cycle end date is" + billCycleEndDate);
			intervalDataHistories.add(intervalDataHistory);
		}
		intervalDataHistoryResponse.setIntervalDataHistory(intervalDataHistories);

		// Calculate aggregated demand peak consumption(kwh)
		BillingPeakHour billingPeakHour = billingPeakHourRepository.findOne(1);

		if (billingPeakHour != null) {
			aggregatedPeakDemandKwh = customerMeterIntervalDataRepository.findAggregatedPeakConsumption(
					billCycleStartDate, billCycleEndDate, customerMeter, billingPeakHour.getStartPeakHour(),
					billingPeakHour.getEndPeakHour());
		}

		// Set aggregatedPeakDemandKwh to IntervaldataHistoryresponse
		intervalDataHistoryResponse.setAggregatedPeakDemandKwh(aggregatedPeakDemandKwh);
		return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get usage history hourly
	 * @param OfDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-08-09
	 */
	@Override
	public ResponseEntity<IntervalDataHistoryResponse> getUsageHistoryHourly(String OfDate, Long meterNumber)
			throws ParseException {
		logger.info("Entered into getUsageHistoryHourly service method.......");
		java.sql.Date sqlOfDate = null;
		
		//aggregate pick hour and kwh added by zameer 06 Sept 2017
		BigDecimal aggregatedPeakDemandKwh = null;
		IntervalDataHistoryResponse intervalDataHistoryResponse = new IntervalDataHistoryResponse();

		// Convert to sql date(CurrentDate-1)
		sqlOfDate = DateUtility.getSqlDateFromStringDate(OfDate);
		logger.debug("Sql  Date for Current Hourly History is" + sqlOfDate.toString());

		// Set meter number to CustomerMeter
		CustomerMeter customerMeter = new CustomerMeter();
		BigDecimal newMeterNumber = new BigDecimal(meterNumber);
		customerMeter.setMeterNumber(newMeterNumber);

		// Call usage history with start date and end date
		List<Object[]> usageObjects = customerMeterIntervalDataRepository.findUsageHistoryHourly(sqlOfDate,
				customerMeter);
		List<IntervalDataHistory> intervalDataHistories = new ArrayList<IntervalDataHistory>();
		for (Object[] usageObject : usageObjects) {
			IntervalDataHistory intervalDataHistory = new IntervalDataHistory();
			BigDecimal meternumber = (BigDecimal) usageObject[0];
			intervalDataHistory.setMeternumber(meternumber);
			logger.debug("Meter no is" + meternumber);
			Date meterDate = (Date) usageObject[1];
			intervalDataHistory.setMeterDate(meterDate);
			logger.debug("Meter date is" + meterDate);
			BigDecimal meterReading = (BigDecimal) usageObject[2];
			intervalDataHistory.setMeterReading(meterReading);
			logger.debug("daily load is" + meterReading);
			BigDecimal temperature = (BigDecimal) usageObject[3];
			intervalDataHistory.setTemprature(temperature);
			logger.debug("tempearture is" + temperature);
			Time metertime = (Time) usageObject[4];
			intervalDataHistory.setMeterTime(metertime);
			logger.debug("Meter time  is" + metertime);
			intervalDataHistories.add(intervalDataHistory);
		}
		
		// Calculate aggregated demand peak consumption(kwh)
				BillingPeakHour billingPeakHour = billingPeakHourRepository.findOne(1);

				if (billingPeakHour != null) {
					aggregatedPeakDemandKwh = customerMeterIntervalDataRepository.findAggregatedPeakConsumptionHourly(
							sqlOfDate, customerMeter, billingPeakHour.getStartPeakHour(),billingPeakHour.getEndPeakHour());
				}
				
				intervalDataHistoryResponse.setIntervalDataHistory(intervalDataHistories);
				intervalDataHistoryResponse.setAggregatedPeakDemandKwh(aggregatedPeakDemandKwh);
		return new ResponseEntity<IntervalDataHistoryResponse>(intervalDataHistoryResponse, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to terminate account service
	 * @param currentDate String
	 * @param accountId Integer
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-18
	 */
	@Override
	@Transactional
	public ResponseEntity<String> terminateAccountService(String currentDate, Integer accountId) throws ParseException {
		Date currentDateSql = DateUtility.getSqlDateFromStringDate(currentDate);
		Integer response = customerAccountRepository.terminateAccountService(currentDateSql, accountId);
		if (response > 0) {
			return new ResponseEntity<String>(CommonMessages.MSG_SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(CommonMessages.MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	/**
	 * @author Nitin K.
	 * @purpose to do the bill adjustment of selected bill
	 * @param billAdjustment BillAdjustment
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	@Override
	public ResponseEntity<String> doBillAdjustment(BillAdjustment billAdjustment) throws ParseException {
		logger.info("Entered into doBillAdjustment service method.......");

		System.out.println("Bill adjustment Id is" + billAdjustment.getBillAdjustmentId());

		BillAdjustment savedBillAdjustment = billAdjustmentRepository.save(billAdjustment);

		// Check savedBillAdjustment is null or not
		if (savedBillAdjustment == null) {
			return new ResponseEntity<String>(CommonMessages.MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		BillAdjustmentHistoryTMS billAdjustementHistory = mapper.map(savedBillAdjustment, BillAdjustmentHistoryTMS.class);
		billAdjustmentHistoryRepository.save(billAdjustementHistory);
		
		return new ResponseEntity<String>(CommonMessages.MSG_SUCCESS, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @return ResponseEntity<CustomerAccount>
	 * @date 2017-08-24
	 */
	@Override
	public ResponseEntity<CustomerAccount> searchCustomerByAccountId(Integer accountId) {
		CustomerAccount customerAccount = customerAccountRepository.findByAccountId(accountId);

		// Check customerAccount is not null before adding to list
		if (customerAccount == null) {
			return new ResponseEntity<CustomerAccount>(customerAccount, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<CustomerAccount>(customerAccount, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get customer bill charge configuration
	 * @param billChargeConfigurationDTO BillChargeConfigurationDTO
	 * @return ResponseEntity<BillChargeResponse>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	@Override
	public ResponseEntity<BillChargeResponse> getCustomerBillChargeConfiguration(
			BillChargeConfigurationDTO billChargeConfigurationDTO) throws ParseException {
		logger.info("Entered into getCustomerBillChargeConfiguration service method.......");

		BigDecimal taxCharges = null;
		BigDecimal totalAmount = new BigDecimal(0.0);

		// Create BillChargeResponse Object
		BillChargeResponse billChargeResponse = new BillChargeResponse();

		String tarrifPlan = billChargeConfigurationDTO.getTarrifPlan();

		// Check Plan charge
		if (tarrifPlan != null) {
			if (tarrifPlan.equalsIgnoreCase("TOU")) {
				totalAmount = totalAmount.add(Const.TOU);
				billChargeResponse.setTarrifPlan(Const.TOU.toString());
			} else if (tarrifPlan.equalsIgnoreCase("TIERED")) {

				totalAmount = totalAmount.add(Const.TIRED);
				billChargeResponse.setTarrifPlan(Const.TIRED.toString());
			} else if (tarrifPlan.equalsIgnoreCase("EV")) {

				totalAmount = totalAmount.add(Const.EV);
				billChargeResponse.setTarrifPlan(Const.EV.toString());
			}
		}

		// Process map of charges
		BillingChargesDTO charges = billChargeConfigurationDTO.getCharges();
		if (charges != null) {
			if (charges.getNetworkCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.networkCharges);
				billChargeResponse.setNetworkCharges(Const.networkCharges.toString());
			}
			if (charges.getCongestionCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.congestionCharges);
				billChargeResponse.setCongestionCharges(Const.congestionCharges.toString());
			}
			if (charges.getFixedCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.fixedCharges);
				billChargeResponse.setFixedCharges(Const.fixedCharges.toString());
			}

			if (charges.getDeemandCharges().equals(true)) {
				totalAmount = totalAmount.add(Const.deemandCharges);
				billChargeResponse.setDeemandCharges(Const.deemandCharges.toString());
			}

			if (charges.getLoadSizeCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.loadSizeCharges);
				billChargeResponse.setLoadSizeCharges(Const.loadSizeCharges.toString());
			}
			if (charges.getEnergyComissionCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.energyComissionCharges);
				billChargeResponse.setEnergyComissionCharges(Const.energyComissionCharges.toString());
			}
			if (charges.getMiscCharges().equals(true)) {

				totalAmount = totalAmount.add(Const.miscCharges);
				billChargeResponse.setMiscCharges(Const.miscCharges.toString());
			}
			if (charges.getTaxCharges().equals(true)) {
				BigDecimal taxPercentage = new BigDecimal(0.08);
				System.out.println("total amount is" + totalAmount);
				taxCharges = totalAmount.multiply(taxPercentage);
				totalAmount = totalAmount.add(taxCharges);
				System.out.println("Tax Charges is" + taxCharges);
				billChargeResponse.setTaxCharges(taxCharges.toString());
			}
		}
		billChargeResponse.setTotalAmount(totalAmount.toString());

		return new ResponseEntity<BillChargeResponse>(billChargeResponse, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param customerAccountId Integer
	 * @return ResponseEntity<BillChargeConfiguration>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	@Override
	public ResponseEntity<BillChargeConfiguration> getBillChargeConfigurationByCustomerAccountId(
			Integer customerAccountId) {
		// Create CustomerAccount object
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setAccountId(customerAccountId);

		BillChargeConfiguration billChargeConfiguration = billChargeConfigurationRespository
				.findBillChargeCfgByAcctId(customerAccount);

		// Check billChargeconfiguration null or not
		if (billChargeConfiguration == null) {
			return new ResponseEntity<BillChargeConfiguration>(billChargeConfiguration, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BillChargeConfiguration>(billChargeConfiguration, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param billChargeRequestDTO BillChargeRequestDTO
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	@Override
	@Transactional
	public ResponseEntity<String> saveCustomerBillChargeConfiguration(BillChargeRequestDTO billChargeRequestDTO)
			throws ParseException {
		boolean isUpDate = false;
		BillChargeConfiguration savedBillChargeConfiguration = null;

		// Check termination date is null or not
		if (billChargeRequestDTO.getTerminationDate() != null) {
			isUpDate = true;
		}

		// Create new BillChargeConfiguration to save it as new Object
		// without termination date
		BillChargeConfiguration newBillChargeConfiguration = new BillChargeConfiguration();

		BillingChargesDTO chargesDTO = billChargeRequestDTO.getCharges();
		newBillChargeConfiguration.setTarrifPlan(billChargeRequestDTO.getTarrifPlan());
		newBillChargeConfiguration.setNetworkCharges(chargesDTO.getNetworkCharges());
		newBillChargeConfiguration.setCongestionCharges(chargesDTO.getCongestionCharges());
		newBillChargeConfiguration.setFixedCharges(chargesDTO.getFixedCharges());
		newBillChargeConfiguration.setDeemandCharges(chargesDTO.getDeemandCharges());
		newBillChargeConfiguration.setLoadSizeCharges(chargesDTO.getLoadSizeCharges());
		newBillChargeConfiguration.setEnergyComissionCharges(chargesDTO.getEnergyComissionCharges());
		newBillChargeConfiguration.setTaxCharges(chargesDTO.getTaxCharges());
		newBillChargeConfiguration.setMiscCharges(chargesDTO.getMiscCharges());
		newBillChargeConfiguration.setEffectiveDate(billChargeRequestDTO.getEffectiveDate());
		newBillChargeConfiguration.setCustomerAccount(billChargeRequestDTO.getCustomerAccount());

		if (isUpDate == true) {
			billChargeConfigurationRespository.updateBillChargeCfgTerminationDate(
					billChargeRequestDTO.getTerminationDate(), billChargeRequestDTO.getBillChargeConfigurationId());
			// Save current object as new Object
			savedBillChargeConfiguration = billChargeConfigurationRespository.save(newBillChargeConfiguration);
		}

		else {
			savedBillChargeConfiguration = billChargeConfigurationRespository.save(newBillChargeConfiguration);
		}

		if (savedBillChargeConfiguration == null) {
			return new ResponseEntity<String>(CommonMessages.MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<String>(CommonMessages.MSG_SUCCESS, HttpStatus.OK);

	}

	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment by CustomerBill Id
	 * @param customerBillId Integer
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<BillAdjustment> getBillAdjustmentByCustomerBill(Integer customerBillId) {
		logger.info("Entered into getBillAdjustmentByCustomerBill service method.......");

		// Create CustomerBill Object
		CustomerBill customerBill = new CustomerBill();
		customerBill.setCustomerBillId(customerBillId);
		List<BillAdjustment> billAdjustmentList = billAdjustmentRepository.findByCustomerBillList(customerBillId);
		// Check it is null or not
		BillAdjustment billAdjustment=null;
		if (billAdjustmentList == null || billAdjustmentList.isEmpty()) {
			return new ResponseEntity<BillAdjustment>(billAdjustment, HttpStatus.NOT_FOUND);
		}
		billAdjustment = billAdjustmentList.get(0);
		return new ResponseEntity<BillAdjustment>(billAdjustment, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to update bill adjustment
	 * @param billAdjustment BillAdjustment
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<String> updateBillAdjustment(BillAdjustment billAdjustment) throws ParseException {
		logger.info("Entered into updateBillAdjustment service method.......");

		System.out.println("Bill adjustment Id is" + billAdjustment.getBillAdjustmentId());

		BillAdjustment updatedBillAdjustment = billAdjustmentRepository.save(billAdjustment);

		// Check it is null or not
		if (updatedBillAdjustment == null) {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<String>(UrlPathConstants.UPDATE_SUCCESS, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to configure billing peak hour
	 * @param billingPeakHour BillingPeakHour
	 * @return ResponseEntity<String>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<String> configurePeakHour(BillingPeakHour billingPeakHour) {
		logger.info("Entered into configurePeakHour service method.......");

		System.out.println("Id is" + billingPeakHour.getBillingPeakHourId());

		BillingPeakHour savedBillingPeakHour = billingPeakHourRepository.save(billingPeakHour);

		// Check savedBillAdjustment is null or not
		if (savedBillingPeakHour == null) {
			return new ResponseEntity<String>(CommonMessages.MSG_ERROR, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<String>(CommonMessages.MSG_SUCCESS, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get billing peak hour settings
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<BillingPeakHour> getPeakHourSettings() {
		BillingPeakHour billingPeakHour = billingPeakHourRepository.findOne(1);

		if (billingPeakHour == null) {
			return new ResponseEntity<BillingPeakHour>(billingPeakHour, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BillingPeakHour>(billingPeakHour, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get BillAdjustmentHistory list by billId
	 * @param billId Integer
	 * @return ResponseEntity<List<BillAdjustmentHistory>>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<List<BillAdjustmentHistory>> getBillAdjustementHistory(Integer billId) {
		List<BillAdjustmentHistory> billAdjustmentHistoryList = new ArrayList<BillAdjustmentHistory>();

		List<Object[]> usageObjects = billAdjustmentRepository.findCustomerBillHistoryByBillId(billId);

		// Check list is empty or not
		if (usageObjects.isEmpty() && usageObjects.size() == 0) {
			return new ResponseEntity<List<BillAdjustmentHistory>>(billAdjustmentHistoryList, HttpStatus.NOT_FOUND);
		}

		// Set the required parameters to CustomerBillHistory DTO
		for (Object[] usageObject : usageObjects) {
			// Create CustomerBillHistory DTO Object
			BillAdjustmentHistory billAdjustmentHistory = new BillAdjustmentHistory();
			//Integer customerBillId = (Integer) usageObject[0];
			//billAdjustmentHistory.setBillAdjustmentId(customerBillId);
			
			Date creationDate = (Date) usageObject[0];
			billAdjustmentHistory.setAdjsutmentCreationDate(creationDate);
			
			BigDecimal amount = (BigDecimal) usageObject[1];
			billAdjustmentHistory.setAdjustmentAmount(amount);
			
			
			String status = (String) usageObject[3];
			billAdjustmentHistory.setAdjustmentStatus(status);
			
			String type = (String) usageObject[4];
			billAdjustmentHistory.setAdjustmentType(type);
			
			
			billAdjustmentHistoryList.add(billAdjustmentHistory);

		}

		return new ResponseEntity<List<BillAdjustmentHistory>>(billAdjustmentHistoryList, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to parse a csv file
	 * @param file MultipartFile
	 * @return ResponseEntity<IntervalDataManagementResponse>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<IntervalDataManagementResponse> readCSVFile(MultipartFile file) {
		
		BufferedReader readRechargeDuniyaInputFile = null;
		List<IntervalDataManagement> intervalDataManagementsList = new ArrayList<IntervalDataManagement>();
		IntervalDataManagementResponse intervalDataManagementResponse = new IntervalDataManagementResponse();
		String line;
        FileInputStream inp = null;
		try {
			inp = (FileInputStream) file.getInputStream();
		} catch (IOException e1) {
			logger.error("Error occured"+e1);
			intervalDataManagementResponse.setMessage("File Not Found");
			return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
		}
        readRechargeDuniyaInputFile = new BufferedReader(new InputStreamReader(inp));
        try {
        	int count = 0;
			while ((line = readRechargeDuniyaInputFile.readLine()) != null) {
				
				if(count==0){
					count++;
					continue;
				}
			        // use comma as separator
			        String[] rechargeDuniyaDetails = line.split(",");
			        if(rechargeDuniyaDetails.length >= 3) {
			            String splitPrefix = rechargeDuniyaDetails[0];
			            IntervalDataManagement intervalDataManagement = new IntervalDataManagement();
			            if(splitPrefix != null && splitPrefix.trim().length() > 0) {
			            	intervalDataManagement.setCustomerAccountNumber(splitPrefix);
			            	intervalDataManagement.setMeterNumber(rechargeDuniyaDetails[1]);
			            	intervalDataManagement.setMonths(rechargeDuniyaDetails[2]);
			            	intervalDataManagement.setkWh(rechargeDuniyaDetails[3]);
			            	intervalDataManagementsList.add(intervalDataManagement);
			            } 
			        } else {
			        	intervalDataManagementResponse.setMessage("Given file is not as per montly interval data management");
			        	//return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
			        }
			}
			intervalDataManagementResponse.setIntervalDataManagementDailyList(intervalDataManagementsList);
			
			 readRechargeDuniyaInputFile.close();
		}catch (IOException e) {
			logger.error("Error occured"+e);
			intervalDataManagementResponse.setMessage("Something went wrong try after some time");
        	return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
		}
       
        return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.OK);
	}
	
	/**
	 * @author Nitin K.
	 * @purpose to parse a csv monthly file
	 * @param file MultipartFile
	 * @return ResponseEntity<IntervalDataManagementResponse>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<IntervalDataManagementResponse> readCSVMonthly(MultipartFile file) {
		
		BufferedReader readRechargeDuniyaInputFile = null;
		List<IntervalDataManagementMonthly> intervalDataManagementsList = new ArrayList<IntervalDataManagementMonthly>();
		IntervalDataManagementResponse intervalDataManagementResponse = new IntervalDataManagementResponse();
		String line;
        FileInputStream inp = null;
		try {
			inp = (FileInputStream) file.getInputStream();
		} catch (IOException e1) {
			logger.error("Error occured"+e1);
			intervalDataManagementResponse.setMessage("File Not Found");
			return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
		}
        readRechargeDuniyaInputFile = new BufferedReader(new InputStreamReader(inp));
        try {
        	int count = 0;
			while ((line = readRechargeDuniyaInputFile.readLine()) != null) {
				
				if(count==0){
					count++;
					continue;
				}
			        // use comma as separator
			        String[] rechargeDuniyaDetails = line.split(",");
			        if(rechargeDuniyaDetails.length >= 27) {
			            String splitPrefix = rechargeDuniyaDetails[0];
			            IntervalDataManagementMonthly intervalDataManagement = new IntervalDataManagementMonthly();
			            if(splitPrefix != null && splitPrefix.trim().length() > 0) {
			            	intervalDataManagement.setCustomerAccountNumber(splitPrefix);
			            	intervalDataManagement.setMeterNumber(rechargeDuniyaDetails[1]);
			            	intervalDataManagement.setMonths(rechargeDuniyaDetails[2]);
			            	intervalDataManagement.setINT01((rechargeDuniyaDetails[3]));
			            	intervalDataManagement.setINT02((rechargeDuniyaDetails[4]));
			            	intervalDataManagement.setINT03((rechargeDuniyaDetails[5]));
			            	intervalDataManagement.setINT04((rechargeDuniyaDetails[6]));
			            	intervalDataManagement.setINT05((rechargeDuniyaDetails[7]));
			            	intervalDataManagement.setINT06((rechargeDuniyaDetails[8]));
			            	intervalDataManagement.setINT07((rechargeDuniyaDetails[9]));
			            	intervalDataManagement.setINT08((rechargeDuniyaDetails[10]));
			            	intervalDataManagement.setINT09((rechargeDuniyaDetails[11]));
			            	intervalDataManagement.setINT10((rechargeDuniyaDetails[12]));
			            	intervalDataManagement.setINT11((rechargeDuniyaDetails[13]));
			            	intervalDataManagement.setINT12((rechargeDuniyaDetails[14]));
			            	intervalDataManagement.setINT13((rechargeDuniyaDetails[15]));
			            	intervalDataManagement.setINT14((rechargeDuniyaDetails[16]));
			            	intervalDataManagement.setINT15((rechargeDuniyaDetails[17]));
			            	intervalDataManagement.setINT16((rechargeDuniyaDetails[18]));
			            	intervalDataManagement.setINT17((rechargeDuniyaDetails[19]));
			            	intervalDataManagement.setINT18((rechargeDuniyaDetails[20]));
			            	intervalDataManagement.setINT19((rechargeDuniyaDetails[21]));
			            	intervalDataManagement.setINT20((rechargeDuniyaDetails[22]));
			            	intervalDataManagement.setINT21((rechargeDuniyaDetails[23]));
			            	intervalDataManagement.setINT22((rechargeDuniyaDetails[24]));
			            	intervalDataManagement.setINT23((rechargeDuniyaDetails[25]));
			            	intervalDataManagement.setINT24((rechargeDuniyaDetails[26]));
			            	
			            	intervalDataManagementsList.add(intervalDataManagement);
			            } else {
			                
			            }
			        } else {
			        	intervalDataManagementResponse.setMessage("Given file is not as per daily interval data management");
			        	//return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
			        }
			}
			
			intervalDataManagementResponse.setIntervalDataManagementMonthlyList(intervalDataManagementsList);
			
			 readRechargeDuniyaInputFile.close();
		}catch (IOException e) {
			logger.error("Error occured"+e);
			intervalDataManagementResponse.setMessage("Something went wrong try after some time");
        	return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.NOT_FOUND);
		}
       
        return new ResponseEntity<IntervalDataManagementResponse>(intervalDataManagementResponse, HttpStatus.OK);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment list
	 * @param accountId Integer
	 * @return ResponseEntity<List<BillAdjustment>>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<List<BillAdjustment>> getBillAdjustmentList(Integer accountId) {
		// TODO Auto-generated method stub

				logger.info("Entered into getBillAdjustmentList service method.......");
				
				CustomerAccount customerAccount = new CustomerAccount();
				customerAccount.setAccountId(accountId);
				List<BillAdjustment> billAdjustmentList = new ArrayList<BillAdjustment>();
				List<BillAdjustment> list = new ArrayList<BillAdjustment>();
				//System.out.println("ACCT_ID------------"+accountId);
					// Call the CustomerBill with given bill cycle dates
				List<CustomerBill> customerBills = customerBillRepository.findCustomerBillByCustomerAccountId(customerAccount);
				
				//System.out.println("customerBills------------"+customerBills.size());
				if(customerBills!=null && !customerBills.isEmpty()){
					for(CustomerBill customerBill : customerBills){
						if(customerBill!=null && customerBill.getCustomerBillId()!=null){
							list = billAdjustmentRepository.findByCustomerBillList(customerBill.getCustomerBillId());
							//System.out.println("list------------"+list.size());
							if(list!=null && !list.isEmpty()){
								for(BillAdjustment billAdjustment:list){
									//System.out.println("BillAdjustment********"+billAdjustment);
									billAdjustmentList.add(billAdjustment);
								}
							}
						}
						
					}
				}

				//List<BillAdjustment> billAdjustment = new ArrayList<BillAdjustment>();
				
				//billAdjustment = (List<BillAdjustment>) billAdjustmentRepository.findAll();
				logger.info("List size:"+billAdjustmentList.size());
				// Check it is null or not
				if (billAdjustmentList == null || billAdjustmentList.isEmpty()) {
					return new ResponseEntity<List<BillAdjustment>>(billAdjustmentList, HttpStatus.NOT_FOUND);
				}

				return new ResponseEntity<List<BillAdjustment>>(billAdjustmentList, HttpStatus.OK);
	}
	
	
	
	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment history list
	 * @param accountId Integer
	 * @return ResponseEntity<List<BillAdjustmentHistoryTMS>>
	 * @date 2017-08-29
	 */
	@Override
	public ResponseEntity<List<BillAdjustmentHistoryTMS>> getBillAdjustmentHistoryList(Integer accountId) {
		logger.info("Entered into getBillAdjustmentList service method.......");
				
				CustomerAccount customerAccount = new CustomerAccount();
				customerAccount.setAccountId(accountId);
				List<BillAdjustmentHistoryTMS> billAdjustmentList = new ArrayList<BillAdjustmentHistoryTMS>();
				List<BillAdjustmentHistoryTMS> list = new ArrayList<BillAdjustmentHistoryTMS>();
				//System.out.println("ACCT_ID------------"+accountId);
					// Call the CustomerBill with given bill cycle dates
				List<CustomerBill> customerBills = customerBillRepository.findCustomerBillByCustomerAccountId(customerAccount);
				
				//System.out.println("customerBills------------"+customerBills.size());
				if(customerBills!=null && !customerBills.isEmpty()){
					for(CustomerBill customerBill : customerBills){
						if(customerBill!=null && customerBill.getCustomerBillId()!=null){
							list = billAdjustmentHistoryRepository.findByCustomerBillList(customerBill.getCustomerBillId());
							//System.out.println("list------------"+list.size());
							if(list!=null && !list.isEmpty()){
								for(BillAdjustmentHistoryTMS billAdjustment:list){
									//System.out.println("BillAdjustment********"+billAdjustment);
									billAdjustmentList.add(billAdjustment);
								}
							}
						}
						
					}
				}

				//List<BillAdjustment> billAdjustment = new ArrayList<BillAdjustment>();
				
				//billAdjustment = (List<BillAdjustment>) billAdjustmentRepository.findAll();
				logger.info("List size:"+billAdjustmentList.size());
				// Check it is null or not
				if (billAdjustmentList == null || billAdjustmentList.isEmpty()) {
					return new ResponseEntity<List<BillAdjustmentHistoryTMS>>(billAdjustmentList, HttpStatus.NOT_FOUND);
				}

				return new ResponseEntity<List<BillAdjustmentHistoryTMS>>(billAdjustmentList, HttpStatus.OK);
	}

}
