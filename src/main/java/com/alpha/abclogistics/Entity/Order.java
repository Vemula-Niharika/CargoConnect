package com.alpha.abclogistics.Entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"order\"")
public class Order {
	@Id
	private int id;
	private String orderdate;
	private String status="Placed";
	private int cost;
	private String mail;
	@Autowired
	@OneToOne
	private Carrier carrier;
	@Autowired

	@OneToOne(cascade = CascadeType.ALL)

	private Cargo cargo;
	@Autowired
	
	@JoinColumn(name = "Loadingaddress_id")
	@ManyToOne(cascade = CascadeType.ALL)

	private Loading loading;
	@Autowired

	@JoinColumn(name = "UnLoadingaddress_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private Unloading unloading;
	
	@Autowired
	@OneToOne
	private Driver driver;

	
	
	

	public Order(int id, String orderdate, String status, int cost, String mail, Carrier carrier, Cargo cargo,
			Loading loading, Unloading unloading) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.status = status;
		this.cost = cost;
		this.mail = mail;
		this.carrier = carrier;
		this.cargo = cargo;
		this.loading = loading;
		this.unloading = unloading;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Carrier getCarrier() {
		return carrier;
	}
	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public Loading getLoading() {
		return loading;
	}
	public void setLoading(Loading loading) {
		this.loading = loading;
	}
	public Unloading getUnloading() {
		return unloading;
	}
	public void setUnloading(Unloading unloading) {
		this.unloading = unloading;
	}
	public Driver getDriver() {
	    return driver;
	}

	public void setDriver(Driver driver) {
	    this.driver = driver;
	}
	public Order() {
		super();
	}

}
