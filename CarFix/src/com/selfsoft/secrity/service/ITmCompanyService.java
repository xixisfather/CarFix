package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmCompany;

public interface ITmCompanyService {
	
	public void insert(TmCompany tmCompany);
	
	public void update(TmCompany tmCompany);
	
	public List<TmCompany> findAll();
	
	public TmCompany acquireUniqueTmCompany();
}
