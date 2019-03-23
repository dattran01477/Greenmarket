package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the evaluations database table.
 * 
 */
@Entity
@Table(name="evaluations")
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int score;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	public Evaluation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}