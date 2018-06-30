package com.ge.tms.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ge.tms.entity.CustomerAccount;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerAccount table in database.
 */
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer> {

	@Override
	public List<CustomerAccount> findAll();

	/**
	 * Connects to database and returns the customer account details for a given acccount id.
	 * @author Nitin K.
	 * @param accountId Integer
	 * @return CustomerAccount
	 */
	public CustomerAccount findByAccountId(Integer accountId);

	/**
	 * Connects to database and returns the customer account details for a given username.
	 * @author Nitin K.
	 * @param username String
	 * @return CustomerAccount
	 */
	public CustomerAccount findByUsername(String username);

	/**
	 * Connects to database and returns all Customer accounts whose account id partially or fully matches given account id.
	 * @author Nitin K.
	 * @param accountId Integer
	 * @return list of CustomerAccount
	 */
	@Query(value="select * from cust_acct where acct_id  like :accountId%",nativeQuery = true)
	public List<CustomerAccount> findByAccountIdWithoutStatus(@Param("accountId")Integer accountId);

	/**
	 * Connects to database and returns all Customer accounts whose account id partially or fully matches given account id
	 * and account status is equal to given status.
	 * @author Nitin K.
	 * @param accountId Integer
	 * @param accountStatus String
	 * @return list of CustomerAccount
	 */
	@Query(value="select * from cust_acct where acct_id  like :accountId%  and acct_status=:accountStatus",nativeQuery = true)
	public List<CustomerAccount> findByAccountIdAndAccountStatus(@Param("accountId") Integer accountId,
			@Param("accountStatus") String accountStatus);

	/**
	 * Connects to database and sets the account status to 'Terminate' and end date to current date for a given account id.
	 * @author Nitin K.
	 * @param currentDate Date
	 * @param accountId Integer
	 * @return id of the updated customer account.
	 */
	@Modifying
	@Query("update CustomerAccount set accountStatus='Terminate', endDate=:currentDate where accountId=:accountId")
	public Integer terminateAccountService(@Param("currentDate") Date currentDate,
			@Param("accountId") Integer accountId);
}