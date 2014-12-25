package com.selfsoft.baseparameter.model;

import java.io.Serializable;

public class TmProjectType implements Serializable{

	private Long id;
	
	private String projectType;
	
	private String zl;
	
	private String xmlx;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	
	
	
}
