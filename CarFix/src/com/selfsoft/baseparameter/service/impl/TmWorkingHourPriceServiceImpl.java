package com.selfsoft.baseparameter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmWorkingHourPriceDao;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmWorkingHourPrice;
import com.selfsoft.baseparameter.service.ITmWorkingHourPriceService;
@Service("tmWorkingHourPriceService")
public class TmWorkingHourPriceServiceImpl implements ITmWorkingHourPriceService{
	
	@Autowired
	private ITmWorkingHourPriceDao tmWorkingHourPriceDao;
	
	public boolean deleteById(Long id) {
		
		return tmWorkingHourPriceDao.deleteById(id);
	}

	public TmWorkingHourPrice findById(Long id) {
		
		return tmWorkingHourPriceDao.findById(id);
	}

	public boolean insert(TmWorkingHourPrice tmWorkingHourPrice) {
		
		return tmWorkingHourPriceDao.insert(tmWorkingHourPrice);
	}

	public boolean update(TmWorkingHourPrice tmWorkingHourPrice) {
		
		return tmWorkingHourPriceDao.update(tmWorkingHourPrice);
	}

	public List<TmWorkingHourPrice> findAll() {
		
		return tmWorkingHourPriceDao.findAll();
	}

	public Map<Long, String> findAllTmWorkingHourPriceMap() {
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		List<TmWorkingHourPrice> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmWorkingHourPrice t : list){
			
				map.put(t.getId(), t.getPrice() +"  "+t.getPriceRemark());
			
			}
		
		}
		return map;
	}

	public Map<Double, String> findAllTmWorkingHourPriceValueMap() { 
		
		Map<Double, String> map = new LinkedHashMap<Double, String>();
		
		List<TmWorkingHourPrice> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmWorkingHourPrice t : list){
			
				map.put(t.getPrice(), t.getPrice() +"  "+t.getPriceRemark());
			
			}
		
		}
		return map;
	}
}
