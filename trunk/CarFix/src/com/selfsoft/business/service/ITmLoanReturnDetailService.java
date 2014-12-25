package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.common.exception.MinusException;

public interface ITmLoanReturnDetailService {

	public List<TmLoanReturnDetail> findByEntity(TmLoanReturnDetail tmLoanReturnDetail);
	
	/**
	 * 批量添加借出归还详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时减少配件表的借出数量
	 */
	public void insertBatchloanDetail(TmLoanReturn tmLoanReturn,String partCol)throws NumberFormatException, MinusException;
	/**
	 * 更新借出归还单下的所有明细 相关联的配件借出量
	 * @param loanReturnId
	 * @throws MinusException 
	 */
	public void updateBathLRDetal(Long loanReturnId ) throws MinusException;
	
	
	
	public void updateBatchloanDetail(TmLoanReturn tmLoanReturn,String partCol) throws NumberFormatException, MinusException;
}
