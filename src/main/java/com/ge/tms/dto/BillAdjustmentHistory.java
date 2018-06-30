package com.ge.tms.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * This class is used to send the BillAdjustmentHistory response.
 * @author Nitin K.
 */
public class BillAdjustmentHistory {
	private Integer billAdjustmentId;

	private BigDecimal adjustmentAmount;

	private String adjustmentType;

	private String comments;

	private Date adjsutmentCreationDate;

	private String adjustmentStatus;

	private String adjustmentRequestBy;

	public Integer getBillAdjustmentId() {
		return billAdjustmentId;
	}

	public void setBillAdjustmentId(Integer billAdjustmentId) {
		this.billAdjustmentId = billAdjustmentId;
	}

	public BigDecimal getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getAdjsutmentCreationDate() {
		return adjsutmentCreationDate;
	}

	public void setAdjsutmentCreationDate(Date adjsutmentCreationDate) {
		this.adjsutmentCreationDate = adjsutmentCreationDate;
	}

	public String getAdjustmentStatus() {
		return adjustmentStatus;
	}

	public void setAdjustmentStatus(String adjustmentStatus) {
		this.adjustmentStatus = adjustmentStatus;
	}

	public String getAdjustmentRequestBy() {
		return adjustmentRequestBy;
	}

	public void setAdjustmentRequestBy(String adjustmentRequestBy) {
		this.adjustmentRequestBy = adjustmentRequestBy;
	}
	
	
	
	
}
