package com.ge.tms.entity;

import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@Table(name = "cust_payment")
public class CustomerPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerPaymentId;

	// Many To One relationship between CustomerMeter and CustomerAccount
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Acct_ID", nullable = false)
	private CustomerAccount customerAccount;

	@Column(name = "Payment_Method", length = 12)
	private String paymentMethod;

	@Column(name = "card_or_chk_num", length = 50)
	private String cardOrChkNumber;

	@Column(name = "Payment_Submit_Date")
	private Date paymentSubmitDate;

	@Column(name = "routing_or_cvv_num", length = 12)
	private String routingOrCvvnumber;

	@Column(name = "Card_or_chk_holder_name", length = 64)
	private String cardOrChkHoldername;

	@Column(name = "card_or_chk_holder_address", length = 128)
	private String cardOrChkHolderAddress;

	// AS per new Modification
	@Column(name = "cardExpiryDate")
	private Date cardExpiryDate;

	@Column(name = "accountType", length = 20)
	private String accountType;

	@Column(name = "nickName", length = 40)
	private String nickName;

	public Integer getCustomerPaymentId() {
		return customerPaymentId;
	}

	public void setCustomerPaymentId(Integer customerPaymentId) {
		this.customerPaymentId = customerPaymentId;
	}

	@JsonIgnore
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardOrChkNumber() {
		return cardOrChkNumber;
	}

	public void setCardOrChkNumber(String cardOrChkNumber) {
		this.cardOrChkNumber = cardOrChkNumber;
	}

	public Date getPaymentSubmitDate() {
		return paymentSubmitDate;
	}

	public void setPaymentSubmitDate(Date paymentSubmitDate) {
		this.paymentSubmitDate = paymentSubmitDate;
	}

	public String getRoutingOrCvvnumber() {
		return routingOrCvvnumber;
	}

	public void setRoutingOrCvvnumber(String routingOrCvvnumber) {
		this.routingOrCvvnumber = routingOrCvvnumber;
	}

	public String getCardOrChkHoldername() {
		return cardOrChkHoldername;
	}

	public void setCardOrChkHoldername(String cardOrChkHoldername) {
		this.cardOrChkHoldername = cardOrChkHoldername;
	}

	public String getCardOrChkHolderAddress() {
		return cardOrChkHolderAddress;
	}

	public void setCardOrChkHolderAddress(String cardOrChkHolderAddress) {
		this.cardOrChkHolderAddress = cardOrChkHolderAddress;
	}

	public Date getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(Date cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
