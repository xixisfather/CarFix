package com.selfsoft.baseparameter.model;

public class TmMaintainAlertDay implements java.io.Serializable {

	private Long id;
	
	private Integer alertDay;

	private Integer continueDay;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAlertDay() {
		return alertDay;
	}

	public void setAlertDay(Integer alertDay) {
		this.alertDay = alertDay;
	}

	public Integer getContinueDay() {
		return continueDay;
	}

	public void setContinueDay(Integer continueDay) {
		this.continueDay = continueDay;
	}
	
	
}
