package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.StStockin;

public interface IStStockinService {

	public void insert(StStockin stStockin);
	
	public void update(StStockin stStockin);
	
	public StStockin findById(Long id);
	
	public boolean deleteById(Long id);
	
	public List<StStockin> findStStockin(StStockin stStockin);
}
