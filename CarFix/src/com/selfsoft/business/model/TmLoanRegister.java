package com.selfsoft.business.model;

import java.util.Date;

/**
 * TmLoanRegister entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmLoanRegister implements java.io.Serializable {

	// Fields

	private Long id;
	private String loanBill;
	private Date loanDate;
	private Long customerId;
	private Long userId;
	private Date createDate;
	private Double totalQuantity;
	private Double totalPrice;
	private Long isConfirm;
	private Long isReturn;
	

	// Constructors

	/** default constructor */
	public TmLoanRegister() {
	}

	/** minimal constructor */
	public TmLoanRegister(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmLoanRegister(Long id, String loanBill, Date loanDate,
			Long customerId, Long userId, Date createDate,
			Double totalQuantity, Double totalPrice, Long isConfirm) {
		this.id = id;
		this.loanBill = loanBill;
		this.loanDate = loanDate;
		this.customerId = customerId;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.isConfirm = isConfirm;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoanBill() {
		return this.loanBill;
	}

	public void setLoanBill(String loanBill) {
		this.loanBill = loanBill;
	}

	public Date getLoanDate() {
		return this.loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getTotalQuantity() {
		return this.totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Long getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Long isReturn) {
		this.isReturn = isReturn;
	}
	
	private String beginDate;
	
	private String endDate;


	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}