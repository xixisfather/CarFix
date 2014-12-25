package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmLianceReturnDao;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLianceReturnDao")
public class TmLianceReturnDaoImpl extends BaseDaoImpl<TmLianceReturn> implements ITmLianceReturnDao{

	public void deleteLianceReturnDetail(Long lianceReturnId){
		String hql = "delete from TmLianceReturnDetail t where t.tmLianceReturn.id = "+lianceReturnId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
