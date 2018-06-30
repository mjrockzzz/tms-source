package com.ge.tms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.dao.BillAdjustmentMasterHistoryRepository;
import com.ge.tms.dao.BillAdjustmentMasterRepository;
import com.ge.tms.entity.BillAdjustmentMaster;
import com.ge.tms.entity.BillAdjustmentMasterHistory;
import com.ge.tms.service.BillAdjustmentService;

/**
 * Service class containing methods to get bill adjustment and history details.
 * @author Nitin K.
 */
@Service
public class BillAdjustmentServiceImpl implements BillAdjustmentService {
	private static final Logger logger = LoggerFactory.getLogger(BillAdjustmentServiceImpl.class);

	@Autowired
	private BillAdjustmentMasterRepository billAdjustmentMasterRepository;
	
	@Autowired
	private BillAdjustmentMasterHistoryRepository billAdjustmentMasterHistoryRepository;

	
	@Autowired
    DozerBeanMapper dozerBeanMapper;

    static Mapper mapper = new DozerBeanMapper();
	
    /**
     * Method to get all the bill adjustment list
     * @author Nitin K.
	 * @return List<BillAdjustmentMaster>
	 */
	@Override
	public ResponseEntity<List<BillAdjustmentMaster>> getAllBillAdjustmentMaster() {
		logger.debug("Inside BillAdjustmentServiceImpl ::  getAllBillAdjustmentMaster method called");
		List<BillAdjustmentMaster> list = new ArrayList<BillAdjustmentMaster>();
		list = (List<BillAdjustmentMaster>) billAdjustmentMasterRepository.findAll();
		logger.info("List Size::"+list.size());
		if(list.size()!=0 && !list.isEmpty()){
		return new ResponseEntity<List<BillAdjustmentMaster>>(list, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<BillAdjustmentMaster>>(list, HttpStatus.NOT_FOUND);
		}
	}
	
	
	/**
     * Method to get all the bill adjustment history list
     * @author Nitin K.
	 * @return List<BillAdjustmentMasterHistory>
	 */
	@Override
	public ResponseEntity<List<BillAdjustmentMasterHistory>> getBillAdjustmentMasterHistory() {
		logger.debug("Inside BillAdjustmentServiceImpl ::  getBillAdjustmentMasterHistory method called");
		List<BillAdjustmentMasterHistory> list = new ArrayList<BillAdjustmentMasterHistory>();
		list = (List<BillAdjustmentMasterHistory>) billAdjustmentMasterHistoryRepository.findAll();
		logger.info("List Size::"+list.size());
		if(list.size()!=0 && !list.isEmpty()){
		return new ResponseEntity<List<BillAdjustmentMasterHistory>>(list, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<BillAdjustmentMasterHistory>>(list, HttpStatus.NOT_FOUND);
		}
	}

	/**
     * Method to get update a bill adjustment.
     * @param billAdjustmentMaster BillAdjustmentMaster
     * @author Nitin K.
	 * @return success/failed message
	 */
	@Override
	public ResponseEntity<String> saveUpdateBillAdjustmentMaster(BillAdjustmentMaster billAdjustmentMaster) {
		logger.debug("Inside BillAdjustmentServiceImpl ::  saveUpdateBillAdjustmentMaster method called");
		System.out.println("OBJ************"+billAdjustmentMaster);
		BillAdjustmentMaster billAdjustmentMaster2 = billAdjustmentMasterRepository.save(billAdjustmentMaster);
		if (billAdjustmentMaster2 != null) {
			
			BillAdjustmentMasterHistory billAdjustmentMasterHistory = mapper.map(billAdjustmentMaster2,BillAdjustmentMasterHistory.class);
			billAdjustmentMasterHistoryRepository.save(billAdjustmentMasterHistory);
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(UrlPathConstants.UPDATE_FAILURE, HttpStatus.NOT_FOUND);
		}
	}
}
