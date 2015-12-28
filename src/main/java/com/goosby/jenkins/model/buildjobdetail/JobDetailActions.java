package com.goosby.jenkins.model.buildjobdetail;

import java.util.List;

public class JobDetailActions {
	
	private List<ParameterDefinitions> parameterDefinitions ;

	public void setParameterDefinitions(List<ParameterDefinitions> parameterDefinitions){
		this.parameterDefinitions = parameterDefinitions;
	}
	public List<ParameterDefinitions> getParameterDefinitions(){
		return this.parameterDefinitions;
	}
}
