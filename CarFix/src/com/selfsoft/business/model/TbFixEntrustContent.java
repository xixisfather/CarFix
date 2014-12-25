package com.selfsoft.business.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.framework.common.Constants;

/**
 * TbFixEntrustContent entity. @author MyEclipse Persistence Tools
 */

public class TbFixEntrustContent implements java.io.Serializable {

	// Fields

	private Long id;
	private TbFixEntrust tbFixEntrust;
	private TbWorkingInfo tbWorkingInfo;
	private Double fixHour;
	private Double workingHourPrice;
	private Double fixHourAll;
	private Double sendHour;
	private Long freesymbol;
	private Long balanceId;
	private String zl;
	private String xmlx;
	private String projectType;
	private String wxlx;
	private Set tbFixShares = new HashSet();
	// Constructors

	/** default constructor */
	public TbFixEntrustContent() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}

	public Double getFixHour() {
		return this.fixHour;
	}

	public void setFixHour(Double fixHour) {
		this.fixHour = fixHour;
	}

	public Double getWorkingHourPrice() {
		return this.workingHourPrice;
	}

	public void setWorkingHourPrice(Double workingHourPrice) {
		this.workingHourPrice = workingHourPrice;
	}

	public Double getFixHourAll() {
		return this.fixHourAll;
	}

	public void setFixHourAll(Double fixHourAll) {
		this.fixHourAll = fixHourAll;
	}

	public Double getSendHour() {
		return this.sendHour;
	}

	public void setSendHour(Double sendHour) {
		this.sendHour = sendHour;
	}

	public Long getFreesymbol() {
		return this.freesymbol;
	}

	public void setFreesymbol(Long freesymbol) {
		this.freesymbol = freesymbol;
	}
	
	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getWxlx() {
		return wxlx;
	}

	public void setWxlx(String wxlx) {
		this.wxlx = wxlx;
	}

	public Set getTbFixShares() {
		return tbFixShares;
	}

	public void setTbFixShares(Set tbFixShares) {
		this.tbFixShares = tbFixShares;
	}

	public Long getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}

	private String stationCode;
	
	private String stationName;
	
	
	
	public String getStationCode() {
		if(null!=this.tbWorkingInfo){
			stationCode = this.tbWorkingInfo.getStationCode();
		}
		
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationName() {
		/*if(null!=this.tbWorkingInfo){
			stationName = this.tbWorkingInfo.getStationName();
		}*/
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	private String freeSymbolShow;

	public String getFreeSymbolShow() {
		
		if(Constants.FREESYMBOL_NO.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_NO_SHOW;
		}
		else if(Constants.FREESYMBOL_SP.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_SP_SHOW;
		}
		else if(Constants.FREESYMBOL_SB.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_SB_SHOW;
		}
		else if(Constants.FREESYMBOL_FG.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_FG_SHOW;
		}
		else if(Constants.FREESYMBOL_FW.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_FW_SHOW;
		}
		else if(Constants.FREESYMBOL_BX.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_BX_SHOW;
		}
		else if(Constants.FREESYMBOL_NF.equals(this.freesymbol)){
			freeSymbolShow = Constants.FREESYMBOL_NF_SHOW;
		}
		return freeSymbolShow;
	}

	public void setFreeSymbolShow(String freeSymbolShow) {
		this.freeSymbolShow = freeSymbolShow;
	}
	
	//冗余字段
	private String fixPersons;
	
	private String fixPersonIds;

	public String getFixPersons() {
		return fixPersons;
	}

	public void setFixPersons(String fixPersons) {
		this.fixPersons = fixPersons;
	}

	public String getFixPersonIds() {
		return fixPersonIds;
	}

	public void setFixPersonIds(String fixPersonIds) {
		this.fixPersonIds = fixPersonIds;
	}
	
}