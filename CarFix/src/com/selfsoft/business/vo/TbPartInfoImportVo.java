package com.selfsoft.business.vo;

public class TbPartInfoImportVo {

	private String xlsPK;
	private String storeHouseCode;				//仓库代码
	private String carModelTypeCode;			//车辆类型
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private String unitName;					//计量单位
	private String partTypeCode;					//配件类型
	private String partBroadType;				//配件大类
	private String storeLocation;				//仓位
	private String factoryCode;					//制造厂号
	private String dangerCode;					//危险品号
	private Double maxStoreQuantity = 0D;		//最大库存
	private Double minStoreQuantity = 0D;		//最小库存
	private Double storeQuantity = 0D;			//库存数量
	private Double costPrice;					//成本价
	private Double stockPrice = 0D;				//进货价(记录最后一次的进货价)
	private Double stockMoney = 0D;				//库存金额

	private Double lianceQuantity = 0D;			//借进量 
	private Double loanQuantity = 0D;			//借出量
	
	private Double sellPrice;
	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getStoreHouseCode() {
		return storeHouseCode;
	}
	
	public String getCarModelTypeCode() {
		return carModelTypeCode;
	}

	public void setCarModelTypeCode(String carModelTypeCode) {
		this.carModelTypeCode = carModelTypeCode;
	}

	public void setStoreHouseCode(String storeHouseCode) {
		this.storeHouseCode = storeHouseCode;
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
	public String getPartTypeCode() {
		return partTypeCode;
	}

	public void setPartTypeCode(String partTypeCode) {
		this.partTypeCode = partTypeCode;
	}

	public String getPartBroadType() {
		return partBroadType;
	}
	public void setPartBroadType(String partBroadType) {
		this.partBroadType = partBroadType;
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public String getDangerCode() {
		return dangerCode;
	}
	public void setDangerCode(String dangerCode) {
		this.dangerCode = dangerCode;
	}
	public Double getMaxStoreQuantity() {
		return maxStoreQuantity;
	}
	public void setMaxStoreQuantity(Double maxStoreQuantity) {
		this.maxStoreQuantity = maxStoreQuantity;
	}
	public Double getMinStoreQuantity() {
		return minStoreQuantity;
	}
	public void setMinStoreQuantity(Double minStoreQuantity) {
		this.minStoreQuantity = minStoreQuantity;
	}
	public Double getStoreQuantity() {
		return storeQuantity;
	}
	public void setStoreQuantity(Double storeQuantity) {
		this.storeQuantity = storeQuantity;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public Double getStockMoney() {
		return stockMoney;
	}
	public void setStockMoney(Double stockMoney) {
		this.stockMoney = stockMoney;
	}
	public Double getLianceQuantity() {
		return lianceQuantity;
	}
	public void setLianceQuantity(Double lianceQuantity) {
		this.lianceQuantity = lianceQuantity;
	}
	public Double getLoanQuantity() {
		return loanQuantity;
	}
	public void setLoanQuantity(Double loanQuantity) {
		this.loanQuantity = loanQuantity;
	}

	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
}
