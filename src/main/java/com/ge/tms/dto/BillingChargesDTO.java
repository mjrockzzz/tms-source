package com.ge.tms.dto;

/**
 * This class is used to send the BillingCharges Response data.
 * @author Nitin K.
 */
public class BillingChargesDTO {

	private Boolean networkCharges;

	private Boolean congestionCharges;

	private Boolean fixedCharges;

	private Boolean deemandCharges;

	private Boolean loadSizeCharges;

	private Boolean energyComissionCharges;

	private Boolean taxCharges;

	private Boolean miscCharges;

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

}
