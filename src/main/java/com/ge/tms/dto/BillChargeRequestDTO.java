package com.ge.tms.dto;

import java.sql.Date;

import com.ge.tms.entity.CustomerAccount;

/**
 * This class is used to get the BillCharge Request data.
 * @author Nitin K.
 */
public class BillChargeRequestDTO {

	private Integer billChargeConfigurationId;

	private String tarrifPlan;

	private BillingChargesDTO charges;

	private Date effectiveDate;

	private Date terminationDate;

	private CustomerAccount customerAccount;

	public String getTarrifPlan() {
		return tarrifPlan;
	}

	public void setTarrifPlan(String tarrifPlan) {
		this.tarrifPlan = tarrifPlan;
	}

	public BillingChargesDTO getCharges() {
		return charges;
	}

	public void setCharges(BillingChargesDTO charges) {
		this.charges = charges;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public Integer getBillChargeConfigurationId() {
		return billChargeConfigurationId;
	}

	public void setBillChargeConfigurationId(Integer billChargeConfigurationId) {
		this.billChargeConfigurationId = billChargeConfigurationId;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

}
