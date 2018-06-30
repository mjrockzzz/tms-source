package com.ge.tms.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiered_rates_history")
public class TieredRatesHistory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tieredRateHistoryId;
	
	
	@Column(name = "tiered_rate_id")
	private Integer tieredRateId;

	@Column(name = "rate_schedule")
	private String rateSchedule;
	
	@Column(name = "rate_plan")
	private String ratePlan;
	
	@Column(name = "customer_class")
	private String customerClass;
	
	
	@Column(name = "rate_schedule_version", precision = 10, scale = 2)
	private BigDecimal rateScheduleVersion;
	
	@Column(name = "delivery_min_bill_amt", precision = 10, scale = 2)
	private BigDecimal deliveryMinBillAmt;
	
	@Column(name = "min_avg_rate_limite", precision = 10, scale = 2)
	private BigDecimal minAvgRateLimite;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "termination_date")
	private Date terminationDate;
	
	
	@Column(name = "tier1", precision = 10, scale = 2)
	private BigDecimal tier1;
	
	@Column(name = "tier2", precision = 10, scale = 2)
	private BigDecimal tier2;
	
	@Column(name = "tier3", precision = 10, scale = 2)
	private BigDecimal tier3;
	
	@Column(name = "tier4", precision = 10, scale = 2)
	private BigDecimal tier4;
	
	@Column(name = "tier5", precision = 10, scale = 2)
	private BigDecimal tier5;
	
	@Column(name = "tier6", precision = 10, scale = 2)
	private BigDecimal tier6;
	
	@Column(name = "tier7", precision = 10, scale = 2)
	private BigDecimal tier7;
	
	@Column(name = "tier8", precision = 10, scale = 2)
	private BigDecimal tier8;
	
	@Column(name = "tier9", precision = 10, scale = 2)
	private BigDecimal tier9;
	
	@Column(name = "tier10", precision = 10, scale = 2)
	private BigDecimal tier10;
	
	
	@Column(name = "Rate_approval_status")
	private String rateApprovalStatus;


	
	
	public Integer getTieredRateHistoryId() {
		return tieredRateHistoryId;
	}


	public void setTieredRateHistoryId(Integer tieredRateHistoryId) {
		this.tieredRateHistoryId = tieredRateHistoryId;
	}


	public Integer getTieredRateId() {
		return tieredRateId;
	}


	public void setTieredRateId(Integer tieredRateId) {
		this.tieredRateId = tieredRateId;
	}


	public String getRateSchedule() {
		return rateSchedule;
	}


	public void setRateSchedule(String rateSchedule) {
		this.rateSchedule = rateSchedule;
	}


	public String getRatePlan() {
		return ratePlan;
	}


	public void setRatePlan(String ratePlan) {
		this.ratePlan = ratePlan;
	}


	public String getCustomerClass() {
		return customerClass;
	}


	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}


	public BigDecimal getRateScheduleVersion() {
		return rateScheduleVersion;
	}


	public void setRateScheduleVersion(BigDecimal rateScheduleVersion) {
		this.rateScheduleVersion = rateScheduleVersion;
	}


	public BigDecimal getDeliveryMinBillAmt() {
		return deliveryMinBillAmt;
	}


	public void setDeliveryMinBillAmt(BigDecimal deliveryMinBillAmt) {
		this.deliveryMinBillAmt = deliveryMinBillAmt;
	}


	public BigDecimal getMinAvgRateLimite() {
		return minAvgRateLimite;
	}


	public void setMinAvgRateLimite(BigDecimal minAvgRateLimite) {
		this.minAvgRateLimite = minAvgRateLimite;
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


	public BigDecimal getTier1() {
		return tier1;
	}


	public void setTier1(BigDecimal tier1) {
		this.tier1 = tier1;
	}


	public BigDecimal getTier2() {
		return tier2;
	}


	public void setTier2(BigDecimal tier2) {
		this.tier2 = tier2;
	}


	public BigDecimal getTier3() {
		return tier3;
	}


	public void setTier3(BigDecimal tier3) {
		this.tier3 = tier3;
	}


	public BigDecimal getTier4() {
		return tier4;
	}


	public void setTier4(BigDecimal tier4) {
		this.tier4 = tier4;
	}


	public BigDecimal getTier5() {
		return tier5;
	}


	public void setTier5(BigDecimal tier5) {
		this.tier5 = tier5;
	}


	public BigDecimal getTier6() {
		return tier6;
	}


	public void setTier6(BigDecimal tier6) {
		this.tier6 = tier6;
	}


	public BigDecimal getTier7() {
		return tier7;
	}


	public void setTier7(BigDecimal tier7) {
		this.tier7 = tier7;
	}


	public BigDecimal getTier8() {
		return tier8;
	}


	public void setTier8(BigDecimal tier8) {
		this.tier8 = tier8;
	}


	public BigDecimal getTier9() {
		return tier9;
	}


	public void setTier9(BigDecimal tier9) {
		this.tier9 = tier9;
	}


	public BigDecimal getTier10() {
		return tier10;
	}


	public void setTier10(BigDecimal tier10) {
		this.tier10 = tier10;
	}


	public String getRateApprovalStatus() {
		return rateApprovalStatus;
	}


	public void setRateApprovalStatus(String rateApprovalStatus) {
		this.rateApprovalStatus = rateApprovalStatus;
	}
	
	
	
}
