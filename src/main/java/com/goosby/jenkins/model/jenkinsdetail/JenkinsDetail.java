package com.goosby.jenkins.model.jenkinsdetail;

import java.util.List;

public class JenkinsDetail {
	
	
	private List<AssignedLabels> assignedLabels;

	private String mode;

	private String nodeDescription;

	private String nodeName;

	private int numExecutors;

	private List<Jobs> jobs;

	private OverallLoad overallLoad;

	private PrimaryView primaryView;

	private boolean quietingDown;

	private int slaveAgentPort;

	private UnlabeledLoad unlabeledLoad;

	private boolean useCrumbs;

	private boolean useSecurity;

	private List<Views> views;

	public void setAssignedLabels(List<AssignedLabels> assignedLabels) {
		this.assignedLabels = assignedLabels;
	}

	public List<AssignedLabels> getAssignedLabels() {
		return this.assignedLabels;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return this.mode;
	}

	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}

	public String getNodeDescription() {
		return this.nodeDescription;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNumExecutors(int numExecutors) {
		this.numExecutors = numExecutors;
	}

	public int getNumExecutors() {
		return this.numExecutors;
	}

	public void setJobs(List<Jobs> jobs) {
		this.jobs = jobs;
	}

	public List<Jobs> getJobs() {
		return this.jobs;
	}

	public void setOverallLoad(OverallLoad overallLoad) {
		this.overallLoad = overallLoad;
	}

	public OverallLoad getOverallLoad() {
		return this.overallLoad;
	}

	public void setPrimaryView(PrimaryView primaryView) {
		this.primaryView = primaryView;
	}

	public PrimaryView getPrimaryView() {
		return this.primaryView;
	}

	public void setQuietingDown(boolean quietingDown) {
		this.quietingDown = quietingDown;
	}

	public boolean getQuietingDown() {
		return this.quietingDown;
	}

	public void setSlaveAgentPort(int slaveAgentPort) {
		this.slaveAgentPort = slaveAgentPort;
	}

	public int getSlaveAgentPort() {
		return this.slaveAgentPort;
	}

	public void setUnlabeledLoad(UnlabeledLoad unlabeledLoad) {
		this.unlabeledLoad = unlabeledLoad;
	}

	public UnlabeledLoad getUnlabeledLoad() {
		return this.unlabeledLoad;
	}

	public void setUseCrumbs(boolean useCrumbs) {
		this.useCrumbs = useCrumbs;
	}

	public boolean getUseCrumbs() {
		return this.useCrumbs;
	}

	public void setUseSecurity(boolean useSecurity) {
		this.useSecurity = useSecurity;
	}

	public boolean getUseSecurity() {
		return this.useSecurity;
	}

	public void setViews(List<Views> views) {
		this.views = views;
	}

	public List<Views> getViews() {
		return this.views;
	}
}
