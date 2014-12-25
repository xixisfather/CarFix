package com.selfsoft.baseinformation.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.framework.common.Constants;

/**
 * TbCarInfo entity. @author MyEclipse Persistence Tools
 */

public class TbCarInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String licenseCode;
	private String chassisCode;					//底盘号
	private String insureCardCode;
	private String factory;
	private TmCarModelType tmCarModelType;
	private String color;
	private String engineCode;
	private String engineType;
	private String changeBoxType;
	private Date purchaseDate;					//购买日期
	private Date productDate;
	private Date insureDeadlineDate;
	private Long kilo;
	private Double firstWrongKilo;
	private Date yearCheckDeadline;
	private String soleCompany;
	private String middleFactory;
	private String backFactory;
	private Long hasInsured;
	private Long hasCliam;
	private Long hasFixed;
	private Long carProperty;
	private Long carUsed;
	private TbCustomer tbCustomer;
	private Date licenseDate;					//上牌日期
	
	//委托书
	private Set tbFixEntrusts = new HashSet();
	//预付款登记
	private Set tbAnvancePays = new HashSet();
	// Constructors

	/** default constructor */
	public TbCarInfo() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicenseCode() {
		return this.licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getChassisCode() {
		return this.chassisCode;
	}

	public void setChassisCode(String chassisCode) {
		this.chassisCode = chassisCode;
	}

	public String getInsureCardCode() {
		return this.insureCardCode;
	}

	public void setInsureCardCode(String insureCardCode) {
		this.insureCardCode = insureCardCode;
	}

	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public TmCarModelType getTmCarModelType() {
		return tmCarModelType;
	}

	public void setTmCarModelType(TmCarModelType tmCarModelType) {
		this.tmCarModelType = tmCarModelType;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngineCode() {
		return this.engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public String getEngineType() {
		return this.engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public String getChangeBoxType() {
		return this.changeBoxType;
	}

	public void setChangeBoxType(String changeBoxType) {
		this.changeBoxType = changeBoxType;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getProductDate() {
		return this.productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getInsureDeadlineDate() {
		return this.insureDeadlineDate;
	}

	public void setInsureDeadlineDate(Date insureDeadlineDate) {
		this.insureDeadlineDate = insureDeadlineDate;
	}

	public Long getKilo() {
		return this.kilo;
	}

	public void setKilo(Long kilo) {
		this.kilo = kilo;
	}

	public Double getFirstWrongKilo() {
		return this.firstWrongKilo;
	}

	public void setFirstWrongKilo(Double firstWrongKilo) {
		this.firstWrongKilo = firstWrongKilo;
	}

	public Date getYearCheckDeadline() {
		return this.yearCheckDeadline;
	}

	public void setYearCheckDeadline(Date yearCheckDeadline) {
		this.yearCheckDeadline = yearCheckDeadline;
	}

	public String getSoleCompany() {
		return this.soleCompany;
	}

	public void setSoleCompany(String soleCompany) {
		this.soleCompany = soleCompany;
	}

	public String getMiddleFactory() {
		return this.middleFactory;
	}

	public void setMiddleFactory(String middleFactory) {
		this.middleFactory = middleFactory;
	}

	public String getBackFactory() {
		return this.backFactory;
	}

	public void setBackFactory(String backFactory) {
		this.backFactory = backFactory;
	}

	public Long getHasInsured() {
		return this.hasInsured;
	}

	public void setHasInsured(Long hasInsured) {
		this.hasInsured = hasInsured;
	}

	public Long getHasCliam() {
		return this.hasCliam;
	}

	public void setHasCliam(Long hasCliam) {
		this.hasCliam = hasCliam;
	}

	public Long getHasFixed() {
		return this.hasFixed;
	}

	public void setHasFixed(Long hasFixed) {
		this.hasFixed = hasFixed;
	}

	public Long getCarProperty() {
		return this.carProperty;
	}

	public void setCarProperty(Long carProperty) {
		this.carProperty = carProperty;
	}

	public Long getCarUsed() {
		return this.carUsed;
	}

	public void setCarUsed(Long carUsed) {
		this.carUsed = carUsed;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}
	
	public Date getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(Date licenseDate) {
		this.licenseDate = licenseDate;
	}

	public Set getTbFixEntrusts() {
		return tbFixEntrusts;
	}

	public void setTbFixEntrusts(Set tbFixEntrusts) {
		this.tbFixEntrusts = tbFixEntrusts;
	}


	public Set getTbAnvancePays() {
		return tbAnvancePays;
	}

	public void setTbAnvancePays(Set tbAnvancePays) {
		this.tbAnvancePays = tbAnvancePays;
	}


	private String modelTypeName;

	public String getModelTypeName() {
		
		if(null!=this.tmCarModelType){
			
			this.modelTypeName = this.tmCarModelType.getModelName();
		
		}
		
		return this.modelTypeName;
	}

	public void setModelTypeName(String modelTypeName) {
		this.modelTypeName = modelTypeName;
	}
	
	
	private String hasInsuredShow;
	private String hasCliamShow;
	private String hasFixedShow;
	private String carPropertyShow;
	private String carUsedShow;

	public String getHasInsuredShow() {
		if(Constants.ISTRUE.equals(this.hasInsured)){
			hasInsuredShow = Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.hasInsured)){
			hasInsuredShow = Constants.NOTTRUESHOW;
		}
		else{
			hasInsuredShow = Constants.NOTDIFINE;
		}
		
		return hasInsuredShow;
	}

	public void setHasInsuredShow(String hasInsuredShow) {
		this.hasInsuredShow = hasInsuredShow;
	}

	public String getHasCliamShow() {
		if(Constants.ISTRUE.equals(this.hasCliam)){
			hasCliamShow = Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.hasCliam)){
			hasCliamShow = Constants.NOTTRUESHOW;
		}
		else{
			hasCliamShow = Constants.NOTDIFINE;
		}
		return hasCliamShow;
	}

	public void setHasCliamShow(String hasCliamShow) {
		this.hasCliamShow = hasCliamShow;
	}

	public String getHasFixedShow() {
		if(Constants.ISTRUE.equals(this.hasFixed)){
			hasFixedShow = Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.hasFixed)){
			hasFixedShow = Constants.NOTTRUESHOW;
		}
		else{
			hasFixedShow = Constants.NOTDIFINE;
		}
		return hasFixedShow;
	}

	public void setHasFixedShow(String hasFixedShow) {
		this.hasFixedShow = hasFixedShow;
	}

	public String getCarPropertyShow() {
		if(Constants.PERSONALCAR.equals(this.carProperty)){
			carPropertyShow = Constants.PERSONALCARSHOW;
		}
		else if(Constants.PUBLICCAR.equals(this.carProperty)){
			carPropertyShow = Constants.PUBLICCARSHOW;
		}
		return carPropertyShow;
	}

	public void setCarPropertyShow(String carPropertyShow) {
		this.carPropertyShow = carPropertyShow;
	}

	public String getCarUsedShow() {
		if(Constants.PUBLICUSE.equals(this.carUsed)){
			carUsedShow = Constants.PUBLICUSESHOW;
		}
		else if(Constants.FAMILYUSE.equals(this.carUsed)){
			carUsedShow = Constants.FAMILYUSESHOW;
		}
		else if(Constants.BUSINESSUSE.equals(this.carUsed)){
			carUsedShow = Constants.BUSINESSUSESHOW;
		}
		else if(Constants.TRADEUSE.equals(this.carUsed)){
			carUsedShow = Constants.TRADEUSESHOW;
		}
		return carUsedShow;
	}

	public void setCarUsedShow(String carUsedShow) {
		this.carUsedShow = carUsedShow;
	}
	
	private String customerCode;
	
	private String customerName;

	public String getCustomerCode() {
		if(null!=this.tbCustomer){
			customerCode = this.tbCustomer.getCustomerCode();
		}
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		if(null!=this.tbCustomer){
			customerName = this.tbCustomer.getCustomerName();
		}
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	private String beginLicenseDate;
	
	private String endLicenseDate;
	
	private String beginPurchaseDate;
	
	private String endPurchaseDate;
	
	private String licenseMonth;
	
	private Long minKilo;
	
	private Long maxKilo;
	
	public Long getMinKilo() {
		return minKilo;
	}

	public void setMinKilo(Long minKilo) {
		this.minKilo = minKilo;
	}

	public Long getMaxKilo() {
		return maxKilo;
	}

	public void setMaxKilo(Long maxKilo) {
		this.maxKilo = maxKilo;
	}

	public String getLicenseMonth() {
		return licenseMonth;
	}

	public void setLicenseMonth(String licenseMonth) {
		this.licenseMonth = licenseMonth;
	}

	public String getBeginPurchaseDate() {
		return beginPurchaseDate;
	}

	public void setBeginPurchaseDate(String beginPurchaseDate) {
		this.beginPurchaseDate = beginPurchaseDate;
	}

	public String getEndPurchaseDate() {
		return endPurchaseDate;
	}

	public void setEndPurchaseDate(String endPurchaseDate) {
		this.endPurchaseDate = endPurchaseDate;
	}

	public String getBeginLicenseDate() {
		return beginLicenseDate;
	}

	public void setBeginLicenseDate(String beginLicenseDate) {
		this.beginLicenseDate = beginLicenseDate;
	}

	public String getEndLicenseDate() {
		return endLicenseDate;
	}

	public void setEndLicenseDate(String endLicenseDate) {
		this.endLicenseDate = endLicenseDate;
	}
	
	private String modelCode;

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}
	
	private String purchaseDate_s;

	public String getPurchaseDate_s() {
		return purchaseDate_s;
	}

	public void setPurchaseDate_s(String purchaseDate_s) {
		this.purchaseDate_s = purchaseDate_s;
	}
	
	private String productDate_s;

	public String getProductDate_s() {
		return productDate_s;
	}

	public void setProductDate_s(String productDate_s) {
		this.productDate_s = productDate_s;
	}
	
	private String insureDeadlineDate_s;

	public String getInsureDeadlineDate_s() {
		return insureDeadlineDate_s;
	}

	public void setInsureDeadlineDate_s(String insureDeadlineDate_s) {
		this.insureDeadlineDate_s = insureDeadlineDate_s;
	}
	
	private String yearCheckDeadline_s;

	public String getYearCheckDeadline_s() {
		return yearCheckDeadline_s;
	}

	public void setYearCheckDeadline_s(String yearCheckDeadline_s) {
		this.yearCheckDeadline_s = yearCheckDeadline_s;
	}
	
	private String licenseDate_s;

	public String getLicenseDate_s() {
		return licenseDate_s;
	}

	public void setLicenseDate_s(String licenseDate_s) {
		this.licenseDate_s = licenseDate_s;
	}
	
	private String hasInsured_s;
	private String hasCliam_s;
	private String hasFixed_s;
	private String carProperty_s;
	private String carUsed_s;

	public String getHasInsured_s() {
		return hasInsured_s;
	}

	public void setHasInsured_s(String hasInsured_s) {
		this.hasInsured_s = hasInsured_s;
	}

	public String getHasCliam_s() {
		return hasCliam_s;
	}

	public void setHasCliam_s(String hasCliam_s) {
		this.hasCliam_s = hasCliam_s;
	}

	public String getHasFixed_s() {
		return hasFixed_s;
	}

	public void setHasFixed_s(String hasFixed_s) {
		this.hasFixed_s = hasFixed_s;
	}

	public String getCarProperty_s() {
		return carProperty_s;
	}

	public void setCarProperty_s(String carProperty_s) {
		this.carProperty_s = carProperty_s;
	}

	public String getCarUsed_s() {
		return carUsed_s;
	}

	public void setCarUsed_s(String carUsed_s) {
		this.carUsed_s = carUsed_s;
	}

	private Integer customerIndex;

	public Integer getCustomerIndex() {
		return customerIndex;
	}

	public void setCustomerIndex(Integer customerIndex) {
		this.customerIndex = customerIndex;
	}
	
	private Date remindMaintainDate;

	public Date getRemindMaintainDate() {
		return remindMaintainDate;
	}

	public void setRemindMaintainDate(Date remindMaintainDate) {
		this.remindMaintainDate = remindMaintainDate;
	}
	
	private Date remindInsuranceDate;
	
	public Date getRemindInsuranceDate() {
		return remindInsuranceDate;
	}

	public void setRemindInsuranceDate(Date remindInsuranceDate) {
		this.remindInsuranceDate = remindInsuranceDate;
	}


	private String address;
	private String phone;
	private String telephone;
	private String zipCode;

	public String getAddress() {
		if(null!=this.tbCustomer){
			address = this.tbCustomer.getAddress();
		}
		
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		if(null!=this.tbCustomer){
			phone = this.tbCustomer.getPhone();
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		if(null!=this.tbCustomer){
			telephone = this.tbCustomer.getTelephone();
		}
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getZipCode() {
		if(null!=this.tbCustomer){
			zipCode = this.tbCustomer.getZipCode();
		}
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	private Date lastFixDay;

	public Date getLastFixDay() {
		return lastFixDay;
	}

	public void setLastFixDay(Date lastFixDay) {
		this.lastFixDay = lastFixDay;
	}
	
	private Date lastFixDayFrom;
	
	private Date lastFixDayTo;

	public Date getLastFixDayFrom() {
		return lastFixDayFrom;
	}

	public void setLastFixDayFrom(Date lastFixDayFrom) {
		this.lastFixDayFrom = lastFixDayFrom;
	}

	public Date getLastFixDayTo() {
		return lastFixDayTo;
	}

	public void setLastFixDayTo(Date lastFixDayTo) {
		this.lastFixDayTo = lastFixDayTo;
	}
	
	private Long entrustId;
	
	private String entrustCode;

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
	
	
	
}