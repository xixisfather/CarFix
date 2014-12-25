package com.selfsoft.business.vo;

import java.util.Date;

public class TbFixEntrustVo {

	private Long entrustId;
	private String entrustCode;
	private String licenseCode;
	private Date fixDate;
	private String customerName;
	private String fixType;
	private String stationCode;
	private String customerCode;
	private Long customerId;
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public Date getFixDate() {
		return fixDate;
	}
	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFixType() {
		return fixType;
	}
	public void setFixType(String fixType) {
		this.fixType = fixType;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public Long getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}
	public String getEntrustCode() {
		return entrustCode;
	}
	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}
	
}
