package com.goosby.jenkins.model.buildjobdetail;

import java.util.HashMap;
import java.util.Map;

public class JobStatus {
	public static enum Status {
		OK,
		WARNING,
		FAILED;
		
		public static Status create(String colorCode) {
			Map<String, Status> codes = new HashMap<String, Status>();
			codes.put("blue", OK);
			codes.put("yellow", WARNING);
			codes.put("red", FAILED);
			return codes.get(colorCode);
		}
	}
	
	String name;
	
	Status status;
	
	public JobStatus(String name, Status status) {
		this.name = name;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public Status getStatus() {
		return status;
	}
}
