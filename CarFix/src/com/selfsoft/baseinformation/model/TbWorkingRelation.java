package com.selfsoft.baseinformation.model;

/**
 * TbWorkingRelation entity. @author MyEclipse Persistence Tools
 */

public class TbWorkingRelation implements java.io.Serializable {

	// Fields

	private Long id;
	private TbWorkingInfo tbWorkingInfo;
	private TbWorkingCollection tbWorkingCollection;

	// Constructors

	/** default constructor */
	public TbWorkingRelation() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}

	public TbWorkingCollection getTbWorkingCollection() {
		return tbWorkingCollection;
	}

	public void setTbWorkingCollection(TbWorkingCollection tbWorkingCollection) {
		this.tbWorkingCollection = tbWorkingCollection;
	}
}