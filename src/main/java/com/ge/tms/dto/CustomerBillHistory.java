package com.ge.tms.dto;

import java.sql.Timestamp;

/**
 * This class is used to send the CustomerBillHistory Response data.
 * @author Nitin K.
 */
public class CustomerBillHistory {

	private Integer customerBillId;

	private String billTempalte;

	private String customerClass;

	private String currentRatePlan;

	private Timestamp rateEffective;

	public Integer getCustomerBillId() {
		return customerBillId;
	}

	public void setCustomerBillId(Integer customerBillId) {
		this.customerBillId = customerBillId;
	}

	public String getBillTempalte() {
		return billTempalte;
	}

	public void setBillTempalte(String billTempalte) {
		this.billTempalte = billTempalte;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public String getCurrentRatePlan() {
		return currentRatePlan;
	}

	public void setCurrentRatePlan(String currentRatePlan) {
		this.currentRatePlan = currentRatePlan;
	}

	public Timestamp getRateEffective() {
		return rateEffective;
	}

	public void setRateEffective(Timestamp rateEffective) {
		this.rateEffective = rateEffective;
	}

}
