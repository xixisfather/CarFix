package com.selfsoft.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.selfsoft.business.model.TbFixEntrustCost;

public interface ITbFixEntrustCostService {
	
	public List<TbFixEntrustCost> findByTbFixEntrustCost(
			TbFixEntrustCost tbFixEntrustCost);
	
	public TbFixEntrustCost findById(Long id);
	
	public void insertTbFixEntrustCost(TbFixEntrustCost tbFixEntrustCost);
	
	public void updateTbFixEntrustCost(TbFixEntrustCost tbFixEntrustCost);
	
	public boolean deleteById(Long id);
	
	public BigDecimal sumTbFixEntrustCostByTbFixEntrustId(Long tbFixEntrustId);
}
