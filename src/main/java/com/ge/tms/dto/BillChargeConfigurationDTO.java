package com.ge.tms.dto;

/**
 * This class is used to send the BillChargeConfiguration response.
 * @author Nitin K.
 */
public class BillChargeConfigurationDTO {

	private String tarrifPlan;

	private BillingChargesDTO charges;

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

}
