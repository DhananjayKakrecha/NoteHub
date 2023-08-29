package com.note;

public class VersionControl {
	private int vid;
	private int nid;
	private int branchFrom;
	private String title;
	private String description;
	private String date;

	public int getVid() {
		return vid;
	}

	public void setVid(int vid) {
		this.vid = vid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public int getBranchFrom() {
		return branchFrom;
	}

	public void setBranchFrom(int branchFrom) {
		this.branchFrom = branchFrom;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
