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
@Table(name = "tou_rates")
public class TOURates {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer touRateId;

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
	private BigDecimal SinglePhaseService;
	
	@Column(name = "poly_phase_service", precision = 10, scale = 2)
	private BigDecimal PolyPhaseService;
	
	@Column(name = "custServ_charge_plusMeter", precision = 10, scale = 2)
	private BigDecimal CustServChargeplusMeter;
	
	@Column(name = "demand_summer_Onpeak", precision = 10, scale = 2)
	private BigDecimal demandSummerOnPeak;
	
	@Column(name = "demand_summer_part_peak", precision = 10, scale = 2)
	private BigDecimal demandSummerPartPeak;
	
	@Column(name = "demand_summer_off_peak", precision = 10, scale = 2)
	private BigDecimal demandSummeroffPeak;
	
	@Column(name = "demand_Winter_part_peak", precision = 10, scale = 2)
	private BigDecimal demandWinterPartPeak;
	
	@Column(name = "demand_Winter_Off_peak", precision = 10, scale = 2)
	private BigDecimal demandWinterOffPeak;
	
	
	@Column(name = "energy_summer_Onpeak", precision = 10, scale = 2)
	private BigDecimal energySummerOnPeak;
	
	@Column(name = "energy_summer_part_peak", precision = 10, scale = 2)
	private BigDecimal energySummerPartPeak;
	

	@Column(name = "energy_summer_off_peak", precision = 10, scale = 2)
	private BigDecimal energySummeroffPeak;
	

	@Column(name = "energy_Winter_part_peak", precision = 10, scale = 2)
	private BigDecimal energyWinterPartPeak;
	

	@Column(name = "energy_winter_off_peak", precision = 10, scale = 2)
	private BigDecimal energyWinterOffPeak;
	

	@Column(name = "rate_approval_status")
	private String rateApprovalStatus;


	public Integer getTouRateId() {
		return touRateId;
	}


	public void setTouRateId(Integer touRateId) {
		this.touRateId = touRateId;
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
		return SinglePhaseService;
	}


	public void setSinglePhaseService(BigDecimal singlePhaseService) {
		SinglePhaseService = singlePhaseService;
	}


	public BigDecimal getPolyPhaseService() {
		return PolyPhaseService;
	}


	public void setPolyPhaseService(BigDecimal polyPhaseService) {
		PolyPhaseService = polyPhaseService;
	}


	public BigDecimal getCustServChargeplusMeter() {
		return CustServChargeplusMeter;
	}


	public void setCustServChargeplusMeter(BigDecimal custServChargeplusMeter) {
		CustServChargeplusMeter = custServChargeplusMeter;
	}


	public BigDecimal getDemandSummerOnPeak() {
		return demandSummerOnPeak;
	}


	public void setDemandSummerOnPeak(BigDecimal demandSummerOnPeak) {
		this.demandSummerOnPeak = demandSummerOnPeak;
	}


	public BigDecimal getDemandSummerPartPeak() {
		return demandSummerPartPeak;
	}


	public void setDemandSummerPartPeak(BigDecimal demandSummerPartPeak) {
		this.demandSummerPartPeak = demandSummerPartPeak;
	}


	public BigDecimal getDemandSummeroffPeak() {
		return demandSummeroffPeak;
	}


	public void setDemandSummeroffPeak(BigDecimal demandSummeroffPeak) {
		this.demandSummeroffPeak = demandSummeroffPeak;
	}


	public BigDecimal getDemandWinterPartPeak() {
		return demandWinterPartPeak;
	}


	public void setDemandWinterPartPeak(BigDecimal demandWinterPartPeak) {
		this.demandWinterPartPeak = demandWinterPartPeak;
	}


	public BigDecimal getDemandWinterOffPeak() {
		return demandWinterOffPeak;
	}


	public void setDemandWinterOffPeak(BigDecimal demandWinterOffPeak) {
		this.demandWinterOffPeak = demandWinterOffPeak;
	}


	public BigDecimal getEnergySummerOnPeak() {
		return energySummerOnPeak;
	}


	public void setEnergySummerOnPeak(BigDecimal energySummerOnPeak) {
		this.energySummerOnPeak = energySummerOnPeak;
	}


	public BigDecimal getEnergySummerPartPeak() {
		return energySummerPartPeak;
	}


	public void setEnergySummerPartPeak(BigDecimal energySummerPartPeak) {
		this.energySummerPartPeak = energySummerPartPeak;
	}


	public BigDecimal getEnergySummeroffPeak() {
		return energySummeroffPeak;
	}


	public void setEnergySummeroffPeak(BigDecimal energySummeroffPeak) {
		this.energySummeroffPeak = energySummeroffPeak;
	}


	public BigDecimal getEnergyWinterPartPeak() {
		return energyWinterPartPeak;
	}


	public void setEnergyWinterPartPeak(BigDecimal energyWinterPartPeak) {
		this.energyWinterPartPeak = energyWinterPartPeak;
	}


	public BigDecimal getEnergyWinterOffPeak() {
		return energyWinterOffPeak;
	}


	public void setEnergyWinterOffPeak(BigDecimal energyWinterOffPeak) {
		this.energyWinterOffPeak = energyWinterOffPeak;
	}


	public String getRateApprovalStatus() {
		return rateApprovalStatus;
	}


	public void setRateApprovalStatus(String rateApprovalStatus) {
		this.rateApprovalStatus = rateApprovalStatus;
	}
	
	
}
