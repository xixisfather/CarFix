package com.selfsoft.business.service;

import java.util.List;

import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.common.exception.MinusException;

public interface ITmStockInService {
	
	public void insert(TmStockIn tmStockIn);
	
	public TmStockIn findById(Long id);
	
	public void update(TmStockIn tmStockIn);
	
	public List<TmStockIn> findByEntity(TmStockIn tmStockIn);
	
	public void updateBathTmStockInDetail(TmStockIn tmStockIn,String partCol) throws NumberFormatException, MinusException;
	
	public boolean deleteStockIn(Long stockInId);
	
	public List<TmStockInVo> getMasterStockIn(Long stockInType,String isConfirms,TmStockIn tmStockIn);
	
	public void updateStockPanMent(Long stockInId,Long payMent);
	
	public List<TmStockInVo> getMasterAllStock(TmStockIn tmStockIn);
}
