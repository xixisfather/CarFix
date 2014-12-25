package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbCustomerDao;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbCustomerDao")
public class TbCustomerDaoImpl extends BaseDaoImpl<TbCustomer> implements ITbCustomerDao{

}
