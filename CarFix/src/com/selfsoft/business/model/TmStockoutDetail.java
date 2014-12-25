package com.selfsoft.business.model;

/**
 * TmStockoutDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmStockoutDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long partinfoId;
	private Long stockoutId;
	private Double quantity;
	private Double price;
	private Long stockInDetailId;				//采购退货
	
	private Long isFree;						//是否免费
	private Long balanceId;						//结算ID
	private String zl;							//账类
	private String xmlx;						//项目类型
	private String projectType;					//维修项目类型

	// Constructors

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

	public Long getStockInDetailId() {
		return stockInDetailId;
	}

	public void setStockInDetailId(Long stockInDetailId) {
		this.stockInDetailId = stockInDetailId;
	}

	/** default constructor */
	public TmStockoutDetail() {
	}

	/** minimal constructor */
	public TmStockoutDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmStockoutDetail(Long id, Long partinfoId, Long stockoutId,
			Double quantity, Double price) {
		this.id = id;
		this.partinfoId = partinfoId;
		this.stockoutId = stockoutId;
		this.quantity = quantity;
		this.price = price;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartinfoId() {
		return this.partinfoId;
	}

	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}

	public Long getStockoutId() {
		return this.stockoutId;
	}

	public void setStockoutId(Long stockoutId) {
		this.stockoutId = stockoutId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}