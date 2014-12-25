package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

public class TbStoreHouseCheck {

	private Long id;
	
	private String checkCode;
	
	private Date checkDate;
	
	private TmUser tmUser;
	
	private Date zzDate;
	
	private String memo;
	
	private Long isConfirm;
	
	private String storeHouseIds;
	
	private String storeHouseNames;
	
	private Long zzType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public Date getZzDate() {
		return zzDate;
	}

	public void setZzDate(Date zzDate) {
		this.zzDate = zzDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getStoreHouseIds() {
		return storeHouseIds;
	}

	public void setStoreHouseIds(String storeHouseIds) {
		this.storeHouseIds = storeHouseIds;
	}

	public Long getZzType() {
		return zzType;
	}

	public void setZzType(Long zzType) {
		this.zzType = zzType;
	}
	
	private String beginCheckDate;
	
	private String endCheckDate;
	
	private String beginZzDate;
	
	private String endZzDate;

	public String getBeginCheckDate() {
		return beginCheckDate;
	}

	public void setBeginCheckDate(String beginCheckDate) {
		this.beginCheckDate = beginCheckDate;
	}

	public String getEndCheckDate() {
		return endCheckDate;
	}

	public void setEndCheckDate(String endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public String getBeginZzDate() {
		return beginZzDate;
	}

	public void setBeginZzDate(String beginZzDate) {
		this.beginZzDate = beginZzDate;
	}

	public String getEndZzDate() {
		return endZzDate;
	}

	public void setEndZzDate(String endZzDate) {
		this.endZzDate = endZzDate;
	}

	public String getStoreHouseNames() {
		return storeHouseNames;
	}

	public void setStoreHouseNames(String storeHouseNames) {
		this.storeHouseNames = storeHouseNames;
	}
	
	private String confirmShowName;

	public String getConfirmShowName() {
		return Constants.checkStatusMap().get(this.isConfirm);
	}
	
}
