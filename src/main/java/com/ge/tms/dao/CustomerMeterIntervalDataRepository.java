package com.ge.tms.dao;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.CustomerMeter;
import com.ge.tms.entity.CustomerMeterIntervalData;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerMeterIntervalData table in database.
 */
public interface CustomerMeterIntervalDataRepository extends CrudRepository<CustomerMeterIntervalData, Integer> {

	//Daily
	public static final String FIND_DAILY= "select meter_num, date(interval_time) meterdate, sum(kWH), max(temperature) "
			+ "temperature from cust_meter_interval_data where date(interval_time) between :sqlBillingCycleStartDate and "
			+ ":sqlBillingCycleEndDate and meter_num =:meterNumber group by date(interval_time) order by interval_time";

	//Monthly
	public static final String FIND_MONTHLY= "select meter_num, date(interval_time) meterdate, sum(kWH) daily_load, "
			+ "max(temperature) temperature from cust_meter_interval_data where date(interval_time) between :fromDate and "
			+ ":toDate and meter_num =:meterNumber group by month(interval_time) order by interval_time";

	//Hourly
	public static final String FIND_HOURLY= "select meter_num, date(interval_time) meterdate, kWH, temperature, "
			+ "time(interval_time) metertime from cust_meter_interval_data where date(interval_time)= :Ofdate and "
			+ "meter_num= :meterNumber order by time(interval_time)";

	//Sum of KWH Bill History
	public static final String FIND_BILL_HISTORY= "select meter_num, date(interval_time) meterdate, sum(kWH), max(temperature)"
			+ " temperature from cust_meter_interval_data where date(interval_time) between :sqlBillingCycleStartDate and "
			+ ":sqlBillingCycleEndDate and meter_num =:meterNumber group by meter_num";

	//Sum of interval amount
	public static final String FIND_INTERVAL_AMOUNT= "select sum(interval_amt) from cust_meter_interval_data where "
			+ "date(interval_time) between :startDate and :endDate and meter_num =:meterNumber group by meter_num";
	
	//Aggregate peak demand consumption
	public static final String FIND_AGGREGATE_PEAK_CONSUMPTION="select avg(kWH) from cust_meter_interval_data where "
			+ "date(interval_time) between :startDate and :endDate and meter_num =:meterNumber and hour(interval_time) "
			+ "between :startPeakHour and :endPeakHour group by meter_num";
	
	//Aggregate peak demand consumption hourly
	public static final String FIND_AGGREGATE_PEAK_CONSUMPTION_HOURLY="select avg(kWH) from cust_meter_interval_data where "
			+ "date(interval_time)=:startDate and meter_num =:meterNumber and hour(interval_time) between :startPeakHour and "
			+ ":endPeakHour group by meter_num";

	/**
	 * Connects to database and returns customer meter usage data on daily basis for a given customer meter & billingcycle.
	 * @author Nitin K.
	 * @param sqlBillingCycleStartDate Date
	 * @param sqlBillingCycleEndDate Date
	 * @param meterNumber CustomerMeter
	 * @return list of Object[]
	 */
	@Query(value = FIND_DAILY, nativeQuery = true)
	public List<Object[]> findUsageHistoryDaily(@Param("sqlBillingCycleStartDate") Date sqlBillingCycleStartDate,
			@Param("sqlBillingCycleEndDate") Date sqlBillingCycleEndDate, @Param("meterNumber") CustomerMeter meterNumber);

	/**
	 * Connects to database and returns customer meter usage data on monthly basis for a given customer meter & date range.
	 * @author Nitin K.
	 * @param fromDate Date
	 * @param toDate Date
	 * @param meterNumber CustomerMeter
	 * @return list of Object[]
	 */
	@Query(value = FIND_MONTHLY, nativeQuery = true)
	public List<Object[]> findUsageHistoryMonthly(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate,
			@Param("meterNumber") CustomerMeter meterNumber);

	/**
	 * Connects to database and returns customer meter usage data for a given customer meter & date.
	 * @author Nitin K.
	 * @param Ofdate Date
	 * @param meterNumber CustomerMeter
	 * @return list of Object[]
	 */
	@Query(value = FIND_HOURLY, nativeQuery = true)
	public List<Object[]> findUsageHistoryHourly(@Param("Ofdate") Date Ofdate, @Param("meterNumber") CustomerMeter meterNumber);

	/**
	 * Connects to database and returns the customer meter usage data for a given customer meter & billingcycle.
	 * @author Nitin K.
	 * @param sqlBillingCycleStartDate Date
	 * @param sqlBillingCycleEndDate Date
	 * @param meterNumber CustomerMeter
	 * @return list of Object[]
	 */
	@Query(value = FIND_BILL_HISTORY, nativeQuery = true)
	public List<Object[]> findBillHistory(@Param("sqlBillingCycleStartDate") Date sqlBillingCycleStartDate,
			@Param("sqlBillingCycleEndDate") Date sqlBillingCycleEndDate, @Param("meterNumber") CustomerMeter meterNumber);

	/**
	 * Connects to database and returns the total interval amount for a given customer meter & date range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @param meterNumber CustomerMeter
	 * @return total interval amount
	 */
	@Query(value = FIND_INTERVAL_AMOUNT, nativeQuery = true)
	public BigDecimal findIntervalAmount(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("meterNumber") CustomerMeter meterNumber);

	/**
	 * Connects to database and returns the average peak hour consumption for a given customer meter, date range
	 * & peak hour range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param endDate Date
	 * @param meterNumber CustomerMeter
	 * @param startPeakHour Integer
	 * @param endPeakHour Integer
	 * @return average peak hour consumption
	 */
	@Query(value = FIND_AGGREGATE_PEAK_CONSUMPTION, nativeQuery = true)
	public BigDecimal findAggregatedPeakConsumption(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("meterNumber") CustomerMeter meterNumber, @Param("startPeakHour") Integer startPeakHour,
			@Param("endPeakHour") Integer endPeakHour);

	/**
	 * Connects to database and returns the average peak hour consumption for a given customer meter, date & peak hour range.
	 * @author Nitin K.
	 * @param startDate Date
	 * @param customerMeter CustomerMeter
	 * @param startPeakHour Integer
	 * @param endPeakHour Integer
	 * @return average peak hour consumption
	 */
	@Query(value = FIND_AGGREGATE_PEAK_CONSUMPTION_HOURLY, nativeQuery = true)
	public BigDecimal findAggregatedPeakConsumptionHourly(@Param("startDate") Date startDate,
			@Param("meterNumber") CustomerMeter customerMeter, @Param("startPeakHour")Integer startPeakHour,
			@Param("endPeakHour") Integer endPeakHour);
}