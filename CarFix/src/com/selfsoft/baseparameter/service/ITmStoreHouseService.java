package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmStoreHouse;

public interface ITmStoreHouseService {
    
	public TmStoreHouse findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean insert(TmStoreHouse tmStoreHouse);
	
	public boolean update(TmStoreHouse tmStoreHouse);
	
	public List<TmStoreHouse>findByTmStoreHouse(TmStoreHouse tmStoreHouse);
	
	public List<TmStoreHouse> findAll();
	
	public TmStoreHouse findByHouseCode(String houseCode);
	
}
