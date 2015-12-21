package com.goosby.jenkins.httpclient;

public class HttpClientException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1407567846571351359L;
	private final HttpResponse response;

	public HttpClientException(HttpResponse response) {
		this.response = response;
	}

	public HttpResponse getResponse() {
		return response;
	}
}
