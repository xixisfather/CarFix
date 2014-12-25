package com.selfsoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbFixEntrustDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbFixEntrustDao")
public class TbFixEntrustDaoImpl extends BaseDaoImpl<TbFixEntrust> implements ITbFixEntrustDao{

	
	public List<TbFixEntrust> findTbMainFixEntrust(){
		StringBuilder hql = new StringBuilder();
		hql.append(" from TbFixEntrust t where not exists (select m.entrustId TbMaintainPartContent m where m.entrustId = t.id ) ");
		
		List<TbFixEntrust> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
}
