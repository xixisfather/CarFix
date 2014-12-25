package com.selfsoft.business.dao;

import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.framework.dao.IDao;

public interface ITmLoanReturnDao extends IDao<TmLoanReturn> {

	public void deleteLoanReturnDetail(Long loanReturnId);
}
