package com.alpha.abclogistics.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cargo {
	@Id
	private int id;
	private String name;
	private String description;
	private int weight;
	private int count;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Cargo(int id, String name, String description, int weight, int count) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.count = count;
	}
	public Cargo() {
		super();
	}
	
}
