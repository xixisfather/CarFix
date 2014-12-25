package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmUnit;

public interface ITmUnitService {
	
	public TmUnit findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmUnit tmUnit);
	
	public void update(TmUnit tmUnit);
	
	public List<TmUnit>findAll();
	
	public TmUnit findByUnitName(String unitName);
}
