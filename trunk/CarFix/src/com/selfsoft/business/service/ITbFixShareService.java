package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbFixShare;

public interface ITbFixShareService {

	public void insert(TbFixShare tbFixShare);
	
	public void update(TbFixShare tbFixShare);
	
	public boolean deleteById(Long id);
	
	public TbFixShare findById(Long id);
	
	public List<TbFixShare> findAll();
	
	public List<TbFixShare> findTbFixShareListByTbFixEntrustContentId(Long tbFixEntrustContentId);
	
	public List<TbFixShare> findByEntity(TbFixShare tbFixShare);
}
