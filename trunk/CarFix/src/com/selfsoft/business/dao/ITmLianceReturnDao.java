package com.selfsoft.business.dao;

import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.framework.dao.IDao;

public interface ITmLianceReturnDao extends IDao<TmLianceReturn> {

	public void deleteLianceReturnDetail(Long lianceReturnId);
}
