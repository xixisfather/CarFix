package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmLoanReturnDetailDao;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLoanReturnDetailDao")
public class TmLoanReturnDetailDaoImpl extends BaseDaoImpl<TmLoanReturnDetail> implements
		ITmLoanReturnDetailDao {

}
