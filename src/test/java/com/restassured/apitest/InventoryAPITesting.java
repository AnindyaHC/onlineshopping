package com.restassured.apitest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.example.demo.CustomerServiceTest;
import com.example.demo.InventoryServiceTestScenarios;
import com.example.demo.InventoryServiceTestScenarios.InventoryServiceTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.APIConfiguration;

import io.restassured.RestAssured;

public class InventoryAPITesting extends APIConfiguration {

	static Map<String, List<Object>> scenarioMaps;

	@BeforeTest
	public static void onlyOnce() {
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = InventoryAPITesting.class.getClassLoader();
		File file = new File(classLoader.getResource("InventoryServiceTestScenarios.json").getFile());
		scenarioMaps = new HashMap<>();
		try {
			InventoryServiceTestScenarios inventoryServiceTestScenarios = objectMapper.readValue(file,
					InventoryServiceTestScenarios.class);
			List<Object> dataList;
			for (InventoryServiceTestData scenarios : inventoryServiceTestScenarios.getInventoryServiceDataList()) {
				dataList = new ArrayList<>();
				dataList.add(scenarios.getMockedData());
				dataList.add(scenarios.getExpectedOutput());
				dataList.add(scenarios.getInputParameters());
				scenarioMaps.put(scenarios.getScenario(), dataList);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 0)
	public void testAddInventory() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			try {
				HashMap<String, Object> HMapAct = new HashMap<String, Object>();
				request = RestAssured.given();
				request.header("Content-Type", "application/json");
				response = request.get("/getInventories/");
				System.out.println(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("--------------------------------End of Test ------------------------------------------");
	}
}
