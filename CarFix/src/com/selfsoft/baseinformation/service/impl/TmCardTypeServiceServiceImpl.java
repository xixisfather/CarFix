package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITmCardTypeServiceDao;
import com.selfsoft.baseinformation.model.TmCardTypeService;
import com.selfsoft.baseinformation.service.ITmCardTypeServiceService;

@Service("tmCardTypeServiceService")
public class TmCardTypeServiceServiceImpl implements ITmCardTypeServiceService{

	@Autowired
	private ITmCardTypeServiceDao tmCardTypeServiceDao;

	
	public void insertTmCardTypeService(TmCardTypeService tmCardTypeService) {
		
		tmCardTypeServiceDao.insert(tmCardTypeService);
	}


	public List<TmCardTypeService> findByTmCardTypeServiceId(
			Long tmCardTypeServiceId) {
		
		
		List<TmCardTypeService> tmCardTypeServiceList = tmCardTypeServiceDao.findBySQL("FROM TmCardTypeService tmCardTypeService where tmCardTypeService.cardTypeId = ?",new Object[]{tmCardTypeServiceId} );
		
		return tmCardTypeServiceList;
	}


	public void deleteByTmCardTypeServiceId(Long tmCardTypeServiceId) {
		
		List<TmCardTypeService> tmCardTypeServiceList = this.findByTmCardTypeServiceId(tmCardTypeServiceId);
		
		if(null != tmCardTypeServiceList) {
			
			for(TmCardTypeService tmCardTypeService : tmCardTypeServiceList) {
				
				tmCardTypeServiceDao.deleteById(tmCardTypeService.getId());
			}
			
		}
		
	}
}
