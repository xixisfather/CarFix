package com.selfsoft.baseinformation.model;

import java.util.HashSet;
import java.util.Set;

import com.selfsoft.baseparameter.model.TmCustomerType;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.framework.common.Constants;

/**
 * TbCustomer entity. @author MyEclipse Persistence Tools
 */

public class TbCustomer implements java.io.Serializable {

	// Fields

	private Long id;
	private String customerCode;
	private String customerName;
	private String pinyinCode;
	private String address;
	private String phone;
	private String telephone;
	private String zipCode;
	private String companyAccountCode;
	private String companyTaxCode;
	private String lawPresent;
	private String contractPerson;
	private Long customerProperty;
	private TmCustomerType tmCustomerType;
	private TmSoleType tmSoleType;
	private Set tbCarInfos = new HashSet();
	//委托书
	private Set tbFixEntrusts = new HashSet();
	// Constructors
	private Set tbReceiveFrees = new HashSet();
	/** default constructor */
	public TbCustomer() {
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPinyinCode() {
		return this.pinyinCode;
	}

	public void setPinyinCode(String pinyinCode) {
		this.pinyinCode = pinyinCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		
		if(null!=this.phone){
			
			this.phone = this.phone.trim();
			
		}
		
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		
		if(null!=this.telephone){
			
			this.telephone = this.telephone.trim();
			
		}
		
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getZipCode() {
		
		if(null!=this.zipCode){
			
			this.zipCode = this.zipCode.trim();
			
		}
		
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCompanyAccountCode() {
		return this.companyAccountCode;
	}

	public void setCompanyAccountCode(String companyAccountCode) {
		this.companyAccountCode = companyAccountCode;
	}

	public String getCompanyTaxCode() {
		return this.companyTaxCode;
	}

	public void setCompanyTaxCode(String companyTaxCode) {
		this.companyTaxCode = companyTaxCode;
	}

	public String getLawPresent() {
		return this.lawPresent;
	}

	public void setLawPresent(String lawPresent) {
		this.lawPresent = lawPresent;
	}

	public String getContractPerson() {
		return this.contractPerson;
	}

	public void setContractPerson(String contractPerson) {
		this.contractPerson = contractPerson;
	}

	public Long getCustomerProperty() {
		return this.customerProperty;
	}

	public void setCustomerProperty(Long customerProperty) {
		this.customerProperty = customerProperty;
	}

	public TmCustomerType getTmCustomerType() {
		return tmCustomerType;
	}

	public void setTmCustomerType(TmCustomerType tmCustomerType) {
		this.tmCustomerType = tmCustomerType;
	}
	
	public TmSoleType getTmSoleType() {
		return tmSoleType;
	}

	public void setTmSoleType(TmSoleType tmSoleType) {
		this.tmSoleType = tmSoleType;
	}

	public Set getTbCarInfos() {
		return tbCarInfos;
	}

	public void setTbCarInfos(Set tbCarInfos) {
		this.tbCarInfos = tbCarInfos;
	}



	public Set getTbFixEntrusts() {
		return tbFixEntrusts;
	}

	public void setTbFixEntrusts(Set tbFixEntrusts) {
		this.tbFixEntrusts = tbFixEntrusts;
	}



	//e3属性
	private String customerPropertyShow;

	private String customerTypeShow;

	public String getCustomerPropertyShow() {
		if(Constants.CUSTOMERVAL.equals(this.customerProperty)){
			customerPropertyShow = Constants.CUSTOMERSHOW;
		}
		else if(Constants.SUPPLIERVAL.equals(this.customerProperty)){
			customerPropertyShow = Constants.SUPPLIERSHOW;
		}
		else if(Constants.CUSTOMERANDSUPPLIERVAL.equals(this.customerProperty)){
			customerPropertyShow = Constants.CUSTOMERANDSUPPLIERSHOW;
		}
		return customerPropertyShow;
	}

	public void setCustomerPropertyShow(String customerPropertyShow) {
		this.customerPropertyShow = customerPropertyShow;
	}

	public String getCustomerTypeShow() {
		return this.tmCustomerType.getTypeName();
	}

	public void setCustomerTypeShow(String customerTypeShow) {
		this.customerTypeShow = customerTypeShow;
	}
	
	private String soleTypeShow;

	public String getSoleTypeShow() {
		return this.tmSoleType.getSoleName();
	}

	public void setSoleTypeShow(String soleTypeShow) {
		this.soleTypeShow = soleTypeShow;
	}

	public Set getTbReceiveFrees() {
		return tbReceiveFrees;
	}

	public void setTbReceiveFrees(Set tbReceiveFrees) {
		this.tbReceiveFrees = tbReceiveFrees;
	}
	
	private Integer customerIndex;
	
	public Integer getCustomerIndex() {
		return customerIndex;
	}

	public void setCustomerIndex(Integer customerIndex) {
		this.customerIndex = customerIndex;
	}
	
	private String licenseCode;
	
	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	
	
	
}