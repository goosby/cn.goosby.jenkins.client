package com.goosby.jenkins.model.jobdetail;

import java.util.List;

public class Actions {
	
	private List<ParameterDefinitions> parameterDefinitions ;

	public void setParameterDefinitions(List<ParameterDefinitions> parameterDefinitions){
		this.parameterDefinitions = parameterDefinitions;
	}
	public List<ParameterDefinitions> getParameterDefinitions(){
		return this.parameterDefinitions;
	}
}
