package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmWorkingHourPrice;

public interface ITmWorkingHourPriceService {
	public TmWorkingHourPrice findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean insert(TmWorkingHourPrice tmWorkingHourPrice);
	
	public boolean update(TmWorkingHourPrice tmWorkingHourPrice);
	
	public List<TmWorkingHourPrice>findAll();
	
	public Map<Long,String>findAllTmWorkingHourPriceMap();
	
	public Map<Double, String> findAllTmWorkingHourPriceValueMap();
}
