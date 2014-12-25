package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLoanReturn;

public interface ITmLoanReturnService {

	public void insert(TmLoanReturn tmLoanReturn);
	
	public TmLoanReturn findById(Long id);
	
	public void update(TmLoanReturn tmLoanReturn);
	
	public List<TmLoanReturn> findByEntity(TmLoanReturn tmLoanReturn);
	
	public boolean deleteLoanReturn(Long loanReturnId);
}
