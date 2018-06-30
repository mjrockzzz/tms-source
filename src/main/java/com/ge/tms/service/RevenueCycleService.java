package com.ge.tms.service;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;

import com.ge.tms.dto.ArAagingDataResponse;
import com.ge.tms.dto.RevenueCycleData;

/**
 * Service class containing methods to get revenue cycle and araging data.
 * @author Nitin K.
 */
public interface RevenueCycleService {

	/**
	 * @author Nitin K.
	 * @param startDate String
	 * @parma endDate String
	 * @param monthToDate boolean
	 * @purpose to get data of revenue cycle based on given dates
	 * @return ResponseEntity<RevenueCycleData>
	 * @throws ParseException
	 * @date 2017-07-31
	 */
	public ResponseEntity<RevenueCycleData> getRevenueCycleData(String startDate, String endDate, boolean monthToDate)
			throws ParseException;

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
	public  ResponseEntity<ArAagingDataResponse> getARRagingData(String currentDate,Long lessThanValue,Long greaterThanValue) throws ParseException;
}
