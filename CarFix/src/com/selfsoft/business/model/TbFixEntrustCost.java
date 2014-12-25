package com.selfsoft.business.model;

public class TbFixEntrustCost implements java.io.Serializable {

	private Long id;
	
	private Double costPrice;
	
	private String costType;
	
	private TbFixEntrust tbFixEntrust;

	public TbFixEntrustCost() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public TbFixEntrust getTbFixEntrust() {
		return tbFixEntrust;
	}

	public void setTbFixEntrust(TbFixEntrust tbFixEntrust) {
		this.tbFixEntrust = tbFixEntrust;
	}
	
	
}
