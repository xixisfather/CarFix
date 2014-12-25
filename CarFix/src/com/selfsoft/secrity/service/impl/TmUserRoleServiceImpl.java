package com.selfsoft.secrity.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmUserRoleDao;
import com.selfsoft.secrity.model.TmUserRole;
import com.selfsoft.secrity.service.ITmUserRoleService;
@Service("tmUserRoleService")
public class TmUserRoleServiceImpl implements ITmUserRoleService {
	@Autowired
	private ITmUserRoleDao tmUserRoleDao;
	
	public Long getUserRoleRelationByUserId(Long userId) {
		TmUserRole tur = new TmUserRole();
		tur.setUserId(userId);
		List<TmUserRole> roleList = this.findByTmUserRole(tur);
		if(roleList != null && roleList.size() >0 )
			return roleList.get(0).getRoleId();
		return null;
	}
	
	public List<TmUserRole> findByTmUserRole(TmUserRole tmUserRole){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmUserRole.class);
		if(null != tmUserRole){
			if(tmUserRole.getRoleId() != null)
				detachedCriteria.add(Restrictions.eq("roleId", tmUserRole.getRoleId()));
			if(tmUserRole.getUserId() != null)
				detachedCriteria.add(Restrictions.eq("userId", tmUserRole.getUserId()));
		}
		
		return tmUserRoleDao.findByCriteria(detachedCriteria, tmUserRole);
	}

	public boolean update(TmUserRole tmUserRole) {
		return tmUserRoleDao.update(tmUserRole);
	}

	public TmUserRole findById(Long id) {
		return tmUserRoleDao.findById(id);
	}
	
	public boolean  insert(TmUserRole tmUserRole){
		return tmUserRoleDao.insert(tmUserRole);
	}
	
	public TmUserRole findTmUserRoleByUserIdRoleId(Long roleId ,Long userId){
		TmUserRole tmUserRole = new TmUserRole();
		tmUserRole.setRoleId(roleId);
		tmUserRole.setUserId(userId);
		List<TmUserRole> roleList =  findByTmUserRole(tmUserRole);
		if(roleList != null && roleList.size() >0 )
			return roleList.get(0);
		return null;
	}

}
