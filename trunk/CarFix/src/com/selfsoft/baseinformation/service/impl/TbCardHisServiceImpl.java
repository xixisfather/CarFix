package com.selfsoft.baseinformation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbCardHisDao;
import com.selfsoft.baseinformation.dao.ITmCardTypeDao;
import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITmCardTypeService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

@Service("tbCardHisService")
public class TbCardHisServiceImpl implements ITbCardHisService{

	@Autowired
	private ITbCardHisDao tbCardHisDao;
	
	@Autowired
	private ITmCardTypeService tmCardTypeService;
	
	@Autowired
	private ITmUserService tmUserService;
	
	public List<TbCardHis> findByTbCardHis(TbCardHis tbCardHis){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbCardHis.class);
		
		if(null != tbCardHis){
			
			if(null != tbCardHis.getCardNo() && !"".equals(tbCardHis.getCardNo())){
				
				detachedCriteria.add(Restrictions.like("cardNo","%" + tbCardHis.getCardNo() + "%"));
				
			}
			
			if(null != tbCardHis.getOperationDateFrom()){
				
				detachedCriteria.add(Restrictions.ge("operationDate", tbCardHis.getOperationDateFrom()));
				
			}
			
			if(null != tbCardHis.getOperationDateTo()){
				
				detachedCriteria.add(Restrictions.le("operationDate", CommonMethod.addDate(tbCardHis.getOperationDateTo(), 1)));
				
			}
			
			if(null != tbCardHis.getLicenseCode() && !"".equals(tbCardHis.getLicenseCode())){
				
				detachedCriteria.add(Restrictions.like("licenseCode","%" + tbCardHis.getLicenseCode() + "%"));
				
			}
			
			if(null != tbCardHis.getOperationType() && !"".equals(tbCardHis.getOperationType())){
				
				detachedCriteria.add(Restrictions.eq("operationType",tbCardHis.getOperationType()));
				
			}
			
			if(null != tbCardHis.getBalanceCode() && !"".equals(tbCardHis.getBalanceCode())){
				detachedCriteria.add(Restrictions.like("balanceCode","%" + tbCardHis.getBalanceCode() + "%"));
			}
			
		}
		
		else {
			
			return null;
			
		}
		
		/*List<TbCardHis> list = tbCardHisDao.findByCriteria(detachedCriteria, tbCardHis);
		
		List<TbCardHis> tbCardHisList = null;
		
		if(null != list && list.size() > 0){
			
			tbCardHisList = new ArrayList<TbCardHis>();
			
			for(TbCardHis _tbCardHis : list){
				
				TmUser tmUser = tmUserService.findById(_tbCardHis.getUserId());
				
				_tbCardHis.setTmUser(tmUser);
				
				tbCardHisList.add(_tbCardHis);
				
			}
			
		}*/
		
		return tbCardHisDao.findByCriteria(detachedCriteria, tbCardHis);
	}
	/**
	 * 充值历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCzCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());
		/**
		 * 存取之前的金额积分
		 */
		tbCardHis.setOriCardPoint(tbMembershipCard.getOriCardPoint());
		
		tbCardHis.setOriCardSaving(tbMembershipCard.getOriCardSaving());
		
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setGiveMoney(tbMembershipCard.getGiveMoney());
		
		tbCardHis.setGivePoint(tbMembershipCard.getGivePoint());
		
		tbCardHis.setCzje(tbMembershipCard.getCzje());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("充值金额【 " + tbMembershipCard.getCzje() +"】元" );
		
		tbCardHis.setOperationType(Constants.CARD_CZ);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
	}
	
	/**
	 * 开卡历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertKkCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());
		
		tbCardHis.setOriCardPoint(0l);
		
		tbCardHis.setOriCardSaving(0d);
		
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("车牌号【"+ tbMembershipCard.getTbCarInfo().getLicenseCode() +"】客户办理【" + tbMembershipCard.getTmCardType().getCardDesc() +"】会员卡号【" + tbMembershipCard.getCardNo() + "】初始金额【 " + tbMembershipCard.getCardSaving() +"】元;初始积分 【" + tbMembershipCard.getCardPoint()+ "】" );
		
		tbCardHis.setOperationType(Constants.CARD_KK);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
	}
	
	/**
	 * 充积分历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertCjfCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());
		/**
		 * 存取之前的金额积分
		 */
		tbCardHis.setOriCardPoint(tbMembershipCard.getOriCardPoint());
		
		tbCardHis.setOriCardSaving(tbMembershipCard.getOriCardSaving());
		
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setGiveMoney(tbMembershipCard.getGiveMoney());
		
		tbCardHis.setGivePoint(tbMembershipCard.getGivePoint());
		
		tbCardHis.setCzjf(tbMembershipCard.getCzjf());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("充【 " + tbMembershipCard.getCzjf() +"】积分" );
		
		tbCardHis.setOperationType(Constants.CARD_CJF);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
		
	}
	
	/**
	 * 换卡历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertHkCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());

		tbCardHis.setOriCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setOriCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("原会员卡号【 " + tbMembershipCard.getPreviousCardNo() +"】;现会员卡号【 " + tbMembershipCard.getCardNo() +"】");
		
		tbCardHis.setOperationType(Constants.CARD_CJF);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
	}
	
	/**
	 * 结算记录
	 * @param tbMembershipCard
	 * @param tmUser
	 */
	public void insertJSCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());
		/**
		 * 结算之前的金额积分
		 */
		tbCardHis.setOriCardPoint(tbMembershipCard.getOriCardPoint());
		
		tbCardHis.setOriCardSaving(tbMembershipCard.getOriCardSaving());
		
		/**
		 * 结算之后的金额积分
		 */
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		/**
		 * 工时返回金额
		 */
		tbCardHis.setGsGiveMoney(tbMembershipCard.getGsGiveMoney());
		
		/**
		 *配件返回金额 
		 */
		tbCardHis.setPjGiveMoney(tbMembershipCard.getPjGiveMoney());
		
		/**
		 * 工时返回积分
		 */
		tbCardHis.setGsGivePoint(tbMembershipCard.getGsGivePoint());
		
		/**
		 * 配件返回积分
		 */
		tbCardHis.setPjGivePoint(tbMembershipCard.getPjGivePoint());
		/**
		 * 返还金额
		 */
		tbCardHis.setGiveMoney(tbMembershipCard.getGiveMoney());
		
		/**
		 * 返还积分
		 */
		tbCardHis.setGivePoint(tbMembershipCard.getGivePoint());
		
		/**
		 * 结算金额
		 */
		tbCardHis.setJexf(tbMembershipCard.getJexf());
		
		/**
		 * 积分消费
		 */
		tbCardHis.setJfxf(0);
		
		/**
		 * 工时金额消费
		 */
		tbCardHis.setGsJexf(tbMembershipCard.getGsJexf());
		
		/**
		 * 配件金额消费
		 */
		tbCardHis.setPjJexf(tbMembershipCard.getPjJexf());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("结算单【" + tbMembershipCard.getBalanceCode() + "】共结算【"+tbCardHis.getJexf()+"】元,工时结算【" + tbCardHis.getGsJexf()+ "】元返还【"+tbCardHis.getGsGiveMoney()+"】元【"+tbCardHis.getGsGivePoint()+"】积分,配件结算【" + tbCardHis.getPjJexf() + "】元返还【"+tbCardHis.getPjGiveMoney()+"】元【"+tbCardHis.getPjGivePoint()+"】积分,共返还【"+tbCardHis.getGiveMoney()+"】元【"+ tbCardHis.getGivePoint()+"】积分,会员卡支付【" + tbMembershipCard.getCardZFJE() +"】元.");
		
		tbCardHis.setBalanceCode(tbMembershipCard.getBalanceCode());
		
		tbCardHis.setBalanceId(tbMembershipCard.getBalanceId());
		
		tbCardHis.setOperationType(Constants.CARD_JS);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
	}
	
	/**
	 * 打印历史记录参数设置
	 */
	public Map putCardHisReportParamMap(List<TbCardHis> tbCardHisList, HttpServletRequest request){
		
		Map map =  new HashMap();
		
		map.put("dataSourceList", tbCardHisList);
		
		map.put("jrxmlPath", "/reportfiles/tbCardHis.jrxml");
			
		map.put("reportTpl", "/tbCardHis_pdf_tpl.properties");
		
		return map;
	}
	
	/**
	 * 消费积分历史记录
	 * @param tbMembershipCard
	 * @param userId
	 */
	public void insertJfxfCardHis(TbMembershipCard tbMembershipCard,TmUser tmUser){
		
		TbCardHis tbCardHis = new TbCardHis();
		
		tbCardHis.setCardId(tbMembershipCard.getId());
		
		tbCardHis.setCardNo(tbMembershipCard.getCardNo());
		/**
		 * 存取之前的金额积分
		 */
		tbCardHis.setOriCardPoint(tbMembershipCard.getOriCardPoint());
		
		tbCardHis.setOriCardSaving(tbMembershipCard.getOriCardSaving());
		
		tbCardHis.setAftCardPoint(tbMembershipCard.getCardPoint());
		
		tbCardHis.setAftCardSaving(tbMembershipCard.getCardSaving());
		
		tbCardHis.setGiveMoney(0);
		
		tbCardHis.setGivePoint(0);
		
		tbCardHis.setCzjf(tbMembershipCard.getCzjf());
		
		tbCardHis.setOperationDate(new Date());
		
		tbCardHis.setOperationDesc("消费【 " + tbMembershipCard.getCzjf() +"】积分," +  tbMembershipCard.getRemark());
		
		tbCardHis.setOperationType(Constants.CARD_JFXF);
		
		//tbCardHis.setUserId(userId);
		
		tbCardHis.setLicenseCode(tbMembershipCard.getLicenseCode());
		
		tbCardHis.setUserName(tmUser.getUserName());
		
		tbCardHis.setUserRealName(tmUser.getUserRealName());
		
		tbCardHisDao.insert(tbCardHis);
		
	}
	
	public List<TbCardHis> findCardHisByBalanceId(Long balanceId){
		
		return tbCardHisDao.findBySQL("select tbCardHis from TbCardHis tbCardHis where tbCardHis.balanceId =" + balanceId, null);
		
	}
}
