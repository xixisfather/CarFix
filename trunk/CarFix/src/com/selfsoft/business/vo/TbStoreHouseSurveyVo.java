package com.selfsoft.business.vo;

public class TbStoreHouseSurveyVo {

	private String houseName;
	
	private Long partInfoCategory;
	
	private Long zeroCategory;
	
	private Double costPrice;
	
	private Double sellPrice;
	
	private Double liancePrice;
	
	private Double loanPrice;
	
	private String modelTypeName;
	
	private Long totalPartInfoCategory;
	
	private Long totalZeroCategory;
	
	private Double totalCostPrice;
	
	private Double totalSellPrice;
	
	private Double totalLiancePrice;
	
	private Double totalLoanPrice;
	
	public TbStoreHouseSurveyVo(){}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Long getPartInfoCategory() {
		return partInfoCategory;
	}

	public void setPartInfoCategory(Long partInfoCategory) {
		this.partInfoCategory = partInfoCategory;
	}

	public Long getZeroCategory() {
		return zeroCategory;
	}

	public void setZeroCategory(Long zeroCategory) {
		this.zeroCategory = zeroCategory;
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

	public Double getLiancePrice() {
		return liancePrice;
	}

	public void setLiancePrice(Double liancePrice) {
		this.liancePrice = liancePrice;
	}

	public Double getLoanPrice() {
		return loanPrice;
	}

	public void setLoanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}

	public String getModelTypeName() {
		return modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}

	public Long getTotalPartInfoCategory() {
		return totalPartInfoCategory;
	}

	public void setTotalPartInfoCategory(Long totalPartInfoCategory) {
		this.totalPartInfoCategory = totalPartInfoCategory;
	}

	public Long getTotalZeroCategory() {
		return totalZeroCategory;
	}

	public void setTotalZeroCategory(Long totalZeroCategory) {
		this.totalZeroCategory = totalZeroCategory;
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

	public Double getTotalLiancePrice() {
		return totalLiancePrice;
	}

	public void setTotalLiancePrice(Double totalLiancePrice) {
		this.totalLiancePrice = totalLiancePrice;
	}

	public Double getTotalLoanPrice() {
		return totalLoanPrice;
	}

	public void setTotalLoanPrice(Double totalLoanPrice) {
		this.totalLoanPrice = totalLoanPrice;
	}
	
	/* 假单导出属性  */
	private String xlsPK;
	/* 假单导出属性  */
	private String xlsPjZl;

	public String getXlsPK() {
		return xlsPK;
	}

	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}

	public String getXlsPjZl() {
		return xlsPjZl;
	}

	public void setXlsPjZl(String xlsPjZl) {
		this.xlsPjZl = xlsPjZl;
	}
}