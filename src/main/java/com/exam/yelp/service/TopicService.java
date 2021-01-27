package com.exam.yelp.service;

import java.util.List;

import com.exam.yelp.ImageDetectorException;
import com.exam.yelp.model.TopicModel;

public interface TopicService {
	List<TopicModel> getAllTopic();
	TopicModel getTopicById(long topicId) throws ImageDetectorException;
}
