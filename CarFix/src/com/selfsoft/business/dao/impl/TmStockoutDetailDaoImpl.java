package com.selfsoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmStockoutDetailDao;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("TmStockoutDetailDao")
public class TmStockoutDetailDaoImpl extends BaseDaoImpl<TmStockoutDetail>
		implements ITmStockoutDetailDao {
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * @param partId
	 * @param customerId
	 * @return
	 */
	public Double getSellPriceByCustomer(Long partId,Long customerId){
		
		StringBuilder hql = new StringBuilder();
		
		hql.append("select sod.price from TmStockOut so , TmStockoutDetail sod ");
		hql.append("where so.id = sod.stockInDetailId");
		hql.append(" and sod.partinfoId = ").append(partId);
		hql.append(" and so.customerBill = ").append(customerId);
		hql.append(" order by so.stockOutDate desc");
		
		List<Object> result = this.getHibernateTemplate().find(hql.toString());
		
		if(null != result && result.size() > 0)
		{
			if( result.get(0)== null )
				return 0D;
			else
				return Double.valueOf(result.get(0).toString());
		}
		
		
		return 0D;
	}
}
