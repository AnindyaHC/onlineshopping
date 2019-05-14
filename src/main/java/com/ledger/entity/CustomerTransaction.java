package com.ledger.entity;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CUSTOMER_TRANSACTION", schema = "storemanagement")
public class CustomerTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CUSTOMER_TRANSACTION_SEQUENCE")
	@SequenceGenerator(name = "CUSTOMER_TRANSACTION_SEQUENCE", sequenceName = "CUSTOMER_TRANSACTION_SEQ")
	private Long id;
	
	//TransactionType C Cash
	//TransactionType L Loan
	//TransactionType P Loan Re payment
	//TransactionType R Refund Transaction
	public static enum TransactionType {
	    C, L, P, R
	}

	@Column(name = "TRANSACTION_TYPE", nullable = true)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	@Column(name = "AMOUNT")
	private Double amount;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", nullable = true)
    private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_LOAN_ID", nullable = true)
    private CustomerLoan customerLoan;
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="TRANSACTION_INVENTORY", joinColumns={@JoinColumn(referencedColumnName="ID")}
                                        , inverseJoinColumns={@JoinColumn(referencedColumnName="ID")}) 
    private Set<Inventory> inventory;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="CUSTOMER_TRANSACTION_ID")
	private CustomerTransaction originalTransaction;

	@OneToMany(mappedBy="originalTransaction")
	private Set<CustomerTransaction> refundTransactions = new HashSet<CustomerTransaction>();
	
	@Column(name = "CREATION_TIME")
	@CreationTimestamp
	private Time creationTime;
	
	@Column(name = "MODIFICATION_TIME")
	@UpdateTimestamp
	private Time modificationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public CustomerTransaction getOriginalTransaction() {
		return originalTransaction;
	}

	public void setOriginalTransaction(CustomerTransaction originalTransaction) {
		this.originalTransaction = originalTransaction;
	}

	public Set<CustomerTransaction> getRefundTransactions() {
		return refundTransactions;
	}

	public void setRefundTransactions(Set<CustomerTransaction> refundTransactions) {
		this.refundTransactions = refundTransactions;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomerLoan getCustomerLoan() {
		return customerLoan;
	}

	public void setCustomerLoan(CustomerLoan customerLoan) {
		this.customerLoan = customerLoan;
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

	public Set<Inventory> getInventory() {
		return inventory;
	}

	public void setInventory(Set<Inventory> inventory) {
		this.inventory = inventory;
	}	
	
}
