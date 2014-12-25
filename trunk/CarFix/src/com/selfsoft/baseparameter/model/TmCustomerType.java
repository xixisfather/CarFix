package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmCustomerType entity. @author MyEclipse Persistence Tools
 */

public class TmCustomerType implements java.io.Serializable {

	// Fields

	private Long id;
	private String typeName;
	private String typeRemark;
	private Set tbCustomers = new HashSet();
	// Constructors

	/** default constructor */
	public TmCustomerType() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeRemark() {
		return this.typeRemark;
	}

	public void setTypeRemark(String typeRemark) {
		this.typeRemark = typeRemark;
	}

	public Set getTbCustomers() {
		return tbCustomers;
	}

	public void setTbCustomers(Set tbCustomers) {
		this.tbCustomers = tbCustomers;
	}
	
}