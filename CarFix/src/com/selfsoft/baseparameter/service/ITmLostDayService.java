package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmLostDay;

public interface ITmLostDayService {
	
	public List<TmLostDay> findAll();
	
	public void updateTmLostDay(TmLostDay tmLostDay);
	
	public TmLostDay findById(Long id);
}
