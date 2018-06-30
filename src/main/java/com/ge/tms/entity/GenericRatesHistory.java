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
@Table(name = "generic_rates_history")
public class GenericRatesHistory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genericRateHistoryId;
	
	@Column(name = "generic_rate_id")
	private Integer genericRateId;

	@Column(name = "rate_schedule")
	private String rateSchedule;
	
	@Column(name = "rate_plan")
	private String ratePlan;
	
	@Column(name = "rate_schedule_version")
	private String rateScheduleVersion;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "termination_date")
	private Date terminationDate;
	
	@Column(name = "single_phase_service", precision = 10, scale = 2)
	private BigDecimal singlePhaseService;
	
	@Column(name = "poly_phase_service", precision = 10, scale = 2)
	private BigDecimal polyPhaseService;
	
	@Column(name = "custServ_charge_plusMeter", precision = 10, scale = 2)
	private BigDecimal custServChargeplusMeter;
	
	
	@Column(name = "seasonal_yn")
	private String SeasonalYN;
	
	@Column(name = "demand_summer_charge", precision = 10, scale = 2)
	private BigDecimal demandSummerCharge;
	
	@Column(name = "demand_winter_charge", precision = 10, scale = 2)
	private BigDecimal demandWinterCharge;
	
	@Column(name = "energy_summer_charge", precision = 10, scale = 2)
	private BigDecimal energySummerCharge;
	
	@Column(name = "energy_winter_charge", precision = 10, scale = 2)
	private BigDecimal energyWinterCharge;
	
	@Column(name = "energy_non_seasonal_charge", precision = 10, scale = 2)
	private BigDecimal energyNonSeasonalCharge;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "rate_approval_status")
	private String rateApprovalStatus;

	public Integer getGenericRateHistoryId() {
		return genericRateHistoryId;
	}

	public void setGenericRateHistoryId(Integer genericRateHistoryId) {
		this.genericRateHistoryId = genericRateHistoryId;
	}

	public Integer getGenericRateId() {
		return genericRateId;
	}

	public void setGenericRateId(Integer genericRateId) {
		this.genericRateId = genericRateId;
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

	public String getRateScheduleVersion() {
		return rateScheduleVersion;
	}

	public void setRateScheduleVersion(String rateScheduleVersion) {
		this.rateScheduleVersion = rateScheduleVersion;
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

	public BigDecimal getSinglePhaseService() {
		return singlePhaseService;
	}

	public void setSinglePhaseService(BigDecimal singlePhaseService) {
		this.singlePhaseService = singlePhaseService;
	}

	public BigDecimal getPolyPhaseService() {
		return polyPhaseService;
	}

	public void setPolyPhaseService(BigDecimal polyPhaseService) {
		this.polyPhaseService = polyPhaseService;
	}

	public BigDecimal getCustServChargeplusMeter() {
		return custServChargeplusMeter;
	}

	public void setCustServChargeplusMeter(BigDecimal custServChargeplusMeter) {
		this.custServChargeplusMeter = custServChargeplusMeter;
	}

	public String getSeasonalYN() {
		return SeasonalYN;
	}

	public void setSeasonalYN(String seasonalYN) {
		SeasonalYN = seasonalYN;
	}

	public BigDecimal getDemandSummerCharge() {
		return demandSummerCharge;
	}

	public void setDemandSummerCharge(BigDecimal demandSummerCharge) {
		this.demandSummerCharge = demandSummerCharge;
	}

	public BigDecimal getDemandWinterCharge() {
		return demandWinterCharge;
	}

	public void setDemandWinterCharge(BigDecimal demandWinterCharge) {
		this.demandWinterCharge = demandWinterCharge;
	}

	public BigDecimal getEnergySummerCharge() {
		return energySummerCharge;
	}

	public void setEnergySummerCharge(BigDecimal energySummerCharge) {
		this.energySummerCharge = energySummerCharge;
	}

	public BigDecimal getEnergyWinterCharge() {
		return energyWinterCharge;
	}

	public void setEnergyWinterCharge(BigDecimal energyWinterCharge) {
		this.energyWinterCharge = energyWinterCharge;
	}

	public BigDecimal getEnergyNonSeasonalCharge() {
		return energyNonSeasonalCharge;
	}

	public void setEnergyNonSeasonalCharge(BigDecimal energyNonSeasonalCharge) {
		this.energyNonSeasonalCharge = energyNonSeasonalCharge;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRateApprovalStatus() {
		return rateApprovalStatus;
	}

	public void setRateApprovalStatus(String rateApprovalStatus) {
		this.rateApprovalStatus = rateApprovalStatus;
	}
	
	
}
