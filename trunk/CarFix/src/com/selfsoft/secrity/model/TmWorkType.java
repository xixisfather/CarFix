package com.selfsoft.secrity.model;

import java.util.Set;

/**
 * TmWorkType entity. @author MyEclipse Persistence Tools
 */

public class TmWorkType implements java.io.Serializable {

	// Fields

	private Long id;
	private String workCode;
	private String workName;
	private Set tbWorkingInfos;
	private Set<TmUser> tmUsers;
	// Constructors

	public Set<TmUser> getTmUsers() {
		return tmUsers;
	}

	public void setTmUsers(Set<TmUser> tmUsers) {
		this.tmUsers = tmUsers;
	}

	/** default constructor */
	public TmWorkType() {
	}

	/** full constructor */
	public TmWorkType(String workCode, String workName) {
		this.workCode = workCode;
		this.workName = workName;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkCode() {
		return this.workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public String getWorkName() {
		return this.workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Set getTbWorkingInfos() {
		return tbWorkingInfos;
	}

	public void setTbWorkingInfos(Set tbWorkingInfos) {
		this.tbWorkingInfos = tbWorkingInfos;
	}

	
}