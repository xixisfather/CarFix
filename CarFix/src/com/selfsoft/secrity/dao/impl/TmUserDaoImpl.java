package com.selfsoft.secrity.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmUserDao;
import com.selfsoft.secrity.model.TmUser;
@Repository("tmUserDao")
public class TmUserDaoImpl extends BaseDaoImpl<TmUser> implements ITmUserDao{
	
}
