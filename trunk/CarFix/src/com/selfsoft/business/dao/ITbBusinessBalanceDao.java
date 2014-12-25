package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.vo.BusinessBalanceCostPriceVo;
import com.selfsoft.framework.dao.IDao;

public interface ITbBusinessBalanceDao extends IDao<TbBusinessBalance>{

	public List<Object> getTotalCostPriceBusinessBalance(BusinessBalanceCostPriceVo businessBalanceCostPriceVo);
	
	public List<Long> getTbEntrustByBalanceDate(TbFixEntrust tbFixEntrust);
}
