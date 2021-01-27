package com.exam.yelp.model;

import java.util.List;

public class UserReviewModel {

	private UserModel user;
	private String userAvatar;
	private List<ImageDetailsModel> imageDetails;

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public List<ImageDetailsModel> getImageDetails() {
		return imageDetails;
	}

	public void setImageDetails(List<ImageDetailsModel> imageDetails) {
		this.imageDetails = imageDetails;
	}

}
