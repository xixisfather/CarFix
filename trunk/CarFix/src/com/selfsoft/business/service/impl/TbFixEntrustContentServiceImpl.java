package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.business.dao.ITbFixEntrustContentDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustContent;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.framework.common.Constants;

@Service("tbFixEntrustContentService")
public class TbFixEntrustContentServiceImpl implements ITbFixEntrustContentService{

	@Autowired
	private ITbFixEntrustContentDao tbFixEntrustContentDao;
	
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbFixEntrustContentDao.deleteById(id);
	}

	public List<TbFixEntrustContent> findAll() {
		// TODO Auto-generated method stub
		return tbFixEntrustContentDao.findAll();
	}

	public TbFixEntrustContent findById(Long id) {
		// TODO Auto-generated method stub
		return tbFixEntrustContentDao.findById(id);
	}

	public void insert(TbFixEntrustContent tbFixEntrustContent) {
		// TODO Auto-generated method stub
		tbFixEntrustContentDao.insert(tbFixEntrustContent);
	}

	public void update(TbFixEntrustContent tbFixEntrustContent) {
		// TODO Auto-generated method stub
		tbFixEntrustContentDao.update(tbFixEntrustContent);
	}
	
	public void updateTbFixEntrustContentUnBalance(Long tbFixEntrustId) {
		
		List<TbFixEntrustContent> tbFixEntrustContentList = this.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);
		
		if(null != tbFixEntrustContentList && tbFixEntrustContentList.size() > 0) {
			
			for(TbFixEntrustContent tt : tbFixEntrustContentList) {
				
				tt.setBalanceId(null);
				
				this.update(tt);
				
			}
			
		}
		
	}
	
	//根据委托书ID查找修理内容LIST
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustId(Long tbFixEntrustId){
		
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentDao.findBySQL("SELECT tbFixEntrustContent from TbFixEntrustContent tbFixEntrustContent inner join tbFixEntrustContent.tbFixEntrust where tbFixEntrustContent.tbFixEntrust.id=?", new Object[]{tbFixEntrustId});
		
		List<TbFixEntrustContent> tbFixEntrustContentListReturn = null;
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			tbFixEntrustContentListReturn = new ArrayList<TbFixEntrustContent>();
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(tbFixEntrustContent.getTbWorkingInfo().getId());
				
				tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);
				
				tbFixEntrustContentListReturn.add(tbFixEntrustContent);
			}
		}
		
		return tbFixEntrustContentListReturn;
	}
	
	//根据委托书ID计算工时总价
	public Double countTbFixEnTrustContentByTbFixEntrustId(Long tbFixEntrustId){
		
		Double countAll = 0D;
		
		List<TbFixEntrustContent> tbFixEntrustContentList = this.findTbFixEnTrustContentListByTbFixEntrustId(tbFixEntrustId);
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			
			BigDecimal sumCount = new BigDecimal("0");
			
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				if(Constants.FREESYMBOL_NO.equals(tbFixEntrustContent.getFreesymbol())){
					
					sumCount = sumCount.add(new BigDecimal(String.valueOf(tbFixEntrustContent.getFixHourAll())));
					
				}
				
				
			}
			
			countAll = sumCount.doubleValue();
		}
		
		return countAll;
		
	}
	
	//根据委托书查找BALANCEID为NULL的修理内容
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceIdIsNull(Long tbFixEntrustId){
		
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentDao.findBySQL("SELECT tbFixEntrustContent from TbFixEntrustContent tbFixEntrustContent inner join tbFixEntrustContent.tbFixEntrust where tbFixEntrustContent.tbFixEntrust.id=? and tbFixEntrustContent.balanceId is null", new Object[]{tbFixEntrustId});
		
		List<TbFixEntrustContent> tbFixEntrustContentListReturn = null;
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			tbFixEntrustContentListReturn = new ArrayList<TbFixEntrustContent>();
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(tbFixEntrustContent.getTbWorkingInfo().getId());
				
				tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);
				
				tbFixEntrustContentListReturn.add(tbFixEntrustContent);
			}
		}
		
		return tbFixEntrustContentListReturn;
	}
	
	//根据委托书ID计算BALANCEID为NULL的工时总价
	public Double countTbFixEnTrustContentByTbFixEntrustIdAndBalanceIdIsNull(Long tbFixEntrustId){
		
		Double countAll = 0D;
		
		List<TbFixEntrustContent> tbFixEntrustContentList = this.findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceIdIsNull(tbFixEntrustId);
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			
			BigDecimal sumCount = new BigDecimal("0");
			
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				if(Constants.FREESYMBOL_NO.equals(tbFixEntrustContent.getFreesymbol())){
					
					sumCount = sumCount.add(new BigDecimal(String.valueOf(tbFixEntrustContent.getFixHourAll())));
					
				}
				
				
			}
			
			countAll = sumCount.doubleValue();
		}
		
		return countAll;
		
	}
	
	
	//根据委托书ID更新修理内容的BALANCE_ID
	public void updateTbFixEnTrustContentBalanceId(Long tbFixEntrustId,Long balanceId){
		
		List<TbFixEntrustContent> tbFixEntrustContentList = this.findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceIdIsNull(tbFixEntrustId);
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				tbFixEntrustContent.setBalanceId(balanceId);
				
				this.update(tbFixEntrustContent);
				
			}
			
		}
	}
	
	//根据委托书和BALANCEID来查找修理内容
	public List<TbFixEntrustContent> findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceId(Long tbFixEntrustId,Long balanceId){
		
		List<TbFixEntrustContent> tbFixEntrustContentList = tbFixEntrustContentDao.findBySQL("SELECT tbFixEntrustContent from TbFixEntrustContent tbFixEntrustContent inner join tbFixEntrustContent.tbFixEntrust where tbFixEntrustContent.tbFixEntrust.id=? and tbFixEntrustContent.balanceId=?", new Object[]{tbFixEntrustId,balanceId});
		
		List<TbFixEntrustContent> tbFixEntrustContentListReturn = null;
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			tbFixEntrustContentListReturn = new ArrayList<TbFixEntrustContent>();
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(tbFixEntrustContent.getTbWorkingInfo().getId());
				
				tbFixEntrustContent.setTbWorkingInfo(tbWorkingInfo);
				
				tbFixEntrustContentListReturn.add(tbFixEntrustContent);
			}
		}
		
		return tbFixEntrustContentListReturn;
	}
	//根据委托书ID和BALANCEID来计算总金额
	public Double countTbFixEnTrustContentByTbFixEntrustIdAndBalanceId(Long tbFixEntrustId,Long balanceId){
		
		Double countAll = 0D;
		
		List<TbFixEntrustContent> tbFixEntrustContentList = this.findTbFixEnTrustContentListByTbFixEntrustIdAndBalanceId(tbFixEntrustId,balanceId);
		
		if(null!=tbFixEntrustContentList&&tbFixEntrustContentList.size()>0){
			
			BigDecimal sumCount = new BigDecimal("0");
			
			for(TbFixEntrustContent tbFixEntrustContent : tbFixEntrustContentList){
				
				if(Constants.FREESYMBOL_NO.equals(tbFixEntrustContent.getFreesymbol())){
					
					sumCount = sumCount.add(new BigDecimal(String.valueOf(tbFixEntrustContent.getFixHourAll())));
					
				}
				
				
			}
			
			countAll = sumCount.doubleValue();
		}
		
		return countAll;
		
	}
	
	/**
	 * 
	 * @param entrustStatus 2 已完成 3 已结算
	 */
	public Double staticsTbFixEntrustContent(Long entrustStatus,TbFixEntrust tbFixEntrust){
		
		String sql = "select sum(fix_hour) from tb_fix_entrust_content a,tb_fix_entrust b,tb_car_info c,TM_CAR_MODEL_TYPE d where 1=1 and a.entrust_id = b.id and b.CAR_INFO_ID = c.id and  c.MODEL_TYPE_ID= d.id ";
	
		String sqlCondition = "";
		
		if(null!=entrustStatus){
			//已完成
			if(Constants.ISFINISH.equals(entrustStatus)){
				
				sqlCondition = "and (b.IS_FINISH ="+Constants.ISFINISH + " or b.ENTRUST_STATUS >=2) ";
				
			}
			//已结算
			else if(Constants.ISBALANCE.equals(entrustStatus)){
				
				sqlCondition = "and a.balance_id is not null";
				
			}
			else{
				sqlCondition = "and b.IS_FINISH !="+Constants.ISFINISH;
			}
		}
		
		if(null!=tbFixEntrust){
			
			if(null!=tbFixEntrust.getFixDateStart_s()&&!"".equals(tbFixEntrust.getFixDateStart_s())){
				sqlCondition +=" and convert(varchar(10),b.fix_date,120)>='"+tbFixEntrust.getFixDateStart_s()+"'";
			}
			if(null!=tbFixEntrust.getFixDateEnd_s()&&!"".equals(tbFixEntrust.getFixDateEnd_s())){
				sqlCondition +=" and convert(varchar(10),b.fix_date,120)<='"+tbFixEntrust.getFixDateEnd_s()+"'";
			}
			if(null!=tbFixEntrust.getTbCarInfo()){
				if(null!=tbFixEntrust.getTbCarInfo().getTmCarModelType())
					if(null!=tbFixEntrust.getTbCarInfo().getTmCarModelType().getId()){
						sqlCondition += " and d.id = " + tbFixEntrust.getTbCarInfo().getTmCarModelType().getId();
					}
			}
		}
		
		List list = tbFixEntrustContentDao.findByOriginSql(sql+sqlCondition, null);
		
		return Double.valueOf((String.valueOf(list.get(0)==null?0.00D:list.get(0))));
	}
	
	/**
	 * 显示各个工时情况
	 * @return
	 */
	public List<StatisticsTbFixBusinessVo> staticsTbFixEntrustContentShow(TbFixEntrust tbFixEntrust){
		
		StatisticsTbFixBusinessVo  statisticsTbFixBusinessVo_noFinsh = new StatisticsTbFixBusinessVo(); 
		
		StatisticsTbFixBusinessVo  statisticsTbFixBusinessVo_finsh = new StatisticsTbFixBusinessVo(); 
		
		StatisticsTbFixBusinessVo  statisticsTbFixBusinessVo_all = new StatisticsTbFixBusinessVo(); 
		
		StatisticsTbFixBusinessVo  statisticsTbFixBusinessVo_balance = new StatisticsTbFixBusinessVo(); 
		
		Double noFinish = this.staticsTbFixEntrustContent(0L,tbFixEntrust);
		
		statisticsTbFixBusinessVo_noFinsh.setContentShow("未完工工时");
		
		statisticsTbFixBusinessVo_noFinsh.setContentQuantity(noFinish);
		
		Double finish = this.staticsTbFixEntrustContent(Constants.ISFINISH,tbFixEntrust);
		
		statisticsTbFixBusinessVo_finsh.setContentShow("完工工时");
		
		statisticsTbFixBusinessVo_finsh.setContentQuantity(finish);
		
		Double all = this.staticsTbFixEntrustContent(null,tbFixEntrust);
		
		statisticsTbFixBusinessVo_all.setContentShow("总修理工时");
		
		statisticsTbFixBusinessVo_all.setContentQuantity(all);
		
		Double balance = this.staticsTbFixEntrustContent(Constants.ISBALANCE,tbFixEntrust);
		
		statisticsTbFixBusinessVo_balance.setContentShow("结算工时");
		
		statisticsTbFixBusinessVo_balance.setContentQuantity(balance);
		
		List<StatisticsTbFixBusinessVo> list = new ArrayList<StatisticsTbFixBusinessVo>();
		
		list.add(statisticsTbFixBusinessVo_noFinsh);
		
		list.add(statisticsTbFixBusinessVo_finsh);
		
		list.add(statisticsTbFixBusinessVo_all);
		
		list.add(statisticsTbFixBusinessVo_balance);
		
		return list;
	}
	
}
