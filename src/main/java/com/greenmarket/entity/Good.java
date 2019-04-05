package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the goods database table.
 * 
 */
@Entity
@Table(name="goods")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int view;
	
	private Timestamp createdtime;

	private String images;

	private BigInteger price;

	private String product;

	private int quality;

	//bi-directional many-to-one association to Commentgood
	@OneToMany(mappedBy="good")
	private List<Commentgood> commentgoods;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category")
	private Category categoryBean;

	//bi-directional many-to-one association to Farm
	@ManyToOne
	@JoinColumn(name="farm")
	private Farm farmBean;

	public Good() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}


	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public BigInteger getPrice() {
		return this.price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQuality() {
		return this.quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public List<Commentgood> getCommentgoods() {
		return this.commentgoods;
	}

	public void setCommentgoods(List<Commentgood> commentgoods) {
		this.commentgoods = commentgoods;
	}

	public Commentgood addCommentgood(Commentgood commentgood) {
		getCommentgoods().add(commentgood);
		commentgood.setGood(this);

		return commentgood;
	}

	public Commentgood removeCommentgood(Commentgood commentgood) {
		getCommentgoods().remove(commentgood);
		commentgood.setGood(null);

		return commentgood;
	}

	public Category getCategoryBean() {
		return this.categoryBean;
	}

	public void setCategoryBean(Category categoryBean) {
		this.categoryBean = categoryBean;
	}

	public Farm getFarmBean() {
		return this.farmBean;
	}

	public void setFarmBean(Farm farmBean) {
		this.farmBean = farmBean;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

}