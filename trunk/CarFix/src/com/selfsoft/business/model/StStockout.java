package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.framework.common.StockTypeElements;

/**
 * StStockout entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StStockout implements java.io.Serializable {

	// Fields

	private Long id;
	private String houseName;
	private String partCode;
	private String partName;
	private String stockoutCode;
	private String stockoutType;
	private Double quantity;
	private Double costPrice;
	private Double sellPrice;
	private Date stockOutDate;

	// Constructors

	/** default constructor */
	public StStockout() {
	}

	/** minimal constructor */
	public StStockout(Long id) {
		this.id = id;
	}

	/** full constructor */
	public StStockout(Long id, String houseName, String partCode,
			String partName, String stockoutCode, String stockoutType,
			Double quantity, Double costPrice, Double sellPrice,
			Date stockOutDate) {
		this.id = id;
		this.houseName = houseName;
		this.partCode = partCode;
		this.partName = partName;
		this.stockoutCode = stockoutCode;
		this.stockoutType = stockoutType;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.stockOutDate = stockOutDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseName() {
		return this.houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getPartCode() {
		return this.partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getStockoutCode() {
		return this.stockoutCode;
	}

	public void setStockoutCode(String stockoutCode) {
		this.stockoutCode = stockoutCode;
	}

	public String getStockoutType() {
		return this.stockoutType;
	}

	public void setStockoutType(String stockoutType) {
		this.stockoutType = stockoutType;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Date getStockOutDate() {
		return this.stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}
	
	private String beginStockOutDate;
	
	private String endStockOutDate;

	public String getBeginStockOutDate() {
		return beginStockOutDate;
	}

	public void setBeginStockOutDate(String beginStockOutDate) {
		this.beginStockOutDate = beginStockOutDate;
	}

	public String getEndStockOutDate() {
		return endStockOutDate;
	}

	public void setEndStockOutDate(String endStockOutDate) {
		this.endStockOutDate = endStockOutDate;
	}
	
	
	private Double totalQuantity;
	
	private Double totalSellPrice;
	
	private Double totalCostPrice;
	
	private Long partCategory;
	
	
	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(Double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public Double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(Double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}

	public Long getPartCategory() {
		return partCategory;
	}

	public void setPartCategory(Long partCategory) {
		this.partCategory = partCategory;
	}


}