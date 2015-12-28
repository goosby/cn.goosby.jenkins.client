package com.goosby.jenkins.model.queueitems;

import java.util.List;

public class QueueActions {
	
	
	private List<QueueParameters> parameters;

	public void setParameters(List<QueueParameters> parameters) {
		this.parameters = parameters;
	}

	public List<QueueParameters> getParameters() {
		return this.parameters;
	}
}
