package com.ge.tms.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.ge.tms.dto.BillAdjustmentHistory;
import com.ge.tms.dto.BillChargeConfigurationDTO;
import com.ge.tms.dto.BillChargeRequestDTO;
import com.ge.tms.dto.BillChargeResponse;
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

/**
 * Service class containing methods to get customer details, bill, bill charge, bill adjustment details, mail service etc.
 * @author Nitin K.
 */
public interface AccountService {

	/**
	 * @author Nitin K.
	 * @purpose to get top 10 customer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	public ResponseEntity<List<WSCustomerAccount>> searchAllCustomer();

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @param activeAccount String
	 * @param inActiveAccount String
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAcctId(Integer accountId, String activeAccount,
			String inActiveAccount);

	/**
	 * @author Nitin K.
	 * @purpose to search customer by meter number
	 * @param meterNumber Long
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-19
	 */
	public ResponseEntity<List<CustomerAccount>> searchCustomerByMeterNumber(Long meterNumber);

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id without account status
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>>
	 * @date 2017-07-20
	 */
	public ResponseEntity<List<CustomerAccount>> searchCustomerByAccountIdNoStatus(Integer accountId);

	/**
	 * @author Nitin K.
	 * @purpose to update the CustomerDetails
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	public ResponseEntity<String> updateCustomerDetails(CustomerAccount customerAccount);

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Account Details
	 * @param customerAccount CustomerAccount
	 * @return ResponseEntity<String>
	 * @date 2017-07-20
	 */
	public ResponseEntity<String> updateCustomerAccountDetails(CustomerAccount customerAccount);

	/**
	 * @author Nitin K.
	 * @purpose to update the Customer Financial Details
	 * @param financialDetails FinancialDetails
	 * @return ResponseEntity<String>
	 * @date 2017-07-24
	 */
	public ResponseEntity<String> updateCustomerFinancial(FinancialDetails financialDetails);

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
	public ResponseEntity<List<UsageHistory>> getBillHistory(Integer accountId, String startDate, String endDate,
			Long meterNumber, boolean isCustom) throws ParseException;

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
	public ResponseEntity<IntervalDataHistoryResponse> getIntervalDataUsageHistory(Integer accountId, String startDate,
			String endDate, Long meterNumber, boolean previous) throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to get usage history hourly
	 * @param OfDate String
	 * @param meterNumber Long
	 * @return ResponseEntity<IntervalDataHistoryResponse>
	 * @throws ParseException
	 * @date 2017-08-09
	 */
	public ResponseEntity<IntervalDataHistoryResponse> getUsageHistoryHourly(String OfDate, Long meterNumber)
			throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to terminate account service
	 * @param currentDate String
	 * @param accountId Integer
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-18
	 */
	public ResponseEntity<String> terminateAccountService(String currentDate, Integer accountId) throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to do the bill adjustment of selected bill
	 * @param billAdjustment BillAdjustment
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	public ResponseEntity<String> doBillAdjustment(BillAdjustment billAdjustment) throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to search customer by account id
	 * @param accountId Integer
	 * @return ResponseEntity<CustomerAccount>
	 * @date 2017-08-24
	 */
	public ResponseEntity<CustomerAccount> searchCustomerByAccountId(Integer accountId);

	/**
	 * @author Nitin K.
	 * @purpose to get customer bill charge configuration
	 * @param billChargeConfigurationDTO BillChargeConfigurationDTO
	 * @return ResponseEntity<BillChargeResponse>
	 * @throws ParseException
	 * @date 2017-08-24
	 */
	public ResponseEntity<BillChargeResponse> getCustomerBillChargeConfiguration(BillChargeConfigurationDTO 
			billChargeConfigurationDTO) throws ParseException;
	
	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param customerAccountId Integer
	 * @return ResponseEntity<BillChargeConfiguration>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	public ResponseEntity<BillChargeConfiguration> getBillChargeConfigurationByCustomerAccountId(Integer customerAccountId);

	/**
	 * @author Nitin K.
	 * @purpose to save customer bill charge configuration
	 * @param billChargeRequestDTO BillChargeRequestDTO
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-30
	 */
	public ResponseEntity<String> saveCustomerBillChargeConfiguration(BillChargeRequestDTO billChargeRequestDTO)
			throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment by CustomerBill Id
	 * @param customerBillId Integer
	 * @return ResponseEntity<BillAdjustment>
	 * @date 2017-08-29
	 */
	public ResponseEntity<BillAdjustment> getBillAdjustmentByCustomerBill(Integer customerBillId);

	/**
	 * @author Nitin K.
	 * @purpose to update bill adjustment
	 * @param billAdjustment BillAdjustment
	 * @return ResponseEntity<String>
	 * @throws ParseException
	 * @date 2017-08-29
	 */
	public ResponseEntity<String> updateBillAdjustment(BillAdjustment billAdjustment) throws ParseException;

	/**
	 * @author Nitin K.
	 * @purpose to configure billing peak hour
	 * @param billingPeakHour BillingPeakHour
	 * @return ResponseEntity<String>
	 * @date 2017-08-29
	 */
	public ResponseEntity<String> configurePeakHour(BillingPeakHour billingPeakHour);

	/**
	 * @author Nitin K.
	 * @purpose to get billing peak hour settings
	 * @return ResponseEntity<BillingPeakHour>
	 * @date 2017-08-29
	 */
	public ResponseEntity<BillingPeakHour> getPeakHourSettings();

	/**
	 * @author Nitin K.
	 * @purpose to get BillAdjustmentHistory list by billId
	 * @param billId Integer
	 * @return ResponseEntity<List<BillAdjustmentHistory>>
	 * @date 2017-08-29
	 */
	public ResponseEntity<List<BillAdjustmentHistory>> getBillAdjustementHistory(Integer accountId);

	/**
	 * @author Nitin K.
	 * @purpose to parse a csv file
	 * @param file MultipartFile
	 * @return ResponseEntity<IntervalDataManagementResponse>
	 * @date 2017-08-29
	 */
	public ResponseEntity<IntervalDataManagementResponse> readCSVFile(MultipartFile file);

	/**
	 * @author Nitin K.
	 * @purpose to parse a csv monthly file
	 * @param file MultipartFile
	 * @return ResponseEntity<IntervalDataManagementResponse>
	 * @date 2017-08-29
	 */
	public ResponseEntity<IntervalDataManagementResponse> readCSVMonthly(MultipartFile file);

	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment list
	 * @param accountId Integer
	 * @return ResponseEntity<List<BillAdjustment>>
	 * @date 2017-08-29
	 */
	public ResponseEntity<List<BillAdjustment>> getBillAdjustmentList(Integer accountId);
	
	/**
	 * @author Nitin K.
	 * @purpose to get bill adjustment history list
	 * @param accountId Integer
	 * @return ResponseEntity<List<BillAdjustmentHistoryTMS>>
	 * @date 2017-08-29
	 */
	public ResponseEntity<List<BillAdjustmentHistoryTMS>> getBillAdjustmentHistoryList(Integer accountId);
}