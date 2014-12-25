package com.selfsoft.business.dao;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.framework.dao.IDao;

public interface ITmStockInDao extends IDao<TmStockIn> {

	public void deleteStockInDetail(Long stockInId);
}
