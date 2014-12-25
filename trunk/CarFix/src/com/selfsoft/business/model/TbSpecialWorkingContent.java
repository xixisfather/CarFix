package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.framework.common.Constants;

/**
 * TbSpecialWorkingContent entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialWorkingContent implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBusinessSpecialBalance tbBusinessSpecialBalance;
	private TbWorkingInfo tbWorkingInfo;
	private String stationNameDB;
	private Double fixHour;
	private Double workingHourPrice;
	private Double fixHourAll;
	private Long freesymbol;

	// Constructors

	/** default constructor */
	public TbSpecialWorkingContent() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbBusinessSpecialBalance getTbBusinessSpecialBalance() {
		return tbBusinessSpecialBalance;
	}

	public void setTbBusinessSpecialBalance(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		this.tbBusinessSpecialBalance = tbBusinessSpecialBalance;
	}

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}

	public String getStationNameDB() {
		return stationNameDB;
	}

	public void setStationNameDB(String stationNameDB) {
		this.stationNameDB = stationNameDB;
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

	public Long getFreesymbol() {
		return this.freesymbol;
	}

	public void setFreesymbol(Long freesymbol) {
		this.freesymbol = freesymbol;
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
		
		if(null != this.stationNameDB){
			
			stationName = this.stationNameDB;
		}
		else{
			
			if(null!=this.tbWorkingInfo){
				stationName = this.tbWorkingInfo.getStationName();
			}
			
		}
		
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	private String freeSymbolShow;

	public String getFreeSymbolShow() {
		
		if(Constants.FREE.equals(this.freesymbol)){
			freeSymbolShow = "是";
		}
		else{
			freeSymbolShow = "否";
		}
		return freeSymbolShow;
	}

	public void setFreeSymbolShow(String freeSymbolShow) {
		this.freeSymbolShow = freeSymbolShow;
	}
	
	
}