package com.selfsoft.business.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.selfsoft.framework.common.Constants;

public class TmStockOutDetVo {

	private Long stockOutId;					//出库ID
	private Long stockOutDetailId;				//出库详细ID
	private String stockOutCode;				//出库单号
	private Date stockOutDate;					//出库日期
	private Long partinfoId;					//配件ID
	private String partCode;					//配件代码
	private String partName;					//配件名称
	private String unitName;					//单位
	private Double quantity;					//出库数量
	private Double price;						//出库价格
	private Long storeHoseId;					//仓库ID
	private String houseCode;					//仓库代码
	private String houseName;					//仓库名称
	private Double sellPrice;					//销售金额小计
	private Double costPrice;					//成本价
	
	//add by CCR  打印报表的时候需要改值
	private String storeLocation;               //仓位
	
	private Long stockInDetailId;				//采购入库明细ID（采购退货）
	private String trustBill;					//委托书号
	private Long customerId;					
	private String customerName;
	
	private Long isFree;						//是否免费
	private String zl;							//账类
	private String xmlx;						//项目类型
	private String projectType;					//维修项目类型
	
	
	//结算单ID
	private Long balanceId;
	
	public Long getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public Long getIsFree() {
		return isFree;
	}

	public void setIsFree(Long isFree) {
		this.isFree = isFree;
	}

	public TmStockOutDetVo(Long stockOutId, Long stockOutDetailId,
			String stockOutCode, Date stockOutDate, Long partinfoId,
			String partCode, String partName, String unitName, Double quantity,
			Double price, Long storeHoseId, String houseCode, String houseName,
			Double sellPrice, Double costPrice, Long stockInDetailId,
			String trustBill, Long customerId, String customerName,
			Long isFree, Long balanceId,String zl,String xmlx,String projectType ) {
		super();
		this.stockOutId = stockOutId;
		this.stockOutDetailId = stockOutDetailId;
		this.stockOutCode = stockOutCode;
		this.stockOutDate = stockOutDate;
		this.partinfoId = partinfoId;
		this.partCode = partCode;
		this.partName = partName;
		this.unitName = unitName;
		this.quantity = quantity;
		this.price = price;
		this.storeHoseId = storeHoseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.sellPrice = sellPrice;
		this.costPrice = costPrice;
		this.stockInDetailId = stockInDetailId;
		this.trustBill = trustBill;
		this.customerId = customerId;
		this.customerName = customerName;
		this.isFree = isFree;
		this.balanceId = balanceId;
		this.zl = zl;
		this.xmlx = xmlx;
		this.projectType = projectType;
		
		if(null != this.isFree){
			if(this.isFree.equals(Constants.FREESYMBOL_NO))
				this.freeName = Constants.FREESYMBOL_NO_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SB))
				this.freeName = Constants.FREESYMBOL_SB_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FG))
				this.freeName = Constants.FREESYMBOL_FG_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FW))
				this.freeName = Constants.FREESYMBOL_FW_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SP))
				this.freeName = Constants.FREESYMBOL_SP_SHOW;
		}
		
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTrustBill() {
		return trustBill;
	}
	public void setTrustBill(String trustBill) {
		this.trustBill = trustBill;
	}
	public Double getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public Long getStockOutId() {
		return stockOutId;
	}
	public void setStockOutId(Long stockOutId) {
		this.stockOutId = stockOutId;
	}
	public Long getStockOutDetailId() {
		return stockOutDetailId;
	}
	public void setStockOutDetailId(Long stockOutDetailId) {
		this.stockOutDetailId = stockOutDetailId;
	}
	public String getStockOutCode() {
		return stockOutCode;
	}
	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}
	public Date getStockOutDate() {
		return stockOutDate;
	}
	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}
	public Long getPartinfoId() {
		return partinfoId;
	}
	public void setPartinfoId(Long partinfoId) {
		this.partinfoId = partinfoId;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	public Long getStoreHoseId() {
		return storeHoseId;
	}
	public void setStoreHoseId(Long storeHoseId) {
		this.storeHoseId = storeHoseId;
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
	public TmStockOutDetVo(Long stockOutId, Long stockOutDetailId,
			String stockOutCode, Date stockOutDate, Long partinfoId,
			String partCode, String partName, String unitName, Double quantity,
			Double price, Long storeHoseId, String houseCode, String houseName,
			Double sellPrice, Double costPrice,Long stockInDetailId,
			Long isFree, String storeLocation,String zl,String xmlx,String projectType) {
		super();
		this.stockOutId = stockOutId;
		this.stockOutDetailId = stockOutDetailId;
		this.stockOutCode = stockOutCode;
		this.stockOutDate = stockOutDate;
		this.partinfoId = partinfoId;
		this.partCode = partCode;
		this.partName = partName;
		this.unitName = unitName;
		this.quantity = quantity;
		this.price = price;
		this.storeHoseId = storeHoseId;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.sellPrice = sellPrice;
		this.costPrice = costPrice;
		this.stockInDetailId = stockInDetailId;
		this.isFree = isFree;
		this.storeLocation = storeLocation;
		this.zl = zl;
		this.xmlx = xmlx;
		this.projectType = projectType;
		
		if(null != this.isFree){
			
			if(this.isFree.equals(Constants.FREESYMBOL_NO))
				this.freeName = Constants.FREESYMBOL_NO_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SB))
				this.freeName = Constants.FREESYMBOL_SB_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FG))
				this.freeName = Constants.FREESYMBOL_FG_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FW))
				this.freeName = Constants.FREESYMBOL_FW_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SP))
				this.freeName = Constants.FREESYMBOL_SP_SHOW;
			
		}
		
	}
	public Long getStockInDetailId() {
		return stockInDetailId;
	}
	public void setStockInDetailId(Long stockInDetailId) {
		this.stockInDetailId = stockInDetailId;
	}
	
	private String freeName;




	public String getFreeName() {
		
		if(null != this.isFree){
			
			if(this.isFree.equals(Constants.FREESYMBOL_NO))
				return Constants.FREESYMBOL_NO_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SB))
				return Constants.FREESYMBOL_SB_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FG))
				return Constants.FREESYMBOL_FG_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FW))
				return Constants.FREESYMBOL_FW_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SP))
				return Constants.FREESYMBOL_SP_SHOW;
		}
		
		
		
		return null;
	}
	
	//add by ccr  单条配件记录的金额
	private Double total = 0.00D;

	public Double getTotal() {
		
		if(null!=this.quantity&&null!=this.price){
			BigDecimal d = new BigDecimal("0.00");
			
			d = new BigDecimal(String.valueOf(this.quantity)).multiply(new BigDecimal(String.valueOf(this.price))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
			total = d.doubleValue();
		}
		
		
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	private TmStockOutVo tmStockOutVo;

	public TmStockOutVo getTmStockOutVo() {
		return tmStockOutVo;
	}

	public void setTmStockOutVo(TmStockOutVo tmStockOutVo) {
		this.tmStockOutVo = tmStockOutVo;
	}
	
	private String modeName;

	private String customerCode;
	
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}
	
	private String userRealName;
	
	private Long outType;
	
	private String outTypeName;

	public Long getOutType() {
		return outType;
	}

	public void setOutType(Long outType) {
		this.outType = outType;
	}

	public String getOutTypeName() {
		return outTypeName;
	}

	public void setOutTypeName(String outTypeName) {
		this.outTypeName = outTypeName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getZl() {
		return zl;
	}

	public void setZl(String zl) {
		this.zl = zl;
	}

	public String getXmlx() {
		return xmlx;
	}

	public void setXmlx(String xmlx) {
		this.xmlx = xmlx;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

}
