package com.selfsoft.business.model;

/**
 * TbBusinessBalanceItem entity. @author MyEclipse Persistence Tools
 */

public class TbBusinessBalanceItem implements java.io.Serializable {

	// Fields

	private Long id;
	private TbBusinessBalance tbBusinessBalance;
	private String balanceItemCode;
	private String balanceItemName;
	private Double balanceItemTotal;

	// Constructors

	/** default constructor */
	public TbBusinessBalanceItem() {
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