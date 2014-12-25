package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmDepartment;

public interface ITmDepartmentService {
	
	public TmDepartment findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmDepartment tmDepartment);
	
	public void update(TmDepartment tmDepartment);
	
	public List<TmDepartment>findAll();
}
