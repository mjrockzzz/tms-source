package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.TieredRatesHistory;

/**
 * Interface for generic CRUD operations on TieredRatesHistory table in database.
 * @author Nitin K.
 */
public interface TieredRatesHistoryRepository extends CrudRepository<TieredRatesHistory,Integer>{

}
