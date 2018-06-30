package com.ge.tms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "bill_charge_configuration")
public class BillChargeConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billChargeConfigurationId;

	private String tarrifPlan;

	private Boolean networkCharges;

	private Boolean congestionCharges;

	private Boolean fixedCharges;

	private Boolean deemandCharges;

	private Boolean loadSizeCharges;

	private Boolean energyComissionCharges;

	private Boolean taxCharges;

	private Boolean miscCharges;

	private Date effectiveDate;

	private Date terminationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acct_id", nullable = false)
	private CustomerAccount customerAccount;

	public Boolean getNetworkCharges() {
		return networkCharges;
	}

	public void setNetworkCharges(Boolean networkCharges) {
		this.networkCharges = networkCharges;
	}

	public Boolean getCongestionCharges() {
		return congestionCharges;
	}

	public void setCongestionCharges(Boolean congestionCharges) {
		this.congestionCharges = congestionCharges;
	}

	public Boolean getFixedCharges() {
		return fixedCharges;
	}

	public void setFixedCharges(Boolean fixedCharges) {
		this.fixedCharges = fixedCharges;
	}

	public Boolean getDeemandCharges() {
		return deemandCharges;
	}

	public void setDeemandCharges(Boolean deemandCharges) {
		this.deemandCharges = deemandCharges;
	}

	public Boolean getLoadSizeCharges() {
		return loadSizeCharges;
	}

	public void setLoadSizeCharges(Boolean loadSizeCharges) {
		this.loadSizeCharges = loadSizeCharges;
	}

	public Boolean getEnergyComissionCharges() {
		return energyComissionCharges;
	}

	public void setEnergyComissionCharges(Boolean energyComissionCharges) {
		this.energyComissionCharges = energyComissionCharges;
	}

	public Boolean getTaxCharges() {
		return taxCharges;
	}

	public void setTaxCharges(Boolean taxCharges) {
		this.taxCharges = taxCharges;
	}

	public Boolean getMiscCharges() {
		return miscCharges;
	}

	public void setMiscCharges(Boolean miscCharges) {
		this.miscCharges = miscCharges;
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

	@JsonBackReference
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getTarrifPlan() {
		return tarrifPlan;
	}

	public void setTarrifPlan(String tarrifPlan) {
		this.tarrifPlan = tarrifPlan;
	}

}
