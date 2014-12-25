package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbReceiveFreeDao;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbReceiveFreeDao")
public class TbReceiveFreeDaoImpl extends BaseDaoImpl<TbReceiveFree> implements ITbReceiveFreeDao{

}
