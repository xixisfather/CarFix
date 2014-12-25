package com.selfsoft.secrity.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.secrity.model.TmWorkType;

public interface ITmWorkTypeService {
	public TmWorkType findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmWorkType tmWorkType);
	
	public void update(TmWorkType tmWorkType);
	
	public List<TmWorkType>findAll();
	
	public Map<Long,String> findAllTmWorkTypeMap();
	
	public List<TmWorkType> findTmWorkTypeByWorkName(String workName);
}
