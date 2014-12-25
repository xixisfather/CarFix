package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmLostDayDao;
import com.selfsoft.baseparameter.model.TmLostDay;
import com.selfsoft.baseparameter.service.ITmLostDayService;
@Service("tmLostDayService")
public class TmLostDayServiceImpl implements ITmLostDayService{
	
	@Autowired
	private ITmLostDayDao tmLostDayDao;
	
	public List<TmLostDay> findAll(){
		
		return tmLostDayDao.findAll();
		
	}
	
	public void updateTmLostDay(TmLostDay tmLostDay){
		
		tmLostDayDao.update(tmLostDay);
		
	}
	
	public TmLostDay findById(Long id){
		
		return tmLostDayDao.findById(id);
	}
}
