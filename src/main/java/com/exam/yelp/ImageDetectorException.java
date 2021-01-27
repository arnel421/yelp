package com.exam.yelp;

public class ImageDetectorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6085060331933107039L;

	public ImageDetectorException() {
		super();
	}
	
	public ImageDetectorException(String msg) {
		super(msg);
	}
	
	public ImageDetectorException(Throwable t) {
		super(t);
	}
	
	public ImageDetectorException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
