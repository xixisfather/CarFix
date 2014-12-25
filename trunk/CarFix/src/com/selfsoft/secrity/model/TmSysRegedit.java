package com.selfsoft.secrity.model;

/**
 * TmSysRegedit entity. @author MyEclipse Persistence Tools
 */

public class TmSysRegedit implements java.io.Serializable {

	// Fields

	private Long id;
	private String sysSn;

	// Constructors

	/** default constructor */
	public TmSysRegedit() {
	}

	/** full constructor */
	public TmSysRegedit(String sysSn) {
		this.sysSn = sysSn;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSysSn() {
		return this.sysSn;
	}

	public void setSysSn(String sysSn) {
		this.sysSn = sysSn;
	}

}