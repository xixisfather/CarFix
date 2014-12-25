package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmStoreDiskDao;
import com.selfsoft.baseparameter.model.TmStoreDisk;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tmStoreDiskDao")
public class TmStoreDiskDaoImpl extends BaseDaoImpl<TmStoreDisk> implements ITmStoreDiskDao {

}
