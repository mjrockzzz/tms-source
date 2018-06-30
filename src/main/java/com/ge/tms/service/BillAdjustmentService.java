package com.ge.tms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ge.tms.entity.BillAdjustmentMaster;
import com.ge.tms.entity.BillAdjustmentMasterHistory;

/**
 * Service class containing methods to get bill adjustment and history details.
 * @author Nitin K.
 */
public interface BillAdjustmentService {

	/**
     * Method to get all the bill adjustment's list
     * @author Nitin K.
	 * @return List<BillAdjustmentMaster>
	 */
	ResponseEntity<List<BillAdjustmentMaster>> getAllBillAdjustmentMaster();
	
	/**
     * Method to get all the bill adjustment history list
     * @author Nitin K.
	 * @return List<BillAdjustmentMasterHistory>
	 */
	ResponseEntity<List<BillAdjustmentMasterHistory>> getBillAdjustmentMasterHistory();
	
	/**
     * Method to get update a bill adjustment.
     * @param billAdjustmentMaster BillAdjustmentMaster
     * @author Nitin K.
	 * @return success/failed message
	 */
	ResponseEntity<String> saveUpdateBillAdjustmentMaster(BillAdjustmentMaster billAdjustmentMaster);

}
