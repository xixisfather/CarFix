package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmLoanReturnDao;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLoanReturnDao")
public class TmLoanReturnDaoImpl extends BaseDaoImpl<TmLoanReturn> implements
		ITmLoanReturnDao {

	
	public void deleteLoanReturnDetail(Long loanReturnId){
		String hql = "delete from TmLoanReturnDetail t where t.tmLoanReturn.id = "+loanReturnId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
