package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmSpecialCarAlertDao;
import com.selfsoft.business.model.TmSpecialCarAlert;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmSpecialCarAlertDao")
public class TmSpecialCarAlertDaoImpl extends BaseDaoImpl<TmSpecialCarAlert> implements
		ITmSpecialCarAlertDao {

}
