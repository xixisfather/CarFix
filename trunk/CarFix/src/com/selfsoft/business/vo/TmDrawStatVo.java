package com.selfsoft.business.vo;

import java.util.Date;

public class TmDrawStatVo {

	private String stockOutCode;
	
	private Date stockOutDate;
	
	private String houseName;
	
	private String partCode;
	
	private String partName;
	
	private String unitName;
	
	private Double costPrice;
	
	private Double sellPrice;
	
	private Double quantity;
	
	private String drawPeopleName;
	
	private String deptName;
	
	private String beginDate;
	
	private String endDate;
	
	private Long drawPeopleId;
	
	private Long deptId;

	public Long getDrawPeopleId() {
		return drawPeopleId;
	}

	public void setDrawPeopleId(Long drawPeopleId) {
		this.drawPeopleId = drawPeopleId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getStockOutCode() {
		return stockOutCode;
	}

	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}

	public Date getStockOutDate() {
		return stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getDrawPeopleName() {
		return drawPeopleName;
	}

	public void setDrawPeopleName(String drawPeopleName) {
		this.drawPeopleName = drawPeopleName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	private Long querySelect;

	private Double totalCostPrice;
	
	private Double totalSellPrice;
	
	public Long getQuerySelect() {
		return querySelect;
	}

	public void setQuerySelect(Long querySelect) {
		this.querySelect = querySelect;
	}

	public Double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(Double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}

	public Double getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(Double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}
	
	
}
