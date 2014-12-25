package com.selfsoft.baseparameter.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmBalanceItemDao;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.baseparameter.service.ITmBalanceService;
@Service("tmBalanceItemService")
public class TmBalanceItemServiceImpl implements ITmBalanceItemService{
	@Autowired
	private ITmBalanceItemDao tmBalanceItemDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tmBalanceItemDao.deleteById(id);
	}

	public List<TmBalanceItem> findAll() {
		// TODO Auto-generated method stub
		return tmBalanceItemDao.findAll();
	}

	public TmBalanceItem findById(Long id) {
		// TODO Auto-generated method stub
		return tmBalanceItemDao.findById(id);
	}

	public List<TmBalanceItem> findByTmBalanceItem(TmBalanceItem tmBalanceItem){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmBalanceItem.class);
		
		if(null!=tmBalanceItem){
			
			if(null!=tmBalanceItem.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmBalanceItem.getId()));
			}
			if(null!=tmBalanceItem.getTmBalance()){
				
				detachedCriteria.createAlias("tmBalance","tmBalance");
				
				if(null!=tmBalanceItem.getTmBalance().getId()){
					detachedCriteria.add(Restrictions.eq("tmBalance.id",tmBalanceItem.getTmBalance().getId()));
				}
			}
		}
		
		return tmBalanceItemDao.findByCriteria(detachedCriteria, tmBalanceItem);
		
	}
	public void insert(TmBalanceItem tmBalanceItem) {
		// TODO Auto-generated method stub
		tmBalanceItemDao.insert(tmBalanceItem);
	}

	public void update(TmBalanceItem tmBalanceItem) {
		// TODO Auto-generated method stub
		tmBalanceItemDao.update(tmBalanceItem);
	}
	
	public Map<Long,String> findAllTmBalanceItemMap(){
		
		Map map = new LinkedHashMap<Long,String>();
		
		List<TmBalanceItem> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmBalanceItem tmBalanceItem : list){
				
				map.put(tmBalanceItem.getId(), tmBalanceItem.getItemName());
			
			}
		}
		
		return map;
	}
	
	//取出String中[]的位置 结对放置在MAP中
	private Map<Integer,Integer> StringIndexMap(String str){
		
		Map<Integer,Integer> map = new LinkedHashMap<Integer,Integer>();
		
		int k = -1 ;
		int p = -1 ;
		for(int i = 0 ; i < str.length() ; i++){
			char c = str.charAt(i);
			if('['==c){
				k=i;
			}
			if(']'==c){
				p=i;
			}
			if(k!=-1&&p!=-1){
				map.put(k, p);
				k=-1;
				p=-1;
			}
		}
		
		return map;
	}
	
	//取出对应的结算项目明细名字
	private List<String> formulaItemName(String formula){
		
		List<String>itemNameList = new ArrayList<String>();
		
		Map<Integer,Integer> indexMap = this.StringIndexMap(formula);
		
		if(null!=indexMap&&indexMap.size()>0){
			
			for(Integer key : indexMap.keySet()){
				
				Integer beginIndex = key;
				
				Integer endIndex = indexMap.get(key);
				
				String itemName = formula.substring(beginIndex+1, endIndex);
				
				itemNameList.add(itemName);
			}
		}
		
		return itemNameList;
	}
	
	/**
	 * 根据公式来计算
	 */
	public List<Integer> formulaCalculateByFormula(String formula,Map map) {
		
		List<String> itemNameList = this.formulaItemName(formula);
		
		if(null!=itemNameList&&itemNameList.size()>0){
			for(String itemName : itemNameList){
				
				formula = formula.replace(itemName.trim(),String.valueOf(map.get(itemName.trim())));
				
				formula = formula.replace("[","");
				
				formula = formula.replace("]","");
			}
		}
		
		return tmBalanceItemDao.findByOriginSql("select "+formula, new Object[]{});
	}
	
	/**
	 * 根据结算项目ID与结算项目明细名称来计算
	 */
	public List<Object> formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long tmBalanceId,String tmBalanceItemName,Map map){
		
		TmBalanceItem tmBalanceItem = this.findTmBalanceItemByTmBalanceIdAndTmBalanceItemName(tmBalanceId, tmBalanceItemName);
		
		if(null!=tmBalanceItem){
			
			String formula = tmBalanceItem.getFormula();
			
			List<String> itemNameList = this.formulaItemName(formula);
			
			if(null!=itemNameList&&itemNameList.size()>0){
				for(String itemName : itemNameList){
					
					formula = formula.replace(itemName.trim(),String.valueOf(map.get(itemName.trim())));
					
					formula = formula.replace("[","");
					
					formula = formula.replace("]","");
				}
			}
			
			return tmBalanceItemDao.findByOriginSql("select "+formula, new Object[]{});
		}
		
		return null;
	}
	
	public List<TmBalanceItem> findTmBalanceItemByTmBalanceId(Long tmBalanceId){
		
		TmBalanceItem tmBalanceItem = new TmBalanceItem();
		
		TmBalance tmBalance = new TmBalance();
		
		tmBalance.setId(tmBalanceId);
		
		tmBalanceItem.setTmBalance(tmBalance);
		
		return this.findByTmBalanceItem(tmBalanceItem);
	}
	
	public Map<Long,String> findTmBalanceItemMapByTmBalanceId(Long tmBalanceId){
		
		Map map = new LinkedHashMap<Long,String>();
		
		List<TmBalanceItem> list = this.findTmBalanceItemByTmBalanceId(tmBalanceId);
		
		if(null!=list&&list.size()>0){
			
			for(TmBalanceItem tmBalanceItem : list){
				
				map.put(tmBalanceItem.getId(), tmBalanceItem.getItemName());
			
			}
		}
		
		return map;
	}
	
	public TmBalanceItem findTmBalanceItemByTmBalanceIdAndTmBalanceItemName(Long tmBalanceId,String tmBalanceItemName){
		
		List<TmBalanceItem>list = tmBalanceItemDao.findBySQL("select tmBalanceItem FROM TmBalanceItem tmBalanceItem inner join tmBalanceItem.tmBalance where tmBalanceItem.tmBalance.id=? and tmBalanceItem.itemName=?", new Object[]{tmBalanceId,tmBalanceItemName});
		
		if(null!=list&&list.size()>0){
			
			return list.get(0);
		
		}
		
		return null;
	}
	
	public TmBalanceItem findTmBalanceItemByTmBalanceItemCode(String itemCode){
		
		List<TmBalanceItem>list = tmBalanceItemDao.findBySQL("select tmBalanceItem FROM TmBalanceItem tmBalanceItem where tmBalanceItem.itemCode=?", new Object[]{itemCode});
		
		if(null!=list&&list.size()>0){
			
			return list.get(0);
		
		}
		
		return null;
	}
}
