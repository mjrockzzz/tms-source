package com.ge.tms.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.config.EmailService;
import com.ge.tms.dto.BillAdjustmentHistory;
import com.ge.tms.dto.BillChargeConfigurationDTO;
import com.ge.tms.dto.BillChargeRequestDTO;
import com.ge.tms.dto.BillChargeResponse;
import com.ge.tms.dto.FinancialDetails;
import com.ge.tms.dto.IntervalDataHistoryResponse;
import com.ge.tms.dto.IntervalDataManagementResponse;
import com.ge.tms.dto.UsageHistory;
import com.ge.tms.dto.WSCustomerAccount;
import com.ge.tms.entity.BillAdjustment;
import com.ge.tms.entity.BillAdjustmentHistoryTMS;
import com.ge.tms.entity.BillChargeConfiguration;
import com.ge.tms.entity.BillingPeakHour;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.service.AccountService;

/**
 * @author Nitin K.
 * Contains APIs for getting customers, customer data, bill history, interval history, mail sending etc.
 */
@RestController
@RequestMapping(value = UrlPathConstants.ACCOUNT)
public class AccountController {
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EmailService emailService;

	/**
	 * @author Nitin K.
	 * @purpose to get top 10 customer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	@RequestMapping(value = UrlPathConstants.SEARCH_ALL_CUSTOMER, method = RequestMethod.GET)
	public ResponseEntity<List<WSCustomerAccount>> searchAllCustomer() {
		logger.info("Entered into searchCustomerByAcctId controller.......");
		return accountService.searchAllCustomer();
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @param activeAccount String
	 * @param inActiveAccount String
	 * @return ResponseEntity<CustomerAccount>
	 * @date 2017-07-19
	 */
	@RequestMapping(value = UrlPathConstants.SEARCH_CUSTOMER_BY_ACCT_ID, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAcctId(
			@RequestParam(value = "accountId") Integer accountId,
			@RequestParam(value = "activeAccount") String activeAccount,
			@RequestParam(value = "inActiveAccount") String inActiveAccount) {
		logger.info("Entered into searchCustomerByAcctId controller.......");
		ResponseEntity<List<CustomerAccount>> result = accountService.searchCustomerByAcctId(accountId, activeAccount,
				inActiveAccount);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by meter number
	 * @param meterNumber Long
	 * @return  ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	@RequestMapping(value = UrlPathConstants.SEARCH_CUSTOMER_BY_METER_NUMBER, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerAccount>> searchCustomerByMeter(
			@RequestParam(value = "meterNumber") Long meterNumber) {
		logger.info("Entered into searchCustomerByMeter controller.......");
		ResponseEntity<List<CustomerAccount>> result = accountService.searchCustomerByMeterNumber(meterNumber);	
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id without account status
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-20
	 */
	@RequestMapping(value = UrlPathConstants.SEARCH_CUSTOMER_BY_ACCT_ID_NO_STATUS, method = RequestMethod.GET)
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAccountIdNoStatus(
			@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into searchCustomerByAccountIdNoStatus controller.......");
		ResponseEntity<List<CustomerAccount>> result = accountService.searchCustomerByAccountIdNoStatus(accountId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Details
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	@RequestMapping(value = UrlPathConstants.UPDATE_CUSTOMER_DETAILS, method = RequestMethod.POST)
	public ResponseEntity<String> updateCustomerDetails(@RequestBody CustomerAccount customerAccount) {
		logger.info("Entered into updateCustomerAccount controller.......");
		ResponseEntity<String> result = accountService.updateCustomerDetails(customerAccount);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Account Details
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	@RequestMapping(value = UrlPathConstants.UPDATE_CUSTOMER_ACCOUNT_DETAILS, method = RequestMethod.POST)
	public ResponseEntity<String> updateCustomerAccountDetails(@RequestBody CustomerAccount customerAccount) {
		logger.info("Entered into updateCustomerAccount controller.......");
		ResponseEntity<String> result = accountService.updateCustomerAccountDetails(customerAccount);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Financial Details
	 * @param financialDetails FinancialDetails
	 * @return ResponseEntity<String>
	 * @date 2017-07-24
	 */
	@RequestMapping(value = UrlPathConstants.UPDATE_CUSTOMER_FINANCIAL_DETAILS, method = RequestMethod.POST)
	public ResponseEntity<String> updateCustomerFinancial(@RequestBody FinancialDetails financialDetails) {
		logger.info("Entered into updateCustomerFinancial controller.......");
		ResponseEntity<String> result = accountService.updateCustomerFinancial(financialDetails);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get bill history as per given custom dates By billSentDate
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<List<UsageHistory>>
	 * @throws ParseException
	 * @date 2017-07-24
	 */
	@RequestMapping(value = UrlPathConstants.BILL_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<UsageHistory>> getBillHistoryByCustomDates(
			@RequestParam(value = "accountId") Integer accountId, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("meterNumber") Long meterNumber)
			throws ParseException {
		logger.info("Entered into getBillHistoryByBillCycleDates controller.......");
		ResponseEntity<List<UsageHistory>> result = accountService.getBillHistory(accountId, startDate, endDate,
				meterNumber, true);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get all bill history
	 * @param accountId Integer
	 * @param meterNumber Long
	 * @return ResponseEntity<List<UsageHistory>>
	 * @throws ParseException
	 * @date 2017-07-24
	 */
	@RequestMapping(value = UrlPathConstants.ALL_BILL_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<UsageHistory>> getAllBillHistoryByMeterNumber(
			@RequestParam(value = "accountId") Integer accountId, @RequestParam("meterNumber") Long meterNumber)
			throws ParseException {
		logger.info("Entered into getAllBillHistoryByMeterNumber controller.......");
		ResponseEntity<List<UsageHistory>> result = accountService.getBillHistory(accountId, null, null, meterNumber,
				false);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get interval data usage history of current bill cycle
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-07-25
	 */
	@RequestMapping(value = UrlPathConstants.INTERVAL_DATA_USAGE_HISTORY_CURRENT_BILL_CYCLE, method = RequestMethod.GET)
	public ResponseEntity<IntervalDataHistoryResponse> getIntervalDataHistoryCurrentBillCycle(
			@RequestParam(value = "accountId") Integer accountId, @RequestParam("meterNumber") Long meterNumber)
			throws ParseException {
		logger.info("Entered into getIntervalDataHistoryCurrentBillCycle controller.......");
		ResponseEntity<IntervalDataHistoryResponse> result = accountService.getIntervalDataUsageHistory(accountId, null,
				null, meterNumber, false);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get interval data usage history of previous bill cycle
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-07-25
	 */
	@RequestMapping(value = UrlPathConstants.INTERVAL_DATA_USAGE_HISTORY_PREVIOUS_BILL_CYCLE, method = RequestMethod.GET)
	public ResponseEntity<IntervalDataHistoryResponse> getIntervalDataHistoryPreviousBillCycle(
			@RequestParam(value = "accountId") Integer accountId, @RequestParam("meterNumber") Long meterNumber)
			throws ParseException {
		logger.info("Entered into getIntervalDataHistoryPreviousBillCycle controller.......");
		ResponseEntity<IntervalDataHistoryResponse> result = accountService.getIntervalDataUsageHistory(accountId, null,
				null, meterNumber, true);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get interval data usage history of previous bill cycle
	 * @param accountId Integer
	 * @param startDate String
	 * @param endDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-07-25
	 */
	@RequestMapping(value = UrlPathConstants.INTERVAL_DATA_USAGE_HISTORY_CUSTOM_BILL_CYCLE, method = RequestMethod.GET)
	public ResponseEntity<IntervalDataHistoryResponse> getIntervalDataHistoryCustomBillCycle(
			@RequestParam(value = "accountId") Integer accountId, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("meterNumber") Long meterNumber)
			throws ParseException {
		logger.info("Entered into getIntervalDataHistoryCustomBillCycle controller.......");
		ResponseEntity<IntervalDataHistoryResponse> result = accountService.getIntervalDataUsageHistory(accountId,
				startDate, endDate, meterNumber, false);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get usage history hourly
	 * @param OfDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<List<IntervalDataHistory>>
	 * @throws ParseException
	 * @date 2017-08-09
	 */
	@RequestMapping(value = UrlPathConstants.INTERVAL_DATA_USAGE_HISTORY_HOURLY, method = RequestMethod.GET)
	public ResponseEntity<IntervalDataHistoryResponse> getUsageHistoryHourly(@RequestParam("OfDate") String OfDate,
			@RequestParam("meterNumber") Long meterNumber) throws ParseException {
		logger.info("Entered into getUsageHistoryHourly controller.......");
		return accountService.getUsageHistoryHourly(OfDate, meterNumber);
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
	@RequestMapping(value = UrlPathConstants.TERMINATE_ACCOUNT_SERVICE, method = RequestMethod.GET)
	public ResponseEntity<String> terminateAccountService(@RequestParam("currentDate") String currentDate,
			@RequestParam("accountId") Integer accountId) throws ParseException {
		logger.info("Entered into terminateAccountService controller.......");
		return accountService.terminateAccountService(currentDate, accountId);
	}

	/**
	 * @author Nitin K.
	 * @purpose to do the bill adjustment of selected bill
	 * @param billAdjustment BillAdjustment         
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	@RequestMapping(value = UrlPathConstants.DO_BILL_ADJUSTMENT, method = RequestMethod.POST)
	public ResponseEntity<String> doBillAdjustment(@RequestBody BillAdjustment billAdjustment) throws ParseException {
		logger.info("Entered into doBillAdjustment controller.......");
		return accountService.doBillAdjustment(billAdjustment);
	}

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @return ResponseEntity<CustomerAccount>
	 * @date 2017-08-24
	 */
	@RequestMapping(value = UrlPathConstants.SEARCH_CUSTOMER_BY_ACCOUNT, method = RequestMethod.GET)
	public ResponseEntity<CustomerAccount> searchCustomerByAccountId(
			@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into searchCustomerByAccountId controller.......");
		ResponseEntity<CustomerAccount> result = accountService.searchCustomerByAccountId(accountId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get customer bill charge configuration
	 * @param billChargeConfigurationDTO BillChargeConfigurationDTO
	 * @return ResponseEntity<BillChargeResponse>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	@RequestMapping(value = UrlPathConstants.GET_CUSTOMER_BILL_CHARGE_CONFIGURATION, method = RequestMethod.POST)
	public ResponseEntity<BillChargeResponse> getCustomerBillChargeConfiguration(
			@RequestBody BillChargeConfigurationDTO billChargeConfigurationDTO) throws ParseException {
		logger.info("Entered into getCustomerBillChargeConfiguration controller.......");
		ResponseEntity<BillChargeResponse> result = accountService
				.getCustomerBillChargeConfiguration(billChargeConfigurationDTO);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param customerAccountId Integer
	 * @return ResponseEntity<BillChargeConfiguration>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	@RequestMapping(value = UrlPathConstants.GET_CUSTOMER_BILL_CHARGE_CONFIGURATION_BY_ACCT_ID, method = RequestMethod.GET)
	public ResponseEntity<BillChargeConfiguration> getBillChargeConfigurationByCustomerAccountId(
			@RequestParam Integer customerAccountId){
		logger.info("Entered into getBillChargeConfigurationByCustomerAccountId controller.......");
		ResponseEntity<BillChargeConfiguration> result = accountService.getBillChargeConfigurationByCustomerAccountId(customerAccountId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param billChargeConfigurationDTO BillChargeConfigurationDTO
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_CUSTOMER_BILL_CHARGE_CONFIGURATION, method = RequestMethod.POST)
	public ResponseEntity<String> saveCustomerBillChargeConfiguration(
			@RequestBody BillChargeRequestDTO billChargeRequestDTO) throws ParseException {
		logger.info("Entered into saveCustomerBillChargeConfiguration controller.......");
		ResponseEntity<String> result = accountService.saveCustomerBillChargeConfiguration(billChargeRequestDTO);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment by CustomerBill Id
	 * @param customerBillId Integer
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-08-29
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_ADJUSTMENT_BY_CUSTOMER_BILL, method = RequestMethod.GET)
	public ResponseEntity<BillAdjustment> getBillAdjustmentByCustomerBill(
			@RequestParam(value = "customerBillId") Integer customerBillId) {
		logger.info("Entered into getBillAdjustmentByCustomerBill controller.......");
		ResponseEntity<BillAdjustment> result = accountService.getBillAdjustmentByCustomerBill(customerBillId);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @purpose to update bill adjustment
	 * @param billAdjustment BillAdjustment
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-29
	 */
	@RequestMapping(value = UrlPathConstants.UPDATE_BILL_ADJUSTMENT, method = RequestMethod.POST)
	public ResponseEntity<String> updateBillAdjustment(@RequestBody BillAdjustment billAdjustment)
			throws ParseException {
		logger.info("Entered into doBillAdjustment controller.......");
		return accountService.updateBillAdjustment(billAdjustment);
	}

	/**
	 * @author Nitin K.
	 * @purpose to configure billing peak hour
	 * @param billingPeakHour BillingPeakHour
	 * @return ResponseEntity<String>
	 * @date 2017-08-29
	 */
	@RequestMapping(value = UrlPathConstants.CONFIGURE_PEAK_HOUR, method = RequestMethod.POST)
	public ResponseEntity<String> configurePeakHour(@RequestBody BillingPeakHour billingPeakHour) {
		logger.info("Entered into configurePeakHour controller.......");
		return accountService.configurePeakHour(billingPeakHour);
	}

	/**
	 * @author Nitin K.
	 * @purpose to get billing peak hour settings
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-08-29
	 */
	@RequestMapping(value = UrlPathConstants.GET_PEAK_HOUR_SETTINGS, method = RequestMethod.GET)
	public ResponseEntity<BillingPeakHour> getPeakHourSettings() {
		logger.info("Entered into configurePeakHour controller.......");
		return accountService.getPeakHourSettings();
	}
	
	/**
	 * @author zameer  .
	 * @purpose to get bill adjustment history
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-09-04
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_ADJUSTMENT_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<BillAdjustmentHistory>> getBillAdjustementHistory(@RequestParam Integer customerBillId) {
		logger.info("Entered into getBillAdjustementHistory controller.......");
		return accountService.getBillAdjustementHistory(customerBillId);
	}
	
	/**
	 * @author zameer  .
	 * @purpose to get bill adjustment history
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-09-04
	 */
	@RequestMapping(value = UrlPathConstants.READ_CSV_FILE, method = RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<IntervalDataManagementResponse> readCSVFile(@RequestParam(required = false) MultipartFile file) {
		logger.info("Entered into readCSVFile controller.......");
		return accountService.readCSVFile(file);
	}
	
	/**
	 * @author zameer  .
	 * @purpose to get bill adjustment history
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-09-04
	 */
	@RequestMapping(value = UrlPathConstants.READ_CSV_MONTHLY, method = RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<IntervalDataManagementResponse> readCSVMonthly(@RequestParam(required = false) MultipartFile file) {
		logger.info("Entered into readCSVMonthly controller.......");
		return accountService.readCSVMonthly(file);
	}
	
	/**
	 * @author Jameer
	 * @purpose to get all bill adjustment 
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-09-13
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_ADJUSTMENT_LIST_BY_ACCT_ID, method = RequestMethod.GET)
	public ResponseEntity<List<BillAdjustment>> getBillAdjustmentList(@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into getBillAdjustmentList controller.......");
		return accountService.getBillAdjustmentList(accountId);
	}
	
	/**
	 * @author Jameer
	 * @purpose to get all bill adjustment 
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-09-13
	 */
	@RequestMapping(value = UrlPathConstants.SEND_MAIL_WITH_ATTACHEMENT, method = RequestMethod.GET)
	public ResponseEntity<String> sendEmailWithAttachment() {
		logger.info("Entered into sendEmailWithAttachment controller.......");
		// emailService.sendMail("shrikale11@gmail.com", "Hello", "<html><body><h1>test email with attachemnt</h1></body></html>");
		// emailService.sendMail("baskar.kumar@gmail.com", "Invoice", "<html><body><p>Hello Sir, <br/> Please click the below link for your Energy Consumption Invoice</p><a href='http://54.191.70.190:8082/tms_ui/assets/images/SampleBill.pdf'><u>See Invoice</u></a></body></html>");
		 emailService.sendMail("devendra.vishwakarma@ge.com", "Invoice", "<html><body><p>Dear Customer, <br/><br/> Kindly note that the tariffs for your customer class will be revised in the month of November. Please pay attention to the attached memos with the October Bill. <br/> <br/><a href='http://54.191.70.190:8082/tms_ui/assets/images/Sep2017Bill.pdf'><u>View Bill</u></a> <br/><br/> Regards, <br/> The Navy Yard Billing Team</p></body></html>"); 
		 return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	/**
	 * @author Jameer
	 * @purpose to get all bill adjustment 
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-09-26
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_ADJUSTMENT_HISTORY_LIST_BY_ACCT_ID, method = RequestMethod.GET)
	public ResponseEntity<List<BillAdjustmentHistoryTMS>> getBillAdjustmentHistoryList(@RequestParam(value = "accountId") Integer accountId) {
		logger.info("Entered into getBillAdjustmentList controller....getBillAdjustmentHistoryList() method called");
		return accountService.getBillAdjustmentHistoryList(accountId);
	}
}