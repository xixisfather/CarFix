package com.selfsoft.business.model;

import com.selfsoft.framework.common.CommonMethod;

/**
 * TmLianceReturnDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLianceReturnDetail implements java.io.Serializable {

	// Fields

	private Long id;
	
	private String lianceBill;
	private Double returnQuantity;
	private Double lackReturnQuantity;
	private Long isReturn;
	private Double costPrice;
	
	private TmLianceReturn tmLianceReturn;
	private TmLianceRegisterDetail tmLianceRegisterDetail;

	// Constructors

	public TmLianceReturn getTmLianceReturn() {
		return tmLianceReturn;
	}

	public void setTmLianceReturn(TmLianceReturn tmLianceReturn) {
		this.tmLianceReturn = tmLianceReturn;
	}

	/** default constructor */
	public TmLianceReturnDetail() {
	}

	/** minimal constructor */
	public TmLianceReturnDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLianceReturnDetail(Long id,
			String lianceBill, Double returnQuantity,
			Double lackReturnQuantity, Long isReturn, Double costPrice) {
		this.id = id;
		this.lianceBill = lianceBill;
		this.returnQuantity = returnQuantity;
		this.lackReturnQuantity = lackReturnQuantity;
		this.isReturn = isReturn;
		this.costPrice = costPrice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getLianceBill() {
		return this.lianceBill;
	}

	public void setLianceBill(String lianceBill) {
		this.lianceBill = lianceBill;
	}

	public Double getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Double getLackReturnQuantity() {
		return this.lackReturnQuantity;
	}

	public void setLackReturnQuantity(Double lackReturnQuantity) {
		this.lackReturnQuantity = lackReturnQuantity;
	}

	public Long getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(Long isReturn) {
		this.isReturn = isReturn;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	

	public TmLianceRegisterDetail getTmLianceRegisterDetail() {
		return tmLianceRegisterDetail;
	}

	public void setTmLianceRegisterDetail(
			TmLianceRegisterDetail tmLianceRegisterDetail) {
		this.tmLianceRegisterDetail = tmLianceRegisterDetail;
	}
	
	
	private String houseName;
	
	private String partCode;
	
	private String partName;
	
	private String unitName;
	
	private String storeLocation;
	
	private Double total;

	public String getHouseName() {
		return this.tmLianceRegisterDetail.getTbPartInfo().getTmStoreHouse().getHouseName();
	}

	public String getPartCode() {
		return this.tmLianceRegisterDetail.getTbPartInfo().getPartCode();
	}

	public String getPartName() {
		return this.tmLianceRegisterDetail.getTbPartInfo().getPartName();
	}

	public String getUnitName() {
		return this.tmLianceRegisterDetail.getTbPartInfo().getTmUnit().getUnitName();
	}

	public String getStoreLocation() {
		return this.tmLianceRegisterDetail.getTbPartInfo().getStoreLocation();
	}

	public Double getTotal() {
		return CommonMethod.convertRadixPoint(this.getCostPrice() * this.getReturnQuantity(), 2);
	}
	
	
	
}