package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TmSpecialCarAlert;

public interface ITmSpecialCarAlertService {

	public boolean deleteById(Long id);
	
	public void insert(TmSpecialCarAlert tmSpecialCarAlert);
	
	public void update(TmSpecialCarAlert tmSpecialCarAlert);
	
	public void findAll();
	
	public TmSpecialCarAlert findById(Long id);
	
	public List<TmSpecialCarAlert > findByEntity(TmSpecialCarAlert tmSpecialCarAlert);
	
	public Map<Long, String> getAllAlertRange();
	
	public Map<Long , String> getAllAlertCount();
	
	public String alertContentByCarInfoId(Long carInfoId,Long alertType);
	
	public boolean hasCarAlertContent(Long carInfoId,Long alertType);
	
	public TmSpecialCarAlert getTmSpecialCarAlertByCarId(Long carInfoId);
}
