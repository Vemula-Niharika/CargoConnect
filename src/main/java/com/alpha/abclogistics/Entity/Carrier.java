package com.alpha.abclogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Carrier {

	@Id
	private int id;
	private String name;
	
	private long contact;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Carrier(int id, String name, String mail, long contact) {
		super();
		this.id = id;
		this.name = name;
	
		this.contact = contact;
	}
	public Carrier() {
		super();
	}
	
}
