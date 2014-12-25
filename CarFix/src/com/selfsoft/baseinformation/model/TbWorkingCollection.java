package com.selfsoft.baseinformation.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarStationType;

/**
 * TbWorkingCollection entity. @author MyEclipse Persistence Tools
 */

public class TbWorkingCollection implements java.io.Serializable {

	// Fields

	private Long id;
	private String workingCollectionCode;
	private String workingCollectionName;
	private Set tbWorkingRelations = new HashSet();
	private TmCarStationType tmCarStationType;
	private Long tmCarStationTypeId;
	private Set tbBookFixStations = new HashSet();
	// Constructors

	/** default constructor */
	public TbWorkingCollection() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkingCollectionCode() {
		return this.workingCollectionCode;
	}

	public void setWorkingCollectionCode(String workingCollectionCode) {
		this.workingCollectionCode = workingCollectionCode;
	}

	public String getWorkingCollectionName() {
		return this.workingCollectionName;
	}

	public void setWorkingCollectionName(String workingCollectionName) {
		this.workingCollectionName = workingCollectionName;
	}

	public Set getTbWorkingRelations() {
		return tbWorkingRelations;
	}

	public void setTbWorkingRelations(Set tbWorkingRelations) {
		this.tbWorkingRelations = tbWorkingRelations;
	}

	public TmCarStationType getTmCarStationType() {
		return tmCarStationType;
	}

	public void setTmCarStationType(TmCarStationType tmCarStationType) {
		this.tmCarStationType = tmCarStationType;
	}

	public Long getTmCarStationTypeId() {
		return tmCarStationTypeId;
	}

	public void setTmCarStationTypeId(Long tmCarStationTypeId) {
		this.tmCarStationTypeId = tmCarStationTypeId;
	}
	
	public Set getTbBookFixStations() {
		return tbBookFixStations;
	}

	public void setTbBookFixStations(Set tbBookFixStations) {
		this.tbBookFixStations = tbBookFixStations;
	}

	private String tmCarStationTypeName;

	public String getTmCarStationTypeName() {
		if(null!=this.tmCarStationType){
			tmCarStationTypeName = this.tmCarStationType.getStationRemark();
		}
		return tmCarStationTypeName;
	}

	public void setTmCarStationTypeName(String tmCarStationTypeName) {
		this.tmCarStationTypeName = tmCarStationTypeName;
	}
	
}