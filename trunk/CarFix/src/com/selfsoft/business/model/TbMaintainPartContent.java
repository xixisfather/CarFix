package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.framework.common.Constants;

/**
 * TbMaintainPartContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbMaintainPartContent implements java.io.Serializable {

	// Fields

	private Long id;
	private Long partId;
	private Double partQuantity;
	private Double price;
	private Double totalPrice;
	private Long cliamPartPersonId;
	private Long entrustId;
	private String maintainCode;
	private Long isConfirm;
	
	private Long isFree;								//是否免费
	private Long balanceId;								//结算ID
	private Date stockOutDate;							//出库日期
	
	private String zl;									//账类
	private String xmlx;								//项目类型
	private String projectType;							//维修项目类型
	
	
	private String zlShow;
	
	private String xmlxShow;
	
	private String isPrint;
	
	// Constructors


	public String getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}

	public Date getStockOutDate() {
		return stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}

	public Long getIsFree() {
		return isFree;
	}

	public void setIsFree(Long isFree) {
		this.isFree = isFree;
	}

	public Long getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}

	/** default constructor */
	public TbMaintainPartContent() {
	}

	/** minimal constructor */
	public TbMaintainPartContent(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TbMaintainPartContent(Long id, Long partId,
			Double partQuantity, Double price, Double totalPrice,
			Long cliamPartPersonId, Long entrustId) {
		this.id = id;
		this.partId = partId;
		this.partQuantity = partQuantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.cliamPartPersonId = cliamPartPersonId;
		this.entrustId = entrustId;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartId() {
		return this.partId;
	}

	public void setPartId(Long partId) {
		this.partId = partId;
	}

	public Double getPartQuantity() {
		return this.partQuantity;
	}

	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getCliamPartPersonId() {
		return this.cliamPartPersonId;
	}

	public void setCliamPartPersonId(Long cliamPartPersonId) {
		this.cliamPartPersonId = cliamPartPersonId;
	}

	public Long getEntrustId() {
		return this.entrustId;
	}

	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}

	public String getMaintainCode() {
		return maintainCode;
	}

	public void setMaintainCode(String maintainCode) {
		this.maintainCode = maintainCode;
	}

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	
	private String beginDate;
	
	private String endDate;
	
	private String partCode;
	
	private String partName;

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
	
	private String userRealName;

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getZlShow() {
		return Constants.zlMap().get(this.getZl());
	}

	public void setZlShow(String zlShow) {
		this.zlShow = zlShow;
	}

	public String getXmlxShow() {
		return Constants.xmlxMap().get(this.getXmlx());
	}

	public void setXmlxShow(String xmlxShow) {
		this.xmlxShow = xmlxShow;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}
}