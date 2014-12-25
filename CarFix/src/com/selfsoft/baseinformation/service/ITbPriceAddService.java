package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbPriceAdd;

public interface ITbPriceAddService {

public void insert(TbPriceAdd tbPriceAdd);
	
	public void update(TbPriceAdd tbPriceAdd);
	
	public boolean deleteById(Long id);
	
	public TbPriceAdd findById(Long id);
	
	public List<TbPriceAdd> findByEntity(TbPriceAdd tbPriceAdd);
	
	public void synTbPartInfoSellPrice(Long id);
	
}
