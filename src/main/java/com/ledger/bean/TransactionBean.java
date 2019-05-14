package com.ledger.bean;

import java.util.List;

public class TransactionBean {

	private List<CheckedOutProduct> checkedOutProductList;
	
	private double finalPayment;
	
	private CustomerBean customerInfo;
	
	private boolean loan;

	public List<CheckedOutProduct> getCheckedOutProductList() {
		return checkedOutProductList;
	}

	public void setCheckedOutProductList(List<CheckedOutProduct> checkedOutProductList) {
		this.checkedOutProductList = checkedOutProductList;
	}

	public double getFinalPayment() {
		return finalPayment;
	}

	public void setFinalPayment(double finalPayment) {
		this.finalPayment = finalPayment;
	}

	public CustomerBean getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerBean customerInfo) {
		this.customerInfo = customerInfo;
	}

	public boolean isLoan() {
		return loan;
	}

	public void setLoan(boolean loan) {
		this.loan = loan;
	}

	
	
	
}
