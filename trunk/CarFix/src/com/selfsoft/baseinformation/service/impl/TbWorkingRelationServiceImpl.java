package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbWorkingRelationDao;
import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.model.TbWorkingRelation;
import com.selfsoft.baseinformation.service.ITbWorkingRelationService;
@Service("tbWorkingRelationService")
public class TbWorkingRelationServiceImpl implements ITbWorkingRelationService{

	@Autowired
	private ITbWorkingRelationDao tbWorkingRelationDao;
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingRelationDao.deleteById(id);
	}

	public TbWorkingRelation findById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingRelationDao.findById(id);
	}

	public void insert(TbWorkingRelation tbWorkingRelation) {
		// TODO Auto-generated method stub
		tbWorkingRelationDao.insert(tbWorkingRelation);
	}

	public void update(TbWorkingRelation tbWorkingRelation) {
		// TODO Auto-generated method stub
		tbWorkingRelationDao.update(tbWorkingRelation);
	}
	
	public List<TbWorkingRelation> findByTbWorkingRelation(TbWorkingRelation tbWorkingRelation){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbWorkingRelation.class);
		
		if(null!=tbWorkingRelation){
			if(null!=tbWorkingRelation.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbWorkingRelation.getId()));
			}
			if(null!=tbWorkingRelation.getTbWorkingInfo()){
				detachedCriteria.createAlias("tbWorkingInfo", "tbWorkingInfo");
				detachedCriteria.add(Restrictions.eq("tbWorkingInfo.id", tbWorkingRelation.getTbWorkingInfo().getId()));
			}
			if(null!=tbWorkingRelation.getTbWorkingCollection()){
				detachedCriteria.createAlias("tbWorkingCollection", "tbWorkingCollection");
				detachedCriteria.add(Restrictions.eq("tbWorkingCollection.id", tbWorkingRelation.getTbWorkingCollection().getId()));
			}
		}
		
		return tbWorkingRelationDao.findByCriteria(detachedCriteria, tbWorkingRelation);
	}

	/**
	 * 根据工时工位的ID来查找关系集
	 */
	public List<TbWorkingRelation> findTbWorkingRelationByTbWorkingInfoId(Long tbWorkingInfoId){
		
		TbWorkingRelation tbWorkingRelation = new TbWorkingRelation();
		
		TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();
		
		tbWorkingInfo.setId(tbWorkingInfoId);
		
		tbWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
		
		return this.findByTbWorkingRelation(tbWorkingRelation);
	}
	
	/**
	 * 根据工位集ID来查找关系集
	 */
	public List<TbWorkingRelation> findTbWorkingRelationByTbWorkingCollectionId(Long tbWorkingCollectionId){
		
		TbWorkingRelation tbWorkingRelation = new TbWorkingRelation();
		
		TbWorkingCollection tbWorkingCollection = new TbWorkingCollection();
		
		tbWorkingCollection.setId(tbWorkingCollectionId);
		
		tbWorkingRelation.setTbWorkingCollection(tbWorkingCollection);
		
		return this.findByTbWorkingRelation(tbWorkingRelation);
	}
}
