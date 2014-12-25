package com.selfsoft.secrity.dao;

import java.util.List;

import com.selfsoft.framework.dao.IDao;
import com.selfsoft.secrity.model.TmResource;

public interface ITmResourceDao extends IDao<TmResource> {
	
	
	public List<TmResource> getOneLevelChildrenTmResource(Long id);
	
	public boolean hasChildren(Long id);
	
	public List<TmResource> getOneLevelChildren(Long id, Boolean isFather);
	
	public Boolean deleteTmResourceAndChildren(Long id);
	
	/**
	 * 得到资源表根节点
	 * @return
	 * @throws Exception
	 */
	public TmResource getRootNode() throws Exception;
}
