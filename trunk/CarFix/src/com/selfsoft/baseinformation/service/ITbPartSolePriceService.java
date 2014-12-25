package com.selfsoft.baseinformation.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TbPartSolePrice;

public interface ITbPartSolePriceService {
	
	public List<TbPartSolePrice> findAll();
	
	public TbPartSolePrice findById(Long id);
	
	public List<TbPartSolePrice> findByEntity(TbPartSolePrice tbPartSolePrice);
	
	public Map<TbPartSolePrice, String> getSolePriceMap(Long partInfoId );
	
	public void update(TbPartSolePrice tbPartSolePrice);
}
