package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * This class is used to send the UsageHistory response data.
 * @author Nitin K.
 */
public class UsageHistory {

	private BigDecimal meternumber;
	private Date meterDate;
	private BigDecimal meterReading;
	private BigDecimal deemand;
	private String applicableRate;
	private Time metertime;
	private BigDecimal billAmount;
	private Date paymentDueDate;
	private Date billSentDate;
	private Date billGrenerationDate;
	private Date paymentPostedDate;
	private String paymentStatus;
	private String paymentMethod;
	private BigDecimal paidAmount;

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

	public BigDecimal getDeemand() {
		return deemand;
	}

	public void setDeemand(BigDecimal deemand) {
		this.deemand = deemand;
	}

	public String getApplicableRate() {
		return applicableRate;
	}

	public void setApplicableRate(String applicableRate) {
		this.applicableRate = applicableRate;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}

	public Date getBillSentDate() {
		return billSentDate;
	}

	public void setBillSentDate(Date billSentDate) {
		this.billSentDate = billSentDate;
	}

	public Date getBillGrenerationDate() {
		return billGrenerationDate;
	}

	public void setBillGrenerationDate(Date billGrenerationDate) {
		this.billGrenerationDate = billGrenerationDate;
	}

	public Date getPaymentPostedDate() {
		return paymentPostedDate;
	}

	public void setPaymentPostedDate(Date paymentPostedDate) {
		this.paymentPostedDate = paymentPostedDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Time getMetertime() {
		return metertime;
	}

	public void setMetertime(Time metertime) {
		this.metertime = metertime;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

}
