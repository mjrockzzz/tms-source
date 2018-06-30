package com.ge.tms.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * This class is used to send the IntervalDataHistory response data.
 * @author Nitin K.
 */
public class IntervalDataHistoryResponse {

	private List<IntervalDataHistory> intervalDataHistory;

	private String typeOfGraph;

	private BigDecimal aggregatedPeakDemandKwh;

	public List<IntervalDataHistory> getIntervalDataHistory() {
		return intervalDataHistory;
	}

	public void setIntervalDataHistory(List<IntervalDataHistory> intervalDataHistory) {
		this.intervalDataHistory = intervalDataHistory;
	}

	public String getTypeOfGraph() {
		return typeOfGraph;
	}

	public void setTypeOfGraph(String typeOfGraph) {
		this.typeOfGraph = typeOfGraph;
	}

	public BigDecimal getAggregatedPeakDemandKwh() {
		return aggregatedPeakDemandKwh;
	}

	public void setAggregatedPeakDemandKwh(BigDecimal aggregatedPeakDemandKwh) {
		this.aggregatedPeakDemandKwh = aggregatedPeakDemandKwh;
	}

}
