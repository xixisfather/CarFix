package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmProjectTypeDao;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmProjectTypeDao")
public class TmProjectTypeDaoImpl extends BaseDaoImpl<TmProjectType> implements ITmProjectTypeDao{

}
