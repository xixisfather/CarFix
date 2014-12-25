package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.IStPartReceiverDao;
import com.selfsoft.business.model.StPartReceiver;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("stPartReceiverDao")
public class StPartReceiverDaoImpl extends BaseDaoImpl<StPartReceiver> implements
		IStPartReceiverDao {

}
