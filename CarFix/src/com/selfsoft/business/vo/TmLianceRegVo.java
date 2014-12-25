package com.selfsoft.business.vo;

import java.util.Date;
/**
 *	借进 
 */
public class TmLianceRegVo {

	private Long lianceId;				//借进ID
	private String lianceBill;			//借进单据
	private Date lianceDate;			//借进日期
	private Long userId;
	private Date createDate;
	private Double totalPrice;
	private Double totalQuantity;
	private Long dutyPeople;			//经办人ID
	private Long supplierId;			//供应商ID
	private String customerCode;		//供应商号
	private String customerName;		//供应商名称
	private String userName;			//经办人
	private String operationName;		//操作者姓名
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public TmLianceRegVo(Long lianceId, String lianceBill, Date lianceDate,
			Long userId, Date createDate, Double totalPrice,
			Double totalQuantity, Long dutyPeople, Long supplierId,
			String customerCode, String customerName,
			String userName , String operationName) {
		super();
		this.lianceId = lianceId;
		this.lianceBill = lianceBill;
		this.lianceDate = lianceDate;
		this.userId = userId;
		this.createDate = createDate;
		this.totalPrice = totalPrice;
		this.totalQuantity = totalQuantity;
		this.dutyPeople = dutyPeople;
		this.supplierId = supplierId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.userName = userName;
		this.operationName = operationName;
	}
	public Long getLianceId() {
		return lianceId;
	}
	public void setLianceId(Long lianceId) {
		this.lianceId = lianceId;
	}
	public String getLianceBill() {
		return lianceBill;
	}
	public void setLianceBill(String lianceBill) {
		this.lianceBill = lianceBill;
	}
	public Date getLianceDate() {
		return lianceDate;
	}
	public void setLianceDate(Date lianceDate) {
		this.lianceDate = lianceDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public Long getDutyPeople() {
		return dutyPeople;
	}
	public void setDutyPeople(Long dutyPeople) {
		this.dutyPeople = dutyPeople;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
