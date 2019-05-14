package com.ledger.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CUSTOMER", schema = "storemanagement")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_SEQUENCE")
	@SequenceGenerator(name = "CUSTOMER_SEQUENCE", sequenceName = "CUSTOMER_SEQ")
	private Long id;

	@Column(name = "CUSTOMER_NAME")
	private String name;

	@Column(name = "FATHER_NAME")
	private String fatherName;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CONTACT_NUMBER")
	private Long contactNumber;

	@Column(name = "CREATION_TIME")
	@CreationTimestamp
	private Timestamp creationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<CustomerTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<CustomerTransaction> transactions) {
		this.transactions = transactions;
	}

	public List<CustomerLoan> getLoans() {
		return loans;
	}

	public void setLoans(List<CustomerLoan> loans) {
		this.loans = loans;
	}

	@Column(name = "MODIFICATION_TIME")
	@UpdateTimestamp
	private Timestamp modificationTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private List<CustomerLoan> loans;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customer")
	private List<CustomerTransaction> transactions;

	public Customer() {
	}
}
