package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public interface ITbFixEntrustContentService {
	
	public void insert(TbFixEntrustContent tbFixEntrustContent);
	
	public void update(TbFixEntrustContent tbFixEntrustContent);
	
	public void updateTbFixEntrustContentUnBalance(Long tbFixEntrustId);
	
	public TbFixEntrustContent findById(Long id);
	
	public boolean deleteById(Long id);
	
	public List<TbFixEntrustContent> findAll();
	
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustId(Long tbFixEntrustId);
	
	public Double countTbFixEnTrustContentByTbFixEntrustId(Long tbFixEntrustId);
	
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceIdIsNull(Long tbFixEntrustId);
	
	public Double countTbFixEnTrustContentByTbFixEntrustIdAndBalanceIdIsNull(Long tbFixEntrustId);
	
	public void updateTbFixEnTrustContentBalanceId(Long tbFixEntrustId,Long balanceId);
	
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceId(Long tbFixEntrustId,Long balanceId);

	public Double countTbFixEnTrustContentByTbFixEntrustIdAndBalanceId(Long tbFixEntrustId,Long balanceId);
	
	public Double staticsTbFixEntrustContent(Long entrustStatus,TbFixEntrust tbFixEntrust);
	
	public List<StatisticsTbFixBusinessVo> staticsTbFixEntrustContentShow(TbFixEntrust tbFixEntrust);
}
