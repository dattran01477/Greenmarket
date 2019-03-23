package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	//bi-directional many-to-one association to Good
	@OneToMany(mappedBy="categoryBean")
	private List<Good> goods;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Good> getGoods() {
		return this.goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public Good addGood(Good good) {
		getGoods().add(good);
		good.setCategoryBean(this);

		return good;
	}

	public Good removeGood(Good good) {
		getGoods().remove(good);
		good.setCategoryBean(null);

		return good;
	}

}