package com.selfsoft.secrity.service;

import java.util.Date;
import java.util.List;

import com.selfsoft.secrity.model.TmSysRegedit;

public interface ITmSysRegeditService {
	
	public void insert(TmSysRegedit tmSysRegedit);
	
	public void update(TmSysRegedit tmSysRegedit);
	
	public TmSysRegedit findById(Long id);
	
	public List<TmSysRegedit> findAll();
	
	public TmSysRegedit showUniqueSysRegedit();
	
	public String getDeadDate();
	
	public void setDeadDate();
}
