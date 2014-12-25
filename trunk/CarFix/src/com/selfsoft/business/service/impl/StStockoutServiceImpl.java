package com.selfsoft.business.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.IStStockoutDao;
import com.selfsoft.business.model.StStockout;
import com.selfsoft.business.service.IStStockoutService;
import com.selfsoft.framework.common.CommonMethod;

@Service("stStockoutService")
public class StStockoutServiceImpl implements IStStockoutService {

	@Autowired
	private IStStockoutDao stStockoutDao;
	
	public boolean deleteById(Long id) {
		return stStockoutDao.deleteById(id);
	}

	public StStockout findById(Long id) {
		return stStockoutDao.findById(id);
	}

	public List<StStockout> findByStStockout(StStockout stStockout) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StStockout.class);
		
		if(null!=stStockout){
			if(null!=stStockout.getId()){
				detachedCriteria.add(Restrictions.eq("id",stStockout.getId()));
			}
			if(StringUtils.isNotBlank(stStockout.getHouseName())){
				detachedCriteria.add(Restrictions.like("houseName", "%"+stStockout.getHouseName()+"%"));
			}
			if(StringUtils.isNotBlank(stStockout.getPartCode())){
				detachedCriteria.add(Restrictions.like("partCode", "%"+stStockout.getPartCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockout.getPartName())){
				detachedCriteria.add(Restrictions.like("partName", "%"+stStockout.getPartName()+"%"));
			}
			if(StringUtils.isNotBlank(stStockout.getStockoutCode())){
				detachedCriteria.add(Restrictions.like("stockoutCode", "%"+stStockout.getStockoutCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockout.getStockoutType())){
				detachedCriteria.add(Restrictions.eq("stockoutType", stStockout.getStockoutType()));
			}
			if(StringUtils.isNotBlank(stStockout.getBeginStockOutDate())){
				detachedCriteria.add(Restrictions.ge("stockOutDate", CommonMethod.parseStringToDate(stStockout.getBeginStockOutDate(),"yyyy-MM-dd")));
			}
			if(StringUtils.isNotBlank(stStockout.getEndStockOutDate())){
				detachedCriteria.add(Restrictions.le("stockOutDate",CommonMethod.parseStringToDate(stStockout.getEndStockOutDate(),"yyyy-MM-dd")));
			}
			
		}
		
		return stStockoutDao.findByCriteria(detachedCriteria, stStockout);
	}

	public void insert(StStockout stStockout) {
		stStockoutDao.insert(stStockout);
	}

	public void update(StStockout stStockout) {
		stStockoutDao.update(stStockout);
	}
	
	
	public StStockout getStStockoutCount(List<StStockout> stStockoutList){
		StStockout countResult = new StStockout();
		
		Double totalQuantity = 0D;
		Double totalSellPrice = 0D;
		Double totalCostPrice = 0D;
		Set<String> partCategorySet  = new HashSet<String>();
		
		for(StStockout stStockout : stStockoutList){
			totalQuantity += stStockout.getQuantity();
			totalSellPrice += stStockout.getSellPrice();
			totalCostPrice += stStockout.getCostPrice();
			partCategorySet.add(stStockout.getStockoutType());
		}
		
		countResult.setTotalQuantity(totalQuantity);
		countResult.setTotalSellPrice(totalSellPrice);
		countResult.setTotalCostPrice(totalCostPrice);
		countResult.setPartCategory(Long.valueOf(partCategorySet.size()));
		
		return countResult;
	} 
}
