package com.selfsoft.business.service;

import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.common.exception.MinusException;

public interface ITmLianceReturnDetailService {
	
	public void insertBatchLianceDetail(TmLianceReturn tmLianceReturn,String partCol)throws NumberFormatException, MinusException;
	
	public void updateBathLRDetal(Long LianceReturnId )throws MinusException;
	
	public void updateBatchLianceDetail(TmLianceReturn tmLianceReturn,String partCol) throws NumberFormatException, MinusException;
}
