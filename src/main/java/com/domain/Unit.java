package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "unit.all", query = "Select u from Unit u"),
		@NamedQuery(name = "unit.id", query = "Select u from Unit u where u.id = :id"),
		@NamedQuery(name = "unit.notInSupply", query = "Select u from Unit u where u.inSupply = false")
})
public class Unit {
	
	private Long id;
	private String number;
	private String name;
	private double pirce;
	private boolean inSupply = false;
	
	public boolean isInSupply(){
		return inSupply;
	}
	public void setInSupply(boolean inSupply){
		this.inSupply = inSupply;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPirce() {
		return pirce;
	}
	public void setPirce(double pirce) {
		this.pirce = pirce;
	}
	
	
}
