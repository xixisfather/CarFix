package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TmMemberShipService;

public interface ITmMemberShipServiceService {

	public void insertTmMemberShipService(TmMemberShipService tmMemberShipService);
	
	public List<TmMemberShipService> findByMemberShipId(Long memberShipId);
	
	public void deleteByMemberShipId(Long memberShipId);
	
	public void updateTmMemberShipService(Long memberShipId,String serviceName);
	
	public void updateTmMemberShipService(Long memberShipId,String serviceName,Integer serviceCount);
}
