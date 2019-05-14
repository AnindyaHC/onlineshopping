package com.ledger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledger.bean.TransactionBean;
import com.ledger.dao.TransactionDAO;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDAO transactionDAO;
	
	public void addTransaction(TransactionBean transactionBean) {
		
		//validateCheckoutProductwithAvailableStock
		//validateFinalPaymentAgainstMarginPrice
		//AddTax
		//checkContactNumberExists
		//checkCustomerExistForLoan
		//supportPartPayment
		//RePayLoan
		//ReturnProduct
		transactionDAO.createTransaction(transactionBean);
	}
}
