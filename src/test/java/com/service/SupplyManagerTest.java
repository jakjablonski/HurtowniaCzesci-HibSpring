package com.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


import com.domain.Supply;
import com.domain.Unit;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class SupplyManagerTest {
	
	@Autowired
	SupplyManager supplyManager;
	private final String START_NUMBER = "START";
	
	private final String SNUMBER_1 = "ABC1234";
	private final String DATE_1 = "19-12-2000";
	private final String CLIENT_1 = "Kowalski";
	
	private final String UNUMBER_1 = "QWER1234";
	private final String NAME_1 = "PART_1";
	private final Double PRICE_1 = 100.0;
	
	@Before
	public void setup(){
		if(supplyManager.findSupplybyNumber(START_NUMBER) == null){
			Unit unit = new Unit();
			unit.setNumber(UNUMBER_1);
			unit.setName(NAME_1);
			unit.setPirce(PRICE_1);
			unit.setInSupply(false);
			
			
			
			Supply supply = new Supply();
			supply.setNumber(START_NUMBER);
			supply.setClient(CLIENT_1);
			supply.setDate(DATE_1);
			supply.getUnits().add(unit);
			unit.setInSupply(true);
			
			supplyManager.addSupply(supply);
		}
	}
	@Test
	public void addSupply(){
		Supply supply = new Supply();
		supply.setNumber(SNUMBER_1);
		supply.setClient(CLIENT_1);
		supply.setDate(DATE_1);
		supplyManager.addSupply(supply);
		Supply recOrder = supplyManager.findSupplybyNumber(SNUMBER_1);
		assertEquals(SNUMBER_1, recOrder.getNumber());
		assertEquals(CLIENT_1,recOrder.getClient());
		assertEquals(DATE_1, recOrder.getDate());
	}

	@Test
	public void editSupply(){
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		supply.setNumber("TEST");
		supply.setClient("TESTClient");
		supply.setDate("19-12-2222");
		Long supplyId = supply.getId();
		supplyManager.editSupply(supply);
		
		Supply supplies = supplyManager.findSupplybyId(supplyId);
		
		assertEquals("TEST", supplies.getNumber());
		assertEquals("TESTClient", supplies.getClient());
		assertEquals("19-12-2222", supplies.getDate());
	}
	
	
	@Test
	public void deleteSupply(){
		int supplyTableSize = supplyManager.getAllSupplies().size();
		int unitTableSize = supplyManager.getAllUnits().size();
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		
		supplyManager.deleteSupply(supply);
		assertEquals(supplyTableSize -1 , supplyManager.getAllSupplies().size());
		assertEquals(unitTableSize -1 , supplyManager.getAllUnits().size());
	}
}