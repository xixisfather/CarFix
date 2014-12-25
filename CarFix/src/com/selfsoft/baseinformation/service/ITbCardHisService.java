package com.selfsoft.baseinformation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.secrity.model.TmUser;

public interface ITbCardHisService {

	public List<TbCardHis> findByTbCardHis(TbCardHis tbCardHis);
	/**
	 * 充值历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCzCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	/**
	 * 开卡历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertKkCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	/**
	 * 充积分历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCjfCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	/**
	 * 换卡历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertHkCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	/**
	 * 结算记录
	 * @param tbMembershipCard
	 * @param tmUser
	 */
	public void insertJSCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	/**
	 * 历史记录明细打印参数设置
	 * @param tbCardHisList
	 * @param request
	 * @return
	 */
	public Map putCardHisReportParamMap(List<TbCardHis> tbCardHisList, HttpServletRequest request);
	
	public void insertJfxfCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser);
	
	public List<TbCardHis> findCardHisByBalanceId(Long balanceId);
}
