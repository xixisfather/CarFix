package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.model.TmRoleResource;

public interface ITmRoleResourceService {
	
	public boolean insert(TmRoleResource tmRoleResource);
	
	public boolean update(TmRoleResource tmRoleResource);
	
	public boolean deleteById(Long id); 
	
	public List<TmRoleResource> findByTmRoleResource(TmRoleResource tmRoleResource);
	
	public TmRoleResource findById(Long id);
	
	public List<TmResource> getRoleResource(Long roleId) throws Exception;
	
	public boolean insertTmRoleResource(String topLevelIds, String levelIds , String roleId);
	
	public List<TmResource> findTmResourceByRoleId(Long roleId);
	
}
