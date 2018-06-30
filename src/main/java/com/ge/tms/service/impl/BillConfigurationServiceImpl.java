package com.ge.tms.service.impl;

import java.text.ParseException;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.dao.BillConfigurationHistoryRepository;
import com.ge.tms.dao.BillConfigurationRepository;
import com.ge.tms.entity.BillConfiguration;
import com.ge.tms.entity.BillConfigurationHistory;
import com.ge.tms.service.BillConfigurationService;
import com.ge.tms.util.DateUtility;
import com.ge.tms.audit.UserRevisionListener;

/**
 * Service class containing methods to get bill configuration and history details.
 * @author Nitin K.
 */
@Service
public class BillConfigurationServiceImpl implements BillConfigurationService {
	private static final Logger logger = LoggerFactory.getLogger(BillConfigurationServiceImpl.class);

	
	@Autowired
	private BillConfigurationRepository billConfigurationRepository;
	
	
	@Autowired
	private BillConfigurationHistoryRepository billConfigurationHistoryRepository;
	
	@Autowired
    DozerBeanMapper dozerBeanMapper;

    static Mapper mapper = new DozerBeanMapper();
    
	 
	/**
	 * Method to update a bill configuration
	 * @param billConfiguration BillConfiguration
	 * @author Nitin K.
	 * @return success/failed message
	 */
	@Override
	public ResponseEntity<String> saveUpdateBillConfiguration(BillConfiguration billConfiguration) {
		logger.debug("Entered into BillConfigurationServiceImpl controller.. saveUpdateBillConfiguration() method called");
		String result = null;
		BillConfiguration billConfigurationInput = mapper.map(billConfiguration, BillConfiguration.class);
		 
		billConfigurationInput.setStatus("Pending");
		UserRevisionListener userRevisionListener = new UserRevisionListener();
		billConfigurationInput.setCreatedBy(userRevisionListener.getAdminName());
		//Date terminationDate = DateUtility.getExtendedSqlDatewithDays(billConfigurationInput.getEffectiveDate(),7);
		try {
			billConfigurationInput.setTerminationDate(DateUtility.getExtendedSqlDatewithDays(billConfigurationInput.getEffectiveDate(),7));
			billConfigurationInput.setApprovedDate(DateUtility.getExtendedSqlDatewithDays(billConfigurationInput.getCreatedDate(),7));
			
		} catch (ParseException e) {
			
		}
		
		BillConfiguration billConfiguration2 = billConfigurationRepository.save(billConfigurationInput);
		if(billConfiguration2!=null)
		{
			BillConfigurationHistory billConfigurationHistory = mapper.map(billConfiguration2, BillConfigurationHistory.class);
			
			billConfigurationHistoryRepository.save(billConfigurationHistory);
			result = "Success";
			return new ResponseEntity<String>(result,HttpStatus.OK);
			
		}else{
			result = "Error occured while saving record try after some time";
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to get all bill configuration list
	 * @author Nitin K.
	 * @return List<BillConfiguration>
	 */
	@Override
	public ResponseEntity<List<BillConfiguration>> getBillConfigurationList() {
		logger.debug("Entered into BillConfigurationServiceImpl controller.. getBillConfigurationList() method called");
		List<BillConfiguration> list = (List<BillConfiguration>) billConfigurationRepository.findAll();
		
		logger.info("List is::"+list.size());
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<BillConfiguration>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<BillConfiguration>>(list,HttpStatus.NOT_FOUND);
	}

	/**
	 * Method to get all bill configuration history list
	 * @author Nitin K.
	 * @return List<BillConfigurationHistory>
	 */
	@Override
	public ResponseEntity<List<BillConfigurationHistory>> getBillConfigurationHistoryList() {
		logger.debug("Entered into BillConfigurationServiceImpl controller.. getBillConfigurationHistoryList() method called");
		List<BillConfigurationHistory> list = (List<BillConfigurationHistory>) billConfigurationHistoryRepository.findAll();
		
		logger.info("List is::"+list.size());
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<BillConfigurationHistory>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<BillConfigurationHistory>>(list,HttpStatus.NOT_FOUND);
	}
}
