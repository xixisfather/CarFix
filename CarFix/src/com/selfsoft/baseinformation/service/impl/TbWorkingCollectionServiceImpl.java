package com.selfsoft.baseinformation.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbWorkingCollectionDao;
import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.model.TbWorkingRelation;
import com.selfsoft.baseinformation.service.ITbWorkingCollectionService;
import com.selfsoft.baseinformation.service.ITbWorkingRelationService;
import com.selfsoft.baseparameter.model.TmCarStationType;
@Service("tbWorkingCollectionService")
public class TbWorkingCollectionServiceImpl implements ITbWorkingCollectionService {
	@Autowired
	private ITbWorkingCollectionDao tbWorkingCollectionDao;
	@Autowired
	private ITbWorkingRelationService tbWorkingRelationService;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingCollectionDao.deleteById(id);
	}

	public List<TbWorkingCollection> findAll() {
		// TODO Auto-generated method stub
		return tbWorkingCollectionDao.findAll();
	}

	public List<TbWorkingCollection> findByTbWorkingCollection(TbWorkingCollection tbWorkingCollection){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbWorkingCollection.class);
		
		if(null!=tbWorkingCollection){
			if(null!=tbWorkingCollection.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbWorkingCollection.getId()));
			}
			if(null!=tbWorkingCollection.getWorkingCollectionCode()&&!"".equals(tbWorkingCollection.getWorkingCollectionCode())){
				detachedCriteria.add(Restrictions.like("workingCollectionCode", "%"+tbWorkingCollection.getWorkingCollectionCode()+"%"));
			}
			if(null!=tbWorkingCollection.getWorkingCollectionName()&&!"".equals(tbWorkingCollection.getWorkingCollectionName())){
				detachedCriteria.add(Restrictions.like("workingCollectionName", "%"+tbWorkingCollection.getWorkingCollectionName()+"%"));
			}
			if(null!=tbWorkingCollection.getTmCarStationType()){
				if(null!=tbWorkingCollection.getTmCarStationType().getId()){
					detachedCriteria.add(Restrictions.eq("tmCarStationType.id", tbWorkingCollection.getTmCarStationType().getId()));
				}
			}
		}
		
		return tbWorkingCollectionDao.findByCriteria(detachedCriteria, tbWorkingCollection);
	}
	
	public void insert(TbWorkingCollection tbWorkingCollection) {
		// TODO Auto-generated method stub
		tbWorkingCollectionDao.insert(tbWorkingCollection);
	}

	public void update(TbWorkingCollection tbWorkingCollection) {
		// TODO Auto-generated method stub
		tbWorkingCollectionDao.update(tbWorkingCollection);
	}

	public TbWorkingCollection findById(Long id) {
		// TODO Auto-generated method stub
		return tbWorkingCollectionDao.findById(id);
	}
	
	//插入工位集表 同时插入工位集工位工时关系表
	public void insertRelation(TbWorkingCollection tbWorkingCollection){
		
		this.insertTbWorkingRelation(tbWorkingCollection);
		
		this.insert(tbWorkingCollection);
		
	}
	
	//插入工位集工位工时关系表
	private void insertTbWorkingRelation(TbWorkingCollection tbWorkingCollection){
		
		Set tbWorkingRelations = tbWorkingCollection.getTbWorkingRelations();
		
		Long tmCarStationTypeId = tbWorkingCollection.getTmCarStationTypeId();
		
		if(null!=tbWorkingRelations){
			
			Iterator<String> it = tbWorkingRelations.iterator();
			
			while(it.hasNext()){
				
				String tbWorkingInfoId = it.next();
				
				TbWorkingInfo tbWorkingInfo = new TbWorkingInfo();
				
				tbWorkingInfo.setId(Long.valueOf(tbWorkingInfoId));
				
				TbWorkingRelation tbWorkingRelation = new TbWorkingRelation();
				
				tbWorkingRelation.setTbWorkingCollection(tbWorkingCollection);
				
				tbWorkingRelation.setTbWorkingInfo(tbWorkingInfo);
				
				tbWorkingRelationService.insert(tbWorkingRelation);
			}
			
		}
		
	}
	/**
	 * 将该工位集与工位对应的关系从关系表中删除
	 */
	private void deleteTbWorkingRelation(TbWorkingCollection tbWorkingCollection){
		
		List<TbWorkingRelation> tbWorkingRelationList = tbWorkingRelationService.findTbWorkingRelationByTbWorkingCollectionId(tbWorkingCollection.getId());
		
		if(null!=tbWorkingRelationList&&tbWorkingRelationList.size()>0){
			
			for(TbWorkingRelation tbWorkingRelation : tbWorkingRelationList){
				
				tbWorkingRelationService.deleteById(tbWorkingRelation.getId());
			}
		}
	}
	
	public void updateRelation(TbWorkingCollection tbWorkingCollection){
		
		this.update(tbWorkingCollection);
		
		this.deleteTbWorkingRelation(tbWorkingCollection);
		
		this.insertTbWorkingRelation(tbWorkingCollection);
		
	}
	
	/**
	 * 将工位集与工时工位关系同时删除
	 */
	public boolean deleteRelation(Long id){
		
		TbWorkingCollection tbWorkingCollection = this.findById(id);
		
		this.deleteTbWorkingRelation(tbWorkingCollection);
		
		this.deleteById(id);
		
		return true;
	}
	//根据工位集代码与车型工位ID来查找工位集对象
	public List<TbWorkingCollection> findTbWorkingCollectionByWorkingCollectionCodeAndTmCarStationTypeId(String workingCollectionCode,Long tmCarStationTypeId){
		
		return tbWorkingCollectionDao.findBySQL("FROM TbWorkingCollection tbWorkingCollection where tbWorkingCollection.workingCollectionCode=? and tbWorkingCollection.tmCarStationType.id=?", new Object[]{workingCollectionCode,tmCarStationTypeId});
	}
}
