package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillConfiguration;

/**
 * Interface for generic CRUD operations on BillConfiguration table in database.
 * @author Nitin K.
 */
public interface BillConfigurationRepository extends CrudRepository<BillConfiguration,Integer>{

}
