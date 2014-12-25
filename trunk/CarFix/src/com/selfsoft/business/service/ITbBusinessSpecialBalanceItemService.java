package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBusinessSpecialBalanceItem;


public interface ITbBusinessSpecialBalanceItemService {
	
	public void insert(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem);
	
	public void update(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem);
	
	public boolean deleteById(Long id);
	
	public List<TbBusinessSpecialBalanceItem> findBySpecialId(Long specialId);
	
	public void deleteBySpecialId(Long specialId);
}
