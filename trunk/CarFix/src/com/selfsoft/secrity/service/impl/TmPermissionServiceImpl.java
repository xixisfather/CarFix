package com.selfsoft.secrity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmPermissionDao;
import com.selfsoft.secrity.model.TmPermission;
import com.selfsoft.secrity.service.ITmPermissionService;
@Service("tmPermissionService")
public class TmPermissionServiceImpl implements ITmPermissionService {

	@Autowired
	private ITmPermissionDao tmPermissionDao;
	
	public boolean insert(TmPermission tmPermission){
		return tmPermissionDao.insert(tmPermission);
	}
	
	public boolean update(TmPermission tmPermission){
		return tmPermissionDao.update(tmPermission);
	}
	
	public TmPermission findById(Long id) {
		return tmPermissionDao.findById(id);
	}
	
}
