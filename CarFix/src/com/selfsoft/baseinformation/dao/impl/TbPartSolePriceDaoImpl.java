package com.selfsoft.baseinformation.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbPartSolePriceDao;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbPartSolePriceDao")
public class TbPartSolePriceDaoImpl extends BaseDaoImpl<TbPartSolePrice> implements ITbPartSolePriceDao  {

	public void deletePartSoleByPartInfo(Long partInfoId){
		String hql = "delete from TbPartSolePrice t where t.partInfoId = "+partInfoId;
		
		Query query = this.getSession(false).createQuery(hql);
		
		query.executeUpdate();
		
	}
}
