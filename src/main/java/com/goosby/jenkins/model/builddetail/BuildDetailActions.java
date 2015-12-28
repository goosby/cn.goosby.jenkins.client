package com.goosby.jenkins.model.builddetail;

import java.util.List;

public class BuildDetailActions {
	
	private List<BuildDetailParameters> parameters ;

	public void setParameters(List<BuildDetailParameters> parameters){
		this.parameters = parameters;
	}
	public List<BuildDetailParameters> getParameters(){
		return this.parameters;
	}
	
	
}
