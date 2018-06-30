package com.ge.tms.dto;

import java.math.BigDecimal;

/**
 * This class is used to send the ArAging graph data response.
 * @author Nitin K.
 */
public class ArAgingGraphData {

	private Long customerCount30Days;

	private Long customerCount60Days;

	private Long customerCount90Days;

	private Long customerCount120Days;

	private Long customerCountMoreThan120Days;

	private BigDecimal customerAmount30Days;

	private BigDecimal customerAmount60Days;

	private BigDecimal customerAmount90Days;

	private BigDecimal customerAmount120Days;

	private BigDecimal customerAmountMoreThan120Days;

	private Integer graphDataYear;

	public Long getCustomerCount30Days() {
		return customerCount30Days;
	}

	public void setCustomerCount30Days(Long customerCount30Days) {
		this.customerCount30Days = customerCount30Days;
	}

	public Long getCustomerCount60Days() {
		return customerCount60Days;
	}

	public void setCustomerCount60Days(Long customerCount60Days) {
		this.customerCount60Days = customerCount60Days;
	}

	public Long getCustomerCount90Days() {
		return customerCount90Days;
	}

	public void setCustomerCount90Days(Long customerCount90Days) {
		this.customerCount90Days = customerCount90Days;
	}

	public Long getCustomerCount120Days() {
		return customerCount120Days;
	}

	public void setCustomerCount120Days(Long customerCount120Days) {
		this.customerCount120Days = customerCount120Days;
	}

	public BigDecimal getCustomerAmount30Days() {
		return customerAmount30Days;
	}

	public void setCustomerAmount30Days(BigDecimal customerAmount30Days) {
		this.customerAmount30Days = customerAmount30Days;
	}

	public BigDecimal getCustomerAmount60Days() {
		return customerAmount60Days;
	}

	public void setCustomerAmount60Days(BigDecimal customerAmount60Days) {
		this.customerAmount60Days = customerAmount60Days;
	}

	public BigDecimal getCustomerAmount90Days() {
		return customerAmount90Days;
	}

	public void setCustomerAmount90Days(BigDecimal customerAmount90Days) {
		this.customerAmount90Days = customerAmount90Days;
	}

	public BigDecimal getCustomerAmount120Days() {
		return customerAmount120Days;
	}

	public void setCustomerAmount120Days(BigDecimal customerAmount120Days) {
		this.customerAmount120Days = customerAmount120Days;
	}

	public Integer getGraphDataYear() {
		return graphDataYear;
	}

	public void setGraphDataYear(Integer graphDataYear) {
		this.graphDataYear = graphDataYear;
	}

	public Long getCustomerCountMoreThan120Days() {
		return customerCountMoreThan120Days;
	}

	public void setCustomerCountMoreThan120Days(Long customerCountMoreThan120Days) {
		this.customerCountMoreThan120Days = customerCountMoreThan120Days;
	}

	public BigDecimal getCustomerAmountMoreThan120Days() {
		return customerAmountMoreThan120Days;
	}

	public void setCustomerAmountMoreThan120Days(BigDecimal customerAmountMoreThan120Days) {
		this.customerAmountMoreThan120Days = customerAmountMoreThan120Days;
	}

}
