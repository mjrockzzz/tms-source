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
import com.ge.tms.entity.BillConfiguration;
import com.ge.tms.entity.BillConfigurationHistory;
import com.ge.tms.service.BillConfigurationService;

/**
 * @author Jameer
 * Contains APIs for getting bill configurations, saving bill configuration etc.
 */
@RestController
@RequestMapping(value = UrlPathConstants.BILL_CONFIGURATION)
public class BillConfigurationController {
	private static final Logger logger = LoggerFactory.getLogger(BillConfigurationController.class);

	@Autowired
	private BillConfigurationService billConfigurationService;

	/**
	 * @author Jameer
	 * @purpose to save tiered rates object
	 * @param billConfiguration BillConfiguration
	 * @return ResponseEntity<String>
	 * @date 2017-09-26
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_UPDATE_BILL_CONFIGURAION, method = RequestMethod.POST)
	public ResponseEntity<String> saveUpdateBillConfiguration(@RequestBody BillConfiguration billConfiguration) {
		logger.info("Entered into BillConfigurationController controller.. saveUpdateBillConfiguration() method called");
		return billConfigurationService.saveUpdateBillConfiguration(billConfiguration);
	}
	
	/**
	 * @author Jameer
	 * @purpose to get list of tiered rates object
	 * @return ResponseEntity<List<BillConfiguration>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_CONFIGURATION_LIST, method = RequestMethod.GET)
	public ResponseEntity<List<BillConfiguration>> getBillConfigurationList() {
		logger.info("Entered into BillConfigurationController controller.. getBillConfigurationList() method called");
		return billConfigurationService.getBillConfigurationList();
	}
	
	/**
	 * @author Jameer
	 * @purpose to get list of tiered history rates object
	 * @return ResponseEntity<List<BillConfigurationHistory>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_BILL_CONFIGURATION_HISTORY_LIST, method = RequestMethod.GET)
	public ResponseEntity<List<BillConfigurationHistory>> getBillConfigurationHistoryList() {
		logger.info("Entered into BillConfigurationController controller.. getBillConfigurationHistoryList() method called");
		return billConfigurationService.getBillConfigurationHistoryList();
	}
}