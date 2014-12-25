package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbAnvancePay;

public interface ITbAnvancePayService {
	
	public void insert(TbAnvancePay tbAnvancePay);
	
	public void update(TbAnvancePay tbAnvancePay);
	
	public List<TbAnvancePay> findAll();
	
	public TbAnvancePay findById(Long id);
	
	public boolean deleteById(Long id);
	
	public List<TbAnvancePay> findByTbAnvancePay(TbAnvancePay tbAnvancePay);
	
	public Double acquireCustomerTotalAnvancePay(Long carInfoId);
	
	public List<TbAnvancePay> findTbAnvancePayListByCarInfoId(Long carInfoId);
}
