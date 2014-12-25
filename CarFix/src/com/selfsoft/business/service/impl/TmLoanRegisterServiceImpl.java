package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmLoanRegisterDao;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;
@Service("tmLoanRegisterService")
public class TmLoanRegisterServiceImpl implements ITmLoanRegisterService {

	@Autowired
	private ITmLoanRegisterDao tmLoanRegisterDao;
	
	public TmLoanRegister findById(Long id) {
		return tmLoanRegisterDao.findById(id);
	}
	
	public void insert(TmLoanRegister tmLoanRegister) {
		tmLoanRegisterDao.insert(tmLoanRegister);
	}

	public void update(TmLoanRegister tmLoanRegister) {
		tmLoanRegisterDao.update(tmLoanRegister);
		
	}
	

	public List<TmLoanRegDetailVo> getLoanRegDetailVo(Long isConfirm,Long isReturn,Long customerId,Long loanRegId){
		return tmLoanRegisterDao.getLoanRegDetailVo(isConfirm,isReturn,customerId, loanRegId);
	}
	
	public List<TmLoanRegVo> getloanRegVo(Long isConfirm,Long isReturn, TmLoanRegister tmLoanRegister, Long loanId){
		return tmLoanRegisterDao.getloanRegVo(isConfirm,isReturn,tmLoanRegister,loanId);
	}
	
	/**
	 * 删除所有借出登记单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteLoanRegister(Long loanRegId){
		//删除入库明细表
		tmLoanRegisterDao.deleteLoanRegDetail(loanRegId);
		//删除入库主表
		boolean flag = tmLoanRegisterDao.deleteById(loanRegId);
		
		return flag;
	}
}
