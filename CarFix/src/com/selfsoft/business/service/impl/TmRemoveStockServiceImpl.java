package com.selfsoft.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmRemoveStockDao;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
@Service("tmRemoveStockService")
public class TmRemoveStockServiceImpl implements ITmRemoveStockService {

	@Autowired
	private ITmRemoveStockDao tmRemoveStockDao;
	
	public void insert(TmRemoveStock tmRemoveStock){
		tmRemoveStockDao.insert(tmRemoveStock);
	}
	
	public void update(TmRemoveStock tmRemoveStock){
		tmRemoveStockDao.update(tmRemoveStock);
	}
	
	public TmRemoveStock findById(Long id){
		return tmRemoveStockDao.findById(id);
	}
	
	public List<TmRemStockDetailVo> getRemStockDetVos(Long isConfirm , Long isValid ,Long removeStockId){
		return tmRemoveStockDao.getRemStockDetVos(isConfirm,isValid,removeStockId);
	}
	
	public List<TmRemStockVo> getAllRemStockVos(Long isConfirm , Long isVaild , TmRemoveStock tmRemoveStock, Long remStockId){
		return tmRemoveStockDao.getAllRemStockVos(isConfirm , isVaild , tmRemoveStock, remStockId);
	}
	
	/**
	 * 删除移库出仓单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteRemoveStock(Long removeStockId){
		//删除入库明细表
		tmRemoveStockDao.deleteTmRemoveStockDetail(removeStockId);
		//删除入库主表
		boolean flag = tmRemoveStockDao.deleteById(removeStockId);
		
		return flag;
	}
}
