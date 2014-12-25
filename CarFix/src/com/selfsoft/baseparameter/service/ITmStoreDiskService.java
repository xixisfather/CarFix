package com.selfsoft.baseparameter.service;

import java.util.List;

import com.selfsoft.baseparameter.model.TmStoreDisk;

public interface ITmStoreDiskService {

	public List<TmStoreDisk> findAll();
	
	public void updateTmStoreDisk(TmStoreDisk tmStoreDisk);
	
	public TmStoreDisk findById(Long id);

}
