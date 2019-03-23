package com.greenmarket.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the posts database table.
 * 
 */
@Entity
@Table(name="posts")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String content;

	private Timestamp createdtime;

	private String title;

	//bi-directional many-to-one association to Commentpost
	@OneToMany(mappedBy="postBean")
	private List<Commentpost> commentposts;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="createdby")
	private User user;

	public Post() {
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

	public Timestamp getCreatedtime() {
		return this.createdtime;
	}

	public void setCreatedtime(Timestamp createdtime) {
		this.createdtime = createdtime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Commentpost> getCommentposts() {
		return this.commentposts;
	}

	public void setCommentposts(List<Commentpost> commentposts) {
		this.commentposts = commentposts;
	}

	public Commentpost addCommentpost(Commentpost commentpost) {
		getCommentposts().add(commentpost);
		commentpost.setPostBean(this);

		return commentpost;
	}

	public Commentpost removeCommentpost(Commentpost commentpost) {
		getCommentposts().remove(commentpost);
		commentpost.setPostBean(null);

		return commentpost;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}