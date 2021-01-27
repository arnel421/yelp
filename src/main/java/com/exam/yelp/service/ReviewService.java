package com.exam.yelp.service;

import java.util.List;

import com.exam.yelp.model.UserReviewModel;

public interface ReviewService {

	List<UserReviewModel> getReviews(long topicId);
	Object getReviewByUserId(long userId);
	
}
