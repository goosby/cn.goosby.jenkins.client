package com.goosby.jenkins.model;

public class HealthReport {
	private String description;

	private String iconUrl;

	private int score;

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setIconUrl(String iconUrl){
		this.iconUrl = iconUrl;
	}
	public String getIconUrl(){
		return this.iconUrl;
	}
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return this.score;
	}
}
