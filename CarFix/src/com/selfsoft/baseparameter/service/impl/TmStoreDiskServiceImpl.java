package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmStoreDiskDao;
import com.selfsoft.baseparameter.model.TmStoreDisk;
import com.selfsoft.baseparameter.service.ITmStoreDiskService;
@Service("tmStoreDiskService")
public class TmStoreDiskServiceImpl implements ITmStoreDiskService{

	@Autowired
	private ITmStoreDiskDao tmStoreDiskDao;
	
	public List<TmStoreDisk> findAll(){
		
		return tmStoreDiskDao.findAll();
		
	}
	
	public void updateTmStoreDisk(TmStoreDisk tmStoreDisk){
		
		tmStoreDiskDao.update(tmStoreDisk);
		
	}
	
	public TmStoreDisk findById(Long id){
		
		return tmStoreDiskDao.findById(id);
	}
	
}
