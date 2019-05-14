package com.ledger.entity;

import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "PRODUCT", schema = "storemanagement", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "PRODUCT_NAME", "PRODUCT_TYPE"}) )
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PRODUCT_SEQUENCE")
	@SequenceGenerator(name = "PRODUCT_SEQUENCE", sequenceName = "PRODUCT_SEQ")
	private Long id;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_TYPE")
	private String productType;
	
	@Column(name = "PRODUCT_MANUFACTURER")
	private String productManufacturer;
	
	@Column(name = "UNIT_PRICE")
	private Double unitPrice;
	
	@Column(name = "MARGIN")
	private Double margin;
	
	@Column(name = "STOCK_BATCH")
	private Date stockBatchDate;
	
	@Column(name = "TAX_RATE")
	private Double taxRate;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "product")
	private List<Inventory> inventories;

	@Column(name = "CREATION_TIME")
	@CreationTimestamp
	private Timestamp creationTime;
	
	@Column(name = "MODIFICATION_TIME")
	@UpdateTimestamp
	private Timestamp modificationTime;
	
	public String getProductName() {
		return productName;
	}
	
	

	public List<Inventory> getInventories() {
		return inventories;
	}



	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductManufacturer() {
		return productManufacturer;
	}


	public void setProductManufacturer(String productManufacturer) {
		this.productManufacturer = productManufacturer;
	}


	public Long getId() {
		return id;
	}


	public Timestamp getCreationTime() {
		return creationTime;
	}


	public Timestamp getModificationTime() {
		return modificationTime;
	}

	public Product() {
	}



	public Double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public Double getMargin() {
		return margin;
	}



	public void setMargin(Double margin) {
		this.margin = margin;
	}



	public Date getStockBatchDate() {
		return stockBatchDate;
	}



	public void setStockBatchDate(Date stockBatchDate) {
		this.stockBatchDate = stockBatchDate;
	}



	public Double getTaxRate() {
		return taxRate;
	}



	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	
	
}
