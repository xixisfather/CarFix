package com.selfsoft.business.vo;

import java.math.BigDecimal;

public class WorkTypeHourPriceVo {

	private Long workTypeId;
	
	private String workTypeName;
	
	private Double totalSendHour;
	
	private Double totalSendPrice;
	
	private Double totalFixHour;
	
	private Double totalFixPrice;

	public Long getWorkTypeId() {
		return workTypeId;
	}

	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}

	public Double getTotalSendHour() {
		return totalSendHour;
	}

	public void setTotalSendHour(Double totalSendHour) {
		this.totalSendHour = totalSendHour;
	}

	public Double getTotalSendPrice() {
		return totalSendPrice;
	}

	public void setTotalSendPrice(Double totalSendPrice) {
		this.totalSendPrice = totalSendPrice;
	}

	public Double getTotalFixHour() {
		return totalFixHour;
	}

	public void setTotalFixHour(Double totalFixHour) {
		this.totalFixHour = totalFixHour;
	}

	public Double getTotalFixPrice() {
		return totalFixPrice;
	}

	public void setTotalFixPrice(Double totalFixPrice) {
		this.totalFixPrice = totalFixPrice;
	}

	public WorkTypeHourPriceVo( String workTypeName,
			Double totalSendHour, Double totalSendPrice, Double totalFixHour,
			Double totalFixPrice) {
		super();
		this.workTypeName = workTypeName;
		this.totalSendHour = totalSendHour;
		this.totalSendPrice = totalSendPrice;
		this.totalFixHour = totalFixHour;
		this.totalFixPrice = totalFixPrice;
	}
	
	
	private BigDecimal totalSendHourD;
	
	private BigDecimal totalSendPriceD;
	
	private BigDecimal totalFixHourD;
	
	private BigDecimal totalFixPriceD;

	public BigDecimal getTotalSendHourD() {
		
		if(null != this.totalSendHour){
			
			totalSendHourD = new BigDecimal(this.totalSendHour);
			totalSendHourD = totalSendHourD.setScale(2,  BigDecimal.ROUND_HALF_UP);  
		}
		
		return totalSendHourD;
	}

	public void setTotalSendHourD(BigDecimal totalSendHourD) {
		
		this.totalSendHourD = totalSendHourD;
	}

	public BigDecimal getTotalSendPriceD() {
		
		if(null != this.totalSendPrice){
			
			totalSendPriceD = new BigDecimal(this.totalSendPrice);
			totalSendPriceD = totalSendPriceD.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}
		
		return totalSendPriceD;
	}

	public void setTotalSendPriceD(BigDecimal totalSendPriceD) {
		this.totalSendPriceD = totalSendPriceD;
	}

	public BigDecimal getTotalFixHourD() {
		
		if(null != this.totalFixHour){
			
			totalFixHourD = new BigDecimal(this.totalFixHour);
			totalFixHourD = totalFixHourD.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}
		
		return totalFixHourD;
	}

	public void setTotalFixHourD(BigDecimal totalFixHourD) {
		this.totalFixHourD = totalFixHourD;
	}

	public BigDecimal getTotalFixPriceD() {
		
		if(null != this.totalFixPrice){
			
			totalFixPriceD = new BigDecimal(this.totalFixPrice);
			totalFixPriceD = totalFixPriceD.setScale(2,  BigDecimal.ROUND_HALF_UP);
		}
		
		return totalFixPriceD;
	}

	public void setTotalFixPriceD(BigDecimal totalFixPriceD) {
		this.totalFixPriceD = totalFixPriceD;
	}
	
	
}
