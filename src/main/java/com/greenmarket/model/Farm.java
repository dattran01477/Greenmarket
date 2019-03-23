package com.greenmarket.model;

public class Farm {

	public Farm() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFarmer() {
		return farmer;
	}

	public void setFarmer(String farmer) {
		this.farmer = farmer;
	}

	public byte[] getImages() {
		return images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public int getLocationx() {
		return locationx;
	}

	public void setLocationx(int locationx) {
		this.locationx = locationx;
	}

	public int getLocationy() {
		return locationy;
	}

	public void setLocationy(int locationy) {
		this.locationy = locationy;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getUserId() {
		return userid;
	}

	public void setUser(int userid) {
		this.userid = userid;
	}
	private int id;

	private String address;

	private String farmer;

	private byte[] images;

	private int locationx;

	private int locationy;

	private String phone;
	
	private int userid;
	
	private int farmid;

	public int getFarmid() {
		return farmid;
	}
	public void setFarmid(int farmid) {
		this.farmid = farmid;
	}
}
