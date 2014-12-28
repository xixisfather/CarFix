package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.common.exception.MinusException;

public interface ITbMaintainPartContentService {

	public List<TbMaintainPartContent> findByEntity(TbMaintainPartContent tbMaintainPartContent);
	
	public List<TbMaintainPartContent> findByEntity(TbMaintainPartContent tbMaintainPartContent,Long balanceType);
	
	public String batchInsertMaintain(String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException;
	
	public void updatePartInfoQuantity(Long partId,Double quantity) throws MinusException;
	
	public void updateMaintainState(String maintainCode) throws MinusException;
	
	public List<TbMaintianVo> getTbMaintianVos(String isConfirms  , TbMaintainPartContent queryEntity);
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode);
	
	public void updateMaintain(String maintainCode ,String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException;
	
	public boolean deleteTbMaintain(String maintainCode);
	
	public void updateConfirmMaintain(String maintainCode ,String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException;
	
	public Double getMaintainPartContentTotalPrice(String maintainCode);
	
	public void updateTbMaintainStatus(Long entrustId , Long status);
	
	public void updateMaintainDetailBalance(Long entrustId , Long balance);
	
	public List<TbMaintianVo> getMaintainContentsByBalanceId(Long entrustId ,Long balanceId);
	
	public Double getTotalPriceByBalanceId(Long entrustId ,Long balanceId );
	
	public List<TbMaintianVo> getTbMaintianDetailVosByEntrustId(Long entrustId,Long balanceType);
	
	public void updateTbMaintainStatus(Long entrustId ,Long balanceId , Long status);
	
	public List<TbMaintainPartContent> getViewEntrustMaintianContent(Long entrustId);
	/**
	 * 如果委托书是未发料的情况，将修改委托书的状态
	 * @param entrustCode
	 * @return
	 */
	public void updateNoMaintainNoReBalance(String entrustCode);
	/**
	 * 得到某个配件卖个客户最近一次的销售价
 	 * 若销售价为0时则取配件的默认销售价
	 * @param partId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long entrustId);
	/**
	 * 维修发料是否能修改     已结算状态不能修改 return false  其余 return true
	 * @param maintainCode
	 * @return
	 * @throws Exception
	 */
	public boolean isMaintainEdit(String maintainCode);
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,TbMaintainPartContent tbMaintainPartContent);
	
	public double getAllStockOutNum(Long partId);
	
	public List<TbMaintianVo> getTbMaintianDetailVosByEntrustIdPrint(Long entrustId,Long balanceType);
}
