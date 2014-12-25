package com.selfsoft.business.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TmStockInDetailVo {

	private Long stockInId;						//入库单ID
	private Long stockInDetailId;				//明细入库ID
	private String stockInCode;           		//入库单号
	private Long partinfoId;					//配件id
	private Double quantity;					//入库数量
	private Double costPrice;					//入库价格
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private Long houseId;						//仓库ID
	private String houseCode;					//仓库代码
	private String houseName;					//仓库名称
	private String unitName;					//计量单位
	private Double storeCostPrice;				//成本价
	private Long stockOutDetailId;				//出库明细id（销售退货）
	
	
	private Long customerId;					//供应商ID
	private String customerCode;				//供应商代码
	private String customerName;				//供应商名称
	private Date arriveDate;					//入库日期
	private String indent;						//入库凭证
	private String storeLocation;				//仓位
	
	
	private String modelName;					//车型
	private String productArea;					//产地
	
	private Double hasQuantity;					//已退数量
	
	private Long userId;						//操作人ID
	private String userName;						//操作人
	private Double ratePrice;					//含税单价
	
	public Double getRatePrice() {
		return ratePrice;
	}
	public void setRatePrice(Double ratePrice) {
		this.ratePrice = ratePrice;
	}
	public Double getHasQuantity() {
		return hasQuantity;
	}
	public void setHasQuantity(Double hasQuantity) {
		this.hasQuantity = hasQuantity;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getProductArea() {
		return productArea;
	}
	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	public String getIndent() {
		return indent;
	}
	public void setIndent(String indent) {
		this.indent = indent;
	}
	public Double getStoreCostPrice() {
		return storeCostPrice;
	}
	public void setStoreCostPrice(Double storeCostPrice) {
		this.storeCostPrice = storeCostPrice;
	}
	public Long getStockInId() {
		return stockInId;
	}
	public void setStockInId(Long stockInId) {
		this.stockInId = stockInId;
	}
	public Long getStockInDetailId() {
		return stockInDetailId;
	}
	public void setStockInDetailId(Long stockInDetailId) {
		this.stockInDetailId = stockInDetailId;
	}
	public String getStockInCode() {
		return stockInCode;
	}
	public void setStockInCode(String stockInCode) {
		this.stockInCode = stockInCode;
	}
	public Long getPartinfoId() {
		return partinfoId;
	}
	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
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
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public String getHouseCode() {
		return houseCode;
	}
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	public Long getStockOutDetailId() {
		return stockOutDetailId;
	}
	public void setStockOutDetailId(Long stockOutDetailId) {
		this.stockOutDetailId = stockOutDetailId;
	}
	public TmStockInDetailVo(Long stockInId, Long stockInDetailId,
			String stockInCode, Long partinfoId, Double quantity,
			Double costPrice, String partCode, String partName, Long houseId,
			String houseCode, String houseName, String unitName,
			Double storeCostPrice, Long stockOutDetailId,String storeLocation,
			String modelName,String productArea) {
		super();
		this.stockInId = stockInId;
		this.stockInDetailId = stockInDetailId;
		this.stockInCode = stockInCode;
		this.partinfoId = partinfoId;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.partCode = partCode;
		this.partName = partName;
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.unitName = unitName;
		this.storeCostPrice = storeCostPrice;
		this.stockOutDetailId = stockOutDetailId;
		this.storeLocation = storeLocation;
		this.modelName = modelName;
		this.productArea = productArea;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public Date getArriveDate() {
		return arriveDate;
	}
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	public TmStockInDetailVo(Long stockInId, Long stockInDetailId,
			String stockInCode, Long partinfoId, Double quantity,
			Double costPrice, String partCode, String partName, Long houseId,
			String houseCode, String houseName, String unitName,
			Double storeCostPrice, Long stockOutDetailId, Long customerId,
			String customerCode, String customerName, Date arriveDate,String indent) {
		super();
		this.stockInId = stockInId;
		this.stockInDetailId = stockInDetailId;
		this.stockInCode = stockInCode;
		this.partinfoId = partinfoId;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.partCode = partCode;
		this.partName = partName;
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.unitName = unitName;
		this.storeCostPrice = storeCostPrice;
		this.stockOutDetailId = stockOutDetailId;
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.arriveDate = arriveDate;
		this.indent = indent;
	}
	
	//add by ccr  单条配件记录的金额
	private Double total = 0.00D;

	public Double getTotal() {
		
		if(null!=this.quantity&&null!=this.costPrice){
			BigDecimal d = new BigDecimal("0.00");
			
			d = new BigDecimal(String.valueOf(this.quantity)).multiply(new BigDecimal(String.valueOf(this.costPrice))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
			total = d.doubleValue();
		}
		
		
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public TmStockInDetailVo() {
		super();
	}
	
	
	/* 假单导出属性  */
	private String xlsPK;

	private Double stockTotalPrice;
	
	private String xlsArriveDate;
	
	
	public String getXlsArriveDate() {
		return xlsArriveDate;
	}
	public void setXlsArriveDate(String xlsArriveDate) {
		this.xlsArriveDate = xlsArriveDate;
	}
	public Double getStockTotalPrice() {
		return stockTotalPrice;
	}
	public void setStockTotalPrice(Double stockTotalPrice) {
		this.stockTotalPrice = stockTotalPrice;
	}
	public String getXlsPK() {
		return xlsPK;
	}
	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
	
	
	public TmStockInDetailVo(Long stockInId, Long stockInDetailId,
			String stockInCode, Long partinfoId, Double quantity,
			Double costPrice, String partCode, String partName, Long houseId,
			String houseCode, String houseName, String unitName,
			Double storeCostPrice, Long stockOutDetailId,String storeLocation,
			String modelName,String productArea,Double hasQuantity) {
		super();
		this.stockInId = stockInId;
		this.stockInDetailId = stockInDetailId;
		this.stockInCode = stockInCode;
		this.partinfoId = partinfoId;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.partCode = partCode;
		this.partName = partName;
		this.houseId = houseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.unitName = unitName;
		this.storeCostPrice = storeCostPrice;
		this.stockOutDetailId = stockOutDetailId;
		this.storeLocation = storeLocation;
		this.modelName = modelName;
		this.productArea = productArea;
		this.hasQuantity = hasQuantity;
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
	
	public TmStockInDetailVo(Long stockInId, Long stockInDetailId,
			String stockInCode, Long partinfoId,  Double quantity,Double costPrice, 
			 Long stockOutDetailId,String productArea,Double hasQuantity) {
		super();
		this.stockInId = stockInId;
		this.stockInDetailId = stockInDetailId;
		this.partinfoId = partinfoId;
		this.stockInCode = stockInCode;
		this.quantity = quantity;
		this.costPrice = costPrice;
		this.stockOutDetailId = stockOutDetailId;
		this.productArea = productArea;
		this.hasQuantity = hasQuantity;
	}
}
