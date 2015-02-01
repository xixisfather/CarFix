package com.selfsoft.baseinformation.model;

public class TmMemberShipService implements java.io.Serializable{

	private Long id;
	
	private Long memberShipId;
	
	private String serviceName;
	
	private Integer serviceCount;
	
	public TmMemberShipService() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberShipId() {
		return memberShipId;
	}

	public void setMemberShipId(Long memberShipId) {
		this.memberShipId = memberShipId;
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
