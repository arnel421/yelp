package com.exam.yelp.model;

public class UserModel {

	private long id;
	private String name;
	// base64
	private String avatar;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", avatar=" + avatar + "]";
	}

}
