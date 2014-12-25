package com.selfsoft.baseparameter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseparameter.dao.ITmSoleTypeDao;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
@Service("tmSoleTypeService")
public class TmSoleTypeServiceImpl implements ITmSoleTypeService{
	
	@Autowired
	private ITmSoleTypeDao tmSoleTypeDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmSoleTypeDao.deleteById(id);
	}

	public List<TmSoleType> findAll() {
		// TODO Auto-generated method stub
		List<TmSoleType> types=  tmSoleTypeDao.findAll();
//		if(types == null || types.size() == 0){
//			return initSoleTypes();
//		}
		return types;
	}

	public TmSoleType findById(Long id) {
		// TODO Auto-generated method stub
		return tmSoleTypeDao.findById(id);
	}

	public void insert(TmSoleType tmSoleType) {
		// TODO Auto-generated method stub
		if(tmSoleType.getIsDefault() == 1L){
			List<TmSoleType> soletypes = this.findAll();
			for(TmSoleType type : soletypes){
				type.setIsDefault(0L);
				tmSoleTypeDao.update(type);
			}
		}
		tmSoleTypeDao.insert(tmSoleType);
	}

	public void update(TmSoleType tmSoleType) {
		tmSoleTypeDao.update(tmSoleType);
		if(tmSoleType.getIsDefault() == 1L){
			List<TmSoleType> soletypes = this.findAll();
			for(TmSoleType type : soletypes){
				if(type.getId().equals(tmSoleType.getId())) continue;
				type.setIsDefault(0L);
				tmSoleTypeDao.update(type);
			}
		}
		
	
	}

	/**
	 * 返回所有的销售类型 主要用于下拉列表
	 */
	public Map<Long, String> findAllTmSoleTypeMap() {
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmSoleType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmSoleType t : list){
			
				map.put(t.getId(),t.getSoleName());
			
			}
		
		}
		return map;
	}
	
	public List<TmSoleType> findByEntity(TmSoleType tmSoleType) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmSoleType.class);
		if(null!=tmSoleType){
			if(null!=tmSoleType.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmSoleType.getId()));
			}
			if(null!=tmSoleType.getIsDefault()){
				
				detachedCriteria.add(Restrictions.eq("isDefault", tmSoleType.getIsDefault()));
			}
			
		} 
		return tmSoleTypeDao.findByCriteria(detachedCriteria, tmSoleType);
	}
	
	public TmSoleType getDefaultSoleType(){
		TmSoleType st = new TmSoleType();
		st.setIsDefault(1L);
		List<TmSoleType> types = this.findByEntity(st);
		
		if(types != null && types.size()>0)
			return types.get(0);
		return null;
	}
	
	public List<TmSoleType> initSoleTypes(){
		TmSoleType t1 = new TmSoleType();
		t1.setSoleName("零售价");t1.setIsDefault(1L);
		TmSoleType t2 = new TmSoleType();
		t2.setSoleName("批发价");t2.setIsDefault(0L);
		TmSoleType t3 = new TmSoleType();
		t3.setSoleName("销售价");t3.setIsDefault(0L);
		TmSoleType t4 = new TmSoleType();
		t4.setSoleName("进货价");t4.setIsDefault(0L);
		tmSoleTypeDao.insert(t1);
		tmSoleTypeDao.insert(t2);
		tmSoleTypeDao.insert(t3);
		tmSoleTypeDao.insert(t4);
		
		return tmSoleTypeDao.findAll();
	}
}
