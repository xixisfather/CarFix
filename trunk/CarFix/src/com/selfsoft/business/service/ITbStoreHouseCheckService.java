package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TbStoreHouseCheck;

public interface ITbStoreHouseCheckService {
	
	public TbStoreHouseCheck findById(Long id);
	
	public List<TbStoreHouseCheck> findByEntity(TbStoreHouseCheck tbStoreHouseCheck);
	
	public void insertTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck);
	
	public void updateTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck);
	
	public boolean deleteById(Long id);
	
	public void insertBatchStoreHouseCheck(Map<String, String> formMap , TbStoreHouseCheck tbStoreHouseCheck);
	
	public void updateBatchStoreHouseCheck(Map<String, String> formMap , TbStoreHouseCheck tbStoreHouseCheck);
}
