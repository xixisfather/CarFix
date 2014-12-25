package com.selfsoft.business.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

/**
 * TbBook entity. @author MyEclipse Persistence Tools
 */

public class TbBook implements java.io.Serializable {

	// Fields

	private Long id;
	private String bookCode;
	private Date registerDate;
	private Date registerDateStart;
	private Date registerDateEnd;
	private String licenseCode;
	private String chassisCode;
	private TmUser tmUser;
	private Long isSysCustomer;
	private String customerName;
	private String linkMan;
	private String phone;
	private String telphone;
	private Date planFixTime;
	private Date planFixTimeStart;
	private Date planFixTimeEnd;
	private TmFixType tmFixType;
	private TmCarModelType tmCarModelType;
	private String fixContent;
	private String carAddress;
	private String wrongDescribe;
	private String bookRemark;
	private Long isCome;
	private Set tbBookFixStations = new HashSet();
	private List<String>tbBookFixStationKeys = new ArrayList<String>();
	private Set tbBookFixParts = new HashSet();
	private List<String>tbBookFixPartKeys = new ArrayList<String>();
	// Constructors

	/** default constructor */
	public TbBook() {
	}
	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookCode() {
		return this.bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getRegisterDateStart() {
		return registerDateStart;
	}

	public void setRegisterDateStart(Date registerDateStart) {
		this.registerDateStart = registerDateStart;
	}

	public Date getRegisterDateEnd() {
		return registerDateEnd;
	}

	public void setRegisterDateEnd(Date registerDateEnd) {
		this.registerDateEnd = registerDateEnd;
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

	
	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public Long getIsSysCustomer() {
		return this.isSysCustomer;
	}

	public void setIsSysCustomer(Long isSysCustomer) {
		this.isSysCustomer = isSysCustomer;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Date getPlanFixTime() {
		return this.planFixTime;
	}

	public void setPlanFixTime(Date planFixTime) {
		this.planFixTime = planFixTime;
	}

	public Date getPlanFixTimeStart() {
		return planFixTimeStart;
	}

	public void setPlanFixTimeStart(Date planFixTimeStart) {
		this.planFixTimeStart = planFixTimeStart;
	}

	public Date getPlanFixTimeEnd() {
		return planFixTimeEnd;
	}

	public void setPlanFixTimeEnd(Date planFixTimeEnd) {
		this.planFixTimeEnd = planFixTimeEnd;
	}

	public TmFixType getTmFixType() {
		return tmFixType;
	}

	public void setTmFixType(TmFixType tmFixType) {
		this.tmFixType = tmFixType;
	}

	public TmCarModelType getTmCarModelType() {
		return tmCarModelType;
	}

	public void setTmCarModelType(TmCarModelType tmCarModelType) {
		this.tmCarModelType = tmCarModelType;
	}

	public String getFixContent() {
		return fixContent;
	}

	public void setFixContent(String fixContent) {
		this.fixContent = fixContent;
	}

	public String getCarAddress() {
		return this.carAddress;
	}

	public void setCarAddress(String carAddress) {
		this.carAddress = carAddress;
	}

	public String getWrongDescribe() {
		return this.wrongDescribe;
	}

	public void setWrongDescribe(String wrongDescribe) {
		this.wrongDescribe = wrongDescribe;
	}

	public String getBookRemark() {
		return this.bookRemark;
	}

	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}

	public Long getIsCome() {
		return this.isCome;
	}

	public void setIsCome(Long isCome) {
		this.isCome = isCome;
	}

	public Set getTbBookFixStations() {
		return tbBookFixStations;
	}

	public void setTbBookFixStations(Set tbBookFixStations) {
		this.tbBookFixStations = tbBookFixStations;
	}



	public List<String> getTbBookFixStationKeys() {
		return tbBookFixStationKeys;
	}

	public void setTbBookFixStationKeys(List<String> tbBookFixStationKeys) {
		this.tbBookFixStationKeys = tbBookFixStationKeys;
	}

	public Set getTbBookFixParts() {
		return tbBookFixParts;
	}

	public void setTbBookFixParts(Set tbBookFixParts) {
		this.tbBookFixParts = tbBookFixParts;
	}

	public List<String> getTbBookFixPartKeys() {
		return tbBookFixPartKeys;
	}

	public void setTbBookFixPartKeys(List<String> tbBookFixPartKeys) {
		this.tbBookFixPartKeys = tbBookFixPartKeys;
	}



	//车型
	private String modelName;
	//修理类型
	private String fixType;
	//是否履约
	private String isComeShow;
	//服务顾问
	private String userName;
	
	public String getIsComeShow() {
		if(Constants.ISTRUE.equals(this.isCome)){
			isComeShow = Constants.ISTRUESHOW;
		}
		else if(Constants.NOTTRUE.equals(this.isCome)){
			isComeShow = Constants.NOTTRUESHOW;
		}
		return isComeShow;
	}

	public void setIsComeShow(String isComeShow) {
		this.isComeShow = isComeShow;
	}

	public String getModelName() {
		if(null!=this.tmCarModelType){
			modelName = this.tmCarModelType.getModelName();
		}
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getFixType() {
		if(null!=this.tmFixType){
			fixType = this.tmFixType.getFixType();
		}
		return fixType;
	}

	public void setFixType(String fixType) {
		this.fixType = fixType;
	}

	public String getUserName() {
		if(null!=this.tmUser){
			userName = this.tmUser.getUserName();
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}