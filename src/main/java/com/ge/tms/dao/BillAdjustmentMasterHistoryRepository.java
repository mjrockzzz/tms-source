package com.ge.tms.dao;

import org.springframework.data.repository.CrudRepository;

import com.ge.tms.entity.BillAdjustmentMasterHistory;

/**
 * Interface for generic CRUD operations on BillAdjustmentMasterHistory table in database.
 * @author Nitin K.
 */
public interface BillAdjustmentMasterHistoryRepository extends CrudRepository<BillAdjustmentMasterHistory, Integer>{

}
