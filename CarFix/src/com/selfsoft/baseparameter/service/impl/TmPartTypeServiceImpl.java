package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmPartTypeDao;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.baseparameter.service.ITmPartTypeService;
@Service("tmPartTypeService")
public class TmPartTypeServiceImpl implements ITmPartTypeService{
	@Autowired
	private ITmPartTypeDao tmPartTypeDao;
	
	public Boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmPartTypeDao.deleteById(id);
	}

	public List<TmPartType> findAll() {
		// TODO Auto-generated method stub
		return tmPartTypeDao.findAll();
	}

	public TmPartType findById(Long id) {
		// TODO Auto-generated method stub
		return tmPartTypeDao.findById(id);
	}

	public List<TmPartType> findTmPartTypeList(TmPartType tmPartType) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmPartType.class);
		
		if(null!=tmPartType){
			if(null!=tmPartType.getTypeCode()&&!"".equals(tmPartType.getTypeCode())){
				detachedCriteria.add(Restrictions.like("typeCode", "%"+tmPartType.getTypeCode()+"%"));
			}
			if(null!=tmPartType.getTypeName()&&!"".equals(tmPartType.getTypeName())){
				detachedCriteria.add(Restrictions.like("typeName", "%"+tmPartType.getTypeName()+"%"));
			}
		}
		return tmPartTypeDao.findByCriteria(detachedCriteria, tmPartType);
	}

	public void saveTmPartType(TmPartType tmPartType) {
		// TODO Auto-generated method stub
		tmPartTypeDao.insert(tmPartType);
	}

	public void updateTmPartType(TmPartType tmPartType) {
		// TODO Auto-generated method stub
		tmPartTypeDao.update(tmPartType);
	}

	public TmPartType findByTypeCode(String typeCode){
		
		String hql = "from TmPartType t where t.typeCode = ?";
		
		List<TmPartType> tmPartTypeList = tmPartTypeDao.findBySQL(hql, new Object[]{typeCode});
		
		if(tmPartTypeList!=null && tmPartTypeList.size() >0)
			return tmPartTypeList.get(0);
		
		return null;
	}
}
