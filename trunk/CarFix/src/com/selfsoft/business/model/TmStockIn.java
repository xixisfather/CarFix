package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.framework.common.StockTypeElements;

/**
 * TmStockIn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TmStockIn implements java.io.Serializable {

	// Fields

	private Long id;
	private String stockInCode;				//入库单号(报溢单号)
	private Long supplierId;				//供货商号
	private Long userId;					//操作人
	private Date createDate;				//生成日期
	private Double totalQuantity;			//合计数量
	private Double totalPrice;				//合计总价
	private Long inType;					//入库类型
	private String indent;					//订货单号(采购)
	private String oucherCode;				//入库凭证(采购)
	private String oucherNo;				//凭证号(调拨)
	private String businessCode;			//业务单据(其他)
	private Date arriveDate;				//到货日期（报溢日期）
	private String profitLossBill;			//盈亏单号（报益）
	private Long isConfirm;					//8000保存(未入库) 8002确认(已入库)
	
	
	private Long stockOutId;				//销售出库ID(销售退货)
	private Long payMent;					//付款方式
	private Double rate;					//税率
	
	// Constructors

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Long getPayMent() {
		return payMent;
	}

	public void setPayMent(Long payMent) {
		this.payMent = payMent;
	}

	/** default constructor */
	public TmStockIn() {
	}

	/** minimal constructor */
	public TmStockIn(Long id) {
		this.id = id;
	}

	/** full constructor */
	public TmStockIn(Long id, String stockInCode, Long supplierId,
			Long userId, Date createDate, Double totalQuantity,
			Double totalPrice, Long inType, String indent,
			String oucherCode, String oucherNo, String businessCode,Date arriveDate,Long isConfirm) {
		this.id = id;
		this.stockInCode = stockInCode;
		this.supplierId = supplierId;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.inType = inType;
		this.indent = indent;
		this.oucherCode = oucherCode;
		this.oucherNo = oucherNo;
		this.businessCode = businessCode;
		this.arriveDate = arriveDate;
		this.isConfirm = isConfirm;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockInCode() {
		return this.stockInCode;
	}

	public void setStockInCode(String stockInCode) {
		this.stockInCode = stockInCode;
	}

	public Long getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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

	public Long getInType() {
		return this.inType;
	}

	public void setInType(Long inType) {
		this.inType = inType;
	}

	public String getIndent() {
		return this.indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}

	public String getOucherCode() {
		return this.oucherCode;
	}

	public void setOucherCode(String oucherCode) {
		this.oucherCode = oucherCode;
	}

	public String getOucherNo() {
		return this.oucherNo;
	}

	public void setOucherNo(String oucherNo) {
		this.oucherNo = oucherNo;
	}

	public String getBusinessCode() {
		return this.businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Date getArriveDate() {
		return arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	public Long getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getProfitLossBill() {
		return profitLossBill;
	}

	public void setProfitLossBill(String profitLossBill) {
		this.profitLossBill = profitLossBill;
	}

	public Long getStockOutId() {
		return stockOutId;
	}

	public void setStockOutId(Long stockOutId) {
		this.stockOutId = stockOutId;
	}
	
	private String inTypeName;


	public String getInTypeName() {
		return StockTypeElements.getElementMap().get(this.inType);
	}
	
	
	private String beginDate;
	
	private String endDate;
	
	private String partCode;
	
	private String partName;

	private Long busType;
	
	public Long getBusType() {
		return busType;
	}

	public void setBusType(Long busType) {
		this.busType = busType;
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

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	
	private String userRealName;

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
}