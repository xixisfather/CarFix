package com.selfsoft.business.model;

/**
 * TmRemoveStockDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmRemoveStockDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long removeStockId;
	private Long partinfoId;
	private Double quantity;
	private Double costPrice;

	// Constructors

	/** default constructor */
	public TmRemoveStockDetail() {
	}

	/** minimal constructor */
	public TmRemoveStockDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmRemoveStockDetail(Long id, Long removeStockId,
			Long partinfoId, Double quantity, Double costPrice) {
		this.id = id;
		this.removeStockId = removeStockId;
		this.partinfoId = partinfoId;
		this.quantity = quantity;
		this.costPrice = costPrice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRemoveStockId() {
		return this.removeStockId;
	}

	public void setRemoveStockId(Long removeStockId) {
		this.removeStockId = removeStockId;
	}

	public Long getPartinfoId() {
		return this.partinfoId;
	}

	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getCostPrice() {
		return this.costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

}