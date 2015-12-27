package com.goosby.jenkins.model.job;

import java.util.List;

public class Property {
	private List<ParameterDefinitions> parameterDefinitions ;

	public void setParameterDefinitions(List<ParameterDefinitions> parameterDefinitions){
		this.parameterDefinitions = parameterDefinitions;
	}
	public List<ParameterDefinitions> getParameterDefinitions(){
		return this.parameterDefinitions;
	}
	
	
	
}
