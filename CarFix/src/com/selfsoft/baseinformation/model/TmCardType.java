package com.selfsoft.baseinformation.model;

import java.util.List;

public class TmCardType implements java.io.Serializable{
	
	private Long id;
	
	private String cardDesc; 
	
	private Integer gsMFullMoney;
	
	private Integer gsMGiveMoney;
	
	private Integer pjMFullMoney;
	
	private Integer pjMGiveMoney;
	
	private Integer gsPFullMoney;
	
	private Integer gsPGivePoint;
	
	private Integer pjPFullMoney;
	
	private Integer pjPGivePoint;
	
	private Integer cFullMoney;
	
	private Integer cGiveMoney;
	
	private Integer cGivePoint;
	
	private Double gsYhl;
	
	private Double pjYhl;
	
	private Integer dhFullMoney;
	
	private Integer dhFullPoint;
	
	private List<TmCardTypeService> tmCardTypeServiceList;
	
	private Integer gsBxMFullMoney;
	
	private Integer gsBxMGiveMoney;
	
	private Integer pjBxMFullMoney;
	
	private Integer pjBxMGiveMoney;
	
	private Integer gsBxPFullMoney;
	
	private Integer gsBxPGivePoint;
	
	private Integer pjBxPFullMoney;
	
	private Double gsBxYhl;
	
	private Double pjBxYhl;
	
	private Integer pjBxPGivePoint; 
	
	private Integer yhMFullMoney;
	
	private Integer yhMMinusPoint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	public Integer getGsMFullMoney() {
		return gsMFullMoney;
	}

	public void setGsMFullMoney(Integer gsMFullMoney) {
		this.gsMFullMoney = gsMFullMoney;
	}

	public Integer getGsMGiveMoney() {
		return gsMGiveMoney;
	}

	public void setGsMGiveMoney(Integer gsMGiveMoney) {
		this.gsMGiveMoney = gsMGiveMoney;
	}

	public Integer getPjMFullMoney() {
		return pjMFullMoney;
	}

	public void setPjMFullMoney(Integer pjMFullMoney) {
		this.pjMFullMoney = pjMFullMoney;
	}

	public Integer getPjMGiveMoney() {
		return pjMGiveMoney;
	}

	public void setPjMGiveMoney(Integer pjMGiveMoney) {
		this.pjMGiveMoney = pjMGiveMoney;
	}

	public Integer getGsPFullMoney() {
		return gsPFullMoney;
	}

	public void setGsPFullMoney(Integer gsPFullMoney) {
		this.gsPFullMoney = gsPFullMoney;
	}

	public Integer getGsPGivePoint() {
		return gsPGivePoint;
	}

	public void setGsPGivePoint(Integer gsPGivePoint) {
		this.gsPGivePoint = gsPGivePoint;
	}

	public Integer getPjPFullMoney() {
		return pjPFullMoney;
	}

	public void setPjPFullMoney(Integer pjPFullMoney) {
		this.pjPFullMoney = pjPFullMoney;
	}

	public Integer getPjPGivePoint() {
		return pjPGivePoint;
	}

	public void setPjPGivePoint(Integer pjPGivePoint) {
		this.pjPGivePoint = pjPGivePoint;
	}

	public Integer getcFullMoney() {
		return cFullMoney;
	}

	public void setcFullMoney(Integer cFullMoney) {
		this.cFullMoney = cFullMoney;
	}

	public Integer getcGiveMoney() {
		return cGiveMoney;
	}

	public void setcGiveMoney(Integer cGiveMoney) {
		this.cGiveMoney = cGiveMoney;
	}

	public Integer getcGivePoint() {
		return cGivePoint;
	}

	public void setcGivePoint(Integer cGivePoint) {
		this.cGivePoint = cGivePoint;
	}

	public Double getGsYhl() {
		return gsYhl;
	}

	public void setGsYhl(Double gsYhl) {
		this.gsYhl = gsYhl;
	}

	public Double getPjYhl() {
		return pjYhl;
	}

	public void setPjYhl(Double pjYhl) {
		this.pjYhl = pjYhl;
	}

	public Integer getDhFullMoney() {
		return dhFullMoney;
	}

	public void setDhFullMoney(Integer dhFullMoney) {
		this.dhFullMoney = dhFullMoney;
	}

	public Integer getDhFullPoint() {
		return dhFullPoint;
	}

	public void setDhFullPoint(Integer dhFullPoint) {
		this.dhFullPoint = dhFullPoint;
	}

	public List<TmCardTypeService> getTmCardTypeServiceList() {
		return tmCardTypeServiceList;
	}

	public void setTmCardTypeServiceList(
			List<TmCardTypeService> tmCardTypeServiceList) {
		this.tmCardTypeServiceList = tmCardTypeServiceList;
	}

	public Integer getGsBxMFullMoney() {
		return gsBxMFullMoney;
	}

	public void setGsBxMFullMoney(Integer gsBxMFullMoney) {
		this.gsBxMFullMoney = gsBxMFullMoney;
	}

	public Integer getGsBxMGiveMoney() {
		return gsBxMGiveMoney;
	}

	public void setGsBxMGiveMoney(Integer gsBxMGiveMoney) {
		this.gsBxMGiveMoney = gsBxMGiveMoney;
	}

	public Integer getPjBxMFullMoney() {
		return pjBxMFullMoney;
	}

	public void setPjBxMFullMoney(Integer pjBxMFullMoney) {
		this.pjBxMFullMoney = pjBxMFullMoney;
	}

	public Integer getPjBxMGiveMoney() {
		return pjBxMGiveMoney;
	}

	public void setPjBxMGiveMoney(Integer pjBxMGiveMoney) {
		this.pjBxMGiveMoney = pjBxMGiveMoney;
	}

	public Integer getGsBxPFullMoney() {
		return gsBxPFullMoney;
	}

	public void setGsBxPFullMoney(Integer gsBxPFullMoney) {
		this.gsBxPFullMoney = gsBxPFullMoney;
	}

	public Integer getGsBxPGivePoint() {
		return gsBxPGivePoint;
	}

	public void setGsBxPGivePoint(Integer gsBxPGivePoint) {
		this.gsBxPGivePoint = gsBxPGivePoint;
	}

	public Integer getPjBxPFullMoney() {
		return pjBxPFullMoney;
	}

	public void setPjBxPFullMoney(Integer pjBxPFullMoney) {
		this.pjBxPFullMoney = pjBxPFullMoney;
	}

	public Double getGsBxYhl() {
		return gsBxYhl;
	}

	public void setGsBxYhl(Double gsBxYhl) {
		this.gsBxYhl = gsBxYhl;
	}

	public Double getPjBxYhl() {
		return pjBxYhl;
	}

	public void setPjBxYhl(Double pjBxYhl) {
		this.pjBxYhl = pjBxYhl;
	}

	public Integer getPjBxPGivePoint() {
		return pjBxPGivePoint;
	}

	public void setPjBxPGivePoint(Integer pjBxPGivePoint) {
		this.pjBxPGivePoint = pjBxPGivePoint;
	}

	public Integer getYhMFullMoney() {
		return yhMFullMoney;
	}

	public void setYhMFullMoney(Integer yhMFullMoney) {
		this.yhMFullMoney = yhMFullMoney;
	}

	public Integer getYhMMinusPoint() {
		return yhMMinusPoint;
	}

	public void setYhMMinusPoint(Integer yhMMinusPoint) {
		this.yhMMinusPoint = yhMMinusPoint;
	}
	
}
