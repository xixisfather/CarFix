package com.selfsoft.baseparameter.model;

public class TmStoreDisk implements java.io.Serializable {

	private Long id;
	
	private String diskPath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiskPath() {
		return diskPath;
	}

	public void setDiskPath(String diskPath) {
		this.diskPath = diskPath;
	}

	
}
