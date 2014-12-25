package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmBalanceDao;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmBalanceDao")
public class TmBalanceDaoImpl extends BaseDaoImpl<TmBalance> implements ITmBalanceDao{

}
