package com.selfsoft.business.dao;

import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.framework.dao.IDao;

public interface IStStorehouseReceiverDao extends IDao<StStorehouseReceiver> {

	public void deleteByYearMonth(String yearStr , String mounthStr );
}
