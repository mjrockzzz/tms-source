package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillAdjustmentMaster;

/**
 * Interface for generic CRUD operations on BillAdjustmentMaster table in database.
 * @author Nitin K.
 */
public interface BillAdjustmentMasterRepository extends CrudRepository<BillAdjustmentMaster,Integer>{

}
