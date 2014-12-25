package com.selfsoft.secrity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmBackMenuDao;
import com.selfsoft.secrity.model.TmBackMenu;
@Repository("tmBackMenuDao")
public class TmBackMenuDaoImpl extends BaseDaoImpl<TmBackMenu> implements ITmBackMenuDao {
	
	public boolean hasChildren(Long id){
		List<TmBackMenu> children = this.getOneLevelChildrenTmBackMenu(id);
		if (children != null && children.size() > 0)
			return true;
		return false;
	}
	
	public List<TmBackMenu> getOneLevelChildrenTmBackMenu(Long id) {
		String hql = "from TmBackMenu t where t.parentId = " + id;
		List<TmBackMenu> result = getHibernateTemplate().find(hql);
		return result;
	}
	
	
}
