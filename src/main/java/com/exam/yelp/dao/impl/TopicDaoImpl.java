package com.exam.yelp.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.exam.yelp.dao.TopicDao;
import com.exam.yelp.model.TopicModel;
import com.exam.yelp.model.UserReviewModel;
import com.exam.yelp.util.FileResourceUtil;
import com.exam.yelp.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;

@Repository
public class TopicDaoImpl implements TopicDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(TopicDaoImpl.class);
	
	private static final String RESOURCE_BASE = "static";
	private static final String RESOURCE_IMAGE = RESOURCE_BASE + "/testImage.jpg";
	
	@Override
	public TopicModel getTopic(long id) {	//TODO: connect to live db
		TopicModel topic = new TopicModel();
		topic.setId(id);
		topic.setUserReviews(testUserReview());
		return topic;
	}
	
	private List<UserReviewModel> testUserReview() {
		UserReviewModel userReview = new UserReviewModel();
		userReview.setUserAvatar(getImageBase64());
		return Arrays.asList(userReview);
	}
	
	private String getImageBase64() {
		try {
			File file = new FileResourceUtil().getFileResourceByFileName(RESOURCE_IMAGE);
			byte[] byteImage = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(byteImage);
			return new String(Base64.encodeBase64(byteImage), "UTF-8");
		} catch (URISyntaxException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		TopicDaoImpl a = new TopicDaoImpl();
		TopicModel model = a.getTopic(4444);
		System.out.println(JsonUtil.toJsonString(model));
	}
	
}
