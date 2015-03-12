package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
@Service("tmStockOutService")
public class TmStockOutSerivceImpl implements ITmStockOutService {

	@Autowired
	private ITmStockOutDao tmStockOutDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;
	@Autowired
	private ITmStockoutDetailService tmStockoutDetailService;
	
	public void insert(TmStockOut tmStockOut){
		tmStockOutDao.insert(tmStockOut);
	}
	
	public TmStockOut findById(Long id){
		return tmStockOutDao.findById(id);
	}
	
	public void update(TmStockOut tmStockOut){
		tmStockOutDao.update(tmStockOut);
	}
	
	
	public List<TmStockOut> findByEntity(TmStockOut tmStockOut) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmStockOut.class);
		if(null!=tmStockOut){
			if(null!=tmStockOut.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmStockOut.getId()));
			}
			if(null!=tmStockOut.getStockOutCode()){
				detachedCriteria.add(Restrictions.like("stockOutCode","%"+ tmStockOut.getStockOutCode()+"%"));
			}
		
			if(null!=tmStockOut.getOutType()){
				detachedCriteria.add(Restrictions.eq("outType",tmStockOut.getOutType()));
			}
			if(null!=tmStockOut.getIsConfirm()){
				detachedCriteria.add(Restrictions.eq("isConfirm",tmStockOut.getIsConfirm()));
			}
			if(null!=tmStockOut.getTrustBill()&&!"".equals(tmStockOut.getTrustBill())){
				detachedCriteria.add(Restrictions.eq("trustBill",tmStockOut.getTrustBill()));
			}
		} 
		return tmStockOutDao.findByCriteria(detachedCriteria, tmStockOut);
	}
	
	
	public List<TmStockOutDetVo>  getStockOutDetVos(Long isConfirm ,Long stockOutId,Long stockOutType){
		String isConfirms = Constants.CONFIRM+","+Constants.RE_BALANCE+","+Constants.FINSH_BALANCE;
		 List<TmStockOutDetVo> tmstockoutList = tmStockOutDao.getStockOutDetVos(isConfirms,stockOutId,stockOutType);
		 for(TmStockOutDetVo tmStockOutDetVo :tmstockoutList ){
			 TbPartInfo tbPartInfo = tbPartInfoService.findById(tmStockOutDetVo.getPartinfoId());
			 if(tbPartInfo.getTmCarModelType()!= null)
				 tmStockOutDetVo.setModeName(tbPartInfo.getTmCarModelType().getModelName());
		 }
		 return tmstockoutList;
	}
	
	public List<TmStockOutDetVo>  getDrawStockOutDetVos(Long isConfirm ,Long stockOutId,Long stockOutType){
		String isConfirms = isConfirm+"";
		 List<TmStockOutDetVo> tmstockoutList = tmStockOutDao.getStockOutDetVos(isConfirms,stockOutId,stockOutType);
		 for(TmStockOutDetVo tmStockOutDetVo :tmstockoutList ){
			 TbPartInfo tbPartInfo = tbPartInfoService.findById(tmStockOutDetVo.getPartinfoId());
			 if(tbPartInfo.getTmCarModelType()!= null)
				 tmStockOutDetVo.setModeName(tbPartInfo.getTmCarModelType().getModelName());
		 }
		 return tmstockoutList;
	}
	
	public List<TmStockOutDetVo> getStockOutDetVos(TmStockOut tmStockOut ,Long stockOutId, Long stockOutType){
		 List<TmStockOutDetVo> tmstockoutList = tmStockOutDao.getStockOutDetVos(tmStockOut,stockOutId,stockOutType);
		 return tmstockoutList;
	}
	
	public List<TmStockOutVo> getStockOutVos(String isConfirm , Long stockOutType,TmStockOut tmStockOut,Long stockOutId){
		String isconfirms = isConfirm != null ? isConfirm+"" : null;
		return tmStockOutDao.getStockOutVos(isconfirms, stockOutType,tmStockOut,stockOutId);
	}
	
	/**
	 * 删除所有出库类型单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteStockOut(Long stockOutId){
		TmStockOut tmStockOut = tmStockOutDao.findById(stockOutId);
		if(null != tmStockOut && tmStockOut.getIsConfirm().equals(Constants.NOT_CONFIRM)){
			//删除出库明细表
			tmStockOutDao.deleteStockOutDetail(stockOutId);
			//删除出库主表
			boolean flag = tmStockOutDao.deleteById(stockOutId);
			
			return flag;
		}
		
		
		return false;
	}
	
	/**
	 * 根据委托书号得到销售明细列表
	 * @Date      2010-6-24
	 * @Function  
	 * @param isConfirm
	 * @param stockOutType
	 * @param trustBill
	 * @return
	 */
	//已做修改  不需要状态  直接查委托书下销售单的明细
	public List<TmStockOutDetVo> getSellByEntrustCode(String trustBill){
		//String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		return tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), trustBill , null,null,null,null);
	}
	
	/**
	 * 根据客户ID得到销售主表 （单独销售）
	 * @Date      2010-6-24
	 * @Function  
	 * @param isConfirm
	 * @param stockOutType
	 * @param trustBill
	 * @return
	 */
	public List<TmStockOutVo> getCustomerSell(){
		String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		TmStockOut queryEntity = new TmStockOut();
		queryEntity.setSellType(Constants.SELLCUSTOMER);
		return tmStockOutDao.getStockOutVos(confirms, StockTypeElements.SELLSTOCKOUT.getElementValue(),queryEntity,null);
	}
	/**
	 * 根据委托书号得到所有销售单总金额
	 * @Date      2010-6-24
	 * @Function  
	 * @param isConfirm
	 * @param trustBill
	 * @return
	 */
	public Double getTotalPriceByEntrustCode(String trustBill){
		Double result = 0D;
		String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		List<TmStockOutDetVo> voList = tmStockOutDao.getStockOutDetVosBySell(confirms, StockTypeElements.SELLSTOCKOUT.getElementValue(), trustBill , null,null,null,null);
		
		for(TmStockOutDetVo vo : voList){
			result += vo.getPrice()* vo.getQuantity();
		}
		

		BigDecimal   b   =   new   BigDecimal(result);  
		result   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		return result;
	}
	
	public List<TmStockOutVo> getAllTypeStockOutDetVo(String isConfirms, Long stockOutType,TmStockOut tmStockOut){
		
		return tmStockOutDao.getStockOutVos(isConfirms, stockOutType,tmStockOut,null);
	}
	
	/**
	 * 更加销售单ID更新销售单状态
	 */
	public void updateSellStatus(Long id ,Long status){
		TmStockOut tmStockOut = tmStockOutDao.findById(id);
		tmStockOut.setIsConfirm(status);
		tmStockOutDao.update(tmStockOut);
	}
	
	
	public void updateSellStatusNotBalance(Long id ,Long status){
		TmStockOut tmStockOut = tmStockOutDao.findById(id);
		tmStockOut.setIsConfirm(status);
		tmStockOutDao.update(tmStockOut);
		tmStockoutDetailService.updateTmStockoutDetailNotBalance(tmStockOut.getId());
	}
	
	/**
	 * 查找出委托书号所对应的销售单，更新此销售单的状态
	 * @param trustBill
	 * @param status
	 */
	public void updateTrustBill(String trustBill, Long status){
		TmStockOut queryEntity = new TmStockOut();
		queryEntity.setTrustBill(trustBill);
		List<TmStockOut> stockoutList = this.findByEntity(queryEntity);
		TmStockOut updateEntity = null;
		if(stockoutList != null && stockoutList.size() > 0){
			updateEntity = stockoutList.get(0);
			updateEntity.setIsConfirm(status);
			tmStockOutDao.update(updateEntity);
		}
	}
	
	public void updateTrustBillNotBalance(String trustBill, Long status){
		TmStockOut queryEntity = new TmStockOut();
		queryEntity.setTrustBill(trustBill);
		List<TmStockOut> stockoutList = this.findByEntity(queryEntity);
		TmStockOut updateEntity = null;
		if(stockoutList != null && stockoutList.size() > 0){
			updateEntity = stockoutList.get(0);
			updateEntity.setIsConfirm(status);
			tmStockOutDao.update(updateEntity);
			tmStockoutDetailService.updateTmStockoutDetailNotBalance(updateEntity.getId());
		}
	}
	
	/**
	 * 根据委托书号 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return
	 */
	public List<TmStockOutDetVo> getSellDetailByEntrustCode(String entrustCode,Long balanceType){
		List<TmStockOutDetVo> result = tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), entrustCode, null,null,null,balanceType);
		return result;
	}
	/**
	 * 根据单独销售ID 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return 
	 */
	public List<TmStockOutDetVo> getCustomerSellDetailByTmStockOutId(Long tmStockOutId,Long balanceType){
		List<TmStockOutDetVo> result = tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), null, null,null,tmStockOutId,balanceType);
		return result;
	}
	
	/**
	 * 根据委托书号，结算ID 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return
	 */
	public List<TmStockOutDetVo> getSellDetailByBalanceId(String entrustCode ,Long balanceId){
		//String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		List<TmStockOutDetVo> result = tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), entrustCode, null,balanceId,null,null);
		return result;
		
	}
	/**
	 * 根据单独销售ID，结算ID 查询明细中LIST
	 * @param entrustId
	 * @param balanceId
	 * @return 
	 */
	public List<TmStockOutDetVo> getCustomerSellDetailByBalanceId(Long tmStockOutId,Long balanceId){
		//EDIT BY CCR  查询已经结算的不需要状态
		//String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		List<TmStockOutDetVo> result = tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), null, null,balanceId,tmStockOutId,null);
		return result;
	}
	
	/**
	 * 根据委托书号，结算ID 返回明细记录的总金额
	 * @Date      2010-6-29
	 * @Function  
	 * @param maintainCode
	 * @param balanceId
	 * @return
	 */
	public Double getSellTotalPriceByBalance(String entrustCode ,Long balanceId){
		Double result = 0D ;
		//List<TmStockOutDetVo> detailVos = this.getSellDetailByBalanceId(entrustCode, balanceId);
		
		List<TmStockOutDetVo> detailVos = tmStockOutDao.getStockOutDetVosBySell(null, StockTypeElements.SELLSTOCKOUT.getElementValue(), entrustCode, null,balanceId,null,Constants.BALANCE_ISNULL);

		for(TmStockOutDetVo vo : detailVos){
			if(vo.getIsFree().equals(Constants.FREESYMBOL_NO)){
				
				result += vo.getPrice() * vo.getQuantity();
			}
		}
		
		BigDecimal   b   =   new   BigDecimal(result);  
		result   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return result;
	}
	/**
	 * 根据单独销售ID，结算ID 返回明细记录的总金额
	 * @Date      2010-6-29
	 * @Function  
	 * @param maintainCode
	 * @param balanceId
	 * @return
	 */
	public Double getCustomerSellTotalPriceByBalance(Long tmStockOutId ,Long balanceId){
		Double result = 0D ;
		List<TmStockOutDetVo> detailVos = this.getCustomerSellDetailByBalanceId(tmStockOutId, balanceId);
		for(TmStockOutDetVo vo : detailVos){
			if(vo.getIsFree().equals(Constants.FREESYMBOL_NO)){
				
				result += vo.getPrice() * vo.getQuantity();
			}
		}
		
		BigDecimal   b   =   new   BigDecimal(result);  
		result   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return result;
	}
	
	
	/**
	 * 根据委托书号 来更新明细中BALANCEID为NULL的为参数BALANCEID
	 * @Date      2010-6-29
	 * @Function  
	 * @param entrustCode
	 * @param balance
	 */
	public void updateSellBalance(String entrustCode, Long balanceId){
		
		//String confirms = Constants.RE_BALANCE + "," + Constants.CONFIRM;
		TmStockOut queryEntity = new TmStockOut();
		queryEntity.setSellType( Constants.SELLENTRUST);
		queryEntity.setTrustBill(entrustCode);
		List<TmStockOutVo> tmStockOuts = tmStockOutDao.getStockOutVos(null,  StockTypeElements.SELLSTOCKOUT.getElementValue(), queryEntity,null);
		
		if(null!=tmStockOuts&&tmStockOuts.size()>0){
			
			Long stockoutId = tmStockOuts.get(0).getStockOutId();
			
			
			tmStockOutDao.updateSellBalanceId(stockoutId, balanceId);
		}
		
		
	}
	
	/**
	 * 根据单独销售ID 来更新明细中BALANCEID为NULL的为参数BALANCEID
	 * @Date      2010-6-29
	 * @Function  
	 * @param entrustCode
	 * @param balance
	 */
	public void updateCustomerSellBalance(Long tmStockOutId, Long balanceId){

		tmStockOutDao.updateSellBalanceId(tmStockOutId, balanceId);
	}
	/**
	 * 得到所有免费标志类型
	 * @Date      2010-7-28
	 * @Function  
	 * @return
	 */
	public List<String> getAllFreeFlagType(){
		List<String> result = new ArrayList<String>();
		result.add(Constants.FREESYMBOL_NO_SHOW);
		result.add(Constants.FREESYMBOL_SB_SHOW);
		result.add(Constants.FREESYMBOL_SP_SHOW);
		result.add(Constants.FREESYMBOL_FG_SHOW);
		result.add(Constants.FREESYMBOL_FW_SHOW);
		return result;
	}
	
	/**
	 * 得到所有免费标志类型
	 * @Date      2010-7-28
	 * @Function  
	 * @return
	 */
	public Map<Long,String> getAllFreeFlagMapType(){
		Map<Long,String> result = new LinkedHashMap<Long, String>();
		result.put(Constants.FREESYMBOL_NO,Constants.FREESYMBOL_NO_SHOW);
		result.put(Constants.FREESYMBOL_SB,Constants.FREESYMBOL_SB_SHOW);
		result.put(Constants.FREESYMBOL_SP,Constants.FREESYMBOL_SP_SHOW);
		result.put(Constants.FREESYMBOL_FG,Constants.FREESYMBOL_FG_SHOW);
		result.put(Constants.FREESYMBOL_FW,Constants.FREESYMBOL_FW_SHOW);
		return result;
	}
	
	
	
	
	/**
	 * 得到修理销售，单独销售结算清单
	 * @Date      2010-8-6
	 * @Function  
	 * @param beginDate
	 * @param endDate
	 * @param carModelType
	 * @return
	 */
	public BalanceSellCountVo getBalanceSellCountVo(String beginDate,String endDate,Long carModelType){
		BalanceSellCountVo result = new BalanceSellCountVo();
		result.setBeginDate(beginDate);
		result.setEndDate(endDate);
		result.setCarModelType(carModelType);
		//未结算修理销售-销售出库
		Long fixSellNoBalanceCount =  tmStockOutDao.getBalanceSellCount(Constants.BALANCEFIX, Constants.BALANCE_ISNULL,result);
		//已结算修理销售-销售出库
		Long fixSellIsBalanceCount =  tmStockOutDao.getBalanceSellCount(Constants.BALANCEFIX, Constants.BALANCE_NOTNULL,result);
		//未结算修理销售-维修发料
		Long maintainFixSellNoBalanceCount =  tmStockOutDao.getMaintainBalanceSellCount(Constants.BALANCE_ISNULL);
		//已结算修理销售-维修发料
		Long maintainFixSellIsBalanceCount =  tmStockOutDao.getMaintainBalanceSellCount(Constants.BALANCE_NOTNULL);
		
		
		Long fixSellAllCount = fixSellNoBalanceCount + fixSellIsBalanceCount+maintainFixSellNoBalanceCount+maintainFixSellIsBalanceCount;
		//未结算单独销售
		Long aloneSellNoBalanceCount =  tmStockOutDao.getBalanceSellCount(Constants.BALANCEALONE, Constants.BALANCE_ISNULL,result);
		//已结算单独销售
		Long aloneSellIsBalanceCount =  tmStockOutDao.getBalanceSellCount(Constants.BALANCEALONE, Constants.BALANCE_NOTNULL,result);
		
		Long aloneSellAllCount = aloneSellNoBalanceCount + aloneSellIsBalanceCount;
		
		
		result.setAloneSellAllCount(aloneSellAllCount);
		result.setAloneSellIsBalanceCount(aloneSellIsBalanceCount);
		result.setAloneSellNoBalanceCount(aloneSellNoBalanceCount);
		result.setFixSellAllCount(fixSellAllCount);
		result.setFixSellIsBalanceCount(fixSellIsBalanceCount);
		result.setFixSellNoBalanceCount(fixSellNoBalanceCount);
		return result;
	}
	
	
	public List<TmStockOutDetVo>  getStockOutDetVos(TmStockIn tmStockIn){
		 List<TmStockOutDetVo> tmstockoutList = tmStockOutDao.getStockOutDetVos(tmStockIn);
		 for(TmStockOutDetVo tmStockOutDetVo :tmstockoutList ){
			 TbPartInfo tbPartInfo = tbPartInfoService.findById(tmStockOutDetVo.getPartinfoId());
			 if(tbPartInfo.getTmCarModelType()!= null)
				 tmStockOutDetVo.setModeName(tbPartInfo.getTmCarModelType().getModelName());
			 if(tmStockOutDetVo.getStockInDetailId() != null){
//				 TmStockinDetail tsid = tmStockinDetailService.findById(tmStockOutDetVo.getStockInDetailId());
//				 TmStockIn sid = tmStockInService.findById(tsid.getStockId());
				 TbCustomer tbCustomer = tbCustomerService.findTbCustomerByStockInDetailId(tmStockOutDetVo.getStockInDetailId());
				 tmStockOutDetVo.setCustomerName(tbCustomer.getCustomerName());
				 tmStockOutDetVo.setCustomerId(tbCustomer.getId());
				 tmStockOutDetVo.setCustomerCode(tbCustomer.getCustomerCode());
			 }
		 }
		 return tmstockoutList;
	}
	
	public List<TmStockOutVo>  getStockOutVos(TmStockIn tmStockIn){
		 List<TmStockOutVo> tmstockoutList = tmStockOutDao.getStockOutVos(tmStockIn);
		return tmstockoutList;
	}
	
	
	public Double sumSingleSellByBanalceCode(String balanceCode){
		List<TbBusinessBalance> busBalanceList= tbBusinessBalanceService.findTbBusinessBalanceByBalanceCode(balanceCode);
		if(busBalanceList!=null && busBalanceList.size()>0){
			Long balanceId = busBalanceList.get(0).getId();
			Double result = tmStockOutDao.sumSingleSellByBanalce(balanceId);
			return result;
		}
		
		return 0D;
	}
}
