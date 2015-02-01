package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.secrity.model.TmUser;

public interface ITbMembershipCardService {
	
	public List<TbMembershipCard> findByTbMembershipCard(TbMembershipCard tbMembershipCard);
	
	public TbMembershipCard findById(Long id);
	
	public void insertTbMembershipCard(TbMembershipCard tbMembershipCard);
	
	public void updateTbMembershipCard(TbMembershipCard tbMembershipCard);
	
	public TbMembershipCard findByCarInfo(TbCarInfo tbCarInfo);
	
	public TbMembershipCard findByCardNo(String cardNo);
	
	/**
	 * 会员卡充值
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCzTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	/**
	 * 开卡
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertKkTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	/**
	 * 充积分
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCjfTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	/**
	 * 换卡
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertHkTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	/**
	 * 结算
	 * @param tbMembershipCard
	 * @param tmUser
	 */
	public void insertJSTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	public boolean validateCarNo(TbMembershipCard tbMembershipCard);
	
	/**
	 * 积分消费
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertJfxfTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	public void updateTbMembershipCard(TbMembershipCard tbMembershipCard,TmUser tmUser,String desc);
	
	public void insertSmartBalanceTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser);
	
	public Long calcDhMoney(String cardNo);
}
