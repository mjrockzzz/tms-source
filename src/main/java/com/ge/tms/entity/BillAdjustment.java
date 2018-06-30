package com.ge.tms.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Audited
@Table(name = "bill_adjustment")
public class BillAdjustment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billAdjustmentId;

	@Column(name = "adjustment_name")
	private String adjustmentName;
	
	@Column(name = "adjustment_description")
	private String adjustmentDescription;
	
	@Column(name = "approved_by")
	private String approvedBy;
	
	
	@Column(name = "approved_date")
	private Date approvedDate;
	
	
	@Column(name = "adjustment_amount", precision = 10, scale = 2)
	private BigDecimal adjustmentAmount;

	@Column(name = "adjustment_type", length = 12)
	private String adjustmentType;

	@Column(name = "comments", length =64)
	private String comments;

	@Column(name = "adjsutment_creation_date")
	private Date adjsutmentCreationDate;

	@Column(name = "adjustment_status", length = 12)
	private String adjustmentStatus;

	@Column(name = "adjustment_request_by", length = 12)
	private String adjustmentRequestBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_bill_id", nullable = false)
	private CustomerBill customerBill;
	
	
	@Column(name = "bill_priod")
	private String billPriod;
	

	public Integer getBillAdjustmentId() {
		return billAdjustmentId;
	}

	public void setBillAdjustmentId(Integer billAdjustmentId) {
		this.billAdjustmentId = billAdjustmentId;
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

	@JsonBackReference
	public CustomerBill getCustomerBill() {
		return customerBill;
	}

	public void setCustomerBill(CustomerBill customerBill) {
		this.customerBill = customerBill;
	}

	public String getBillPriod() {
		return billPriod;
	}

	public void setBillPriod(String billPriod) {
		this.billPriod = billPriod;
	}



	

	
	
}
