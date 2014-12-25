package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.dao.IDao;

public interface ITmStockOutDao extends IDao<TmStockOut> {

	public List<TmStockOutDetVo>  getStockOutDetVos(String isConfirm ,Long stockOutId,Long stockOutType);
	
	public List<TmStockOutVo> getStockOutVos(String isConfirms, Long stockOutType,TmStockOut tmStockOut,Long stockOutId);
	
	public void deleteStockOutDetail(Long stockOutId);
	
	public List<TmStockOutDetVo>getStockOutDetVosBySell(String isConfirm ,Long stockOutType,String trustBill,Long customerId,Long balanceId,Long stockoutId,Long balanceType);
	
	public void updateSellBalanceId(Long stockoutId , Long balanceId );
	
	public Long getBalanceSellCount(Long sellType , Long balanceType,BalanceSellCountVo balanceSellCountVo);
	
	public Long getMaintainBalanceSellCount(Long balanceType);
	
	public List<TmStockOutDetVo>  getStockOutDetVos(Object queryObj);
	
	public List<TmStockOutVo> getStockOutVos(Object queryObj);
	
	public List<Object[]> getStockOutCustomerTotalPriceByStat(TmStockIn tmStockIn);
	
	public double sumStockoutDetailByEntrustCode(String entrustCode);
	
	public double sumSingleSellByBanalce(Long balanceId);

	public double sumStockoutByBalanceId(Long balanceId);
	
	public List<TmStockOutDetVo>  getStockOutDetVos(TmStockOut tmStockOut,Long stockOutId,Long stockOutType);
}
