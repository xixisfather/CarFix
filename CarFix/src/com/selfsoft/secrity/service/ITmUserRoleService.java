package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmUserRole;

public interface ITmUserRoleService {
	
	public Long getUserRoleRelationByUserId(Long userId);
	
	public List<TmUserRole> findByTmUserRole(TmUserRole tmUserRole);
	
	public boolean update(TmUserRole tmUserRole);
	
	public TmUserRole findById (Long id);
	
	public TmUserRole findTmUserRoleByUserIdRoleId(Long roleId ,Long userId);
	
	public boolean  insert(TmUserRole tmUserRole);
	
}
