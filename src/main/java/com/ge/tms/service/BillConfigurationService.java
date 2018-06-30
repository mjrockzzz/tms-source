package com.ge.tms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ge.tms.entity.BillConfiguration;
import com.ge.tms.entity.BillConfigurationHistory;

/**
 * Service class containing methods to get bill configuration and history details.
 * @author Nitin K.
 */
public interface BillConfigurationService {

	/**
	 * Method to update a bill configuration
	 * @param billConfiguration BillConfiguration
	 * @author Nitin K.
	 * @return success/failed message
	 */
	ResponseEntity<String> saveUpdateBillConfiguration(BillConfiguration billConfiguration);

	/**
	 * Method to get all bill configuration list
	 * @author Nitin K.
	 * @return List<BillConfiguration>
	 */
	ResponseEntity<List<BillConfiguration>> getBillConfigurationList();

	/**
	 * Method to get all bill configuration history list
	 * @author Nitin K.
	 * @return List<BillConfigurationHistory>
	 */
	ResponseEntity<List<BillConfigurationHistory>> getBillConfigurationHistoryList();

}
