package com.selfsoft.baseparameter.dao;

import java.util.List;

import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.framework.dao.IDao;

public interface ITmDictionaryDao extends IDao<TmDictionary>{

	public List<TmDictionary> findByDictionaryType(String type);
	
	public void resetParamValueByType(String types);
	
	public void updateCurrMonthStatus();
	
	public List<TmDictionary> findByZeroMounth();
	
	public void resetParamValue();
	
	public List<TmDictionary> findNullStatus();
}
