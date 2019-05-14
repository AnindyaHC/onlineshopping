package com.ledger.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.ledger.HibernateUtil;
import com.ledger.entity.Customer;
import com.ledger.entity.CustomerLoan;
import com.ledger.entity.CustomerTransaction;

@Repository
public class CustomerDAO {

	

	public List<Customer> getCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Customer";
		Query query = session.createQuery(hql);
		query.setMaxResults(50);
		List<Customer> customers = query.getResultList();
		for(Customer customer : customers) {
			for(CustomerTransaction transaction : customer.getTransactions()) {
				transaction.getTransactionType();
			}
			for(CustomerLoan loan : customer.getLoans()) {
				loan.getLoanType();
			}
		}
		session.close();
		return customers;
	}

	public Customer getCustomersByCriteria(String criteria, Long criteriaValue) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Customer> q = cb.createQuery(Customer.class);
		Root<Customer> c = q.from(Customer.class);
		q.select(c).where(cb.equal(c.get(criteria), criteriaValue));

		  Query query = session.createQuery(q);
		 Customer customer = (Customer) query.getSingleResult();
		session.close();
		return customer;
	}

}
