package com.ge.tms.dto;

import java.math.BigDecimal;

/**
 * This class is used to store the IntervalDataManagement response data.
 * @author Nitin K.
 */
public class IntervalDataManagement {

	private String customerAccountNumber;
	private String meterNumber;
	private String months;
	private String kWh;
	public String getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	public void setCustomerAccountNumber(String customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}
	public String getMeterNumber() {
		return meterNumber;
	}
	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getkWh() {
		return kWh;
	}
	public void setkWh(String kWh) {
		this.kWh = kWh;
	}
	
}
