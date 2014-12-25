package com.selfsoft.secrity.model;

/**
 * TmRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmRole implements java.io.Serializable {

	// Fields

	private Long id;
	private String roleCode;
	private String roleName;
	private String roleNote;

	// Constructors

	/** default constructor */
	public TmRole() {
	}

	/** minimal constructor */
	public TmRole(Long id, String roleCode) {
		this.id = id;
		this.roleCode = roleCode;
	}

	/** full constructor */
	public TmRole(Long id, String roleCode, String roleName, String roleNote) {
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleNote = roleNote;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNote() {
		return this.roleNote;
	}

	public void setRoleNote(String roleNote) {
		this.roleNote = roleNote;
	}

}