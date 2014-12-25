package com.selfsoft.secrity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmDepartmentDao;
import com.selfsoft.secrity.model.TmDepartment;
import com.selfsoft.secrity.service.ITmDepartmentService;

@Service("tmDepartmentService")
public class TmDepartmentServiceImpl implements ITmDepartmentService{

	@Autowired
	private ITmDepartmentDao tmDepartmentDao;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmDepartmentDao.deleteById(id);
	}

	public List<TmDepartment> findAll() {
		// TODO Auto-generated method stub
		return tmDepartmentDao.findAll();
	}

	public TmDepartment findById(Long id) {
		// TODO Auto-generated method stub
		return tmDepartmentDao.findById(id);
	}

	public void insert(TmDepartment tmDepartment) {
		// TODO Auto-generated method stub
		tmDepartmentDao.insert(tmDepartment);
	}

	public void update(TmDepartment tmDepartment) {
		// TODO Auto-generated method stub
		tmDepartmentDao.update(tmDepartment);
	}

}
