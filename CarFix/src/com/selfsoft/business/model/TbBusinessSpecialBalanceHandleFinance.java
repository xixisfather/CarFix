package com.selfsoft.business.model;

import java.util.Date;

/**
 * TbBusinessSpecialBalanceHandleFinance entity. @author MyEclipse Persistence
 * Tools
 */

public class TbBusinessSpecialBalanceHandleFinance implements
		java.io.Serializable {

	// Fields

	private Long id;
	private Integer countAll;
	private Integer numFixSole;
	private Integer numUniSole;
	private String staticFixType;
	private String staticCarModelType;
	private Integer unNumFixSole;
	private Double unFixFee;
	private Double unFixPartFee;
	private Integer unNumUniSole;
	private Double unUniPartFee;
	private Integer unSoleCount;
	private Double unFeeCount;
	private Double spFixFee;
	private Double byFixFree;
	private Double fgFixFee;
	private Double spPartFee;
	private Double byPartFee;
	private Double fgPartFee;
	private Double spFeeCount;
	private Double fgFeeCount;
	private Double byFeeCount;
	private Double hasFixFee;
	private Double hasPartFee;
	private Double hasSoleFee;
	private Double hasFeeCount;
	private Double unPayFixSole;
	private Double unPayFixSolePayed;
	private Double unPayFixSoleOwe;
	private Double unPayUniSole;
	private Double unPayUniSolePayed;
	private Double unPayUniSoleOwe;
	private Double hasPayFixSole;
	private Double hasPayFixSolePayed;
	private Double hasPayUniSole;
	private Double hasPayUniSolePayed;
	private Integer numFree;
	private Integer numHas;
	private Double patedCount;
	private Double freeCount;
	private Double oweCount;
	private Double profitVal;
	private Double purchaseVal;
	private Double partCoseVal;
	private Double unFixHour;
	private Double hasFixHour;
	private Double allFixHour;
	private Double payedFixHour;
	private Double fixPartCosr;
	private Double solePartCost;
	private Double fixPartCount;
	private Long userId;
	private String staticsDate;
	private String staticsDateFrom;
	private String staticsDateEnd;

	// Constructors

	/** default constructor */
	public TbBusinessSpecialBalanceHandleFinance() {
	}


	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCountAll() {
		return countAll;
	}


	public void setCountAll(Integer countAll) {
		this.countAll = countAll;
	}


	public Integer getNumFixSole() {
		return numFixSole;
	}


	public void setNumFixSole(Integer numFixSole) {
		this.numFixSole = numFixSole;
	}


	public Integer getNumUniSole() {
		return numUniSole;
	}


	public void setNumUniSole(Integer numUniSole) {
		this.numUniSole = numUniSole;
	}


	public String getStaticFixType() {
		return this.staticFixType;
	}

	public void setStaticFixType(String staticFixType) {
		this.staticFixType = staticFixType;
	}

	public String getStaticCarModelType() {
		return this.staticCarModelType;
	}

	public void setStaticCarModelType(String staticCarModelType) {
		this.staticCarModelType = staticCarModelType;
	}

	public Integer getUnNumFixSole() {
		return this.unNumFixSole;
	}

	public void setUnNumFixSole(Integer unNumFixSole) {
		this.unNumFixSole = unNumFixSole;
	}

	public Double getUnFixFee() {
		return this.unFixFee;
	}

	public void setUnFixFee(Double unFixFee) {
		this.unFixFee = unFixFee;
	}

	public Double getUnFixPartFee() {
		return this.unFixPartFee;
	}

	public void setUnFixPartFee(Double unFixPartFee) {
		this.unFixPartFee = unFixPartFee;
	}

	public Integer getUnNumUniSole() {
		return this.unNumUniSole;
	}

	public void setUnNumUniSole(Integer unNumUniSole) {
		this.unNumUniSole = unNumUniSole;
	}

	public Double getUnUniPartFee() {
		return this.unUniPartFee;
	}

	public void setUnUniPartFee(Double unUniPartFee) {
		this.unUniPartFee = unUniPartFee;
	}

	public Integer getUnSoleCount() {
		return this.unSoleCount;
	}

	public void setUnSoleCount(Integer unSoleCount) {
		this.unSoleCount = unSoleCount;
	}

	public Double getUnFeeCount() {
		return this.unFeeCount;
	}

	public void setUnFeeCount(Double unFeeCount) {
		this.unFeeCount = unFeeCount;
	}

	public Double getSpFixFee() {
		return this.spFixFee;
	}

	public void setSpFixFee(Double spFixFee) {
		this.spFixFee = spFixFee;
	}

	public Double getByFixFree() {
		return this.byFixFree;
	}

	public void setByFixFree(Double byFixFree) {
		this.byFixFree = byFixFree;
	}

	public Double getFgFixFee() {
		return this.fgFixFee;
	}

	public void setFgFixFee(Double fgFixFee) {
		this.fgFixFee = fgFixFee;
	}

	public Double getSpPartFee() {
		return this.spPartFee;
	}

	public void setSpPartFee(Double spPartFee) {
		this.spPartFee = spPartFee;
	}

	public Double getByPartFee() {
		return this.byPartFee;
	}

	public void setByPartFee(Double byPartFee) {
		this.byPartFee = byPartFee;
	}

	public Double getFgPartFee() {
		return this.fgPartFee;
	}

	public void setFgPartFee(Double fgPartFee) {
		this.fgPartFee = fgPartFee;
	}

	public Double getSpFeeCount() {
		return this.spFeeCount;
	}

	public void setSpFeeCount(Double spFeeCount) {
		this.spFeeCount = spFeeCount;
	}

	public Double getFgFeeCount() {
		return this.fgFeeCount;
	}

	public void setFgFeeCount(Double fgFeeCount) {
		this.fgFeeCount = fgFeeCount;
	}

	public Double getByFeeCount() {
		return this.byFeeCount;
	}

	public void setByFeeCount(Double byFeeCount) {
		this.byFeeCount = byFeeCount;
	}

	public Double getHasFixFee() {
		return this.hasFixFee;
	}

	public void setHasFixFee(Double hasFixFee) {
		this.hasFixFee = hasFixFee;
	}

	public Double getHasPartFee() {
		return this.hasPartFee;
	}

	public void setHasPartFee(Double hasPartFee) {
		this.hasPartFee = hasPartFee;
	}

	public Double getHasSoleFee() {
		return this.hasSoleFee;
	}

	public void setHasSoleFee(Double hasSoleFee) {
		this.hasSoleFee = hasSoleFee;
	}

	public Double getHasFeeCount() {
		return this.hasFeeCount;
	}

	public void setHasFeeCount(Double hasFeeCount) {
		this.hasFeeCount = hasFeeCount;
	}

	public Double getUnPayFixSole() {
		return this.unPayFixSole;
	}

	public void setUnPayFixSole(Double unPayFixSole) {
		this.unPayFixSole = unPayFixSole;
	}

	public Double getUnPayFixSolePayed() {
		return this.unPayFixSolePayed;
	}

	public void setUnPayFixSolePayed(Double unPayFixSolePayed) {
		this.unPayFixSolePayed = unPayFixSolePayed;
	}

	public Double getUnPayFixSoleOwe() {
		return this.unPayFixSoleOwe;
	}

	public void setUnPayFixSoleOwe(Double unPayFixSoleOwe) {
		this.unPayFixSoleOwe = unPayFixSoleOwe;
	}

	public Double getUnPayUniSole() {
		return unPayUniSole;
	}


	public void setUnPayUniSole(Double unPayUniSole) {
		this.unPayUniSole = unPayUniSole;
	}


	public Double getUnPayUniSolePayed() {
		return unPayUniSolePayed;
	}


	public void setUnPayUniSolePayed(Double unPayUniSolePayed) {
		this.unPayUniSolePayed = unPayUniSolePayed;
	}


	public Double getUnPayUniSoleOwe() {
		return unPayUniSoleOwe;
	}


	public void setUnPayUniSoleOwe(Double unPayUniSoleOwe) {
		this.unPayUniSoleOwe = unPayUniSoleOwe;
	}


	public Double getHasPayFixSole() {
		return this.hasPayFixSole;
	}

	public void setHasPayFixSole(Double hasPayFixSole) {
		this.hasPayFixSole = hasPayFixSole;
	}

	public Double getHasPayFixSolePayed() {
		return this.hasPayFixSolePayed;
	}

	public void setHasPayFixSolePayed(Double hasPayFixSolePayed) {
		this.hasPayFixSolePayed = hasPayFixSolePayed;
	}

	public Double getHasPayUniSole() {
		return this.hasPayUniSole;
	}

	public void setHasPayUniSole(Double hasPayUniSole) {
		this.hasPayUniSole = hasPayUniSole;
	}

	public Double getHasPayUniSolePayed() {
		return this.hasPayUniSolePayed;
	}

	public void setHasPayUniSolePayed(Double hasPayUniSolePayed) {
		this.hasPayUniSolePayed = hasPayUniSolePayed;
	}

	public Integer getNumFree() {
		return this.numFree;
	}

	public void setNumFree(Integer numFree) {
		this.numFree = numFree;
	}

	public Integer getNumHas() {
		return this.numHas;
	}

	public void setNumHas(Integer numHas) {
		this.numHas = numHas;
	}

	public Double getPatedCount() {
		return this.patedCount;
	}

	public void setPatedCount(Double patedCount) {
		this.patedCount = patedCount;
	}

	public Double getFreeCount() {
		return this.freeCount;
	}

	public void setFreeCount(Double freeCount) {
		this.freeCount = freeCount;
	}

	public Double getOweCount() {
		return this.oweCount;
	}

	public void setOweCount(Double oweCount) {
		this.oweCount = oweCount;
	}

	public Double getProfitVal() {
		return this.profitVal;
	}

	public void setProfitVal(Double profitVal) {
		this.profitVal = profitVal;
	}

	public Double getPurchaseVal() {
		return this.purchaseVal;
	}

	public void setPurchaseVal(Double purchaseVal) {
		this.purchaseVal = purchaseVal;
	}

	public Double getPartCoseVal() {
		return this.partCoseVal;
	}

	public void setPartCoseVal(Double partCoseVal) {
		this.partCoseVal = partCoseVal;
	}

	public Double getUnFixHour() {
		return this.unFixHour;
	}

	public void setUnFixHour(Double unFixHour) {
		this.unFixHour = unFixHour;
	}

	public Double getHasFixHour() {
		return this.hasFixHour;
	}

	public void setHasFixHour(Double hasFixHour) {
		this.hasFixHour = hasFixHour;
	}

	public Double getAllFixHour() {
		return this.allFixHour;
	}

	public void setAllFixHour(Double allFixHour) {
		this.allFixHour = allFixHour;
	}

	public Double getPayedFixHour() {
		return this.payedFixHour;
	}

	public void setPayedFixHour(Double payedFixHour) {
		this.payedFixHour = payedFixHour;
	}

	public Double getFixPartCosr() {
		return this.fixPartCosr;
	}

	public void setFixPartCosr(Double fixPartCosr) {
		this.fixPartCosr = fixPartCosr;
	}

	public Double getSolePartCost() {
		return this.solePartCost;
	}

	public void setSolePartCost(Double solePartCost) {
		this.solePartCost = solePartCost;
	}

	public Double getFixPartCount() {
		return this.fixPartCount;
	}

	public void setFixPartCount(Double fixPartCount) {
		this.fixPartCount = fixPartCount;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getStaticsDateFrom() {
		return this.staticsDateFrom;
	}

	public void setStaticsDateFrom(String staticsDateFrom) {
		this.staticsDateFrom = staticsDateFrom;
	}

	public String getStaticsDateEnd() {
		return this.staticsDateEnd;
	}

	public void setStaticsDateEnd(String staticsDateEnd) {
		this.staticsDateEnd = staticsDateEnd;
	}


	public String getStaticsDate() {
		return staticsDate;
	}


	public void setStaticsDate(String staticsDate) {
		this.staticsDate = staticsDate;
	}

	private String userRealName;

	public String getUserRealName() {
		return userRealName;
	}


	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	
}