package com.exam.yelp.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class FileResourceUtil {

	public File getFileResourceByFileName(String fileName) throws FileNotFoundException, URISyntaxException {
		URL resource = getClass().getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new FileNotFoundException();
		}
		return new File(resource.toURI());
	}
	
}
