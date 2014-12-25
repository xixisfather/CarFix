package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmFixTypeDao;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmFixTypeDao")
public class TmFixTypeDaoImpl extends BaseDaoImpl<TmFixType> implements ITmFixTypeDao{

}
