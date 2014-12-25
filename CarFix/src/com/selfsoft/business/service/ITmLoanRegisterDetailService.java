package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanRegisterDetail;

public interface ITmLoanRegisterDetailService {

	
	public List<TmLoanRegisterDetail> findByEntity(TmLoanRegisterDetail tmLoanRegisterDetail);
	
	public void insertBatchLoanDetail(TmLoanRegister tmLoanRegister,String partCol);
	
	public void updateBatchPartInfoLoan(Long loanId);
	
	public void updateBatchLoanDetail(TmLoanRegister tmLoanRegister,String partCol);
	
}
