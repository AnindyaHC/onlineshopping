package com.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledger.bean.InventoryBean;
import com.ledger.bean.ProductBean;
import com.ledger.dao.InventoryDAO;
import com.ledger.dao.ProductDao;
import com.ledger.entity.CustomerTransaction.TransactionType;
import com.ledger.entity.Inventory;
import com.ledger.entity.Product;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryDAO inventoryDao;
	
	@Autowired
	private ProductDao productDao;

	// Scenario : As a counter staff, I want to add a new Inventory for new Product.
		public void addProduct(InventoryBean inventoryBean) {
			inventoryBean.setTransactionStatus("Success");
			inventoryBean.setInventoryType('C');

			inventoryDao.addInventoryTransaction(inventoryBean);
		}

		public List<InventoryBean> getInventories() {
			List<Product> products = productDao.getInventoriesByProduct();
			List<InventoryBean> inventoryBeans = new ArrayList<>();
			InventoryBean inventoryBean = new InventoryBean();
			ProductBean productBean  =  new ProductBean();
			for(Product product : products) {
				inventoryBean = new InventoryBean();
				productBean  =  new ProductBean();
				productBean.setProductName(product.getProductName());
				productBean.setProductType(product.getProductType());
				productBean.setManufacturer(product.getProductManufacturer());
				productBean.setUnitPrice(product.getUnitPrice());
				productBean.setMargin(product.getMargin());
				productBean.setStockBatchDate(product.getStockBatchDate());
				productBean.setProductId(product.getId());
				inventoryBean.setNewProduct(productBean);
				
				inventoryBean.setQuantity(0.00);
				if(null != product.getInventories()) {
					for(Inventory inventory : product.getInventories()) {
						
						if(TransactionType.C.name().equals(inventory.getInventoryType().name())) {						
							inventoryBean.setQuantity(inventoryBean.getQuantity() + inventory.getQuantity());
						}else {
							inventoryBean.setQuantity(inventoryBean.getQuantity() - inventory.getQuantity());
						}
						
					}
				}
				inventoryBeans.add(inventoryBean);
			}
			return inventoryBeans;			
		}
}
