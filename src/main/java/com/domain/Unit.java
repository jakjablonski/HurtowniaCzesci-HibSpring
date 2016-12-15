package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "unit.all", query = "Select u from unit"),
		@NamedQuery(name = "unit.id", query = "Select u from unit where u.id = :id"),
		@NamedQuery(name = "unit.number", query = "Select u from unit where u.number = :number")
})
public class Unit {
	
	private long id;
	private String number;
	private String name;
	private double pirce;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
