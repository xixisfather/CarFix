package com.selfsoft.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbBusinessBalanceDao;
import com.selfsoft.business.dao.ITbFixShareDao;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.service.IStatisticsWorkBusinessService;
import com.selfsoft.business.service.ITbFixShareService;
import com.selfsoft.business.vo.BusinessBalanceCostPriceVo;
import com.selfsoft.business.vo.WorkTypeHourPriceVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.secrity.dao.ITmWorkTypeDao;
import com.selfsoft.secrity.model.TmWorkType;
@Service("statisticsWorkBusinessService")
public class StatisticsWorkBusinessServiceImpl implements
		IStatisticsWorkBusinessService {

	@Autowired
	private ITbFixShareService tbFixShareService;
	@Autowired
	private ITbFixShareDao tbFixShareDao;
	@Autowired
	private ITbBusinessBalanceDao tbBusinessBalanceDao;
	
	public List<TbFixShare> getFixShareDetail( TbFixShare tbFixShare){
		
		List<TbFixShare> result  = tbFixShareService.findByEntity(tbFixShare);
		
		return result;
	}
	
	public List<TbFixShare> getTbFixShareByGroupFixPerson(TbFixShare tbFixShare){
		 List<TbFixShare> result = tbFixShareDao.getTbFixShareByGroupFixPerson(tbFixShare);
		 return result;
	}
	
	public List<WorkTypeHourPriceVo> getWorkTypeStat(TbFixShare tbFixShare){
		List<WorkTypeHourPriceVo> resultList = tbFixShareDao.getWorkTypeStat(tbFixShare);
		return resultList;
	}
	
	
	public Double getTotalCostPriceBusinessBalance(BusinessBalanceCostPriceVo businessBalanceCostPriceVo){
		Double result = 0D;
		
		List<Object> objList = tbBusinessBalanceDao.getTotalCostPriceBusinessBalance(businessBalanceCostPriceVo);
		Double xsPrice = 0D;
		Double wxPrice = 0D;
		Double ddxsPrice = 0D;
		if(objList != null && objList.size() >0){
			if(objList.get(0) != null )
				xsPrice = Double.valueOf(objList.get(0).toString()) ;
			if(objList.get(1) != null )
				wxPrice = Double.valueOf(objList.get(1).toString()) ;
			if(objList.get(2) != null )
				ddxsPrice = Double.valueOf(objList.get(2).toString()) ;
		}
		result = CommonMethod.convertRadixPoint(xsPrice + wxPrice + ddxsPrice, 2);
		
		return result;
		
	}
	
	
	
	
}
