package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmAlertDayDao;
import com.selfsoft.baseparameter.model.TmAlertDay;
import com.selfsoft.baseparameter.service.ITmAlertDayService;
@Service("tmAlertDayService")
public class TmAlertDayServiceImpl implements ITmAlertDayService{
	
	@Autowired
	private ITmAlertDayDao tmAlertDayDao;
	
	public List<TmAlertDay> findAll(){
		
		return tmAlertDayDao.findAll();
		
	}
	
	public void updateTmAlertDay(TmAlertDay tmAlertDay){
		
		tmAlertDayDao.update(tmAlertDay);
		
	}
	
	public TmAlertDay findById(Long id){
		
		return tmAlertDayDao.findById(id);
	}
}
