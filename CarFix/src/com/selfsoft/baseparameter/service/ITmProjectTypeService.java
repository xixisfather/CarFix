package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmProjectType;

public interface ITmProjectTypeService {

public TmProjectType findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmProjectType tmProjectType);
	
	public void update(TmProjectType tmProjectType);
	
	public List<TmProjectType>findAll();
	
	public Map<Long,String>findAllTmProjectTypeMap();
}
