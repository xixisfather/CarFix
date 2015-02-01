package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbSmartBalance;
import com.selfsoft.secrity.model.TmUser;

public interface ITbSmartBalanceService {

	public void insertTbSmartBalance(TbSmartBalance tbSmartBalance, TmUser tmUser);

	public List<TbSmartBalance> findByTbSmartBalance(TbSmartBalance tbSmartBalance);
	
	public TbSmartBalance findById(Long id);
}
