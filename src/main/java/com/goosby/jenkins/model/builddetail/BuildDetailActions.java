package com.goosby.jenkins.model.builddetail;

import java.util.List;

public class BuildDetailActions {
	
	private List<Parameters> parameters ;

	public void setParameters(List<Parameters> parameters){
		this.parameters = parameters;
	}
	public List<Parameters> getParameters(){
		return this.parameters;
	}
	
	
}
