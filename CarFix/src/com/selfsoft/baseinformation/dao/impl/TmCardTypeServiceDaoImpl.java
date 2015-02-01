package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;
import com.selfsoft.baseinformation.dao.ITmCardTypeServiceDao;
import com.selfsoft.baseinformation.model.TmCardTypeService;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tmCardTypeServiceDao")
public class TmCardTypeServiceDaoImpl extends BaseDaoImpl<TmCardTypeService> implements ITmCardTypeServiceDao{

}
