package com.selfsoft.baseparameter.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TmFixType entity. @author MyEclipse Persistence Tools
 */

public class TmFixType implements java.io.Serializable {

	// Fields

	private Long id;
	private String fixType;
	private TmBalance tmBalance;
	//废弃    工时单价与修理类型不绑定
	//private Double workingHourPrice;
	//预约单
	private Set tbBooks = new HashSet();
	//委托书
	private Set tbFixEntrusts = new HashSet();
	// Constructors

	/** default constructor */
	public TmFixType() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFixType() {
		return this.fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
	}

	public TmBalance getTmBalance() {
		return tmBalance;
	}

	public void setTmBalance(TmBalance tmBalance) {
		this.tmBalance = tmBalance;
	}

	/*public Double getWorkingHourPrice() {
		return this.workingHourPrice;
	}

	public void setWorkingHourPrice(Double workingHourPrice) {
		this.workingHourPrice = workingHourPrice;
	}*/

	public Set getTbBooks() {
		return tbBooks;
	}

	public void setTbBooks(Set tbBooks) {
		this.tbBooks = tbBooks;
	}


	public Set getTbFixEntrusts() {
		return tbFixEntrusts;
	}

	public void setTbFixEntrusts(Set tbFixEntrusts) {
		this.tbFixEntrusts = tbFixEntrusts;
	}


	private String tmBalanceName;

	public String getTmBalanceName() {
		if(null!=this.tmBalance){
			tmBalanceName = this.tmBalance.getBalanceName();
		}
		
		return tmBalanceName;
	}

	public void setTmBalanceName(String tmBalanceName) {
		this.tmBalanceName = tmBalanceName;
	}
	
	
}