package com.selfsoft.baseinformation.model;

import java.math.BigDecimal;
import java.util.Date;

import com.selfsoft.secrity.model.TmUser;

public class TbCardHis implements java.io.Serializable{
	
	private Long id;
	
	private Long cardId;
	
	private String cardNo;
	
	private String operationType;
	
	private String operationDesc;

	private Date operationDate;
	
	private Date operationDateFrom;
	
	private Date operationDateTo;
	
	private Long userId;
	
	private Long oriCardPoint;
	
	private Double oriCardSaving;
	
	private Integer gsGiveMoney;
	
	private Integer pjGiveMoney;
	
	private Integer gsGivePoint;
	
	private Integer pjGivePoint;
	
	private Integer giveMoney;
	
	private Integer givePoint;
	
	private Long aftCardPoint;
	
	private Double aftCardSaving;
	
	private Double czje;
	
	private Long czjf;
	
	private Double gsJexf;
	
	private Double pjJexf;
	
	private Double jexf;
	
	private Integer jfxf;
	
	private String licenseCode;
	
	private String userName;
	
	private String userRealName;
	
	//private String balanceCode;
	
	private Long customerId;
	
	private String customerName;
	
	private String customerCode;

	public TbCardHis() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	
	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public Date getOperationDateFrom() {
		return operationDateFrom;
	}

	public void setOperationDateFrom(Date operationDateFrom) {
		this.operationDateFrom = operationDateFrom;
	}

	public Date getOperationDateTo() {
		return operationDateTo;
	}

	public void setOperationDateTo(Date operationDateTo) {
		this.operationDateTo = operationDateTo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOriCardPoint() {
		
		return oriCardPoint;
	}

	public void setOriCardPoint(Long oriCardPoint) {
		this.oriCardPoint = oriCardPoint;
	}

	public Double getOriCardSaving() {
		
		return oriCardSaving;
	}

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

	public void setOriCardSaving(Double oriCardSaving) {
		this.oriCardSaving = oriCardSaving;
	}

	public Long getAftCardPoint() {
		
		
		return aftCardPoint;
	}

	public void setAftCardPoint(Long aftCardPoint) {
		this.aftCardPoint = aftCardPoint;
	}

	public Double getAftCardSaving() {
		
		return aftCardSaving;
	}

	public void setAftCardSaving(Double aftCardSaving) {
		this.aftCardSaving = aftCardSaving;
	}

	public Double getCzje() {
		return czje;
	}

	public void setCzje(Double czje) {
		this.czje = czje;
	}

	public Long getCzjf() {
		return czjf;
	}

	public void setCzjf(Long czjf) {
		this.czjf = czjf;
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

	private TmUser tmUser;

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	private BigDecimal oriCardSavingD;
	
	private BigDecimal aftCardSavingD;

	public BigDecimal getOriCardSavingD() {
		
		return oriCardSavingD;
	}

	public void setOriCardSavingD(BigDecimal oriCardSavingD) {
		this.oriCardSavingD = oriCardSavingD;
	}

	public BigDecimal getAftCardSavingD() {
		
		return aftCardSavingD;
	}

	public void setAftCardSavingD(BigDecimal aftCardSavingD) {
		this.aftCardSavingD = aftCardSavingD;
	}
	
	private Integer oriCardPointI;
	
	private Integer aftCardPointI;

	public Integer getOriCardPointI() {
		
		return oriCardPointI;
	}

	public void setOriCardPointI(Integer oriCardPointI) {
		this.oriCardPointI = oriCardPointI;
	}

	public Integer getAftCardPointI() {
		
		return aftCardPointI;
	}

	public void setAftCardPointI(Integer aftCardPointI) {
		this.aftCardPointI = aftCardPointI;
	}
	
	private String balanceCode;

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	
	private Long balanceId;

	public Long getBalanceId() {
		return balanceId;
	}

	public void setBalanceId(Long balanceId) {
		this.balanceId = balanceId;
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

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	
}
