package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TbBespokePartContent;

public interface ITbBespokePartContentService {

	public TbBespokePartContent findById(Long id);
	
	public void insert(TbBespokePartContent tbBespokePartContent);
	
	public void update(TbBespokePartContent tbBespokePartContent);
	
	public boolean deleteById(Long id);
	
	public List<TbBespokePartContent> findByEntity(TbBespokePartContent tbBespokePartContent);
	
	public void deleteByEntrustId(Long entrustId);
}
