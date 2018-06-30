package com.ge.tms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.CustomerAccount;


/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerAccount table in database.
 */
public interface AccountHistoryRepository extends CrudRepository<CustomerAccount, Integer> {

	public static final String FIND_CUSTOMER_BILL_HISTORY = "select customer_bill_id,bill_template,customer_class,rate_plan,rate_effective from cust_bill_aud where acct_id =:accountId order by customer_bill_id desc";

	/**
	 * Connects to database and retrieves all bill history for a given customer account id.
	 * @author Nitin K.
	 * @param accountId Integer
	 * @return list of Object[]
	 */
	@Query(value = FIND_CUSTOMER_BILL_HISTORY, nativeQuery = true)
	public List<Object[]> findCustomerBillHistoryByAccountId(@Param("accountId") Integer accountId);

}
