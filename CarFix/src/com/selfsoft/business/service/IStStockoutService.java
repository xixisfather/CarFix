package com.selfsoft.business.service;

import java.util.List;
import com.selfsoft.business.model.StStockout;

public interface IStStockoutService {

	public void update(StStockout stStockout);
	
	public void insert(StStockout stStockout);
	
	public List<StStockout> findByStStockout(StStockout stStockout);
	
	public boolean deleteById(Long id);
	
	public StStockout findById(Long id);
	
	public StStockout getStStockoutCount(List<StStockout> stStockoutList);
}
