package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmMaintainAlertDay;

public interface ITmMaintainAlertDayService {
	
	public List<TmMaintainAlertDay> findAll();
	
	public void updateTmMaintainAlertDay(TmMaintainAlertDay tmMaintainAlertDay);
	
	public TmMaintainAlertDay findById(Long id);
}
