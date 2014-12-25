package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbWorkingRelation;

public interface ITbWorkingRelationService {
	
	public void insert(TbWorkingRelation tbWorkingRelation);
	
	public void update(TbWorkingRelation tbWorkingRelation);
	
	public boolean deleteById(Long id);
	
	public TbWorkingRelation findById(Long id);
	
	public List<TbWorkingRelation> findByTbWorkingRelation(TbWorkingRelation tbWorkingRelation);
	
	public List<TbWorkingRelation> findTbWorkingRelationByTbWorkingInfoId(Long tbWorkingInfoId);
	
	public List<TbWorkingRelation> findTbWorkingRelationByTbWorkingCollectionId(Long tbWorkingCollectionId);
}
