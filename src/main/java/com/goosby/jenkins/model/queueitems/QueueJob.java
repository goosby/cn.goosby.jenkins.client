package com.goosby.jenkins.model.queueitems;

import java.util.List;

public class QueueJob {
	
	
	private List<Items> items;

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public List<Items> getItems() {
		return this.items;
	}
}
