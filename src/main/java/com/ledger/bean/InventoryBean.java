package com.ledger.bean;

public class InventoryBean {

	private ProductBean newProduct;

	private char inventoryType;
	
	private String transactionStatus;
	
	private Double quantity;
	
	private Double newQuantity;
	
	private Long existingProductId;	

	public ProductBean getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(ProductBean newProduct) {
		this.newProduct = newProduct;
	}

	public Long getExistingProductId() {
		return existingProductId;
	}

	public void setExistingProductId(Long existingProductId) {
		this.existingProductId = existingProductId;
	}

	public char getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(char inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getNewQuantity() {
		return newQuantity;
	}

	public void setNewQuantity(Double newQuantity) {
		this.newQuantity = newQuantity;
	}

	
}
