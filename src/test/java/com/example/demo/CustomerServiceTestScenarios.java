package com.example.demo;

import java.util.List;

import com.ledger.bean.CustomerBean;
import com.ledger.entity.Customer;

public class CustomerServiceTestScenarios {

	List<CustomerServiceTestData> customerServiceDataList;

	public List<CustomerServiceTestData> getCustomerServiceDataList() {
		return customerServiceDataList;
	}

	public void setCustomerServiceDataList(List<CustomerServiceTestData> customerServiceDataList) {
		this.customerServiceDataList = customerServiceDataList;
	}
	
	static class CustomerServiceTestData {

		private String scenarioId;
		private String scenario;
		private List<InputParameters> inputParameters;
		private List<Customer> mockedData;
		private List<CustomerBean> expectedOutput;
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
		public List<Customer> getMockedData() {
			return mockedData;
		}
		public void setMockedData(List<Customer> mockedData) {
			this.mockedData = mockedData;
		}
		public List<CustomerBean> getExpectedOutput() {
			return expectedOutput;
		}
		public void setExpectedOutput(List<CustomerBean> expectedOutput) {
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

