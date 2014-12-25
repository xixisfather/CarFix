package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmSoleTypeDao;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmSoleTypeDao")
public class TmSoleTypeDaoImpl extends BaseDaoImpl<TmSoleType> implements ITmSoleTypeDao{

}
