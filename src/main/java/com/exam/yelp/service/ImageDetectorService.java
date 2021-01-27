package com.exam.yelp.service;

import java.util.List;

import com.exam.yelp.ImageDetectorException;
import com.exam.yelp.model.ImageDetailsModel;

public interface ImageDetectorService {
	List<ImageDetailsModel> facialRecognition(String base64Image) throws ImageDetectorException;
}
