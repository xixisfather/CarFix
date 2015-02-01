package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TmCardTypeService;

public interface ITmCardTypeServiceService {

	public void insertTmCardTypeService(TmCardTypeService tmCardTypeService);
	
	public List<TmCardTypeService> findByTmCardTypeServiceId(Long tmCardTypeServiceId);
	
	public void deleteByTmCardTypeServiceId(Long tmCardTypeServiceId);
}
