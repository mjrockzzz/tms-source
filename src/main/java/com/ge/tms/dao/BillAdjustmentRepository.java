package com.ge.tms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.BillAdjustment;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerBill;

/**
 * @author Nitin K.
 * This interface contains methods to get data from BillAdjustment table in database.
 */
public interface BillAdjustmentRepository extends CrudRepository<BillAdjustment, Integer>{
	
	public static final String FIND_ADJUSTEMENT_BILL_HISTORY = "select adjsutment_creation_date,adjustment_amount,"
			+ "adjustment_request_by,adjustment_status,adjustment_type,comments from  bill_adjustment_aud "
			+ "where customer_bill_id =:customer_bill_id order by adjsutment_creation_date desc";

	/**
	 * Connects to database and returns bill adjustment data for a given customer bill.
	 * @author Nitin K.
	 * @param customerBill CustomerBill
	 * @return BillAdjustment
	 */
	public BillAdjustment findByCustomerBill(CustomerBill customerBill);
	  
	
	/**
	 * Connects to database and returns all the bill adjustments for a given customer bill id.
	 * @author Nitin K.
	 * @param customer_bill_id Integer
	 * @return list of BillAdjustment
	 */
	@Query(value="select * from bill_adjustment where customer_bill_id=:customer_bill_id",nativeQuery = true)
	public List<BillAdjustment> findByCustomerBillList(@Param("customer_bill_id") Integer customer_bill_id);
		
	@Query(value = FIND_ADJUSTEMENT_BILL_HISTORY, nativeQuery = true)
	public List<Object[]> findCustomerBillHistoryByBillId(@Param("customer_bill_id") Integer customer_bill_id);
}