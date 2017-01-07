package com.service;

import java.util.List;

import com.domain.Supply;
import com.domain.Unit;

public interface SupplyManager {
	void addSupply(Supply supply);
	List<Supply> getAllSupplies();
	void deleteSupply(Supply supply);
	Supply findSupplybyId(Long id);
	Supply findSupplybyNumber(String number);
	
	Long addUnit(Unit unit);
	List<Unit> getAllUnits();
	void deleteUnit(Unit unit);
	Unit findUnitById(Long id);
	
	List<Unit> getOwnedUnits(Supply supply);
	void SupplyUnit(Long supplyId, Long unitId);
	boolean editSupply(Supply supply);
	boolean editUnit(Unit unit);

}
