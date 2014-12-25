package com.selfsoft.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.IStStorehouseSurveyDao;
import com.selfsoft.business.model.StStockin;
import com.selfsoft.business.model.StStorehouseSurvey;
import com.selfsoft.business.service.IStStorehouseSurveyService;
import com.selfsoft.framework.common.CommonMethod;
@Service("stStorehouseSurveyService")
public class StStorehouseSurveyService implements IStStorehouseSurveyService {
	
	@Autowired
	private IStStorehouseSurveyDao stStorehouseSurveyDao;

	public boolean deleteById(Long id) {
		return stStorehouseSurveyDao.deleteById(id);
	}

	public StStorehouseSurvey findById(Long id) {
		return stStorehouseSurveyDao.findById(id);
	}

	public List<StStorehouseSurvey> findByStStorehouseSurvey(StStorehouseSurvey stStorehouseSurvey){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StStorehouseSurvey.class);
		
		if(null!=stStorehouseSurvey){
			if(null!=stStorehouseSurvey.getId()){
				detachedCriteria.add(Restrictions.eq("id",stStorehouseSurvey.getId()));
			}
			if(StringUtils.isNotBlank(stStorehouseSurvey.getHouseName())){
				detachedCriteria.add(Restrictions.like("houseName", "%"+stStorehouseSurvey.getHouseName()+"%"));
			}
			
		}
		
		return stStorehouseSurveyDao.findByCriteria(detachedCriteria, stStorehouseSurvey);
	}

	public void insert(StStorehouseSurvey stStorehouseSurvey) {
		stStorehouseSurveyDao.insert(stStorehouseSurvey);
	}

	public void update(StStorehouseSurvey stStorehouseSurvey) {
		stStorehouseSurveyDao.update(stStorehouseSurvey);
	}

}
