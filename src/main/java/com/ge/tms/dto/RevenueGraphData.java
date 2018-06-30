package com.ge.tms.dto;

import java.util.List;

/**
 * This class is used to send the RevenueGraph response data.
 * @author Nitin K.
 */
public class RevenueGraphData {

	private List<RevenueGraphBilled> revenueGraphBilledList;

	private List<RevenueGraphCollected> revenueGraphCollectedList;

	public List<RevenueGraphBilled> getRevenueGraphBilledList() {
		return revenueGraphBilledList;
	}

	public void setRevenueGraphBilledList(List<RevenueGraphBilled> revenueGraphBilledList) {
		this.revenueGraphBilledList = revenueGraphBilledList;
	}

	public List<RevenueGraphCollected> getRevenueGraphCollectedList() {
		return revenueGraphCollectedList;
	}

	public void setRevenueGraphCollectedList(List<RevenueGraphCollected> revenueGraphCollectedList) {
		this.revenueGraphCollectedList = revenueGraphCollectedList;
	}

}
