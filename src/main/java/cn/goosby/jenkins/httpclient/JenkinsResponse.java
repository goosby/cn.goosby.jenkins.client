package cn.goosby.jenkins.httpclient;

import java.io.Serializable;

public class JenkinsResponse implements Serializable{
	
	private int responseCode;
	
	private String responseBody;

	public JenkinsResponse() {

	}

	public JenkinsResponse(int responseCode) {
		this.responseCode = responseCode;
	}

	public JenkinsResponse(String responseBody) {
		this.responseBody = responseBody;
	}

	public JenkinsResponse(int responseCode, String responseBody){
		this.responseCode = responseCode;
		this.responseBody = responseBody;
	}
	
	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
}
