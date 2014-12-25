package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.framework.dao.IDao;

public interface ITmLoanRegisterDao extends IDao<TmLoanRegister>{
	
	public List<TmLoanRegDetailVo> getLoanRegDetailVo(Long isConfirm,Long isReturn,Long customerId,Long loanRegId);
	
	public List<TmLoanRegVo> getloanRegVo(Long isConfirm,Long isReturn,TmLoanRegister tmLoanRegister, Long loanId);

	public void deleteLoanRegDetail(Long loanRegId);
}
