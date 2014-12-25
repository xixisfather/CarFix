package com.selfsoft.business.model;

import java.util.Date;

/**
 * StStockin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class StStockin implements java.io.Serializable {

	// Fields

	private Long id;
	private String stockInCode;
	private Date stockInDate;
	private String houseName;
	private String partCode;
	private String partName;
	private Double quantity;
	private Double price;
	private String customerCode;
	private String customerName;
	private String oucherCode;

	// Constructors

	/** default constructor */
	public StStockin() {
	}

	/** minimal constructor */
	public StStockin(Long id) {
		this.id = id;
	}

	/** full constructor */
	public StStockin(Long id, String stockInCode, Date stockInDate,
			String houseName, String partCode, String partName,
			Double quantity, Double price, String customerCode,
			String customerName, String oucherCode) {
		this.id = id;
		this.stockInCode = stockInCode;
		this.stockInDate = stockInDate;
		this.houseName = houseName;
		this.partCode = partCode;
		this.partName = partName;
		this.quantity = quantity;
		this.price = price;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.oucherCode = oucherCode;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockInCode() {
		return this.stockInCode;
	}

	public void setStockInCode(String stockInCode) {
		this.stockInCode = stockInCode;
	}

	public Date getStockInDate() {
		return this.stockInDate;
	}

	public void setStockInDate(Date stockInDate) {
		this.stockInDate = stockInDate;
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

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOucherCode() {
		return this.oucherCode;
	}

	public void setOucherCode(String oucherCode) {
		this.oucherCode = oucherCode;
	}
	
	private String beginStockInDate;
	
	private String endStockInDate;

	public String getBeginStockInDate() {
		return beginStockInDate;
	}

	public void setBeginStockInDate(String beginStockInDate) {
		this.beginStockInDate = beginStockInDate;
	}

	public String getEndStockInDate() {
		return endStockInDate;
	}

	public void setEndStockInDate(String endStockInDate) {
		this.endStockInDate = endStockInDate;
	}
	
	

}