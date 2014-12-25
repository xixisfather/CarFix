package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbCarStationWorkingRelation;

public interface ITbCarStationWorkingRelationService {
	
	public void insert(TbCarStationWorkingRelation tbCarStationWorkingRelation);
	
	public void update(TbCarStationWorkingRelation tbCarStationWorkingRelation);
	
	public boolean delete(Long id);
	
	public TbCarStationWorkingRelation findById(Long id);
	
	public List<TbCarStationWorkingRelation>findByTbCarStationWorkingRelation(TbCarStationWorkingRelation tbCarStationWorkingRelation);
}
