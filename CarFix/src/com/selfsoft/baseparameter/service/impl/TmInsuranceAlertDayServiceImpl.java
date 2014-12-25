package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmInsuranceAlertDayDao;
import com.selfsoft.baseparameter.model.TmInsuranceAlertDay;
import com.selfsoft.baseparameter.service.ITmInsuranceAlertDayService;
@Service("tmInsuranceAlertDayService")
public class TmInsuranceAlertDayServiceImpl implements ITmInsuranceAlertDayService{
	
	@Autowired
	private ITmInsuranceAlertDayDao tmInsuranceAlertDayDao;
	
	public List<TmInsuranceAlertDay> findAll(){
		
		return tmInsuranceAlertDayDao.findAll();
		
	}
	
	public void updateTmInsuranceAlertDay(TmInsuranceAlertDay tmInsuranceAlertDay){
		
		tmInsuranceAlertDayDao.update(tmInsuranceAlertDay);
		
	}
	
	public TmInsuranceAlertDay findById(Long id){
		
		return tmInsuranceAlertDayDao.findById(id);
	}

}
