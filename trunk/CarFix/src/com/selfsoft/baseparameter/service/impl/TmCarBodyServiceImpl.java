package com.selfsoft.baseparameter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmCarBodyDao;
import com.selfsoft.baseparameter.model.TmCarBody;
import com.selfsoft.baseparameter.service.ITmCarBodyService;

@Service("tmCarBodyService")
public class TmCarBodyServiceImpl implements ITmCarBodyService{
	
	@Autowired
	private ITmCarBodyDao tmCarBodyDao;
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmCarBodyDao.deleteById(id);
	}

	public List<TmCarBody> findAll() {
		// TODO Auto-generated method stub
		return tmCarBodyDao.findAll();
	}

	public Map<Long, String> findAllTmCarBodyMap() {
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmCarBody> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			for(TmCarBody t : list){
				map.put(t.getId(), t.getBodyName());
			}
		}
		return map;
	}

	public TmCarBody findById(Long id) {
		// TODO Auto-generated method stub
		return tmCarBodyDao.findById(id);
	}


	public void insert(TmCarBody tmCarBody) {
		// TODO Auto-generated method stub
		tmCarBodyDao.insert(tmCarBody);
	}

	public void update(TmCarBody tmCarBody) {
		// TODO Auto-generated method stub
		tmCarBodyDao.update(tmCarBody);
	}

	public List<TmCarBody> findTmCarBodyByBodyName(String bodyName){
		
		return tmCarBodyDao.findBySQL("from TmCarBody tmCarBody where tmCarBody.bodyName=?", new Object[]{bodyName});
	}
}
