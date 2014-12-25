package com.selfsoft.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.IStStockinDao;
import com.selfsoft.business.model.StStockin;
import com.selfsoft.business.service.IStStockinService;
import com.selfsoft.framework.common.CommonMethod;
@Service("stStockinService")
public class StStockinServiceImpl implements IStStockinService {

	@Autowired
	private IStStockinDao stStockinDao;
	
	public boolean deleteById(Long id) {
		return stStockinDao.deleteById(id);
	}

	public StStockin findById(Long id) {
		return stStockinDao.findById(id);
	}
	
	public void insert(StStockin stStockin) {
		stStockinDao.insert(stStockin);
	}

	public void update(StStockin stStockin) {
		stStockinDao.update(stStockin);
	}

	public List<StStockin> findStStockin(StStockin stStockin) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StStockin.class);
		
		if(null!=stStockin){
			if(null!=stStockin.getId()){
				detachedCriteria.add(Restrictions.eq("id",stStockin.getId()));
			}
			if(StringUtils.isNotBlank(stStockin.getStockInCode())){
				detachedCriteria.add(Restrictions.like("stockInCode", "%"+stStockin.getStockInCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getHouseName())){
				detachedCriteria.add(Restrictions.like("houseName",  "%"+stStockin.getHouseName()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getPartCode())){
				detachedCriteria.add(Restrictions.like("partCode",  "%"+stStockin.getPartCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getPartName())){
				detachedCriteria.add(Restrictions.like("partName",  "%"+stStockin.getPartName()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getCustomerCode())){
				detachedCriteria.add(Restrictions.like("customerCode",  "%"+stStockin.getCustomerCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getOucherCode())){
				detachedCriteria.add(Restrictions.like("oucherCode",  "%"+stStockin.getOucherCode()+"%"));
			}
			if(StringUtils.isNotBlank(stStockin.getBeginStockInDate())){
				detachedCriteria.add(Restrictions.ge("stockInDate", CommonMethod.parseStringToDate(stStockin.getBeginStockInDate(),"yyyy-MM-dd")));
			}
			if(StringUtils.isNotBlank(stStockin.getEndStockInDate())){
				detachedCriteria.add(Restrictions.le("stockInDate",CommonMethod.parseStringToDate(stStockin.getEndStockInDate(),"yyyy-MM-dd")));
			}
			
		}
		
		return stStockinDao.findByCriteria(detachedCriteria, stStockin);
	}

	

}
