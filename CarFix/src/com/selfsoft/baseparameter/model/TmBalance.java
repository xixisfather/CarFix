package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.framework.common.Constants;

/**
 * TmBalance entity. @author MyEclipse Persistence Tools
 */

public class TmBalance implements java.io.Serializable {

	// Fields

	private Long id;
	private String balanceName;
	private Long balanceType;
	private Set tmBalanceItems = new HashSet();
	private Set tmFixTypes = new HashSet();
	// Constructors

	/** default constructor */
	public TmBalance() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBalanceName() {
		return this.balanceName;
	}

	public void setBalanceName(String balanceName) {
		this.balanceName = balanceName;
	}

	public Long getBalanceType() {
		return this.balanceType;
	}

	public void setBalanceType(Long balanceType) {
		this.balanceType = balanceType;
	}

	
	public Set getTmBalanceItems() {
		return tmBalanceItems;
	}

	public void setTmBalanceItems(Set tmBalanceItems) {
		this.tmBalanceItems = tmBalanceItems;
	}

	public Set getTmFixTypes() {
		return tmFixTypes;
	}

	public void setTmFixTypes(Set tmFixTypes) {
		this.tmFixTypes = tmFixTypes;
	}



	private String balanceTypeShow;

	public String getBalanceTypeShow() {
		if(Constants.BALANCEFIX.equals(this.balanceType)){
			balanceTypeShow = Constants.BALANCEFIXSHOW;
		}
		else if(Constants.BALANCEALONE.equals(this.balanceType)){
			balanceTypeShow = Constants.BALANCEALONESHOW;
		}
		else{
			balanceTypeShow = Constants.NOTDIFINE;
		}
		
		return balanceTypeShow;
	}

	public void setBalanceTypeShow(String balanceTypeShow) {
		this.balanceTypeShow = balanceTypeShow;
	}
	
	
}