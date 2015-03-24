package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.framework.common.Constants;

/**
 * TbSpecialPartContent entity. @author MyEclipse Persistence Tools
 */

public class TbSpecialPartContent implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBusinessSpecialBalance tbBusinessSpecialBalance;
	private TbPartInfo tbPartInfo;
	private Double partPrice;
	private Double partQuantity;
	private Double partTotal;
	private Long isFree;
	private String partNameDB;

	// Constructors

	/** default constructor */
	public TbSpecialPartContent() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbBusinessSpecialBalance getTbBusinessSpecialBalance() {
		return tbBusinessSpecialBalance;
	}

	public void setTbBusinessSpecialBalance(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		this.tbBusinessSpecialBalance = tbBusinessSpecialBalance;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public String getPartNameDB() {
		return partNameDB;
	}

	public void setPartNameDB(String partNameDB) {
		this.partNameDB = partNameDB;
	}

	public Double getPartPrice() {
		return this.partPrice;
	}

	public void setPartPrice(Double partPrice) {
		this.partPrice = partPrice;
	}

	public Double getPartQuantity() {
		return this.partQuantity;
	}

	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}

	public Double getPartTotal() {
		return this.partTotal;
	}

	public void setPartTotal(Double partTotal) {
		this.partTotal = partTotal;
	}

	public Long getIsFree() {
		return this.isFree;
	}

	public void setIsFree(Long isFree) {
		this.isFree = isFree;
	}

	private String partCode;

	private String partName;

	private String unitName;

	private Double quantity;

	private Double total;

	public String getPartCode() {

		if (null != this.tbPartInfo) {
			partCode = this.tbPartInfo.getPartCode();
		}
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {

		if (null != this.partNameDB) {

			partName = this.partNameDB;

		} else {

			if (null != this.tbPartInfo) {
				partName = this.tbPartInfo.getPartName();
			}
			
		}

		return partName;

	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	private String freeName;

	public String getFreeName() {
		if (this.isFree.equals(Constants.FREESYMBOL_NO))
			return Constants.FREESYMBOL_NO_SHOW;
		if (this.isFree.equals(Constants.FREESYMBOL_FG))
			return Constants.FREESYMBOL_FG_SHOW;
		if (this.isFree.equals(Constants.FREESYMBOL_FW))
			return Constants.FREESYMBOL_FW_SHOW;
		if (this.isFree.equals(Constants.FREESYMBOL_SB))
			return Constants.FREESYMBOL_SB_SHOW;
		if (this.isFree.equals(Constants.FREESYMBOL_SP))
			return Constants.FREESYMBOL_SP_SHOW;

		return null;
	}

	public String getUnitName() {
		if (null != this.tbPartInfo) {
			unitName = this.tbPartInfo.getUnitName();
		}
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Double getQuantity() {

		this.quantity = this.partQuantity;

		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public Double acquireTotal() {
		
		return total;
	}

	public Double getTotal() {

		this.total = this.partTotal;

		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public void setFreeName(String freeName) {
		this.freeName = freeName;
	}

	private Double price;

	private String houseName;

	public Double getPrice() {

		this.price = this.partPrice;

		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getHouseName() {

		if (null != this.tbPartInfo) {
			this.houseName = this.tbPartInfo.getHouseName();
		}
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

}