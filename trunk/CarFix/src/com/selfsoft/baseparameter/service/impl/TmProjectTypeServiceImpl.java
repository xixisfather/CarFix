package com.selfsoft.baseparameter.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmAlertDayDao;
import com.selfsoft.baseparameter.dao.ITmProjectTypeDao;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;

@Service("tmProjectTypeService")
public class TmProjectTypeServiceImpl implements ITmProjectTypeService{

	@Autowired
	private ITmProjectTypeDao tmProjectTypeDao;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmProjectTypeDao.deleteById(id);
	}

	public List<TmProjectType> findAll() {
		// TODO Auto-generated method stub
		return tmProjectTypeDao.findAll();
	}

	public Map<Long, String> findAllTmProjectTypeMap() {
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmProjectType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			for(TmProjectType t : list){
				map.put(t.getId(), t.getProjectType());
			}
		}
		return map;
	}

	public TmProjectType findById(Long id) {
		// TODO Auto-generated method stub
		return tmProjectTypeDao.findById(id);
	}


	public void insert(TmProjectType tmProjectType) {
		// TODO Auto-generated method stub
		tmProjectTypeDao.insert(tmProjectType);
	}

	public void update(TmProjectType tmProjectType) {
		// TODO Auto-generated method stub
		tmProjectTypeDao.update(tmProjectType);
	}
}
