package com.selfsoft.business.model;
import java.util.Date;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;

/**
 * TbAnvancePay entity. @author MyEclipse Persistence Tools
 */

public class TbAnvancePay implements java.io.Serializable {

	// Fields

	private Long id;
	private String anvanceCode;
	private TbCarInfo tbCarInfo;
	private Date payDay;
	private Double payAmount;
	private Long payPattern;
	private String chequeCode;
	private String remark;
	private TmUser tmUser;
	
	// Constructors

	/** default constructor */
	public TbAnvancePay() {
	}
	
	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnvanceCode() {
		return this.anvanceCode;
	}

	public void setAnvanceCode(String anvanceCode) {
		this.anvanceCode = anvanceCode;
	}


	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}

	public Date getPayDay() {
		return this.payDay;
	}

	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}

	public Double getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}
	
	private String payPatternShow;

	public String getPayPatternShow() {
		
		if(Constants.PAYCASH.equals(this.payPattern)){
			payPatternShow = Constants.PAYCASHSHOW;
		}
		else if(Constants.PAYCHEQUE.equals(this.payPattern)){
			payPatternShow = Constants.PAYCHEQUESHOW;
		}
		
		return payPatternShow;
	}

	public void setPayPatternShow(String payPatternShow) {
		this.payPatternShow = payPatternShow;
	}
	
	private String licenseCode;
	
	private String realName;

	public String getLicenseCode() {
		if(null!=this.tbCarInfo){
			licenseCode = this.tbCarInfo.getLicenseCode();
		}
		
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getRealName() {
		if(null!=this.tmUser){
			realName = this.tmUser.getUserRealName();
		}
		
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
	
}