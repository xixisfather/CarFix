package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITmCardTypeDao;
import com.selfsoft.baseinformation.model.TmCardType;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tmCardTypeDao")
public class TmCardTypeDaoImpl extends BaseDaoImpl<TmCardType> implements ITmCardTypeDao{

}
