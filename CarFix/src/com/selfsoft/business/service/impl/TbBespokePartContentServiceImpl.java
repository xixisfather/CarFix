package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbBespokePartContentDao;
import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.service.ITbBespokePartContentService;
@Service("tbBespokePartContentService")
public class TbBespokePartContentServiceImpl implements
		ITbBespokePartContentService {

	@Autowired
	private ITbBespokePartContentDao tbBespokePartContentDao;
	
	public boolean deleteById(Long id) {
		return tbBespokePartContentDao.deleteById(id);
	}

	public List<TbBespokePartContent> findByEntity(
			TbBespokePartContent tbBespokePartContent) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbBespokePartContent.class);
		if(null!=tbBespokePartContent){
			if(null!=tbBespokePartContent.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbBespokePartContent.getId()));
			}
			if(null!=tbBespokePartContent.getTbFixEntrust() && null != tbBespokePartContent.getTbFixEntrust().getId() ){
				detachedCriteria.add(Restrictions.eq("tbFixEntrust.id", tbBespokePartContent.getTbFixEntrust().getId()));
			}
		} 
		return tbBespokePartContentDao.findByCriteria(detachedCriteria, tbBespokePartContent);
	}

	public TbBespokePartContent findById(Long id) {
		return tbBespokePartContentDao.findById(id);
	}

	public void insert(TbBespokePartContent tbBespokePartContent) {
		tbBespokePartContentDao.insert(tbBespokePartContent);
	}

	public void update(TbBespokePartContent tbBespokePartContent) {
		tbBespokePartContentDao.update(tbBespokePartContent);
	}
	
	public void deleteByEntrustId(Long entrustId){
		tbBespokePartContentDao.deleteByEntrustId(entrustId);
	}

	
}
