package com.selfsoft.secrity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.secrity.dao.ITmCompanyDao;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.service.ITmCompanyService;

@Service("tmCompanyService")
public class TmCompanyServiceImpl implements ITmCompanyService{

	@Autowired
	private ITmCompanyDao tmCompanyDao;

	public List<TmCompany> findAll() {
		// TODO Auto-generated method stub
		return tmCompanyDao.findAll();
	}

	public void insert(TmCompany tmCompany) {
		// TODO Auto-generated method stub
		tmCompanyDao.insert(tmCompany);
	}

	public void update(TmCompany tmCompany) {
		// TODO Auto-generated method stub
		tmCompanyDao.update(tmCompany);
	}
	
	public TmCompany acquireUniqueTmCompany(){
		
		List<TmCompany> tmCompanyList = this.findAll();
		
		if(null!=tmCompanyList&&tmCompanyList.size()>0){
			
			return tmCompanyList.get(0);
		}
		
		return defaultInsert();
	}
	
	
	
	public TmCompany defaultInsert(){
		
		TmCompany tmCompany = new TmCompany();
		
		this.insert(tmCompany);
		
		return tmCompany;
	}
}
