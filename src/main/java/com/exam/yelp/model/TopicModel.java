package com.exam.yelp.model;

import java.util.List;

public class TopicModel {

	private long id;
	private String title;
	private String description;
	private List<UserReviewModel> userReviews;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserReviewModel> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(List<UserReviewModel> userReviews) {
		this.userReviews = userReviews;
	}

}
