package com.selfsoft.baseinformation.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.business.model.TmLianceRegisterDetail;
import com.selfsoft.business.model.TmLoanRegisterDetail;

/**
 * TbPartInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbPartInfoVo implements java.io.Serializable {

	// Fields


	private Long id;
	private Long tmStoreHouseId;				//仓库fk
	private String houseCode;					//仓库代码
	private String houseName;					//仓库名称
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private String pinyinCode;					//拼音代码
	private String unitName;					//计量单位
	private String typeName; 					//配件类型
	private Double storeQuantity;				//库存数量
	private Double costPrice;					//成本价
	private Double sellPrice;					//默认销售价
	private Double customSellPrice;				//客户销售价
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTmStoreHouseId() {
		return tmStoreHouseId;
	}
	public void setTmStoreHouseId(Long tmStoreHouseId) {
		this.tmStoreHouseId = tmStoreHouseId;
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
	public String getPinyinCode() {
		return pinyinCode;
	}
	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Double getCustomSellPrice() {
		return customSellPrice;
	}
	public void setCustomSellPrice(Double customSellPrice) {
		this.customSellPrice = customSellPrice;
	}
	public TbPartInfoVo(Long id, Long tmStoreHouseId, String houseCode,
			String houseName, String partCode, String partName,
			String pinyinCode, String unitName, Double storeQuantity,
			Double costPrice, Double sellPrice, Double customSellPrice) {
		super();
		this.id = id;
		this.tmStoreHouseId = tmStoreHouseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.partCode = partCode;
		this.partName = partName;
		this.pinyinCode = pinyinCode;
		this.unitName = unitName;
		this.storeQuantity = storeQuantity;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.customSellPrice = customSellPrice;
	}
	
	public TbPartInfoVo(){}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}