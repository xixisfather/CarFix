package com.selfsoft.baseparameter.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.selfsoft.baseparameter.model.TmBalanceItem;

public interface ITmBalanceItemService {
	
	public void insert(TmBalanceItem tmBalanceItem);
	
	public void update(TmBalanceItem tmBalanceItem);
	
	public boolean deleteById(Long id);
	
	public List<TmBalanceItem> findAll();
	
	public List<TmBalanceItem> findByTmBalanceItem(TmBalanceItem tmBalanceItem);
	
	public TmBalanceItem findById(Long id);
	
	public Map<Long,String> findAllTmBalanceItemMap();
	
	public List<Integer> formulaCalculateByFormula(String formula,Map map);
	
	public List<TmBalanceItem> findTmBalanceItemByTmBalanceId(Long tmBalanceId);
	
	public Map<Long,String> findTmBalanceItemMapByTmBalanceId(Long tmBalanceId);
	
	public TmBalanceItem findTmBalanceItemByTmBalanceIdAndTmBalanceItemName(Long tmBalanceId,String tmBalanceItemName);

	public List<Object> formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long tmBalanceId,String tmBalanceItemName,Map map);
	
	public TmBalanceItem findTmBalanceItemByTmBalanceItemCode(String itemCode);
}
