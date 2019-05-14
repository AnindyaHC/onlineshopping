package com.ledger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledger.bean.CustomerBean;
import com.ledger.dao.CustomerDAO;
import com.ledger.entity.Customer;
import com.ledger.entity.CustomerLoan;
import com.ledger.entity.CustomerTransaction;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerDAO customerDao;
	
	public List<CustomerBean> getCustomers() {
			List<Customer> customers = customerDao.getCustomers();
			CustomerBean customerBean = new CustomerBean();
			List<CustomerBean> customerList = new ArrayList<>();
			Double transactionByDay;
			Double loanAmount;
			for(Customer customer : customers) {
				transactionByDay = 0.0;
				loanAmount = 0.0;
				customerBean = new CustomerBean();
				customerBean.setCustomerId(customer.getId());
				customerBean.setCustomerName(customer.getName());
				customerBean.setFatherName(customer.getFatherName());
				customerBean.setAddress(customer.getAddress());
				customerBean.setContactNumber(customer.getContactNumber());
				if(customer.getTransactions() != null) {
					for(CustomerTransaction transaction : customer.getTransactions()) {
						transactionByDay = transactionByDay + transaction.getAmount();
					}
				}
				if(customer.getLoans() != null) {
					for(CustomerLoan loan : customer.getLoans()) {
						loanAmount = loanAmount + loan.getAmount();
					}
				}
				customerBean.setLoan(loanAmount);
				customerBean.setCustomerTransactionByDay(transactionByDay);
				customerList.add(customerBean);
			}
			return customerList;			
		}

			
	public CustomerBean getCustomersByContactNumber(Long contactNumber) {
		Customer customer = customerDao.getCustomersByCriteria("contactNumber", contactNumber);
		CustomerBean customerBean = new CustomerBean();
		
			customerBean.setCustomerId(customer.getId());
			customerBean.setCustomerName(customer.getName());
			customerBean.setFatherName(customer.getFatherName());
			customerBean.setAddress(customer.getAddress());
			customerBean.setContactNumber(customer.getContactNumber());
			return customerBean;
			
		}
		

}
