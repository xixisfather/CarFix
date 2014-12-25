package com.selfsoft.business.model;

/**
 * TmStockinDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmStockinDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long partinfoId;
	private Long stockId;
	private Double quantity;
	private Double price;				//报溢价格
	
	private Long stockoutDetailId;		//销售出库明细ID
	
	private String productArea;			//产地
	private Double hasQuantity;			//已退数量（采购退货）


	// Constructors

	public Double getHasQuantity() {
		return hasQuantity;
	}

	public void setHasQuantity(Double hasQuantity) {
		this.hasQuantity = hasQuantity;
	}

	public String getProductArea() {
		return productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}

	/** default constructor */
	public TmStockinDetail() {
	}

	/** minimal constructor */
	public TmStockinDetail(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmStockinDetail(Long id, Long partinfoId, Long stockId,
			Double quantity) {
		this.id = id;
		this.partinfoId = partinfoId;
		this.stockId = stockId;
		this.quantity = quantity;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPartinfoId() {
		return this.partinfoId;
	}

	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}

	public Long getStockId() {
		return this.stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getStockoutDetailId() {
		return stockoutDetailId;
	}

	public void setStockoutDetailId(Long stockoutDetailId) {
		this.stockoutDetailId = stockoutDetailId;
	}
	
	

}