package com.ledger.bean;

import java.util.Date;

public class ProductBean {
	
	private Long productId;

	private String productName;

	private String productType;

	private String manufacturer;
	
	private Double unitPrice;
	
	private Double margin;
	
	private Date stockBatchDate;
	
	public String getProductName() {
		return productName;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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


	
	
}
