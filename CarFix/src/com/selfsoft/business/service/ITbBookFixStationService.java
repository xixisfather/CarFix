package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TbBookFixStation;

public interface ITbBookFixStationService {
	
	public void insert(TbBookFixStation tbBookFixStation);
	
	public void update(TbBookFixStation tbBookFixStation);
	
	public boolean deleteById(Long id);
	
	public TbBookFixStation findById(Long id);
	
	public List<TbBookFixStation> findByTbBookFixStation(TbBookFixStation tbBookFixStation);
	
	public List<TbBookFixStation> findTbBookFixStationListByTbBookId(Long tbBookId);
	
	public Map<String,String> findTbBookFixStationMapByTbBookId(Long tbBookId);
}
