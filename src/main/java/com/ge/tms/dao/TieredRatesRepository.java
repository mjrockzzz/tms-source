package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.TieredRates;

/**
 * Interface for generic CRUD operations on TieredRates table in database.
 * @author Nitin K.
 */
public interface TieredRatesRepository extends CrudRepository<TieredRates,Integer>{

}
