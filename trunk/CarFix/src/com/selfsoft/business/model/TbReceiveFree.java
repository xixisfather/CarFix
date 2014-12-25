package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.framework.common.Constants;

/**
 * TbReceiveFree entity. @author MyEclipse Persistence Tools
 */

public class TbReceiveFree implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBusinessBalance tbBusinessBalance;
	private Long amountType;
	private Double feeAmount;
	private String remark;
	private TbCustomer tbCustomer;
	private Date amountDate;
	// Constructors

	/** default constructor */
	public TbReceiveFree() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbBusinessBalance getTbBusinessBalance() {
		return tbBusinessBalance;
	}

	public void setTbBusinessBalance(TbBusinessBalance tbBusinessBalance) {
		this.tbBusinessBalance = tbBusinessBalance;
	}

	public Long getAmountType() {
		return this.amountType;
	}

	public void setAmountType(Long amountType) {
		this.amountType = amountType;
	}

	public Double getFeeAmount() {
		return this.feeAmount;
	}

	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String balanceCode;
	
	private String amountTypeShow;

	public String getBalanceCode() {
		if(null!=this.tbBusinessBalance){
			balanceCode = this.tbBusinessBalance.getBalanceCode();
		}
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public String getAmountTypeShow() {
		
		if(Constants.AMOUNTR.equals(this.amountType)){
			amountTypeShow = Constants.AMOUNTRSHOW;
		}
		else if(Constants.AMOUNTS.equals(this.amountType)){
			amountTypeShow = Constants.AMOUNTSSHOW;
		}
		
		return amountTypeShow;
	}

	public void setAmountTypeShow(String amountTypeShow) {
		this.amountTypeShow = amountTypeShow;
	}
	
	private String customerCode;
	
	private String customerName;

	public String getCustomerCode() {
		if(null!=this.tbCustomer){
			customerCode = this.tbCustomer.getCustomerCode();
		}
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		if(null!=this.tbCustomer.getCustomerName()){
			customerName = this.tbCustomer.getCustomerName();
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public Date getAmountDate() {
		return amountDate;
	}

	public void setAmountDate(Date amountDate) {
		this.amountDate = amountDate;
	}
	
	
}