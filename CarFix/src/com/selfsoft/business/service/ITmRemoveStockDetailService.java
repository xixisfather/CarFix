package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.model.TmRemoveStockDetail;
import com.selfsoft.common.exception.MinusException;

public interface ITmRemoveStockDetailService {
	
	public List<TmRemoveStockDetail> findByEntity(TmRemoveStockDetail tmRemoveStockDetail);

	/**
	 * 批量添加移库出仓详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但出库，并及时减少配件表的库存数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchRemoveStockDetail(TmRemoveStock tmRemoveStock,String partCol) throws NumberFormatException, MinusException;
	
	public void updateBatchRemoveStockDetail(TmRemoveStock tmRemoveStock,String partCol) throws NumberFormatException, MinusException;
}
