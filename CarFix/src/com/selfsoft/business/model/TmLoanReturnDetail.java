package com.selfsoft.business.model;

import java.util.Set;

import com.selfsoft.framework.common.CommonMethod;

/**
 * TmLoanReturnDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLoanReturnDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private String loanRegBill;
	private Double lackReturnQuantity;
	private Double returnQuantity;
	private Double costPrice;
	private Long isReturn;
	
	private TmLoanReturn tmLoanReturn;
	private TmLoanRegisterDetail tmLoanRegisterDetail;
	

	// Constructors

	public TmLoanReturn getTmLoanReturn() {
		return tmLoanReturn;
	}

	public void setTmLoanReturn(TmLoanReturn tmLoanReturn) {
		this.tmLoanReturn = tmLoanReturn;
	}

	public TmLoanRegisterDetail getTmLoanRegisterDetail() {
		return tmLoanRegisterDetail;
	}

	public void setTmLoanRegisterDetail(TmLoanRegisterDetail tmLoanRegisterDetail) {
		this.tmLoanRegisterDetail = tmLoanRegisterDetail;
	}

	/** default constructor */
	public TmLoanReturnDetail() {
	}

	/** minimal constructor */
	public TmLoanReturnDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLoanReturnDetail(Long id, Long loanReturnId,
			Long loanRegdetailId, String loanRegBill,
			Double lackReturnQuantity, Double returnQuantity, Double costPrice,
			Long isReturn) {
		this.id = id;
		this.loanRegBill = loanRegBill;
		this.lackReturnQuantity = lackReturnQuantity;
		this.returnQuantity = returnQuantity;
		this.costPrice = costPrice;
		this.isReturn = isReturn;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanRegBill() {
		return this.loanRegBill;
	}

	public void setLoanRegBill(String loanRegBill) {
		this.loanRegBill = loanRegBill;
	}

	public Double getLackReturnQuantity() {
		return this.lackReturnQuantity;
	}

	public void setLackReturnQuantity(Double lackReturnQuantity) {
		this.lackReturnQuantity = lackReturnQuantity;
	}

	public Double getReturnQuantity() {
		return this.returnQuantity;
	}

	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Long getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(Long isReturn) {
		this.isReturn = isReturn;
	}
	
	private String houseName;
	
	private String partCode;
	
	private String partName;
	
	private String storeLocation;
	
	private String unitName;
	
	private Double total;


	public String getHouseName() {
		return this.tmLoanRegisterDetail.getTbPartInfo().getTmStoreHouse().getHouseName();
	}

	public String getPartCode() {
		return this.tmLoanRegisterDetail.getTbPartInfo().getPartCode();
	}

	public String getPartName() {
		return this.tmLoanRegisterDetail.getTbPartInfo().getPartName();
	}

	public String getStoreLocation() {
		return this.tmLoanRegisterDetail.getTbPartInfo().getStoreLocation();
	}

	public String getUnitName() {
		return  this.tmLoanRegisterDetail.getTbPartInfo().getTmUnit().getUnitName();
	}

	public Double getTotal() {
		return CommonMethod.convertRadixPoint(this.costPrice * this.returnQuantity, 2);
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}