package com.ledger.bean;

public class CustomerBean {

	private Long customerId;
	private String customerName;
	private String fatherName;
	private String address;
	private Long contactNumber;
	private Double loan;
	private Double customerTransactionByDay;
	private Double customerTransactionByMonth;
	private Double customerTransactionByYear;
	private String customerMostBought;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	
	
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Double getLoan() {
		return loan;
	}
	public void setLoan(Double loan) {
		this.loan = loan;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getCustomerTransactionByDay() {
		return customerTransactionByDay;
	}
	public void setCustomerTransactionByDay(Double customerTransactionByDay) {
		this.customerTransactionByDay = customerTransactionByDay;
	}
	public Double getCustomerTransactionByMonth() {
		return customerTransactionByMonth;
	}
	public void setCustomerTransactionByMonth(Double customerTransactionByMonth) {
		this.customerTransactionByMonth = customerTransactionByMonth;
	}
	public Double getCustomerTransactionByYear() {
		return customerTransactionByYear;
	}
	public void setCustomerTransactionByYear(Double customerTransactionByYear) {
		this.customerTransactionByYear = customerTransactionByYear;
	}
	public String getCustomerMostBought() {
		return customerMostBought;
	}
	public void setCustomerMostBought(String customerMostBought) {
		this.customerMostBought = customerMostBought;
	}
	
}
