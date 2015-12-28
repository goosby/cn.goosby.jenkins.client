package com.goosby.jenkins.model.builddetail;

public class Culprits {
	private String absoluteUrl;

	private String fullName;

	public void setAbsoluteUrl(String absoluteUrl) {
		this.absoluteUrl = absoluteUrl;
	}

	public String getAbsoluteUrl() {
		return this.absoluteUrl;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return this.fullName;
	}
}
