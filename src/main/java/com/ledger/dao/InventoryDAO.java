package com.ledger.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ledger.HibernateUtil;
import com.ledger.bean.InventoryBean;
import com.ledger.entity.Inventory;
import com.ledger.entity.Product;

@Repository
public class InventoryDAO {
	
	
	@Autowired
	private ProductDao productDAO;
	
	public void addInventoryTransaction(InventoryBean inventoryBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		
		session.beginTransaction();
		
		Inventory inventory = new Inventory();
		Product product = new Product();
		
		inventory.setQuantity(inventoryBean.getQuantity());
		inventory.setTransactionStatus(inventoryBean.getTransactionStatus().equals("SUCCESS") ? Inventory.TransactionStatus.SUCCESS : Inventory.TransactionStatus.ORDERED);
		inventory.setInventoryType(inventoryBean.getInventoryType() == 'C' ? Inventory.InventoryType.C : Inventory.InventoryType.D);

		if(null != inventoryBean.getExistingProductId() || null != inventoryBean.getNewProduct()) {
			if(null != inventoryBean.getNewProduct()) {
				product.setProductManufacturer(inventoryBean.getNewProduct().getManufacturer());
				product.setProductName(inventoryBean.getNewProduct().getProductName());
				product.setProductType(inventoryBean.getNewProduct().getProductType());
				product.setUnitPrice(inventoryBean.getNewProduct().getUnitPrice());
				product.setMargin(inventoryBean.getNewProduct().getMargin());
				product.setStockBatchDate(inventoryBean.getNewProduct().getStockBatchDate());
				inventory.setProduct(product);
			}else {
				inventory.setQuantity(inventoryBean.getNewQuantity());
				inventory.setProduct(productDAO.getProductById(inventoryBean.getExistingProductId()));
			}
		}
		
		
		session.save(inventory);
		
		session.getTransaction().commit();
		session.close();
	}

	
}
