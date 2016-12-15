package com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "zamowienie.wszystkie", query = "Select z from zamowienie"),
		@NamedQuery(name = "zamowienie.id", query = "Select z from zamowienie where c.id = :id"),
		@NamedQuery(name = "zamowienie.numer", query = "Select z from zamowienie where c.numer = :numer")
})
public class Zamowienie {
	private int id;
	private String numer;
	private String data;
	private String kontrahent;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumer() {
		return numer;
	}
	public void setNumer(String numer) {
		this.numer = numer;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getKontrahent() {
		return kontrahent;
	}
	public void setKontrahent(String kontrahent) {
		this.kontrahent = kontrahent;
	}

	
}
