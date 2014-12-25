package com.selfsoft.business.model;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.secrity.model.TmUser;

/**
 * TbBusinessSpecialBalance entity. @author MyEclipse Persistence Tools
 */

public class TbBusinessSpecialBalance implements java.io.Serializable {

	// Fields

	private Long id;
	private String editCode;
	private TbBusinessBalance tbBusinessBalance;
	private Long entrustId;
	private Date bananceDate;
	private Date bananceDateStart;
	private Date bananceDateEnd;
	private Long stockOutId;
	private Double workingHourFavourRate;
	private Double workingHourTotalAll;
	private Double fixPartFavourRate;
	private Double fixPartTotalAll;
	private Double solePartFavourRate;
	private Double solePartTotalAll;
	private Double balanceTotalAll;
	private TmUser tmUser;
	private Long oldPartDeal;
	private Long payPattern;
	private String chequeCode;
	private Date balanceDeadTime;
	private String remark;
	private Double saveAmount;
	private Double usedSaveAmount;
	private Double cashAmount;
	private Double payedAmount;
	private Double shouldPayAmount;
	private Set tbSpecialWorkingContents = new LinkedHashSet(); 
	private Set tbSpecialPartContents = new LinkedHashSet(); 
	private Set tbBusinessSpecialBalanceItems = new LinkedHashSet();
	
	//ADD START
	private String balanceCodeDB;
	private String entrustCodeDB;
	private String licenseCode;
	private String customerCode;
	private String customerName;
	private Long tixTypeId;
	private Long serviceId;
	private String stockOutCodeDB;
	private Date stockOutDate;
	private Long soleTypeId;
	private Long specialType;
	private Long tmBalanceId;
	private Long tmCarModelTypeId;
	
	public Long getTmCarModelTypeId() {
		return tmCarModelTypeId;
	}

	public void setTmCarModelTypeId(Long tmCarModelTypeId) {
		this.tmCarModelTypeId = tmCarModelTypeId;
	}

	public Long getTmBalanceId() {
		return tmBalanceId;
	}

	public void setTmBalanceId(Long tmBalanceId) {
		this.tmBalanceId = tmBalanceId;
	}

	public String getBalanceCodeDB() {
		return balanceCodeDB;
	}

	public void setBalanceCodeDB(String balanceCodeDB) {
		this.balanceCodeDB = balanceCodeDB;
	}

	public String getEntrustCodeDB() {
		return entrustCodeDB;
	}

	public void setEntrustCodeDB(String entrustCodeDB) {
		this.entrustCodeDB = entrustCodeDB;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
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

	public Long getTixTypeId() {
		return tixTypeId;
	}

	public void setTixTypeId(Long tixTypeId) {
		this.tixTypeId = tixTypeId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getStockOutCodeDB() {
		return stockOutCodeDB;
	}

	public void setStockOutCodeDB(String stockOutCodeDB) {
		this.stockOutCodeDB = stockOutCodeDB;
	}

	public Date getStockOutDate() {
		return stockOutDate;
	}

	public void setStockOutDate(Date stockOutDate) {
		this.stockOutDate = stockOutDate;
	}

	public Long getSoleTypeId() {
		return soleTypeId;
	}

	public void setSoleTypeId(Long soleTypeId) {
		this.soleTypeId = soleTypeId;
	}

	public Long getSpecialType() {
		return specialType;
	}

	public void setSpecialType(Long specialType) {
		this.specialType = specialType;
	}

	//ADD END
	public TbBusinessSpecialBalance() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEditCode() {
		return this.editCode;
	}

	public void setEditCode(String editCode) {
		this.editCode = editCode;
	}

	public TbBusinessBalance getTbBusinessBalance() {
		return tbBusinessBalance;
	}

	public void setTbBusinessBalance(TbBusinessBalance tbBusinessBalance) {
		this.tbBusinessBalance = tbBusinessBalance;
	}

	public Long getEntrustId() {
		return this.entrustId;
	}

	public void setEntrustId(Long entrustId) {
		this.entrustId = entrustId;
	}

	public Date getBananceDate() {
		/*if(null!=this.tbBusinessBalance){
			this.bananceDate = this.tbBusinessBalance.getBananceDate();
		}
		*/
		return this.bananceDate;
	}

	public void setBananceDate(Date bananceDate) {
		this.bananceDate = bananceDate;
	}

	public Date getBananceDateStart() {
		return bananceDateStart;
	}

	public void setBananceDateStart(Date bananceDateStart) {
		this.bananceDateStart = bananceDateStart;
	}

	public Date getBananceDateEnd() {
		return bananceDateEnd;
	}

	public void setBananceDateEnd(Date bananceDateEnd) {
		this.bananceDateEnd = bananceDateEnd;
	}

	public Long getStockOutId() {
		return this.stockOutId;
	}

	public void setStockOutId(Long stockOutId) {
		this.stockOutId = stockOutId;
	}

	public Double getWorkingHourFavourRate() {
		return this.workingHourFavourRate;
	}

	public void setWorkingHourFavourRate(Double workingHourFavourRate) {
		this.workingHourFavourRate = workingHourFavourRate;
	}

	public Double getWorkingHourTotalAll() {
		return this.workingHourTotalAll;
	}

	public void setWorkingHourTotalAll(Double workingHourTotalAll) {
		this.workingHourTotalAll = workingHourTotalAll;
	}

	public Double getFixPartFavourRate() {
		return this.fixPartFavourRate;
	}

	public void setFixPartFavourRate(Double fixPartFavourRate) {
		this.fixPartFavourRate = fixPartFavourRate;
	}

	public Double getFixPartTotalAll() {
		return this.fixPartTotalAll;
	}

	public void setFixPartTotalAll(Double fixPartTotalAll) {
		this.fixPartTotalAll = fixPartTotalAll;
	}

	public Double getSolePartFavourRate() {
		return this.solePartFavourRate;
	}

	public void setSolePartFavourRate(Double solePartFavourRate) {
		this.solePartFavourRate = solePartFavourRate;
	}

	public Double getSolePartTotalAll() {
		return this.solePartTotalAll;
	}

	public void setSolePartTotalAll(Double solePartTotalAll) {
		this.solePartTotalAll = solePartTotalAll;
	}

	public Double getBalanceTotalAll() {
		return this.balanceTotalAll;
	}

	public void setBalanceTotalAll(Double balanceTotalAll) {
		this.balanceTotalAll = balanceTotalAll;
	}

	

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public Long getOldPartDeal() {
		return this.oldPartDeal;
	}

	public void setOldPartDeal(Long oldPartDeal) {
		this.oldPartDeal = oldPartDeal;
	}

	public Long getPayPattern() {
		return this.payPattern;
	}

	public void setPayPattern(Long payPattern) {
		this.payPattern = payPattern;
	}

	public String getChequeCode() {
		return this.chequeCode;
	}

	public void setChequeCode(String chequeCode) {
		this.chequeCode = chequeCode;
	}

	public Date getBalanceDeadTime() {
		return this.balanceDeadTime;
	}

	public void setBalanceDeadTime(Date balanceDeadTime) {
		this.balanceDeadTime = balanceDeadTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getSaveAmount() {
		return this.saveAmount;
	}

	public void setSaveAmount(Double saveAmount) {
		this.saveAmount = saveAmount;
	}

	public Double getUsedSaveAmount() {
		return this.usedSaveAmount;
	}

	public void setUsedSaveAmount(Double usedSaveAmount) {
		this.usedSaveAmount = usedSaveAmount;
	}

	public Double getCashAmount() {
		return this.cashAmount;
	}

	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public Double getPayedAmount() {
		return this.payedAmount;
	}

	public void setPayedAmount(Double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public Double getShouldPayAmount() {
		return this.shouldPayAmount;
	}

	public void setShouldPayAmount(Double shouldPayAmount) {
		this.shouldPayAmount = shouldPayAmount;
	}

	public Set getTbSpecialWorkingContents() {
		return tbSpecialWorkingContents;
	}

	public void setTbSpecialWorkingContents(Set tbSpecialWorkingContents) {
		this.tbSpecialWorkingContents = tbSpecialWorkingContents;
	}

	public Set getTbSpecialPartContents() {
		return tbSpecialPartContents;
	}

	public void setTbSpecialPartContents(Set tbSpecialPartContents) {
		this.tbSpecialPartContents = tbSpecialPartContents;
	}

	public Set getTbBusinessSpecialBalanceItems() {
		return tbBusinessSpecialBalanceItems;
	}

	public void setTbBusinessSpecialBalanceItems(Set tbBusinessSpecialBalanceItems) {
		this.tbBusinessSpecialBalanceItems = tbBusinessSpecialBalanceItems;
	}

	//委托书号
	private String entrustCode;
	//结算员
	private String userRealName;
	//销售单号
	private String stockOutCode;
	//结算单号
	private String balanceCode;
	
	public String getEntrustCode() {
		
		if(null!=this.tbBusinessBalance&&null!=this.tbBusinessBalance.getTbFixEntrust()){
			entrustCode = this.tbBusinessBalance.getTbFixEntrust().getEntrustCode();
		}
		return entrustCode;
	}

	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}

	public String getUserRealName() {
		if(null!=this.tmUser){
			userRealName = this.tmUser.getUserRealName();
		}
		
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getStockOutCode() {
		
		if(null!=this.tbBusinessBalance&&null!=this.tbBusinessBalance.getTmStockOut()){
			stockOutCode = this.tbBusinessBalance.getTmStockOut().getStockOutCode();
		}
		return stockOutCode;
	}

	public void setStockOutCode(String stockOutCode) {
		this.stockOutCode = stockOutCode;
	}

	public String getBalanceCode() {
		
		if(null!=this.tbBusinessBalance){
			balanceCode = this.tbBusinessBalance.getBalanceCode();
		}
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	
	private TbCarInfo tbCarInfo;
	
	private TbCustomer tbCustomer;

	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}
	
	private String modelCode;

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	
}