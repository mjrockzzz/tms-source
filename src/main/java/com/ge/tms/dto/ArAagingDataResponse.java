package com.ge.tms.dto;

import java.util.List;

/**
 * This class is used to send the ArAging response.
 * @author Nitin K.
 */
public class ArAagingDataResponse {

	private List<ArAgingGraphData> arAgingGraphData;

	private List<ArAgingData> arAgingDataList;

	public List<ArAgingGraphData> getArAgingGraphData() {
		return arAgingGraphData;
	}

	public void setArAgingGraphData(List<ArAgingGraphData> arAgingGraphData) {
		this.arAgingGraphData = arAgingGraphData;
	}

	public List<ArAgingData> getArAgingDataList() {
		return arAgingDataList;
	}

	public void setArAgingDataList(List<ArAgingData> arAgingDataList) {
		this.arAgingDataList = arAgingDataList;
	}

}
