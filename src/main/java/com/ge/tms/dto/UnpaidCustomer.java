package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is used to store the UnpaidCustomer response data.
 * @author Nitin K.
 */
public class UnpaidCustomer {
	private String customerName;
	private Date billDueDate;
	private BigDecimal billAmount;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getBillDueDate() {
		return billDueDate;
	}
	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}
	public BigDecimal getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}
	
}
