package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;

public interface ITmRemoveStockService {

	public void insert(TmRemoveStock tmRemoveStock);
	
	public void update(TmRemoveStock tmRemoveStock);
	
	public TmRemoveStock findById(Long id);
	
	public List<TmRemStockDetailVo> getRemStockDetVos(Long isConfirm , Long isValid ,Long removeStockId);
	
	public List<TmRemStockVo> getAllRemStockVos(Long isConfirm , Long isVaild , TmRemoveStock tmRemoveStock, Long remStockId);
	
	public boolean deleteRemoveStock(Long removeStockId);
}
