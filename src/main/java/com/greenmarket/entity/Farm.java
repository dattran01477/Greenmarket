package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the farms database table.
 * 
 */
@Entity
@Table(name="farms")
public class Farm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String address;

	private String farmer;

	@Lob
	private byte[] images;

	private int locationx;

	private int locationy;

	private String phone;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="createdby")
	private User user;

	//bi-directional many-to-one association to Location
	@ManyToOne
	@JoinColumn(name="province")
	private Location location;

	//bi-directional many-to-one association to Good
	@OneToMany(mappedBy="farmBean")
	private List<Good> goods;

	public Farm() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFarmer() {
		return this.farmer;
	}

	public void setFarmer(String farmer) {
		this.farmer = farmer;
	}

	public byte[] getImages() {
		return this.images;
	}

	public void setImages(byte[] images) {
		this.images = images;
	}

	public int getLocationx() {
		return this.locationx;
	}

	public void setLocationx(int locationx) {
		this.locationx = locationx;
	}

	public int getLocationy() {
		return this.locationy;
	}

	public void setLocationy(int locationy) {
		this.locationy = locationy;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Good> getGoods() {
		return this.goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public Good addGood(Good good) {
		getGoods().add(good);
		good.setFarmBean(this);

		return good;
	}

	public Good removeGood(Good good) {
		getGoods().remove(good);
		good.setFarmBean(null);

		return good;
	}

}