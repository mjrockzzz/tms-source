package com.ge.tms.common.consts;

/**
 * @author Nitin K.
 * Class containing the API end-points for the application.
 */
public final class UrlPathConstants {

	// Common Messages
	public static final String UPDATE_SUCCESS = "Data Updated Successfylly......";
	public static final String UPDATE_FAILURE = "Data Updation Failed...........";

	// LOGIN URL
	public static final String LOGIN = "/login";
	public static final String AUTHENTICATE = "/authenticate";

	// ACCOUNT OPERATION URLS
	public static final String ACCOUNT = "/account";
	public static final String SEARCH_ALL_CUSTOMER = "/searchAllCustomer";
	public static final String SEARCH_CUSTOMER_BY_ACCT_ID = "/searchCustomerByAcctId";
	public static final String SEARCH_CUSTOMER_BY_ACCT_ID_NO_STATUS = "/searchCustomerByAcctIdNoStatus";
	public static final String SEARCH_CUSTOMER_BY_METER_NUMBER = "/searchCustomerByMeterNumber";
	public static final String UPDATE_CUSTOMER_DETAILS = "/updateCustomerDetails";
	public static final String UPDATE_CUSTOMER_ACCOUNT_DETAILS = "/updateCustomerAccountDetails";
	public static final String UPDATE_CUSTOMER_FINANCIAL_DETAILS = "/updateCustomerFinancialDetails";
	public static final String BILL_HISTORY = "/billHistory";
	public static final String ALL_BILL_HISTORY = "/allBillHistory";
	public static final String INTERVAL_DATA_USAGE_HISTORY_CURRENT_BILL_CYCLE = "/intervalDataUsageHistoryCurrentBillCycle";
	public static final String INTERVAL_DATA_USAGE_HISTORY_PREVIOUS_BILL_CYCLE = "/intervalDataUsageHistoryPreviousBillCycle";
	public static final String INTERVAL_DATA_USAGE_HISTORY_CUSTOM_BILL_CYCLE = "/intervalDataUsageHistoryCustomBillCycle";
	public static final String INTERVAL_DATA_USAGE_HISTORY_HOURLY = "/intervalDataUsageHistoryHourly";
	public static final String TERMINATE_ACCOUNT_SERVICE = "/terminateAccountService";
	public static final String DO_BILL_ADJUSTMENT = "/doBillAdjustment";
	public static final String UPDATE_BILL_ADJUSTMENT = "/updateBillAdjustment";
	public static final String GET_BILL_ADJUSTMENT_BY_CUSTOMER_BILL = "/getBillAdjustmentByCustomerBill";
	public static final String GET_BILL_ADJUSTMENT_LIST_BY_ACCT_ID = "/getBillAdjustmentListByAccountId";
	public static final String SEARCH_CUSTOMER_BY_ACCOUNT = "/searchCustomerByAccount";
	public static final String GET_CUSTOMER_BILL_CHARGE_CONFIGURATION = "/getCustomerBillChargeConfiguration";
	public static final String SAVE_CUSTOMER_BILL_CHARGE_CONFIGURATION = "/saveCustomerBillChargeConfiguration";
	public static final String GET_CUSTOMER_BILL_CHARGE_CONFIGURATION_BY_ACCT_ID = "/getCustomerBillChargeConfigurationByAcctId";
	public static final String CONFIGURE_PEAK_HOUR = "/configurePeakHour";
	public static final String GET_PEAK_HOUR_SETTINGS = "/getPeakHourSettings";
	
	public static final String GET_BILL_ADJUSTMENT_HISTORY = "/getBillAdjustementHistory";
	public static final String GET_BILL_ADJUSTMENT_HISTORY_LIST_BY_ACCT_ID = "/getBillAdjustmentHistoryListByAccountId";
	

	// REVENUE CYCLE DASHBOARD URLS
	public static final String REVENUE_CYCLE = "/revenueCycle";
	public static final String REVENUE_CYCLE_DATA_MONTH_TO_DATE = "/revenueCycleDataMonthToDate";
	public static final String REVENUE_CYCLE_DATA_YEAR_TO_DATE = "/revenueCycleDataYearToDate";
	public static final String REVENUE_CYCLE_DATA_CUSTOM = "/revenueCycleDataCustom";
	public static final String AR_RAGING_DATA = "/arRagingData";

	// ACCOUNT HISTORY URL
	public static final String ACCOUNT_HISTORY = "/accountHistory";
	public static final String CUSTOMER_ACCOUNT_HISTORY = "/customerAccountHistory";
	public static final String CUSTOMER_BILL_HISTORY = "/customerBillHistory";
	public static final String CUSTOMER_METER_HISTORY = "/customerMeterHistory";
	
	
	
	//IMPORT CSV FILE
	
	public static final String READ_CSV_FILE = "/readCSVFile";
	public static final String READ_CSV_MONTHLY = "/readCSVMonthly";
	
	
	
	//BILL ADJUSTMENT URLS
	// ACCOUNT OPERATION URLS
	public static final String BILL_ADJUSTMENT = "/billAdjustment";
	public static final String GET_ALL_BILL_ADJUSTMENT_MASTER = "/getAllBillAdjustmentMaster";
	public static final String GET_ALL_BILL_ADJUSTMENT_MASTER_HISTORY = "/getAllBillAdjustmentMasterHistory";
	public static final String SAVE_UPDATE_BILL_ADJUSTEMENT_MASTER = "/saveUpdateBillAdjustmentMaster";
	
	
	
	
	public static final String SEND_MAIL_WITH_ATTACHEMENT = "/sendEmailWithAttachment";
	
	
	//Rate Management
	public static final String RATE_MANAGEMENT = "/rateManagement";
	
	public static final String SAVE_TIERED_RATES = "/saveTieredRates";
	public static final String GET_TIERED_RATES = "/getTieredRates";
	public static final String GET_TIERED_RATES_HISTORY = "/getTieredRatesHistory";
	
	
	public static final String SAVE_TOU_RATES = "/saveTouRates";
	public static final String GET_TOU_RATES = "/getTouRates";
	public static final String GET_TOU_RATES_HISTORY = "/getTouRatesHistory";
	
	
	public static final String SAVE_GENERIC_RATES = "/saveGenericRates";
	public static final String GET_GENERIC_RATES = "/getGenericRates";
	public static final String GET_GENERIC_RATES_HISTORY = "/getGenericRatesHistory";
	
	
	//Bill Configuration
	public static final String BILL_CONFIGURATION = "/billConfiguration";
	
	public static final String SAVE_UPDATE_BILL_CONFIGURAION = "/saveUpdateBillConfiguration";
	public static final String GET_BILL_CONFIGURATION_LIST = "/getBillConfigurationList";
	public static final String GET_BILL_CONFIGURATION_HISTORY_LIST = "/getBillConfigurationHistoryList";
	
	
	public static final String STUDENT = "/student";
	public static final String GET_ALL_STUDENT = "/getAllStudent";
}
	