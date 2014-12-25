package com.selfsoft.business.vo;

import java.util.Date;
import java.util.Map;

public class StatisticsTbFixBusinessVo {
	//车型代码
	private String modelTypeCode;
	//车型名称
	private String modelTypeName;
	//统计数量
	private Integer countNum;
	//修理类型
	private String fixType;
	//工时类型--已完工时，未完工时，总修理工时，结算工时
	private String contentShow;
	//工时数量
	private Double contentQuantity;
	//结算状态显示
	private String balanceStatusShow;
	//销售类型
	private String soleType;
	//总单数
	private Integer allBalance;
	//已结算单数
	private Integer hasBalance;
	//未结算单数
	private Integer noBalance;
	//减免单数
	private Integer freeBalance;
	//再结算单数
	private Integer reBalance;
	//结算项目名称
	private String balanceItemName;
	//结算项目金额
	private Double balanceItemAmount;
	//结算项目欠款金额
	private Double balanceItemOweAmount;
	//结算项目收款金额
	private Double balanceItemPay;
	//结算项目减免金额
	private Double balanceItemFree;
	
	
	
	//统计显示
	private Object staticsShow;
	//统计值
	private Object staticsVal;
	
	
	
	public Integer getReBalance() {
		return reBalance;
	}

	public void setReBalance(Integer reBalance) {
		this.reBalance = reBalance;
	}

	public Object getStaticsShow() {
		return staticsShow;
	}

	public void setStaticsShow(Object staticsShow) {
		this.staticsShow = staticsShow;
	}

	public Object getStaticsVal() {
		return staticsVal;
	}

	public void setStaticsVal(Object staticsVal) {
		this.staticsVal = staticsVal;
	}

	public Integer getFreeBalance() {
		return freeBalance;
	}

	public void setFreeBalance(Integer freeBalance) {
		this.freeBalance = freeBalance;
	}

	public Double getBalanceItemFree() {
		return balanceItemFree;
	}

	public void setBalanceItemFree(Double balanceItemFree) {
		this.balanceItemFree = balanceItemFree;
	}

	public Double getBalanceItemPay() {
		return balanceItemPay;
	}

	public void setBalanceItemPay(Double balanceItemPay) {
		this.balanceItemPay = balanceItemPay;
	}

	public Double getBalanceItemOweAmount() {
		return balanceItemOweAmount;
	}

	public void setBalanceItemOweAmount(Double balanceItemOweAmount) {
		this.balanceItemOweAmount = balanceItemOweAmount;
	}

	public String getBalanceItemName() {
		return balanceItemName;
	}

	public void setBalanceItemName(String balanceItemName) {
		this.balanceItemName = balanceItemName;
	}

	public Double getBalanceItemAmount() {
		return balanceItemAmount;
	}

	public void setBalanceItemAmount(Double balanceItemAmount) {
		this.balanceItemAmount = balanceItemAmount;
	}

	public Integer getAllBalance() {
		return allBalance;
	}

	public void setAllBalance(Integer allBalance) {
		this.allBalance = allBalance;
	}

	public Integer getHasBalance() {
		return hasBalance;
	}

	public void setHasBalance(Integer hasBalance) {
		this.hasBalance = hasBalance;
	}

	public Integer getNoBalance() {
		return noBalance;
	}

	public void setNoBalance(Integer noBalance) {
		this.noBalance = noBalance;
	}

	public String getSoleType() {
		return soleType;
	}

	public void setSoleType(String soleType) {
		this.soleType = soleType;
	}

	public String getBalanceStatusShow() {
		return balanceStatusShow;
	}

	public void setBalanceStatusShow(String balanceStatusShow) {
		this.balanceStatusShow = balanceStatusShow;
	}

	public String getContentShow() {
		return contentShow;
	}

	public void setContentShow(String contentShow) {
		this.contentShow = contentShow;
	}

	public Double getContentQuantity() {
		return contentQuantity;
	}

	public void setContentQuantity(Double contentQuantity) {
		this.contentQuantity = contentQuantity;
	}

	public String getFixType() {
		return fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
	}

	public String getModelTypeCode() {
		return modelTypeCode;
	}

	public void setModelTypeCode(String modelTypeCode) {
		this.modelTypeCode = modelTypeCode;
	}

	public String getModelTypeName() {
		return modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}

	public Integer getCountNum() {
		return countNum;
	}

	public void setCountNum(Integer countNum) {
		this.countNum = countNum;
	}
	
	
}
