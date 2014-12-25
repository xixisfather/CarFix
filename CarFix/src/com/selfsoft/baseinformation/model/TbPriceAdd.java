package com.selfsoft.baseinformation.model;

public class TbPriceAdd {

	private Long id;
	
	private String schemeName;
	
	private Double maxPrice;
	
	private Double minPrice;
	
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	private String priceRegion;

	public String getPriceRegion() {
		return this.getMinPrice() + "~" + this.getMaxPrice();
	}

	public void setPriceRegion(String priceRegion) {
		this.priceRegion = priceRegion;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
}
