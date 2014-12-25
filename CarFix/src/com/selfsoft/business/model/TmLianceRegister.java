package com.selfsoft.business.model;

import java.util.Date;

/**
 * TmLianceRegister entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLianceRegister implements java.io.Serializable {

	// Fields

	private Long id;
	private String lianceBill;
	private Long supplierId;
	private Date lianceDate;
	private Long userId;
	private Date createDate;
	private Long dutyPeople;
	private Double totalPrice;
	private Double totalQuantity;
	private Long isConfirm;
	private Long isReturn;

	// Constructors

	/** default constructor */
	public TmLianceRegister() {
	}

	/** minimal constructor */
	public TmLianceRegister(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLianceRegister(Long id, String lianceBill, Long supplierId,
			Date lianceDate, Long userId, Date createDate,
			Long dutyPeople, Double totalPrice, Double totalQuantity,
			Long isConfirm, Long isReturn) {
		this.id = id;
		this.lianceBill = lianceBill;
		this.supplierId = supplierId;
		this.lianceDate = lianceDate;
		this.userId = userId;
		this.createDate = createDate;
		this.dutyPeople = dutyPeople;
		this.totalPrice = totalPrice;
		this.totalQuantity = totalQuantity;
		this.isConfirm = isConfirm;
		this.isReturn = isReturn;
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

	public Long getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public Date getLianceDate() {
		return this.lianceDate;
	}

	public void setLianceDate(Date lianceDate) {
		this.lianceDate = lianceDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getDutyPeople() {
		return this.dutyPeople;
	}

	public void setDutyPeople(Long dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalQuantity() {
		return this.totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Long getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Long getIsReturn() {
		return this.isReturn;
	}

	public void setIsReturn(Long isReturn) {
		this.isReturn = isReturn;
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

}