package com.selfsoft.baseinformation.model;

import java.util.Date;

/**
 * TbPartCollection entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbPartCollection implements java.io.Serializable {

	// Fields

	private Long id;
	private Long storeHouseId;
	private Long partInfoId;
	private Double partQuantity;
	private String collectionCode;
	private Date createDate;
	
	// Constructors

	/** default constructor */
	public TbPartCollection() {
	}

	/** minimal constructor */
	public TbPartCollection(Long id, String collectionCode) {
		this.id = id;
		this.collectionCode = collectionCode;
	}

	/** full constructor */
	public TbPartCollection(Long id, Long storeHouseId,
			Long partInfoId, Double partQuantity, String collectionCode,Date createDate) {
		this.id = id;
		this.storeHouseId = storeHouseId;
		this.partInfoId = partInfoId;
		this.partQuantity = partQuantity;
		this.collectionCode = collectionCode;
		this.createDate = createDate;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreHouseId() {
		return this.storeHouseId;
	}

	public void setStoreHouseId(Long storeHouseId) {
		this.storeHouseId = storeHouseId;
	}

	public Long getPartInfoId() {
		return this.partInfoId;
	}

	public void setPartInfoId(Long partInfoId) {
		this.partInfoId = partInfoId;
	}

	public Double getPartQuantity() {
		return this.partQuantity;
	}

	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}

	public String getCollectionCode() {
		return this.collectionCode;
	}

	public void setCollectionCode(String collectionCode) {
		this.collectionCode = collectionCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}