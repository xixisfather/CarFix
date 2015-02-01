package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;
import com.selfsoft.baseinformation.dao.ITmMemberShipServiceDao;
import com.selfsoft.baseinformation.model.TmMemberShipService;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tmMemberShipServiceDao")
public class TmMemberShipServiceDaoImpl extends BaseDaoImpl<TmMemberShipService> implements ITmMemberShipServiceDao{

}
