package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmFixType;

public interface ITmFixTypeService {
	
	public void insert(TmFixType tmFixType);
	
	public void update(TmFixType tmFixType);
	
	public boolean deleteById(Long id);
	
	public TmFixType findById(Long id);
	
	public List<TmFixType> findAll();
	
	public Map<Long,String> findAllTmFixTypeMap();
}
