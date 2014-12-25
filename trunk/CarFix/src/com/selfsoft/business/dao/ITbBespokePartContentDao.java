package com.selfsoft.business.dao;

import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.framework.dao.IDao;

public interface ITbBespokePartContentDao extends IDao<TbBespokePartContent> {

	public void deleteByEntrustId(Long entrustId);
}
