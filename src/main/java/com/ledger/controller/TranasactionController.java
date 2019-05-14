package com.ledger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.bean.TransactionBean;
import com.ledger.service.TransactionService;

@RestController
public class TranasactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/createTransaction/")
	public void createTransaction(@RequestBody TransactionBean transactions) throws JsonProcessingException {
		System.out.println("==== in Create Transaction ====");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Request======>" + mapper.writeValueAsString(transactions));
		transactionService.addTransaction(transactions);
	}

}