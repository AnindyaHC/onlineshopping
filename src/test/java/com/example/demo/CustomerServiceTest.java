package com.example.demo;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.CustomerServiceTestScenarios.CustomerServiceTestData;
import com.example.demo.CustomerServiceTestScenarios.InputParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.bean.CustomerBean;
import com.ledger.dao.CustomerDAO;
import com.ledger.entity.Customer;
import com.ledger.service.CustomerService;

public class CustomerServiceTest {

	@Mock
	private CustomerDAO customerDao;

	@InjectMocks
	CustomerService service = new CustomerService();

	static Map<String, List<Object>> scenarioMaps;

	@BeforeClass
	public static void onlyOnce() {
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = CustomerServiceTest.class.getClassLoader();
		File file = new File(classLoader.getResource("CustomerServiceTestScenarios.json").getFile());
		scenarioMaps = new HashMap<>();
		try {
			CustomerServiceTestScenarios customerServiceTestScenarios = objectMapper.readValue(file,
					CustomerServiceTestScenarios.class);
			List<Object> dataList;
			for (CustomerServiceTestData scenarios : customerServiceTestScenarios.getCustomerServiceDataList()) {
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

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllCustomersDefaultBehaviour() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(customerDao.getCustomers()).thenReturn((List<Customer>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName,
					((List<CustomerBean>) scenarioMaps.get(methodName).get(1)).get(0).getCustomerId(),
					service.getCustomers().get(0).getCustomerId());
		}

	}

	@Test
	public void testGetAllCustomersForNoCustomers() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(customerDao.getCustomers()).thenReturn((List<Customer>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName, ((List<CustomerBean>) scenarioMaps.get(methodName).get(1)).size(),
					service.getCustomers().size());
		}

	}

	@Test
	public void testGetAllCustomersForMaxResults() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(customerDao.getCustomers()).thenReturn((List<Customer>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName, ((List<CustomerBean>) scenarioMaps.get(methodName).get(1)).size(),
					service.getCustomers().size());
		}

	}

	@Test
	public void testGetCustomersByContactNumberForDefaultBeahaviour() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		System.out.println(methodName);
		List<InputParameters> inputParameters = (List<CustomerServiceTestScenarios.InputParameters>) scenarioMaps
				.get(methodName).get(2);

		when(customerDao.getCustomersByCriteria(inputParameters.get(0).getCriteriaName(),
				Long.valueOf(inputParameters.get(0).getCriteriaValue())))
						.thenReturn(((List<Customer>) scenarioMaps.get(methodName).get(0)).get(0));

		Assert.assertEquals(methodName,
				(((List<CustomerBean>) scenarioMaps.get(methodName).get(1))).get(0).getContactNumber(),
				service.getCustomersByContactNumber(Long.valueOf(inputParameters.get(0).getCriteriaValue()))
						.getContactNumber());

	}
	
	@Test
	public void testGetCustomersByContactNumberForNoResults() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		System.out.println(methodName);
		List<InputParameters> inputParameters = (List<CustomerServiceTestScenarios.InputParameters>) scenarioMaps
				.get(methodName).get(2);

		when(customerDao.getCustomersByCriteria(inputParameters.get(0).getCriteriaName(),
				Long.valueOf(inputParameters.get(0).getCriteriaValue())))
						.thenReturn(((List<Customer>) scenarioMaps.get(methodName).get(0)).get(0));

		Assert.assertNull(service.getCustomersByContactNumber(Long.valueOf(inputParameters.get(0).getCriteriaValue())).getCustomerName());

	}
	
	@Test
	public void testGetAllCustomersForLoans() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(customerDao.getCustomers()).thenReturn((List<Customer>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName, ((List<CustomerBean>) scenarioMaps.get(methodName).get(1)).get(0).getLoan(),
					service.getCustomers().get(0).getLoan());
		}

	}
	
	@Test
	public void testGetAllCustomersForTransactionsByDay() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(customerDao.getCustomers()).thenReturn((List<Customer>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName, ((List<CustomerBean>) scenarioMaps.get(methodName).get(1)).get(0).getCustomerTransactionByDay(),
					service.getCustomers().get(0).getCustomerTransactionByDay());
		}

	}
}
