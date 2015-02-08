package com.selfsoft.business.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public interface ITbBusinessBalanceService {
	
	public void insert(TbBusinessBalance tbBusinessBalance);
	
	public void update(TbBusinessBalance tbBusinessBalance);
	
	public boolean deleteById(Long id);
	
	public TbBusinessBalance findById(Long id);
	
	public List<TbBusinessBalance> findAll();
	
	public void insertAll(TbBusinessBalance tbBusinessBalance);
	
	public List<TbBusinessBalance> findByTbBusinessBalance(TbBusinessBalance tbBusinessBalance);
	
	public boolean reBabanceTbBusinessBalance(Long id);
	
	public TbBusinessBalance findByEntrustId(Long entrustId);
	
	public TbBusinessBalance findByStockOutId(Long stockOutId);
	
	public List<TbBusinessBalance> findTbBusinessBalanceByBalanceCode(String balanceCode);
	
	public void updateTbBusinessBalanceStatusByBalanceCode(String balanceCode,Long status);
	
	public List<TbBusinessBalance> findTbBusinessBalanceToGroup(TbBusinessBalance tbBusinessBalance);
	
	public List<TbBusinessBalance> findTbBusinessBalanceToGroupReceiveFree(TbBusinessBalance tbBusinessBalance);

	public Map<String,BigDecimal> sumGroupList(List<TbBusinessBalance> list);
	
	public Double findEntrustHasPayedAmount(Long entrustId);
	
	public Double findStockOutHasPayedAmout(Long stockOutId);
	
	public Double calcItemFavourAmount(TbBusinessBalance tbBusinessBalance,String itemCode);
	
	public Map putEntrustBalanceReportParamMap(Long id,HttpServletRequest request);
	
	public Map putXsdBalanceReportParamMap(Long id,HttpServletRequest request);
	
	public List<TbBusinessBalance> fixBusinessBalanceOrder(String orderCode,String orderType);
	
	public List<TbBusinessBalance> findTbBusinessBalanceOweGroup(TbBusinessBalance tbBusinessBalance);

	public void registerReceiveFee(TbReceiveFree tbReceiveFree);
	
	public void deleteReceiveFee(TbReceiveFree tbReceiveFree);
	
	public List<TbBusinessBalance> findTbBusinessBalanceNoOweGroup(TbBusinessBalance tbBusinessBalance);
	
	public List<StatisticsTbFixBusinessVo> statisticsPayed(TbBusinessBalance tbBusinessBalance,Long type);
	
	public List<StatisticsTbFixBusinessVo> statisticsAll(TbBusinessBalance tbBusinessBalance);
	
	public void printTbBusinessBalanceTemplate(OutputStream os, String tpl,
			Long id,String companyName);
	
	public void exportTbBusinessBalanceGroupXls(OutputStream os, String tpl);
	
	public void exportOweXls(OutputStream os,String tpl,List<TbBusinessBalance> tbBusinessBalanceList);
		
	public boolean updateTbBusinessBalanceReback(String balanceCode);
}
