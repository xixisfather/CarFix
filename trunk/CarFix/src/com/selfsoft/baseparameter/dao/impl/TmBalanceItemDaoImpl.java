package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmBalanceItemDao;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmBalanceItemDao")
public class TmBalanceItemDaoImpl extends BaseDaoImpl<TmBalanceItem> implements ITmBalanceItemDao{

}
