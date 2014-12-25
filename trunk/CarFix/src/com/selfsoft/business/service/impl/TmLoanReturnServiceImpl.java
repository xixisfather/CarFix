package com.selfsoft.business.service.impl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmLoanReturnDao;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.service.ITmLoanReturnService;
@Service("tmLoanReturnService")
public class TmLoanReturnServiceImpl implements ITmLoanReturnService {

	@Autowired
	private ITmLoanReturnDao tmLoanReturnDao;
	
	
	public void insert(TmLoanReturn tmLoanReturn){
		tmLoanReturnDao.insert(tmLoanReturn);
	}
	
	public TmLoanReturn findById(Long id){
		return tmLoanReturnDao.findById(id);
	}
	
	public void update(TmLoanReturn tmLoanReturn){
		tmLoanReturnDao.update(tmLoanReturn);
	}
	public List<TmLoanReturn> findByEntity(TmLoanReturn tmLoanReturn) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLoanReturn.class);
		if(null!=tmLoanReturn){
			if(null!=tmLoanReturn.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLoanReturn.getId()));
			}
			if(null!= tmLoanReturn.getLoanReturnBill()){
				detachedCriteria.add(Restrictions.like("loanReturnBill","%"+ tmLoanReturn.getLoanReturnBill()+"%"));
			}
		
			if(null!= tmLoanReturn.getIsConfirm()){
				detachedCriteria.add(Restrictions.eq("isConfirm",tmLoanReturn.getIsConfirm()));
			}
			
			if(StringUtils.isNotBlank(tmLoanReturn.getBeginDate())){
				detachedCriteria.add(Restrictions.ge("returnDate",Date.valueOf(tmLoanReturn.getBeginDate())));
				
			}
			
			if(StringUtils.isNotBlank(tmLoanReturn.getEndDate())){
				detachedCriteria.add(Restrictions.le("returnDate", Date.valueOf(tmLoanReturn.getEndDate())));
			}
		} 
		return tmLoanReturnDao.findByCriteria(detachedCriteria, tmLoanReturn);
	}
	
	/**
	 * 删除所有借出归还单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteLoanReturn(Long loanReturnId){
		//删除入库明细表
		tmLoanReturnDao.deleteLoanReturnDetail(loanReturnId);
		//删除入库主表
		boolean flag = tmLoanReturnDao.deleteById(loanReturnId);
		
		return flag;
	}
}
