package com.selfsoft.secrity.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmRoleDao;
import com.selfsoft.secrity.dao.ITmRoleResourceDao;
import com.selfsoft.secrity.model.TmRole;
import com.selfsoft.secrity.service.ITmRoleService;
@Service("tmRoleService")
public class TmRoleServiceImpl implements ITmRoleService {

	@Autowired
	private ITmRoleDao tmRoleDao;
	@Autowired
	private ITmRoleResourceDao tmRoleResourceDao;
	
	public boolean deleteById(Long id) {
		tmRoleResourceDao.deleteTmRoleByRoleId(id);
		return tmRoleDao.deleteById(id);
	}

	public TmRole findById(Long id) {
		return tmRoleDao.findById(id);
	}

	public List<TmRole> findByTmRole(TmRole tmRole) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmRole.class);
		if(null != tmRole){
			if(StringUtils.isNotBlank(tmRole.getRoleCode())){
				detachedCriteria.add(Restrictions.eq("roleCode", tmRole.getRoleCode()));
			}
			if(StringUtils.isNotBlank(tmRole.getRoleName())){
				detachedCriteria.add(Restrictions.like("roleName", tmRole.getRoleName()));
			}
			if(StringUtils.isNotBlank(tmRole.getRoleNote())){
				detachedCriteria.add(Restrictions.like("roleNote", tmRole.getRoleNote()));
			}
		}
		return tmRoleDao.findByCriteria(detachedCriteria, tmRole);
	}

	public boolean insert(TmRole tmRole) {
		TmRole queryRole = new TmRole();
		queryRole.setRoleCode(tmRole!=null?tmRole.getRoleCode():null);
		List<TmRole> roles = this.findByTmRole(queryRole);
		if(roles != null && roles.size()>0)
			return false;
		return tmRoleDao.insert(tmRole);
	}

	public boolean update(TmRole tmRole) {
		return tmRoleDao.update(tmRole);
	}
	
	public List<TmRole> findAll(){
		return tmRoleDao.findAll();
	}

	

}
