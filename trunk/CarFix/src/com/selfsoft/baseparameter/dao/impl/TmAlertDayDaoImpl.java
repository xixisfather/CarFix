package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmAlertDayDao;
import com.selfsoft.baseparameter.model.TmAlertDay;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmAlertDayDao")
public class TmAlertDayDaoImpl extends BaseDaoImpl<TmAlertDay> implements ITmAlertDayDao{

}
