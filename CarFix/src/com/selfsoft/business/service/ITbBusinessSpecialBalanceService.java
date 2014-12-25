package com.selfsoft.business.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.selfsoft.business.model.TbBusinessSpecialBalance;

public interface ITbBusinessSpecialBalanceService {
	
	public void insert(TbBusinessSpecialBalance tbBusinessSpecialBalance);
	
	public void update(TbBusinessSpecialBalance tbBusinessSpecialBalance);
	
	public boolean deleteById(Long id);
	
	public List<TbBusinessSpecialBalance> findAll();
	
	public TbBusinessSpecialBalance findById(Long id);
	
	public void insertAll(TbBusinessSpecialBalance tbBusinessSpecialBalance);
	
	public List<TbBusinessSpecialBalance> findByTbBusinessSpecialBalance(TbBusinessSpecialBalance tbBusinessSpecialBalance);

	public boolean deleteAll(Long id);
	
	public void updateAll(TbBusinessSpecialBalance tbBusinessSpecialBalance);
	
	public Map putEntrustSpecialBalanceReportParamMap(Long id,HttpServletRequest request);
	
	public Map putXsdBalanceReportParamMap(Long id,HttpServletRequest request);
	
	public Map putFinanceSpecialBalanceReportParamMap(Long id,HttpServletRequest request);
	
	public void printTbBusinessBalanceTemplate(OutputStream os, String tpl,
			Long id,String companyName);
}
