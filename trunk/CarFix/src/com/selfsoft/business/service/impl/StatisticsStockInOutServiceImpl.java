package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.business.dao.ITbMaintainPartContentDao;
import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLianceReturnService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmLoanReturnService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
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
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.service.ITmUserService;
@Service("statisticsStockInOutService")
public class StatisticsStockInOutServiceImpl implements IStatisticsStockInOutService {

	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	@Autowired
	private ITmLianceReturnService tmLianceReturnService;
	@Autowired
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmLoanReturnService tmLoanReturnService;
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITbCarInfoService tbCarInfoService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	@Autowired
	private ITmStockOutDao tmStockOutDao;
	@Autowired
	private ITbMaintainPartContentDao tbMaintainPartContentDao;

	/**
	 * 查询已入库的入库单（类型包括采购，调拨，报溢，销售退货，其它）
	 */
	public List<TmStockInVo> getStockInTypeBill(Long elementType,TmStockIn tmStockIn){
		List<TmStockInVo> result = tmStockinDetailService.getStockInVoStat(elementType, Constants.CONFIRM,tmStockIn);
		return result;
	}
	
	/**
	 * 查询已入库的维修发料单
	 */
	public List<TbMaintianVo> getMaintainTypeBill(TbMaintainPartContent tbMaintainPartContent){
		String isConfirms = Constants.CONFIRM+","+Constants.RE_BALANCE+","+Constants.FINSH_BALANCE;
		List<TbMaintianVo> result = tbMaintainPartContentService.getTbMaintianVos(isConfirms, tbMaintainPartContent);
		return result;
	}
	
	/**
	 * 查询已出库的出库单（类型包括销售，调拨，报损，采购退货，领用）
	 */
	public List<TmStockOutVo> getStockOutTypeBill(Long elementType,TmStockOut tmStockOut){
		String isConfirms = Constants.CONFIRM+","+Constants.RE_BALANCE+","+Constants.FINSH_BALANCE;
		List<TmStockOutVo> result = tmStockOutService.getAllTypeStockOutDetVo(isConfirms, elementType, tmStockOut);
		return result;
	}
	/**
	 * 查询已入库借进登记单
	 */
	public List<TmLianceRegVo> getLianceRegisterTypeBill(TmLianceRegister tmLianceRegister){
		List<TmLianceRegVo> result = tmLianceRegisterService.getLianceRegVo(Constants.CONFIRM, null, tmLianceRegister,null);
		return result;
	}
	/**
	 * 查询已入库借进归还单
	 */
	public List<TmLianceReturn> getLianceReturnTypeBill(TmLianceReturn tmLianceReturn){
		List<TmLianceReturn> result = tmLianceReturnService.findByEntity(tmLianceReturn);
		return result;
	}
	/**
	 * 查询已入库借出登记单
	 */
	public List<TmLoanRegVo> getLoanRegisterTypeBill(TmLoanRegister tmLoanRegister){
		List<TmLoanRegVo> result = tmLoanRegisterService.getloanRegVo(Constants.CONFIRM, null, tmLoanRegister,null);
		return result;
	}
	/**
	 * 查询已入库借出归还单
	 */
	public List<TmLoanReturn> getLoanReturnTypeBill(TmLoanReturn tmLoanReturn){
		List<TmLoanReturn> result = tmLoanReturnService.findByEntity(tmLoanReturn);
		return result;
	}
	
	/**
	 * 得到所有出入库类型
	 * @return
	 */
	public Map<Long, String> getStockInOutElementMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		map.put(StockTypeElements.STOCK.getElementValue(), StockTypeElements.STOCK.getElementName());
		map.put(StockTypeElements.ALLOT.getElementValue(), StockTypeElements.ALLOT.getElementName());
		map.put(StockTypeElements.OTHERSTOCKIN.getElementValue(), StockTypeElements.OTHERSTOCKIN.getElementName());
		map.put(StockTypeElements.OVERFLOW.getElementValue(), StockTypeElements.OVERFLOW.getElementName());
		map.put(StockTypeElements.STOCKRETURN.getElementValue(), StockTypeElements.STOCKRETURN.getElementName());
		map.put(StockTypeElements.MAINTAIN.getElementValue(), StockTypeElements.MAINTAIN.getElementName());
		map.put(StockTypeElements.SELLSTOCKOUT.getElementValue(), StockTypeElements.SELLSTOCKOUT.getElementName());
		map.put(StockTypeElements.DRAWSTOCKOUT.getElementValue(), StockTypeElements.DRAWSTOCKOUT.getElementName());
		map.put(StockTypeElements.ALLOTSTOCKOUT.getElementValue(), StockTypeElements.ALLOTSTOCKOUT.getElementName());
		map.put(StockTypeElements.SHATTERSTOCKOUT.getElementValue(), StockTypeElements.SHATTERSTOCKOUT.getElementName());
		map.put(StockTypeElements.LIANCEREGISTER.getElementValue(), StockTypeElements.LIANCEREGISTER.getElementName());
		map.put(StockTypeElements.LIANCERETURN.getElementValue(), StockTypeElements.LIANCERETURN.getElementName());
		map.put(StockTypeElements.LOANREGISTER.getElementValue(), StockTypeElements.LOANREGISTER.getElementName());
		map.put(StockTypeElements.LOANRETURN.getElementValue(), StockTypeElements.LOANRETURN.getElementName());
		
		return map;
	}
	
	
	/**
	 * 配件期间收发存统计 所有列表
	 */
	public List<TbPartReceiverStatVo> getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo){
		List<TbPartReceiverStatVo> result = new ArrayList<TbPartReceiverStatVo>();
		List<Object[]> list = tbPartInfoDao.getPartReceiveListStat(tbPartReceiverStatVo);
		for(Object[]  obj : list){
			
			TbPartReceiverStatVo vo = new TbPartReceiverStatVo();
			vo.setStoreHouseName(obj[0] != null ? obj[0].toString() : null);
			vo.setPartCode(obj[1] != null ? obj[1].toString() : null);
			vo.setStoreQuantity(obj[2] != null ? Double.valueOf(obj[2].toString()) : null);
			vo.setStorePrice(obj[3] != null ? Double.valueOf(obj[3].toString()) : null);
			vo.setPartName(obj[4] != null ? obj[4].toString() : null);
			vo.setStockInQuantity(obj[5] != null ? Double.valueOf(obj[5].toString()) : null);
			vo.setStockOutQuantity(obj[6] != null ? Double.valueOf(obj[6].toString()) : null);
			vo.setStockInPrice(obj[7] != null ? Double.valueOf(obj[7].toString()) : null);
			vo.setStockOutPrice(obj[8] != null ? Double.valueOf(obj[8].toString()) : null);
			vo.setTbPartInfoId(obj[9] != null ? Long.valueOf(obj[9].toString()) : null);
			
			
			/*
			Double StockInQuantity = vo.getStockInQuantity()!=null?vo.getStockInQuantity():0D;
			Double StockOutQuantity = vo.getStockOutQuantity()!=null?vo.getStockOutQuantity():0D;
			Double storequantity = vo.getStoreQuantity()!=null?vo.getStoreQuantity():0D;
			if((StockInQuantity-StockOutQuantity)!= storequantity){
				
				TbPartInfo tbPartInfo = tbPartInfoDao.findById(vo.getTbPartInfoId());
				System.out.println("StockInQuantity = " + StockInQuantity+ "StockOutQuantity = "+StockOutQuantity+"storequantity = "+storequantity);
				Double quantity = vo.getStockInQuantity() - vo.getStockOutQuantity();
				tbPartInfo.setQuantity(quantity);
				Double stockMoney = CommonMethod.convertRadixPoint(tbPartInfo.getCostPrice()*quantity,2);
				tbPartInfo.setStockMoney(Math.abs(stockMoney));
				tbPartInfoDao.update(tbPartInfo);
				
				vo.setStoreQuantity(quantity);
			}
			
			
			TbPartInfo pi = tbPartInfoDao.findById(vo.getTbPartInfoId());
			Double outQ = tbPartInfoDao.getOutTbPartInfoByMonth(vo.getTbPartInfoId());
			Double inQ = tbPartInfoDao.getInTbPartInfoByMonth(vo.getTbPartInfoId());
			
			Double stockInQuantity = vo.getStockInQuantity()!=null?vo.getStockInQuantity():0D;
			Double stockOutQuantity = vo.getStockOutQuantity()!=null?vo.getStockOutQuantity():0D;
			
			Double qsQauntity = (stockInQuantity - inQ) - (stockOutQuantity - outQ);
			vo.setQsQauntity(qsQauntity);
			vo.setQsPrice(CommonMethod.convertRadixPoint(pi.getCostPrice()*qsQauntity,2));
			*/
			result.add(vo);
		}
		return result;
	}
	
	
	/**
	 * 配件月度期间收发存统计 所有列表
	 */
	public Map<TbPartReceiverStatVo, List<TbPartReceiverStatVo>> getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo,String year,String mounth){
		
		if(year==null || mounth==null) return null;
		Double totalQcPrice = 0d;
		Double totalQmPrice = 0d;
		Double totalStockInPrice = 0d;
		Double totalStockOutPrice = 0d;
		Map<TbPartReceiverStatVo, List<TbPartReceiverStatVo>> resultMap = new HashMap<TbPartReceiverStatVo, List<TbPartReceiverStatVo>>();
		TbPartReceiverStatVo statVo = new TbPartReceiverStatVo();
		List<TbPartReceiverStatVo> result = new ArrayList<TbPartReceiverStatVo>();
		List<Object[]> list = tbPartInfoDao.getPartReceiveListStat(tbPartReceiverStatVo,year,mounth);
		for(Object[]  obj : list){
			
			TbPartReceiverStatVo vo = new TbPartReceiverStatVo();
			vo.setStoreHouseName(obj[0] != null ? obj[0].toString() : null);
			vo.setPartCode(obj[1] != null ? obj[1].toString() : null);
			vo.setStoreQuantity(obj[2] != null ? Double.valueOf(obj[2].toString()) : null);
			vo.setStorePrice(obj[3] != null ? Double.valueOf(obj[3].toString()) : null);
			vo.setPartName(obj[4] != null ? obj[4].toString() : null);
			vo.setStockInQuantity(obj[5] != null ? Double.valueOf(obj[5].toString()) : null);
			vo.setStockOutQuantity(obj[6] != null ? Double.valueOf(obj[6].toString()) : null);
			vo.setStockInPrice(obj[7] != null ? Double.valueOf(obj[7].toString()) : null);
			vo.setStockOutPrice(obj[8] != null ? Double.valueOf(obj[8].toString()) : null);
			vo.setTbPartInfoId(obj[9] != null ? Long.valueOf(obj[9].toString()) : null);
			vo.setSellStockOutPrice(obj[10] != null ? Double.valueOf(obj[10].toString()) : null);
			
			List<Object[]> inBeginList = tbPartInfoDao.getInTbPartInfoByMonth(vo.getTbPartInfoId(), year, mounth, 0);
			List<Object[]> inFinalList= tbPartInfoDao.getInTbPartInfoByMonth(vo.getTbPartInfoId(), year, mounth, 1);
			
			List<Object[]> outBeginList= tbPartInfoDao.getOutTbPartInfoByMonth(vo.getTbPartInfoId(), year, mounth, 0);
			List<Object[]> outFinalList= tbPartInfoDao.getOutTbPartInfoByMonth(vo.getTbPartInfoId(), year, mounth, 1);
			
			Double inBeginQuantity = inBeginList.get(0)[0] != null ?new Double (inBeginList.get(0)[0].toString()):0d;
			Double inBeginPrcie = inBeginList.get(0)[0] != null ?new Double (inBeginList.get(0)[1].toString()):0d;
			
			Double inFinalQuantity = inFinalList.get(0)[0] != null ?new Double (inFinalList.get(0)[0].toString()):0d;
			Double inFinalPrcie = inFinalList.get(0)[0] != null ?new Double (inFinalList.get(0)[1].toString()):0d;
			
			Double outBeginQuantity =  outBeginList.get(0)[0] != null ?new Double (outBeginList.get(0)[0].toString()):0d;
			Double outBeginPrcie =  outBeginList.get(0)[0] != null ?new Double (outBeginList.get(0)[1].toString()):0d;
			
			Double outFinalQuantity = outFinalList.get(0)[0] != null ? new Double (outFinalList.get(0)[0].toString()):0d;
			Double outFinalPrcie = outFinalList.get(0)[1] != null ? new Double (outFinalList.get(0)[1].toString()):0d;
			
			Double qcQuantity = inBeginQuantity - outBeginQuantity;
			Double qcPrice = inBeginPrcie -outBeginPrcie;
			Double qmQuantity = inFinalQuantity - outFinalQuantity;
			Double qmPrice = inFinalPrcie - outFinalPrcie;
			
			vo.setQcQuantity(qcQuantity);
			vo.setQcPrice(CommonMethod.convertRadixPoint(qcPrice,2));
			vo.setQmQuantity(qmQuantity);
			vo.setQmPrice(CommonMethod.convertRadixPoint(qmPrice,2));
			
			totalQcPrice += qcPrice;
			totalQmPrice += qmPrice;
			totalStockInPrice += vo.getStockInPrice()!=null ?vo.getStockInPrice():0d;
			totalStockOutPrice += vo.getStockOutPrice()!=null?vo.getStockOutPrice():0d;
			result.add(vo);
		}
		
		statVo.setTotalQcPrice(CommonMethod.convertRadixPoint(totalQcPrice,2));
		statVo.setTotalQmPrice(CommonMethod.convertRadixPoint(totalQmPrice,2));
		statVo.setTotalStockInPrice(CommonMethod.convertRadixPoint(totalStockInPrice,2));
		statVo.setTotalStockOutPrice(CommonMethod.convertRadixPoint(totalStockOutPrice,2));
		
		
		resultMap.put(statVo, result);
		return resultMap;
	}
	
	
	/**
	 * 配件期间收发存统计 金额，数据 合计数
	 */
	public TbPartReceiverStatVo getPartReceiveTotalStat(TbPartReceiverStatVo tbPartReceiverStatVo){
		
		List<Object[]> quantitylist = tbPartInfoDao.getPartReceiveQuantityStat(tbPartReceiverStatVo);
		List<Object[]> pricelist = tbPartInfoDao.getPartReceivePriceStat(tbPartReceiverStatVo);
		
		Object[] quantityObj =  quantitylist.get(0);
		Object[] priceObj =  pricelist.get(0);
		
		TbPartReceiverStatVo result = new TbPartReceiverStatVo();
		Double totalStockInQuantity = quantityObj[0] != null ? Double.valueOf(quantityObj[0].toString()) : 0D;
		Double totalStockOutQuantity = quantityObj[1] != null ? Double.valueOf(quantityObj[1].toString()) : 0D;
		Double totalStockInPrice = priceObj[0] != null ? Double.valueOf(priceObj[0].toString()) : 0D;
		Double totalStockOutPrice = priceObj[1] != null ? Double.valueOf(priceObj[1].toString()) : 0D;


		result.setTotalStockInQuantity(totalStockInQuantity);
		result.setTotalStockOutQuantity(totalStockOutQuantity);
		result.setTotalStockInPrice(CommonMethod.convertRadixPoint(totalStockInPrice,2));
		result.setTotalStockOutPrice(CommonMethod.convertRadixPoint(totalStockOutPrice,2));
		
		return result;
	}
	

	/**
	 * 仓库概貌统计  -- 车型分组
	 * @Date      2010-7-8
	 * @Function  
	 * @return
	 */
	public List<TbStoreHouseSurveyVo> getCarModelTypeSurveyStat(){
		List<TbStoreHouseSurveyVo> results = new ArrayList<TbStoreHouseSurveyVo>();
		List<Object[]> list = tbPartInfoDao.getCarModelTypeSurveyStat();

		for(Object[]  obj : list){
			TbStoreHouseSurveyVo vo = new TbStoreHouseSurveyVo();
			vo.setModelTypeName(obj[0]!=null?obj[0].toString():null);
			vo.setPartInfoCategory(obj[1]!=null?Long.valueOf(obj[1].toString()):null);
			vo.setCostPrice(obj[2]!=null?Double.valueOf(obj[2].toString()):null);
			vo.setSellPrice(obj[3]!=null?Double.valueOf(obj[3].toString()):null);
			vo.setLiancePrice(obj[4]!=null?Double.valueOf(obj[4].toString()):null);
			vo.setLoanPrice(obj[5]!=null?Double.valueOf(obj[5].toString()):null);
			vo.setZeroCategory(obj[6]!=null?Long.valueOf(obj[6].toString()):0L);
			results.add(vo);
		}
		return results;
		
	}
	/**
	 * 仓库概貌统计  -- 仓库分组
	 * @Date      2010-7-8
	 * @Function  
	 * @return
	 */
	public List<TbStoreHouseSurveyVo> getStoreHouseSurveyStat(){
		List<TbStoreHouseSurveyVo> results = new ArrayList<TbStoreHouseSurveyVo>();
		List<Object[]> list = tbPartInfoDao.getStoreHouseSurveyStat();
		
		for(Object[]  obj : list){
			TbStoreHouseSurveyVo vo = new TbStoreHouseSurveyVo();
			vo.setHouseName(obj[0]!=null?obj[0].toString():null);
			vo.setPartInfoCategory(obj[1]!=null?Long.valueOf(obj[1].toString()):null);
			vo.setCostPrice(obj[2]!=null?Double.valueOf(obj[2].toString()):null);
			vo.setSellPrice(obj[3]!=null?Double.valueOf(obj[3].toString()):null);
			vo.setLiancePrice(obj[4]!=null?Double.valueOf(obj[4].toString()):null);
			vo.setLoanPrice(obj[5]!=null?Double.valueOf(obj[5].toString()):null);
			vo.setZeroCategory(obj[6]!=null?Long.valueOf(obj[6].toString()):0L);
			results.add(vo);
		}
		return results;
		
	}
	
	public TbStoreHouseSurveyVo getStoreHouseSurveyTotalStat(){
		
		List<Object[]> list = tbPartInfoDao.getStoreHouseSurveyStat();
		Double totalCostPrice = 0D;
		Double totalSellPrice = 0D;
		Double totalLiancePrice = 0D;
		Double totalLoanPrice = 0D;
		
		for(Object[]  obj : list){
			Double tcp = obj[2]!=null?Double.valueOf(obj[2].toString()):0D;
			Double tsp = obj[3]!=null?Double.valueOf(obj[3].toString()):0D;
			Double tlip = obj[4]!=null?Double.valueOf(obj[4].toString()):0D;
			Double tlop = obj[5]!=null?Double.valueOf(obj[5].toString()):0D;
			
			totalCostPrice += tcp;
			totalSellPrice += tsp;
			totalLiancePrice += tlip;
			totalLoanPrice += tlop;
		}
		
		TbStoreHouseSurveyVo result = new TbStoreHouseSurveyVo();
		result.setTotalCostPrice(totalCostPrice);
		result.setTotalSellPrice(totalSellPrice);
		result.setTotalLiancePrice(totalLiancePrice);
		result.setTotalLoanPrice(totalLoanPrice);
		
		return result;
	}
	
	
	
	public TbStoreHouseSurveyVo getCarModelTypeSurveyTotalStat(){
		
		List<Object[]> list = tbPartInfoDao.getCarModelTypeSurveyStat();
		Double totalCostPrice = 0D;
		Double totalSellPrice = 0D;
		Double totalLiancePrice = 0D;
		Double totalLoanPrice = 0D;
		
		for(Object[]  obj : list){
			Double tcp = obj[2]!=null?Double.valueOf(obj[2].toString()):0D;
			Double tsp = obj[3]!=null?Double.valueOf(obj[3].toString()):0D;
			Double tlip = obj[4]!=null?Double.valueOf(obj[4].toString()):0D;
			Double tlop = obj[5]!=null?Double.valueOf(obj[5].toString()):0D;
			
			totalCostPrice += tcp;
			totalSellPrice += tsp;
			totalLiancePrice += tlip;
			totalLoanPrice += tlop;
		}
		
		TbStoreHouseSurveyVo result = new TbStoreHouseSurveyVo();
		result.setTotalCostPrice(totalCostPrice);
		result.setTotalSellPrice(totalSellPrice);
		result.setTotalLiancePrice(totalLiancePrice);
		result.setTotalLoanPrice(totalLoanPrice);
		
		return result;
	}
	
	
	/**
	 * 配件流量及流向统计 -- 所有类型配件流量统计
	 * @Date      2010-7-9
	 * @Function  
	 * @param partInfoId
	 * @return
	 */
	public List getAllTypeSumReFlowStat(List<TbPartInfo> tbPartInfoList){
		List<TbPartInfoReFlowStatVo> results = new ArrayList<TbPartInfoReFlowStatVo>();
		
		List<Object[]> list = null;
		if(null != tbPartInfoList && tbPartInfoList.size()>0){
			String[] partIds = new String[tbPartInfoList.size()];
			for(int i=0; i<tbPartInfoList.size();i++){
				partIds[i] = tbPartInfoList.get(i).getId()+"";
			}
			
			list = tbPartInfoDao.getAllTypeSumReFlowStat(StringUtils.join(partIds, ","));
			for(Object[] obj :list){
				if(obj[1] == null || Double.valueOf(obj[1].toString()) <= 0) 
					continue;
				
				TbPartInfoReFlowStatVo vo = new TbPartInfoReFlowStatVo();
				vo.setTypeValue(new Long(obj[0].toString()));
				vo.setTypeName(StockTypeElements.getElementMap().get(vo.getTypeValue()));
				vo.setTotalQuantity(Double.valueOf(obj[1].toString()));
				results.add(vo);
			}
		}else{
			list=tbPartInfoDao.getAllTypeSumReFlowStat(null);
			for(Object[] obj :list){
				if(obj[1] == null || Double.valueOf(obj[1].toString()) <= 0) 
					continue;
				
				TbPartInfoReFlowStatVo vo = new TbPartInfoReFlowStatVo();
				vo.setTypeValue(new Long(obj[0].toString()));
				vo.setTypeName(StockTypeElements.getElementMap().get(vo.getTypeValue()));
				vo.setTotalQuantity(Double.valueOf(obj[1].toString()));
				results.add(vo);
			}
			
		}
		
		return results;
		
	}
	
	/**
	 * 配件流量及流向统计 -- 所有配件流量明细
	 * @Date      2010-7-9
	 * @Function  
	 * @param partInfoId
	 * @param elementType
	 * @return
	 */
	public List<TbPartInfoReFlowStatVo> getPartInfoReFlowDetailStat(List<TbPartInfo> tbPartInfoList, Long elementType,TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo ){
		List<TbPartInfoReFlowStatVo> results = new ArrayList<TbPartInfoReFlowStatVo>();
		
		List<Object[]> list = null;
		
		if(null != tbPartInfoList){
			String[] partIds = new String[tbPartInfoList.size()];
			for(int i=0; i<tbPartInfoList.size();i++){
				partIds[i] = tbPartInfoList.get(i).getId()+"";
			}
			list = tbPartInfoDao.getPartInfoReFlowDetailStat(StringUtils.join(partIds, ","), elementType, tbPartInfoReFlowStatVo);
		}else{
			list = tbPartInfoDao.getPartInfoReFlowDetailStat(null, elementType, tbPartInfoReFlowStatVo);

		}
		
		for(Object[] obj :list){
			
			TbPartInfoReFlowStatVo vo = new TbPartInfoReFlowStatVo();
			String dateStr = obj[0]!=null?obj[0].toString():null;
			Date date = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			vo.setCreateDate(date);
			vo.setElementType(obj[1]!=null?StockTypeElements.getElementMap().get(new Long(obj[1].toString())):null);
			Double partQuantity = obj[2]!=null? Double.valueOf(obj[2].toString()):null;
			Long typeVal = StockTypeElements.getElementTypeVal(Long.valueOf(obj[1].toString()));
			if(typeVal.equals(1L)){	
				//入库类型
				vo.setInQuantity(partQuantity);
			}
			if(typeVal.equals(2L)){
				//出库类型
				vo.setOutQuantity(partQuantity);
			}
			
			vo.setRatePrice(obj[3]!=null ? Double.valueOf(obj[3].toString()):null);
			vo.setSubTotalPrice(obj[4]!=null ?Double.valueOf(obj[4].toString()):null);
			vo.setCustomerName(obj[5]!=null ?obj[5].toString():null);
			Long tbCarInfoId = obj[7]!=null?Long.valueOf(obj[7].toString()):null;
			vo.setTbCarInfoId(tbCarInfoId);
			vo.setLicenseCode(obj[8]!=null ?obj[8].toString():null);
			vo.setChassisCode(obj[9]!=null ?obj[9].toString():null);
			results.add(vo);
		}
		
		return results;
	}
	
	/**
	 * 配件出库排行榜
	 * @Date      2010-7-12
	 * @Function  
	 * @param tbPartInfoStockOutVo
	 * @return
	 */
	public Map<TbPartInfoStockOutVo,List<TbPartInfoStockOutVo>> getToppartInfoStockOut(TbPartInfoStockOutVo tbPartInfoStockOutVo){
		Map<TbPartInfoStockOutVo,List<TbPartInfoStockOutVo>> map = new HashMap<TbPartInfoStockOutVo, List<TbPartInfoStockOutVo>>();
		List<Object[]> list = tbPartInfoDao.getToppartInfoStockOut(tbPartInfoStockOutVo);
		List<TbPartInfoStockOutVo> results = new ArrayList<TbPartInfoStockOutVo>();
		
		Double totalSellPrice = 0D;			//销售总金额
		Double totalGain = 0D;				//总利润
		Double totalStoreQuantity = 0D;		//库存总量
		Double totalCostPrice = 0D;			//库存成本
		
		for(Object[] obj :list){
			TbPartInfoStockOutVo vo = new TbPartInfoStockOutVo();
			vo.setPartName(obj[0]!= null ? obj[0].toString(): null);
			vo.setModelName(obj[1]!= null ? obj[1].toString(): null);
			vo.setStoreLocation(obj[2]!= null ? obj[2].toString(): null);
			vo.setCostPrice(obj[3]!= null ? Double.valueOf(obj[3].toString()): 0D);
			vo.setStoreQuantity(obj[4]!= null ?  Double.valueOf(obj[4].toString()): 0D);
			vo.setSellQuantity(obj[5]!= null ?  Double.valueOf(obj[5].toString()): 0D);
			vo.setSellPrice(obj[6]!= null ?  Double.valueOf(obj[6].toString()): 0D);
			vo.setCostXj(obj[7]!= null ?  Double.valueOf(obj[7].toString()): 0D);
			vo.setGain(obj[8]!= null ?  Double.valueOf(obj[8].toString()): 0D);
			
			totalSellPrice += vo.getSellPrice();
			totalGain += vo.getGain();
			totalStoreQuantity += vo.getStoreQuantity();
			totalCostPrice += vo.getCostPrice();
			results.add(vo);
		}
		
		TbPartInfoStockOutVo countVo = new TbPartInfoStockOutVo();
		countVo.setTotalSellPrice(CommonMethod.convertRadixPoint(totalSellPrice, 2));
		countVo.setTotalGain(CommonMethod.convertRadixPoint(totalGain, 2));
		countVo.setTotalStoreQuantity(CommonMethod.convertRadixPoint(totalStoreQuantity, 2));
		countVo.setTotalCostPrice(CommonMethod.convertRadixPoint(totalCostPrice, 2));
		
		
		map.put(countVo,results);
		return map;
	}
	
	/**
	 * 得到所有出库类型
	 * @return
	 */
	public Map<Long, String> getStockOutElementMap(){
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		map.put(StockTypeElements.MAINTAIN.getElementValue(), StockTypeElements.MAINTAIN.getElementName());
		map.put(StockTypeElements.SELLSTOCKOUT.getElementValue(), StockTypeElements.SELLSTOCKOUT.getElementName());
		map.put(StockTypeElements.DRAWSTOCKOUT.getElementValue(), StockTypeElements.DRAWSTOCKOUT.getElementName());
		map.put(StockTypeElements.ALLOTSTOCKOUT.getElementValue(), StockTypeElements.ALLOTSTOCKOUT.getElementName());
		map.put(StockTypeElements.SHATTERSTOCKOUT.getElementValue(), StockTypeElements.SHATTERSTOCKOUT.getElementName());
		
		return map;
	}
	
	/**
	 *  仓库期间收发统计
	 * @Date      2010-7-13
	 * @Function  
	 * @param tmStoreHouseReceiverStatVo
	 * @return
	 */
	public List<TmStoreHouseReceiverStatVo> getStoreHouseReceiverStat(TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo){
		
		List<TmStoreHouseReceiverStatVo> results = new ArrayList<TmStoreHouseReceiverStatVo>();
		
		List<Object[]> list = tbPartInfoDao.getStoreHouseReceiverStat(tmStoreHouseReceiverStatVo);
		
		for(Object[] obj :list){
			TmStoreHouseReceiverStatVo vo = new TmStoreHouseReceiverStatVo();
			vo.setStoreHouseName(obj[0]!=null?obj[0].toString():null);
			vo.setInOutType(obj[2]!=null?Long.valueOf(obj[2].toString()):null);
			vo.setInOutTypeName(obj[2]!=null?StockTypeElements.getElementMap().get(new Long(obj[2].toString())):null);
			Long type = Long.valueOf(obj[3].toString());
			if(type.equals(0L)){
				//类型为出库
				vo.setStockOutCostPrice(obj[4]!=null?Double.valueOf(obj[4].toString()):0D);
				vo.setSellCostPrice(obj[5]!=null?Double.valueOf(obj[5].toString()):0D);
			}
			if(type.equals(1L)){
				//类型为入库
				vo.setStockInCostPrice(obj[4]!=null?Double.valueOf(obj[4].toString()):0D);
			}
			
			results.add(vo);
		}
		
		return results;
	}
	/**
	 *  仓库月度收发存统计
	 * @Date      2010-7-13
	 * @Function  
	 * @param tmStoreHouseReceiverStatVo
	 * @return
	 */
	public List<TmStoreHouseReceiverStatVo> getStoreHouseReceiverStatByMonth(String year,String mounth){
		
		if(year==null || mounth==null) return null;
		
		List<TmStoreHouseReceiverStatVo> results = new ArrayList<TmStoreHouseReceiverStatVo>();
		
		List<Object[]> list = tbPartInfoDao.getStoreHouseReceiverStat(year,mounth);
		
		for(Object[] obj :list){
			TmStoreHouseReceiverStatVo vo = new TmStoreHouseReceiverStatVo();
			vo.setStoreHouseName(obj[0]!=null?obj[0].toString():null);
			vo.setInOutType(obj[2]!=null?Long.valueOf(obj[2].toString()):null);
			vo.setInOutTypeName(obj[2]!=null?StockTypeElements.getElementMap().get(new Long(obj[2].toString())):null);
			Long type = Long.valueOf(obj[3].toString());
			if(type.equals(0L)){
				//类型为出库
				vo.setStockOutCostPrice(obj[4]!=null?Double.valueOf(obj[4].toString()):0D);
				vo.setSellCostPrice(obj[5]!=null?Double.valueOf(obj[5].toString()):0D);
			}
			if(type.equals(1L)){
				//类型为入库
				vo.setStockInCostPrice(obj[4]!=null?Double.valueOf(obj[4].toString()):0D);
			}
			
			
			vo.setStoreHouseId(obj[1]!=null?new Long(obj[1].toString()):null);
			
			Double inBeginCount = tbPartInfoDao.getInTmStoreHouseAsBegin(vo.getStoreHouseId(), year, mounth,vo.getInOutType());
			Double inFinalCount = tbPartInfoDao.getInTmStoreHouseAsFinal(vo.getStoreHouseId(), year, mounth,vo.getInOutType());
			
			Double outBeginCount = tbPartInfoDao.getOutTmStoreHouseAsBegin(vo.getStoreHouseId(), year, mounth,vo.getInOutType());
			Double outFinalCount = tbPartInfoDao.getOutTmStoreHouseAsFinal(vo.getStoreHouseId(), year, mounth,vo.getInOutType());
			
			//期末
			Double incount = (inBeginCount - outBeginCount);
			//期初
			Double outcount = (inFinalCount - outFinalCount );
			
			vo.setQcPrice(CommonMethod.convertRadixPoint(incount,2));
			vo.setQmPrice(CommonMethod.convertRadixPoint(outcount,2));
			results.add(vo);
		}
		
		return results;
	}
	
	/**
	 * 配件领用统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public List<TmDrawStatVo> getDrawStockOutStat(TmDrawStatVo tmDrawStatVo){
		List<TmDrawStatVo> results = new ArrayList<TmDrawStatVo>();
		
		List<Object[]> list = tbPartInfoDao.getDrawStockOutStat(tmDrawStatVo);
		for(Object[] obj :list){
			TmDrawStatVo vo = new TmDrawStatVo();
			vo.setStockOutCode(obj[0]!=null?obj[0].toString():null);
			vo.setStockOutDate((obj[1]!=null?CommonMethod.parseStringToDate(obj[1].toString(), "yyyy-MM-dd"):null));
			vo.setHouseName(obj[2]!=null?obj[2].toString():null);
			vo.setPartCode(obj[3]!=null?obj[3].toString():null);
			vo.setPartName(obj[4]!=null?obj[4].toString():null);
			vo.setUnitName(obj[5]!=null?obj[5].toString():null);
			vo.setCostPrice(obj[6]!=null?Double.valueOf(obj[6].toString()):null);
			vo.setSellPrice(obj[7]!=null?Double.valueOf(obj[7].toString()):null);
			vo.setQuantity(obj[8]!=null?Double.valueOf(obj[8].toString()):null);
			vo.setDrawPeopleName(obj[9]!=null?obj[9].toString():null);
			vo.setDeptName(obj[10]!=null?obj[10].toString():null);
			results.add(vo);
		}
		return results;
	}
	
	/**
	 * 配件领用人分组统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public Map<TmDrawStatVo, List<TmDrawStatVo>> getGroupDrawStockOutStat(TmDrawStatVo tmDrawStatVo){
		Map<TmDrawStatVo, List<TmDrawStatVo>> map = new HashMap<TmDrawStatVo, List<TmDrawStatVo>>();
		List<TmDrawStatVo> results = new ArrayList<TmDrawStatVo>();
		TmDrawStatVo coutRes = new TmDrawStatVo();
		Double totalCostPrice = 0D;
		Double totalSellPrice = 0D;
		
		List<Object[]> list = tbPartInfoDao.getGroupDrawStockOutStat(tmDrawStatVo);
		for(Object[] obj :list){
			TmDrawStatVo vo = new TmDrawStatVo();
			vo.setDeptName(obj[0]!=null?obj[0].toString():null);
			vo.setDrawPeopleName(obj[1]!=null?obj[1].toString():null);
			vo.setCostPrice(obj[2]!=null?Double.valueOf(obj[2].toString()):null);
			vo.setSellPrice(obj[3]!=null?Double.valueOf(obj[3].toString()):null);
			
			totalCostPrice += vo.getCostPrice();
			totalSellPrice += vo.getSellPrice();
			
			results.add(vo);
		}
		coutRes.setTotalCostPrice(CommonMethod.convertRadixPoint(totalCostPrice, 2));
		coutRes.setTotalSellPrice(CommonMethod.convertRadixPoint(totalSellPrice, 2));
		map.put(coutRes, results);
		return map;
	}
	
	
	/**
	 * 配件每日出库查询
	 * @Date      2010-7-21
	 * @Function  
	 * @param dailyStockOutVo
	 * @return
	 */
	public Map<DailyStockOutVo, List<DailyStockOutVo>> getDailyStockOutStat(DailyStockOutVo dailyStockOutVo){
		
		Map<DailyStockOutVo, List<DailyStockOutVo>> map = new HashMap<DailyStockOutVo, List<DailyStockOutVo>>();
		
		List<Object[]> list = tbPartInfoDao.getDailyStockOutStat(dailyStockOutVo);
		
		List<DailyStockOutVo> dailyStockOutVoList = new ArrayList<DailyStockOutVo>();
		
		Double totalQuantity = 0D;
		Double totalSellPrice = 0D;
		Double totalCostPrice = 0D;
		Set set = new HashSet();
		
		
		for(Object[] obj :list){
			DailyStockOutVo vo = new DailyStockOutVo();
			
			vo.setHouseName(obj[0]!=null?obj[0].toString():null);
			vo.setPartCode(obj[1]!=null?obj[1].toString():null);
			vo.setPartName(obj[2]!=null?obj[2].toString():null);
			vo.setStockOutCode(obj[3]!=null?obj[3].toString():null);
			vo.setOutType(obj[4]!=null?Long.valueOf(obj[4].toString()):null);
			vo.setQuantity(obj[5]!=null?Double.valueOf(obj[5].toString()):0D);
			vo.setCostPrice(obj[6]!=null?Double.valueOf(obj[6].toString()):0D);
			vo.setSellPrice(obj[7]!=null?Double.valueOf(obj[7].toString()):0D);
			String dateStr = obj[8]!=null?obj[8].toString():null;
			Date date = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			vo.setStockOutDate(date);
			vo.setIsFree(obj[9]!=null?Long.valueOf(obj[9].toString()):null);
			vo.setPartInfoId(obj[10]!=null?Long.valueOf(obj[10].toString()):null);
			
			Long createUserId = obj[11]!=null?Long.valueOf(obj[11].toString()):null;
			Long drowUserId = obj[12]!=null?Long.valueOf(obj[12].toString()):null;
			vo.setCreateUserName(createUserId != null ? tmUserService.findById(createUserId).getUserRealName() :null);
			vo.setDrowUserName(drowUserId != null ? tmUserService.findById(drowUserId).getUserRealName() :null);
			vo.setServiceUserName(obj[13]!=null ? obj[13].toString():null);
			dailyStockOutVoList.add(vo);
			
			totalQuantity += vo.getQuantity();
			totalCostPrice += vo.getCostPrice()*vo.getQuantity();
			if(vo.getIsFree() == null || vo.getIsFree().equals(Constants.FREESYMBOL_NO))
				totalSellPrice += vo.getSellPrice()*vo.getQuantity();
			set.add(vo.getPartInfoId().longValue());
			
		}
		
		DailyStockOutVo countVo = new DailyStockOutVo();
		countVo.setTotalQuantity(totalQuantity);
		countVo.setTotalCostPrice(CommonMethod.convertRadixPoint(totalCostPrice, 2));
		countVo.setTotalSellPrice(CommonMethod.convertRadixPoint(totalSellPrice,2));
		countVo.setPartCategory(new Long(set.size()));
		
		map.put(countVo, dailyStockOutVoList);
		return map;
	}
	
	
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List<BalanceFixSellVo> getNotBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo){
		List<BalanceFixSellVo> results = new ArrayList<BalanceFixSellVo>();
		
		List<Object[]> list = tbPartInfoDao.getNotBalanceFixSellCostDetail(balanceFixSellVo);
		
		for(Object[] obj :list){
			BalanceFixSellVo vo = new BalanceFixSellVo();
			vo.setEntrustId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
			vo.setEntrustCode(obj[1]!=null?obj[1].toString():null);
			String dateStr = obj[2]!=null?obj[2].toString():null;
			Date fixDate = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			vo.setFixDate(fixDate);
			vo.setMaintainCode(obj[3]!=null?obj[3].toString():null);
			vo.setStockOutCode(obj[4]!=null?obj[4].toString():null);
			vo.setLianceseCode(obj[5]!=null?obj[5].toString():null);
			vo.setFixType(obj[6]!=null?obj[6].toString():null);
			vo.setCustomerName(obj[7]!=null?obj[7].toString():null);
			vo.setUserRealName(obj[8]!=null?obj[8].toString():null);
			vo.setCostXj(obj[9]!=null?Double.valueOf(obj[9].toString()):0D);
			vo.setFixCostPrice(obj[10]!=null?Double.valueOf(obj[10].toString()):0D);
			vo.setSellCostPrice(obj[11]!=null?Double.valueOf(obj[11].toString()):0D);
			vo.setNoPrice(obj[12]!=null?Double.valueOf(obj[12].toString()):0D);
			vo.setSwPrice(obj[13]!=null?Double.valueOf(obj[13].toString()):0D);
			vo.setSpPrice(obj[14]!=null?Double.valueOf(obj[14].toString()):0D);
			vo.setFgPrice(obj[15]!=null?Double.valueOf(obj[15].toString()):0D);
			vo.setFwPrice(obj[16]!=null?Double.valueOf(obj[16].toString()):0D);
			
			results.add(vo);
		}
		
		return results;
	}
	
	
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List<BalanceFixSellVo> getIsBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo){
		List<BalanceFixSellVo> results = new ArrayList<BalanceFixSellVo>();
		
		List<Object[]> list = tbPartInfoDao.getIsBalanceFixSellCostDetail(balanceFixSellVo);
		
		for(Object[] obj :list){
			BalanceFixSellVo vo = new BalanceFixSellVo();
			vo.setEntrustId(obj[0]!=null?Long.valueOf(obj[0].toString()):null);
			vo.setEntrustCode(obj[1]!=null?obj[1].toString():null);
			String dateStr = obj[2]!=null?obj[2].toString():null;
			Date fixDate = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			vo.setFixDate(fixDate);
			vo.setMaintainCode(obj[3]!=null?obj[3].toString():null);
			vo.setStockOutCode(obj[4]!=null?obj[4].toString():null);
			vo.setLianceseCode(obj[5]!=null?obj[5].toString():null);
			vo.setFixType(obj[6]!=null?obj[6].toString():null);
			vo.setCustomerName(obj[7]!=null?obj[7].toString():null);
			vo.setUserRealName(obj[8]!=null?obj[8].toString():null);
			vo.setCostXj(obj[9]!=null?Double.valueOf(obj[9].toString()):0D);
			vo.setFixCostPrice(obj[10]!=null?Double.valueOf(obj[10].toString()):0D);
			vo.setSellCostPrice(obj[11]!=null?Double.valueOf(obj[11].toString()):0D);
			vo.setNoPrice(obj[12]!=null?Double.valueOf(obj[12].toString()):0D);
			vo.setSwPrice(obj[13]!=null?Double.valueOf(obj[13].toString()):0D);
			vo.setSpPrice(obj[14]!=null?Double.valueOf(obj[14].toString()):0D);
			vo.setFgPrice(obj[15]!=null?Double.valueOf(obj[15].toString()):0D);
			vo.setFwPrice(obj[16]!=null?Double.valueOf(obj[16].toString()):0D);
			Date bananceDate = CommonMethod.parseStringToDate(obj[17]!=null?obj[17].toString():null, "yyyy-MM-dd"); 
			vo.setBananceDate(bananceDate);
			vo.setBalanceCode(obj[18]!=null?obj[18].toString():null);
			results.add(vo);
		}
		
		return results;
	}
	
	
	
	public Double sumStockDetailByEntrustId(Long entrustId){
		
		Double result = 0D;
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
		
		
		result = tmStockOutDao.sumStockoutDetailByEntrustCode(tbFixEntrust.getEntrustCode());
			
		result += tbMaintainPartContentDao.sumMaintainByEntrustId(entrustId);
			
		return result;
		
	}
	
	/**
	 * 根据结算ID得到修理费
	 * @param balanceId
	 * @return
	 */
	public Double getMaintainPriceByBalanceId(Long balanceId){
		Double result = 0D;
		//维护发料修理费
		result += tbMaintainPartContentDao.sumMaintainPriceByBalanceId(balanceId);
		//销售修理费
		result += tmStockOutDao.sumStockoutByBalanceId(balanceId);
		return result;
	}
	
	/**
	 * 根据结算ID得到销售费
	 * @param balanceId
	 * @return
	 */
	public Double getStockPriceByBalanceId(Long balanceId){
		Double result = 0D;
		//销售修理费
		result += tmStockOutDao.sumStockoutByBalanceId(balanceId);
		return result;

	}
	
	/**
	 * 根据结算ID得到材料成本费
	 * @param balanceId
	 * @return
	 */
	public Double getMaintainCostPriceByBalanceId(Long balanceId){
		Double result = 0D;
		//维修发料成本费
		result += tbMaintainPartContentDao.sumMaintainCostPriceByBalanceId(balanceId);
		return result;
	}
}
