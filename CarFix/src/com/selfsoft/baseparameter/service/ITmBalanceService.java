package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmBalance;

public interface ITmBalanceService {
	
	public void insert(TmBalance tmBalance);
	
	public void update(TmBalance tmBalance);
	
	public boolean deleteById(Long id);
	
	public List<TmBalance> findAll();
	
	public TmBalance findById(Long id);
	
	public Map<Long,String> findAllTmBalanceMap();
	
}
