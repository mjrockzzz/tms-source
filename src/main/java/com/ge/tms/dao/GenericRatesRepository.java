package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.GenericRates;

/**
 * Interface for generic CRUD operations on GenericRates table in database.
 * @author Nitin K.
 */
public interface GenericRatesRepository extends CrudRepository<GenericRates,Integer>{

}
