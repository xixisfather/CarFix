package com.selfsoft.secrity.dao;

import com.selfsoft.framework.dao.IDao;
import com.selfsoft.secrity.model.TmUserRole;

public interface ITmUserRoleDao extends IDao<TmUserRole>{

	public void deleteUserRole(Long userId);
}
