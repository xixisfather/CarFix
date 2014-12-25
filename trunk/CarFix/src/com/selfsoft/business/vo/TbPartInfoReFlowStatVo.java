package com.selfsoft.business.vo;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbCarInfo;

public class TbPartInfoReFlowStatVo {

	private Long typeValue ;
	
	private String typeName;
	
	private Double totalQuantity;

	private Date createDate;
	
	private String elementType;
	
	private Double inQuantity;
	
	private Double outQuantity;
	
	private Double ratePrice;
	
	private Double subTotalPrice;
	
	private String customerName;
	
	
	private String licenseCode;
	private String chassisCode;					//底盘号
	private Long tbCarInfoId;

	public Long getTbCarInfoId() {
		return tbCarInfoId;
	}

	public void setTbCarInfoId(Long tbCarInfoId) {
		this.tbCarInfoId = tbCarInfoId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public Double getInQuantity() {
		return inQuantity;
	}

	public void setInQuantity(Double inQuantity) {
		this.inQuantity = inQuantity;
	}

	public Double getOutQuantity() {
		return outQuantity;
	}

	public void setOutQuantity(Double outQuantity) {
		this.outQuantity = outQuantity;
	}

	public Double getRatePrice() {
		return ratePrice;
	}

	public void setRatePrice(Double ratePrice) {
		this.ratePrice = ratePrice;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(Long typeValue) {
		this.typeValue = typeValue;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(Double subTotalPrice) {
		this.subTotalPrice = subTotalPrice;
	}

	
	
	private String beginDate;
	
	private String endDate;


	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getChassisCode() {
		return chassisCode;
	}

	public void setChassisCode(String chassisCode) {
		this.chassisCode = chassisCode;
	}
	
	
	
}
