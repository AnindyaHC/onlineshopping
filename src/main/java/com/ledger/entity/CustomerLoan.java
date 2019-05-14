package com.ledger.entity;


import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CUSTOMER_LOAN",schema = "storemanagement")
public class CustomerLoan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CUSTOMER_LOAN_SEQUENCE")
	@SequenceGenerator(name = "CUSTOMER_LOAN_SEQUENCE", sequenceName = "CUSTOMER_LOAN_SEQ")
	private Long id;

	@Column(name = "AMOUNT")
	private Double amount;

	//TransactionType L Loan
	//TransactionType P Loan Re payment
	//TransactionType R Refund Transaction
	public static enum LoanType {
	    L, P
	}
	
	@Column(name = "LOAN_TYPE")
	@Enumerated(EnumType.STRING)
	private LoanType loanType;

	@Column(name = "CREATION_TIME")
	@CreationTimestamp
	private Time creationTime;
	
	@Column(name = "MODIFICATION_TIME")
	@UpdateTimestamp
	private Time modificationTime;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_TRANSACTION_ID", nullable = false)
	private CustomerTransaction transaction;
	
	public CustomerLoan() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

	public LoanType getLoanType() {
		return loanType;
	}

	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}

	public Time getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public Time getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Time modificationTime) {
		this.modificationTime = modificationTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(CustomerTransaction transaction) {
		this.transaction = transaction;
	}
	
	
}
