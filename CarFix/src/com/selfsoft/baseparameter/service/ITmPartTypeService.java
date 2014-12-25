package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmPartType;

public interface ITmPartTypeService {
	
	public TmPartType findById(Long id);
	
	public Boolean deleteById(Long id);
	
	public void saveTmPartType(TmPartType tmPartType);
	
	public void updateTmPartType(TmPartType tmPartType);

	public List<TmPartType> findTmPartTypeList(TmPartType tmPartType);
	
	public List<TmPartType> findAll();
	
	public TmPartType findByTypeCode(String typeCode);
}
