package com.selfsoft.baseinformation.model;

public class TmCardTypeService implements java.io.Serializable{

	private Long id;
	
	private Long cardTypeId;
	
	private String serviceName;
	
	private Integer serviceCount;
	
	public TmCardTypeService(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(Long cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	
	
}
