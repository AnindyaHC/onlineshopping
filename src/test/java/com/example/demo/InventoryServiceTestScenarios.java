package com.example.demo;

import java.util.List;

import com.ledger.bean.InventoryBean;
import com.ledger.entity.Product;

public class InventoryServiceTestScenarios {

	List<InventoryServiceTestData> inventoryServiceDataList;	
	
	public List<InventoryServiceTestData> getInventoryServiceDataList() {
		return inventoryServiceDataList;
	}

	public void setInventoryServiceDataList(List<InventoryServiceTestData> inventoryServiceDataList) {
		this.inventoryServiceDataList = inventoryServiceDataList;
	}

	public static class InventoryServiceTestData {

		private String scenarioId;
		private String scenario;
		private List<InputParameters> inputParameters;
		private List<Product> mockedData;
		private List<InventoryBean> expectedOutput;
		public String getScenarioId() {
			return scenarioId;
		}
		public void setScenarioId(String scenarioId) {
			this.scenarioId = scenarioId;
		}
		public String getScenario() {
			return scenario;
		}
		public void setScenario(String scenario) {
			this.scenario = scenario;
		}
		
		public List<Product> getMockedData() {
			return mockedData;
		}
		public void setMockedData(List<Product> mockedData) {
			this.mockedData = mockedData;
		}
		public List<InventoryBean> getExpectedOutput() {
			return expectedOutput;
		}
		public void setExpectedOutput(List<InventoryBean> expectedOutput) {
			this.expectedOutput = expectedOutput;
		}
		public List<InputParameters> getInputParameters() {
			return inputParameters;
		}
		public void setInputParameters(List<InputParameters> inputParameters) {
			this.inputParameters = inputParameters;
		}
		
		
		
	}
	
	static class InputParameters {
		
		String criteriaName;
		String criteriaValue;
		public String getCriteriaName() {
			return criteriaName;
		}
		public void setCriteriaName(String criteriaName) {
			this.criteriaName = criteriaName;
		}
		public String getCriteriaValue() {
			return criteriaValue;
		}
		public void setCriteriaValue(String criteriaValue) {
			this.criteriaValue = criteriaValue;
		}
		
		
	}

	
}

