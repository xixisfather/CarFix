package com.selfsoft.business.vo;

import java.util.Date;

import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class DailyStockOutVo {
	
	private String houseName;

	private String partCode;

	private String partName;
	
	private String stockOutCode;
	
	private Long outType;
	
	private Double costPrice;
	
	private Double sellPrice;
	
	private Double quantity;
	
	private String outTypeName;
	
	private String beginDate;
	
	private String endDate;
	
	private Long houseId;
	
	private Date stockOutDate;
	
	private Long isFree;
	
	private Long partInfoId;
	
	private Double totalQuantity;
	
	private Double totalSellPrice;
	
	private Double totalCostPrice;
	
	private Long partCategory;
	
	private Long TbFixEntrustId;
	
	private String serviceUserName;
	
	private Long serviceUserId;

	public Long getPartInfoId() {
		return partInfoId;
	}

	public void setPartInfoId(Long partInfoId) {
		this.partInfoId = partInfoId;
	}

	public Long getIsFree() {
		return isFree;
	}

	public void setIsFree(Long isFree) {
		this.isFree = isFree;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
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

	public String getStockOutCode() {
		return stockOutCode;
	}

	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}

	public Long getOutType() {
		return outType;
	}

	public void setOutType(Long outType) {
		this.outType = outType;
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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getOutTypeName() {
		return StockTypeElements.getElementMap().get(this.outType);
	}

	public void setOutTypeName(String outTypeName) {
		this.outTypeName = outTypeName;
	}

	public Date getStockOutDate() {
		return stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}

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
	
	
	/* 假单导出属性  */
	private String xlsPK;
	
	/* 假单导出属性  */
	private String xlsStockOutTypeName;
	
	/* 假单导出属性  */
	private String xlsStockOutDate;
	

	public String getXlsStockOutTypeName() {
		return xlsStockOutTypeName;
	}

	public void setXlsStockOutTypeName(String xlsStockOutTypeName) {
		this.xlsStockOutTypeName = xlsStockOutTypeName;
	}

	public String getXlsStockOutDate() {
		return xlsStockOutDate;
	}

	public void setXlsStockOutDate(String xlsStockOutDate) {
		this.xlsStockOutDate = xlsStockOutDate;
	}

	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
	
	public String getFreeName() {
		
		if(this.isFree != null){
			
			if(this.isFree.equals(Constants.FREESYMBOL_NO))
				return Constants.FREESYMBOL_NO_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FG))
				return Constants.FREESYMBOL_FG_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FW))
				return Constants.FREESYMBOL_FW_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SB))
				return Constants.FREESYMBOL_SB_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SP))
				return Constants.FREESYMBOL_SP_SHOW;
			
			
		}
		
		
		return null;
	}

	private String createUserName;
	
	private String drowUserName;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getDrowUserName() {
		return drowUserName;
	}

	public void setDrowUserName(String drowUserName) {
		this.drowUserName = drowUserName;
	}

	public Long getTbFixEntrustId() {
		return TbFixEntrustId;
	}

	public void setTbFixEntrustId(Long tbFixEntrustId) {
		TbFixEntrustId = tbFixEntrustId;
	}

	public String getServiceUserName() {
		return serviceUserName;
	}

	public void setServiceUserName(String serviceUserName) {
		this.serviceUserName = serviceUserName;
	}

	public Long getServiceUserId() {
		return serviceUserId;
	}

	public void setServiceUserId(Long serviceUserId) {
		this.serviceUserId = serviceUserId;
	}
	
}
