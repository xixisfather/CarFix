package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbStoreHouseCheckDetailDao;
import com.selfsoft.business.model.TbStoreHouseCheck;
import com.selfsoft.business.model.TbStoreHouseCheckDetail;
import com.selfsoft.business.service.ITbStoreHouseCheckDetailService;


@Service("tbStoreHouseCheckDetailService")
public class TbStoreHouseCheckDetailServiceImpl implements ITbStoreHouseCheckDetailService {

	@Autowired
	private ITbStoreHouseCheckDetailDao tbStoreHouseCheckDetailDao;
	
	@Override
	public boolean deleteById(Long id) {
		return tbStoreHouseCheckDetailDao.deleteById(id);
	}

	@Override
	public List<TbStoreHouseCheckDetail> findByEntity(TbStoreHouseCheckDetail tbStoreHouseCheckDetail) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbStoreHouseCheckDetail.class);
		
		if(null!=tbStoreHouseCheckDetail){
			if(null!=tbStoreHouseCheckDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id",tbStoreHouseCheckDetail.getId()));
			}
			if(null!=tbStoreHouseCheckDetail.getTbStoreHouseCheck()){
				
				if(null!=tbStoreHouseCheckDetail.getTbStoreHouseCheck().getId()){
					
					detachedCriteria.createAlias("tbStoreHouseCheck","tbStoreHouseCheck");
					
					detachedCriteria.add(Restrictions.eq("tbStoreHouseCheck.id",tbStoreHouseCheckDetail.getTbStoreHouseCheck().getId()));
					
				}
			}
			
		}
		
		return tbStoreHouseCheckDetailDao.findByCriteria(detachedCriteria, tbStoreHouseCheckDetail);
	}

	@Override
	public TbStoreHouseCheckDetail findById(Long id) {
		return tbStoreHouseCheckDetailDao.findById(id);
	}

	@Override
	public void insertTbStoreHouseCheckDetail(
			TbStoreHouseCheckDetail tbStoreHouseCheckDetail) {
		tbStoreHouseCheckDetailDao.insert(tbStoreHouseCheckDetail);
	}

	@Override
	public void updateTbStoreHouseCheckDetail(TbStoreHouseCheckDetail tbStoreHouseCheckDetail) {
		tbStoreHouseCheckDetailDao.update(tbStoreHouseCheckDetail);
	}

	@Override
	public void deleteCheckDetail(Long checkId) {
		tbStoreHouseCheckDetailDao.deleteCheckDetail(checkId);
	}

}
