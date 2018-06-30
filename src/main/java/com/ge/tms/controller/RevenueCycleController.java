package com.ge.tms.controller;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ge.tms.common.consts.UrlPathConstants;
import com.ge.tms.dto.ArAagingDataResponse;
import com.ge.tms.dto.RevenueCycleData;
import com.ge.tms.service.RevenueCycleService;

/**
 * @author Nitin K.
 * Contains APIs for getting revenue cycle data and ARAging data.
 */
@RestController
@RequestMapping(value = UrlPathConstants.REVENUE_CYCLE)
public class RevenueCycleController {
	private static final Logger logger = LoggerFactory.getLogger(RevenueCycleController.class);

	@Autowired
	private RevenueCycleService revenueCycleService;

	/**
	 * @author Nitin K.
	 * @param currentDate String
	 * @purpose to get data of revenue cycle for month to date
	 * @return ResponseEntity<RevenueCycleData>
	 * @throws ParseException
	 * @date 2017-07-31
	 */
	@RequestMapping(value = UrlPathConstants.REVENUE_CYCLE_DATA_MONTH_TO_DATE, method = RequestMethod.GET)
	public ResponseEntity<RevenueCycleData> getRevenueCycleDataMonthToDate(
			@RequestParam("currentDate") String currentDate) throws ParseException {
		logger.info("Entered into getRevenueCycleDataMonthToDate controller.......");
		ResponseEntity<RevenueCycleData> result = revenueCycleService.getRevenueCycleData(null, currentDate, true);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @param currentDate String
	 * @purpose to get data of revenue cycle for year to date
	 * @return ResponseEntity<RevenueCycleData>
	 * @throws ParseException
	 * @date 2017-07-31
	 */
	@RequestMapping(value = UrlPathConstants.REVENUE_CYCLE_DATA_YEAR_TO_DATE, method = RequestMethod.GET)
	public ResponseEntity<RevenueCycleData> getRevenueCycleDataYearToDate(
			@RequestParam("currentDate") String currentDate) throws ParseException {
		logger.info("Entered into getRevenueCycleDataYearToDate controller.......");
		ResponseEntity<RevenueCycleData> result = revenueCycleService.getRevenueCycleData(null, currentDate, false);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @param startDate String
	 * @param endDate String
	 * @purpose to get data of revenue cycle based on given custom dates
	 * @return ResponseEntity<RevenueCycleData>
	 * @throws ParseException
	 * @date 2017-07-31
	 */
	@RequestMapping(value = UrlPathConstants.REVENUE_CYCLE_DATA_CUSTOM, method = RequestMethod.GET)
	public ResponseEntity<RevenueCycleData> getRevenueCycleDataCustom(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) throws ParseException {
		logger.info("Entered into getRevenueCycleDataCustom controller.......");
		ResponseEntity<RevenueCycleData> result = revenueCycleService.getRevenueCycleData(startDate, endDate, false);
		return result;
	}

	/**
	 * @author Nitin K.
	 * @param currentDate String
	 * @param lessThanValue Long
	 * @param greaterThanValue Long
	 * @purpose to get data of ArRaging
	 * @return ResponseEntity<ArAagingDataResponse>
	 * @throws ParseException
	 * @date 2017-08-07
	 */
	@RequestMapping(value = UrlPathConstants.AR_RAGING_DATA, method = RequestMethod.GET)
	public ResponseEntity<ArAagingDataResponse> getARRagingData(@RequestParam("currentDate") String currentDate,
			@RequestParam("lessThanValue") Long lessThanValue, @RequestParam("greaterThanValue") Long greaterThanValue)
			throws ParseException {
		logger.info("Entered into getARRagingData controller.......");
		ResponseEntity<ArAagingDataResponse> result = revenueCycleService.getARRagingData(currentDate,lessThanValue,greaterThanValue);
		return result;
	}
}