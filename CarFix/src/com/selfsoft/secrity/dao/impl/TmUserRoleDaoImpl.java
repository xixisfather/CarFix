package com.selfsoft.secrity.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmUserRoleDao;
import com.selfsoft.secrity.model.TmUserRole;
@Repository("tmUserRoleDao")
public class TmUserRoleDaoImpl extends BaseDaoImpl<TmUserRole> implements ITmUserRoleDao {

	public void deleteUserRole(Long userId){
		String hql = "delete from TmUserRole t where t.userId = "+userId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}

}
