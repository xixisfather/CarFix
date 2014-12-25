package com.selfsoft.baseinformation.model;

/**
 * TbPartSolePrice entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbPartSolePrice implements java.io.Serializable {

	// Fields

	private Long id;
	private Long partInfoId;
	private Long soleTypeId;
	private Double solePrice;

	// Constructors

	/** default constructor */
	public TbPartSolePrice() {
	}

	/** minimal constructor */
	public TbPartSolePrice(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TbPartSolePrice(Long id, Long partInfoId, Long soleTypeId,
			Double solePrice) {
		this.id = id;
		this.partInfoId = partInfoId;
		this.soleTypeId = soleTypeId;
		this.solePrice = solePrice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartInfoId() {
		return this.partInfoId;
	}

	public void setPartInfoId(Long partInfoId) {
		this.partInfoId = partInfoId;
	}

	public Long getSoleTypeId() {
		return this.soleTypeId;
	}

	public void setSoleTypeId(Long soleTypeId) {
		this.soleTypeId = soleTypeId;
	}

	public Double getSolePrice() {
		return this.solePrice;
	}

	public void setSolePrice(Double solePrice) {
		this.solePrice = solePrice;
	}

		

}