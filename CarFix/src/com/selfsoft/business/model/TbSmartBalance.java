package com.selfsoft.business.model;

import java.util.Date;

import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.framework.common.Constants;

/**
 * @author chenchunrong
 *
 */
public class TbSmartBalance implements java.io.Serializable {

	private Long id;
	
	private String serviceName;
	
	private Double serviceMoney;
	
	private Long payPatten;
	
	private String cardNo;
	
	private Double cardSaving;
	
	private String workerName;
	
	private Date balanceDate;
	
	private Date balanceDateStart;
	
	private Date balanceDateEnd;
	
	
	private String licenseCode;
	
	private Long userId;
	
	private Long useCardService;
	
	private TbMembershipCard tbMembershipCard;
	
	public TbSmartBalance() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Double getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(Double serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public Long getPayPatten() {
		return payPatten;
	}

	public void setPayPatten(Long payPatten) {
		this.payPatten = payPatten;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Double getCardSaving() {
		return cardSaving;
	}

	public void setCardSaving(Double cardSaving) {
		this.cardSaving = cardSaving;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Date getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUseCardService() {
		return useCardService;
	}

	public void setUseCardService(Long useCardService) {
		this.useCardService = useCardService;
	}

	public TbMembershipCard getTbMembershipCard() {
		return tbMembershipCard;
	}

	public void setTbMembershipCard(TbMembershipCard tbMembershipCard) {
		this.tbMembershipCard = tbMembershipCard;
	}

	public Date getBalanceDateStart() {
		return balanceDateStart;
	}

	public void setBalanceDateStart(Date balanceDateStart) {
		this.balanceDateStart = balanceDateStart;
	}

	public Date getBalanceDateEnd() {
		return balanceDateEnd;
	}

	public void setBalanceDateEnd(Date balanceDateEnd) {
		this.balanceDateEnd = balanceDateEnd;
	}
	
	private String payPattenShow;

	public String getPayPattenShow() {
		
		if(null!=this.payPatten){
			
			if(Constants.PAYCASH.equals(this.payPatten)){
				payPattenShow = Constants.PAYCASHSHOW;
			}
			
			else if(Constants.PAYCHEQUE.equals(this.payPatten)){
				payPattenShow = Constants.PAYCHEQUESHOW;
			}
			
			else if(Constants.PAYACCOUNT.equals(this.payPatten)){
				payPattenShow = Constants.PAYACCOUNTSHOW;
			}
			
			else if(Constants.PAYCARD.equals(this.payPatten)){
				payPattenShow = Constants.PAYCARDSHOW;
			}
			
			else if(Constants.PAYMEMBERCARD.equals(this.payPatten)){
				payPattenShow = Constants.PAYMEMBERCARDSHOW;
			}
		}
		
		return payPattenShow;
	}
	
	private String useCardServiceShow;

	public String getUseCardServiceShow() {
		
		if(Constants.ISTRUE.equals(useCardService)){
			
			useCardServiceShow = Constants.ISTRUESHOW;
		}
		
		else {
			
			useCardServiceShow = Constants.NOTTRUESHOW;
		}
		
		
		return useCardServiceShow;
	}
	
	private Integer serviceCount;

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	
	
	
	
}
