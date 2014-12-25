package com.selfsoft.secrity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmResourceDao;
import com.selfsoft.secrity.model.TmResource;
import com.selfsoft.secrity.service.ITmResourceService;
@Service("tmResourceService")
public class TmResourceServiceImpl implements ITmResourceService {
	

	@Autowired
	private ITmResourceDao tmResourceDao;
	
	
	public void getTmResourceTree(Long id){
		TmResource tmResource = this.findById(id);
		handlerTree(tmResource);
	}
	
	private void handlerTree(TmResource tmResource){
		if(tmResource == null) return ;
		boolean haschildren = tmResourceDao.hasChildren(tmResource.getId());
		List<TmResource> resChildren = tmResourceDao.getOneLevelChildrenTmResource(tmResource.getId());
		tmResource.setChildren(resChildren);
		if(haschildren){
			for(TmResource tr : resChildren){
				handlerTree(tr);
			}
		}else{
			return;
		}
	}


	public boolean deleteById(Long id) {
		
		TmResource tmResource = tmResourceDao.findById(id);
		boolean delFlag = false;
		if(tmResource != null){
			TmResource fatherResource = tmResourceDao.findById(tmResource.getParentId());
			delFlag = tmResourceDao.deleteTmResourceAndChildren(id);
			if(delFlag){
				boolean haschildren = tmResourceDao.hasChildren(fatherResource.getId());
				if(haschildren)
					fatherResource.setIsLeaf(0L);
				else	
					fatherResource.setIsLeaf(1L);
				tmResourceDao.update(fatherResource);
				
				return true;
			}
		}
		
		return false;
		
	}


	public TmResource findById(Long id) {
		return tmResourceDao.findById(id);
	}


	public List<TmResource> findByTmResource(TmResource tmResource) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmResource.class);
		if(null != tmResource){
			if(StringUtils.isNotBlank(tmResource.getResourceName())){
				detachedCriteria.add(Restrictions.like("resourceName","%"+ tmResource.getResourceName()+"%"));
			}
			if(StringUtils.isNotBlank(tmResource.getResourceDesc())){
				detachedCriteria.add(Restrictions.like("resourceDesc","%"+ tmResource.getResourceDesc()+"%"));
			}
		}
		
		return tmResourceDao.findByCriteria(detachedCriteria, tmResource);
	}


	public boolean insert(TmResource tmResource) {
		//父节点
		TmResource fatherResource = tmResourceDao.findById(tmResource.getParentId());
		//父节点非叶子节点
		if(fatherResource != null ){
			fatherResource.setIsLeaf(0L);
			tmResourceDao.update(fatherResource);
		}
		tmResource.setIsLeaf(1L);
		return tmResourceDao.insert(tmResource);
	}


	public boolean update(TmResource tmResource) {
		return tmResourceDao.update(tmResource);
	}


	public boolean updateTmResourceNoValid(Long id) {
		return tmResourceDao.updateObjectNotValid(id);
	}


	public List<TmResource> getChildrenTmResource(Long id , Boolean isFather) {
		return tmResourceDao.getOneLevelChildren(id,isFather);
	}
	
	public void handlerTmResourceChildren(Long id , List<TmResource> result , int level){
		++level;
		boolean haschildren = tmResourceDao.hasChildren(id);
		if(haschildren){
			List<TmResource> resChildren = tmResourceDao.getOneLevelChildrenTmResource(id);
			if(resChildren != null && resChildren.size() > 0 ) result.addAll(resChildren);
			for(TmResource tr : resChildren){
				tr.setLevel(level);
				handlerTmResourceChildren(tr.getId() ,result,level );
			}
		}else{
		}
		
	}
	
	
	public List<TmResource> getTmResourceAllChildren(Long id){
		List<TmResource> result = new ArrayList<TmResource>();
		result.add(findById(id));
		handlerTmResourceChildren(id, result,0);
		return result;
	}
	
	
	public TmResource getRootTmResource()throws Exception{
		return tmResourceDao.getRootNode();
	}

	
	public void initAllResource(){
		
		TmResource root = new TmResource();
		root.setIsLeaf(0L);
		root.setResourceName("所有资源");
		root.setParentId(null);
		root.setResourcePath(null);
		tmResourceDao.insert(root);
		
		TmResource xxgl = new TmResource();
		xxgl.setParentId(root.getId());
		xxgl.setIsLeaf(0L);
		xxgl.setResourceName("信息管理");
		xxgl.setParentId(root.getId());
		root.setResourcePath(null);
		TmResource chgl = new TmResource();
		xxgl.setParentId(root.getId());
		xxgl.setIsLeaf(0L);
		xxgl.setResourceName("出库管理");
		xxgl.setParentId(root.getId());
		root.setResourcePath(null);
		TmResource rkgl = new TmResource();
		xxgl.setParentId(root.getId());
		xxgl.setIsLeaf(0L);
		xxgl.setResourceName("入库管理");
		xxgl.setParentId(root.getId());
		root.setResourcePath(null);
		TmResource cszc = new TmResource();
		xxgl.setParentId(root.getId());
		xxgl.setIsLeaf(0L);
		xxgl.setResourceName("优先参数设置管理");
		xxgl.setParentId(root.getId());
		root.setResourcePath(null);
	}
	
}
