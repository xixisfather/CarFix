package com.selfsoft.business.vo;

import java.util.Date;

public class TmLoanRegVo {
	
	private Long loanId;
	private String loanBill;
	private Date loanDate;
	private Long userId;
	private Date createDate;
	private Double totalQuantity;
	private Double totalPrice;
	private Long customerId;
	
	private String customerCode;		//客户号
	private String customerName;		//客户名称
	
	private String userName;			//操作人姓名
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public String getLoanBill() {
		return loanBill;
	}
	public void setLoanBill(String loanBill) {
		this.loanBill = loanBill;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
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
	public Double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
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
	public TmLoanRegVo(Long loanId, String loanBill, Date loanDate,
			Long userId, Date createDate, Double totalQuantity,
			Double totalPrice, Long customerId, String customerCode,
			String customerName,String userName) {
		super();
		this.loanId = loanId;
		this.loanBill = loanBill;
		this.loanDate = loanDate;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
