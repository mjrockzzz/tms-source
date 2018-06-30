package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is used to send the ArAging table data response.
 * @author Nitin K.
 */
public class ArAgingData {

	private String customerName;

	private Integer customerId;

	private BigDecimal billAmount;

	private Long daysBeyondDueDate;

	private Integer noOfRemindersSent;

	private Date nextReminderdate;

	private Date finalReminderdate;

	private Date billDueDate;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public Long getDaysBeyondDueDate() {
		return daysBeyondDueDate;
	}

	public void setDaysBeyondDueDate(Long daysBeyondDueDate) {
		this.daysBeyondDueDate = daysBeyondDueDate;
	}

	public Integer getNoOfRemindersSent() {
		return noOfRemindersSent;
	}

	public void setNoOfRemindersSent(Integer noOfRemindersSent) {
		this.noOfRemindersSent = noOfRemindersSent;
	}

	public Date getNextReminderdate() {
		return nextReminderdate;
	}

	public void setNextReminderdate(Date nextReminderdate) {
		this.nextReminderdate = nextReminderdate;
	}

	public Date getFinalReminderdate() {
		return finalReminderdate;
	}

	public void setFinalReminderdate(Date finalReminderdate) {
		this.finalReminderdate = finalReminderdate;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

}
