package com.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
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
	
	private final String UNUMBER_2 = "asdf1234";
	private final String NAME_2 = "PART_2";
	private final Double PRICE_2 = 1200.0;
	
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
	
	@Test
	public void getAllSupplies()
	{
		Supply supply = new Supply();
		supply.setNumber(SNUMBER_1);
		supply.setClient(CLIENT_1);
		supply.setDate(DATE_1);
		supplyManager.addSupply(supply);
		
		List<Supply> supplies = supplyManager.getAllSupplies();
		assertEquals(2,supplies.size());
		
		assertEquals(START_NUMBER, supplies.get(0).getNumber());
		assertEquals(SNUMBER_1, supplies.get(1).getNumber());
			
	}
	
	@Test
	public void supplyUnits(){
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		List<Unit> ownedUnits = supplyManager.getOwnedUnits(supply);
		
		assertEquals(UNUMBER_1, ownedUnits.get(0).getNumber());
		assertEquals(NAME_1, ownedUnits.get(0).getName());
	}
	
	@Test
	public void getSupplyById(){
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		
		Supply supplyid = supplyManager.findSupplybyId(supply.getId());
		
		assertEquals(supply.getId(), supplyid.getId());
	}
	
	@Test
	public void addUnit(){
		Unit unit = new Unit();
		unit.setNumber(UNUMBER_2);
		unit.setName(NAME_2);
		unit.setPirce(PRICE_2);
		unit.setInSupply(false);
		
		Long unitId = supplyManager.addUnit(unit);
		Unit retUnit = supplyManager.findUnitById(unitId);
		
		assertEquals(UNUMBER_2, retUnit.getNumber());
		assertEquals(NAME_2, retUnit.getName());
		
	}
	
	@Test
	public void editUnit(){
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		Unit unit = supply.getUnits().get(0);
		unit.setName("TEST2");
		unit.setNumber("TESTT2");
		unit.setPirce(12.1);
		Long supplyId = supply.getId();
		supplyManager.editUnit(unit);
		Supply supplyCheck = supplyManager.findSupplybyId(supplyId);
		assertEquals("TESTT2", supplyCheck.getUnits().get(0).getNumber());
		assertEquals("TEST2", supplyCheck.getUnits().get(0).getName());
	}
	
	@Test
	public void deleteUnit(){
		int supplyTableSize = supplyManager.getAllSupplies().size();
		int unitTableSize = supplyManager.getAllUnits().size();
		
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		int units = supply.getUnits().size();
		Unit unit = supply.getUnits().get(0);
		
		supplyManager.deleteUnit(unit);
		
		supply = supplyManager.findSupplybyNumber(START_NUMBER);
		
		assertEquals(supplyTableSize, supplyManager.getAllSupplies().size());
		assertEquals(unitTableSize - 1, supplyManager.getAllUnits().size());
		assertEquals(units - 1, supply.getUnits().size());
	}
	@Test
	public void getAllUnits(){
		Unit unit = new Unit();
		unit.setNumber(UNUMBER_2);
		unit.setName(NAME_2);
		unit.setPirce(PRICE_2);
		unit.setInSupply(false);
		
		int unitTableSize = supplyManager.getAllUnits().size();
		assertEquals(unitTableSize, supplyManager.getAllUnits().size());
	}
	@Test
	public void getUnitbyId(){
		Supply supply = supplyManager.findSupplybyNumber(START_NUMBER);
		Unit unit = supply.getUnits().get(0);
		Unit unitId = supplyManager.findUnitById(supply.getUnits().get(0).getId());
		
		assertEquals(unit.getId(), unitId.getId());
	}
	@Test
	public void  SearchbyPrice(){
		Unit unit1 = new Unit();
		unit1.setNumber(UNUMBER_2);
		unit1.setName(NAME_2);
		unit1.setPirce(PRICE_2);
		unit1.setInSupply(false);
		
		Long unitId = supplyManager.addUnit(unit1);
		
		Unit unit2 = new Unit();
		unit2.setNumber("aqws123");
		unit2.setName("testtest");
		unit2.setPirce(112.0);
		unit2.setInSupply(false);
		
		Long unit2Id = supplyManager.addUnit(unit2);
		
		Unit unit3 = new Unit();
		unit3.setNumber("asdfgh123");
		unit3.setName("testtest3");
		unit3.setPirce(2.0);
		unit3.setInSupply(false);
		
		Long unit3Id = supplyManager.addUnit(unit3);
		
		List<Unit> units = supplyManager.getAllUnits();
		assertEquals(4, units.size());
		
		List<Unit> searchUnits = new ArrayList<Unit>();
		for(Unit unit : units)
			if(unit.getPirce()>11.0 && unit.getPirce()<115.0)
				searchUnits.add(unit);
		
		assertEquals(2,searchUnits.size());
		
		assertEquals(UNUMBER_1, searchUnits.get(0).getNumber());
		assertEquals("aqws123", searchUnits.get(1).getNumber());
		

		
	}
}