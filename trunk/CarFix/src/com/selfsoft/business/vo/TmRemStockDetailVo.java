package com.selfsoft.business.vo;

import com.selfsoft.framework.common.CommonMethod;

public class TmRemStockDetailVo {

	private Long removeStockDetailId;			//移库出仓明细id
	private Long removeStockId;					//移库出仓id
	private Long partinfoId;					//配件id
	private Double quantity;					//移出数量
	private Double costPrice;					//成本价
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private Long houseId;						//仓库ID
	private String houseCode;					//仓库代码
	private String houseName;					//仓库名称
	private String unitName;					//计量单位	
	private Double price;						//成本金额小计
	private Double storeQuantity;				//配件库存
	private String storeLocation;				//仓位
	
	
	public Double getStoreQuantity() {
		return storeQuantity;
	}
	public void setStoreQuantity(Double storeQuantity) {
		this.storeQuantity = storeQuantity;
	}
	public Long getRemoveStockDetailId() {
		return removeStockDetailId;
	}
	public void setRemoveStockDetailId(Long removeStockDetailId) {
		this.removeStockDetailId = removeStockDetailId;
	}
	public Long getRemoveStockId() {
		return removeStockId;
	}
	public void setRemoveStockId(Long removeStockId) {
		this.removeStockId = removeStockId;
	}
	public Long getPartinfoId() {
		return partinfoId;
	}
	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
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
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public TmRemStockDetailVo(Long removeStockDetailId, Long removeStockId,
			Long partinfoId, Double quantity, Double costPrice,
			String partCode, String partName, Long houseId, String houseCode,
			String houseName, String unitName,Double price,Double storeQuantity,
			String storeLocation) {
		super();
		this.removeStockDetailId = removeStockDetailId;
		this.removeStockId = removeStockId;
		this.partinfoId = partinfoId;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.partCode = partCode;
		this.partName = partName;
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.unitName = unitName;
		this.price = price;
		this.storeQuantity = storeQuantity;
		this.storeLocation = storeLocation;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	private Double total;


	public Double getTotal() {
		return CommonMethod.convertRadixPoint(this.costPrice * this.quantity , 2);
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	
}
