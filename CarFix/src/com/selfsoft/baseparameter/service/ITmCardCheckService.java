package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmCardCheck;

public interface ITmCardCheckService {

	public TmCardCheck findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmCardCheck tmCardCheck);
	
	public void update(TmCardCheck tmCardCheck);
	
	public List<TmCardCheck>findAll();
}
