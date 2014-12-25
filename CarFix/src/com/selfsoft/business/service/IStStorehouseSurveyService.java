package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.StStorehouseSurvey;

public interface IStStorehouseSurveyService {

	public void insert(StStorehouseSurvey stStorehouseSurvey);
	
	public StStorehouseSurvey findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void update(StStorehouseSurvey stStorehouseSurvey);
	
	public List<StStorehouseSurvey> findByStStorehouseSurvey(StStorehouseSurvey stStorehouseSurvey);
}
