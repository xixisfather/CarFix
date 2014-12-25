package com.selfsoft.baseinformation.dao;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.business.vo.BalanceFixSellVo;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbPartInfoReFlowStatVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TmDrawStatVo;
import com.selfsoft.business.vo.TmStoreHouseReceiverStatVo;
import com.selfsoft.framework.dao.IDao;

public interface ITbPartInfoDao extends IDao<TbPartInfo> {

	public List<TbPartInfo> getTbPartInfoByCollectionCode(String collectionCode);
	
	public TbPartInfo getPartInfoByCodeAndStore(String partCode,Long storeHoseId);
	
	public List getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo);
	
	public List getPartReceiveQuantityStat(TbPartReceiverStatVo tbPartReceiverStatVo);
	
	public List getPartReceivePriceStat(TbPartReceiverStatVo tbPartReceiverStatVo);
	
	public List getStoreHouseSurveyStat();
	
	public List getCarModelTypeSurveyStat();
	
	public List getAllTypeSumReFlowStat(String partInfoId);
	
	public List getPartInfoReFlowDetailStat(String partInfoIds , Long elementType ,TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo);
	
	public List getToppartInfoStockOut(TbPartInfoStockOutVo tbPartInfoStockOutVo);
	
	public List getStoreHouseReceiverStat(TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo);
	
	public List getDrawStockOutStat(TmDrawStatVo tmDrawStatVo);
	
	public List getGroupDrawStockOutStat(TmDrawStatVo tmDrawStatVo);
	
	public List getDailyStockOutStat(DailyStockOutVo dailyStockOutVo);
	
	public List getNotBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo);
	
	public List getIsBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo);
	
	public List<Object[]> getTbPartInfoVoByCustomerId(TbPartInfo tbPartInfo , String customerId);
	
	public Double getOutTbPartInfoByMonth(Long partInfoId);
	
	public Double getInTbPartInfoByMonth(Long partInfoId);
	
	public List<TbPartInfo> checkHousePartInfo(Map<Long, Long> paramMap);
	
	public Double getOutTmStoreHouseAsBegin(Long storeHouseId,String year,String mounth,Long outType);
	
	public Double getOutTmStoreHouseAsFinal(Long storeHouseId,String year,String mounth,Long outType);
	
	public Double getInTmStoreHouseAsFinal(Long storeHouseId,String year,String mounth,Long inType);
	
	public Double getInTmStoreHouseAsBegin(Long storeHouseId,String year,String mounth,Long inType);
	
	public List getStoreHouseReceiverStat(String year,String mounth);

	public List getOutTbPartInfoByMonth(Long partInfoId,String year,String mounth,int bRfType);
	
	public List getInTbPartInfoByMonth(Long partInfoId,String year,String mounth,int bRfType);
	
	public List getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo,String year,String mounth);
	
}
