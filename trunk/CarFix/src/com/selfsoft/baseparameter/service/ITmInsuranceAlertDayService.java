package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmInsuranceAlertDay;

public interface ITmInsuranceAlertDayService {
	
	public List<TmInsuranceAlertDay> findAll();
	
	public void updateTmInsuranceAlertDay(TmInsuranceAlertDay tmInsuranceAlertDay);
	
	public TmInsuranceAlertDay findById(Long id);
}
