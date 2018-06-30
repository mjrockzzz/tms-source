package com.ge.tms.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "billing_peak_hour")
public class BillingPeakHour {

	@Id
	private Integer billingPeakHourId;

	@Column(name = "start_peak_hour", length = 2)
	private Integer startPeakHour;

	@Column(name = "end_peak_hour", length = 2)
	private Integer endPeakHour;

	@Column(name = "trigger_temperature", precision =5, scale = 0)
	private BigDecimal triggerTemperature;

	@Column(name = "month", length = 2)
	private Integer month;

	public Integer getBillingPeakHourId() {
		return billingPeakHourId;
	}

	public void setBillingPeakHourId(Integer billingPeakHourId) {
		this.billingPeakHourId = billingPeakHourId;
	}

	public Integer getStartPeakHour() {
		return startPeakHour;
	}

	public void setStartPeakHour(Integer startPeakHour) {
		this.startPeakHour = startPeakHour;
	}

	public Integer getEndPeakHour() {
		return endPeakHour;
	}

	public void setEndPeakHour(Integer endPeakHour) {
		this.endPeakHour = endPeakHour;
	}

	public BigDecimal getTriggerTemperature() {
		return triggerTemperature;
	}

	public void setTriggerTemperature(BigDecimal triggerTemperature) {
		this.triggerTemperature = triggerTemperature;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
