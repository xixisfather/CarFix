package com.selfsoft.business.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.selfsoft.framework.common.StockTypeElements;

/**
 * TmStockOut entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmStockOut implements java.io.Serializable {

	// Fields

	private Long id;
	private String stockOutCode;						//出库单号
	private Long customerBill;							//客户号(销售,调拨)
	private String trustBill;							//委托书号(销售)
	private Long soleTypeId;							//销售类型(销售)
	private Long drawPeople;							//领用人(领用)
	private String profitlossBill;						//盈亏单号(报损)
	private Date stockOutDate;							//出库日期
	private Long userId;								//操作人
	private Date createDate;							//操作日期
	private Double totalQuantity;						//合计数量
	private Double totalPrice;							//合计总价
	private Long outType;								//出库类型
	private Long isConfirm;								//8000保存(未入库) 8002确认(已入库)
	
	private Long stockInId;								//采购入库Id(采购退货)
	private Long sellBalance;							//是否结算（针对于和委托书挂钩的销售单）
	private Set tbBusinessBalances = new HashSet();     //结算处理
	
	
	// Constructors

	/** default constructor */
	public TmStockOut() {
	}

	/** minimal constructor */
	public TmStockOut(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmStockOut(Long id, String stockOutCode, Long customerBill,
			String trustBill, Long soleTypeId, Long drawPeople,
			String profitlossBill, Date stockOutDate, Long userId,
			Date createDate, Double totalQuantity, Double totalPrice,
			Long outType) {
		this.id = id;
		this.stockOutCode = stockOutCode;
		this.customerBill = customerBill;
		this.trustBill = trustBill;
		this.soleTypeId = soleTypeId;
		this.drawPeople = drawPeople;
		this.profitlossBill = profitlossBill;
		this.stockOutDate = stockOutDate;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.outType = outType;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockOutCode() {
		return this.stockOutCode;
	}

	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}

	public Long getCustomerBill() {
		return this.customerBill;
	}

	public void setCustomerBill(Long customerBill) {
		this.customerBill = customerBill;
	}

	public String getTrustBill() {
		return this.trustBill;
	}

	public void setTrustBill(String trustBill) {
		this.trustBill = trustBill;
	}

	public Long getSoleTypeId() {
		return this.soleTypeId;
	}

	public void setSoleTypeId(Long soleTypeId) {
		this.soleTypeId = soleTypeId;
	}

	public Long getDrawPeople() {
		return this.drawPeople;
	}

	public void setDrawPeople(Long drawPeople) {
		this.drawPeople = drawPeople;
	}

	public String getProfitlossBill() {
		return this.profitlossBill;
	}

	public void setProfitlossBill(String profitlossBill) {
		this.profitlossBill = profitlossBill;
	}

	public Date getStockOutDate() {
		return this.stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getTotalQuantity() {
		return this.totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getOutType() {
		return this.outType;
	}

	public void setOutType(Long outType) {
		this.outType = outType;
	}

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Long getStockInId() {
		return stockInId;
	}

	public void setStockInId(Long stockInId) {
		this.stockInId = stockInId;
	}

	public Set getTbBusinessBalances() {
		return tbBusinessBalances;
	}

	public void setTbBusinessBalances(Set tbBusinessBalances) {
		this.tbBusinessBalances = tbBusinessBalances;
	}
	
	private String outTypeName;

	public String getOutTypeName() {
		return StockTypeElements.getElementMap().get(this.outType);
	}

	public Long getSellBalance() {
		return sellBalance;
	}

	public void setSellBalance(Long sellBalance) {
		this.sellBalance = sellBalance;
	}
	
	private Long sellType;

	public Long getSellType() {
		return sellType;
	}

	public void setSellType(Long sellType) {
		this.sellType = sellType;
	}
	
	
	private String beginDate;
	
	private String endDate;

	private String partCode;
	
	private String partName;
	
	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

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
	
	
}