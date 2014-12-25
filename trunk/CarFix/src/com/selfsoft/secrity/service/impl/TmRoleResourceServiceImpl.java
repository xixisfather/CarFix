package com.selfsoft.secrity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmPermissionDao;
import com.selfsoft.secrity.dao.ITmResourceDao;
import com.selfsoft.secrity.dao.ITmRoleDao;
import com.selfsoft.secrity.dao.ITmRoleResourceDao;
import com.selfsoft.secrity.model.TmPermission;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.model.TmRoleResource;
import com.selfsoft.secrity.service.ITmResourceService;
import com.selfsoft.secrity.service.ITmRoleResourceService;
@Service("tmRoleResourceService")
public class TmRoleResourceServiceImpl implements ITmRoleResourceService {

	@Autowired
	private ITmResourceService tmResourceService;
	@Autowired
	private ITmRoleResourceDao tmRoleResourceDao;
	@Autowired
	private ITmResourceDao tmResourceDao;
	@Autowired
	private ITmPermissionDao tmPermissionDao;
	
	public List<TmResource> getRoleResource(Long roleId) throws Exception{
		//得到角色权限列表
		List<TmRoleResource>  roleResourceList = tmRoleResourceDao.findTmRoleResourceByRoleId(roleId);
		//得到所有权限列表
		Long rootId = tmResourceService.getRootTmResource().getId();
		List<TmResource> allResourceList = tmResourceService.getTmResourceAllChildren(rootId);
		
		if(allResourceList.size() == 1)
			this.initTm_Resource();		//初始化资源表
		
		for(TmResource resource : allResourceList){
			for(TmRoleResource roleResource : roleResourceList){
				if(resource.getId().equals(roleResource.getResourceId())){
					resource.setChecked(true);
				}
			}
		}
		
		return allResourceList;
	}

	public boolean deleteById(Long id) {
		return tmRoleResourceDao.deleteById(id);
	}

	public TmRoleResource findById(Long id) {
		return tmRoleResourceDao.findById(id);
	}

	public List<TmRoleResource> findByTmRoleResource(TmRoleResource tmRoleResource) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmRoleResource.class);
		if(null != tmRoleResource){
			if(tmRoleResource.getResourceId()!= null){
				detachedCriteria.add(Restrictions.eq("resourceId", tmRoleResource.getResourceId()));
			}
			if(tmRoleResource.getRoleId() != null){
				detachedCriteria.add(Restrictions.eq("roleId", tmRoleResource.getRoleId()));
			}
		}
		return tmRoleResourceDao.findByCriteria(detachedCriteria, tmRoleResource);
	}

	public boolean insert(TmRoleResource tmRoleResource) {
		return tmRoleResourceDao.insert(tmRoleResource);
	}

	public boolean update(TmRoleResource tmRoleResource) {
		return tmRoleResourceDao.update(tmRoleResource);
	}

	public boolean insertTmRoleResource(String topLevelIds, String levelIds,
			String roleId) {
		
		tmRoleResourceDao.deleteTmRoleByRoleId(new Long(roleId));
		
		String[] topLevelIdArray = null; 
		String[] levelIdArrray = null;
		topLevelIdArray = StringUtils.isNotBlank(topLevelIds) ? topLevelIds.split(","):null;
		levelIdArrray = StringUtils.isNotBlank(levelIds) ? levelIds.split(","):null;
		if(null != topLevelIdArray){
			for(String resourceId : topLevelIdArray){
				TmRoleResource trr = new TmRoleResource();
				trr.setRoleId(new Long(roleId));
				trr.setResourceId(new Long(resourceId.split(";")[0]));
				tmRoleResourceDao.insert(trr);
				
				/**保存资源权限**/
				/*
				if(resourceId.split(";").length <2) continue;
				for (String permissionType : resourceId.split(";")[1].split(":") ) {
					TmPermission permission = new TmPermission();
					permission.setPermissionType(permissionType);
					permission.setTmRoleResource(trr);
					tmPermissionDao.insert(permission);
				}
				*/
				/**保存资源权限**/
			}
		}
		
		if(null != levelIdArrray){
			for(String resourceId : levelIdArrray){
				TmRoleResource trr = new TmRoleResource();
				trr.setRoleId(new Long(roleId));
				trr.setResourceId(new Long(resourceId.split(";")[0]));
				tmRoleResourceDao.insert(trr);
				
				/**保存资源权限**/
				/*
				if(resourceId.split(";").length <2) continue;
				for (String permissionType : resourceId.split(";")[1].split(":") ) {
					TmPermission permission = new TmPermission();
					permission.setPermissionType(permissionType);
					permission.setTmRoleResource(trr);
					tmPermissionDao.insert(permission);
				}
				*/
				/**保存资源权限**/
			}
		}
		return true;
	}
	
	public List<TmResource> findTmResourceByRoleId(Long roleId){
		
		List<TmResource> resourceList = tmRoleResourceDao.findTmResourceByRoleId(roleId);
		List<TmResource> result = new ArrayList<TmResource>();
		
		for(TmResource res : resourceList){
			Long parentId = res.getParentId();
			boolean flag =  false;
			for(TmResource chi : resourceList){
				if(chi.getId().equals(parentId)){
					flag = true;
				}
			}
			if(!flag){
				TmResource cloneTm = (TmResource) res.clone();
				cloneTm.setParentId(0L);
				result.add(cloneTm);
			}else{
				result.add(res);
			}
		}
		//虚拟节点
		TmResource root = new TmResource();
		root.setResourceName("资源虚拟根节点");
		root.setResourceDesc("该节点为隐藏节点");
		root.setIsLeaf(0L);
		root.setId(0L);
		result.add(root);
		
		return result;
	}
	
	/**
	 * 初始化资源表，第一次默认添加一条记录最为根节点。
	 */
	private void initTm_Resource(){
		TmResource root = new TmResource();
		root.setId(1L);
		root.setParentId(null);
		root.setResourceName("所有资源");
		root.setResourceDesc(null);
		root.setResourcePath(null);
		root.setIsLeaf(0L);
		tmResourceDao.insert(root);
	}

}
