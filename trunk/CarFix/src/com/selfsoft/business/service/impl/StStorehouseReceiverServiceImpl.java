package com.selfsoft.business.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.dao.IStStorehouseReceiverDao;
import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.business.service.IStStorehouseReceiverService;
@Service("stStorehouseReceiverService")
public class StStorehouseReceiverServiceImpl implements
		IStStorehouseReceiverService {

	
	@Autowired
	private IStStorehouseReceiverDao stStorehouseReceiverDao;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;

	public boolean deleteById(Long id) {
		return stStorehouseReceiverDao.delete(id);
	}

	public StStorehouseReceiver findById(Long id) {
		return stStorehouseReceiverDao.findById(id);
	}

	public List<StStorehouseReceiver> findByStStorehouseReceiver(
			StStorehouseReceiver stStorehouseReceiver) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StStorehouseReceiver.class);
		
		if(null!=stStorehouseReceiver){
			
			if(null!=stStorehouseReceiver.getId()){
				detachedCriteria.add(Restrictions.eq("id",stStorehouseReceiver.getId()));
			}
			
			if(StringUtils.isNotBlank(stStorehouseReceiver.getStockoutType())){
				detachedCriteria.add(Restrictions.eq("stockoutType", stStorehouseReceiver.getStockoutType()));
			}
			if(StringUtils.isNotBlank(stStorehouseReceiver.getSearchBeginDate())){
				int year = Integer.valueOf(stStorehouseReceiver.getSearchBeginDate().split("-")[0]);
				int mounth = Integer.valueOf(stStorehouseReceiver.getSearchBeginDate().split("-")[1]);
				detachedCriteria.add(Restrictions.sqlRestriction(" datepart(yy,create_Date)="+year));
				detachedCriteria.add(Restrictions.sqlRestriction(" datepart(mm,create_Date)="+mounth));
			}
			if(null!=stStorehouseReceiver.getTmStoreHouse()){
				detachedCriteria.createAlias("tmStoreHouse","tmStoreHouse");
				
				if(null!=stStorehouseReceiver.getTmStoreHouse().getId()){
					detachedCriteria.add(Restrictions.eq("tmStoreHouse.id", stStorehouseReceiver.getTmStoreHouse().getId()));
				}
			}
			
		}
		return stStorehouseReceiverDao.findByCriteria(detachedCriteria, stStorehouseReceiver);
	}

	public void insert(StStorehouseReceiver stStorehouseReceiver) {
		stStorehouseReceiverDao.insert(stStorehouseReceiver);
		
	}

	public void update(StStorehouseReceiver stStorehouseReceiver) {
		stStorehouseReceiverDao.update(stStorehouseReceiver);
	}
	
	public void batchInsert(Map<String, String> formMap,Date newDate){
		
		for(String key : formMap.keySet()){
			if(key.indexOf("sell") != -1 || key.indexOf("qc") != -1 || key.indexOf("qm") != -1 || key.indexOf("rkxj") != -1 || key.indexOf("ckxj") != -1 )
				continue;
			
			String val = formMap.get(key);
			if(StringUtils.isNotBlank(val)){
				String type = key.split("_")[0];
				String storeHouseId = key.split("_")[1];
				
				StStorehouseReceiver  pojo = new StStorehouseReceiver();
				TmStoreHouse tmStoreHouse = tmStoreHouseService.findById(new Long(storeHouseId));
				pojo.setStockoutType(type);
				pojo.setTmStoreHouse(tmStoreHouse);
				if(newDate != null)
					pojo.setCreateDate(newDate);
				else
					pojo.setCreateDate(new Date());
				
				String QStart = formMap.get("qc_"+storeHouseId);
				if(StringUtils.isNotBlank(QStart))
					pojo.setQStart(new Double(QStart));
				
				String QFinal = formMap.get("qm_"+storeHouseId);
				if(StringUtils.isNotBlank(QFinal))
					pojo.setQFinal(new Double(QFinal));
				if(type.indexOf("CG")!= -1 || type.indexOf("OVERFLOW")!= -1 || type.indexOf("ALLOTIN")!= -1 || type.indexOf("REMOVESTOCK")!= -1 || type.indexOf("OTHERSTOCKIN")!= -1){
					pojo.setStockinCostPrice(new Double(val));
					
				
				}else{
					pojo.setStockoutCostPrice(new Double(val));
					
					String sellTotalPrice = formMap.get(type+"sell_"+storeHouseId);
					if(StringUtils.isNotBlank(sellTotalPrice)){
						pojo.setSellTotalPrice(new Double(sellTotalPrice));
					}
					
					
				}
				String stockinSubTotal = formMap.get("rkxj_"+storeHouseId);
				if(StringUtils.isNotBlank(stockinSubTotal)){
					pojo.setStockinSubTotal(new Double(stockinSubTotal));
				}
				String stockoutSubTotal = formMap.get("ckxj_"+storeHouseId);
				if(StringUtils.isNotBlank(stockoutSubTotal))
					pojo.setStockoutSubTotal(new Double(stockoutSubTotal));
				stStorehouseReceiverDao.insert(pojo);
				
			}
		}
	}
	
	
	public void batchUpdate(String dateStr , Map<String, String> formMap){
		
		String yearStr = dateStr.split("-")[0];
		
		String mounthStr = dateStr.split("-")[1];
		
		stStorehouseReceiverDao.deleteByYearMonth(yearStr, Integer.valueOf(mounthStr)+"");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Date newDate = sdf.parse(dateStr);
			this.batchInsert(formMap,newDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean hasAdd(){
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String yearStr = sdf.format(currentDate);
		sdf = new SimpleDateFormat("MM");
		String mounthStr = sdf.format(currentDate);
		
		String searchBeginDate = yearStr + "-" + mounthStr;
		StStorehouseReceiver queryEntity = new StStorehouseReceiver();
		queryEntity.setSearchBeginDate(searchBeginDate);
		List<StStorehouseReceiver> receiverList = this.findByStStorehouseReceiver(queryEntity);
		
		if(receiverList != null && receiverList.size()>0)
			return false;
		
		return true;
		
		
	}
	
	
	public Map<String,String> initEditStoreHouseReceiver(String dateStr){
		
		Map<String,String> mapResult = new HashMap<String, String>();
		
		StStorehouseReceiver queryEntity = new StStorehouseReceiver(); 
		
		String yearStr = dateStr.split("-")[0];
		
		String mounthStr = dateStr.split("-")[1];
		
		String searchBeginDate = yearStr + "-" + mounthStr;
		
		queryEntity.setSearchBeginDate(searchBeginDate);
		
		List<StStorehouseReceiver> stStoreHouseReceiverList = this.findByStStorehouseReceiver(queryEntity);
		
		for(StStorehouseReceiver receiver : stStoreHouseReceiverList){
			
			mapResult.put("qc_"+receiver.getTmStoreHouse().getId(), receiver.getQStart()+"");
			mapResult.put("qm_"+receiver.getTmStoreHouse().getId(), receiver.getQFinal()+"");
			mapResult.put("rkxj_"+receiver.getTmStoreHouse().getId(), receiver.getStockinSubTotal()+"");
			mapResult.put("ckxj_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutSubTotal()+"");
			
			if(receiver.getStockoutType().equals("CG")){
				mapResult.put("CG_"+receiver.getTmStoreHouse().getId(), receiver.getStockinCostPrice()+"");
				
			}else if(receiver.getStockoutType().equals("ALLOTIN")){
				mapResult.put("ALLOTIN_"+receiver.getTmStoreHouse().getId(), receiver.getStockinCostPrice()+"");
				
			}else if(receiver.getStockoutType().equals("OVERFLOW")){
				mapResult.put("OVERFLOW_"+receiver.getTmStoreHouse().getId(), receiver.getStockinCostPrice()+"");
				
			}else if(receiver.getStockoutType().equals("REMOVESTOCK")){
				mapResult.put("REMOVESTOCK_"+receiver.getTmStoreHouse().getId(), receiver.getStockinCostPrice()+"");
				
			}else if(receiver.getStockoutType().equals("OTHERSTOCKIN")){
				mapResult.put("OTHERSTOCKIN_"+receiver.getTmStoreHouse().getId(), receiver.getStockinCostPrice()+"");
				
			}else if(receiver.getStockoutType().equals("MAINTAIN")){
				mapResult.put("MAINTAIN_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("MAINTAINsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("STOCKOUT")){
				mapResult.put("STOCKOUT_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("STOCKOUTsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("ALLOTSTOCKOUT")){
				mapResult.put("ALLOTSTOCKOUT_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("ALLOTSTOCKOUTsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("SHATTERSTOCKOUT")){
				mapResult.put("SHATTERSTOCKOUT_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("SHATTERSTOCKOUTsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("SHIFTSTOCK")){
				mapResult.put("SHIFTSTOCK_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("SHIFTSTOCKsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("UQFL")){
				mapResult.put("UQFL_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("UQFLsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("STOCKRETURN")){
				mapResult.put("STOCKRETURN_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("STOCKRETURNsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}else if(receiver.getStockoutType().equals("OTHERSTOCKOUT")){
				mapResult.put("OTHERSTOCKOUT_"+receiver.getTmStoreHouse().getId(), receiver.getStockoutCostPrice()+"");
				mapResult.put("OTHERSTOCKOUTsell_"+receiver.getTmStoreHouse().getId(), receiver.getSellTotalPrice()+"");
				
			}
			
		}
		
		return mapResult;
		
	}
	
}
