package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbReturnVisit;

public interface ITbReturnVisitService {
	
	public List<TbReturnVisit> findByTbReturnVisit(TbReturnVisit tbReturnVisit);
	
	public void insertTbReturnVisit(TbReturnVisit tbReturnVisit);
	
	public void updateTbReturnVisit(TbReturnVisit tbReturnVisit);
	
	public TbReturnVisit findById(Long id);
	
	public boolean deleteById(Long id);
}
