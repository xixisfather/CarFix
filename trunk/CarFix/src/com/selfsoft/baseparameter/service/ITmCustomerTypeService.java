package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmCustomerType;

public interface ITmCustomerTypeService {
	public TmCustomerType findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmCustomerType tmCustomerType);
	
	public void update(TmCustomerType tmCustomerType);
	
	public List<TmCustomerType>findAll();
	
	public Map<Long,String> findAllTmCustomerTypeMap();
}
