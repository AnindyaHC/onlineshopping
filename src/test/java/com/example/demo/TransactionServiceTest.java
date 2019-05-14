package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ledger.bean.TransactionBean;
import com.ledger.service.TransactionService;

public class TransactionServiceTest {

	
	@Test
	public void testAddTransaction() {
	
		TransactionService service = mock(TransactionService.class);
		TransactionBean bean = new TransactionBean();
		
		 ArgumentCaptor valueCapture = ArgumentCaptor.forClass(TransactionBean.class);
		    doNothing().when(service).addTransaction((TransactionBean)valueCapture.capture());
		    
		service.addTransaction(bean);
		
		verify(service, times(1)).addTransaction(bean);
		assertEquals(bean, (TransactionBean)valueCapture.getValue());
	   
	}
}
