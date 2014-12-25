package com.selfsoft.baseparameter.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmCarBody;

public interface ITmCarBodyService {
	public TmCarBody findById(Long id);
	
	public boolean deleteById(Long id);
	
	public void insert(TmCarBody tmCarBody);
	
	public void update(TmCarBody tmCarBody);
	
	public List<TmCarBody>findAll();
	
	public Map<Long,String>findAllTmCarBodyMap();
	
	public List<TmCarBody> findTmCarBodyByBodyName(String bodyName);
}
