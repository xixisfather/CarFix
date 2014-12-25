package com.selfsoft.business.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.IStStorehouseReceiverDao;
import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("stStorehouseReceiverDao")
public class StStorehouseReceiverDaoImpl extends BaseDaoImpl<StStorehouseReceiver> implements
		IStStorehouseReceiverDao {

	
	public void deleteByYearMonth(String yearStr , String mounthStr ){
		StringBuilder hql = new StringBuilder();
		hql.append("delete from StStorehouseReceiver t where 1=1 ");
		if(StringUtils.isNotBlank(yearStr)){
			hql.append(" and datepart(yy,t.createDate)=").append(yearStr);
		}
		if(StringUtils.isNotBlank(mounthStr)){
			hql.append(" and datepart(mm,t.createDate)=").append(mounthStr);
		}
		this.getHibernateTemplate().bulkUpdate(hql.toString());
	}
	
	
	
}
