package com.ge.tms.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.dao.CustomerBillRepository;
import com.ge.tms.dto.ArAagingDataResponse;
import com.ge.tms.dto.ArAgingData;
import com.ge.tms.dto.ArAgingGraphData;
import com.ge.tms.dto.RevenueCycleData;
import com.ge.tms.dto.RevenueGraphBilled;
import com.ge.tms.dto.RevenueGraphCollected;
import com.ge.tms.dto.RevenueGraphData;
import com.ge.tms.dto.UnpaidCustomer;
import com.ge.tms.entity.CustomerBill;
import com.ge.tms.service.RevenueCycleService;
import com.ge.tms.util.DateUtility;

/**
 * Service class containing methods to get revenue cycle and araging data.
 * @author Nitin K.
 */
@Service
public class RevenueCycleServiceImpl implements RevenueCycleService {

	private static final Logger logger = LoggerFactory.getLogger(RevenueCycleServiceImpl.class);

	@Autowired
	private CustomerBillRepository customerBillRepository;

	/**
	 * @author Nitin K.
	 * @param startDate String
	 * @parma endDate String
	 * @param monthToDate boolean
	 * @purpose to get data of revenue cycle based on given dates
	 * @return ResponseEntity<RevenueCycleData>
	 * @throws ParseException
	 * @date 2017-07-31
	 */
	@Override
	public ResponseEntity<RevenueCycleData> getRevenueCycleData(String startDate, String endDate, boolean monthToDate)
			throws ParseException {
		// TODO Auto-generated method stub

		logger.info("Entered into getRevenueCycleData service method.......");

		java.sql.Date sqlStartDate = null;
		java.sql.Date sqlEndDate = null;
		boolean isMonthToDateGraph = false;
		boolean isYearToDateGraph = false;
		boolean isCustomDateGraph = false;
		List<Object[]> revenueTotalBilledObjects = null;
		List<Object[]> revenueTotalCollectedObjects = null;
		boolean isDaysExceeded = false;

		// Create RevenueCycleData DTO object
		RevenueCycleData revenueCycleData = new RevenueCycleData();

		// Check for month to date revenue cycle
		if (startDate == null && endDate != null && monthToDate == true) {
			// Set endDate=CurrentDate-1 and startDate=CurrentDate-30
			logger.info("Entered into getRevenueCycleDataMonthToDate service method.......");
			sqlStartDate = DateUtility.getStartingSqlDateOfCurrentMonth(endDate);
			sqlEndDate = DateUtility.getSqlDateFromStringDate(endDate);
			isMonthToDateGraph = true;
			revenueCycleData.setTypeOfGraph("Daily");
		}

		// Check for year to date revenue cycle
		else if (startDate == null && endDate != null && monthToDate == false) {
			logger.info("Entered into getRevenueCycleDataYearToDate service method.......");
			sqlStartDate = DateUtility.getReducedSqlDatewithMonths(endDate, 12);
			sqlEndDate = DateUtility.getReducedSqlDatewithDays(endDate, 1);
			isYearToDateGraph = true;
			revenueCycleData.setTypeOfGraph("Monthy");

		}
		// Check for custom revenue cycle
		else if (startDate != null && endDate != null && monthToDate == false) {
			logger.info("Entered into getRevenueCycleDataCustom service method.......");
			sqlStartDate = DateUtility.getSqlDateFromStringDate(startDate);
			sqlEndDate = DateUtility.getSqlDateFromStringDate(endDate);
			isCustomDateGraph = true;
			// Calculate no of days
			int noOfdays = DateUtility.getDaysDifference(startDate, endDate);
			// Only applied for 1 year
			if (noOfdays > 365) {
				return new ResponseEntity<RevenueCycleData>(revenueCycleData, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			// Check no of days are exceeded than 60 or not
			if (noOfdays > 30) {
				logger.debug("Days are exceeded than 60......");
				isDaysExceeded = true;
			}

		}

		// Now calculate revenue cycle data based on start date and end date
		// First calculate total customer billed
		Integer totalCustomerBilled = customerBillRepository.findTotalCustomerBilled(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalCustomerBilled(totalCustomerBilled);

		// Calculate total customer paid
		Integer totalCustomerPaid = customerBillRepository.findTotalCustomerPaid(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalCustomerPaid(totalCustomerPaid);

		// Calculate total bill amount
		BigDecimal totalBillAmount = customerBillRepository.findTotalBillAmount(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalBillAmount(totalBillAmount);

		// Calculate total bill received
		BigDecimal totalBillReceived = customerBillRepository.findTotalBillReceived(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalBillReceived(totalBillReceived);

		// Calculate total payment received before due date
		BigDecimal totalPaymentReceivedBeforeDueDate = customerBillRepository
				.findTotalBillReceivedBeforeDueDate(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalPaymentRecdBeforeDueDate(totalPaymentReceivedBeforeDueDate);

		// Calculate total payment received after due date
		BigDecimal totalPaymentReceivedAfterDueDate = customerBillRepository
				.findTotalBillReceivedafterDueDate(sqlStartDate, sqlEndDate);
		revenueCycleData.setTotalPaymentRecdAfterDueDate(totalPaymentReceivedAfterDueDate);

		// Calculate total outstanding amount
		BigDecimal subtrahend = totalBillReceived;
		if (totalBillAmount != null) {

			if (subtrahend != null) {
				BigDecimal totalOutstandingAmount = totalBillAmount.subtract(subtrahend);
				revenueCycleData.setTotalOutstandingAmount(totalOutstandingAmount);
			} else {
				revenueCycleData.setTotalOutstandingAmount(totalBillAmount);
			}

		}

		
		//added by zameer to get total unpaid customer list
		
		List<CustomerBill> customerList = customerBillRepository.findTotalCustomerListPaid(sqlStartDate, sqlEndDate);
		List<UnpaidCustomer> unpaidCustomerList = new ArrayList<UnpaidCustomer>();
		for(CustomerBill customer:customerList){
			UnpaidCustomer unpaidCustomer = new UnpaidCustomer();
			unpaidCustomer.setCustomerName(customer.getCustomerAccount().getCustomerName());
			unpaidCustomer.setBillDueDate(customer.getBillDueDate());
			unpaidCustomer.setBillAmount(customer.getBillAmount());
			unpaidCustomerList.add(unpaidCustomer);
		}
		revenueCycleData.setUnpaidCustomerList(unpaidCustomerList);
		
		// Total percentage of interest on penalties
		BigDecimal totalPercentageOnPenalties = customerBillRepository.findTotalPercentageOnPenalties(sqlStartDate,
				sqlEndDate);
		revenueCycleData.setTotalPercentageOnPenalties(totalPercentageOnPenalties);

		// Create RevenueGraphData DTO Object
		RevenueGraphData revenueGraphData = new RevenueGraphData();
		// Create RevenueBilled List
		List<RevenueGraphBilled> revenueGraphBilledList = new ArrayList<RevenueGraphBilled>();

		if (isMonthToDateGraph == true) {
			revenueTotalBilledObjects = customerBillRepository.findRevenueGraphTotalBilledMonth(sqlStartDate,
					sqlEndDate);
			revenueTotalCollectedObjects = customerBillRepository.findRevenueGraphTotalCollectedMonth(sqlStartDate,
					sqlEndDate);

		} else if (isYearToDateGraph == true) {
			revenueTotalBilledObjects = customerBillRepository.findRevenueGraphTotalBilledYear(sqlStartDate,
					sqlEndDate);
			revenueTotalCollectedObjects = customerBillRepository.findRevenueGraphTotalCollectedYear(sqlStartDate,
					sqlEndDate);
		}

		else if (isCustomDateGraph == true) {
			// For more than 30 days show graph monthly else show it as daily
			if (isDaysExceeded == true) {
				logger.info("Days are exceeded.....Showing monthly....");
				revenueCycleData.setTypeOfGraph("Monthly");
				revenueTotalBilledObjects = customerBillRepository.findRevenueGraphTotalBilledYear(sqlStartDate,
						sqlEndDate);
				revenueTotalCollectedObjects = customerBillRepository.findRevenueGraphTotalCollectedYear(sqlStartDate,
						sqlEndDate);
			} else {
				logger.info("Days are not exceeded...Showing Daily....");
				revenueCycleData.setTypeOfGraph("Daily");
				revenueTotalBilledObjects = customerBillRepository.findRevenueGraphTotalBilledMonth(sqlStartDate,
						sqlEndDate);
				revenueTotalCollectedObjects = customerBillRepository.findRevenueGraphTotalCollectedMonth(sqlStartDate,
						sqlEndDate);
			}
		}

		// Get revenue cycle graph data for total Amount Billed
		// Set the data to RevenueGraphBilled response DTO
		for (Object[] revenueTotalBilledObject : revenueTotalBilledObjects) {
			RevenueGraphBilled revenueGraphBilled = new RevenueGraphBilled();
			revenueGraphBilled.setTotalBilledAmount((BigDecimal) revenueTotalBilledObject[0]);
			revenueGraphBilled.setBillDate((Date) revenueTotalBilledObject[1]);
			revenueGraphBilledList.add(revenueGraphBilled);
		}

		// Put list in RevenueCycledata graph
		revenueGraphData.setRevenueGraphBilledList(revenueGraphBilledList);

		// Create RevenueCollected List
		List<RevenueGraphCollected> revenueGraphCollectedList = new ArrayList<RevenueGraphCollected>();

		// Get revenue cycle graph data for total Amount Collected
		// Set the data to RevenueGraphCollected response DTO
		for (Object[] revenueTotalCollectedObject : revenueTotalCollectedObjects) {
			RevenueGraphCollected revenueGraphCollected = new RevenueGraphCollected();
			revenueGraphCollected.setTotalCollectedAmount((BigDecimal) revenueTotalCollectedObject[0]);
			revenueGraphCollected.setBillDate((Date) revenueTotalCollectedObject[1]);
			revenueGraphCollectedList.add(revenueGraphCollected);
		}

		// Put list in RevenueCycledata graph
		revenueGraphData.setRevenueGraphCollectedList(revenueGraphCollectedList);

		// Add RevenueGraphData in RevenueCycleData DTO
		revenueCycleData.setRevenueCycleGraphData(revenueGraphData);

		return new ResponseEntity<RevenueCycleData>(revenueCycleData, HttpStatus.OK);

	}

	/**
	 * @author Nitin K.
	 * @param currentDate String
	 * @param lessThanValue Long
	 * @param greaterThanValue Long
	 * @purpose to get data of ArRaging
	 * @return ResponseEntity<ArAagingDataResponse>
	 * @throws ParseException
	 * @date 2017-08-07
	 */
	@Override
	public ResponseEntity<ArAagingDataResponse> getARRagingData(String currentDate, Long lessThanValue,
			Long greaterThanValue) throws ParseException {
		// TODO Auto-generated method stub

		// java.sql.Date startDate =
		// DateUtility.getStartingSqlDateOfCurrentMonth(currentDate);
		// java.sql.Date enddate =
		// DateUtility.getSqlDateFromStringDate(currentDate);

		java.sql.Date currentDateSql = DateUtility.getSqlDateFromStringDate(currentDate);
		List<ArAgingData> arRagingDataList = new ArrayList<ArAgingData>();
		List<ArAgingGraphData> arAgingGraphDataList = new ArrayList<ArAgingGraphData>();
		ArAagingDataResponse arAagingDataResponse = new ArAagingDataResponse();
		ArAgingGraphData arAgingGraphDataCurrentYear = new ArAgingGraphData();
		ArAgingGraphData arAgingGraphDataPreviousYear = new ArAgingGraphData();
		List<Object[]> arRagingObjects=null;

		//This is for more than 120 days
		if(lessThanValue==null){
			arRagingObjects = customerBillRepository.findArRagingDataWithoutLessThanValue(currentDateSql,greaterThanValue);	
		}
		
		if(lessThanValue!=null && greaterThanValue!=null){
		arRagingObjects = customerBillRepository.findArRagingData(currentDateSql, lessThanValue,
				greaterThanValue);
		}

		logger.info("List of Ar Raging " + arRagingObjects.isEmpty());

		// Check result exists or not
		if (arRagingObjects.size() == 0 && arRagingObjects.isEmpty()) {

			return new ResponseEntity<ArAagingDataResponse>(arAagingDataResponse, HttpStatus.NOT_FOUND);
		}

		// Set the result data to ARRaging DTO
		for (Object[] arRagingObject : arRagingObjects) {
			ArAgingData arRagingData = new ArAgingData();
			arRagingData.setBillAmount((BigDecimal) arRagingObject[0]);
			arRagingData.setNoOfRemindersSent((Integer) arRagingObject[1]);
			arRagingData.setNextReminderdate((Date) arRagingObject[2]);
			arRagingData.setFinalReminderdate((Date) arRagingObject[3]);
			arRagingData.setDaysBeyondDueDate((Long) arRagingObject[4]);
			arRagingData.setCustomerName((String) arRagingObject[5]);
			arRagingData.setCustomerId((Integer) arRagingObject[6]);
			arRagingData.setBillDueDate((Date) arRagingObject[7]);
			arRagingDataList.add(arRagingData);
		}

		// Calculate ArAging Graph Data for Current Year
		// Get Current Year from Current Date
		int currentYear = DateUtility.getYearFromSqlDate(currentDateSql);

		List<Object[]> arRaging30DaysGraphDataObjectsCurrentYear = customerBillRepository
				.findArAgingGraphData30Days(currentDateSql, currentYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging30DaysGraphDataObjectCurrentYear : arRaging30DaysGraphDataObjectsCurrentYear) {

			arAgingGraphDataCurrentYear.setCustomerCount30Days((Long) arRaging30DaysGraphDataObjectCurrentYear[0]);
			arAgingGraphDataCurrentYear
					.setCustomerAmount30Days((BigDecimal) arRaging30DaysGraphDataObjectCurrentYear[1]);
		}

		List<Object[]> arRaging60DaysGraphDataObjectsCurrentYear = customerBillRepository
				.findArAgingGraphData60Days(currentDateSql, currentYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging60DaysGraphDataObjectCurrentYear : arRaging60DaysGraphDataObjectsCurrentYear) {

			arAgingGraphDataCurrentYear.setCustomerCount60Days((Long) arRaging60DaysGraphDataObjectCurrentYear[0]);
			arAgingGraphDataCurrentYear
					.setCustomerAmount60Days((BigDecimal) arRaging60DaysGraphDataObjectCurrentYear[1]);
		}

		List<Object[]> arRaging90DaysGraphDataObjectsCurrentYear = customerBillRepository
				.findArAgingGraphData90Days(currentDateSql, currentYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging90DaysGraphDataObjectCurrentYear : arRaging90DaysGraphDataObjectsCurrentYear) {

			arAgingGraphDataCurrentYear.setCustomerCount90Days((Long) arRaging90DaysGraphDataObjectCurrentYear[0]);
			arAgingGraphDataCurrentYear
					.setCustomerAmount90Days((BigDecimal) arRaging90DaysGraphDataObjectCurrentYear[1]);
		}

		List<Object[]> arRaging120DaysGraphDataObjectsCurrentYear = customerBillRepository
				.findArAgingGraphData120Days(currentDateSql, currentYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging120DaysGraphDataObjectCurrentYear : arRaging120DaysGraphDataObjectsCurrentYear) {

			arAgingGraphDataCurrentYear.setCustomerCount120Days((Long) arRaging120DaysGraphDataObjectCurrentYear[0]);
			arAgingGraphDataCurrentYear
					.setCustomerAmount120Days((BigDecimal) arRaging120DaysGraphDataObjectCurrentYear[1]);
		}
		
		// As per new modifications
		List<Object[]> arRagingMoreThan120DaysGraphDataObjectsCurrentYear = customerBillRepository
				.findArAgingGraphDataMoreThan120Days(currentDateSql, currentYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRagingMoreThan120DaysGraphDataObjectCurrentYear : arRagingMoreThan120DaysGraphDataObjectsCurrentYear) {

			arAgingGraphDataCurrentYear
					.setCustomerCountMoreThan120Days((Long) arRagingMoreThan120DaysGraphDataObjectCurrentYear[0]);
			arAgingGraphDataCurrentYear.setCustomerAmountMoreThan120Days(
					(BigDecimal) arRagingMoreThan120DaysGraphDataObjectCurrentYear[1]);
		}

		// Set year of arAgingGraphDataCurrentYear
		arAgingGraphDataCurrentYear.setGraphDataYear(currentYear);
		// Add it to arAgingGraphDataList
		arAgingGraphDataList.add(arAgingGraphDataCurrentYear);

		// Calculate ArAging Graph Data for Previous Year
		// Get Previous Year from Current Date
		// int previousYear =
		// DateUtility.getPreviousYearFromSqlDate(currentDateSql);
		int previousYear = DateUtility.getPreviousYearFromSqlDate(currentDateSql);

		List<Object[]> arRaging30DaysGraphDataObjectsPreviousYear = customerBillRepository
				.findArAgingGraphData30Days(currentDateSql, previousYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging30DaysGraphDataObjectPreviousYear : arRaging30DaysGraphDataObjectsPreviousYear) {

			arAgingGraphDataPreviousYear.setCustomerCount30Days((Long) arRaging30DaysGraphDataObjectPreviousYear[0]);
			arAgingGraphDataPreviousYear
					.setCustomerAmount30Days((BigDecimal) arRaging30DaysGraphDataObjectPreviousYear[1]);
		}

		List<Object[]> arRaging60DaysGraphDataObjectsPreviousYear = customerBillRepository
				.findArAgingGraphData60Days(currentDateSql, previousYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging60DaysGraphDataObjectPreviousYear : arRaging60DaysGraphDataObjectsPreviousYear) {

			arAgingGraphDataPreviousYear.setCustomerCount60Days((Long) arRaging60DaysGraphDataObjectPreviousYear[0]);
			arAgingGraphDataPreviousYear
					.setCustomerAmount60Days((BigDecimal) arRaging60DaysGraphDataObjectPreviousYear[1]);
		}

		List<Object[]> arRaging90DaysGraphDataObjectsPreviousYear = customerBillRepository
				.findArAgingGraphData90Days(currentDateSql, previousYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging90DaysGraphDataObjectPreviousYear : arRaging90DaysGraphDataObjectsPreviousYear) {

			arAgingGraphDataPreviousYear.setCustomerCount90Days((Long) arRaging90DaysGraphDataObjectPreviousYear[0]);
			arAgingGraphDataPreviousYear
					.setCustomerAmount90Days((BigDecimal) arRaging90DaysGraphDataObjectPreviousYear[1]);
		}

		List<Object[]> arRaging120DaysGraphDataObjectsPreviousYear = customerBillRepository
				.findArAgingGraphData120Days(currentDateSql, previousYear);
		// Set the result data to ARRaging DTO
		for (Object[] arRaging120DaysGraphDataObjectPreviousYear : arRaging120DaysGraphDataObjectsPreviousYear) {

			arAgingGraphDataPreviousYear.setCustomerCount120Days((Long) arRaging120DaysGraphDataObjectPreviousYear[0]);
			arAgingGraphDataPreviousYear
					.setCustomerAmount120Days((BigDecimal) arRaging120DaysGraphDataObjectPreviousYear[1]);
		}

		// Set year of arAgingGraphDataCurrentYear
		arAgingGraphDataPreviousYear.setGraphDataYear(previousYear);
		// Add it to arAgingGraphDataList
		arAgingGraphDataList.add(arAgingGraphDataPreviousYear);

		// Set ArAgingData and ArAgingGrapgData to ArAgingResponse
		arAagingDataResponse.setArAgingDataList(arRagingDataList);
		arAagingDataResponse.setArAgingGraphData(arAgingGraphDataList);

		return new ResponseEntity<ArAagingDataResponse>(arAagingDataResponse, HttpStatus.OK);
	}
}
