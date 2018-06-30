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
import com.ge.tms.entity.GenericRates;
import com.ge.tms.entity.GenericRatesHistory;
import com.ge.tms.entity.TOURates;
import com.ge.tms.entity.TOURatesHistory;
import com.ge.tms.entity.TieredRates;
import com.ge.tms.entity.TieredRatesHistory;
import com.ge.tms.service.RateManagementService;

/**
 * @author Jameer
 * Contains API for getting and saving Rate management details.
 */
@RestController
@RequestMapping(value = UrlPathConstants.RATE_MANAGEMENT)
public class RateManagementController {
	private static final Logger logger = LoggerFactory.getLogger(RateManagementController.class);

	@Autowired
	private RateManagementService rateManagementService;
	
	/**
	 * @author Jameer
	 * @purpose to save tiered rates object
	 * @param tieredRates TieredRates
	 * @return ResponseEntity<String>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_TIERED_RATES, method = RequestMethod.POST)
	public ResponseEntity<String> saveTieredRates(@RequestBody TieredRates tieredRates) {
		logger.info("Entered into RateManagementController controller.. saveTieredRates() method called");
		return rateManagementService.saveTieredRates(tieredRates);
	}
	
	/**
	 * @author Jameer
	 * @purpose to get list of tiered rates object
	 * @return ResponseEntity<List<TieredRates>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_TIERED_RATES, method = RequestMethod.GET)
	public ResponseEntity<List<TieredRates>> getTieredRates() {
		logger.info("Entered into RateManagementController controller.. getTieredRates() method called");
		return rateManagementService.getTieredRates();
	}
	
	/**
	 * @author Jameer
	 * @purpose to get list of tiered history rates object
	 * @return ResponseEntity<List<TieredRatesHistory>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_TIERED_RATES_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<TieredRatesHistory>> getTieredRatesHistory() {
		logger.info("Entered into RateManagementController controller.. getTieredRatesHistory() method called");
		return rateManagementService.getTieredRatesHistory();
	}	
	
	/**
	 * @author Jameer
	 * @purpose to save tou rates object
	 * @param touRates TOURates
	 * @return ResponseEntity<String>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_TOU_RATES, method = RequestMethod.POST)
	public ResponseEntity<String> saveTouRates(@RequestBody TOURates touRates) {
		logger.info("Entered into RateManagementController controller.. saveTouRates() method called");
		return rateManagementService.saveTouRates(touRates);
	}	
	
	/**
	 * @author Jameer
	 * @purpose to get list of tou rates object
	 * @return ResponseEntity<List<TOURates>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_TOU_RATES, method = RequestMethod.GET)
	public ResponseEntity<List<TOURates>> getTouRates() {
		logger.info("Entered into RateManagementController controller.. getTouRates() method called");
		return rateManagementService.getTouRates();
	}	
	
	/**
	 * @author Jameer
	 * @purpose to get list of tou history rates object
	 * @return ResponseEntity<List<TOURatesHistory>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_TOU_RATES_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<TOURatesHistory>> getTouRatesHistory() {
		logger.info("Entered into RateManagementController controller.. getTouRatesHistory() method called");
		return rateManagementService.getTouRatesHistory();
	}	
	
	/**
	 * @author Jameer
	 * @purpose to save generic rates object
	 * @param genericRates GenericRates
	 * @return ResponseEntity<String>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.SAVE_GENERIC_RATES, method = RequestMethod.POST)
	public ResponseEntity<String> saveGenericRates(@RequestBody GenericRates genericRates) {
		logger.info("Entered into RateManagementController controller.. saveGenericRates() method called");
		return rateManagementService.saveGenericRates(genericRates);
	}	
	
	/**
	 * @author Jameer
	 * @purpose to get list of generic rates object
	 * @return ResponseEntity<List<GenericRates>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_GENERIC_RATES, method = RequestMethod.GET)
	public ResponseEntity<List<GenericRates>> getGenericRates() {
		logger.info("Entered into RateManagementController controller.. getGenericRates() method called");
		return rateManagementService.getGenericRates();
	}	
	
	/**
	 * @author Jameer
	 * @purpose to get list of generic  history rates object
	 * @return ResponseEntity<List<GenericRatesHistory>>
	 * @date 2017-09-25
	 */
	@RequestMapping(value = UrlPathConstants.GET_GENERIC_RATES_HISTORY, method = RequestMethod.GET)
	public ResponseEntity<List<GenericRatesHistory>> getGenericRatesHistory() {
		logger.info("Entered into RateManagementController controller.. getGenericRatesHistory() method called");
		return rateManagementService.getGenericRatesHistory();
	}
}