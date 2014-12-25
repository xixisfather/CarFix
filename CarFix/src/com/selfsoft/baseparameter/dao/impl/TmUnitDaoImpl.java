package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmUnitDao;
import com.selfsoft.baseparameter.model.TmUnit;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmUnitDao")
public class TmUnitDaoImpl extends BaseDaoImpl<TmUnit> implements ITmUnitDao{

}
