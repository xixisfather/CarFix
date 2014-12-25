package com.selfsoft.secrity.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmBackMenuDao;
import com.selfsoft.secrity.model.TmBackMenu;
import com.selfsoft.secrity.service.ITmBackMenuService;
@Service("tmBackMenuService")
public class TmBackMenuServiceImpl implements ITmBackMenuService {
	@Autowired
	private ITmBackMenuDao tmBackMenuDao;

	public boolean deleteById(Long id) {
		return false;
	}

	public TmBackMenu findById(Long id) {
		return tmBackMenuDao.findById(id);
	}

	public List<TmBackMenu> findTmBackMenu(TmBackMenu tmBackMenu) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmBackMenu.class);
		if(null != tmBackMenu){
			if(StringUtils.isNotBlank(tmBackMenu.getTreeName())){
				detachedCriteria.add(Restrictions.like("treeName", tmBackMenu.getTreeName()));
			}
			if(StringUtils.isNotBlank(tmBackMenu.getUrl())){
				detachedCriteria.add(Restrictions.like("url", tmBackMenu.getUrl()));
			}
			
		}
		return tmBackMenuDao.findByCriteria(detachedCriteria, tmBackMenu);
	}

	public List<TmBackMenu> getTmBackMenuTree(Long id) {
		List<TmBackMenu> result = new ArrayList<TmBackMenu>();
		result.add(findById(id));
		handlerTmBackMenuChildren(id, result);
		return result;
	}

	public boolean insert(TmBackMenu tmBackMenu) {
		return tmBackMenuDao.insert(tmBackMenu);
	}

	public boolean update(TmBackMenu tmBackMenu) {
		return tmBackMenuDao.update(tmBackMenu);
	}
	
	public void handlerTmBackMenuChildren(Long id,List<TmBackMenu> result){
		boolean haschildren = tmBackMenuDao.hasChildren(id);
		if(haschildren){
			List<TmBackMenu> resChildren = tmBackMenuDao.getOneLevelChildrenTmBackMenu(id);
			if(resChildren != null && resChildren.size() > 0 ) result.addAll(resChildren);
			for(TmBackMenu tr : resChildren){
				handlerTmBackMenuChildren(tr.getId() ,result);
			}
		}
	}
	

}
