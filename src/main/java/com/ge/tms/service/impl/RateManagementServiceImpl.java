package com.ge.tms.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ge.tms.dao.GenericRatesHistoryRepository;
import com.ge.tms.dao.GenericRatesRepository;
import com.ge.tms.dao.TOURatesHistoryRepository;
import com.ge.tms.dao.TOURatesRepository;
import com.ge.tms.dao.TieredRatesHistoryRepository;
import com.ge.tms.dao.TieredRatesRepository;
import com.ge.tms.entity.GenericRates;
import com.ge.tms.entity.GenericRatesHistory;
import com.ge.tms.entity.TOURates;
import com.ge.tms.entity.TOURatesHistory;
import com.ge.tms.entity.TieredRates;
import com.ge.tms.entity.TieredRatesHistory;
import com.ge.tms.service.RateManagementService;

/**
 * Service class containing methods to get rate management data.
 * @author Nitin K.
 */
@Service
public class RateManagementServiceImpl implements RateManagementService {

	@Autowired
	private TieredRatesRepository tieredRatesRepository;
	
	@Autowired
	private TieredRatesHistoryRepository tieredRatesHistoryRepository;
	
	@Autowired
	private TOURatesRepository touRatesRepository;
	
	@Autowired
	private TOURatesHistoryRepository touRatesHistoryRepository;

	@Autowired
	private GenericRatesRepository genericRatesRepository;
	
	
	@Autowired
	private GenericRatesHistoryRepository genericRatesHistoryRepository;
	
	
	@Autowired
    DozerBeanMapper dozerBeanMapper;

    static Mapper mapper = new DozerBeanMapper();
	
    /**
     * Method to save a new Tiered Rate object
     * @author Nitin K.
     * @param tieredRates TieredRates
     * @return success/failed message
     */
	@Override
	public ResponseEntity<String> saveTieredRates(TieredRates tieredRates) {
		
		String result = null;
		TieredRates tieredRates2 = tieredRatesRepository.save(tieredRates);
		if(tieredRates2!=null)
		{
			TieredRatesHistory tieredRatesHistory = mapper.map(tieredRates2, TieredRatesHistory.class);
			
			tieredRatesHistoryRepository.save(tieredRatesHistory);
			result = "Success";
			return new ResponseEntity<String>(result,HttpStatus.OK);
			
		}else{
			result = "Error occured while saving record try after some time";
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
		
	}

	 /**
     * Method to get all Tiered Rates
     * @author Nitin K.
     * @return List<TieredRates>
     */
	@Override
	public ResponseEntity<List<TieredRates>> getTieredRates() {
		List<TieredRates> tieredRates2 = (List<TieredRates>) tieredRatesRepository.findAll();
		
		if(tieredRates2!=null && tieredRates2.size()>0){
			return new ResponseEntity<List<TieredRates>>(tieredRates2,HttpStatus.OK);
		}
		return new ResponseEntity<List<TieredRates>>(tieredRates2,HttpStatus.NOT_FOUND);
	}

	/**
     * Method to get all Tiered Rates history
     * @author Nitin K.
     * @return List<TieredRatesHistory>
     */
	@Override
	public ResponseEntity<List<TieredRatesHistory>> getTieredRatesHistory() {
		List<TieredRatesHistory> tieredRates2 = (List<TieredRatesHistory>) tieredRatesHistoryRepository.findAll();
		
		if(tieredRates2!=null && tieredRates2.size()>0){
			return new ResponseEntity<List<TieredRatesHistory>>(tieredRates2,HttpStatus.OK);
		}
		return new ResponseEntity<List<TieredRatesHistory>>(tieredRates2,HttpStatus.NOT_FOUND);
	}

	/**
     * Method to save a new tou Rate object
     * @author Nitin K.
     * @param touRates TOURates
     * @return success/failed message
     */
	@Override
	public ResponseEntity<String> saveTouRates(TOURates touRates) {
		String result = null;
		TOURates touRates2 = touRatesRepository.save(touRates);
		if(touRates2!=null)
		{
			TOURatesHistory touRatesHistory = mapper.map(touRates2, TOURatesHistory.class);
			
			touRatesHistoryRepository.save(touRatesHistory);
			result = "Success";
			return new ResponseEntity<String>(result,HttpStatus.OK);
			
		}else{
			result = "Error occured while saving record try after some time";
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
	}

	/**
     * Method to get all Tou Rates
     * @author Nitin K.
     * @return List<TOURates>
     */
	@Override
	public ResponseEntity<List<TOURates>> getTouRates() {
	List<TOURates> list = (List<TOURates>) touRatesRepository.findAll();
		
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<TOURates>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<TOURates>>(list,HttpStatus.NOT_FOUND);
	}

	/**
     * Method to get all Tou Rates History
     * @author Nitin K.
     * @return List<TOURatesHistory>
     */
	@Override
	public ResponseEntity<List<TOURatesHistory>> getTouRatesHistory() {
		List<TOURatesHistory> list = (List<TOURatesHistory>) touRatesHistoryRepository.findAll();
		
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<TOURatesHistory>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<TOURatesHistory>>(list,HttpStatus.NOT_FOUND);
	}

	/**
     * Method to save a new generic Rate object
     * @author Nitin K.
     * @param genericRates GenericRates
     * @return success/failed message
     */
	@Override
	public ResponseEntity<String> saveGenericRates(GenericRates genericRates) {
		String result = null;
		GenericRates genericRates2 = genericRatesRepository.save(genericRates);
		if(genericRates2!=null)
		{
			GenericRatesHistory genericRatesHistory = mapper.map(genericRates2, GenericRatesHistory.class);
			
			genericRatesHistoryRepository.save(genericRatesHistory);
			result = "Success";
			return new ResponseEntity<String>(result,HttpStatus.OK);
			
		}else{
			result = "Error occured while saving record try after some time";
			return new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
	}

	/**
     * Method to get all Generic Rates
     * @author Nitin K.
     * @return List<GenericRates>
     */
	@Override
	public ResponseEntity<List<GenericRates>> getGenericRates() {
		List<GenericRates> list = (List<GenericRates>) genericRatesRepository.findAll();
		
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<GenericRates>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<GenericRates>>(list,HttpStatus.NOT_FOUND);
	}

	/**
     * Method to get all Generic Rates History
     * @author Nitin K.
     * @return List<GenericRatesHistory>
     */
	@Override
	public ResponseEntity<List<GenericRatesHistory>> getGenericRatesHistory() {
		List<GenericRatesHistory> list = (List<GenericRatesHistory>) genericRatesHistoryRepository.findAll();
		
		if(list!=null && list.size()>0){
			return new ResponseEntity<List<GenericRatesHistory>>(list,HttpStatus.OK);
		}
		return new ResponseEntity<List<GenericRatesHistory>>(list,HttpStatus.NOT_FOUND);
	}

}
