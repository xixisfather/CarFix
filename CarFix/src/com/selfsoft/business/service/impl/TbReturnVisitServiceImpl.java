package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbReturnVisitDao;
import com.selfsoft.business.model.TbReturnVisit;
import com.selfsoft.business.service.ITbReturnVisitService;
import com.selfsoft.framework.common.CommonMethod;
@Service("tbReturnVisitService")
public class TbReturnVisitServiceImpl implements ITbReturnVisitService{
	
	@Autowired
	private ITbReturnVisitDao tbReturnVisitDao;
	
	public List<TbReturnVisit> findByTbReturnVisit(TbReturnVisit tbReturnVisit){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbReturnVisit.class);
		
		if(null != tbReturnVisit){
			
			if(null != tbReturnVisit.getTbCarInfo()){
				
				detachedCriteria.createAlias("tbCarInfo", "tbCarInfo");
				
				if(null != tbReturnVisit.getTbCarInfo().getLicenseCode() && !"".equals(tbReturnVisit.getTbCarInfo().getLicenseCode())){
					
					detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode", "%" + tbReturnVisit.getTbCarInfo().getLicenseCode() + "%"));
					
				}
			}
			
			if(null != tbReturnVisit.getVisitDateFrom()){
				
				detachedCriteria.add(Restrictions.ge("visitDate", tbReturnVisit.getVisitDateFrom()));
			
			}
			
			if(null != tbReturnVisit.getVisitDateTo()){
				
				detachedCriteria.add(Restrictions.le("visitDate", CommonMethod.addDate(tbReturnVisit.getVisitDateTo(), 1)));
			
			}
			
			if(null != tbReturnVisit.getReturnType() && !"".equals(tbReturnVisit.getReturnType())){
				
				detachedCriteria.add(Restrictions.eq("returnType", tbReturnVisit.getReturnType()));
			}
		}
		
		return tbReturnVisitDao.findByCriteria(detachedCriteria, tbReturnVisit);
	}
	
	public void insertTbReturnVisit(TbReturnVisit tbReturnVisit){
		
		tbReturnVisitDao.insert(tbReturnVisit);
		
	}
	
	public void updateTbReturnVisit(TbReturnVisit tbReturnVisit){
		
		tbReturnVisitDao.update(tbReturnVisit);
		
	}
	
	public TbReturnVisit findById(Long id){
		
		return tbReturnVisitDao.findById(id);
		
	}
	
	public boolean deleteById(Long id){
		
		return tbReturnVisitDao.deleteById(id);
	}
}
