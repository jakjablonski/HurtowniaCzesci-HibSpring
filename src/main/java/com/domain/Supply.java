package com.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "supply.all", query = "Select s from Supply s"),
	@NamedQuery(name = "supply.id", query = "Select s from Supply s where s.id = :id"),
	@NamedQuery(name = "supply.number", query = "Select s from Supply s where s.number = :number")
})
public class Supply {
	private Long id;
	private String number;
	private String date;
	private String client;

	private List<Unit> units = new ArrayList<Unit>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable = false)
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Unit> getUnits(){
		return units;
	}
	public void setUnits(List<Unit> units){
		this.units = units;
	}
	
	
	
}
