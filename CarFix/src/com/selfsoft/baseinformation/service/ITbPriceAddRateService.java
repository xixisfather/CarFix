package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbPriceAddRate;

public interface ITbPriceAddRateService {

	
	public void insert(TbPriceAddRate tbPriceAddRate);
	
	public void update(TbPriceAddRate tbPriceAddRate);
	
	public boolean deleteById(Long id);
	
	public TbPriceAddRate findById(Long id);
	
	public List<TbPriceAddRate> findByEntity(TbPriceAddRate tbPriceAddRate);
	
	public void synTbPartInfoSellPrice(Long id);
}
