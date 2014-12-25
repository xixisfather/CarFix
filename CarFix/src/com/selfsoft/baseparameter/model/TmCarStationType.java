package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmCarStationType entity. @author MyEclipse Persistence Tools
 */

public class TmCarStationType implements java.io.Serializable {

	// Fields

	private Long id;
	private String stationCode;
	private String stationRemark;
	private Set tmCarModelTypes = new HashSet();
	private Set tbCarStationWorkingRelations = new HashSet(); 
	private Set tbWorkingCollections = new HashSet();
	// Constructors

	/** default constructor */
	public TmCarStationType() {
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStationCode() {
		return this.stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationRemark() {
		return this.stationRemark;
	}

	public void setStationRemark(String stationRemark) {
		this.stationRemark = stationRemark;
	}

	public Set getTmCarModelTypes() {
		return tmCarModelTypes;
	}

	public void setTmCarModelTypes(Set tmCarModelTypes) {
		this.tmCarModelTypes = tmCarModelTypes;
	}


	public Set getTbCarStationWorkingRelations() {
		return tbCarStationWorkingRelations;
	}


	public void setTbCarStationWorkingRelations(Set tbCarStationWorkingRelations) {
		this.tbCarStationWorkingRelations = tbCarStationWorkingRelations;
	}


	public Set getTbWorkingCollections() {
		return tbWorkingCollections;
	}


	public void setTbWorkingCollections(Set tbWorkingCollections) {
		this.tbWorkingCollections = tbWorkingCollections;
	}


	
	
}