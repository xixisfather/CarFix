package com.selfsoft.business.model;

/**
 * TbBusinessSpecialBalanceItem entity. @author MyEclipse Persistence Tools
 */

public class TbBusinessSpecialBalanceItem implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBusinessSpecialBalance tbBusinessSpecialBalance;
	private String balanceItemCode;
	private String balanceItemName;
	private Double balanceItemTotal;

	// Constructors

	/** default constructor */
	public TbBusinessSpecialBalanceItem() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbBusinessSpecialBalance getTbBusinessSpecialBalance() {
		return tbBusinessSpecialBalance;
	}

	public void setTbBusinessSpecialBalance(
			TbBusinessSpecialBalance tbBusinessSpecialBalance) {
		this.tbBusinessSpecialBalance = tbBusinessSpecialBalance;
	}

	public String getBalanceItemCode() {
		return this.balanceItemCode;
	}

	public void setBalanceItemCode(String balanceItemCode) {
		this.balanceItemCode = balanceItemCode;
	}

	public String getBalanceItemName() {
		return this.balanceItemName;
	}

	public void setBalanceItemName(String balanceItemName) {
		this.balanceItemName = balanceItemName;
	}

	public Double getBalanceItemTotal() {
		return this.balanceItemTotal;
	}

	public void setBalanceItemTotal(Double balanceItemTotal) {
		this.balanceItemTotal = balanceItemTotal;
	}

}