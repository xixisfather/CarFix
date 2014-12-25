package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.vo.BalanceFixSellVo;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TbPartInfoReFlowStatVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TbStoreHouseSurveyVo;
import com.selfsoft.business.vo.TmDrawStatVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.business.vo.TmStoreHouseReceiverStatVo;

public interface IStatisticsStockInOutService {

	public List<TmStockInVo> getStockInTypeBill(Long elementType,TmStockIn tmStockIn);
	
	public List<TmStockOutVo> getStockOutTypeBill(Long elementType,TmStockOut tmStockOut);
	
	public List<TmLianceRegVo> getLianceRegisterTypeBill(TmLianceRegister tmLianceRegister);
	
	public List<TmLianceReturn> getLianceReturnTypeBill(TmLianceReturn tmLianceReturn);
	
	public List<TmLoanRegVo> getLoanRegisterTypeBill(TmLoanRegister tmLoanRegister);
	
	public List<TmLoanReturn> getLoanReturnTypeBill(TmLoanReturn tmLoanReturn);
	
	public Map<Long, String> getStockInOutElementMap();
	
	public List<TbMaintianVo> getMaintainTypeBill(TbMaintainPartContent tbMaintainPartContent);
	
	public List<TbPartReceiverStatVo> getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo);
	
	public TbPartReceiverStatVo getPartReceiveTotalStat(TbPartReceiverStatVo tbPartReceiverStatVo);
	
	public List<TbStoreHouseSurveyVo> getStoreHouseSurveyStat();
	
	public List<TbStoreHouseSurveyVo> getCarModelTypeSurveyStat();
	
	public TbStoreHouseSurveyVo getStoreHouseSurveyTotalStat();
	
	public TbStoreHouseSurveyVo getCarModelTypeSurveyTotalStat();
	
	public List<TbPartInfoReFlowStatVo> getAllTypeSumReFlowStat(List<TbPartInfo> partInfoList);
	
	public List<TbPartInfoReFlowStatVo> getPartInfoReFlowDetailStat(List<TbPartInfo> tbpartinfoList, Long elementType,TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo );
	
	public Map<TbPartInfoStockOutVo,List<TbPartInfoStockOutVo>> getToppartInfoStockOut(TbPartInfoStockOutVo tbPartInfoStockOutVo);
	
	public Map<Long, String> getStockOutElementMap();
	
	public List<TmStoreHouseReceiverStatVo> getStoreHouseReceiverStat(TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo);
	
	public List<TmDrawStatVo> getDrawStockOutStat(TmDrawStatVo tmDrawStatVo);
	
	public Map<TmDrawStatVo, List<TmDrawStatVo>> getGroupDrawStockOutStat(TmDrawStatVo tmDrawStatVo);
	
	public Map<DailyStockOutVo, List<DailyStockOutVo>> getDailyStockOutStat(DailyStockOutVo dailyStockOutVo);
	
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List<BalanceFixSellVo> getNotBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo);
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List<BalanceFixSellVo> getIsBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo);
	
	/**
	 *  仓库月度收发存统计
	 * @Date      2010-7-13
	 * @Function  
	 * @param tmStoreHouseReceiverStatVo
	 * @return
	 */
	public List<TmStoreHouseReceiverStatVo> getStoreHouseReceiverStatByMonth(String year,String mounth);
	
	public Map<TbPartReceiverStatVo, List<TbPartReceiverStatVo>> getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo,String year,String mounth);
	
	public Double sumStockDetailByEntrustId(Long entrustId);
	
	/**
	 * 根据结算ID得到修理费
	 * @param balanceId
	 * @return
	 */
	public Double getMaintainPriceByBalanceId(Long balanceId);
	/**
	 * 根据结算ID得到销售费
	 * @param balanceId
	 * @return
	 */
	public Double getStockPriceByBalanceId(Long balanceId);
	/**
	 * 根据结算ID得到材料成本费
	 * @param balanceId
	 * @return
	 */
	public Double getMaintainCostPriceByBalanceId(Long balanceId);
}
