package com.goosby.jenkins.model.builddetail;

import java.util.List;

public class BuildDetail {
	
	
	private List<BuildDetailActions> actions;

	private List<Artifacts> artifacts;

	private boolean building;

	private long duration;

	private long estimatedDuration;

	private String fullDisplayName;

	private String id;

	private boolean keepLog;

	private long number;

	private String result;

	private long timestamp;

	private String url;

	private String builtOn;

	private ChangeSet changeSet;

	private List<Culprits> culprits;

	public void setActions(List<BuildDetailActions> actions) {
		this.actions = actions;
	}

	public List<BuildDetailActions> getActions() {
		return this.actions;
	}

	public void setArtifacts(List<Artifacts> artifacts) {
		this.artifacts = artifacts;
	}

	public List<Artifacts> getArtifacts() {
		return this.artifacts;
	}

	public void setBuilding(boolean building) {
		this.building = building;
	}

	public boolean getBuilding() {
		return this.building;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getDuration() {
		return this.duration;
	}

	public void setEstimatedDuration(long estimatedDuration) {
		this.estimatedDuration = estimatedDuration;
	}

	public long getEstimatedDuration() {
		return this.estimatedDuration;
	}

	public void setFullDisplayName(String fullDisplayName) {
		this.fullDisplayName = fullDisplayName;
	}

	public String getFullDisplayName() {
		return this.fullDisplayName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setKeepLog(boolean keepLog) {
		this.keepLog = keepLog;
	}

	public boolean getKeepLog() {
		return this.keepLog;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getNumber() {
		return this.number;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return this.result;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setBuiltOn(String builtOn) {
		this.builtOn = builtOn;
	}

	public String getBuiltOn() {
		return this.builtOn;
	}

	public void setChangeSet(ChangeSet changeSet) {
		this.changeSet = changeSet;
	}

	public ChangeSet getChangeSet() {
		return this.changeSet;
	}

	public void setCulprits(List<Culprits> culprits) {
		this.culprits = culprits;
	}

	public List<Culprits> getCulprits() {
		return this.culprits;
	}
}
