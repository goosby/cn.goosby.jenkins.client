package com.goosby.jenkins.model.job;

public class DefaultParameterValue {
	private String value;

	public void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}

}
