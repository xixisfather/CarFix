package com.selfsoft.business.vo;

public class TbPartInfoStockOutVo {

	private String partName;			//配件名称
	
	private String modelName;			//车型名称
	
	private String storeLocation;		//仓位
	
	private Double costPrice;			//成本价
	
	private Double storeQuantity;		//库存
	
	private Double sellQuantity;		//销售量
	
	private Double sellPrice;			//销售金额
	
	private Double costXj;				//成本小计
	
	private Double gain;				//利润
	
	private Double totalSellPrice;		//销售总金额
	
	private Double totalGain;			//总利润
	
	private Double totalStoreQuantity;	//库存总量
	
	private Double totalCostPrice;		//库存成本

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(Double storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getCostXj() {
		return costXj;
	}

	public void setCostXj(Double costXj) {
		this.costXj = costXj;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	public Double getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(Double totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public Double getTotalGain() {
		return totalGain;
	}

	public void setTotalGain(Double totalGain) {
		this.totalGain = totalGain;
	}

	public Double getTotalStoreQuantity() {
		return totalStoreQuantity;
	}

	public void setTotalStoreQuantity(Double totalStoreQuantity) {
		this.totalStoreQuantity = totalStoreQuantity;
	}

	public Double getTotalCostPrice() {
		return totalCostPrice;
	}

	public void setTotalCostPrice(Double totalCostPrice) {
		this.totalCostPrice = totalCostPrice;
	}
	
	
	
	private String beginDate;
	
	private String endDate;
	
	private Long storeHouseId;
	
	private Long carModelTypeId;
	
	private Long stockOutType;
	
	private Long orderByType;

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

	public Long getStockOutType() {
		return stockOutType;
	}

	public void setStockOutType(Long stockOutType) {
		this.stockOutType = stockOutType;
	}

	public Long getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(Long orderByType) {
		this.orderByType = orderByType;
	}

	public Long getCarModelTypeId() {
		return carModelTypeId;
	}

	public void setCarModelTypeId(Long carModelTypeId) {
		this.carModelTypeId = carModelTypeId;
	}
	
	
	
	
}
