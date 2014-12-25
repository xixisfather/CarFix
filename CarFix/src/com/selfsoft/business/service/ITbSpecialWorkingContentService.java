package com.selfsoft.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.selfsoft.business.model.TbSpecialWorkingContent;

public interface ITbSpecialWorkingContentService {
	
	public void insert(TbSpecialWorkingContent tbSpecialWorkingContent);
	
	public void update(TbSpecialWorkingContent tbSpecialWorkingContent);
	
	public boolean deleteById(Long id);
	
	public List<TbSpecialWorkingContent> findBySpecialId(Long specialId);
	
	public void deleteBySpecialId(Long specialId);
	
	public BigDecimal countTbSpecialWorkingContent(Long specialId);
}
