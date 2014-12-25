package com.selfsoft.business.vo;

public class TbCustomerVo {

	private Long customerId;
	private String customerCode;
	private String customerName;
	private Double totalPrice;
	private Double thPrice;
	private Double cjPrice;
	
	public Double getThPrice() {
		return thPrice;
	}
	public void setThPrice(Double thPrice) {
		this.thPrice = thPrice;
	}
	public Double getCjPrice() {
		return cjPrice;
	}
	public void setCjPrice(Double cjPrice) {
		this.cjPrice = cjPrice;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public TbCustomerVo(Long customerId, String customerCode,
			String customerName, Double totalPrice) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.totalPrice = totalPrice;
	}
}
