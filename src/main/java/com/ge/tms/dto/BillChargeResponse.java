package com.ge.tms.dto;

/**
 * This class is used to send the BillCharge response data.
 * @author Nitin K.
 */
public class BillChargeResponse {

	private String tarrifPlan;

	private String networkCharges;

	private String congestionCharges;

	private String fixedCharges;

	private String deemandCharges;

	private String loadSizeCharges;

	private String energyComissionCharges;

	private String taxCharges;

	private String miscCharges;

	private String totalAmount;

	public String getTarrifPlan() {
		return tarrifPlan;
	}

	public void setTarrifPlan(String tarrifPlan) {
		this.tarrifPlan = tarrifPlan;
	}

	public String getNetworkCharges() {
		return networkCharges;
	}

	public void setNetworkCharges(String networkCharges) {
		this.networkCharges = networkCharges;
	}

	public String getCongestionCharges() {
		return congestionCharges;
	}

	public void setCongestionCharges(String congestionCharges) {
		this.congestionCharges = congestionCharges;
	}

	public String getFixedCharges() {
		return fixedCharges;
	}

	public void setFixedCharges(String fixedCharges) {
		this.fixedCharges = fixedCharges;
	}

	public String getDeemandCharges() {
		return deemandCharges;
	}

	public void setDeemandCharges(String deemandCharges) {
		this.deemandCharges = deemandCharges;
	}

	public String getLoadSizeCharges() {
		return loadSizeCharges;
	}

	public void setLoadSizeCharges(String loadSizeCharges) {
		this.loadSizeCharges = loadSizeCharges;
	}

	public String getEnergyComissionCharges() {
		return energyComissionCharges;
	}

	public void setEnergyComissionCharges(String energyComissionCharges) {
		this.energyComissionCharges = energyComissionCharges;
	}

	public String getTaxCharges() {
		return taxCharges;
	}

	public void setTaxCharges(String taxCharges) {
		this.taxCharges = taxCharges;
	}

	public String getMiscCharges() {
		return miscCharges;
	}

	public void setMiscCharges(String miscCharges) {
		this.miscCharges = miscCharges;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

}
