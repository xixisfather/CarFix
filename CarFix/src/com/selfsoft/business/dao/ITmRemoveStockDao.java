package com.selfsoft.business.dao;

import java.util.List;

import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.framework.dao.IDao;

public interface ITmRemoveStockDao extends IDao<TmRemoveStock> {

	public List<TmRemStockDetailVo> getRemStockDetVos(Long isConfirm , Long isValid ,Long removeStockId);
	
	public List<TmRemStockVo> getAllRemStockVos(Long isConfirm , Long isVaild , TmRemoveStock tmRemoveStock , Long remStockId);
	
	public void deleteTmRemoveStockDetail(Long removeStockId);
}
