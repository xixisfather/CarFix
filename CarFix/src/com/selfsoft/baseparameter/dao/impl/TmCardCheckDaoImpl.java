package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmCardCheckDao;
import com.selfsoft.baseparameter.model.TmCardCheck;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmCardCheckDao")
public class TmCardCheckDaoImpl extends BaseDaoImpl<TmCardCheck> implements ITmCardCheckDao{

}
