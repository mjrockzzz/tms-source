package com.ge.tms.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Table(name = "cust_bill")
public class CustomerBill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerBillId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Acct_ID", nullable = false)
	private CustomerAccount customerAccount;

	@Column(name = "Bill_Amt", precision = 10, scale = 2)
	private BigDecimal billAmount;

	@Column(name = "Bill_Due")
	private Date billDueDate;

	@Column(name = "rate_plan", length = 32)
	private String ratePlan;

	@Column(name = "Billcycle_startdate")
	private Date billCycleStartDate;

	@Column(name = "billcycle_enddate")
	private Date billCycleEndDate;

	@Column(name = "payment_status", length = 16)
	private String paymentStatus;

	@Column(name = "payment_date")
	private Date paymentDate;

	@Column(name = "bill_delivery_method", length = 16)
	private String billDeliveryMethod;

	@Column(name = "alt_rate_plan", length = 32)
	private String alternateRatePlan;

	@Column(name = "alt_rate_amt", precision = 10, scale = 2)
	private BigDecimal alternateRateAmount;

	// As per new modifications
	@Column(name = "rate_effective")
	private Timestamp rateEffective;

	@Column(name = "rate_termination ")
	private Timestamp rateTermination;

	// As per new Modifications
	@Column(name = "billTemplate", length = 30)
	private String billTemplate;

	@Column(name = "customerClass", length = 30)
	private String customerClass;

	@Column(name = "paidAmount", precision = 10, scale = 2)
	private BigDecimal paidAmount;

	@Column(name = "billGenerationDate")
	private Date billGenerationDate;

	@Column(name = "billReadyDate")
	private Date billReadyDate;

	@Column(name = "billSentDate")
	private Date billSentDate;

	// As per new modifications
	@Column(name = "feeInterest", precision = 10, scale = 2)
	private BigDecimal feeInterest;

	@Column(name = "noOfReminderSent", length = 10)
	private Integer noOfReminderSent;

	@Column(name = "nextReminderDate")
	private Date nextReminderDate;

	@Column(name = "finalReminderDate")
	private Date finalReminderDate;

	@Column(name = "rate_plan_terimantion_timestamp")
	private Date ratePlanTerimantionTimestamp;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerBill")
	private List<BillAdjustment> adjustments = new ArrayList<BillAdjustment>(0);

	public Integer getCustomerBillId() {
		return customerBillId;
	}

	public void setCustomerBillId(Integer customerBillId) {
		this.customerBillId = customerBillId;
	}

	@JsonIgnore
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

	public String getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(String ratePlan) {
		this.ratePlan = ratePlan;
	}

	public Date getBillCycleStartDate() {
		return billCycleStartDate;
	}

	public void setBillCycleStartDate(Date billCycleStartDate) {
		this.billCycleStartDate = billCycleStartDate;
	}

	public Date getBillCycleEndDate() {
		return billCycleEndDate;
	}

	public void setBillCycleEndDate(Date billCycleEndDate) {
		this.billCycleEndDate = billCycleEndDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getBillDeliveryMethod() {
		return billDeliveryMethod;
	}

	public void setBillDeliveryMethod(String billDeliveryMethod) {
		this.billDeliveryMethod = billDeliveryMethod;
	}

	public String getAlternateRatePlan() {
		return alternateRatePlan;
	}

	public void setAlternateRatePlan(String alternateRatePlan) {
		this.alternateRatePlan = alternateRatePlan;
	}

	public BigDecimal getAlternateRateAmount() {
		return alternateRateAmount;
	}

	public void setAlternateRateAmount(BigDecimal alternateRateAmount) {
		this.alternateRateAmount = alternateRateAmount;
	}

	public Timestamp getRateEffective() {
		return rateEffective;
	}

	public void setRateEffective(Timestamp rateEffective) {
		this.rateEffective = rateEffective;
	}

	public Timestamp getRateTermination() {
		return rateTermination;
	}

	public void setRateTermination(Timestamp rateTermination) {
		this.rateTermination = rateTermination;
	}

	public String getBillTemplate() {
		return billTemplate;
	}

	public void setBillTemplate(String billTemplate) {
		this.billTemplate = billTemplate;
	}

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getBillGenerationDate() {
		return billGenerationDate;
	}

	public void setBillGenerationDate(Date billGenerationDate) {
		this.billGenerationDate = billGenerationDate;
	}

	public Date getBillReadyDate() {
		return billReadyDate;
	}

	public void setBillReadyDate(Date billReadyDate) {
		this.billReadyDate = billReadyDate;
	}

	public Date getBillSentDate() {
		return billSentDate;
	}

	public void setBillSentDate(Date billSentDate) {
		this.billSentDate = billSentDate;
	}

	public BigDecimal getFeeInterest() {
		return feeInterest;
	}

	public void setFeeInterest(BigDecimal feeInterest) {
		this.feeInterest = feeInterest;
	}

	public Integer getNoOfReminderSent() {
		return noOfReminderSent;
	}

	public void setNoOfReminderSent(Integer noOfReminderSent) {
		this.noOfReminderSent = noOfReminderSent;
	}

	public Date getNextReminderDate() {
		return nextReminderDate;
	}

	public void setNextReminderDate(Date nextReminderDate) {
		this.nextReminderDate = nextReminderDate;
	}

	public Date getFinalReminderDate() {
		return finalReminderDate;
	}

	public void setFinalReminderDate(Date finalReminderDate) {
		this.finalReminderDate = finalReminderDate;
	}

	public Date getRatePlanTerimantionTimestamp() {
		return ratePlanTerimantionTimestamp;
	}

	public void setRatePlanTerimantionTimestamp(Date ratePlanTerimantionTimestamp) {
		this.ratePlanTerimantionTimestamp = ratePlanTerimantionTimestamp;
	}

	public List<BillAdjustment> getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(List<BillAdjustment> adjustments) {
		this.adjustments = adjustments;
	}



	

	
	
}
