package com.exam.yelp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.exam.yelp.ImageDetectorException;
import com.exam.yelp.model.TopicModel;
import com.exam.yelp.service.TopicService;
import com.exam.yelp.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@GetMapping("yelp/{topicId}")
	public ResponseEntity<String> getTopic(@PathVariable long topicId) {
		TopicModel topic = null;
		try {
			topic = topicService.getTopicById(topicId);
			return new ResponseEntity<>(JsonUtil.toJsonString(topic), HttpStatus.OK);
		} catch (ImageDetectorException | JsonProcessingException e) {
			// TODO: ControllerAdvice
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
