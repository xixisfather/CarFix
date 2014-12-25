package com.selfsoft.secrity.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmCompanyDao;
import com.selfsoft.secrity.model.TmCompany;

@Repository("tmCompanyDao")
public class TmCompanyDaoImpl extends BaseDaoImpl<TmCompany> implements ITmCompanyDao{

}
