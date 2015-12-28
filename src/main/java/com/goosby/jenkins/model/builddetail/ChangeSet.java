package com.goosby.jenkins.model.builddetail;

import java.util.List;

public class ChangeSet {
	private List<Items> items ;

	private String kind;

	private List<Revisions> revisions ;

	public void setItems(List<Items> items){
		this.items = items;
	}
	public List<Items> getItems(){
		return this.items;
	}
	public void setKind(String kind){
		this.kind = kind;
	}
	public String getKind(){
		return this.kind;
	}
	public void setRevisions(List<Revisions> revisions){
		this.revisions = revisions;
	}
	public List<Revisions> getRevisions(){
		return this.revisions;
	}
}
