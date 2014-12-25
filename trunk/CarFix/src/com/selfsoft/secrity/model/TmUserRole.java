package com.selfsoft.secrity.model;

/**
 * TmUserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmUserRole implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleId;
	private Long userId;

	// Constructors

	/** default constructor */
	public TmUserRole() {
	}

	/** full constructor */
	public TmUserRole(Long id, Long roleId, Long userId) {
		this.id = id;
		this.roleId = roleId;
		this.userId = userId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}