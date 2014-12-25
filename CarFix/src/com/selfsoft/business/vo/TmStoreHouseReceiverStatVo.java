package com.selfsoft.business.vo;

public class TmStoreHouseReceiverStatVo {

	private String beginDate;
	
	private String endDate;
	
	private Long storeHouseId;
	
	private String inOutTypeName;
	
	private Long inOutType;
	
	private String storeHouseName ;
	
	private Double stockInCostPrice;
	
	private Double stockOutCostPrice;
	
	private Double sellCostPrice;
	
	private Double qcQuantity;
	
	private Double qmQuantity;
	
	private Double qcPrice;
	
	private Double qmPrice;

	public Double getQcQuantity() {
		return qcQuantity;
	}

	public void setQcQuantity(Double qcQuantity) {
		this.qcQuantity = qcQuantity;
	}

	public Double getQmQuantity() {
		return qmQuantity;
	}

	public void setQmQuantity(Double qmQuantity) {
		this.qmQuantity = qmQuantity;
	}

	public Double getQcPrice() {
		return qcPrice;
	}

	public void setQcPrice(Double qcPrice) {
		this.qcPrice = qcPrice;
	}

	public Double getQmPrice() {
		return qmPrice;
	}

	public void setQmPrice(Double qmPrice) {
		this.qmPrice = qmPrice;
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

	public Long getStoreHouseId() {
		return storeHouseId;
	}

	public void setStoreHouseId(Long storeHouseId) {
		this.storeHouseId = storeHouseId;
	}

	public String getInOutTypeName() {
		return inOutTypeName;
	}

	public void setInOutTypeName(String inOutTypeName) {
		this.inOutTypeName = inOutTypeName;
	}

	public Long getInOutType() {
		return inOutType;
	}

	public void setInOutType(Long inOutType) {
		this.inOutType = inOutType;
	}

	public String getStoreHouseName() {
		return storeHouseName;
	}

	public void setStoreHouseName(String storeHouseName) {
		this.storeHouseName = storeHouseName;
	}

	public Double getStockInCostPrice() {
		return stockInCostPrice;
	}

	public void setStockInCostPrice(Double stockInCostPrice) {
		this.stockInCostPrice = stockInCostPrice;
	}

	public Double getStockOutCostPrice() {
		return stockOutCostPrice;
	}

	public void setStockOutCostPrice(Double stockOutCostPrice) {
		this.stockOutCostPrice = stockOutCostPrice;
	}

	public Double getSellCostPrice() {
		return sellCostPrice;
	}

	public void setSellCostPrice(Double sellCostPrice) {
		this.sellCostPrice = sellCostPrice;
	}
	
	
	
	
	
}
