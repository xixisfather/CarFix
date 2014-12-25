package com.selfsoft.business.vo;


public class TmStockInImportVo {

	private String xlsPK;
	private String supplierCode;			//供货商代码
	private String indent;					//订货单号(采购)
	private String oucherCode;				//入库凭证(采购)
	private String partCode;				//配件代码
	private Double quantity;				//数量
	private Double price;					//价格
	private String productArea;				//产地
	private String userName;				//操作人
	private String payMentName;				//付款方式
	private Long payMent;
	private Long supplierId;
	private Long partInfoId;
	private Long userId;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Long getPartInfoId() {
		return partInfoId;
	}
	public void setPartInfoId(Long partInfoId) {
		this.partInfoId = partInfoId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	public String getPayMentName() {
		return payMentName;
	}
	public void setPayMentName(String payMentName) {
		this.payMentName = payMentName;
	}
	public Long getPayMent() {
		return payMent;
	}
	public void setPayMent(Long payMent) {
		this.payMent = payMent;
	}
	public String getXlsPK() {
		return xlsPK;
	}
	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
}
