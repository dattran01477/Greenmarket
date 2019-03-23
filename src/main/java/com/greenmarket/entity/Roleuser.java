package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roleusers database table.
 * 
 */
@Entity
@Table(name="roleusers")
public class Roleuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String rolename;

	public Roleuser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}