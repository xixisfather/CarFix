package com.selfsoft.baseinformation.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.business.model.TmLianceRegisterDetail;
import com.selfsoft.business.model.TmLoanRegisterDetail;
import com.selfsoft.framework.common.CommonMethod;

/**
 * TbPartInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbPartInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private TmStoreHouse tmStoreHouse;			//仓库fk
	private TmCarModelType tmCarModelType;		//车辆类型fk
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private String pinyinCode;					//拼音代码
	private TmUnit tmUnit;						//计量单位fk
	private TmPartType tmPartType;				//配件类型fk
	private String partBroadType;				//配件大类
	private String storeLocation;				//仓位
	private String factoryCode;					//制造厂号
	private String dangerCode;					//危险品号
	private Double maxStoreQuantity;			//最大库存
	private Double minStoreQuantity;			//最小库存
	private Double storeQuantity;				//库存数量
	private Double costPrice;					//成本价
	private Double stockPrice;					//进货价(记录最后一次的进货价)
	private Double stockMoney;					//库存金额

	private Double lianceQuantity;				//借进量 
	private Double loanQuantity;				//借出量
	
	private Double sellPrice;					//销售价
	private Set tbBookFixParts = new HashSet(); //维修预约配件
	private Set tbBespokePartContents = new HashSet();			//委托预约配件
	
	private Set<TmLianceRegisterDetail> lianceRegDets = new HashSet<TmLianceRegisterDetail>();			//借进登记
	private Set<TmLoanRegisterDetail> loanRegDets = new HashSet<TmLoanRegisterDetail>();				//借出登记
	
	private Date lastModifyDate;				//最后修改日期
	
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Set<TmLianceRegisterDetail> getLianceRegDets() {
		return lianceRegDets;
	}

	public void setLianceRegDets(Set<TmLianceRegisterDetail> lianceRegDets) {
		this.lianceRegDets = lianceRegDets;
	}

	/** default constructor */
	public TbPartInfo() {
	}

	/** minimal constructor */
	public TbPartInfo(Long id) {
		this.id = id;
	}
	/** full constructor */
	public TbPartInfo(Long id, TmStoreHouse tmStoreHouse,
			TmCarModelType tmCarModelType, String partCode, String partName,
			String pinyinCode, TmUnit tmUnit, TmPartType tmPartType,
			String partBroadType, String storeLocation, String factoryCode,
			String dangerCode, Double maxStoreQuantity,
			Double minStoreQuantity, Double storeQuantity) {
		super();
		this.id = id;
		this.tmStoreHouse = tmStoreHouse;
		this.tmCarModelType = tmCarModelType;
		this.partCode = partCode;
		this.partName = partName;
		this.pinyinCode = pinyinCode;
		this.tmUnit = tmUnit;
		this.tmPartType = tmPartType;
		this.partBroadType = partBroadType;
		this.storeLocation = storeLocation;
		this.factoryCode = factoryCode;
		this.dangerCode = dangerCode;
		this.maxStoreQuantity = maxStoreQuantity;
		this.minStoreQuantity = minStoreQuantity;
		this.storeQuantity = storeQuantity;
	}

	
	// Property accessors
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TmStoreHouse getTmStoreHouse() {
		return tmStoreHouse;
	}

	public void setTmStoreHouse(TmStoreHouse tmStoreHouse) {
		this.tmStoreHouse = tmStoreHouse;
	}

	public TmCarModelType getTmCarModelType() {
		return tmCarModelType;
	}

	public void setTmCarModelType(TmCarModelType tmCarModelType) {
		this.tmCarModelType = tmCarModelType;
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

	public TmUnit getTmUnit() {
		return tmUnit;
	}

	public void setTmUnit(TmUnit tmUnit) {
		this.tmUnit = tmUnit;
	}

	public TmPartType getTmPartType() {
		return tmPartType;
	}

	public void setTmPartType(TmPartType tmPartType) {
		this.tmPartType = tmPartType;
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
	
	private String houseName; 
	
	private String modelName;

	private String unitName;
	
	private String typeName;

	private Double quantity;
	
	private String xlsPK;
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getHouseName() {
		if(this.tmStoreHouse != null)
			return this.tmStoreHouse.getHouseName();
		return null;
	}

	public String getModelName() {
		if(this.tmCarModelType != null)
			return this.tmCarModelType.getModelName();
		return null;
	}

	public String getUnitName() {
		if(this.tmUnit != null)
			return this.tmUnit.getUnitName();
		return null;
	}

	public String getTypeName() {
		if(this.tmPartType != null)
			return this.tmPartType.getTypeName();
		return null;
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

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Set getTbBookFixParts() {
		return tbBookFixParts;
	}

	public void setTbBookFixParts(Set tbBookFixParts) {
		this.tbBookFixParts = tbBookFixParts;
	}

	public Set<TmLoanRegisterDetail> getLoanRegDets() {
		return loanRegDets;
	}

	public void setLoanRegDets(Set<TmLoanRegisterDetail> loanRegDets) {
		this.loanRegDets = loanRegDets;
	}

	public Set getTbBespokePartContents() {
		return tbBespokePartContents;
	}

	public void setTbBespokePartContents(Set tbBespokePartContents) {
		this.tbBespokePartContents = tbBespokePartContents;
	}
	
	private Double minPrice;
	
	private Double maxPrice;

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	private Double factPrice;

	public Double getFactPrice() {
		return CommonMethod.convertRadixPoint(this.costPrice*this.storeQuantity,2);
	}
	
	public void setFactPrice(Double factPrice) {
		this.factPrice = factPrice;
	}
	
	private BigDecimal pageCostPrice;

	public BigDecimal getPageCostPrice() {
		
		if(null != this.costPrice){
			
			pageCostPrice = new BigDecimal(this.costPrice).multiply(new BigDecimal(1.17)).setScale(0, BigDecimal.ROUND_HALF_UP);
			
			
		}
		
		return pageCostPrice;
	}

	public void setPageCostPrice(BigDecimal pageCostPrice) {
		this.pageCostPrice = pageCostPrice;
	}

	
}