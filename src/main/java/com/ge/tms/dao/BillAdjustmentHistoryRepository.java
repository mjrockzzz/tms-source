package com.ge.tms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.BillAdjustmentHistoryTMS;

/**
 * @author Nitin K.
 * This interface contains methods to get data from BillAdjustmentHistoryTMS table in database.
 */
public interface BillAdjustmentHistoryRepository extends CrudRepository<BillAdjustmentHistoryTMS, Integer>{

	/**
	 * Connects to database and retrieves all bill adjustment history data for a given customer bill id.
	 * @author Nitin K.
	 * @param customer_bill_id Integer
	 * @return list of BillAdjustmentHistory objects
	 */
	@Query(value="select * from bill_adjustment_history where customer_bill_id=:customer_bill_id",nativeQuery = true)
	public List<BillAdjustmentHistoryTMS> findByCustomerBillList(@Param("customer_bill_id") Integer customer_bill_id);
}