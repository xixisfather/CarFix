package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.service.ITbCardHisService;
import com.selfsoft.baseinformation.service.ITbMembershipCardService;
import com.selfsoft.baseinformation.service.ITmMemberShipServiceService;
import com.selfsoft.baseinformation.service.impl.TbCardHisServiceImpl;
import com.selfsoft.business.dao.ITbBookDao;
import com.selfsoft.business.dao.ITbSmartBalanceDao;
import com.selfsoft.business.model.TbSmartBalance;
import com.selfsoft.business.service.ITbSmartBalanceService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;
@Service("tbSmartBalanceService")
public class TbSmartBalanceServiceImpl implements ITbSmartBalanceService {

	@Autowired
	private ITbSmartBalanceDao tbSmartBalanceDao;
	
	@Autowired
	private ITbMembershipCardService tbMembershipCardService;
	@Autowired
	private ITmMemberShipServiceService tmMemberShipServiceService;
	@Autowired
	private ITbCardHisService tbCardHisService;
	
	public TbSmartBalance findById(Long id) {
		
		return tbSmartBalanceDao.findById(id);
	}

	public void insertTbSmartBalance(TbSmartBalance tbSmartBalance, TmUser tmUser) {
		
		tbSmartBalanceDao.insert(tbSmartBalance);
		
		if(tbSmartBalance.getPayPatten().equals(Constants.PAYMEMBERCARD)) {
			
			if(tbSmartBalance.getUseCardService().equals(0L)) {
				
				
				tbMembershipCardService.insertSmartBalanceTbMembershipCard(tbSmartBalance.getTbMembershipCard(), tmUser);
				
			}
			
			else {
				
				tmMemberShipServiceService.updateTmMemberShipService(tbSmartBalance.getTbMembershipCard().getId(), tbSmartBalance.getServiceName());
			
				tbCardHisService.insertSmartBalanceUseServiceCardHis(tbSmartBalance.getTbMembershipCard(), tmUser);
			}
			
			
		}
		
	}

	
	public List<TbSmartBalance> findByTbSmartBalance(
			TbSmartBalance tbSmartBalance) {
	
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbSmartBalance.class);
		
		if(null!=tbSmartBalance){
			
			if(null != tbSmartBalance.getPayPatten()) {
				
				detachedCriteria.add(Restrictions.eq("payPatten", tbSmartBalance.getPayPatten()));
				
			}
			
			if(null != tbSmartBalance.getWorkerName() && !"".equals(tbSmartBalance.getWorkerName())) {
				
				detachedCriteria.add(Restrictions.eq("workerName", tbSmartBalance.getWorkerName()));
				
			}
			
			if(null != tbSmartBalance.getBalanceDateStart()) {
				detachedCriteria.add(Restrictions.ge("balanceDate",
						tbSmartBalance.getBalanceDateStart()));
			}
			if (null != tbSmartBalance.getBalanceDateEnd()) {
				detachedCriteria.add(Restrictions.le("balanceDate",
						CommonMethod.addDate(tbSmartBalance.getBalanceDateEnd(), 1)));
			}
			
			
		}
		
		
		return tbSmartBalanceDao.findByCriteria(detachedCriteria, tbSmartBalance);
	}
}
