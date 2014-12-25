package com.selfsoft.business.model;

import com.selfsoft.baseinformation.model.TbPartInfo;

/**
 * TbBespokePartContent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbBespokePartContent implements java.io.Serializable {

	// Fields

	private Long id;
	private Double partQuantity;
	private Double price;
	private Double totalPrice;
	private TbFixEntrust tbFixEntrust;
	private TbPartInfo tbPartInfo;
	
	
	// Constructors

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	/** default constructor */
	public TbBespokePartContent() {
	}

	/** minimal constructor */
	public TbBespokePartContent(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TbBespokePartContent(Long id, 
			Double partQuantity, Double price, Double totalPrice,
			Long entrustId) {
		this.id = id;
		this.partQuantity = partQuantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPartQuantity() {
		return this.partQuantity;
	}

	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}
	
}