package com.goosby.jenkins.model.jobdetail;

public class ParameterDefinitions {
	
	
	private DefaultParameterValue defaultParameterValue;

	private String description;

	private String name;

	private String type;

	public void setDefaultParameterValue(DefaultParameterValue defaultParameterValue) {
		this.defaultParameterValue = defaultParameterValue;
	}

	public DefaultParameterValue getDefaultParameterValue() {
		return this.defaultParameterValue;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
}
