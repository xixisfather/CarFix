package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.framework.common.Constants;


public class TmSpecialCarAlert implements java.io.Serializable{

	private Long id;
	
	private Date beginDate;
	
	private Date endDate;
	
	private Long alertCount;
	
	private String alertContent;
	
	private String alertRange;
	
	private Long isAlert;
	
	private TbCarInfo tbCarInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(Long alertCount) {
		this.alertCount = alertCount;
	}

	public String getAlertContent() {
		return alertContent;
	}

	public void setAlertContent(String alertContent) {
		this.alertContent = alertContent;
	}

	public String getAlertRange() {
		return alertRange;
	}

	public void setAlertRange(String alertRange) {
		this.alertRange = alertRange;
	}

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}
	
	private String alertCountName;
	
	private String alertRangeName;

	public String getAlertCountName() {
		if(this.getAlertCount().equals(Constants.SINGLEALERT))
			return "单次提醒";
		if(this.getAlertCount().equals(Constants.MULTIALERT))
			return "反复提醒";
		
		return null;
		
	}

	public String getAlertRangeName() {
		
		String returnName = "";
		if(this.alertRange.contains(Constants.ENTRIST_RANGE_ALERT+""))
			returnName += "开委托书,";
		
		if(this.alertRange.contains(Constants.BALANCE_RANGE_ALERT+""))
			returnName += "结算时,";
		
		if(this.alertRange.contains(Constants.FINISH_RANGE_ALERT+""))
			returnName += "竣工时,";
		
		return returnName;
	}

	public Long getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(Long isAlert) {
		this.isAlert = isAlert;
	}
	
	
}
