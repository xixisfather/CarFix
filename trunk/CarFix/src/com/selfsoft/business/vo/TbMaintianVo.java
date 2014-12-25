package com.selfsoft.business.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;

public class TbMaintianVo {

	private String maintainCode;			//维修发料单号
	private Long entrustId;					//委托书ID
	private String entrustCode;				//委托书号
	
	
	private Long partId;					//配件ID
	private Double partQuantity;			//数量
	private Double price;					//单价
	private String partCode;				//配件代码
	private String partName;				//配件名称
	private String houseCode;				//仓库代码
	private String houseName;				//仓库名称
	private String unitName;				//计量单位
	private Long cliamPartPersonId;			//领用人
	private String userRealName;			//领用人姓名
	
	private Long isConfirm;					//出库单号状态(8000-保存,8002-出库,8004-已结算,8006-再结算)	
	
	
	private Long isFree;						//是否免费
	private Long balanceId;						//结算ID
	
	
	private String customerName;						//客户名称
	private String licenseCode;							//牌照号
	private String fixType;								//修理类型
	private Date fixDate;								//修理日期
	private String storeLocation;						//仓位
	private Double totalPrice;							//维修发料金额合计
	private String partNameDB;
	private TbPartInfo tbPartInfo;
	private String zl;
	private String xmlx;
	private String zlShow;
	private String xmlxShow;
	private String projectType;
	
	private Double jxPrice;
	private String isPrint;
	private Long tbMaintainId;
	
	private String userServiceName;					//服务顾问
	private Date stockoutDate;						//出库日期
	
	public Date getStockoutDate() {
		return stockoutDate;
	}
	public void setStockoutDate(Date stockoutDate) {
		this.stockoutDate = stockoutDate;
	}
	public String getUserServiceName() {
		return userServiceName;
	}
	public void setUserServiceName(String userServiceName) {
		this.userServiceName = userServiceName;
	}
	public Long getTbMaintainId() {
		return tbMaintainId;
	}
	public void setTbMaintainId(Long tbMaintainId) {
		this.tbMaintainId = tbMaintainId;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public Double getJxPrice() {
		return CommonMethod.convertRadixPoint(this.partQuantity * this.price, 2) ;
	}
	public void setJxPrice(Double jxPrice) {
		this.jxPrice = jxPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getIsConfirm() {
		return isConfirm;
	}
	public void setIsConfirm(Long isConfirm) {
		this.isConfirm = isConfirm;
	}
	public String getMaintainCode() {
		return maintainCode;
	}
	public void setMaintainCode(String maintainCode) {
		this.maintainCode = maintainCode;
	}
	public Long getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}
	public String getEntrustCode() {
		return entrustCode;
	}
	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}
	
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	public Double getPartQuantity() {
		return partQuantity;
	}
	public void setPartQuantity(Double partQuantity) {
		this.partQuantity = partQuantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
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
	public Long getCliamPartPersonId() {
		return cliamPartPersonId;
	}
	public void setCliamPartPersonId(Long cliamPartPersonId) {
		this.cliamPartPersonId = cliamPartPersonId;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public TbMaintianVo(){}
	public TbMaintianVo(String maintainCode, Long entrustId, String entrustCode,Long isConfirm,
			String licenseCode,String customerName,Double totalPrice) {
		super();
		this.maintainCode = maintainCode;
		this.entrustId = entrustId;
		this.entrustCode = entrustCode;
		this.isConfirm = isConfirm;
		this.licenseCode = licenseCode;
		this.customerName = customerName;
		this.totalPrice = totalPrice;
	}
	public TbMaintianVo(Long partId, Double partQuantity, Double price,
			String partCode, String partName, String houseCode,
			String houseName, String unitName, Long cliamPartPersonId,
			String userRealName,String maintainCode,Long isFree,
			String customerName, String licenseCode , String fixType,
			Date fixDate, Long balanceId,String entrustCode,
			String storeLocation,Double totalPrice,String zl,String xmlx,String projectType,String isPrint,
			Long tbMaintainId) {
		super();
		this.partId = partId;
		this.partQuantity = partQuantity;
		this.price = price;
		this.partCode = partCode;
		this.partName = partName;
		this.houseCode = houseCode;
		this.houseName = houseName;
		this.unitName = unitName;
		this.cliamPartPersonId = cliamPartPersonId;
		this.userRealName = userRealName;
		this.maintainCode = maintainCode;
		this.isFree = isFree;
		this.customerName = customerName;
		this.licenseCode = licenseCode;
		this.fixType = fixType;
		this.fixDate = fixDate;
		this.balanceId  = balanceId;
		this.entrustCode = entrustCode;
		this.storeLocation = storeLocation;
		this.totalPrice = totalPrice;
		this.zl = zl;
		this.xmlx = xmlx;
		this.projectType = projectType;
		this.isPrint = isPrint;
		this.tbMaintainId = tbMaintainId;
	}
	
	
	private String confirmName;

	public String getConfirmName() {
		
		if(this.isConfirm != null){
			
			if(this.isConfirm.equals(Constants.CONFIRM))
				return "出库";
			if(this.isConfirm.equals(Constants.NOT_CONFIRM))
				return "保存";
			if(this.isConfirm.equals(Constants.RE_BALANCE))
				return "再结算";
			if(this.isConfirm.equals(Constants.FINSH_BALANCE))
				return "已结算";
		}
		
		
		return null;
	}
	public Long getIsFree() {
		return isFree;
	}
	public void setIsFree(Long isFree) {
		this.isFree = isFree;
	}
	public Long getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}
	
	//add by ccr  单条配件记录的金额
	private Double total = 0.00D;

	public Double getTotal() {
		
		if(null!=this.partQuantity&&null!=this.price){
			BigDecimal d = new BigDecimal("0.00");
			
			d = new BigDecimal(String.valueOf(this.partQuantity)).multiply(new BigDecimal(String.valueOf(this.price))).setScale(2,BigDecimal.ROUND_HALF_UP);
		
			total = d.doubleValue();
		}
		
		
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	private String freeName;




	public String getFreeName() {
		
		if(this.isFree != null){
			
			if(this.isFree.equals(Constants.FREESYMBOL_NO))
				return Constants.FREESYMBOL_NO_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FG))
				return Constants.FREESYMBOL_FG_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_FW))
				return Constants.FREESYMBOL_FW_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SB))
				return Constants.FREESYMBOL_SB_SHOW;
			if(this.isFree.equals(Constants.FREESYMBOL_SP))
				return Constants.FREESYMBOL_SP_SHOW;
			
			
		}
		
		
		return null;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getFixType() {
		return fixType;
	}
	public void setFixType(String fixType) {
		this.fixType = fixType;
	}
	public Date getFixDate() {
		return fixDate;
	}
	public void setFixDate(Date fixDate) {
		this.fixDate = fixDate;
	}
	public String getStoreLocation() {
		return storeLocation;
	}
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	public String getPartNameDB() {
		return partNameDB;
	}
	public void setPartNameDB(String partNameDB) {
		this.partNameDB = partNameDB;
	}
	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}
	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
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
	
	public String getZlShow() {
		return Constants.zlMap().get(this.getZl());
	}

	public void setZlShow(String zlShow) {
		this.zlShow = zlShow;
	}

	public String getXmlxShow() {
		return Constants.xmlxMap().get(this.getXmlx());
	}

	public void setXmlxShow(String xmlxShow) {
		this.xmlxShow = xmlxShow;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	/* 假单导出属性  */
	private String xlsPK;

	public String getXlsPK() {
		return xlsPK;
	}
	public void setXlsPK(String xlsPK) {
		this.xlsPK = xlsPK;
	}
}
