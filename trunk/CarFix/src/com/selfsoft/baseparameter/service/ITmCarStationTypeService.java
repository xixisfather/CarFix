package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmCarStationType;

public interface ITmCarStationTypeService {

	public TmCarStationType findById(Long id);
	
	public Boolean deleteById(Long id);
	
	public void saveTmCarStationType(TmCarStationType tmCarStationType);
	
	public void updateTmCarStationType(TmCarStationType tmCarStationType);

	public List<TmCarStationType> findTmCarStationTypeList(TmCarStationType tmCarStationType);
	
	public List<TmCarStationType> findAll();
	
	public Map<Long, String> findAllTmCarStationTypeMap();
	
	public TmCarStationType findTmCarStationTypeByTmCarModelTypeId(Long tmCarModelTypeId);
}