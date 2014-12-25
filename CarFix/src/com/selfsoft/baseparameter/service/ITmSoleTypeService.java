package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmSoleType;

public interface ITmSoleTypeService {
	public TmSoleType findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmSoleType tmSoleType);
	
	public void update(TmSoleType tmSoleType);
	
	public List<TmSoleType>findAll();
	
	public Map<Long, String> findAllTmSoleTypeMap();
	
	public List<TmSoleType> findByEntity(TmSoleType tmSoleType);
	
	public TmSoleType getDefaultSoleType();
}
