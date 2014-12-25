package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbCarStationWorkingRelationDao;
import com.selfsoft.baseinformation.model.TbCarStationWorkingRelation;
import com.selfsoft.baseinformation.service.ITbCarStationWorkingRelationService;
@Service("tbCarStationWorkingRelationService")
public class TbCarStationWorkingRelationServiceImpl implements ITbCarStationWorkingRelationService{

	@Autowired
	private ITbCarStationWorkingRelationDao tbCarStationWorkingRelationDao;
	
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return tbCarStationWorkingRelationDao.deleteById(id);
	}

	public void insert(TbCarStationWorkingRelation tbCarStationWorkingRelation) {
		// TODO Auto-generated method stub
		tbCarStationWorkingRelationDao.insert(tbCarStationWorkingRelation);
	}

	public void update(TbCarStationWorkingRelation tbCarStationWorkingRelation) {
		// TODO Auto-generated method stub
		tbCarStationWorkingRelationDao.update(tbCarStationWorkingRelation);
	}

	public TbCarStationWorkingRelation findById(Long id) {
		
		return tbCarStationWorkingRelationDao.findById(id);
	
	}

	public List<TbCarStationWorkingRelation>findByTbCarStationWorkingRelation(TbCarStationWorkingRelation tbCarStationWorkingRelation){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbCarStationWorkingRelation.class);
		
		if(null!=tbCarStationWorkingRelation){
			if(null!=tbCarStationWorkingRelation.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbCarStationWorkingRelation.getId()));
			}
			if(null!=tbCarStationWorkingRelation.getTbWorkingInfo()){
				detachedCriteria.createAlias("tbWorkingInfo", "tbWorkingInfo");
				detachedCriteria.add(Restrictions.eq("tbWorkingInfo.id", tbCarStationWorkingRelation.getTbWorkingInfo().getId()));
			}
			if(null!=tbCarStationWorkingRelation.getTmCarStationType()){
				detachedCriteria.createAlias("tmCarStationType", "tmCarStationType");
				detachedCriteria.add(Restrictions.eq("tmCarStationType.id", tbCarStationWorkingRelation.getTmCarStationType().getId()));
			}
		}
		return tbCarStationWorkingRelationDao.findByCriteria(detachedCriteria, tbCarStationWorkingRelation);
	}
}
