package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmResource;

public interface ITmResourceService {

	public boolean insert(TmResource tmResource);
	
	public boolean update(TmResource tmResource);
	
	public boolean deleteById(Long id); 
	
	public List<TmResource> findByTmResource(TmResource tmResource);
	
	public TmResource findById(Long id);
	
	public boolean updateTmResourceNoValid(Long id);
	
	public void getTmResourceTree(Long id);
	
	public List<TmResource> getChildrenTmResource(Long id, Boolean isFather);
	
	public List<TmResource> getTmResourceAllChildren(Long id);
	/**
	 * 得到资源表根节点
	 * @return
	 * @throws Exception
	 */
	public TmResource getRootTmResource() throws Exception;
	
}
