package com.selfsoft.business.model;

import java.math.BigDecimal;

import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.secrity.model.TmUser;

/**
 * TbFixShare entity. @author MyEclipse Persistence Tools
 */

public class TbFixShare implements java.io.Serializable {

	// Fields

	private Long id;
	private TbFixEntrustContent tbFixEntrustContent;
	private Double sendHour;
	private TmUser tmUser;
	
	//冗余字段
	private TbWorkingInfo tbWorkingInfo;

	// Constructors

	public TbFixShare(Double sumSendHour, String fixPersonName, Long fixPersonId,Double sumWorkPrice,Double sumFixPrice) {
		super();
		this.sumSendHour = sumSendHour;
		this.fixPersonName = fixPersonName;
		this.fixPersonId = fixPersonId;
		this.sumWorkPrice = sumWorkPrice;
		this.sumFixPrice = sumFixPrice;
	}

	/** default constructor */
	public TbFixShare() {
	}
	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSendHour() {
		return this.sendHour;
	}

	public void setSendHour(Double sendHour) {
		this.sendHour = sendHour;
	}

	public TbFixEntrustContent getTbFixEntrustContent() {
		return tbFixEntrustContent;
	}

	public void setTbFixEntrustContent(TbFixEntrustContent tbFixEntrustContent) {
		this.tbFixEntrustContent = tbFixEntrustContent;
	}

	public TmUser getTmUser() {
		return tmUser;
	}

	public void setTmUser(TmUser tmUser) {
		this.tmUser = tmUser;
	}

	public TbWorkingInfo getTbWorkingInfo() {
		return tbWorkingInfo;
	}

	public void setTbWorkingInfo(TbWorkingInfo tbWorkingInfo) {
		this.tbWorkingInfo = tbWorkingInfo;
	}

	//冗余字段
	//总价
	private Double workingHourTotal;
	
	private Long index;
	
	private String fixPersonIds;
	
	private String fixPersons;

	public Double getWorkingHourTotal() {
		return workingHourTotal;
	}

	public void setWorkingHourTotal(Double workingHourTotal) {
		this.workingHourTotal = workingHourTotal;
	}

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getFixPersonIds() {
		return fixPersonIds;
	}

	public void setFixPersonIds(String fixPersonIds) {
		this.fixPersonIds = fixPersonIds;
	}

	public String getFixPersons() {
		return fixPersons;
	}

	public void setFixPersons(String fixPersons) {
		this.fixPersons = fixPersons;
	}
	
	private Double sumSendHour;
	
	private String fixPersonName;
	
	private Long fixPersonId;
	
	private Double sumWorkPrice;
	
	private Double sumFixPrice;
	
	private Double workPrice;

	public Double getSumSendHour() {
		return sumSendHour;
	}

	public void setSumSendHour(Double sumSendHour) {
		this.sumSendHour = sumSendHour;
	}

	public String getFixPersonName() {
		return fixPersonName;
	}

	public void setFixPersonName(String fixPersonName) {
		this.fixPersonName = fixPersonName;
	}

	public Long getFixPersonId() {
		return fixPersonId;
	}

	public void setFixPersonId(Long fixPersonId) {
		this.fixPersonId = fixPersonId;
	}

	public Double getSumWorkPrice() {
		return sumWorkPrice;
	}

	public void setSumWorkPrice(Double sumWorkPrice) {
		this.sumWorkPrice = sumWorkPrice;
	}

	public Double getSumFixPrice() {
		return sumFixPrice;
	}

	public void setSumFixPrice(Double sumFixPrice) {
		this.sumFixPrice = sumFixPrice;
	}

	public Double getWorkPrice() {
		return CommonMethod.convertRadixPoint(this.sendHour * this.tbFixEntrustContent.getWorkingHourPrice(),2);
	}

	public void setWorkPrice(Double workPrice) {
		this.workPrice = workPrice;
	}
	
	private BigDecimal sumWorkPriceD;
	
	private BigDecimal sumFixPriceD;

	public BigDecimal getSumWorkPriceD() {
		
		if(null != this.sumWorkPrice){
			
			sumWorkPriceD = new BigDecimal(this.sumWorkPrice).setScale(0,BigDecimal.ROUND_HALF_UP);
			
		}
		
		return sumWorkPriceD;
	}

	public void setSumWorkPriceD(BigDecimal sumWorkPriceD) {
		this.sumWorkPriceD = sumWorkPriceD;
	}

	public BigDecimal getSumFixPriceD() {
		
		if(null != this.sumFixPrice){
			
			sumFixPriceD = new BigDecimal(this.sumFixPrice).setScale(0,BigDecimal.ROUND_HALF_UP);
			
		}
		
		return sumFixPriceD;
	}

	public void setSumFixPriceD(BigDecimal sumFixPriceD) {
		this.sumFixPriceD = sumFixPriceD;
	}
	
	
	
}