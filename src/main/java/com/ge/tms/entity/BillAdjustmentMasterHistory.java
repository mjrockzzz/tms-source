package com.ge.tms.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "bill_adjustment_master_history")
public class BillAdjustmentMasterHistory {


	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer masterId;
	

	@Column(name = "bill_adjustement_master_id")
	private Integer id;

	@Column(name = "adjustment_name")
	private String adjustmentName;
	
	@Column(name = "adjustment_description")
	private String adjustmentDescription;
	
	@Column(name = "adjustment_type", length = 12)
	private String adjustmentType;
	
	@Column(name = "adjustment_status", length = 12)
	private String adjustmentStatus;
	
	@Column(name = "approved_by")
	private String approvedBy;
		
	@Column(name = "approved_date")
	private Date approvedDate;
	

	@Column(name = "comments", length = 40)
	private String comments;


	
	
	public Integer getMasterId() {
		return masterId;
	}


	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAdjustmentName() {
		return adjustmentName;
	}


	public void setAdjustmentName(String adjustmentName) {
		this.adjustmentName = adjustmentName;
	}


	public String getAdjustmentDescription() {
		return adjustmentDescription;
	}


	public void setAdjustmentDescription(String adjustmentDescription) {
		this.adjustmentDescription = adjustmentDescription;
	}


	public String getAdjustmentType() {
		return adjustmentType;
	}


	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}


	public String getAdjustmentStatus() {
		return adjustmentStatus;
	}


	public void setAdjustmentStatus(String adjustmentStatus) {
		this.adjustmentStatus = adjustmentStatus;
	}


	public String getApprovedBy() {
		return approvedBy;
	}


	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}


	public Date getApprovedDate() {
		return approvedDate;
	}


	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}

}
