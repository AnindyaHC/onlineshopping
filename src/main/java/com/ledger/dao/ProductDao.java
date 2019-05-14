package com.ledger.dao;

import com.ledger.HibernateUtil;

import com.ledger.entity.Inventory;
import com.ledger.entity.Product;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/*
 * Data Access Layer for Interacting with Product and Inventory Table. 
 */
@Repository
public class ProductDao {

	public Product getProductById(Long productId) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Product product = session.get(Product.class, productId);
		session.close();

		return product;
	}

	public List<Product> getInventoriesByProduct() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		query.setMaxResults(20);
		List<Product> products = query.getResultList();
		for (Product product : products) {
			for (Inventory inventory : product.getInventories()) {
				inventory.getQuantity();
			}
		}
		session.close();
		return products;
	}

}
