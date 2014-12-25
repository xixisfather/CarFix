package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public interface ITbBusinessBalanceItemService {
	
	public void insert(TbBusinessBalanceItem tbBusinessBalanceItem);
	
	public void update(TbBusinessBalanceItem tbBusinessBalanceItem);
	
	public List<TbBusinessBalanceItem> findTbBusinessBalanceItemListByTbBusinessBalanceId(Long tbBusinessBalanceId);
	
	public List<TbBusinessBalanceItem> findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long tbBusinessBalanceId);

	public List<StatisticsTbFixBusinessVo> staticsBalanceItem(TbBusinessBalance tbBusinessBalance);
}
