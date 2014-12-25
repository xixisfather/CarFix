package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmCardCheckDao;
import com.selfsoft.baseparameter.model.TmCardCheck;
import com.selfsoft.baseparameter.service.ITmCardCheckService;
@Service("tmCardCheckService")
public class TmCardCheckServiceImpl implements ITmCardCheckService{

	@Autowired
	private ITmCardCheckDao tmCardCheckDao;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmCardCheckDao.deleteById(id);
	}

	public List<TmCardCheck> findAll() {
		// TODO Auto-generated method stub
		return tmCardCheckDao.findAll();
	}

	public TmCardCheck findById(Long id) {
		// TODO Auto-generated method stub
		return tmCardCheckDao.findById(id);
	}

	public void insert(TmCardCheck tmCardCheck) {
		// TODO Auto-generated method stub
		tmCardCheckDao.insert(tmCardCheck);
	}

	public void update(TmCardCheck tmCardCheck) {
		// TODO Auto-generated method stub
		tmCardCheckDao.update(tmCardCheck);
	}
}
