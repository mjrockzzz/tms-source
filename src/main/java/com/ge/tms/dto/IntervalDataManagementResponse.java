package com.ge.tms.dto;

import java.util.List;

/**
 * This class is used to send the IntervalDataManagement response data.
 * @author Nitin K.
 */
public class IntervalDataManagementResponse {
	private String message;
	private List<IntervalDataManagement> intervalDataManagementDailyList;
	private List<IntervalDataManagementMonthly> intervalDataManagementMonthlyList;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<IntervalDataManagement> getIntervalDataManagementDailyList() {
		return intervalDataManagementDailyList;
	}
	public void setIntervalDataManagementDailyList(List<IntervalDataManagement> intervalDataManagementDailyList) {
		this.intervalDataManagementDailyList = intervalDataManagementDailyList;
	}
	public List<IntervalDataManagementMonthly> getIntervalDataManagementMonthlyList() {
		return intervalDataManagementMonthlyList;
	}
	public void setIntervalDataManagementMonthlyList(
			List<IntervalDataManagementMonthly> intervalDataManagementMonthlyList) {
		this.intervalDataManagementMonthlyList = intervalDataManagementMonthlyList;
	}
	
	
}
