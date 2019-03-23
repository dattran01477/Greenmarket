package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the commentposts database table.
 * 
 */
@Entity
@Table(name="commentposts")
public class Commentpost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String content;

	private String createdby;

	private Timestamp createdtime;

	//bi-directional many-to-one association to Post
	@ManyToOne
	@JoinColumn(name="post")
	private Post postBean;

	public Commentpost() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	public Post getPostBean() {
		return this.postBean;
	}

	public void setPostBean(Post postBean) {
		this.postBean = postBean;
	}

}