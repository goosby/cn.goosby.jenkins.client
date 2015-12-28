package com.goosby.jenkins.model.buildjobdetail;

public class LastSuccessfulBuild {
	private int number;

	private String url;

	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return this.number;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
}
