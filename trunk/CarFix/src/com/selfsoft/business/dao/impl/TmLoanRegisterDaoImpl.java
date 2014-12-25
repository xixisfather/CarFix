package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmLoanRegisterDao;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegDetailVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLoanRegisterDao")
public class TmLoanRegisterDaoImpl extends BaseDaoImpl<TmLoanRegister> implements ITmLoanRegisterDao {

	public List<TmLoanRegDetailVo> getLoanRegDetailVo(Long isConfirm,Long isReturn,Long customerId,Long loanRegId){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmLoanRegDetailVo ");
		hql.append("(lr.id,lrd.id,lr.loanBill,lr.loanDate , pi.partCode,pi.partName,pi.tmUnit.unitName,lrd.loanQuantity,lrd.loanPrice,lrd.oldCostPrice,lrd.returnQuantity,pi.tmStoreHouse.houseName,pi.costPrice,pi.id,pi.storeLocation) ");
		hql.append("from TmLoanRegisterDetail lrd , TmLoanRegister lr , TbPartInfo pi  ");
		hql.append("where lrd.loanId = lr.id and pi.id = lrd.tbPartInfo.id ");
		if(customerId != null)
			hql.append(" and lr.customerId ="+customerId);
		if(loanRegId != null)
			hql.append(" and lr.id ="+loanRegId);
		if(isConfirm != null)
			hql.append(" and lr.isConfirm = "+isConfirm);
		if(isReturn != null)
			hql.append(" and lr.isReturn = "+isReturn);
		
		List<TmLoanRegDetailVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}


	public List<TmLoanRegVo> getloanRegVo(Long isConfirm,Long isReturn , TmLoanRegister tmLoanRegister , Long loanId){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmLoanRegVo ");
		hql.append("(lr.id,lr.loanBill,lr.loanDate,lr.userId,lr.createDate,lr.totalQuantity,lr.totalPrice,");
		hql.append("lr.customerId,t.customerCode,t.customerName,u.userRealName) ");
		hql.append("from TmLoanRegister lr , TbCustomer t , TmUser u ");
		hql.append("where lr.customerId = t.id and lr.userId = u.id ");
		if(tmLoanRegister != null){
			if(StringUtils.isNotBlank(tmLoanRegister.getLoanBill()))
				hql.append(" and lr.loanBill like '%").append(tmLoanRegister.getLoanBill()).append("%'");
			if(tmLoanRegister.getCustomerId() != null)
				hql.append(" and lr.customerId = "+tmLoanRegister.getCustomerId());
			if(StringUtils.isNotBlank(tmLoanRegister.getBeginDate())){
				hql.append(" and lr.loanDate >= '").append(tmLoanRegister.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmLoanRegister.getEndDate())){
				hql.append(" and lr.loanDate <= '").append(tmLoanRegister.getEndDate()).append("'");
			}
			
		}
		if(isConfirm != null)
			hql.append(" and lr.isConfirm = "+isConfirm);
		if(isReturn != null)
			hql.append(" and lr.isReturn = "+isReturn);
		if(loanId != null)
			hql.append(" and lr.id = "+loanId);
		hql.append(" order by lr.id desc");
		List<TmLoanRegVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	public void deleteLoanRegDetail(Long loanRegId){
		String hql = "delete from TmLoanRegisterDetail t where t.loanId = "+loanRegId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
