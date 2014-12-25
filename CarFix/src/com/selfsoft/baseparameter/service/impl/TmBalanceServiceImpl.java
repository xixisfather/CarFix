package com.selfsoft.baseparameter.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmBalanceDao;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.service.ITmBalanceService;
@Service("tmBalanceService")
public class TmBalanceServiceImpl implements ITmBalanceService{

	@Autowired
	private ITmBalanceDao tmBalanceDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmBalanceDao.deleteById(id);
	}

	public List<TmBalance> findAll() {
		// TODO Auto-generated method stub
		return tmBalanceDao.findAll();
	}

	public void insert(TmBalance tmBalance) {
		// TODO Auto-generated method stub
		tmBalanceDao.insert(tmBalance);
	}

	public void update(TmBalance tmBalance) {
		// TODO Auto-generated method stub
		tmBalanceDao.update(tmBalance);
	}

	public TmBalance findById(Long id) {
		// TODO Auto-generated method stub
		return tmBalanceDao.findById(id);
	}
	
	public Map<Long,String> findAllTmBalanceMap(){
		
		Map<Long,String> map = new LinkedHashMap<Long, String>();
		
		List<TmBalance> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmBalance t : list){
			
				map.put(t.getId(), t.getBalanceName());
			
			}
		
		}
		return map;
	}
}
