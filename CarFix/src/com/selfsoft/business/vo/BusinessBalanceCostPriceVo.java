package com.selfsoft.business.vo;

import java.util.Date;

public class BusinessBalanceCostPriceVo {

	private String balanceCode;
	
	private String entrustCode;
	
	private Long userId;
	
	private String beginBalanceDeadTime;
	
	private String endBalanceDeadTime;
	
	private String licenseCode;
	
	private Long serviceUserId;

	public Long getServiceUserId() {
		return serviceUserId;
	}

	public void setServiceUserId(Long serviceUserId) {
		this.serviceUserId = serviceUserId;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public String getEntrustCode() {
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBeginBalanceDeadTime() {
		return beginBalanceDeadTime;
	}

	public void setBeginBalanceDeadTime(String beginBalanceDeadTime) {
		this.beginBalanceDeadTime = beginBalanceDeadTime;
	}

	public String getEndBalanceDeadTime() {
		return endBalanceDeadTime;
	}

	public void setEndBalanceDeadTime(String endBalanceDeadTime) {
		this.endBalanceDeadTime = endBalanceDeadTime;
	}

	
	
	
}
