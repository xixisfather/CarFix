package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbStoreHouseCheckDetail;

public interface ITbStoreHouseCheckDetailService {

	public TbStoreHouseCheckDetail findById(Long id);
	
	public List<TbStoreHouseCheckDetail> findByEntity(TbStoreHouseCheckDetail tbStoreHouseCheckDetail);
	
	public void insertTbStoreHouseCheckDetail(TbStoreHouseCheckDetail tbStoreHouseCheckDetail);
	
	public void updateTbStoreHouseCheckDetail(TbStoreHouseCheckDetail tbStoreHouseCheckDetail);
	
	public boolean deleteById(Long id);
	
	public void deleteCheckDetail(Long checkId);
}
