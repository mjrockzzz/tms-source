package com.ge.tms.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill_configuration")
public class BillConfiguration {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billConfigurationId;

	@Column(name = "bill_template_name")
	private String billTemplateName;
	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "version", precision = 5, scale = 2)
	private BigDecimal version;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "rate_plan")
	private String ratePlan;
	
	@Column(name = "effective_date")
	private Date effectiveDate;
	
	@Column(name = "termination_date")
	private Date terminationDate;	
	
	@Column(name = "bill_equation")
	private String billChargesEquation;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	
	@Column(name = "approved_by")
	private String approvedBy;
	
	@Column(name = "approved_date")
	private Date approvedDate;
	
	@Column(name = "notes")
	private String notes;

	public Integer getBillConfigurationId() {
		return billConfigurationId;
	}

	public void setBillConfigurationId(Integer billConfigurationId) {
		this.billConfigurationId = billConfigurationId;
	}

	public String getBillTemplateName() {
		return billTemplateName;
	}

	public void setBillTemplateName(String billTemplateName) {
		this.billTemplateName = billTemplateName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(String ratePlan) {
		this.ratePlan = ratePlan;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	
	public String getBillChargesEquation() {
		return billChargesEquation;
	}

	public void setBillChargesEquation(String billChargesEquation) {
		this.billChargesEquation = billChargesEquation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
