package com.exam.yelp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.yelp.ImageDetectorException;
import com.exam.yelp.dao.TopicDao;
import com.exam.yelp.model.TopicModel;
import com.exam.yelp.model.UserReviewModel;
import com.exam.yelp.service.ImageDetectorService;
import com.exam.yelp.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private ImageDetectorService imageDetectorService;
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public List<TopicModel> getAllTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TopicModel getTopicById(long topicId) throws ImageDetectorException {
		TopicModel topic = topicDao.getTopic(topicId);
		if (topic != null && topic.getUserReviews() != null) {
			for (UserReviewModel userReview: topic.getUserReviews()) {
				userReview.setImageDetails(imageDetectorService.facialRecognition(userReview.getUserAvatar()));
			}
		}
		return topic;
	}

}
