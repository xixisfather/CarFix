package com.selfsoft.baseparameter.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmCarModelType;


public interface ITmCarModelTypeService {
	
	public TmCarModelType findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean insert(TmCarModelType tmCarModelType);
	
	public boolean update(TmCarModelType tmCarModelType);
	
	public List<TmCarModelType>findAll();
	
	public Map<Long,String>findAllTmCarModelTypeMap();
	
	public List<TmCarModelType> findByTmCarModelType(TmCarModelType tmCarModelType);
	
	public TmCarModelType findByModelCode(String modelCode);
	
	public String importTmCarModelType(InputStream in , String tpl);
}
