package com.selfsoft.baseinformation.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbCardHisDao;
import com.selfsoft.baseinformation.dao.ITbMembershipCardDao;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.model.TmCardType;
import com.selfsoft.baseinformation.model.TmCardTypeService;
import com.selfsoft.baseinformation.model.TmMemberShipService;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.baseinformation.service.ITmCardTypeServiceService;
import com.selfsoft.baseinformation.service.ITmMemberShipServiceService;
import com.selfsoft.baseparameter.model.TmCardCheck;
import com.selfsoft.baseparameter.service.ITmCardCheckService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.secrity.model.TmUser;
@Service("tbMembershipCardService")
public class TbMembershipCardServiceImpl implements ITbMembershipCardService{
	
	@Autowired
	private ITbMembershipCardDao tbMembershipCardDao;
	
	@Autowired
	private ITbCardHisService tbCardHisService;
	
	@Autowired
	private ITmCardTypeService tmCardTypeService;
	
	@Autowired
	private ITmCardCheckService tmCardCheckService;
	
	@Autowired
	private ITmMemberShipServiceService tmMemberShipServiceService;
	
	@Autowired
	private ITmCardTypeServiceService tmCardTypeServiceService;
	
	public List<TbMembershipCard> findByTbMembershipCard(TbMembershipCard tbMembershipCard){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbMembershipCard.class);
		
		if(null!=tbMembershipCard){
			
			if(null!=tbMembershipCard.getCardNo()&&!"".equals(tbMembershipCard.getCardNo())){
				
				detachedCriteria.add(Restrictions.like("cardNo", "%"+tbMembershipCard.getCardNo()+"%"));
				
			}
			
			if(null!=tbMembershipCard.getCardStatus()){
				
				detachedCriteria.add(Restrictions.eq("cardStatus", tbMembershipCard.getCardStatus()));
				
			}
			
			if(null!=tbMembershipCard.getTmCardType()&&null!=tbMembershipCard.getTmCardType().getId()){
				
				detachedCriteria.createAlias("tmCardType", "tmCardType");
				
				detachedCriteria.add(Restrictions.eq("tmCardType.id",tbMembershipCard.getTmCardType().getId()));
				
			}
			
			//boolean flag = false;
			
			//if(null!=tbMembershipCard.getLicenseCode()&&!"".equals(tbMembershipCard.getLicenseCode())){
				
			//	flag = true;
			//	
			//	detachedCriteria.createAlias("tbCarInfo", "tbCarInfo");
				
			//	detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode","%"+tbMembershipCard.getLicenseCode()+"%"));
			//}
			//if(null!=tbMembershipCard.getTbCustomer()){
				
				if((null!=tbMembershipCard.getCustomerName()&&!"".equals(tbMembershipCard.getCustomerName()))||(null!=tbMembershipCard.getTelephone()&&!"".equals(tbMembershipCard.getTelephone()))){
					
					//detachedCriteria.setFetchMode("tbMembershipCard.tbCustomer", FetchMode.JOIN);
					
					//if(!flag)
					detachedCriteria.createAlias("tbCustomer", "tbCustomer");
					
					
				}
				
				if(null!=tbMembershipCard.getCustomerName()&&!"".equals(tbMembershipCard.getCustomerName())){
					
					detachedCriteria.add(Restrictions.like("tbCustomer.customerName","%"+tbMembershipCard.getCustomerName()+"%"));
					
				}
				
				if(null!=tbMembershipCard.getTelephone()&&!"".equals(tbMembershipCard.getTelephone())){
					
					detachedCriteria.add(Restrictions.like("tbCustomer.telephone","%"+tbMembershipCard.getTelephone()+"%"));
					
				}
				
			//}
				
			
		}
		
		return tbMembershipCardDao.findByCriteria(detachedCriteria, tbMembershipCard);
	}
	
	public TbMembershipCard findById(Long id){
		
		return tbMembershipCardDao.findById(id);
		
	}

	public void insertTbMembershipCard(TbMembershipCard tbMembershipCard) {
		
		tbMembershipCardDao.insert(tbMembershipCard);
		
	}

	public void updateTbMembershipCard(TbMembershipCard tbMembershipCard) {
		
		tbMembershipCardDao.update(tbMembershipCard);
		
	}
	
	public void updateTbMembershipCard(TbMembershipCard tbMembershipCard,TmUser tmUser,String desc) {
		
		tbMembershipCardDao.update(tbMembershipCard);
		
		tbCardHisService.insertCardHis(tbMembershipCard, tmUser, desc);
		
	}
	
	public TbMembershipCard findByCarInfo(TbCarInfo tbCarInfo){
		
		if(null != tbCarInfo){
			
			if(null!=tbCarInfo.getId()){
				
				List<TbMembershipCard> tbMembershipCardList = tbMembershipCardDao.findBySQL("from TbMembershipCard tbMembershipCard where tbMembershipCard.tbCarInfo.id = ?", new Object[]{tbCarInfo.getId()});
				
				if(null != tbMembershipCardList && tbMembershipCardList.size() > 0){
					
					return tbMembershipCardList.get(0);
					
				}
				
			}
			
		}
		
		return null;
	}
	
	public TbMembershipCard findByCardNo(String cardNo){
		
		if(null == cardNo || "".equals(cardNo)){
			
			return null;
			
		}
		
		List<TbMembershipCard> tbMembershipCardList = tbMembershipCardDao.findBySQL("from TbMembershipCard tbMembershipCard where tbMembershipCard.cardNo = ?", new Object[]{cardNo});
		
		if(null != tbMembershipCardList && tbMembershipCardList.size() > 0){
			
			return tbMembershipCardList.get(0);
			
		}
		
		return null;
	}
	
	/**
	 * 会员卡充值
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCzTbMembershipCard(TbMembershipCard tbMembershipCard,TmUser tmUser) {
		
		Double czje = tbMembershipCard.getCzje();
		
		tbMembershipCard = this.findById(tbMembershipCard.getId());
		
		tbMembershipCard.setCzje(czje);
		
		tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
		
		tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
		
		BigDecimal d_money = new BigDecimal("0.00");
		
		d_money = d_money.add(new BigDecimal(tbMembershipCard.getCardSaving()));
		
		d_money = d_money.add(new BigDecimal(czje));
		
		d_money = d_money.add(new BigDecimal(tmCardTypeService.calcCGiveMoney(czje, tbMembershipCard.getTmCardType().getId())));
		
		tbMembershipCard.setGiveMoney(tmCardTypeService.calcCGiveMoney(czje, tbMembershipCard.getTmCardType().getId()));
		
		tbMembershipCard.setCardSaving(d_money.doubleValue());
		
		BigDecimal d_point = new BigDecimal("0");
		
		d_point = d_point.add(new BigDecimal(tbMembershipCard.getCardPoint()));
		
		d_point = d_point.add(new BigDecimal(tmCardTypeService.calcCGivePoint(czje, tbMembershipCard.getTmCardType().getId())));
		
		tbMembershipCard.setGivePoint(tmCardTypeService.calcCGivePoint(czje, tbMembershipCard.getTmCardType().getId()));
		
		tbMembershipCard.setCardPoint(d_point.longValue());
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertCzCardHis(tbMembershipCard, tmUser);
	}
	
	/**
	 * 开卡
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertKkTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		tbMembershipCardDao.insert(tbMembershipCard);
		
		List<TmCardTypeService> tmCardTypeServiceList = tmCardTypeServiceService.findByTmCardTypeServiceId(tbMembershipCard.getTmCardType().getId());
		
		if(null != tmCardTypeServiceList) {
			
			for(TmCardTypeService tmCardTypeService : tmCardTypeServiceList) {
				
				TmMemberShipService tmMemberShipService = new TmMemberShipService();
				
				tmMemberShipService.setMemberShipId(tbMembershipCard.getId());
				
				tmMemberShipService.setServiceName(tmCardTypeService.getServiceName());
				
				tmMemberShipService.setServiceCount(tmCardTypeService.getServiceCount());
			
				tmMemberShipServiceService.insertTmMemberShipService(tmMemberShipService);
				
			}
		}
		
		
		tbCardHisService.insertKkCardHis(tbMembershipCard, tmUser);
		
	}
	
	/**
	 * 充积分
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCjfTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		Long czjf = tbMembershipCard.getCzjf();
		
		tbMembershipCard = this.findById(tbMembershipCard.getId());
		
		tbMembershipCard.setCzjf(czjf);
		
		tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
		
		tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
		
		BigDecimal d = new BigDecimal("0");
		
		d = d.add(new BigDecimal(tbMembershipCard.getCardPoint()));
		
		d = d.add(new BigDecimal(czjf));
		
		tbMembershipCard.setCardPoint(d.longValue());
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertCjfCardHis(tbMembershipCard, tmUser);
	}
	
	/**
	 * 换卡
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertHkTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertHkCardHis(tbMembershipCard, tmUser);
	}
	
	/**
	 * 结算
	 * @param tbMembershipCard
	 * @param tmUser
	 */
	public void insertJSTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertJSCardHis(tbMembershipCard, tmUser);
		
	}
	
	public boolean validateCarNo(TbMembershipCard tbMembershipCard){
		
		String cardNo = tbMembershipCard.getCardNo().substring(0,3);
		
		List<TmCardCheck> tmCardCheckList = tmCardCheckService.findAll();
		
		if(1==1){
			return true;
		}
		
		if(null != tmCardCheckList && tmCardCheckList.size() > 0){
			
			for(TmCardCheck tmCardCheck : tmCardCheckList){
				
				try {
					if(CommonMethod.encryptBASE64(cardNo).trim().equals(tmCardCheck.getCardNo())){
						
						return true;
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
		return false;
	}
	
	/**
	 * 积分消费
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertJfxfTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		Long czjf = tbMembershipCard.getCzjf();
		
		String remark = tbMembershipCard.getRemark();
		
		tbMembershipCard = this.findById(tbMembershipCard.getId());
		
		tbMembershipCard.setCzjf(czjf);
		
		tbMembershipCard.setRemark(remark == null ? "" : remark);
		
		tbMembershipCard.setOriCardPoint(tbMembershipCard.getCardPoint());
		
		tbMembershipCard.setOriCardSaving(tbMembershipCard.getCardSaving());
		
		BigDecimal d = new BigDecimal("0");
		
		d = d.add(new BigDecimal(tbMembershipCard.getCardPoint()));
		
		d = d.subtract(new BigDecimal(czjf));
		
		tbMembershipCard.setCardPoint(d.longValue());
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertJfxfCardHis(tbMembershipCard, tmUser);
	}
	
	public void insertSmartBalanceTbMembershipCard(TbMembershipCard tbMembershipCard, TmUser tmUser){
		
		this.updateTbMembershipCard(tbMembershipCard);
		
		tbCardHisService.insertSmartBalanceCardHis(tbMembershipCard, tmUser);
		
	}
	
	public Long calcDhMoney(String cardNo) {
		
		TbMembershipCard tbMembershipCard = this.findByCardNo(cardNo);
		
		Long cardPoint = tbMembershipCard.getCardPoint();
		
		TmCardType tmCardType = tbMembershipCard.getTmCardType();
		
		Integer dhFullPoint = tmCardType.getDhFullPoint();
		
		Integer dhFullMoney = tmCardType.getDhFullMoney();
		
		return (cardPoint/dhFullPoint) * dhFullMoney;
	}
	
	
	public Integer calcDhJF(String cardNo, Integer money) {
		
		TbMembershipCard tbMembershipCard = this.findByCardNo(cardNo);
		
		TmCardType tmCardType = tbMembershipCard.getTmCardType();
		
		Integer dhFullPoint = tmCardType.getDhFullPoint();
		
		Integer dhFullMoney = tmCardType.getDhFullMoney();
		
		return money/dhFullMoney * dhFullPoint;
	}
}
