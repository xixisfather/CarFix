package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public interface ITbReceiveFreeService {
	
	public void insert(TbReceiveFree tbReceiveFree);
	
	public void update(TbReceiveFree tbReceiveFree);
	
	public void deleteById(Long id);
	
	public TbReceiveFree findById(Long id);
	
	public List<TbReceiveFree> findByTbReceiveFree(TbReceiveFree tbReceiveFree);
	
	public StatisticsTbFixBusinessVo staticsTbReceiveFree(List<TbBusinessBalance> tbBusinessBalanceList);
	
	public List<TbReceiveFree> findByBalanceId(Long balanceId);
	
	public void deleteByBalanceId(Long balanceId);
}
