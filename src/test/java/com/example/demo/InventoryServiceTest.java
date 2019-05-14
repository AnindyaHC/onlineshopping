package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.InventoryServiceTestScenarios.InventoryServiceTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledger.bean.InventoryBean;
import com.ledger.bean.TransactionBean;
import com.ledger.dao.ProductDao;
import com.ledger.entity.Product;
import com.ledger.service.InventoryService;

public class InventoryServiceTest {

	@Mock
	private ProductDao productDAO;

	@InjectMocks
	InventoryService service = new InventoryService();

	static Map<String, List<Object>> scenarioMaps;

	@BeforeClass
	public static void onlyOnce() {
		ObjectMapper objectMapper = new ObjectMapper();
		ClassLoader classLoader = CustomerServiceTest.class.getClassLoader();
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

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddProduct() {

		InventoryService service = mock(InventoryService.class);
		InventoryBean bean = new InventoryBean();

		ArgumentCaptor valueCapture = ArgumentCaptor.forClass(TransactionBean.class);
		doNothing().when(service).addProduct((InventoryBean) valueCapture.capture());

		service.addProduct(bean);

		verify(service, times(1)).addProduct(bean);
		assertEquals(bean, (InventoryBean) valueCapture.getValue());

	}

	@Test
	public void testGetInventoriesDefaultBehaviour() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(productDAO.getInventoriesByProduct()).thenReturn((List<Product>) scenarioMaps.get(methodName).get(0));
			Assert.assertEquals(methodName,
					((List<InventoryBean>) scenarioMaps.get(methodName).get(1)).get(0).getNewProduct().getProductId(),
					service.getInventories().get(0).getNewProduct().getProductId());
		}
	}
	
	@Test
	public void testGetInventoriesNoProducts() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(productDAO.getInventoriesByProduct()).thenReturn((List<Product>) scenarioMaps.get(methodName).get(0));
			if (scenarioMaps.get(methodName) != null) {
				when(productDAO.getInventoriesByProduct()).thenReturn((List<Product>) scenarioMaps.get(methodName).get(0));
				Assert.assertEquals(methodName, ((List<InventoryBean>) scenarioMaps.get(methodName).get(1)).size(),
						service.getInventories().size());
			}

		}
	}
	
	@Test
	public void testGetInventoriesWithInventories() {
		String methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		System.out.println(methodName);
		if (scenarioMaps.get(methodName) != null) {
			when(productDAO.getInventoriesByProduct()).thenReturn((List<Product>) scenarioMaps.get(methodName).get(0));
			if (scenarioMaps.get(methodName) != null) {
				when(productDAO.getInventoriesByProduct()).thenReturn((List<Product>) scenarioMaps.get(methodName).get(0));
				Assert.assertEquals(methodName, ((List<InventoryBean>) scenarioMaps.get(methodName).get(1)).get(0).getQuantity(),
						service.getInventories().get(0).getQuantity());
			}

		}
	}
}
