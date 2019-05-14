package com.ledger.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ledger.HibernateUtil;
import com.ledger.bean.CheckedOutProduct;
import com.ledger.bean.TransactionBean;
import com.ledger.entity.Customer;
import com.ledger.entity.CustomerLoan;
import com.ledger.entity.CustomerLoan.LoanType;
import com.ledger.entity.CustomerTransaction;
import com.ledger.entity.CustomerTransaction.TransactionType;
import com.ledger.entity.Inventory;
import com.ledger.entity.Inventory.InventoryType;
import com.ledger.entity.Inventory.TransactionStatus;

@Repository
public class TransactionDAO {

	@Autowired
	private ProductDao productDAO;

	@Autowired
	private CustomerDAO customerDao;

	public void createTransaction(TransactionBean transactionBean) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		CustomerTransaction customerTransaction = new CustomerTransaction();
		Inventory inventory = new Inventory();
		Set<Inventory> inventories = new HashSet<>();
		Customer customer = new Customer();

		customerTransaction.setAmount(transactionBean.getFinalPayment());
		customerTransaction.setTransactionType(TransactionType.C);

		for (CheckedOutProduct checkedOutProduct : transactionBean.getCheckedOutProductList()) {
			inventory = new Inventory();
			inventory.setProduct(productDAO.getProductById(checkedOutProduct.getProductId()));
			inventory.setInventoryType(InventoryType.D);
			inventory.setTransactionStatus(TransactionStatus.SUCCESS);
			inventory.setQuantity(checkedOutProduct.getQuantity());
			inventories.add(inventory);
		}
		customerTransaction.setInventory(inventories);
		if (transactionBean.getCustomerInfo() != null) {
			if (transactionBean.getCustomerInfo().getCustomerId() != null) {
				customer = customerDao.getCustomersByCriteria("id", transactionBean.getCustomerInfo().getCustomerId());
			} else {
				customer.setName(transactionBean.getCustomerInfo().getCustomerName());
				customer.setAddress(transactionBean.getCustomerInfo().getAddress());
				customer.setContactNumber(transactionBean.getCustomerInfo().getContactNumber());
				customer.setFatherName(transactionBean.getCustomerInfo().getFatherName());
			}
			customerTransaction.setCustomer(customer);
		}

		if (transactionBean.isLoan()) {
			CustomerLoan customerLoan = new CustomerLoan();
			customerLoan.setAmount(transactionBean.getFinalPayment());
			customerLoan.setCustomer(customer);
			customerLoan.setLoanType(LoanType.L);
			customerLoan.setTransaction(customerTransaction);
			customerTransaction.setCustomerLoan(customerLoan);
		}

		session.save(customerTransaction);

		session.getTransaction().commit();
		session.close();
	}

}
