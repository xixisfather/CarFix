package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.StockTypeElements;

public interface ITmStockOutService {
	public void insert(TmStockOut tmStockOut);
	
	public TmStockOut findById(Long id);
	
	public void update(TmStockOut tmStockOut);
	
	public List<TmStockOutDetVo>  getStockOutDetVos(Long isConfirm ,Long stockOutId,Long stockOutType);
	
	public List<TmStockOutVo> getStockOutVos(String isConfirm, Long stockOutType,TmStockOut tmStockOut,Long stockOutId);
	
	public List<TmStockOut> findByEntity(TmStockOut tmStockOut);
	
	public boolean deleteStockOut(Long stockOutId);
	
	public List<TmStockOutDetVo> getSellByEntrustCode(String trustBill);
	
	public List<TmStockOutVo> getCustomerSell();
	
	public Double getTotalPriceByEntrustCode(String trustBill);
	
	public List<TmStockOutVo> getAllTypeStockOutDetVo(String isConfirms, Long stockOutType,TmStockOut tmStockOut);
	
	public void updateSellStatus(Long id ,Long status);
	
	public void updateTrustBill(String trustBill, Long status);
	
	
	/**
	 * 根据委托书号 来更新明细中BALANCEID为NULL的为参数BALANCEID
	 * @param entrustCode
	 * @param balanceId
	 */
	public void updateSellBalance(String entrustCode, Long balanceId);
	/**
	 * 根据委托书号，结算ID 查询明细中LIST
	 * @param entrustCode
	 * @param balanceId
	 * @return
	 */
	public List<TmStockOutDetVo> getSellDetailByBalanceId(String entrustCode ,Long balanceId);
	/**
	 * 根据委托书号，结算ID 返回明细记录的总金额
	 * @param entrustCode
	 * @param balanceId
	 * @return
	 */
	public Double getSellTotalPriceByBalance(String entrustCode ,Long balanceId);
	/**
	 * 根据单独销售ID 来更新明细中BALANCEID为NULL的为参数BALANCEID
	 * @param tmStockOutId
	 * @param balanceId
	 */
	public void updateCustomerSellBalance(Long tmStockOutId, Long balanceId);
	/**
	 * 据单独销售ID，结算ID 查询明细中LIST
	 * @param tmStockOutId
	 * @param balanceId
	 * @return
	 */
	public List<TmStockOutDetVo> getCustomerSellDetailByBalanceId(Long tmStockOutId,Long balanceId);
	/**
	 * 根据单独销售ID，结算ID 返回明细记录的总金额
	 * @param maintainCode
	 * @param balanceId
	 * @return
	 */
	public Double getCustomerSellTotalPriceByBalance(Long tmStockOutId ,Long balanceId);
	/**
	 * 根据委托书号 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return
	 */
	public List<TmStockOutDetVo> getSellDetailByEntrustCode(String entrustCode,Long balanceType);
	/**
	 * 根据单独销售ID 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return 
	 */
	public List<TmStockOutDetVo> getCustomerSellDetailByTmStockOutId(Long tmStockOutId,Long balanceType);
	/**
	 * 得到所有免费标志类型
	 * @Date      2010-7-28
	 * @Function  
	 * @return
	 */
	public List<String> getAllFreeFlagType();
	/**
	 * 得到所有免费标志类型
	 * @Date      2010-7-28
	 * @Function  
	 * @return
	 */
	public Map<Long,String> getAllFreeFlagMapType();
	/**
	 * 得到修理销售，单独销售结算清单
	 * @Date      2010-8-6
	 * @Function  
	 * @param beginDate
	 * @param endDate
	 * @param carModelType
	 * @return
	 */
	public BalanceSellCountVo getBalanceSellCountVo(String beginDate,String endDate,Long carModelType);
	
	public List<TmStockOutDetVo>  getStockOutDetVos(TmStockIn tmStockIn);
	
	public List<TmStockOutVo>  getStockOutVos(TmStockIn tmStockIn);
	
	public Double sumSingleSellByBanalceCode(String balanceCode);
	
	public List<TmStockOutDetVo> getStockOutDetVos(TmStockOut tmStockOut , Long stockOutId,Long stockOutType);
	
	public List<TmStockOutDetVo> getDrawStockOutDetVos(Long isConfirm ,Long stockOutId,Long stockOutType);

	public void updateTrustBillNotBalance(String trustBill, Long status);
	
	public void updateSellStatusNotBalance(Long id ,Long status);
}
