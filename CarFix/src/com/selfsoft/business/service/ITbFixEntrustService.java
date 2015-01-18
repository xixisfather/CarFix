package com.selfsoft.business.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;

public interface ITbFixEntrustService {
	
	public void insert(TbFixEntrust tbFixEntrust);
	
	public void update(TbFixEntrust tbFixEntrust);
	
	public boolean deleteById(Long id);
	
	public TbFixEntrust findById(Long id);
	
	public List<TbFixEntrust> findByTbFixEntrust(TbFixEntrust tbFixEntrust);
	
	public boolean updateTbFixEntrustNotValid(Long id);
	
	public void insertAll(TbFixEntrust tbFixEntrust,String partCol,Double totalPrice);
	
	public void updateAll(TbFixEntrust tbFixEntrust,String partCol,Double totalPrice);
	
	public List<TbFixEntrust> findTbMainFixEntrust(TbFixEntrust tbFixEntrust);
	
	public TbFixEntrust findByEntrustCode(String entrustCode);
	
	public void updateTbFixEntrustHasSendPart(String entrustCode);
	
	public boolean isTbFixEntrustHasSendPart(String entrustCode);
	
	public void updateTbFixEntrustReBalance(TbFixEntrust tbFixEntrust);
	
	public boolean cancelTbFixEntrustFinish(Long id);
	
	public List<TbFixEntrust> findTbFixEntrustByFixDate(Date planFixTime);
	
	public Map putReportParamMap(Long id);
	
	public List<StatisticsTbFixBusinessVo> statisticsTbFixEntrust(TbFixEntrust tbFixEntrust);
	
	public List<StatisticsTbFixBusinessVo> statisticsFixType(TbFixEntrust tbFixEntrust);
	
	public Integer statisticsBalance(Long entrustStatus,TbFixEntrust tbFixEntrust);
	
	public List<StatisticsTbFixBusinessVo> statisticsBalanceShow(TbFixEntrust tbFixEntrust);
	
	public Map putSendPersonReportParamMap(Long id);
	
	public BigDecimal getTotalCostPriceByEntrustList(List<TbFixEntrust> tbfixEntrustList);
	
	public BigDecimal getTotalSellPriceByEntrustList(List<TbFixEntrust> tbfixEntrustList);
	
	public List<TbFixEntrust> findMaintainCarFixEntrust();
	
	public List<TbFixEntrust> findInsuranceCarFixEntrust();
	
	public void printTbFixEntrustTemplate(OutputStream os, String tpl, Long tbFixEntrustId);
	
	public void printTbFixEntrustTemplateBlank(OutputStream os, String tpl, Long tbFixEntrustId);
	
	public void printTbFixEntrustTemplateXTL(OutputStream os, String tpl, Long tbFixEntrustId);
	
	public void printTbFixEntrustTemplateBlankXTL(OutputStream os, String tpl, Long tbFixEntrustId);
	
	public Map putEntrustBalanceReportParamMap(Long id,
			HttpServletRequest request);
}
