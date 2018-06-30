package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is used to store the RevenueGraphBilled response data.
 * @author Nitin K.
 */
public class RevenueGraphBilled {

	private BigDecimal totalBilledAmount;

	private Date billDate;

	public BigDecimal getTotalBilledAmount() {
		return totalBilledAmount;
	}

	public void setTotalBilledAmount(BigDecimal totalBilledAmount) {
		this.totalBilledAmount = totalBilledAmount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

}
