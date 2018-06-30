package com.ge.tms.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.CustomerMeter;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerMeter table in database.
 */
public interface CustomerMeterRepository extends CrudRepository<CustomerMeter, BigDecimal> {

	public static final String FIND_ACCOUNT_ID = "select acct_id from cust_meter where meter_num =:meterNumber and meter_status=:meterStatus";
	
	public static final String FIND_ACCOUNT_ID_NO_STATUS = "select acct_id from cust_meter where meter_num =:meterNumber";

	/**
	 * Connects to database and returns customer meter details for a given meter number.
	 * @author Nitin K.
	 * @param meterNumber BigDecimal
	 * @return CustomerMeter
	 */
	public CustomerMeter findByMeterNumber(BigDecimal meterNumber);

	/**
	 * Connects to database and returns customer account id for a given meter number and meter status.
	 * @author Nitin K.
	 * @param meterNumber BigDecimal
	 * @param meterStatus String
	 * @return customer account id
	 */
	@Query(value = FIND_ACCOUNT_ID, nativeQuery = true)
	public BigDecimal findAccountIdByMeterNumber(@Param("meterNumber") BigDecimal meterNumber, @Param("meterStatus") String meterStatus);
	
	/**
	 * Connects to database and returns customer account id for a given meter number.
	 * @author Nitin K.
	 * @param meterNumber BigDecimal
	 * @return customer account id
	 */
	@Query(value = FIND_ACCOUNT_ID_NO_STATUS, nativeQuery = true)
	public BigDecimal findAccountIdByMeterNumberBoth(@Param("meterNumber") BigDecimal meterNumber);
	
	/**
	 * Connects to database and returns a list of all meter numbers which partially or fully matches the given meter number.
	 * @author Nitin K.
	 * @param meterNumber Long
	 * @return list of meter numbers
	 */
	@Query(value="select meter_num from cust_meter where meter_num like :meterNumber% ",nativeQuery = true)
	List<BigDecimal> findCustomerMeterByMeterNumberLike(@Param("meterNumber") Long meterNumber);
}