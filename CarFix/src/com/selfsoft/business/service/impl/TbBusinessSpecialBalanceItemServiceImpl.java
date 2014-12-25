package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.business.dao.ITbBusinessSpecialBalanceItemDao;
import com.selfsoft.business.model.TbBusinessSpecialBalanceItem;
import com.selfsoft.business.model.TbSpecialWorkingContent;
import com.selfsoft.business.service.ITbBusinessSpecialBalanceItemService;
@Service("tbBusinessSpecialBalanceItemService")
public class TbBusinessSpecialBalanceItemServiceImpl implements ITbBusinessSpecialBalanceItemService{

	@Autowired
	private ITbBusinessSpecialBalanceItemDao tbBusinessSpecialBalanceItemDao;
	
	@Autowired
	private ITmBalanceItemService tmBalanceItemService;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBusinessSpecialBalanceItemDao.deleteById(id);
	}

	public void insert(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceItemDao.insert(tbBusinessSpecialBalanceItem);
	}

	public void update(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem) {
		// TODO Auto-generated method stub
		tbBusinessSpecialBalanceItemDao.update(tbBusinessSpecialBalanceItem);
	}
	
	public List<TbBusinessSpecialBalanceItem> findBySpecialId(Long specialId){
		
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = tbBusinessSpecialBalanceItemDao.findBySQL("SELECT tbBusinessSpecialBalanceItem FROM TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem where tbBusinessSpecialBalanceItem.tbBusinessSpecialBalance.id=?",new Object[]{specialId});
		
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemListReturn = new ArrayList<TbBusinessSpecialBalanceItem>();
		
		if(null!=tbBusinessSpecialBalanceItemList&&tbBusinessSpecialBalanceItemList.size()>0){
			
			for(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList){
				
				TmBalanceItem tmBalanceItem = tmBalanceItemService.findTmBalanceItemByTmBalanceItemCode(tbBusinessSpecialBalanceItem.getBalanceItemCode());
				
				tbBusinessSpecialBalanceItem.setBalanceItemName(tmBalanceItem.getItemName());
			
				tbBusinessSpecialBalanceItemListReturn.add(tbBusinessSpecialBalanceItem);
			}
			
		}
		
		return tbBusinessSpecialBalanceItemListReturn;
	}
	
	public void deleteBySpecialId(Long specialId){
		
		List<TbBusinessSpecialBalanceItem> tbBusinessSpecialBalanceItemList = this.findBySpecialId(specialId);
		
		if(null!=tbBusinessSpecialBalanceItemList&&tbBusinessSpecialBalanceItemList.size()>0){
			
			for(TbBusinessSpecialBalanceItem tbBusinessSpecialBalanceItem : tbBusinessSpecialBalanceItemList){
				
				tbBusinessSpecialBalanceItemDao.delete(tbBusinessSpecialBalanceItem);
				
			}
			
		}
		
	}
}
