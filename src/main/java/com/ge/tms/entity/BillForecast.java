package com.ge.tms.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Table(name = "bill_forecast")
public class BillForecast {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billForecastId;

	// Many To One relationship between BillForecast and CustomerAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Acct_ID", nullable = false)
	private CustomerAccount customerAccount;

	@Column(name = "forecastbill_amt", precision = 10, scale = 0)
	private BigDecimal forecastBillAmount;

	@Column(name = "bill_todate", precision = 10, scale = 2)
	private BigDecimal billTodate;

	@Column(name = "next_billcycle_date")
	private Date nextBillCycleDate;

	public Integer getBillForecastId() {
		return billForecastId;
	}

	public void setBillForecastId(Integer billForecastId) {
		this.billForecastId = billForecastId;
	}

	@JsonIgnore
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public BigDecimal getForecastBillAmount() {
		return forecastBillAmount;
	}

	public void setForecastBillAmount(BigDecimal forecastBillAmount) {
		this.forecastBillAmount = forecastBillAmount;
	}

	public BigDecimal getBillTodate() {
		return billTodate;
	}

	public void setBillTodate(BigDecimal billTodate) {
		this.billTodate = billTodate;
	}

	public Date getNextBillCycleDate() {
		return nextBillCycleDate;
	}

	public void setNextBillCycleDate(Date nextBillCycleDate) {
		this.nextBillCycleDate = nextBillCycleDate;
	}

}
