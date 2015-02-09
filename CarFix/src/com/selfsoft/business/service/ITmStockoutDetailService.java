package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.common.exception.MinusException;

public interface ITmStockoutDetailService {
	/**
	 * 批量添加出库详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但出库，并及时减少配件表的库存数量
	 */
	public void insertBatchStockOutDetail(TmStockOut tmStockOut,Long isConfirm ,String partCol)throws NumberFormatException, MinusException;
	
	/**
	 * 更新出库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function  
	 * @param stockInId
	 */
	public void updatePartInfoQuantity(Long stockOutId)throws MinusException;

	
	public void updateBatchStockOutDetail(TmStockOut tmStockOut,String partCol) throws NumberFormatException, MinusException;
	
	public void updateStockReturn(TmStockOut tmStockOut) throws MinusException;
	
	public void updateConfrimSellDetail(TmStockOut tmStockOut,String partCol) throws NumberFormatException, MinusException;
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * 若销售价为0时则取配件的默认销售价
	 * @param partId
	 * @param customerId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long customerId);
	
	/**
	 * 得到多个配件的客户最近一次的销售价，并字符串形式返回，前台进行分割处理
	 * 输出格式：配件id:销售价,配件id:销售价,
	 * 参数格式：配件id,配件id,配件id,
	 * @param partIds
	 * @param customerId
	 * @return
	 */
	public String getCustomerSellPriceByPartIds(String partIds,Long customerId);
	
	public void insertBatchStock(Map<String, String> formMap , TmStockOut tmStockOut);
	
	public void batchInsertStock(Map<String, String> formMap , TmStockOut tmStockOut);
	
	public void stockBatchUpdate(Map<String, String> formMap , TmStockOut tmStockOut);
	
	public Map<Double,List<TbCustomerVo>> getStockOutCustomerTotalPriceByStat(TmStockIn tmStockIn);
	
	public void insert(TmStockoutDetail tmStockoutDetail);
	
	public void updateTmStockoutDetailNotBalance(Long stockoutId);
}
