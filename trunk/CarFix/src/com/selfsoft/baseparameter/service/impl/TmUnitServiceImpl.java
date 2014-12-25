package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmUnitDao;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.baseparameter.service.ITmUnitService;

@Service("tmUnitService")
public class TmUnitServiceImpl implements ITmUnitService{

	@Autowired
	private ITmUnitDao tmUnitDao;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmUnitDao.deleteById(id);
	}

	public List<TmUnit> findAll() {
		// TODO Auto-generated method stub
		return tmUnitDao.findAll();
	}

	public TmUnit findById(Long id) {
		// TODO Auto-generated method stub
		return tmUnitDao.findById(id);
	}

	public void insert(TmUnit tmUnit) {
		// TODO Auto-generated method stub
		tmUnitDao.insert(tmUnit);
	}

	public void update(TmUnit tmUnit) {
		// TODO Auto-generated method stub
		tmUnitDao.update(tmUnit);
	}
	
	
	public TmUnit findByUnitName(String UnitName){
		
		String hql = "from TmUnit t where t.unitName = ?";
		
		List<TmUnit> tmUnitList = tmUnitDao.findBySQL(hql, new Object[]{UnitName});
		
		if(tmUnitList!=null && tmUnitList.size() >0)
			return tmUnitList.get(0);
		
		return null;
	}

}
