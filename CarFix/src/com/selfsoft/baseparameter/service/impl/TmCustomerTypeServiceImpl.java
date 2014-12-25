package com.selfsoft.baseparameter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmCustomerTypeDao;
import com.selfsoft.baseparameter.model.TmCustomerType;
import com.selfsoft.baseparameter.service.ITmCustomerTypeService;
@Service("tmCustomerTypeService")
public class TmCustomerTypeServiceImpl implements ITmCustomerTypeService{

	@Autowired
	private ITmCustomerTypeDao tmCustomerTypeDao;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmCustomerTypeDao.deleteById(id);
	}

	public List<TmCustomerType> findAll() {
		// TODO Auto-generated method stub
		return tmCustomerTypeDao.findAll();
	}

	public TmCustomerType findById(Long id) {
		// TODO Auto-generated method stub
		return tmCustomerTypeDao.findById(id);
	}

	public void insert(TmCustomerType tmCustomerType) {
		// TODO Auto-generated method stub
		tmCustomerTypeDao.insert(tmCustomerType);
	}

	public void update(TmCustomerType tmCustomerType) {
		// TODO Auto-generated method stub
		tmCustomerTypeDao.update(tmCustomerType);
	}
	
	public Map<Long,String> findAllTmCustomerTypeMap(){
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmCustomerType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmCustomerType c : list){
			
				map.put(c.getId(),c.getTypeName());
			
			}
		
		}
		return map;	
	}
	

}
