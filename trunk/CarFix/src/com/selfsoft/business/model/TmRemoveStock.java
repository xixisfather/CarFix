package com.selfsoft.business.model;

import java.util.Date;

/**
 * TmRemoveStock entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmRemoveStock implements java.io.Serializable {

	// Fields

	private Long id;
	private String removeStockBill;
	private Double removeQuantity;
	private Double removePrice;
	private Date removeDate;
	private Long userId;
	private Date createDate;
	private Long isValid;
	private Long storeHoseId;
	private Long isConfirm;

	// Constructors

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	/** default constructor */
	public TmRemoveStock() {
	}

	/** minimal constructor */
	public TmRemoveStock(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmRemoveStock(Long id, String removeStockBill,
			Double removeQuantity, Double removePrice, Date removeDate,
			Long userId, Date createDate, Long isValid) {
		this.id = id;
		this.removeStockBill = removeStockBill;
		this.removeQuantity = removeQuantity;
		this.removePrice = removePrice;
		this.removeDate = removeDate;
		this.userId = userId;
		this.createDate = createDate;
		this.isValid = isValid;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemoveStockBill() {
		return this.removeStockBill;
	}

	public void setRemoveStockBill(String removeStockBill) {
		this.removeStockBill = removeStockBill;
	}

	public Double getRemoveQuantity() {
		return this.removeQuantity;
	}

	public void setRemoveQuantity(Double removeQuantity) {
		this.removeQuantity = removeQuantity;
	}

	public Double getRemovePrice() {
		return this.removePrice;
	}

	public void setRemovePrice(Double removePrice) {
		this.removePrice = removePrice;
	}

	public Date getRemoveDate() {
		return this.removeDate;
	}

	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Long isValid) {
		this.isValid = isValid;
	}

	public Long getStoreHoseId() {
		return storeHoseId;
	}

	public void setStoreHoseId(Long storeHoseId) {
		this.storeHoseId = storeHoseId;
	}
	
}