package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * This class is used to get and send the IntervalDataHistory request and response data.
 * @author Nitin K.
 */
public class IntervalDataHistory {

	private BigDecimal meternumber;
	private Date meterDate;
	private BigDecimal meterReading;
	private BigDecimal temprature;
	private Date billingCycleStartdate;
	private Date billingCycleEnddate;
	private Time meterTime;

	public BigDecimal getMeternumber() {
		return meternumber;
	}

	public void setMeternumber(BigDecimal meternumber) {
		this.meternumber = meternumber;
	}

	public Date getMeterDate() {
		return meterDate;
	}

	public void setMeterDate(Date meterDate) {
		this.meterDate = meterDate;
	}

	public BigDecimal getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(BigDecimal meterReading) {
		this.meterReading = meterReading;
	}

	public Date getBillingCycleStartdate() {
		return billingCycleStartdate;
	}

	public void setBillingCycleStartdate(Date billingCycleStartdate) {
		this.billingCycleStartdate = billingCycleStartdate;
	}

	public Date getBillingCycleEnddate() {
		return billingCycleEnddate;
	}

	public void setBillingCycleEnddate(Date billingCycleEnddate) {
		this.billingCycleEnddate = billingCycleEnddate;
	}

	public BigDecimal getTemprature() {
		return temprature;
	}

	public void setTemprature(BigDecimal temprature) {
		this.temprature = temprature;
	}

	public Time getMeterTime() {
		return meterTime;
	}

	public void setMeterTime(Time meterTime) {
		this.meterTime = meterTime;
	}

	
}
