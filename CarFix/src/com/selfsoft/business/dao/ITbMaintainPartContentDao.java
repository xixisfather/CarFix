package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.framework.dao.IDao;


public interface ITbMaintainPartContentDao extends IDao<TbMaintainPartContent> {

	public List<TbMaintianVo> getTbMaintianVos(String isConfirms, TbMaintainPartContent queryEntity);

	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,Long entrustId,Long balanceId,Long balanceType);
	
	public boolean deleteTbMaintain(String maintainCode);
	
	public void updateMaintainDetailBalance(Long entrustId , Long balance);
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * @param partId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long customerId);
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,TbMaintainPartContent tbMaintainPartContent);
	
	public void updateTbMaintainBalanceId(Long balanceId,Long status ,Long entrustId);
	
	public double sumMaintainByEntrustId(Long entrustId);
	
	public double sumMaintainCostPriceByBalanceId(Long balanceId);
	
	public double sumMaintainPriceByBalanceId(Long balanceId);
	
	public double getAllStockOutNum(Long partId);
	public List<TbMaintianVo> getTbMaintianDetailVosByPrint(String maintainCode,Long entrustId,Long balanceId,Long balanceType);
}
