package com.selfsoft.baseparameter.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmFixTypeDao;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
@Service("tmFixTypeService")
public class TmFixTypeServiceImpl implements ITmFixTypeService{
	
	@Autowired
	private ITmFixTypeDao tmFixTypeDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmFixTypeDao.deleteById(id);
	}

	public List<TmFixType> findAll() {
		// TODO Auto-generated method stub
		return tmFixTypeDao.findAll();
	}

	public TmFixType findById(Long id) {
		// TODO Auto-generated method stub
		return tmFixTypeDao.findById(id);
	}

	public void insert(TmFixType tmFixType) {
		// TODO Auto-generated method stub
		tmFixTypeDao.insert(tmFixType);
	}

	public void update(TmFixType tmFixType) {
		// TODO Auto-generated method stub
		tmFixTypeDao.update(tmFixType);
	}
	
	public Map<Long,String> findAllTmFixTypeMap(){
		
		Map map = new LinkedHashMap<Long,String>();
		
		List<TmFixType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmFixType tmFixType : list){
				
				map.put(tmFixType.getId(),tmFixType.getFixType());
			
			}
		}
		
		return map;
	}
}
