package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmCarModelTypeDao;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmCarModelTypeDao")
public class TmCarModelTypeDaoImpl extends BaseDaoImpl<TmCarModelType> implements ITmCarModelTypeDao{

}
