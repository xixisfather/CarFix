package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartCollectionDao;
import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartCollectionService;
import com.selfsoft.baseinformation.vo.TbPartCollectionVo;
@Service("tbPartCollectionService")
public class TbPartCollectionServiceImpl implements ITbPartCollectionService {
	@Autowired
	private ITbPartCollectionDao tbPartCollectionDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	
	
	public List<TbPartCollectionVo> getTbPartCollection(){
		List<TbPartCollectionVo> result = tbPartCollectionDao.getTbPartCollection();
		return result;
	}
	
	
	public void insertTbPartCollection(TbPartCollection tbPartCollection){
		tbPartCollectionDao.insert(tbPartCollection);
	}
	
	public List<TbPartCollection> findByEntity(TbPartCollection tbPartCollection) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbPartCollection.class);
		if(null!=tbPartCollection){
			if(null!=tbPartCollection.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbPartCollection.getId()));
			}
			if(null!=tbPartCollection.getPartInfoId()){
				detachedCriteria.add(Restrictions.eq("partInfoId", tbPartCollection.getPartInfoId()));
			}
		
			if(StringUtils.isNotBlank(tbPartCollection.getCollectionCode())){
				detachedCriteria.add(Restrictions.eq("collectionCode", tbPartCollection.getCollectionCode()));
			}
		} 
		return tbPartCollectionDao.findByCriteria(detachedCriteria, tbPartCollection);
	}
	
	
	public boolean isUniquecollectionCode(String collectionCode){
		if(StringUtils.isBlank(collectionCode)) return false;
		
		
		TbPartCollection tpc = new TbPartCollection();
		tpc.setCollectionCode(collectionCode);
		List<TbPartCollection>  tpcList = this.findByEntity(tpc);
		
		if(tpcList!=null && tpcList.size()>0 ) return false;
		
		return true;
	}
	
	
	public void deleteByCollectionCode(String collectionCode){
		/*
		TbPartCollection tpc = new TbPartCollection();
		tpc.setCollectionCode(collectionCode);
		//与配件集所关联的配件
		List<TbPartCollection> collectionList = this.findByEntity(tpc);
		//归还每个配件，更新库存数量
		for(TbPartCollection collection : collectionList){
			deleteCollectionRelation(collection);
		}
		
		*/
		//删除配件集，以及相关的配件的关联 
		tbPartCollectionDao.deleteByCollectionCode(collectionCode);
	}
	
	public void deleteCollectionRelation(TbPartCollection collection){
		/*
		TbPartInfo tpi = tbPartInfoDao.findById(collection.getPartInfoId());
		tpi.setStoreQuantity(tpi.getStoreQuantity()+collection.getPartQuantity());
		tbPartInfoDao.update(tpi);
		*/
		tbPartCollectionDao.delete(collection);
	}
}
