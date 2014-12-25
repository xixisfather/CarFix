package com.selfsoft.secrity.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmWorkTypeDao;
import com.selfsoft.secrity.model.TmWorkType;
import com.selfsoft.secrity.service.ITmWorkTypeService;
@Service("tmWorkTypeService")
public class TmWorkTypeServiceImpl implements ITmWorkTypeService{

	@Autowired
	private ITmWorkTypeDao tmWorkTypeDao;
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmWorkTypeDao.deleteById(id);
	}

	public List<TmWorkType> findAll() {
		// TODO Auto-generated method stub
		return tmWorkTypeDao.findAll();
	}

	public TmWorkType findById(Long id) {
		// TODO Auto-generated method stub
		return tmWorkTypeDao.findById(id);
	}

	public void insert(TmWorkType tmWorkType) {
		// TODO Auto-generated method stub
		tmWorkTypeDao.insert(tmWorkType);
	}

	public void update(TmWorkType tmWorkType) {
		// TODO Auto-generated method stub
		tmWorkTypeDao.update(tmWorkType);
	}
	
	public Map<Long,String> findAllTmWorkTypeMap(){
		
		Map<Long, String> map = new HashMap<Long, String>();
		
		List<TmWorkType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmWorkType t : list){
			
				map.put(t.getId(),t.getWorkCode()+" "+t.getWorkName());
			
			}
		
		}
		return map;
	}
	
	public List<TmWorkType> findTmWorkTypeByWorkName(String workName){
		
		return tmWorkTypeDao.findBySQL("from TmWorkType tmWorkType where tmWorkType.workName=?", new Object[]{workName});
	}
}
