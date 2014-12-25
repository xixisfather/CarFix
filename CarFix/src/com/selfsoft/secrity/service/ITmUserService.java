package com.selfsoft.secrity.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.secrity.model.TmUser;

public interface ITmUserService {
	
	public List<TmUser>findAll();
	
	public TmUser findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean delete(TmUser tmUser);
	
	public boolean insert(TmUser tmUser);
	
	public boolean update(TmUser tmUser);
	
	public List<TmUser>findByTmUser(TmUser tmUser);
	
	public List<TmUser>findBySQL(TmUser tmUser);
	
	public Map<Long,String> findAllTmUserMap();

	public List<TmUser> findValidUser(TmUser tmUser);
	
	public TmUser findByUserName(String userName);
	
	public List<TmUser> findValidFixUser(TmUser tmUser);
}
