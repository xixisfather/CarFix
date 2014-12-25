package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbBusinessSpecialBalanceHandleFinanceDao;
import com.selfsoft.business.model.TbBusinessSpecialBalanceHandleFinance;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceHandleFinanceService;
@Service("tbBusinessSpecialBalanceHandleFinanceService")
public class TbBusinessSpecialBalanceHandleFinanceServiceImpl implements ITbBusinessSpecialBalanceHandleFinanceService{

	@Autowired
	private ITbBusinessSpecialBalanceHandleFinanceDao tbBusinessSpecialBalanceHandleFinanceDao;
	
	public boolean deleteByID(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessSpecialBalanceHandleFinanceDao.deleteById(id);
	}

	public List<TbBusinessSpecialBalanceHandleFinance> findByTbBusinessSpecialBalanceHandleFinance(
			TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbBusinessSpecialBalanceHandleFinance.class);
		
		if(null!=tbBusinessSpecialBalanceHandleFinance){
			
			if(null!=tbBusinessSpecialBalanceHandleFinance.getStaticsDateFrom()&&!"".equals(tbBusinessSpecialBalanceHandleFinance.getStaticsDateFrom())){
				
				detachedCriteria.add(Restrictions.ge("staticsDate", tbBusinessSpecialBalanceHandleFinance.getStaticsDateFrom()));
				
			}
			
			if(null!=tbBusinessSpecialBalanceHandleFinance.getStaticsDateEnd()&&!"".equals(tbBusinessSpecialBalanceHandleFinance.getStaticsDateEnd())){
				
				detachedCriteria.add(Restrictions.le("staticsDate", tbBusinessSpecialBalanceHandleFinance.getStaticsDateEnd()));
				
			}
			
			if(null!=tbBusinessSpecialBalanceHandleFinance.getStaticsDate()&&!"".equals(tbBusinessSpecialBalanceHandleFinance.getStaticsDate())){
				
				detachedCriteria.add(Restrictions.eq("staticsDate", tbBusinessSpecialBalanceHandleFinance.getStaticsDate()));
				
			}
		}
		
		return tbBusinessSpecialBalanceHandleFinanceDao.findByCriteria(detachedCriteria, tbBusinessSpecialBalanceHandleFinance);
	}

	public void insertTbBusinessSpecialBalanceHandleFinance(
			TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceHandleFinanceDao.insert(tbBusinessSpecialBalanceHandleFinance);
	}

	public void updateTbBusinessSpecialBalanceHandleFinance(
			TbBusinessSpecialBalanceHandleFinance tbBusinessSpecialBalanceHandleFinance) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceHandleFinanceDao.update(tbBusinessSpecialBalanceHandleFinance);
	}
	
	public TbBusinessSpecialBalanceHandleFinance findById(Long id){
		
		return tbBusinessSpecialBalanceHandleFinanceDao.findById(id);
	}

	public TbBusinessSpecialBalanceHandleFinance findByStaticsDate(String staticsDate){
		
		TbBusinessSpecialBalanceHandleFinance t = new TbBusinessSpecialBalanceHandleFinance();
		
		t.setStaticsDate(staticsDate);
		
		List<TbBusinessSpecialBalanceHandleFinance> list = this.findByTbBusinessSpecialBalanceHandleFinance(t);
		
		if(null!=list&&list.size()>0){
			
			return list.get(0);
		}
		
		return null;
	}
}
