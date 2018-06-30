package com.ge.tms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.dto.CustomerBillHistory;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerMeterHistory;
import com.ge.tms.service.AccountHistoryService;

/**
 * @author Nitin K.
 * Contains APIs for getting history details of customer account, bills, meters.
 */
@RestController
@RequestMapping(value = UrlPathConstants.ACCOUNT_HISTORY)
public class AccountHistoryController {

	private static final Logger logger = LoggerFactory.getLogger(AccountHistoryController.class);

	@Autowired
	private AccountHistoryService accountHistoryService;

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Account History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>
	 * @date 2017-08-17
	 */
	@RequestMapping(value = UrlPathConstants.CUSTOMER_ACCOUNT_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerAccount>> getCustomerAccountHistoryByAcctId(
			@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into getCustomerAccountHistoryByAcctId controller.......");
		ResponseEntity<List<CustomerAccount>> result = accountHistoryService
				.getCustomerAccountHistoryByAcctId(accountId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Bill History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerBillHistory>>
	 * @date 2017-08-17
	 */
	@RequestMapping(value = UrlPathConstants.CUSTOMER_BILL_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerBillHistory>> getCustomerBillHistoryByAcctId(
			@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into getCustomerBillHistoryByAcctId controller.......");
		ResponseEntity<List<CustomerBillHistory>> result = accountHistoryService
				.getCustomerBillHistoryByAcctId(accountId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Meter History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerMeterHistory>>
	 * @date 2017-08-21
	 */
	@RequestMapping(value = UrlPathConstants.CUSTOMER_METER_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerMeterHistory>> getCustomerMeterHistoryByAcctId(
			@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into getCustomerMeterHistoryByAcctId controller.......");
		ResponseEntity<List<CustomerMeterHistory>> result = accountHistoryService
				.getCustomerMeterHistoryByAcctId(accountId);
		return result;
	}
}