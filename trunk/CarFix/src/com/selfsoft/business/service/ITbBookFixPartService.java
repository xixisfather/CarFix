package com.selfsoft.business.service;

import java.util.List;
import java.util.Map;

import com.selfsoft.business.model.TbBookFixPart;

public interface ITbBookFixPartService {
	
	public void insert(TbBookFixPart tbBookFixPart);
	
	public void update(TbBookFixPart tbBookFixPart);
	
	public boolean deleteById(Long id);
	
	public TbBookFixPart findById(Long id);
	
	public List<TbBookFixPart> findTbBookFixPartListByTbBookId(Long tbBookId);
	
	public Map<String,String>  findTbBookFixPartMapByTbBookId(Long tbBookId);
}
