package com.service;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.domain.Order;
import com.domain.Unit;

@Component
@Transactional
public class OrderManagerHibernateImpl implements OrderManager {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
}
	
	@Override
	public void addOrder(Order order) {
		order.setId(null);
		sessionFactory.getCurrentSession().persist(order);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("order.all").list();
	}

	@Override
	public void deleteOrder(Order order) {
		order = (Order) sessionFactory.getCurrentSession().get(Order.class, order.getId());
		for (Unit units : order.getUnits()){
			sessionFactory.getCurrentSession().update(units);
		}
		sessionFactory.getCurrentSession().delete(order);
	}

	@Override
	public Order findOrderbyId(Long id) {
		return (Order) sessionFactory.getCurrentSession().getNamedQuery("order.id").setLong("id",id).uniqueResult();
	}
	
	@Override
	public Order findOrderbyNumber(String number) {
		return (Order) sessionFactory.getCurrentSession().getNamedQuery("order.number").setString("number",number).uniqueResult();
	}

	@Override
	public void addUnit(Unit unit) {
		unit.setId(null);
		sessionFactory.getCurrentSession().persist(unit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getAllUnits() {
		return sessionFactory.getCurrentSession().getNamedQuery("unit.all").list();
	}

	@Override
	public void deleteUnit(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Unit findUnitById(Long id) {
		return (Unit) sessionFactory.getCurrentSession().getNamedQuery("unit.id").setLong("id",id).uniqueResult();
	}

	@Override
	public List<Unit> getOwnedUnits(Unit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void OrderUnit(Long orderId, Long unitId) {
		// TODO Auto-generated method stub
		
	}

	
		
	}
	

