package com.ge.tms.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class is used to send the RevenueCycle response data.
 * @author Nitin K.
 */
public class RevenueCycleData {

	private Integer totalCustomerBilled;

	private Integer totalCustomerPaid;

	private BigDecimal totalBillAmount;

	private BigDecimal totalBillReceived;

	private BigDecimal totalPaymentRecdBeforeDueDate;

	private BigDecimal totalPaymentRecdAfterDueDate;

	private BigDecimal totalOutstandingAmount;

	private BigDecimal totalPercentageOnPenalties;

	private RevenueGraphData revenueCycleGraphData;
	
	private List<UnpaidCustomer> unpaidCustomerList;

	private String typeOfGraph;

	public Integer getTotalCustomerBilled() {
		return totalCustomerBilled;
	}

	public void setTotalCustomerBilled(Integer totalCustomerBilled) {
		this.totalCustomerBilled = totalCustomerBilled;
	}

	public Integer getTotalCustomerPaid() {
		return totalCustomerPaid;
	}

	public void setTotalCustomerPaid(Integer totalCustomerPaid) {
		this.totalCustomerPaid = totalCustomerPaid;
	}

	public BigDecimal getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(BigDecimal totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public BigDecimal getTotalBillReceived() {
		return totalBillReceived;
	}

	public void setTotalBillReceived(BigDecimal totalBillReceived) {
		this.totalBillReceived = totalBillReceived;
	}

	public BigDecimal getTotalPaymentRecdBeforeDueDate() {
		return totalPaymentRecdBeforeDueDate;
	}

	public void setTotalPaymentRecdBeforeDueDate(BigDecimal totalPaymentRecdBeforeDueDate) {
		this.totalPaymentRecdBeforeDueDate = totalPaymentRecdBeforeDueDate;
	}

	public BigDecimal getTotalPaymentRecdAfterDueDate() {
		return totalPaymentRecdAfterDueDate;
	}

	public void setTotalPaymentRecdAfterDueDate(BigDecimal totalPaymentRecdAfterDueDate) {
		this.totalPaymentRecdAfterDueDate = totalPaymentRecdAfterDueDate;
	}

	public BigDecimal getTotalOutstandingAmount() {
		return totalOutstandingAmount;
	}

	public void setTotalOutstandingAmount(BigDecimal totalOutstandingAmount) {
		this.totalOutstandingAmount = totalOutstandingAmount;
	}

	public RevenueGraphData getRevenueCycleGraphData() {
		return revenueCycleGraphData;
	}

	public void setRevenueCycleGraphData(RevenueGraphData revenueCycleGraphData) {
		this.revenueCycleGraphData = revenueCycleGraphData;
	}

	public String getTypeOfGraph() {
		return typeOfGraph;
	}

	public void setTypeOfGraph(String typeOfGraph) {
		this.typeOfGraph = typeOfGraph;
	}

	public BigDecimal getTotalPercentageOnPenalties() {
		return totalPercentageOnPenalties;
	}

	public void setTotalPercentageOnPenalties(BigDecimal totalPercentageOnPenalties) {
		this.totalPercentageOnPenalties = totalPercentageOnPenalties;
	}

	public List<UnpaidCustomer> getUnpaidCustomerList() {
		return unpaidCustomerList;
	}

	public void setUnpaidCustomerList(List<UnpaidCustomer> unpaidCustomerList) {
		this.unpaidCustomerList = unpaidCustomerList;
	}

	

	

	

}
