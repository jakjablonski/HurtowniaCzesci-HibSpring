package com.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



import com.domain.Supply;
import com.domain.Unit;

@Component
@Transactional
public class SupplyManagerHibernateImpl implements SupplyManager {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory _sessionFactory){
		this.sessionFactory = _sessionFactory;
	}
	
	@Override
	public void addSupply(Supply supply) {
		supply.setId(null);
		sessionFactory.getCurrentSession().persist(supply);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Supply> getAllSupplies() {
		return sessionFactory.getCurrentSession().getNamedQuery("supply.all").list();
	}

	@Override
	public void deleteSupply(Supply supply) {
		supply = (Supply) sessionFactory.getCurrentSession().get(Supply.class, supply.getId());
		for (Unit units : supply.getUnits()){
			sessionFactory.getCurrentSession().delete(units);
		}
		sessionFactory.getCurrentSession().delete(supply);
	}
//
	@Override
	public Supply findSupplybyId(Long id) {
		return (Supply) sessionFactory.getCurrentSession().get(Supply.class,id);
	}
	
	@Override
	public Supply findSupplybyNumber(String number) {
		List<Supply> supplies = sessionFactory.getCurrentSession().getNamedQuery("supply.number").setString("number",number).list();
		if(supplies.size() == 0){
			return null;
		}else{
			return supplies.get(0);
		}
	}
	
	@Override
	public boolean editSupply(Supply supply) {
		try{
			sessionFactory.getCurrentSession().update(supply);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@Override
	public Long addUnit(Unit unit) {
		unit.setId(null);
		return (Long)sessionFactory.getCurrentSession().save(unit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> getAllUnits() {
		return sessionFactory.getCurrentSession().getNamedQuery("unit.all").list();
	}

	@Override
	public void deleteUnit(Unit unit) {
		Unit deleteunit = (Unit) sessionFactory.getCurrentSession().get(Unit.class, unit.getId());
		
		List<Supply> supplies = getAllSupplies();
		for(Supply s : supplies){
			for(Unit u : s.getUnits()){
				if(u.getId() == deleteunit.getId()){
					s.getUnits().remove(u);
					sessionFactory.getCurrentSession().update(s);
					break;
				}
			}
		}
		sessionFactory.getCurrentSession().delete(deleteunit);
	}

	@Override
	public Unit findUnitById(Long id) {
		return (Unit) sessionFactory.getCurrentSession().get(Unit.class,id);
	}

	@Override
	public List<Unit> getOwnedUnits(Supply supply) {
		supply = (Supply) sessionFactory.getCurrentSession().get(Supply.class, supply.getId());
		List<Unit> units = new ArrayList<Unit>(supply.getUnits());
		return units;
	}

	@Override
	public void SupplyUnit(Long supplyId, Long unitId) {
		Supply supply = (Supply) sessionFactory.getCurrentSession().get(Supply.class, supplyId);
		Unit unit = (Unit) sessionFactory.getCurrentSession().get(Unit.class, unitId);
		unit.setInSupply(true);
		supply.getUnits().add(unit);
	}
	@Override
	public boolean editUnit(Unit unit){
		try{
			sessionFactory.getCurrentSession().update(unit);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	public List<Unit> getUnitsWithoutOrder() {
		return sessionFactory.getCurrentSession().getNamedQuery("unit.notInOrder").list();
	}

	
		
	}
	

