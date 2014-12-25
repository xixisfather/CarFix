package com.selfsoft.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.StStorehouseReceiver;

public interface IStStorehouseReceiverService {

	public void insert(StStorehouseReceiver stStorehouseReceiver);
	
	public void update(StStorehouseReceiver stStorehouseReceiver);
	
	public boolean deleteById(Long id);
	
	public StStorehouseReceiver findById(Long id);
	
	public List<StStorehouseReceiver> findByStStorehouseReceiver(StStorehouseReceiver stStorehouseReceiver);
	
	public void batchInsert(Map<String, String> formMap,Date newDate);
	
	public boolean hasAdd();
	
	public void batchUpdate(String dateStr , Map<String, String> formMap);
	
	public Map<String,String> initEditStoreHouseReceiver(String DateStr);
}
