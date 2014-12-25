package com.selfsoft.business.model;

import java.util.Date;

/**
 * StPartReceiver entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StPartReceiver implements java.io.Serializable {

	// Fields

	private Long id;
	private String houseName;
	private String partCode;
	private String partName;
	private Double stockinQuantity;
	private Double stockinPrice;
	private Double stockoutQuantity;
	private Double stockoutPrice;
	private Double storeQuantity;
	private Double storePrice;
	private Date createDate;

	// Constructors

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** default constructor */
	public StPartReceiver() {
	}

	/** minimal constructor */
	public StPartReceiver(Long id) {
		this.id = id;
	}

	/** full constructor */
	public StPartReceiver(Long id, String houseName, String partCode,
			String partName, Double stockinQuantity, Double stockinPrice,
			Double stockoutQuantity, Double stockoutPrice,
			Double storeQuantity, Double storePrice) {
		this.id = id;
		this.houseName = houseName;
		this.partCode = partCode;
		this.partName = partName;
		this.stockinQuantity = stockinQuantity;
		this.stockinPrice = stockinPrice;
		this.stockoutQuantity = stockoutQuantity;
		this.stockoutPrice = stockoutPrice;
		this.storeQuantity = storeQuantity;
		this.storePrice = storePrice;
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

	public String getPartCode() {
		return this.partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Double getStockinQuantity() {
		return this.stockinQuantity;
	}

	public void setStockinQuantity(Double stockinQuantity) {
		this.stockinQuantity = stockinQuantity;
	}

	public Double getStockinPrice() {
		return this.stockinPrice;
	}

	public void setStockinPrice(Double stockinPrice) {
		this.stockinPrice = stockinPrice;
	}

	public Double getStockoutQuantity() {
		return this.stockoutQuantity;
	}

	public void setStockoutQuantity(Double stockoutQuantity) {
		this.stockoutQuantity = stockoutQuantity;
	}

	public Double getStockoutPrice() {
		return this.stockoutPrice;
	}

	public void setStockoutPrice(Double stockoutPrice) {
		this.stockoutPrice = stockoutPrice;
	}

	public Double getStoreQuantity() {
		return this.storeQuantity;
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

	
	private Double totalStockinQuantity ;
	private Double totalStockinPrice ;
	private Double totalStockoutQuantity ;
	private Double totalStockoutPrice ;

	public Double getTotalStockinQuantity() {
		return totalStockinQuantity;
	}

	public void setTotalStockinQuantity(Double totalStockinQuantity) {
		this.totalStockinQuantity = totalStockinQuantity;
	}

	public Double getTotalStockinPrice() {
		return totalStockinPrice;
	}

	public void setTotalStockinPrice(Double totalStockinPrice) {
		this.totalStockinPrice = totalStockinPrice;
	}

	public Double getTotalStockoutQuantity() {
		return totalStockoutQuantity;
	}

	public void setTotalStockoutQuantity(Double totalStockoutQuantity) {
		this.totalStockoutQuantity = totalStockoutQuantity;
	}

	public Double getTotalStockoutPrice() {
		return totalStockoutPrice;
	}

	public void setTotalStockoutPrice(Double totalStockoutPrice) {
		this.totalStockoutPrice = totalStockoutPrice;
	}

	
}