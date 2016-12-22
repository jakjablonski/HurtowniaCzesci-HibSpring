package com.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class OrderManagerTest {
	@Autowired
	OrderManager orderManager;
	
	
	private final String NUMBER_1 = "ABC1234";
	private final String DATE = "19-12-2000";
	private final String CLIENT = "Kowalski";
	
	@Test
	public void addOrderCheck() {
		List<Order> orders = orderManager.getAllOrders();
		
		for (Order order : orders){
			if(order.getNumber().equals(NUMBER_1)){
				orderManager.deleteOrder(order);
			}
		}
		Order order = new Order();
		order.setNumber(NUMBER_1);
		order.setDate(DATE);
		order.setClient(CLIENT);
		
		orderManager.addOrder(order);
		Order retrievedorder = orderManager.findOrderbyNumber(NUMBER_1);
		
		assertEquals(NUMBER_1,retrievedorder.getNumber());
	}
}
