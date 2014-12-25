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
import com.selfsoft.baseinformation.service.ITmCardTypeService;
@Service("tmCardTypeService")
public class TmCardTypeServiceImpl implements ITmCardTypeService{
	
	
	@Autowired
	private ITmCardTypeDao tmCardTypeDao;
	
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
	}

	@Override
	public void update(TmCardType tmCardType) {
		// TODO Auto-generated method stub
		tmCardTypeDao.update(tmCardType);
	}
	
	public TmCardType findById(Long id){
		
		return tmCardTypeDao.findById(id);
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
	public Integer calcGsGiveMoney(Double gsMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_gsMoney = new BigDecimal(gsMoney);
		
		Integer gsMFullMoney = tmCardType.getGsMFullMoney();
		
		BigDecimal bs = d_gsMoney.divide(new BigDecimal(gsMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getGsMGiveMoney();
		
	}
	
	/**
	 * 工时结算积分返回
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGsGivePoint(Double gsMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_gsMoney = new BigDecimal(gsMoney);
		
		Integer gsPFullMoney = tmCardType.getGsPFullMoney();
		
		BigDecimal bs = d_gsMoney.divide(new BigDecimal(gsPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getGsPGivePoint();
		
	}
	
	/**
	 * 配件结算金额返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGiveMoney(Double pjMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(pjMoney);
		
		Integer pjMFullMoney = tmCardType.getPjMFullMoney();
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjMFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getPjMGiveMoney();
		
	}
	
	/**
	 * 配件积分返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGivePoint(Double pjMoney, Long cardTypeId){
		
		TmCardType tmCardType = tmCardTypeDao.findById(cardTypeId);
		
		BigDecimal d_pjMoney = new BigDecimal(pjMoney);
		
		Integer pjPFullMoney = tmCardType.getPjPFullMoney();
		
		BigDecimal bs = d_pjMoney.divide(new BigDecimal(pjPFullMoney),2,
				BigDecimal.ROUND_HALF_UP).setScale(2,
						BigDecimal.ROUND_HALF_UP);
		
		return bs.intValue() * tmCardType.getPjPGivePoint();
		
	}
	
}
