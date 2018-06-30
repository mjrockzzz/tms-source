package com.ge.tms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.entity.BillAdjustmentMaster;
import com.ge.tms.entity.BillAdjustmentMasterHistory;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.service.BillAdjustmentService;

/**
 * @author Jameer
 * Contains APIs to get Bill Adjustment details, save bill adjustments etc.
 */
@RestController
@RequestMapping(value = UrlPathConstants.BILL_ADJUSTMENT)
public class BillAdjustmentController {

	private static final Logger logger = LoggerFactory.getLogger(BillAdjustmentController.class);

	@Autowired
	private BillAdjustmentService billAdjustmentService;

	/**
	 * @author Jameer
	 * @purpose to get billAdjustment master list
	 * @return ResponseEntity<List<BillAdjustmentMaster>>
	 * @date 2017-09-11
	 */
	@RequestMapping(value = UrlPathConstants.GET_ALL_BILL_ADJUSTMENT_MASTER, method = RequestMethod.GET)
	public ResponseEntity<List<BillAdjustmentMaster>> getAllBillAdjustmentMaster() {
		logger.info("Entered into BillAdjustmentController controller....... getAllBillAdjustmentMaster()");
		ResponseEntity<List<BillAdjustmentMaster>> result = billAdjustmentService.getAllBillAdjustmentMaster();
		return result;
	}
	
	/**
	 * @author Jameer.
	 * @purpose to save the bill adjustment master Details
	 * @param billAdjustmentMaster BillAdjustmentMaster
	 * @return ResponseEntity<String>
	 * @date 2017-09-11
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_UPDATE_BILL_ADJUSTEMENT_MASTER, method = RequestMethod.POST)
	public ResponseEntity<String> saveUpdateBillAdjustmentMaster(@RequestBody BillAdjustmentMaster billAdjustmentMaster) {
		logger.info("Entered into BillAdjustmentController controller....... saveUpdateBillAdjustmentMaster()");
		ResponseEntity<String> result = billAdjustmentService.saveUpdateBillAdjustmentMaster(billAdjustmentMaster);
		return result;
	}
	
	/**
	 * @author Jameer
	 * @purpose to get billAdjustmentmaster history list
	 * @return ResponseEntity<List<BillAdjustmentMasterHistory>>
	 * @date 2017-09-27
	 */
	@RequestMapping(value = UrlPathConstants.GET_ALL_BILL_ADJUSTMENT_MASTER_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<BillAdjustmentMasterHistory>> getBillAdjustmentMasterHistory() {
		logger.info("Entered into BillAdjustmentController controller....... getAllBillAdjustmentMaster()");
		return billAdjustmentService.getBillAdjustmentMasterHistory();
	}
}