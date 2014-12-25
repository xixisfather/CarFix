package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmPartTypeDao;
import com.selfsoft.baseparameter.model.TmPartType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmPartTypeDao")
public class TmPartTypeDaoImpl extends BaseDaoImpl<TmPartType> implements ITmPartTypeDao{

}
