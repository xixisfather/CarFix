package com.selfsoft.secrity.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmWorkTypeDao;
import com.selfsoft.secrity.model.TmWorkType;

@Repository("tmWorkTypeDao")
public class TmWorkTypeDaoImpl extends BaseDaoImpl<TmWorkType> implements ITmWorkTypeDao{

}
