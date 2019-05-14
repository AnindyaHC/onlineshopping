package com.ledger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.bean.InventoryBean;
import com.ledger.service.InventoryService;

@RestController
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;


	@GetMapping("/getInventories/")
    public List<InventoryBean> getInventories() {
        System.out.println("==== in Get Inventory ====");
        
		return inventoryService.getInventories();
        
    }
	
    @PostMapping("/addInventory/")
    public void addProductWithInventory(@RequestBody  InventoryBean inventoryBean) {
    	
        System.out.println("==== in Add Product ====");
       
        inventoryService.addProduct(inventoryBean);
    }
    
   

}