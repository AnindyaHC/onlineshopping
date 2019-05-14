package com.ledger.entity;

import java.sql.Timestamp;

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

/*
 * Java Representation for Inventory Table
 */
@Entity
@Table(name = "INVENTORY", schema = "storemanagement")
public class Inventory {

	// Inventory Id
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVENTORY_SEQUENCE")
	@SequenceGenerator(name = "INVENTORY_SEQUENCE", sequenceName = "INVENTORY_SEQ")
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID", nullable = true)
	private Product product;

	/*
	 * C = Credit D = Debit
	 */
	public enum InventoryType {
		C, D
	}

	/*
	 * Transaction Status Success Ordered --> Inflights orders
	 */
	public enum TransactionStatus {
		SUCCESS, ORDERED
	}

	@Column(name = "TRANSACTION_TYPE")
	@Enumerated(EnumType.STRING)
	private InventoryType inventoryType;

	@Column(name = "TRANSACTION_STATUS")
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;

	@Column(name = "QUANTITY", nullable = false)
	private Double quantity;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMER_TRANSACTION_ID", nullable = true)
	private CustomerTransaction transaction;

	@Column(name = "CREATION_TIME")
	@CreationTimestamp
	private Timestamp creationTime;

	@Column(name = "MODIFICATION_TIME")
	@UpdateTimestamp
	private Timestamp modificationTime;

	public Long getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(final InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(final TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(final Double quantity) {
		this.quantity = quantity;
	}

	public CustomerTransaction getTransaction() {
		return transaction;
	}

	public void setTransaction(final CustomerTransaction transaction) {
		this.transaction = transaction;
	}

}
