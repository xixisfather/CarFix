package com.selfsoft.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.IStPartReceiverDao;
import com.selfsoft.business.model.StPartReceiver;
import com.selfsoft.business.model.StStockin;
import com.selfsoft.business.service.IStPartReceiverService;
@Service("stPartReceiverService")
public class StPartReceiverServiceImpl implements IStPartReceiverService {

	@Autowired
	private IStPartReceiverDao stPartReceiverDao;
	
	public boolean deleteById(Long id) {
		return stPartReceiverDao.deleteById(id);
	}

	public StPartReceiver findById(Long id) {
		return stPartReceiverDao.findById(id);
	}

	public List<StPartReceiver> findByStPartReceiver(StPartReceiver stPartReceiver) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StPartReceiver.class);
		
		if(null!=stPartReceiver){
			if(null!=stPartReceiver.getId()){
				detachedCriteria.add(Restrictions.eq("id",stPartReceiver.getId()));
			}
			if(StringUtils.isNotBlank(stPartReceiver.getHouseName())){
				detachedCriteria.add(Restrictions.like("houseName", "%"+stPartReceiver.getHouseName()+"%"));
			}
			if(StringUtils.isNotBlank(stPartReceiver.getPartCode())){
				detachedCriteria.add(Restrictions.like("partCode", "%"+stPartReceiver.getPartCode()+"%"));
			}
			if(StringUtils.isNotBlank(stPartReceiver.getPartName())){
				detachedCriteria.add(Restrictions.like("partName", "%"+stPartReceiver.getPartName()+"%"));
			}
			
		}
		
		return stPartReceiverDao.findByCriteria(detachedCriteria, stPartReceiver);
	}

	public void insert(StPartReceiver stPartReceiver) {
		stPartReceiverDao.insert(stPartReceiver);
	}

	public void update(StPartReceiver stPartReceiver) {
		stPartReceiverDao.update(stPartReceiver);
	}
	
	public StPartReceiver getStPartReceiverCount(List<StPartReceiver> stPartReceiverList){
		StPartReceiver countResult = new StPartReceiver();
		
		
		Double totalStockinQuantity = 0D;
		Double totalStockinPrice = 0D;
		Double totalStockoutQuantity = 0D;
		Double totalStockoutPrice = 0D;
		
		for(StPartReceiver stPartReceiver : stPartReceiverList){
			totalStockinQuantity += stPartReceiver.getStockinQuantity();
			totalStockinPrice += stPartReceiver.getStockinPrice();
			totalStockoutQuantity += stPartReceiver.getStockoutQuantity();
			totalStockoutPrice += stPartReceiver.getStockoutPrice();
		}
		
		countResult.setTotalStockinPrice(totalStockinPrice);
		countResult.setTotalStockinQuantity(totalStockinQuantity);
		countResult.setTotalStockoutQuantity(totalStockoutQuantity);
		countResult.setTotalStockoutPrice(totalStockoutPrice);
		
		return countResult;
	}

}
