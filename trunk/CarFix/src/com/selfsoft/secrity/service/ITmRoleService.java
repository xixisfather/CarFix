package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmRole;

public interface ITmRoleService {

	public boolean insert(TmRole tmRole);
	
	public boolean update(TmRole tmRole);
	
	public boolean deleteById(Long id); 
	
	public List<TmRole> findByTmRole(TmRole tmRole);
	
	public TmRole findById(Long id);
	
	public List<TmRole> findAll();
}
