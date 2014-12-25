package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbBusinessBalanceItemDao;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbBusinessBalanceItemDao")
public class TbBusinessBalanceItemDaoImpl extends BaseDaoImpl<TbBusinessBalanceItem> implements ITbBusinessBalanceItemDao{

}
