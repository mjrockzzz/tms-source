package com.ge.tms.dto;

import com.ge.tms.entity.CustomerAccount;
import com.ge.tms.entity.CustomerBill;
import com.ge.tms.entity.CustomerPayment;

/**
 * This class is used to send the FinancialDetails Response data.
 * @author Nitin K.
 */
public class FinancialDetails {

	private CustomerAccount customerAccount;
	
	private CustomerBill customerBill;
	
	private CustomerPayment customerPayment;

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public CustomerBill getCustomerBill() {
		return customerBill;
	}

	public void setCustomerBill(CustomerBill customerBill) {
		this.customerBill = customerBill;
	}

	public CustomerPayment getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(CustomerPayment customerPayment) {
		this.customerPayment = customerPayment;
	}

}
