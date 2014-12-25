package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmAlertDay;

public interface ITmAlertDayService {
	
	public List<TmAlertDay> findAll();
	
	public void updateTmAlertDay(TmAlertDay tmAlertDay);
	
	public TmAlertDay findById(Long id);
}
