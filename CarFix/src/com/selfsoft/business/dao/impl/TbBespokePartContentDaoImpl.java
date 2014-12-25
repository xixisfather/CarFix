package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbBespokePartContentDao;
import com.selfsoft.business.model.TbBespokePartContent;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbBespokePartContentDao")
public class TbBespokePartContentDaoImpl extends BaseDaoImpl<TbBespokePartContent> implements
		ITbBespokePartContentDao {



	public void deleteByEntrustId(Long entrustId){
		String hql = "delete from TbBespokePartContent t where t.tbFixEntrust.id = "+entrustId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
