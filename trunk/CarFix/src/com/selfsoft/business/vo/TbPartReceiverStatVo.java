package com.selfsoft.business.vo;

public class TbPartReceiverStatVo {

	private String beginDate;
	
	private String endDate;
	
	private Long storeHouseId;
	
	private Long carModelTypeId;
	
	
	private String storeHouseName ;
	
	private String partCode;
	
	private Double storeQuantity;
	
	private Double storePrice;
	
	private String partName;
	
	private Double stockInQuantity;
	
	private Double stockOutQuantity;
	
	private Double stockInPrice;
	
	private Double stockOutPrice;
	
	
	private Double totalStockInQuantity;
	
	private Double totalStockInPrice;
	
	private Double totalStockOutQuantity;
	
	private Double totalStockOutPrice;
	
	private Long tbPartInfoId;
	
	
	private Double qcQuantity;
	
	private Double qcPrice;
	
	private Double qmQuantity;
	
	private Double qmPrice;
	
	private Double sellStockOutPrice;
	
	
	private Double totalQcPrice ;
	
	private Double totalQmPrice ;
	
	
	public Double getSellStockOutPrice() {
		return sellStockOutPrice;
	}

	public void setSellStockOutPrice(Double sellStockOutPrice) {
		this.sellStockOutPrice = sellStockOutPrice;
	}



	public Double getQcQuantity() {
		return qcQuantity;
	}

	public void setQcQuantity(Double qcQuantity) {
		this.qcQuantity = qcQuantity;
	}

	public Double getQcPrice() {
		return qcPrice;
	}

	public void setQcPrice(Double qcPrice) {
		this.qcPrice = qcPrice;
	}

	public Long getTbPartInfoId() {
		return tbPartInfoId;
	}

	public void setTbPartInfoId(Long tbPartInfoId) {
		this.tbPartInfoId = tbPartInfoId;
	}

	public String getStoreHouseName() {
		return storeHouseName;
	}

	public void setStoreHouseName(String storeHouseName) {
		this.storeHouseName = storeHouseName;
	}


	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public Double getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(Double storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public Double getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(Double storePrice) {
		this.storePrice = storePrice;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Double getStockInQuantity() {
		return stockInQuantity;
	}

	public void setStockInQuantity(Double stockInQuantity) {
		this.stockInQuantity = stockInQuantity;
	}

	public Double getStockOutQuantity() {
		return stockOutQuantity;
	}

	public void setStockOutQuantity(Double stockOutQuantity) {
		this.stockOutQuantity = stockOutQuantity;
	}

	public Double getStockInPrice() {
		return stockInPrice;
	}

	public void setStockInPrice(Double stockInPrice) {
		this.stockInPrice = stockInPrice;
	}

	public Double getStockOutPrice() {
		return stockOutPrice;
	}

	public void setStockOutPrice(Double stockOutPrice) {
		this.stockOutPrice = stockOutPrice;
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

	public Long getCarModelTypeId() {
		return carModelTypeId;
	}

	public void setCarModelTypeId(Long carModelTypeId) {
		this.carModelTypeId = carModelTypeId;
	}

	public Double getTotalStockInQuantity() {
		return totalStockInQuantity;
	}

	public void setTotalStockInQuantity(Double totalStockInQuantity) {
		this.totalStockInQuantity = totalStockInQuantity;
	}

	public Double getTotalStockInPrice() {
		return totalStockInPrice;
	}

	public void setTotalStockInPrice(Double totalStockInPrice) {
		this.totalStockInPrice = totalStockInPrice;
	}

	public Double getTotalStockOutQuantity() {
		return totalStockOutQuantity;
	}

	public void setTotalStockOutQuantity(Double totalStockOutQuantity) {
		this.totalStockOutQuantity = totalStockOutQuantity;
	}

	public Double getTotalStockOutPrice() {
		return totalStockOutPrice;
	}

	public void setTotalStockOutPrice(Double totalStockOutPrice) {
		this.totalStockOutPrice = totalStockOutPrice;
	} 
	
	/* 假单导出属性  */
	private String xlsPK;

	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}

	public Double getQmQuantity() {
		return qmQuantity;
	}

	public void setQmQuantity(Double qmQuantity) {
		this.qmQuantity = qmQuantity;
	}

	public Double getQmPrice() {
		return qmPrice;
	}

	public void setQmPrice(Double qmPrice) {
		this.qmPrice = qmPrice;
	}

	public Double getTotalQcPrice() {
		return totalQcPrice;
	}

	public void setTotalQcPrice(Double totalQcPrice) {
		this.totalQcPrice = totalQcPrice;
	}

	public Double getTotalQmPrice() {
		return totalQmPrice;
	}

	public void setTotalQmPrice(Double totalQmPrice) {
		this.totalQmPrice = totalQmPrice;
	}
}
