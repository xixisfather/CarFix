package com.selfsoft.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.selfsoft.business.model.TbSpecialPartContent;

public interface ITbSpecialPartContentService {
	
	public void insert(TbSpecialPartContent tbSpecialPartContent);
	
	public void update(TbSpecialPartContent tbSpecialPartContent);
	
	public boolean deleteById(Long id);
	
	public List<TbSpecialPartContent> findBySpecialId(Long specialId);
	
	public void deleteBySpecialId(Long specialId);
	
	public Double getTotalCostPriceBySpecialPartList(List<TbSpecialPartContent> specialPartContentList);
	
	public BigDecimal countTbSpecialPartContent(Long specialId);
	
	public BigDecimal countTbSpecialPartContentCost(Long specialId);
}
