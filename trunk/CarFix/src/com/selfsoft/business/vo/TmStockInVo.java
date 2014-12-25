package com.selfsoft.business.vo;

import java.util.Date;

public class TmStockInVo {

	private Long stockInId;					//入库ID
	private String stockInCode;				//入库单号
	private Double totalQuantity;			//合计数量
	private Double totalPrice;				//合计总价
	private String indent;					//订货单号(采购)
	private String oucherCode;				//入库凭证(采购)
	private Date arriveDate;				//到货日期
	private Long inType;					//入库类型
	
	private Long supplierId;				//供货商ID
	private String supplierName;			//供应商名称
	
	private String supplierCode;			//供应商号
	private Long userId;					//操作员ID
	private String userName;				//操作人
	private Long payMent;					//支付方式ID
	private String payMentShow;					//支付方式
	
	public TmStockInVo(){}
	
	public TmStockInVo(Long stockInId, String stockInCode,
			Double totalQuantity, Double totalPrice, String indent,
			String oucherCode, Date arriveDate, Long inType, Long supplierId,
			String supplierName, Long userId, String userName,String supplierCode) {
		super();
		this.stockInId = stockInId;
		this.stockInCode = stockInCode;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.indent = indent;
		this.oucherCode = oucherCode;
		this.arriveDate = arriveDate;
		this.inType = inType;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.userId = userId;
		this.userName = userName;
		this.supplierCode = supplierCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public TmStockInVo(Long stockInId, String stockInCode,
			Double totalQuantity, Double totalPrice, String indent,
			String oucherCode, Date arriveDate, Long inType, Long supplierId,
			String supplierName,String userName,String supplierCode) {
		super();
		this.stockInId = stockInId;
		this.stockInCode = stockInCode;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.indent = indent;
		this.oucherCode = oucherCode;
		this.arriveDate = arriveDate;
		this.inType = inType;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.userName = userName;
		this.supplierCode = supplierCode;
	}
	public Long getInType() {
		return inType;
	}
	public void setInType(Long inType) {
		this.inType = inType;
	}
	public Long getStockInId() {
		return stockInId;
	}
	public void setStockInId(Long stockInId) {
		this.stockInId = stockInId;
	}
	public String getStockInCode() {
		return stockInCode;
	}
	public void setStockInCode(String stockInCode) {
		this.stockInCode = stockInCode;
	}
	public Double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getIndent() {
		return indent;
	}
	public void setIndent(String indent) {
		this.indent = indent;
	}
	public String getOucherCode() {
		return oucherCode;
	}
	public void setOucherCode(String oucherCode) {
		this.oucherCode = oucherCode;
	}
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Long getPayMent() {
		return payMent;
	}

	public void setPayMent(Long payMent) {
		this.payMent = payMent;
	}

	public String getPayMentShow() {
		return payMentShow;
	}

	public void setPayMentShow(String payMentShow) {
		this.payMentShow = payMentShow;
	}
	
	
	
	
}
