package com.selfsoft.business.vo;


public class BalanceSellCountVo {

	private Long FixSellNoBalanceCount;				//修理销售未结算
	private Long FixSellIsBalanceCount ;			//修理销售已结算
	private Long fixSellAllCount ;					//修理销售所有
	private Long aloneSellNoBalanceCount ;			//单独销售未结算
	private Long aloneSellIsBalanceCount ;			//单独销售已结算
	private Long aloneSellAllCount ;				//单独销售所有
	
	private String beginDate;
	private String endDate;
	private Long carModelType;
	
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
	public Long getCarModelType() {
		return carModelType;
	}
	public void setCarModelType(Long carModelType) {
		this.carModelType = carModelType;
	}
	public Long getFixSellNoBalanceCount() {
		return FixSellNoBalanceCount;
	}
	public void setFixSellNoBalanceCount(Long fixSellNoBalanceCount) {
		FixSellNoBalanceCount = fixSellNoBalanceCount;
	}
	public Long getFixSellIsBalanceCount() {
		return FixSellIsBalanceCount;
	}
	public void setFixSellIsBalanceCount(Long fixSellIsBalanceCount) {
		FixSellIsBalanceCount = fixSellIsBalanceCount;
	}
	public Long getFixSellAllCount() {
		return fixSellAllCount;
	}
	public void setFixSellAllCount(Long fixSellAllCount) {
		this.fixSellAllCount = fixSellAllCount;
	}
	public Long getAloneSellNoBalanceCount() {
		return aloneSellNoBalanceCount;
	}
	public void setAloneSellNoBalanceCount(Long aloneSellNoBalanceCount) {
		this.aloneSellNoBalanceCount = aloneSellNoBalanceCount;
	}
	public Long getAloneSellIsBalanceCount() {
		return aloneSellIsBalanceCount;
	}
	public void setAloneSellIsBalanceCount(Long aloneSellIsBalanceCount) {
		this.aloneSellIsBalanceCount = aloneSellIsBalanceCount;
	}
	public Long getAloneSellAllCount() {
		return aloneSellAllCount;
	}
	public void setAloneSellAllCount(Long aloneSellAllCount) {
		this.aloneSellAllCount = aloneSellAllCount;
	}
}
