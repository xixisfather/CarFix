package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmSoleType entity. @author MyEclipse Persistence Tools
 */

public class TmSoleType implements java.io.Serializable {

	// Fields

	private Long id;
	private String soleName;
	private Long isDefault;
	private Set tbCustomers = new HashSet();
	// Constructors

	/** default constructor */
	public TmSoleType() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSoleName() {
		return this.soleName;
	}

	public void setSoleName(String soleName) {
		this.soleName = soleName;
	}

	public Set getTbCustomers() {
		return tbCustomers;
	}

	public void setTbCustomers(Set tbCustomers) {
		this.tbCustomers = tbCustomers;
	}

	public Long getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Long isDefault) {
		this.isDefault = isDefault;
	}

	private String defaultName;

	public String getDefaultName() {
		if(this.isDefault == 0L)
			return "否";
		
		if(this.isDefault == 1L)
			return "是";
		return null;
	}
	
	
	
}