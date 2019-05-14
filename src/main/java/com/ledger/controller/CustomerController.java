package com.ledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ledger.bean.CustomerBean;
import com.ledger.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;


	@GetMapping("/getCustomers/")
    public List<CustomerBean> getCustomers() {
		return customerService.getCustomers();
        
    }
	
	@RequestMapping(value = "/getCustomersByContactNumber/{contactNumber}", method=RequestMethod.GET)
	public CustomerBean getUser(final @PathVariable Long contactNumber) {
		return customerService.getCustomersByContactNumber(contactNumber);
	}
	
    
    
   

}