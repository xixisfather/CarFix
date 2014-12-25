package com.selfsoft.business.model;

import java.util.Date;

/**
 * TmShiftinStock entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmShiftinStock implements java.io.Serializable {

	// Fields

	private Long id;
	private String shiftinBill;
	private Date shiftinDate;
	private Long storeHoseId;
	private Long removeStockId;
	private Long userId;
	private Date createDate;
	private Long isConfirm;

	// Constructors

	/** default constructor */
	public TmShiftinStock() {
	}

	/** minimal constructor */
	public TmShiftinStock(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmShiftinStock(Long id, String shiftinBill, Date shiftinDate,
			Long storeHoseId, Long removeStockId, Long isConfirm) {
		this.id = id;
		this.shiftinBill = shiftinBill;
		this.shiftinDate = shiftinDate;
		this.storeHoseId = storeHoseId;
		this.removeStockId = removeStockId;
		this.isConfirm = isConfirm;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShiftinBill() {
		return this.shiftinBill;
	}

	public void setShiftinBill(String shiftinBill) {
		this.shiftinBill = shiftinBill;
	}

	public Date getShiftinDate() {
		return this.shiftinDate;
	}

	public void setShiftinDate(Date shiftinDate) {
		this.shiftinDate = shiftinDate;
	}

	public Long getStoreHoseId() {
		return this.storeHoseId;
	}

	public void setStoreHoseId(Long storeHoseId) {
		this.storeHoseId = storeHoseId;
	}

	public Long getRemoveStockId() {
		return this.removeStockId;
	}

	public void setRemoveStockId(Long removeStockId) {
		this.removeStockId = removeStockId;
	}

	public Long getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}