package com.selfsoft.baseinformation.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarBody;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.secrity.model.TmWorkType;

/**
 * TbWorkingInfo entity. @author MyEclipse Persistence Tools
 */

public class TbWorkingInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String stationCode;
	private String stationName;
	private String pinyinCode;
	private TmWorkType tmWorkType;
	private TmCarBody tmCarBody;
	private Double fixHour;
	private Double sendHour;
	private Set tbCarStationWorkingRelations = new HashSet();
	private Set tmCarStationTypes = new HashSet();
	private Long tmCarStationTypeId;
	private Set tbWorkingRelations = new HashSet();
	private Set tbBookFixStations = new HashSet();
	private Set tbFixEntrustContents = new HashSet();
	// Constructors

	/** default constructor */
	public TbWorkingInfo() {
	}

	// Property accessors

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

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getPinyinCode() {
		return this.pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public TmWorkType getTmWorkType() {
		return tmWorkType;
	}

	public void setTmWorkType(TmWorkType tmWorkType) {
		this.tmWorkType = tmWorkType;
	}

	public TmCarBody getTmCarBody() {
		return tmCarBody;
	}

	public void setTmCarBody(TmCarBody tmCarBody) {
		this.tmCarBody = tmCarBody;
	}

	public Double getFixHour() {
		return this.fixHour;
	}

	public void setFixHour(Double fixHour) {
		this.fixHour = fixHour;
	}

	public Double getSendHour() {
		return this.sendHour;
	}

	public void setSendHour(Double sendHour) {
		this.sendHour = sendHour;
	}

	
	public Set getTbCarStationWorkingRelations() {
		return tbCarStationWorkingRelations;
	}

	public void setTbCarStationWorkingRelations(Set tbCarStationWorkingRelations) {
		this.tbCarStationWorkingRelations = tbCarStationWorkingRelations;
	}

	public Set getTmCarStationTypes() {
		return tmCarStationTypes;
	}

	public void setTmCarStationTypes(Set tmCarStationTypes) {
		this.tmCarStationTypes = tmCarStationTypes;
	}

	public Long getTmCarStationTypeId() {
		return tmCarStationTypeId;
	}

	public void setTmCarStationTypeId(Long tmCarStationTypeId) {
		this.tmCarStationTypeId = tmCarStationTypeId;
	}

	public Set getTbWorkingRelations() {
		return tbWorkingRelations;
	}

	public void setTbWorkingRelations(Set tbWorkingRelations) {
		this.tbWorkingRelations = tbWorkingRelations;
	}



	public Set getTbFixEntrustContents() {
		return tbFixEntrustContents;
	}

	public void setTbFixEntrustContents(Set tbFixEntrustContents) {
		this.tbFixEntrustContents = tbFixEntrustContents;
	}



	private String workName;
	
	private	String bodyName;
	
	private String tmCarStationNames;

	private String xlsPK;
	
	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}

	public String getWorkName() {
		if(null!=this.tmWorkType){
			workName = this.tmWorkType.getWorkName();
		}
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getBodyName() {
		if(null!=this.tmCarBody){
			bodyName = this.tmCarBody.getBodyName();
		}
		return bodyName;
	}

	public void setBodyName(String bodyName) {
		this.bodyName = bodyName;
	}

	public String getTmCarStationNames() {
		return tmCarStationNames;
	}

	public void setTmCarStationNames(String tmCarStationNames) {
		this.tmCarStationNames = tmCarStationNames;
	}
	
	public Set getTbBookFixStations() {
		return tbBookFixStations;
	}

	public void setTbBookFixStations(Set tbBookFixStations) {
		this.tbBookFixStations = tbBookFixStations;
	}



	//EXCEL中导出时用字符型
	private String fixHourXls;
	
	private String sendHourXls;

	public String getFixHourXls() {
		if(null!=this.fixHour){
			fixHourXls = String.valueOf(this.fixHour);
		}
		return fixHourXls;
	}

	public void setFixHourXls(String fixHourXls) {
		this.fixHourXls = fixHourXls;
	}

	public String getSendHourXls() {
		if(null!=this.sendHour){
			sendHourXls = String.valueOf(this.sendHour);
		}
		return sendHourXls;
	}

	public void setSendHourXls(String sendHourXls) {
		this.sendHourXls = sendHourXls;
	}
	
	
	
	
}