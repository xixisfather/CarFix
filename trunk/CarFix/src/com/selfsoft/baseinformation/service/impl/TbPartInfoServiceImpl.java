package com.selfsoft.baseinformation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.dao.ITbPartSolePriceDao;
import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.baseinformation.service.ITbPartCollectionService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseinformation.service.ITbPartSolePriceService;
import com.selfsoft.baseinformation.vo.TbPartInfoVo;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.vo.TbPartInfoReFlowStatVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
@Service("tbPartInfoService")
public class TbPartInfoServiceImpl implements ITbPartInfoService {
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITbPartCollectionService tbPartCollectionService;
	@Autowired
	private ITbPartSolePriceDao tbPartSolePriceDao;
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;
	@Autowired
	private ITbPartSolePriceService tbPartSolePriceService;
	@Autowired
	private IStatisticsStockInOutService statisticsStockInOutService;

	public boolean deleteById(Long id) {
		//删除配件相关的所有价格
		tbPartSolePriceDao.deletePartSoleByPartInfo(id);
		//删除配件信息
		boolean flag = tbPartInfoDao.deleteById(id);
		
		return flag;
	}

	public TbPartInfo findByCode(String partCode){
		
		List<TbPartInfo> partInfoList = this.tbPartInfoDao.findBySQL("from TbPartInfo t where t.partCode = ?", new String[]{partCode});
		
		if(null != partInfoList && partInfoList.size() > 0){
			return partInfoList.get(0);
		}
		
		return null;
	}
	
	public List<TbPartInfo> findByEntity(TbPartInfo tbPartInfo) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbPartInfo.class);
		if(null!=tbPartInfo){
			if(null!=tbPartInfo.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbPartInfo.getId()));
			}
			if(null!=tbPartInfo.getTmStoreHouse() && null!=tbPartInfo.getTmStoreHouse().getId()){
				
				
				detachedCriteria.add(Restrictions.eq("tmStoreHouse.id", tbPartInfo.getTmStoreHouse().getId()));
			}
			if(null!=tbPartInfo.getTmCarModelType() && null!=tbPartInfo.getTmCarModelType().getId()){
				detachedCriteria.add(Restrictions.eq("tmCarModelType.id", tbPartInfo.getTmCarModelType().getId()));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getPartCode())){
				//detachedCriteria.add(Restrictions.eq("partCode", tbPartInfo.getPartCode()));
				/**
				 * edited by ccr
				 */
				detachedCriteria.add(Restrictions.like("partCode","%" + tbPartInfo.getPartCode() + "%"));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getPartName())){
				detachedCriteria.add(Restrictions.like("partName", "%"+tbPartInfo.getPartName()+"%"));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getPinyinCode())){
				//detachedCriteria.add(Restrictions.eq("pinyinCode", tbPartInfo.getPinyinCode()));
				detachedCriteria.add(Restrictions.like("pinyinCode","%" + tbPartInfo.getPinyinCode() + "%"));
			}
			if(null!=tbPartInfo.getTmUnit() && null!=tbPartInfo.getTmUnit().getId()){
				detachedCriteria.add(Restrictions.eq("tmUnit.id", tbPartInfo.getTmUnit().getId()));
			}
			if(null!=tbPartInfo.getTmPartType() && null!=tbPartInfo.getTmPartType().getId()){
				detachedCriteria.add(Restrictions.eq("tmPartType.id", tbPartInfo.getTmPartType().getId()));
			}
			
			if(StringUtils.isNotBlank(tbPartInfo.getPartBroadType())){
				detachedCriteria.add(Restrictions.eq("partBroadType", tbPartInfo.getPartBroadType()));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getStoreLocation())){
				detachedCriteria.add(Restrictions.eq("storeLocation", tbPartInfo.getStoreLocation()));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getFactoryCode())){
				detachedCriteria.add(Restrictions.eq("factoryCode", tbPartInfo.getFactoryCode()));
			}
			if(StringUtils.isNotBlank(tbPartInfo.getDangerCode())){
				detachedCriteria.add(Restrictions.eq("dangerCode", tbPartInfo.getDangerCode()));
			}
			if(tbPartInfo.getLastModifyDate()!= null){
				detachedCriteria.add(Restrictions.le("lastModifyDate", tbPartInfo.getLastModifyDate()));
			}
			if(tbPartInfo.getStoreQuantity() != null){
				detachedCriteria.add(Restrictions.gt("storeQuantity", tbPartInfo.getStoreQuantity()));
			}
			
			if(tbPartInfo.getMinPrice()!=null){
				//>=
				detachedCriteria.add(Restrictions.ge("sellPrice", tbPartInfo.getMinPrice()));
			}
			if(tbPartInfo.getMaxPrice()!=null){
				//<=
				detachedCriteria.add(Restrictions.le("sellPrice", tbPartInfo.getMaxPrice()));
			}
			
		} 
		return tbPartInfoDao.findByCriteria(detachedCriteria, tbPartInfo);
	}

	public TbPartInfo findById(Long id) {
		return tbPartInfoDao.findById(id);
	}

	public void insert(String soleTypes,TbPartInfo tbPartInfo) {
		//第一次进货价就是成本价
		tbPartInfo.setStockPrice(tbPartInfo.getCostPrice());
		
		if(tbPartInfo.getTmUnit() == null || tbPartInfo.getTmUnit().getId() == null)
			tbPartInfo.setTmUnit(null);
		if(tbPartInfo.getTmCarModelType() == null || tbPartInfo.getTmCarModelType().getId() == null)
			tbPartInfo.setTmCarModelType(null);
		if(tbPartInfo.getTmPartType() == null || tbPartInfo.getTmPartType().getId() == null)
			tbPartInfo.setTmPartType(null);
		
		//新增配件信息
		tbPartInfoDao.insert(tbPartInfo);
		//新增配件所有价格类型的价格
		if(StringUtils.isNotBlank(soleTypes)){
			for(String st : soleTypes.split(",")){
				if(StringUtils.isBlank(st)) continue;
				
				String soleTypeId = st.split(":")[0].toString();
				String solePrice = "0";
				if(st.split(":").length > 1 && StringUtils.isNotBlank(st.split(":")[1]))
					solePrice = st.split(":")[1].toString();
				
				TbPartSolePrice tpsp = new TbPartSolePrice();
				tpsp.setPartInfoId(tbPartInfo.getId());
				tpsp.setSoleTypeId(new Long(soleTypeId));
				tpsp.setSolePrice(new Double(solePrice));
				tbPartSolePriceDao.insert(tpsp);
				
			}
		}
		
		//加入配件的默认销售价
		TmSoleType tmSoleType = tmSoleTypeService.getDefaultSoleType();
		TbPartSolePrice partPrice = new TbPartSolePrice();
		partPrice.setPartInfoId(tbPartInfo.getId());
		partPrice.setSoleTypeId(tmSoleType.getId());
		List<TbPartSolePrice> priceList = tbPartSolePriceService.findByEntity(partPrice);
		tbPartInfo.setSellPrice(priceList.get(0).getSolePrice());
		
		tbPartInfoDao.update(tbPartInfo);
	}

	public void update(String soleTypes , TbPartInfo tbPartInfo) {
		
		if(tbPartInfo.getTmUnit() == null || tbPartInfo.getTmUnit().getId() == null)
			tbPartInfo.setTmUnit(null);
		if(tbPartInfo.getTmCarModelType() == null || tbPartInfo.getTmCarModelType().getId() == null)
			tbPartInfo.setTmCarModelType(null);
		if(tbPartInfo.getTmPartType() == null || tbPartInfo.getTmPartType().getId() == null)
			tbPartInfo.setTmPartType(null);
		
		//修改配件信息
		tbPartInfoDao.update(tbPartInfo);
		//修改配件所有价格
		if(StringUtils.isNotBlank(soleTypes)){
			for(String st : soleTypes.split(",")){
				if(StringUtils.isBlank(st)) continue;
				
				String id = st.split(":")[0].toString();
				String solePrice = "0";
				if(st.split(":").length > 1 && StringUtils.isNotBlank(st.split(":")[1]))
					solePrice = st.split(":")[1].toString();
				
				TbPartSolePrice tpsp =  this.tbPartSolePriceDao.findById(new Long(id));
				tpsp.setSolePrice(new Double(solePrice));
				tbPartSolePriceDao.update(tpsp);
				
			}
		}
		
		//加入配件的默认销售价
		TmSoleType tmSoleType = tmSoleTypeService.getDefaultSoleType();
		TbPartSolePrice partPrice = new TbPartSolePrice();
		partPrice.setPartInfoId(tbPartInfo.getId());
		partPrice.setSoleTypeId(tmSoleType.getId());
		List<TbPartSolePrice> priceList = tbPartSolePriceService.findByEntity(partPrice);
		tbPartInfo.setSellPrice(priceList.get(0).getSolePrice());
		
		tbPartInfoDao.update(tbPartInfo);
	}
	
	public List<TbPartInfo> getTbPartInfoByCollectionCode(String collectionCode){
		List<TbPartInfo> result = tbPartInfoDao.getTbPartInfoByCollectionCode(collectionCode);
		
		for(TbPartInfo tpi :result){
			TbPartCollection tpc = new TbPartCollection();
			tpc.setCollectionCode(collectionCode);
			tpc.setPartInfoId(tpi.getId());
			List<TbPartCollection> tbcList = tbPartCollectionService.findByEntity(tpc);
			if(tbcList!=null && tbcList.size()>0)
				tpi.setQuantity(tbcList.get(0).getPartQuantity());
			
		}
		
		return result;
	}
	
	
	public void updateTbPartInfoStoreQuantity(Long tbPartInfoId , Double num){
		TbPartInfo tpi  = tbPartInfoDao.findById(tbPartInfoId);
		Double oldquantity = tpi.getStoreQuantity();
		Double newQuantity = oldquantity - num;
		tpi.setStoreQuantity(newQuantity);
		tbPartInfoDao.update(tpi);
	}
	
	public Double getTbPartInfoCostPrice(Long tbPartInfoId){
		Map<Long, String> soleTypeMap = tmSoleTypeService.findAllTmSoleTypeMap();
		Long soleTypeId = null;
		for(Long key : soleTypeMap.keySet()){
			if(soleTypeMap.get(key).indexOf("成本价") != -1){
				soleTypeId = key;
				break;
			}
		}
		if(soleTypeId == null)
			return 0D;
		TbPartSolePrice tbPartSolePrice = new TbPartSolePrice();
		tbPartSolePrice.setSoleTypeId(new Long(soleTypeId));
		tbPartSolePrice.setPartInfoId(new Long(tbPartInfoId));
		List<TbPartSolePrice> solepriceList = tbPartSolePriceService.findByEntity(tbPartSolePrice);
		
		if(solepriceList!=null && solepriceList.size()>0)
			return solepriceList.get(0).getSolePrice();
		
		return 0D;
	}
	
	/**
	 * added by ccr
	 * 根据车辆类型ID来查找配件
	 */
	public List<TbPartInfo> findPartListByTmCarModelTypeId(Long tmCarModelTypeId){
		
		return tbPartInfoDao.findBySQL("SELECT tbPartInfo FROM TbPartInfo tbPartInfo WHERE tbPartInfo.tmCarModelType.id=?", new Object[]{tmCarModelTypeId});
		
	}
	
	
	/**
	 * 根据配件代码，仓库来定位到单个配件
	 * @param partCode
	 * @param storeHoseId
	 * @return
	 */
	public TbPartInfo getPartInfoByCodeAndStore(String partCode,Long storeHoseId){
		List<TbPartInfo> result = tbPartInfoDao.findBySQL("from TbPartInfo t where t.partCode = ? and t.tmStoreHouse.id = ?", new Object[]{partCode,storeHoseId});
		if(result != null && result.size()>0)
			return result.get(0);
		
		return null;
	}
	
	
	public boolean updateCostPrice(Long id , Double newCostPrice){
		TbPartInfo partinfo = tbPartInfoDao.findById(id);
		partinfo.setCostPrice(newCostPrice);
		Double stockMoney = CommonMethod.convertRadixPoint(newCostPrice*partinfo.getStoreQuantity(),2);
		
		partinfo.setStockMoney(Math.abs(stockMoney));
		boolean flag = tbPartInfoDao.update(partinfo);
		return flag;
		
	}
	
	
	public List<TbPartInfoVo> getTbPartInfoVoByCustomerId(TbPartInfo tbPartInfo , String customerId){
		
		 List<Object[]> objList = tbPartInfoDao.getTbPartInfoVoByCustomerId(tbPartInfo,customerId);
		 List<TbPartInfoVo> result = new ArrayList<TbPartInfoVo>();
		 for(Object[] objs: objList){
			 Double customSellPrice= objs[0] != null ? Double.valueOf(objs[0].toString()):0D;		
			 Long tmStoreHouseId = Long.valueOf(objs[1].toString());
			 String houseCode= objs[2].toString();					
			 String houseName= objs[3].toString();				
			 String unitName= objs[4].toString();			
			 
			 Long tbPartinfoId = Long.valueOf(objs[5].toString());
  			 String partCode= objs[6].toString();					
			 String partName= objs[7].toString();				
			 String pinyinCode= objs[8].toString();			
			 Double storeQuantity= Double.valueOf(objs[9].toString());			
			 Double costPrice= Double.valueOf(objs[10].toString());					
			 Double sellPrice= customSellPrice == 0D ? Double.valueOf(objs[11].toString()): customSellPrice;				
			 String typeName = objs[12]!= null ?objs[12].toString():null;
			 TbPartInfoVo vo = new TbPartInfoVo();
			 vo.setCustomSellPrice(customSellPrice);
			 vo.setTmStoreHouseId(tmStoreHouseId);
			 vo.setHouseName(houseName);
			 vo.setHouseCode(houseCode);
			 vo.setUnitName(unitName);
			 vo.setId(tbPartinfoId);
			 vo.setPartCode(partCode);
			 vo.setPartName(partName);
			 vo.setPinyinCode(pinyinCode);
			 vo.setStoreQuantity(storeQuantity);
			 vo.setCostPrice(costPrice);
			 vo.setSellPrice(sellPrice);
			 vo.setTypeName(typeName);
			 result.add(vo);
		 }
		 
		 return result;
	}
	
	
	public Double getTotalStockMoney(TbPartInfo tbPartInfo){
		
		List<TbPartInfo> partInfoList = this.findByEntity(tbPartInfo);
		
		Double result = 0D;
		
		for(TbPartInfo partinfo : partInfoList){
			Double stockmoney = partinfo.getStockMoney()!=null?partinfo.getStockMoney():0D;
			result += stockmoney;
		}
		
		return CommonMethod.convertRadixPoint(result, 2);
	}
	
	public List<TbPartInfo> checkHousePartInfo(String parameters) {
		

		 Map<Long, Long> paramMap = new LinkedHashMap<Long, Long>();
		 if(StringUtils.isNotBlank(parameters)){
			 String[] params = parameters.split(";");
			 
			 if(params != null && params.length >0){
				 
				 for(String param : params){
					 Long houseId = new Long(param.split(",")[0]);
					 Long checkType = new Long(param.split(",")[1]);
					 
					 paramMap.put(houseId, checkType);
				 }
			 }
		 }
		 
		 List<TbPartInfo> result = tbPartInfoDao.checkHousePartInfo(paramMap);
		 
		 return result;
		
	}
	
	public void checkPartInfoStockInQuantity(Long tbPartInfoId , Double newStoreQuantity)throws MinusException{
		TbPartInfo tbPartInfo = this.findById(tbPartInfoId);
		Double minStoreQuantity = tbPartInfo.getMinStoreQuantity();
		Double maxStoreQuantity = tbPartInfo.getMaxStoreQuantity();
		
		if(maxStoreQuantity!=null && maxStoreQuantity != 0){
			
			if(newStoreQuantity > maxStoreQuantity ){
				throw new MinusException("配件："+tbPartInfo.getPartName()+"最大安全库存为"+maxStoreQuantity+",无法入库");
			}
		}
	}
	
	public void updateAllNotRightStoreQuantity() {
		
		System.out.println("begin update storequantity");
		
		List<TbPartInfo> list = tbPartInfoDao.findAll();
		
		if(null !=list &&list.size() > 0) {
			
			for(TbPartInfo tpo : list) {
				
				List<TbPartInfo> temp = new ArrayList<TbPartInfo>();
				
				temp.add(tpo);
				
				List<TbPartInfoReFlowStatVo> results = statisticsStockInOutService.getPartInfoReFlowDetailStat(temp, null, new TbPartInfoReFlowStatVo());
				
				BigDecimal total = new BigDecimal(0.00d);
				
				if(null !=results&&results.size() > 0) {
					
					for(TbPartInfoReFlowStatVo t : results) {
						
						Double in = t.getInQuantity() == null?0.00d : t.getInQuantity();
						
						Double out = t.getOutQuantity() == null ? 0.00d:t.getOutQuantity();
						
						Double totalQuantity = t.getTotalQuantity();
						
						BigDecimal din = new BigDecimal(in.toString());
						
						BigDecimal dout = new BigDecimal(out.toString());
						
						total = total.add(din).subtract(dout);
						
					}
					if(!tpo.getStoreQuantity().equals(total.doubleValue())) {
						//System.out.println(tpo.getPartCode() + " " + tpo.getStoreQuantity() + " " + total);
						tpo.setStoreQuantity(total.doubleValue());
						tbPartInfoDao.update(tpo);
						
					}
				}
			
			
			
			}
		
		
		}
	}
}
