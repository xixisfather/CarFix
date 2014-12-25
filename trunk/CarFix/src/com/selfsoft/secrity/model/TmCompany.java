package com.selfsoft.secrity.model;

/**
 * TmCompany entity. @author MyEclipse Persistence Tools
 */

public class TmCompany implements java.io.Serializable {

	// Fields

	private Long id;
	private String companyName;
	private String lawerPerson;
	private String companyAddress;
	private String companyZipCode;
	private String companyPhone;
	private String companyFax;
	private String taxCode;
	private String companyAccount;
	private String serviceLeader;
	private String bankName;
	// Constructors

	/** default constructor */
	public TmCompany() {
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		
		if(null!=this.companyName){
			
			this.companyName = this.companyName.trim();
			
		}
		
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLawerPerson() {
		return this.lawerPerson;
	}

	public void setLawerPerson(String lawerPerson) {
		this.lawerPerson = lawerPerson;
	}

	public String getCompanyAddress() {
		
		if(null!=this.companyAddress){
			
			this.companyAddress = this.companyAddress.trim();
			
		}
		
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyZipCode() {
		return this.companyZipCode;
	}

	public void setCompanyZipCode(String companyZipCode) {
		this.companyZipCode = companyZipCode;
	}

	public String getCompanyPhone() {
		return this.companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyFax() {
		return this.companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getTaxCode() {
		return this.taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getCompanyAccount() {
		return this.companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}


	public String getServiceLeader() {
		return serviceLeader;
	}


	public void setServiceLeader(String serviceLeader) {
		this.serviceLeader = serviceLeader;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}