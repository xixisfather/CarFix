package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITmMemberShipServiceDao;
import com.selfsoft.baseinformation.model.TmMemberShipService;
import com.selfsoft.baseinformation.service.ITmMemberShipServiceService;

@Service("tmMemberShipServiceService")
public class TmMemberShipServiceServiceImpl implements ITmMemberShipServiceService{

	@Autowired
	private ITmMemberShipServiceDao tmMemberShipServiceDao;

	
	public void insertTmMemberShipService(TmMemberShipService tmMemberShipService) {
		
		tmMemberShipServiceDao.insert(tmMemberShipService);
	}


	public List<TmMemberShipService> findByMemberShipId(
			Long memberShipId) {
		
		
		List<TmMemberShipService> tmMemberShipServiceList = tmMemberShipServiceDao.findBySQL("FROM TmMemberShipService tmMemberShipService where tmMemberShipService.memberShipId = ?",new Object[]{memberShipId} );
		
		return tmMemberShipServiceList;
	}


	public void deleteByMemberShipId(Long memberShipId) {
		
		List<TmMemberShipService> tmMemberShipServiceList = this.findByMemberShipId(memberShipId);
		
		if(null != tmMemberShipServiceList) {
			
			for(TmMemberShipService tmMemberShipService : tmMemberShipServiceList) {
				
				tmMemberShipServiceDao.deleteById(tmMemberShipService.getId());
			}
			
		}
		
	}
	
	public void updateTmMemberShipService(Long memberShipId,String serviceName) {
		
		List<TmMemberShipService> tmMemberShipServiceList = this.findByMemberShipId(memberShipId);
		
		if(null != tmMemberShipServiceList) {
			
			for(TmMemberShipService tmMemberShipService : tmMemberShipServiceList) {
				
				if(tmMemberShipService.getServiceName().equals(serviceName)&&tmMemberShipService.getServiceCount() > 0) {
					
					tmMemberShipService.setServiceCount(tmMemberShipService.getServiceCount()-1);
					
					tmMemberShipServiceDao.update(tmMemberShipService);
					
					
				}
				
				
			}
			
		}
	}
	
	
	public void updateTmMemberShipService(Long memberShipId,String serviceName,Integer serviceCount) {
		
		List<TmMemberShipService> tmMemberShipServiceList = this.findByMemberShipId(memberShipId);
		
		if(null != tmMemberShipServiceList) {
			
			for(TmMemberShipService tmMemberShipService : tmMemberShipServiceList) {
				
				if(tmMemberShipService.getServiceName().equals(serviceName)&&tmMemberShipService.getServiceCount() > 0) {
					
					tmMemberShipService.setServiceCount(serviceCount);
					
					tmMemberShipServiceDao.update(tmMemberShipService);
					
					
				}
				
				
			}
			
		}
	}

}
