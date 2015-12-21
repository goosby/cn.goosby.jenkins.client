package com.goosby.jenkins.model;

import java.util.List;

public class JenkinsJob {

	private List<Actions> actions;

	private String description;

	private String displayName;

	private String name;

	private String url;

	private boolean buildable;

	private List<Builds> builds;

	private String color;

	private FirstBuild firstBuild;

	private List<HealthReport> healthReport;

	private boolean inQueue;

	private boolean keepDependencies;

	private LastBuild lastBuild;

	private LastCompletedBuild lastCompletedBuild;

	private LastStableBuild lastStableBuild;

	private LastSuccessfulBuild lastSuccessfulBuild;

	private int nextBuildNumber;

	private List<Property> property;

	private boolean concurrentBuild;

	private List<DownstreamProjects> downstreamProjects;

	private Scm scm;

	private List<UpstreamProjects> upstreamProjects;

	public void setActions(List<Actions> actions) {
		this.actions = actions;
	}

	public List<Actions> getActions() {
		return this.actions;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}

	public boolean getBuildable() {
		return this.buildable;
	}

	public void setBuilds(List<Builds> builds) {
		this.builds = builds;
	}

	public List<Builds> getBuilds() {
		return this.builds;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getColor() {
		return this.color;
	}

	public void setFirstBuild(FirstBuild firstBuild) {
		this.firstBuild = firstBuild;
	}

	public FirstBuild getFirstBuild() {
		return this.firstBuild;
	}

	public void setHealthReport(List<HealthReport> healthReport) {
		this.healthReport = healthReport;
	}

	public List<HealthReport> getHealthReport() {
		return this.healthReport;
	}

	public void setInQueue(boolean inQueue) {
		this.inQueue = inQueue;
	}

	public boolean getInQueue() {
		return this.inQueue;
	}

	public void setKeepDependencies(boolean keepDependencies) {
		this.keepDependencies = keepDependencies;
	}

	public boolean getKeepDependencies() {
		return this.keepDependencies;
	}

	public void setLastBuild(LastBuild lastBuild) {
		this.lastBuild = lastBuild;
	}

	public LastBuild getLastBuild() {
		return this.lastBuild;
	}

	public void setLastCompletedBuild(LastCompletedBuild lastCompletedBuild) {
		this.lastCompletedBuild = lastCompletedBuild;
	}

	public LastCompletedBuild getLastCompletedBuild() {
		return this.lastCompletedBuild;
	}

	public void setLastStableBuild(LastStableBuild lastStableBuild) {
		this.lastStableBuild = lastStableBuild;
	}

	public LastStableBuild getLastStableBuild() {
		return this.lastStableBuild;
	}

	public void setLastSuccessfulBuild(LastSuccessfulBuild lastSuccessfulBuild) {
		this.lastSuccessfulBuild = lastSuccessfulBuild;
	}

	public LastSuccessfulBuild getLastSuccessfulBuild() {
		return this.lastSuccessfulBuild;
	}

	public void setNextBuildNumber(int nextBuildNumber) {
		this.nextBuildNumber = nextBuildNumber;
	}

	public int getNextBuildNumber() {
		return this.nextBuildNumber;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}

	public List<Property> getProperty() {
		return this.property;
	}

	public void setConcurrentBuild(boolean concurrentBuild) {
		this.concurrentBuild = concurrentBuild;
	}

	public boolean getConcurrentBuild() {
		return this.concurrentBuild;
	}

	public void setDownstreamProjects(List<DownstreamProjects> downstreamProjects) {
		this.downstreamProjects = downstreamProjects;
	}

	public List<DownstreamProjects> getDownstreamProjects() {
		return this.downstreamProjects;
	}

	public void setScm(Scm scm) {
		this.scm = scm;
	}

	public Scm getScm() {
		return this.scm;
	}

	public void setUpstreamProjects(List<UpstreamProjects> upstreamProjects) {
		this.upstreamProjects = upstreamProjects;
	}

	public List<UpstreamProjects> getUpstreamProjects() {
		return this.upstreamProjects;
	}

}
