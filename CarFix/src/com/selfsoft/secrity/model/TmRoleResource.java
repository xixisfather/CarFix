package com.selfsoft.secrity.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmRoleResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmRoleResource implements java.io.Serializable {

	// Fields

	private Long id;
	private Long resourceId;
	private Long roleId;
	private Set<TmPermission> permissions = new HashSet<TmPermission>();

	// Constructors

	public Set<TmPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<TmPermission> permissions) {
		this.permissions = permissions;
	}

	/** default constructor */
	public TmRoleResource() {
	}
	
	/** full constructor */
	public TmRoleResource(Long id, Long resourceId, Long roleId) {
		super();
		this.id = id;
		this.resourceId = resourceId;
		this.roleId = roleId;
	}

	// Property accessors
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}