package com.goosby.jenkins.model.builddetail;

public class Revisions {
	
	private String module;

	private int revision;

	public void setModule(String module){
	this.module = module;
	}
	public String getModule(){
	return this.module;
	}
	public void setRevision(int revision){
	this.revision = revision;
	}
	public int getRevision(){
	return this.revision;
	}

}
