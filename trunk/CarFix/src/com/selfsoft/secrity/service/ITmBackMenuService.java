package com.selfsoft.secrity.service;

import java.util.List;

import com.selfsoft.secrity.model.TmBackMenu;

public interface ITmBackMenuService {

	public TmBackMenu findById(Long id);
	
	public List<TmBackMenu> findTmBackMenu(TmBackMenu tmBackMenu);
	
	public List<TmBackMenu> getTmBackMenuTree(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean insert(TmBackMenu tmBackMenu);
	
	public boolean update(TmBackMenu tmBackMenu);
}
