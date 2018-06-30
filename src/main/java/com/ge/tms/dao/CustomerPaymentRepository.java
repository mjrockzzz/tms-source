package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerPayment;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerPayment table in database.
 */
public interface CustomerPaymentRepository extends CrudRepository<CustomerPayment, Integer> {

	/**
	 * Connects to database and returns customer payment details for a given customer account.
	 * @author Nitin K.
	 * @param customerAccount CustomerAccount
	 * @return CustomerPayment
	 */
	public CustomerPayment findByCustomerAccount(CustomerAccount customerAccount);
	
	/**
	 * Connects to database and returns customer payment details for a given customer payment id.
	 * @author Nitin K.
	 * @param customerPaymentId Integer
	 * @return CustomerPayment
	 */
	public CustomerPayment findByCustomerPaymentId(Integer customerPaymentId);
}