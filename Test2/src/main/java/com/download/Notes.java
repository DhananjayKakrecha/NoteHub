package com.download;

public class Notes {
	private String subName;
	private String name;
	private String fileName;
	private int nid;
	private String sender;
	
	public Notes() {
		// TODO Auto-generated constructor stub
	}
	
	public Notes(String subName, String name, String fileName) {
		super();
		this.subName = subName;
		this.name = name;
		this.fileName = fileName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
}
