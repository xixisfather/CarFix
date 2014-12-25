package com.selfsoft.business.dao;

import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.framework.dao.IDao;

public interface ITmStockoutDetailDao extends IDao<TmStockoutDetail> {

	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * @param partId
	 * @param customerId
	 * @return
	 */
	public Double getSellPriceByCustomer(Long partId,Long customerId);
}
