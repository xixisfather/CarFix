package com.selfsoft.business.service.impl;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmLianceReturnDao;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.service.ITmLianceReturnService;
@Service("tmLianceReturnService")
public class TmLianceReturnServiceImpl implements ITmLianceReturnService{

	@Autowired
	private ITmLianceReturnDao tmLianceReturnDao;
	

	public void insert(TmLianceReturn tmLianceReturn){
		tmLianceReturnDao.insert(tmLianceReturn);
	}
	
	public TmLianceReturn findById(Long id){
		return tmLianceReturnDao.findById(id);
	}
	
	public void update(TmLianceReturn tmLianceReturn){
		tmLianceReturnDao.update(tmLianceReturn);
	}
	
	public List<TmLianceReturn> findByEntity(TmLianceReturn tmLianceReturn) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLianceReturn.class);
		if(null!=tmLianceReturn){
			if(null!=tmLianceReturn.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLianceReturn.getId()));
			}
			if(null!= tmLianceReturn.getLianceReturnBill()){
				detachedCriteria.add(Restrictions.like("lianceReturnBill","%"+ tmLianceReturn.getLianceReturnBill()+"%"));
			}
		
			if(null!= tmLianceReturn.getIsConfirm()){
				detachedCriteria.add(Restrictions.eq("isConfirm",tmLianceReturn.getIsConfirm()));
			}
			
			if(StringUtils.isNotBlank(tmLianceReturn.getBeginDate())){
				detachedCriteria.add(Restrictions.ge("returnDate", Date.valueOf(tmLianceReturn.getBeginDate())));
			}
			
			if(StringUtils.isNotBlank(tmLianceReturn.getEndDate())){
				detachedCriteria.add(Restrictions.le("returnDate", Date.valueOf(tmLianceReturn.getEndDate())));
			}
		} 
		return tmLianceReturnDao.findByCriteria(detachedCriteria, tmLianceReturn);
	}
	
	/**
	 * 删除所有借进归还单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteLianceReturn(Long lianceReturnId){
		//删除入库明细表
		tmLianceReturnDao.deleteLianceReturnDetail(lianceReturnId);
		//删除入库主表
		boolean flag = tmLianceReturnDao.deleteById(lianceReturnId);
		
		return flag;
	}
}
