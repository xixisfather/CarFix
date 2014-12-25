package com.selfsoft.business.vo;

import java.util.Date;

import com.selfsoft.framework.common.CommonMethod;

public class TmLianceRegDetailVo {

	private Long lianceRegId;					//借进登记ID
	private Long lianceRegDtlId;				//借进登记详细ID
	private String lianceBill;					//借进登记单号
	private Date lianceDate;					//借进日期
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private String unitName;					//单位
	private Double lianceQuantity;				//借进数量
	private Double liancePrice;					//借进价格
	private Double oldCostPrice;				//当时成本价
	private Double returnQuantity;				//已还数量
	private String houseName;					//仓库
	private Double nowCosePrice;				//现成本价
	private Long partinfoId;					//配件ID
	private String storeLocation;				//仓位
	
	
	public TmLianceRegDetailVo() {
		super();
	}
	
	public TmLianceRegDetailVo(Long lianceRegId, Long lianceRegDtlId,
			String lianceBill, Date lianceDate, String partCode,
			String partName, String unitName, Double lianceQuantity,
			Double liancePrice, Double oldCostPrice, Double returnQuantity,
			String houseName,Double nowCosePrice,Long partinfoId,
			String storeLocation) {
		super();
		this.lianceRegId = lianceRegId;
		this.lianceRegDtlId = lianceRegDtlId;
		this.lianceBill = lianceBill;
		this.lianceDate = lianceDate;
		this.partCode = partCode;
		this.partName = partName;
		this.unitName = unitName;
		this.lianceQuantity = lianceQuantity;
		this.liancePrice = liancePrice;
		this.oldCostPrice = oldCostPrice;
		this.returnQuantity = returnQuantity;
		this.houseName = houseName;
		this.nowCosePrice = nowCosePrice;
		this.partinfoId = partinfoId;
		this.storeLocation = storeLocation;
	}


	public Long getLianceRegDtlId() {
		return lianceRegDtlId;
	}

	public void setLianceRegDtlId(Long lianceRegDtlId) {
		this.lianceRegDtlId = lianceRegDtlId;
	}

	public Long getLianceRegId() {
		return lianceRegId;
	}
	public void setLianceRegId(Long lianceRegId) {
		this.lianceRegId = lianceRegId;
	}
	public String getLianceBill() {
		return lianceBill;
	}
	public void setLianceBill(String lianceBill) {
		this.lianceBill = lianceBill;
	}
	public Date getLianceDate() {
		return lianceDate;
	}
	public void setLianceDate(Date lianceDate) {
		this.lianceDate = lianceDate;
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
	public Double getLianceQuantity() {
		return lianceQuantity;
	}
	public void setLianceQuantity(Double lianceQuantity) {
		this.lianceQuantity = lianceQuantity;
	}
	public Double getLiancePrice() {
		return liancePrice;
	}
	public void setLiancePrice(Double liancePrice) {
		this.liancePrice = liancePrice;
	}
	public Double getOldCostPrice() {
		return oldCostPrice;
	}
	public void setOldCostPrice(Double oldCostPrice) {
		this.oldCostPrice = oldCostPrice;
	}
	public Double getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Double returnQuantity) {
		this.returnQuantity = returnQuantity;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Double getNowCosePrice() {
		return nowCosePrice;
	}

	public void setNowCosePrice(Double nowCosePrice) {
		this.nowCosePrice = nowCosePrice;
	}

	public Long getPartinfoId() {
		return partinfoId;
	}

	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}
	
	private Double total;


	public Double getTotal() {
		return CommonMethod.convertRadixPoint(this.liancePrice * this.lianceQuantity , 2);
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	
	
}
