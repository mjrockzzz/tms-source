package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.GenericRatesHistory;

/**
 * Interface for generic CRUD operations on GenericRatesHistory table in database.
 * @author Nitin K.
 */
public interface GenericRatesHistoryRepository extends CrudRepository<GenericRatesHistory,Integer>{

}
