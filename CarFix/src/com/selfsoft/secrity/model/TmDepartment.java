package com.selfsoft.secrity.model;

import java.util.Set;

/**
 * TmDepartment entity. @author MyEclipse Persistence Tools
 */

public class TmDepartment implements java.io.Serializable {

	// Fields

	private Long id;
	private String deptCode;
	private String deptName;
	
	private Set<TmUser> tmUsers;

	// Constructors

	public Set<TmUser> getTmUsers() {
		return tmUsers;
	}

	public void setTmUsers(Set<TmUser> tmUsers) {
		this.tmUsers = tmUsers;
	}

	/** default constructor */
	public TmDepartment() {
	}

	/** full constructor */
	public TmDepartment(String deptCode, String deptName) {
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}