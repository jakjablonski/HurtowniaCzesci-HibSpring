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
import com.domain.Unit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class OrderManagerTest {
	
	@Autowired
	OrderManager orderManager;
	
	
	private final String ONUMBER_1 = "ABC1234";
	private final String DATE_1 = "19-12-2000";
	private final String CLIENT_1 = "Kowalski";
	
	private final String UNUMBER_1 = "QWER1234";
	private final String NAME_1 = "PART_1";
	private final Double PRICE_1 = 100.0;
	
	@Test
	public void addOrderCheck() {
		Order order = new Order();
		order.setNumber(ONUMBER_1);
		order.setDate(DATE_1);
		order.setClient(CLIENT_1);
		
		orderManager.addOrder(order);
		Order retrievedOrder = orderManager.findOrderbyNumber(ONUMBER_1);
		
		assertEquals(ONUMBER_1,retrievedOrder.getNumber());
		assertEquals(DATE_1, retrievedOrder.getDate());
		assertEquals(CLIENT_1, retrievedOrder.getClient());
	}
	
	@Test
	public void deleteOrderCheck(){
		Unit unit1 = new Unit();
		unit1.setNumber(UNUMBER_1);
		unit1.setName(NAME_1);
		unit1.setPirce(PRICE_1);
		
		orderManager.addUnit(unit1);
		
		Order order1 = new Order();
		order1.setNumber(ONUMBER_1);
		order1.setDate(DATE_1);
		order1.setClient(CLIENT_1);
		
		orderManager.addOrder(order1);
		
		
		int unitSize = orderManager.getAllUnits().size();
		int orderSize = orderManager.getAllOrders().size();
		
		
		
		Order order = orderManager.findOrderbyNumber(ONUMBER_1);
		Long orderID = order.getId();
		orderManager.deleteOrder(order1);
		
		assertEquals(null,orderManager.findOrderbyId(orderID));
		
		
	}
}
