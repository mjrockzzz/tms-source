package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillingPeakHour;

/**
 * Interface for generic CRUD operations on BillingPeakHour table in database.
 * @author Nitin K.
 */
public interface BillingPeakHourRepository extends CrudRepository<BillingPeakHour, Integer> {

}
