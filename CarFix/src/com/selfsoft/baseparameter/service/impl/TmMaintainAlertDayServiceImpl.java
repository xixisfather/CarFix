package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmMaintainAlertDayDao;
import com.selfsoft.baseparameter.model.TmMaintainAlertDay;
import com.selfsoft.baseparameter.service.ITmMaintainAlertDayService;
@Service("tmMaintainAlertDayService")
public class TmMaintainAlertDayServiceImpl implements ITmMaintainAlertDayService{
	
	@Autowired
	private ITmMaintainAlertDayDao tmMaintainAlertDayDao;
	
	public List<TmMaintainAlertDay> findAll(){
		
		return tmMaintainAlertDayDao.findAll();
		
	}
	
	public void updateTmMaintainAlertDay(TmMaintainAlertDay tmMaintainAlertDay){
		
		tmMaintainAlertDayDao.update(tmMaintainAlertDay);
		
	}
	
	public TmMaintainAlertDay findById(Long id){
		
		return tmMaintainAlertDayDao.findById(id);
	}

}
