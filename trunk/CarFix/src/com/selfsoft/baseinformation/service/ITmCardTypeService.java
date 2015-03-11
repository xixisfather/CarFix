package com.selfsoft.baseinformation.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseinformation.model.TmCardType;

public interface ITmCardTypeService {

	public Map<Long, String> findAllTmCardTypeMap();
	
	public List<TmCardType> findAll();
	
	public void insert(TmCardType tmCardType);
	
	public void update(TmCardType tmCardType);
	
	public boolean deleteById(Long id);
	
	public TmCardType findById(Long id);
	
	/**
	 * 计算工时满多少返回多少
	 */
	public Integer calcGSGiveMoney(Double gsMoney,Long cardTypeId);
	
	/**
	 * 计算配件满多少返回多少
	 */
	public Integer calcPJGiveMoney(Double pjMoney,Long cardTypeId);
	
	/**
	 * 工时送积分
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGSGivePoint(Double gsMoney,Long cardTypeId);
	
	/**
	 * 配件送积分
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPJGivePoint(Double gsMoney,Long cardTypeId);
	
	/**
	 * 充值送钱
	 * @param cMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcCGiveMoney(Double cMoney, Long cardTypeId);
	
	/**
	 * 充值送积分
	 * @param cMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcCGivePoint(Double cMoney, Long cardTypeId);
	
	/**
	 * 工时结算金额返回
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGsGiveMoney(Double gsMoney, Long cardTypeId, String fixType);
	
	/**
	 * 工时结算积分返回
	 * @param gsMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcGsGivePoint(Double gsMoney, Long cardTypeId, String fixType);
	
	/**
	 * 配件结算金额返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGiveMoney(Double pjMoney, Long cardTypeId, String fixType);
	
	/**
	 * 配件积分返回
	 * @param pjMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcPjGivePoint(Double pjMoney, Long cardTypeId, String fixType);
	
	/**
	 * 优惠扣积分
	 * @param yhMoney
	 * @param cardTypeId
	 * @return
	 */
	public Integer calcYhMinusPoint(Double yhMoney, Long cardTypeId);
}
