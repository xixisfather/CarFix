package com.selfsoft.secrity.dao;

import java.util.List;

import com.selfsoft.framework.dao.IDao;
import com.selfsoft.secrity.model.TmBackMenu;

public interface ITmBackMenuDao extends IDao<TmBackMenu> {
	
	public boolean hasChildren(Long id);
	
	public List<TmBackMenu> getOneLevelChildrenTmBackMenu(Long id);
}
