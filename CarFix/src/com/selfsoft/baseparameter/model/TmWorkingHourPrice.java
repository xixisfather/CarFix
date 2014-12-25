package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 工时单价
 *
 */

public class TmWorkingHourPrice implements java.io.Serializable {

	// Fields

	private Long id;
	private Double price;
	private String priceRemark;
	private Set tmCarModelTypes = new HashSet();
	// Constructors

	/** default constructor */
	public TmWorkingHourPrice() {
	}

	/** full constructor */
	public TmWorkingHourPrice(Double price, String priceRemark) {
		this.price = price;
		this.priceRemark = priceRemark;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceRemark() {
		return this.priceRemark;
	}

	public void setPriceRemark(String priceRemark) {
		this.priceRemark = priceRemark;
	}

	public Set getTmCarModelTypes() {
		return tmCarModelTypes;
	}

	public void setTmCarModelTypes(Set tmCarModelTypes) {
		this.tmCarModelTypes = tmCarModelTypes;
	}

}