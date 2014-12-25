package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.vo.BusinessBalanceCostPriceVo;
import com.selfsoft.business.vo.WorkTypeHourPriceVo;

public interface IStatisticsWorkBusinessService {

	public List<TbFixShare> getFixShareDetail( TbFixShare tbFixShare);
	
	public List<TbFixShare> getTbFixShareByGroupFixPerson(TbFixShare tbFixShare);
	
	public Double getTotalCostPriceBusinessBalance(BusinessBalanceCostPriceVo businessBalanceCostPriceVo);
	
	public List<WorkTypeHourPriceVo> getWorkTypeStat(TbFixShare tbFixShare);
}
