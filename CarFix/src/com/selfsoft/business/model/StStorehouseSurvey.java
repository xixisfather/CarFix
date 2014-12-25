package com.selfsoft.business.model;

import java.util.Date;

/**
 * StStorehouseSurvey entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StStorehouseSurvey implements java.io.Serializable {

	// Fields

	private Long id;
	private String houseName;
	private Long partCategory;
	private Double costPrice;
	private Double sellPrice;
	private Double linacePrice;
	private Double loanPrice;
	private Date createDate;

	// Constructors

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** default constructor */
	public StStorehouseSurvey() {
	}

	/** minimal constructor */
	public StStorehouseSurvey(Long id) {
		this.id = id;
	}

	/** full constructor */
	public StStorehouseSurvey(Long id, String houseName,
			Long partCategory,Double costPrice,
			Double sellPrice, Double linacePrice, Double loanPrice) {
		this.id = id;
		this.houseName = houseName;
		this.partCategory = partCategory;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.linacePrice = linacePrice;
		this.loanPrice = loanPrice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseName() {
		return this.houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Long getPartCategory() {
		return this.partCategory;
	}

	public void setPartCategory(Long partCategory) {
		this.partCategory = partCategory;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getLinacePrice() {
		return this.linacePrice;
	}

	public void setLinacePrice(Double linacePrice) {
		this.linacePrice = linacePrice;
	}

	public Double getLoanPrice() {
		return this.loanPrice;
	}

	public void setLoanPrice(Double loanPrice) {
		this.loanPrice = loanPrice;
	}

}