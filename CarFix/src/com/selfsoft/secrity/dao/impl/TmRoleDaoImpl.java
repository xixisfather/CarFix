package com.selfsoft.secrity.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmRoleDao;
import com.selfsoft.secrity.model.TmRole;
@Repository("tmRoleDao")
public class TmRoleDaoImpl extends BaseDaoImpl<TmRole> implements ITmRoleDao {

}
