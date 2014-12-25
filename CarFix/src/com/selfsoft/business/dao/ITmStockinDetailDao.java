package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.framework.dao.IDao;

public interface ITmStockinDetailDao extends IDao<TmStockinDetail> {

	public List<TmStockInDetailVo> getStockInDetVo(Long stockInType,Long tmStockInId,Long isConfirm);
	
	public List<TmStockInVo> getStockInVo(Long stockInType,Long isConfirm, Long stockInId , TmStockIn tmStockIn);
	
	public List<TmStockInDetailVo> getStockInDetailVoByStat(Long stockInType,Long tmStockInId,String isConfirms,TmStockIn tmStockIn);
	
	public List<Object[]> getStockInCustomerTotalPriceByStat(Long stockInType,String isConfirms,TmStockIn tmStockIn);
	
	public Double getStockInTotalPriceByStat(Long stockInType,Long tmStockInId,String isConfirms,TmStockIn tmStockIn);
	
	public List<TmStockInVo> getStockInVoStat(Long stockInType,Long isConfirm,TmStockIn tmStockIn);
	
	public List<Object[]> getMasterStockIn(Long stockInType,String isConfirms,TmStockIn tmStockIn);
	
	public List<TmStockInDetailVo> getStockInDetVoNew(Long stockInType,Long tmStockInId,Long isConfirm);
	
	public List<Object[]> getMasterAllStock(TmStockIn tmStockIn);
}
