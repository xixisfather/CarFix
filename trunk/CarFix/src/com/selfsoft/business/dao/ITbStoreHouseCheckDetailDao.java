package com.selfsoft.business.dao;

import com.selfsoft.business.model.TbStoreHouseCheckDetail;
import com.selfsoft.framework.dao.IDao;

public interface ITbStoreHouseCheckDetailDao extends IDao<TbStoreHouseCheckDetail> {

	public void deleteCheckDetail(Long checkId);
}
