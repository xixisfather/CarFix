package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbCarInfo;

public class TbReturnVisit implements java.io.Serializable{
	
	private Long id;
	
	private TbCarInfo tbCarInfo;
	
	private String returnType;
	
	private String visitRemark;
	
	private Date visitDate;
	
	private Date visitDateFrom;
	
	private Date visitDateTo;
	
	private Long entrustId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getVisitRemark() {
		return visitRemark;
	}

	public void setVisitRemark(String visitRemark) {
		this.visitRemark = visitRemark;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Date getVisitDateFrom() {
		return visitDateFrom;
	}

	public void setVisitDateFrom(Date visitDateFrom) {
		this.visitDateFrom = visitDateFrom;
	}

	public Date getVisitDateTo() {
		return visitDateTo;
	}

	public void setVisitDateTo(Date visitDateTo) {
		this.visitDateTo = visitDateTo;
	}

	public Long getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}
	
	private TbFixEntrust tbFixEntrust;

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}
	
	
}
