package com.ge.tms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ge.tms.entity.GenericRates;
import com.ge.tms.entity.GenericRatesHistory;
import com.ge.tms.entity.TOURates;
import com.ge.tms.entity.TOURatesHistory;
import com.ge.tms.entity.TieredRates;
import com.ge.tms.entity.TieredRatesHistory;

/**
 * Service class containing methods to get rate management data.
 * @author Nitin K.
 */
public interface RateManagementService {

	/**
     * Method to save a new Tiered Rate object
     * @author Nitin K.
     * @param tieredRates TieredRates
     * @return success/failed message
     */
	ResponseEntity<String> saveTieredRates(TieredRates tieredRates);

	 /**
     * Method to get all Tiered Rates
     * @author Nitin K.
     * @return List<TieredRates>
     */
	ResponseEntity<List<TieredRates>> getTieredRates();

	/**
     * Method to get all Tiered Rates history
     * @author Nitin K.
     * @return List<TieredRatesHistory>
     */
	ResponseEntity<List<TieredRatesHistory>> getTieredRatesHistory();

	/**
     * Method to save a new tou Rate object
     * @author Nitin K.
     * @param touRates TOURates
     * @return success/failed message
     */
	ResponseEntity<String> saveTouRates(TOURates touRates);

	/**
     * Method to get all Tou Rates
     * @author Nitin K.
     * @return List<TOURates>
     */
	ResponseEntity<List<TOURates>> getTouRates();

	/**
     * Method to get all Tou Rates History
     * @author Nitin K.
     * @return List<TOURatesHistory>
     */
	ResponseEntity<List<TOURatesHistory>> getTouRatesHistory();

	/**
     * Method to save a new generic Rate object
     * @author Nitin K.
     * @param genericRates GenericRates
     * @return success/failed message
     */
	ResponseEntity<String> saveGenericRates(GenericRates genericRates);

	/**
     * Method to get all Generic Rates
     * @author Nitin K.
     * @return List<GenericRates>
     */
	ResponseEntity<List<GenericRates>> getGenericRates();

	/**
     * Method to get all Generic Rates History
     * @author Nitin K.
     * @return List<GenericRatesHistory>
     */
	ResponseEntity<List<GenericRatesHistory>> getGenericRatesHistory();

}
