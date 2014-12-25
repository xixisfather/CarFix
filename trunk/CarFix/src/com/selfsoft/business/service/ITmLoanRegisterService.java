package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;

public interface ITmLoanRegisterService {

	public TmLoanRegister findById(Long id);
	
	public void insert(TmLoanRegister tmLoanRegister);

	public void update(TmLoanRegister tmLoanRegister);
	
	public List<TmLoanRegDetailVo> getLoanRegDetailVo(Long isConfirm,Long isReturn,Long customerId,Long loanRegId);
	
	public List<TmLoanRegVo> getloanRegVo(Long isConfirm,Long isReturn, TmLoanRegister tmLoanRegister, Long loanId);
	
	public boolean deleteLoanRegister(Long loanRegId);
}
