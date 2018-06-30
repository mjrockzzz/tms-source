package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillConfigurationHistory;

/**
 * Interface for generic CRUD operations on BillConfigurationHistory table in database.
 * @author Nitin K.
 */
public interface BillConfigurationHistoryRepository extends CrudRepository<BillConfigurationHistory,Integer>{

}
