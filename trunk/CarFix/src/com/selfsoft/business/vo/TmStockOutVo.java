package com.selfsoft.business.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmStockOutVo {

	private Long stockOutId;							//出库ID
	private String stockOutCode;						//出库单号
	private String trustBill;							//委托书号(销售)
	private Date stockOutDate;							//出库日期
	private Long userId;								//操作人
	private Date createDate;							//操作日期
	private Double totalQuantity;						//合计数量
	private Double totalPrice;							//合计总价
	
	private Long customerBill;							//客户号(销售,调拨)
	private String customerName;						//客户名称
	private Long soleTypeId;							//销售类型(销售)
	private String soleName;							//销售名称
	private String userRealName;						//领用人
	private Long outType;								//出库类型
	
	private Long stockInId;								//采购入库ID(采购退货)
	private String stockInCode;							//采购单号
	private Long isConfirm;								//出库单号状态(8000-保存,8002-出库,8004-已结算,8006-再结算)
	
	private String customerCode;						//客户号
	private String userName;							//操作人
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public TmStockOutVo(String stockOutCode) {
		super();
		this.stockOutCode = stockOutCode;
	}
	public Long getOutType() {
		return outType;
	}
	public void setOutType(Long outType) {
		this.outType = outType;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public Long getStockOutId() {
		return stockOutId;
	}
	public void setStockOutId(Long stockOutId) {
		this.stockOutId = stockOutId;
	}
	public String getStockOutCode() {
		return stockOutCode;
	}
	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}
	public String getTrustBill() {
		return trustBill;
	}
	public void setTrustBill(String trustBill) {
		this.trustBill = trustBill;
	}
	public Date getStockOutDate() {
		return stockOutDate;
	}
	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
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
	public Long getCustomerBill() {
		return customerBill;
	}
	public void setCustomerBill(Long customerBill) {
		this.customerBill = customerBill;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getSoleTypeId() {
		return soleTypeId;
	}
	public void setSoleTypeId(Long soleTypeId) {
		this.soleTypeId = soleTypeId;
	}
	public String getSoleName() {
		return soleName;
	}
	public void setSoleName(String soleName) {
		this.soleName = soleName;
	}
	public TmStockOutVo(Long stockOutId, String stockOutCode, String trustBill,
			Date stockOutDate, Long userId, Date createDate,
			Double totalQuantity, Double totalPrice, Long customerBill,
			String customerName, Long soleTypeId, String soleName
			,String userRealName,Long outType,Long isConfirm,String customerCode,String userName) {
		super();
		this.stockOutId = stockOutId;
		this.stockOutCode = stockOutCode;
		this.trustBill = trustBill;
		this.stockOutDate = stockOutDate;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.customerBill = customerBill;
		this.customerName = customerName;
		this.soleTypeId = soleTypeId;
		this.soleName = soleName;
		this.userRealName = userRealName;
		this.outType = outType;
		this.isConfirm = isConfirm;
		this.customerCode = customerCode;
		this.userName = userName;
	}
	
	private String outTypeName;

	public String getOutTypeName() {
		return StockTypeElements.getElementMap().get(this.outType);
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
	
	
	public TmStockOutVo(Long stockOutId, String stockOutCode, String trustBill,
			Date stockOutDate, Long userId, Date createDate,
			Double totalQuantity, Double totalPrice, Long customerBill,
			String customerName, Long soleTypeId, String soleName,
			String userRealName, Long outType, Long stockInId,
			String stockInCode) {
		super();
		this.stockOutId = stockOutId;
		this.stockOutCode = stockOutCode;
		this.trustBill = trustBill;
		this.stockOutDate = stockOutDate;
		this.userId = userId;
		this.createDate = createDate;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
		this.customerBill = customerBill;
		this.customerName = customerName;
		this.soleTypeId = soleTypeId;
		this.soleName = soleName;
		this.userRealName = userRealName;
		this.outType = outType;
		this.stockInId = stockInId;
		this.stockInCode = stockInCode;
		
	}
	public Long getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}
	
	private String confirmName;

	public String getConfirmName() {
		if(this.isConfirm.equals(Constants.CONFIRM))
			return "出库";
		if(this.isConfirm.equals(Constants.NOT_CONFIRM))
			return "保存";
		if(this.isConfirm.equals(Constants.RE_BALANCE))
			return "再结算";
		if(this.isConfirm.equals(Constants.FINSH_BALANCE))
			return "已结算";
		return null;
	}
	
	//add by ccr  单条配件记录的金额
	private Double total = 0.00D;

	public Double getTotal() {
		
		if(null!=this.totalQuantity&&null!=this.totalPrice){
			BigDecimal d = new BigDecimal("0.00");
			
			d = new BigDecimal(String.valueOf(this.totalQuantity)).multiply(new BigDecimal(String.valueOf(this.totalPrice))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
			total = d.doubleValue();
		}
		
		
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	//销售类型
	private String soleTypeName;

	public String getSoleTypeName() {
		
		if(StringUtils.isBlank(this.trustBill))
			return "单独销售";
		else
			return "委托书销售";
		
	}
	
}
