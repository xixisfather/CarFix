package com.selfsoft.baseinformation.dao;

import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.framework.dao.IDao;

public interface ITbPartSolePriceDao extends IDao<TbPartSolePrice> {

	public void deletePartSoleByPartInfo(Long partInfoId);
}
