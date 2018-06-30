package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillForecast;

/**
 * Interface for generic CRUD operations on BillForecast table in database.
 * @author Nitin K.
 */
public interface BillForecastRepository extends CrudRepository<BillForecast, Integer> {

}
