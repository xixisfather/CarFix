package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.StPartReceiver;


public interface IStPartReceiverService {

public void insert(StPartReceiver stPartReceiver);
	
	public StPartReceiver findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void update(StPartReceiver stPartReceiver);
	
	public List<StPartReceiver> findByStPartReceiver(StPartReceiver stPartReceiver);
	
	public StPartReceiver getStPartReceiverCount(List<StPartReceiver> stPartReceiverList);

}
