package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmCustomerTypeDao;
import com.selfsoft.baseparameter.model.TmCustomerType;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmCustomerTypeDao")
public class TmCustomerTypeDaoImpl extends BaseDaoImpl<TmCustomerType> implements ITmCustomerTypeDao{

}
