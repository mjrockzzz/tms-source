package com.ge.tms.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.BillChargeConfiguration;
import com.ge.tms.entity.CustomerAccount;

/**
 * @author Nitin K.
 * This interface contains methods to get data from BillChargeConfiguration table in database.
 */
public interface BillChargeConfigurationRespository extends CrudRepository<BillChargeConfiguration, Integer> {

	/**
	 * Connects to database and sets the termination date of a bill configuration with given id.
	 * @author Nitin K.
	 * @param terminationDate Date
	 * @param billChargeConfigurationId Integer
	 * @return id of the updated bill configuration
	 */
	@Modifying
	@Query("update BillChargeConfiguration  set terminationDate=:terminationDate where billChargeConfigurationId=:billChargeConfigurationId")
	public Integer updateBillChargeCfgTerminationDate(@Param("terminationDate") Date terminationDate,
			@Param("billChargeConfigurationId") Integer billChargeConfigurationId);
	
	/**
	 * Connects to database and returns bill charge configuration details for a given customer account.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @return BillChargeConfiguration
	 */
	@Query("from BillChargeConfiguration where customerAccount=:customerAccount and terminationDate IS NULL")
	public BillChargeConfiguration findBillChargeCfgByAcctId(@Param("customerAccount") CustomerAccount customerAccount);
}
