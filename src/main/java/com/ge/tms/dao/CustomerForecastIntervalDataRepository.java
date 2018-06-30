package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.CustomerForecastIntervalData;

/**
 * Interface for generic CRUD operations on CustomerForecastIntervalData table in database.
 * @author Nitin K.
 */
public interface CustomerForecastIntervalDataRepository extends CrudRepository<CustomerForecastIntervalData, Integer> {

}
