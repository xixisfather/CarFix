package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmLianceReturn;

public interface ITmLianceReturnService {

	public void insert(TmLianceReturn tmLianceReturn);
	
	public TmLianceReturn findById(Long id);
	
	public void update(TmLianceReturn tmLianceReturn);
	
	public List<TmLianceReturn> findByEntity(TmLianceReturn tmLianceReturn); 
	
	public boolean deleteLianceReturn(Long lianceReturnId);
}
