package com.alpha.abclogistics.Dto;


public class OrderDto {
	private int id;
	private String orderdate;
	private int cargoid;
	private String cargoname;
	private String cargodescription;
	private int cargoweight;
	private int cargocount;
	private int loadingaddid;
	private int unloadingaddid;
	private String mail;
	 
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
	public int getCargoid() {
		return cargoid;
	}
	public void setCargoid(int cargoid) {
		this.cargoid = cargoid;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getCargodescription() {
		return cargodescription;
	}
	public void setCargodescription(String cargodescription) {
		this.cargodescription = cargodescription;
	}
	public int getCargoweight() {
		return cargoweight;
	}
	public void setCargoweight(int cargoweight) {
		this.cargoweight = cargoweight;
	}
	public int getCargocount() {
		return cargocount;
	}
	public void setCargocount(int cargocount) {
		this.cargocount = cargocount;
	}
	public int getLoadingaddid() {
		return loadingaddid;
	}
	public void setLoadingaddid(int loadingaddid) {
		this.loadingaddid = loadingaddid;
	}
	public int getUnloadingaddid() {
		return unloadingaddid;
	}
	public void setUnloadingaddid(int unloadingaddid) {
		this.unloadingaddid = unloadingaddid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public OrderDto(int id, String orderdate, int cargoid, String cargoname, String cargodescription, int cargoweight,
			int cargocount, int loadingaddid, int unloadingaddid, String mail) {
		super();
		this.id = id;
		this.orderdate = orderdate;
		this.cargoid = cargoid;
		this.cargoname = cargoname;
		this.cargodescription = cargodescription;
		this.cargoweight = cargoweight;
		this.cargocount = cargocount;
		this.loadingaddid = loadingaddid;
		this.unloadingaddid = unloadingaddid;
		this.mail = mail;
	}
	public OrderDto() {
		super();
	}
	
}
