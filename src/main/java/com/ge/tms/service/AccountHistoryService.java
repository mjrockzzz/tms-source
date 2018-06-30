package com.ge.tms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ge.tms.dto.CustomerBillHistory;
import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerMeterHistory;

/**
 * Service class containing methods to get customer meter, bill and account history.
 * @author Nitin K.
 */
public interface AccountHistoryService {

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Account History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerAccount>
	 * @date 2017-08-17
	 */
	public ResponseEntity<List<CustomerAccount>> getCustomerAccountHistoryByAcctId(Integer accountId);

	/**
	 * @author Nitin K.
	 * @purpose to get Customer Bill History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerBillHistory>>
	 * @date 2017-08-17
	 */
	public ResponseEntity<List<CustomerBillHistory>> getCustomerBillHistoryByAcctId(Integer accountId);
	
	/**
	 * @author Nitin K.
	 * @purpose to get Customer Meter History by Account Id
	 * @param accountId Integer
	 * @return ResponseEntity<List<CustomerMeterHistory>>
	 * @date 2017-08-21
	 */
	public ResponseEntity<List<CustomerMeterHistory>> getCustomerMeterHistoryByAcctId(Integer accountId);

}
