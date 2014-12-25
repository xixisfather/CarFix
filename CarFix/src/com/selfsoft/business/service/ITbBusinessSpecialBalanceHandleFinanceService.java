package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBusinessSpecialBalanceHandleFinance;

public interface ITbBusinessSpecialBalanceHandleFinanceService {
	
	public void insertTbBusinessSpecialBalanceHandleFinance(TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance);
	
	public void updateTbBusinessSpecialBalanceHandleFinance(TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance);

	public boolean deleteByID(Long id);
	
	public List<TbBusinessSpecialBalanceHandleFinance> findByTbBusinessSpecialBalanceHandleFinance(TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance);

	public TbBusinessSpecialBalanceHandleFinance findById(Long id);
	
	public TbBusinessSpecialBalanceHandleFinance findByStaticsDate(String staticsDate);
}
