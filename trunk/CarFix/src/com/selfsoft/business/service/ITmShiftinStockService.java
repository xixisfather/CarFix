package com.selfsoft.business.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.selfsoft.business.model.TmShiftinStock;
import com.selfsoft.business.vo.TmShiftinStockVo;
import com.selfsoft.common.exception.MinusException;

public interface ITmShiftinStockService {

	public void insert(TmShiftinStock tmShiftinStock) throws MinusException, IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	public void update(TmShiftinStock tmShiftinStock) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, MinusException;
	
	public TmShiftinStock findById(Long id);
	
	public List<TmShiftinStockVo> getTmshiftinStockVos(Long isConfirm , TmShiftinStock tmShiftinStock, Long shiftInId);
	
	public void updateTmShiftinStock(TmShiftinStock tmShiftinStock);
	
	public boolean deleteById(Long id);
}
