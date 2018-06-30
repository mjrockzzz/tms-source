package com.ge.tms.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerMeterHistory;

/**
 * @author Nitin K.
 * This interface contains methods to get data from CustomerMeterHistory table in database.
 */
public interface CustomerMeterHistoryRepository extends CrudRepository<CustomerMeterHistory, Integer> {

    /**
	 * Connects to database and returns all meter history data for a given customer account.
	 * @author Nitin K.
     * @param customerAccount CustomerAccount
     * @return list of CustomerMeterHistory
     */
    public List<CustomerMeterHistory> findByCustomerAccount(CustomerAccount customerAccount);
}