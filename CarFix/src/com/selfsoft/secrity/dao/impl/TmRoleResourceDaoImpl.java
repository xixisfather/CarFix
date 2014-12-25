package com.selfsoft.secrity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmRoleResourceDao;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.model.TmRoleResource;
@Repository("tmRoleResourceDao")
public class TmRoleResourceDaoImpl extends BaseDaoImpl<TmRoleResource> implements ITmRoleResourceDao {

	public List<TmRoleResource> findTmRoleResourceByRoleId(Long id){
		String hql = "from TmRoleResource t where t.roleId = "+id;
		return this.getHibernateTemplate().find(hql);
	}

	public boolean deleteTmRoleByRoleId(Long id) {
		String hql = "delete from TmRoleResource t where t.roleId="+id;
		int rows = this.getHibernateTemplate().bulkUpdate(hql);
		if(rows >0 )
			return true;
		return false;
	}
	
	public List<TmResource> findTmResourceByRoleId(Long roleId){
		String hql = "from TmResource t where exists (select resourceId from TmRoleResource r where r.resourceId = t.id and roleId= "+roleId+" )";
		return this.getHibernateTemplate().find(hql);
	}
	

}
