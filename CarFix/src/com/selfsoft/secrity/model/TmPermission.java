package com.selfsoft.secrity.model;

/**
 * TmPermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmPermission implements java.io.Serializable {

	// Fields

	private Long id;
	private String permissionType;
	private TmRoleResource tmRoleResource;

	// Constructors

	/** default constructor */
	public TmPermission() {
	}

	/** minimal constructor */
	public TmPermission(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmPermission(Long id, String permissionType) {
		this.id = id;
		this.permissionType = permissionType;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public TmRoleResource getTmRoleResource() {
		return tmRoleResource;
	}

	public void setTmRoleResource(TmRoleResource tmRoleResource) {
		this.tmRoleResource = tmRoleResource;
	}

}