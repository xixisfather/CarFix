package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmCarBody entity. @author MyEclipse Persistence Tools
 */

public class TmCarBody implements java.io.Serializable {

	// Fields

	private Long id;
	private String bodyName;
	private String bodyRemark;
	private Set tbWorkingInfos = new HashSet();
	// Constructors

	/** default constructor */
	public TmCarBody() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBodyName() {
		return this.bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}

	public String getBodyRemark() {
		return this.bodyRemark;
	}

	public void setBodyRemark(String bodyRemark) {
		this.bodyRemark = bodyRemark;
	}

	public Set getTbWorkingInfos() {
		return tbWorkingInfos;
	}

	public void setTbWorkingInfos(Set tbWorkingInfos) {
		this.tbWorkingInfos = tbWorkingInfos;
	}

}