package com.greenmarket.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class Good {

	public Good() {
		// TODO Auto-generated constructor stub
	}
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	public String getGoodscol() {
		return goodscol;
	}

	public void setGoodscol(String goodscol) {
		this.goodscol = goodscol;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getFarmid() {
		return farmid;
	}

	public void setFarmid(int farmid) {
		this.farmid = farmid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	private Timestamp createdtime;

	private String goodscol;

	private String images;

	private BigInteger price;

	private String product;

	private int quality;
	
	private int farmid;
	
	private int categoryid;
}
