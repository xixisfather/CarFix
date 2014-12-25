package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmCarBodyDao;
import com.selfsoft.baseparameter.model.TmCarBody;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmCarBodyDao")
public class TmCarBodyDaoImpl extends BaseDaoImpl<TmCarBody> implements ITmCarBodyDao{

}
