package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * TmUnit entity. @author MyEclipse Persistence Tools
 */

public class TmUnit implements java.io.Serializable {

	// Fields

	private Long id;
	private String unitName;
	private String unitRemark;
	private Set<TbPartInfo> tbPartInfos = new HashSet<TbPartInfo>();

	// Constructors

	/** default constructor */
	public TmUnit() {
	}

	/** full constructor */
	public TmUnit(String unitName, String unitRemark) {
		this.unitName = unitName;
		this.unitRemark = unitRemark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnitName() {
		return this.unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitRemark() {
		return this.unitRemark;
	}

	public void setUnitRemark(String unitRemark) {
		this.unitRemark = unitRemark;
	}

	public Set<TbPartInfo> getTbPartInfos() {
		return tbPartInfos;
	}

	public void setTbPartInfos(Set<TbPartInfo> tbPartInfos) {
		this.tbPartInfos = tbPartInfos;
	}
	
}