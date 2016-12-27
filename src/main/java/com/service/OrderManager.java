package com.service;

import java.util.List;


import com.domain.Order;
import com.domain.Unit;

public interface OrderManager {
	void addOrder(Order order);
	List<Order> getAllOrders();
	void deleteOrder(Order order);
	Order findOrderbyId(Long id);
	Order findOrderbyNumber(String number);
	
	Long addUnit(Unit unit);
	List<Unit> getAllUnits();
	void deleteUnit(Unit unit);
	Unit findUnitById(Long id);
	
	List<Unit> getOwnedUnits(Order order);
	void OrderUnit(Long orderId, Long unitId);

}
