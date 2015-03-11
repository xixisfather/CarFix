package com.selfsoft.baseinformation.service.impl;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITmCardTypeDao;
import com.selfsoft.baseinformation.model.TmCardType;
import com.selfsoft.baseinformation.model.TmCardTypeService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.baseinformation.service.ITmCardTypeServiceService;
@Service("tmCardTypeService")
public class TmCardTypeServiceImpl implements ITmCardTypeService{
	
	
	@Autowired
	private ITmCardTypeDao tmCardTypeDao;
	@Autowired
	private ITmCardTypeServiceService tmCardTypeServiceService;
	
	public List<TmCardType> findAll(){
		
		return tmCardTypeDao.findAll();
	}
	
	public Map<Long, String> findAllTmCardTypeMap(){
		
		Map<Long, String> tmCardTypeMap = new LinkedHashMap<Long, String>();
		
		List<TmCardType> tmCardTypeList = this.findAll();
		
		if(null!=tmCardTypeList&&tmCardTypeList.size()>0){
			
			for(TmCardType tmCardType : tmCardTypeList){
				
				tmCardTypeMap.put(tmCardType.getId(), tmCardType.getCardDesc());
				
			}
			
		}
		
		return tmCardTypeMap;
		
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmCardTypeDao.deleteById(id);
	}

	@Override
	public void insert(TmCardType tmCardType) {
		// TODO Auto-generated method stub
		tmCardTypeDao.insert(tmCardType);
		
		List<TmCardTypeService> tmCardTypeServiceList = tmCardType.getTmCardTypeServiceList();
		
		if(null != tmCardTypeServiceList && tmCardTypeServiceList.size() > 0) {
			
			for (TmCardTypeService tmCardTypeService : tmCardTypeServiceList) {
				
				tmCardTypeService.setCardTypeId(tmCardType.getId());
				
				tmCardTypeServiceService.insertTmCardTypeService(tmCardTypeService);
			}
			
		}
	}

	@Override
	public void update(TmCardType tmCardType) {
		
		tmCardTypeServiceService.deleteByTmCardTypeServiceId(tmCardType.getId());
		
		tmCardTypeDao.update(tmCardType);
		
		List<TmCardTypeService> tmCardTypeServiceList = tmCardType.getTmCardTypeServiceList();
		
		if(null != tmCardTypeServiceList && tmCardTypeServiceList.size() > 0) {
			
			for (TmCardTypeService tmCardTypeService : tmCardTypeServiceList) {
				
				tmCardTypeService.setCardTypeId(tmCardType.getId());
				
				tmCardTypeServiceService.insertTmCardTypeService(tmCardTypeService);
			}
			
		}
	}
	
	public TmCardType findById(Long id){
		
		TmCardType tmCardType = tmCardTypeDao.findById(id);
		
		if(null != tmCardType) {
			
			List<TmCardTypeService> tmCardTypeServiceList = tmCardTypeServiceService.findByTmCardTypeServiceId(id);
			
			tmCardType.setTmCardTypeServiceList(tmCardTypeServiceList);
		}
		
		return tmCardType;
	}
	
	/**
	 * 计算工时满多少返回多少
	 */
	public Integer calcGSGiveMoney(Double gsMoney,Long cardTypeId) {
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_fixHourMoney = new BigDecimal(gsMoney);
		
		Integer gsMFullMoney = tmCardType.getGsMFullMoney();
		
		BigDecimal bs = d_fixHourMoney.divide(new BigDecimal(gsMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getGsMGiveMoney();
	}
	
	/**
	 * 计算配件满多少返回多少
	 */
	public Integer calcPJGiveMoney(Double pjMoney,Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(pjMoney);
		
		Integer pjMFullMoney = tmCardType.getPjMFullMoney();
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getPjMGiveMoney();
	}
	
	/**
	 * 工时送积分
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGSGivePoint(Double gsMoney,Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_fixHourMoney = new BigDecimal(gsMoney);
		
		Integer gsPFullMoney = tmCardType.getGsPFullMoney();
		
		BigDecimal bs = d_fixHourMoney.divide(new BigDecimal(gsPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getGsPGivePoint();
		
	}
	
	/**
	 * 配件送积分
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPJGivePoint(Double gsMoney,Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(gsMoney);
		
		Integer pjPFullMoney = tmCardType.getPjPFullMoney();
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getPjPGivePoint();
		
	}
	
	/**
	 * 充值送钱
	 * @param cMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcCGiveMoney(Double cMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_cMoney = new BigDecimal(cMoney);
		
		Integer cFullMoney = tmCardType.getcFullMoney();
		
		BigDecimal bs = d_cMoney.divide(new BigDecimal(cFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		return bs.intValue() * tmCardType.getcGiveMoney();
	}
	
	/**
	 * 充值送积分
	 * @param cMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcCGivePoint(Double cMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_cMoney = new BigDecimal(cMoney);
		
		Integer cFullMoney = tmCardType.getcFullMoney();
		
		BigDecimal bs = d_cMoney.divide(new BigDecimal(cFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getcGivePoint();
	}
	
	/**
	 * 工时结算金额返回
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGsGiveMoney(Double gsMoney, Long cardTypeId,String fixType){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_gsMoney = new BigDecimal(gsMoney);
		
		Integer gsMFullMoney = 0;
		Integer gsMGiveMoney = 0;
		if(fixType.indexOf("保险") == -1){
			gsMFullMoney = tmCardType.getGsMFullMoney();
			gsMGiveMoney = tmCardType.getGsMGiveMoney();
		}
		else {
			gsMFullMoney = tmCardType.getGsBxMFullMoney();
			gsMGiveMoney = tmCardType.getGsBxMGiveMoney();
		}
				
				
		
		BigDecimal bs = d_gsMoney.divide(new BigDecimal(gsMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * gsMGiveMoney;
		
	}
	
	/**
	 * 工时结算积分返回
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGsGivePoint(Double gsMoney, Long cardTypeId, String fixType){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_gsMoney = new BigDecimal(gsMoney);
		Integer gsPFullMoney = 0;
		Integer gsPGivePoint = 0;
		if(fixType.indexOf("保险")==-1) {
			gsPFullMoney = tmCardType.getGsPFullMoney();
			gsPGivePoint = tmCardType.getGsPGivePoint();
		}
		else {
			gsPFullMoney = tmCardType.getGsBxPFullMoney();
			gsPGivePoint = tmCardType.getGsBxPGivePoint();
		}
		
		BigDecimal bs = d_gsMoney.divide(new BigDecimal(gsPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * gsPGivePoint;
		
	}
	
	/**
	 * 配件结算金额返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGiveMoney(Double pjMoney, Long cardTypeId,String fixType){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(pjMoney);
		
		Integer pjMFullMoney = 0;
		Integer pjMGiveMoney = 0;
		if(fixType.indexOf("保险")==-1) {
			pjMFullMoney = tmCardType.getPjMFullMoney();
			pjMGiveMoney = tmCardType.getPjMGiveMoney();
		}
		else {
			pjMFullMoney = tmCardType.getPjBxMFullMoney();
			pjMGiveMoney = tmCardType.getPjBxMGiveMoney();
		}
		
		
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * pjMGiveMoney;
		
	}
	
	/**
	 * 配件积分返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGivePoint(Double pjMoney, Long cardTypeId, String fixType){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(pjMoney);
		Integer pjPFullMoney = 0;
		Integer pjPGivePoint = 0;
		if(fixType.indexOf("保险") == -1){
			pjPFullMoney = tmCardType.getPjPFullMoney();
			pjPGivePoint = tmCardType.getPjPGivePoint();
		}
		else {
			pjPFullMoney = tmCardType.getPjBxPFullMoney();
			pjPGivePoint = tmCardType.getPjBxPGivePoint();
		}
		
		
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * pjPGivePoint;
		
	}
	
	/**
	 * 优惠扣积分
	 * @param yhMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcYhMinusPoint(Double yhMoney, Long cardTypeId) {
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_yhMoney = new BigDecimal(yhMoney);
		
		Integer yhMFullMoney = tmCardType.getYhMFullMoney();
		
		BigDecimal bs = d_yhMoney.divide(new BigDecimal(yhMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getYhMMinusPoint();
	}
	
}
