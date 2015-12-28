package com.goosby.jenkins.model.queueitems;

import java.util.List;

public class Items {
	
	
	private List<QueueActions> actions;

	private boolean blocked;

	private boolean buildable;

	private long id;

	private long inQueueSince;

	private String params;

	private boolean stuck;

	private Task task;

	private String why;

	private long buildableStartMilliseconds;

	public void setActions(List<QueueActions> actions) {
		this.actions = actions;
	}

	public List<QueueActions> getActions() {
		return this.actions;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public boolean getBlocked() {
		return this.blocked;
	}

	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}

	public boolean getBuildable() {
		return this.buildable;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setInQueueSince(long inQueueSince) {
		this.inQueueSince = inQueueSince;
	}

	public long getInQueueSince() {
		return this.inQueueSince;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParams() {
		return this.params;
	}

	public void setStuck(boolean stuck) {
		this.stuck = stuck;
	}

	public boolean getStuck() {
		return this.stuck;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return this.task;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getWhy() {
		return this.why;
	}

	public void setBuildableStartMilliseconds(long buildableStartMilliseconds) {
		this.buildableStartMilliseconds = buildableStartMilliseconds;
	}

	public long getBuildableStartMilliseconds() {
		return this.buildableStartMilliseconds;
	}
}
