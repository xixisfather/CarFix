package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.common.exception.MinusException;

public interface ITmStockinDetailService {

	public TmStockinDetail findById(Long id);
	
	public void insert(TmStockinDetail tmStockinDetail);
	
	public List<TmStockinDetail> findByEntity(TmStockinDetail tmStockinDetail);
	
	public void insertBatchStockinDetail(TmStockIn tmStockIn,String partCol) throws NumberFormatException, MinusException;
	
	public void partInfoStorkIn(Long partInfoId , Double newQuantity,Double userprice) throws MinusException;
	
	public void updatePartInfoQuantity(Long stockInId) throws MinusException;
	
	public void insertBatchOverFlowDetail(Long isConfirm ,Long stockInId,String partCol) throws NumberFormatException, MinusException;
	
	public List<TmStockInDetailVo> getStockInDetVo(Long stockInType,Long tmStockInId,Long isConfirm);
	
	public void updateBatchTbPartQuan(Long stockInId);
	
	public List<TmStockInVo> getStockInVo(Long stockInType,Long isConfirm, Long stockInId);
	
	public void updateTbPartInfoNocprice(Long tbPartInfoId , Double num);
	
	public void updateSellReturnQuantity(TmStockIn tmStockIn ,Long stockOutDetailId , Double num);
	
	public void updateSellReturn(TmStockIn tmStockIn);
	
	public List<TmStockInVo> getStockInVoStat(Long stockInType,Long isConfirm,TmStockIn tmStockIn);
	
	/**
	 * 查询统计所有已入库采购单
	 * @param tmStockIn
	 * @return
	 */
	public List<TmStockInDetailVo> getSellDetailByStat(TmStockIn tmStockIn);
	/**
	 * 查询所有采购入库下供应商的金额汇总
	 */
	public Map<Double,List<TbCustomerVo>>  getSellCustomerTotalPriceByStat(TmStockIn tmStockIn);
	/**
	 * 查询统计所有已入库采购单的总金额
	 * @param tmStockIn
	 * @return
	 */
	public Double getStockInTotalPriceByStat(TmStockIn tmStockIn);
	/**
	 * 配件批量入库(新增）
	 * @param formMap
	 * @param tmStockIn
	 * @throws MinusException 
	 */
	public void insertBatchStock(Map<String, String> formMap , TmStockIn tmStockIn) throws MinusException;
	
	/**
	 * 配件批量入库（更新）
	 * @param formMap
	 * @param tmStockIn
	 * @throws MinusException 
	 */
	public void stockBatchUpdate(Map<String, String> formMap , TmStockIn tmStockIn) throws MinusException;
	
	/**
	 * 根据入库类型查询入库单
	 * @param tmStockIn
	 * @return
	 */
	public List<TmStockInDetailVo> getSellDetailByStat(Long intype,TmStockIn tmStockIn);
}
