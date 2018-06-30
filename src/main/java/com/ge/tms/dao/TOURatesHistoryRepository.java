package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.TOURatesHistory;

/**
 * Interface for generic CRUD operations on TOURatesHistory table in database.
 * @author Nitin K.
 */
public interface TOURatesHistoryRepository  extends CrudRepository<TOURatesHistory,Integer>{

}
