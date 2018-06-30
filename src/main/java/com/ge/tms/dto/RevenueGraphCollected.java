package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is used to store the RevenueGraphCollected response data.
 * @author Nitin K.
 */
public class RevenueGraphCollected {

	private BigDecimal totalCollectedAmount;

	private Date billDate;

	public BigDecimal getTotalCollectedAmount() {
		return totalCollectedAmount;
	}

	public void setTotalCollectedAmount(BigDecimal totalCollectedAmount) {
		this.totalCollectedAmount = totalCollectedAmount;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

}
