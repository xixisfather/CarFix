package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmLostDayDao;
import com.selfsoft.baseparameter.model.TmLostDay;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLostDayDao")
public class TmLostDayDaoImpl extends BaseDaoImpl<TmLostDay> implements ITmLostDayDao{

}
