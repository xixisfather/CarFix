package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.framework.common.Constants;

/**
 * TmBalanceItem entity. @author MyEclipse Persistence Tools
 */

public class TmBalanceItem implements java.io.Serializable {

	// Fields

	private Long id;
	private String itemCode;
	private String itemName;
	private String itemRemark;
	private TmBalance tmBalance;
	private String formula;
	private Long allowHand;
	// Constructors

	/** default constructor */
	public TmBalanceItem() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemRemark() {
		return this.itemRemark;
	}

	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
	}

	public TmBalance getTmBalance() {
		return tmBalance;
	}

	public void setTmBalance(TmBalance tmBalance) {
		this.tmBalance = tmBalance;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public Long getAllowHand() {
		return allowHand;
	}

	public void setAllowHand(Long allowHand) {
		this.allowHand = allowHand;
	}

	private String balanceName;

	private String allowHandShow;
	
	public String getBalanceName() {
		if(null!=this.tmBalance){
			balanceName = this.tmBalance.getBalanceName();
		}
		
		return balanceName;
	}

	public void setBalanceName(String balanceName) {
		this.balanceName = balanceName;
	}

	public String getAllowHandShow() {
		if(Constants.ISTRUE.equals(this.allowHand)){
			allowHandShow = Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.allowHand)){
			allowHandShow = Constants.NOTTRUESHOW;
		}
		
		return allowHandShow;
	}

	public void setAllowHandShow(String allowHandShow) {
		this.allowHandShow = allowHandShow;
	}
	
	

	
}