package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbWorkingCollection;

public interface ITbWorkingCollectionService {
	public void insert(TbWorkingCollection tbWorkingCollection);
	
	public void update(TbWorkingCollection tbWorkingCollection);
	
	public boolean deleteById(Long id);
	
	public List<TbWorkingCollection> findAll();
	
	public List<TbWorkingCollection> findByTbWorkingCollection(TbWorkingCollection tbWorkingCollection);
	
	public TbWorkingCollection findById(Long id);
	
	public void insertRelation(TbWorkingCollection tbWorkingCollection);
	
	public void updateRelation(TbWorkingCollection tbWorkingCollection);
	
	public boolean deleteRelation(Long id);
	
	public List<TbWorkingCollection> findTbWorkingCollectionByWorkingCollectionCodeAndTmCarStationTypeId(String workingCollectionCode,Long tmCarStationTypeId);
}
