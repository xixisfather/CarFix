package com.selfsoft.baseinformation.model;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.framework.common.Constants;


public class TbMembershipCard implements java.io.Serializable{
	
	private Long id;
	
	private String cardNo;
	
	private Long cardPoint;
	
	private Double cardSaving;
	
	private Long cardStatus;
	
	private TbCustomer tbCustomer;
	
	private TbCarInfo tbCarInfo;
	
	private Date createDate;
	
	private Date validDate; 
	
	private TmCardType tmCardType;
	
	private String cardPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getCardPoint() {
		return cardPoint;
	}

	public void setCardPoint(Long cardPoint) {
		this.cardPoint = cardPoint;
	}

	public Double getCardSaving() {
		return cardSaving;
	}

	public void setCardSaving(Double cardSaving) {
		this.cardSaving = cardSaving;
	}

	public Long getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Long cardStatus) {
		this.cardStatus = cardStatus;
	}

	public TbCustomer getTbCustomer() {
		
		if(null!=this.tbCarInfo){
			
			tbCustomer = this.tbCarInfo.getTbCustomer();
			
		}
		
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}
	
	public TbCarInfo getTbCarInfo() {
		return tbCarInfo;
	}

	public void setTbCarInfo(TbCarInfo tbCarInfo) {
		this.tbCarInfo = tbCarInfo;
	}
	
	public Date getCreateDate() {
		return createDate;
	} 

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public TmCardType getTmCardType() {
		return tmCardType;
	}

	public void setTmCardType(TmCardType tmCardType) {
		this.tmCardType = tmCardType;
	}

	public String getCardPassword() {
		return cardPassword;
	}

	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}


	private String cardDesc;

	public String getCardDesc() {
		
		if(null!=tmCardType){
			
			cardDesc = tmCardType.getCardDesc();
			
		}
		
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	
	private String customerCode;
	
	private String customerName;
	
	private String telephone;

	public String getCustomerCode() {
		
		if(null!=this.getTbCustomer()){
			
			customerCode = this.getTbCustomer().getCustomerCode();
			
		}
		
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		
		if(null!=this.getTbCustomer()){
			
			customerName = this.getTbCustomer().getCustomerName();
			
		}
		
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getTelephone() {
		
		if(null!=this.getTbCustomer()){
			
			telephone = this.getTbCustomer().getTelephone();
		}
		
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	private String cardStatusShow;

	public String getCardStatusShow() {
		
		if(null!=this.cardStatus){
			
			if(this.cardStatus.equals(Constants.CARD_VALID_STATUS)){
				
				cardStatusShow = Constants.CARD_VALID_STATUS_SHOW;
				
			}
			
			else if(this.cardStatus.equals(Constants.CARD_NO_VALID_STATUS)){
				
				cardStatusShow = Constants.CARD_NO_VALID_STATUS_SHOW;
				
			}
			
			else if(this.cardStatus.equals(Constants.CARD_LOST_STATUS)){
				
				cardStatusShow = Constants.CARD_LOST_STATUS_SHOW;
				
			}
			
			
		}
		
		return cardStatusShow;
	}

	public void setCardStatusShow(String cardStatusShow) {
		this.cardStatusShow = cardStatusShow;
	}
	
	private Double czje;

	public Double getCzje() {
		return czje;
	}

	public void setCzje(Double czje) {
		this.czje = czje;
	}
	
	private Long czjf;
	
	private Integer giveMoney;
	
	private Integer givePoint;
	
	private Double gsJexf;
	
	private Double pjJexf;
	
	private Double jexf;
	
	private Integer jfxf;
	
	public Long getCzjf() {
		return czjf;
	}

	public void setCzjf(Long czjf) {
		this.czjf = czjf;
	}

	public Integer getGiveMoney() {
		return giveMoney;
	}

	public void setGiveMoney(Integer giveMoney) {
		this.giveMoney = giveMoney;
	}

	public Integer getGivePoint() {
		return givePoint;
	}

	public void setGivePoint(Integer givePoint) {
		this.givePoint = givePoint;
	}

	public Double getGsJexf() {
		return gsJexf;
	}

	public void setGsJexf(Double gsJexf) {
		this.gsJexf = gsJexf;
	}

	public Double getPjJexf() {
		return pjJexf;
	}

	public void setPjJexf(Double pjJexf) {
		this.pjJexf = pjJexf;
	}

	public Double getJexf() {
		return jexf;
	}

	public void setJexf(Double jexf) {
		this.jexf = jexf;
	}

	public Integer getJfxf() {
		return jfxf;
	}

	public void setJfxf(Integer jfxf) {
		this.jfxf = jfxf;
	}


	private String licenseCode;

	public String getLicenseCode() {
		
		if(null!=this.tbCarInfo){
			
			licenseCode = this.tbCarInfo.getLicenseCode();
			
		}
		
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	
	private String currentCardNo;

	public String getCurrentCardNo() {
		return currentCardNo;
	}

	public void setCurrentCardNo(String currentCardNo) {
		this.currentCardNo = currentCardNo;
	}
	
	private String previousCardNo;
	
	public String getPreviousCardNo() {
		return previousCardNo;
	}

	public void setPreviousCardNo(String previousCardNo) {
		this.previousCardNo = previousCardNo;
	}


	private Double oriCardSaving;
	
	private Long oriCardPoint;

	public Double getOriCardSaving() {
		return oriCardSaving;
	}

	public void setOriCardSaving(Double oriCardSaving) {
		this.oriCardSaving = oriCardSaving;
	}

	public Long getOriCardPoint() {
		return oriCardPoint;
	}

	public void setOriCardPoint(Long oriCardPoint) {
		this.oriCardPoint = oriCardPoint;
	}
	
	private Double aftCardSaving;

	public Double getAftCardSaving() {
		return aftCardSaving;
	}

	public void setAftCardSaving(Double aftCardSaving) {
		this.aftCardSaving = aftCardSaving;
	}
	
	private Integer gsGiveMoney;
	
	private Integer pjGiveMoney;
	
	private Integer gsGivePoint;
	
	private Integer pjGivePoint;

	public Integer getGsGiveMoney() {
		return gsGiveMoney;
	}

	public void setGsGiveMoney(Integer gsGiveMoney) {
		this.gsGiveMoney = gsGiveMoney;
	}

	public Integer getPjGiveMoney() {
		return pjGiveMoney;
	}

	public void setPjGiveMoney(Integer pjGiveMoney) {
		this.pjGiveMoney = pjGiveMoney;
	}

	public Integer getGsGivePoint() {
		return gsGivePoint;
	}

	public void setGsGivePoint(Integer gsGivePoint) {
		this.gsGivePoint = gsGivePoint;
	}

	public Integer getPjGivePoint() {
		return pjGivePoint;
	}

	public void setPjGivePoint(Integer pjGivePoint) {
		this.pjGivePoint = pjGivePoint;
	}
	
	private String balanceCode;

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	
	private Double cardZFJE;

	public Double getCardZFJE() {
		return cardZFJE;
	}

	public void setCardZFJE(Double cardZFJE) {
		this.cardZFJE = cardZFJE;
	}
	
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	private Long balanceId;

	public Long getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
	}
	
	
	
}
