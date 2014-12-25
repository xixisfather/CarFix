package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmStockInDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmStockInDao")
public class TmStockInDaoImpl extends BaseDaoImpl<TmStockIn> implements ITmStockInDao {

	public void deleteStockInDetail(Long stockInId){
		String hql = "delete from TmStockinDetail sd where sd.stockId = "+stockInId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
