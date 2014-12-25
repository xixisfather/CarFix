package com.selfsoft.secrity.dao;

import java.util.List;

import com.selfsoft.framework.dao.IDao;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.model.TmRoleResource;

public interface ITmRoleResourceDao extends IDao<TmRoleResource>{
	
	/**
	 * 根据角色Id获取资源关系
	 * @param id
	 * @return
	 */
	public List<TmRoleResource> findTmRoleResourceByRoleId(Long id);
	
	/**
	 * 根据角色id删除对应资源
	 * @param id
	 * @return
	 */
	public boolean deleteTmRoleByRoleId(Long id);
	
	/**
	 * 根据角色Id获取资源内容
	 * @param roleId
	 * @return
	 */
	public List<TmResource> findTmResourceByRoleId(Long roleId);
}
