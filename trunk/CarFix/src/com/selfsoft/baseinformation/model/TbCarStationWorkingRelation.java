package com.selfsoft.baseinformation.model;

import com.selfsoft.baseparameter.model.TmCarStationType;

/**
 * TbCarStationWorkingRelation entity. @author MyEclipse Persistence Tools
 */

public class TbCarStationWorkingRelation implements java.io.Serializable {

	// Fields

	private Long id;
	private TbWorkingInfo tbWorkingInfo;
	private TmCarStationType tmCarStationType;
	// Constructors

	/** default constructor */
	public TbCarStationWorkingRelation() {
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

	public TmCarStationType getTmCarStationType() {
		return tmCarStationType;
	}

	public void setTmCarStationType(TmCarStationType tmCarStationType) {
		this.tmCarStationType = tmCarStationType;
	}

	
}