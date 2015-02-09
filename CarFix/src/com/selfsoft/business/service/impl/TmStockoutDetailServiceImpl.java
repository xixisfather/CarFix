package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.business.dao.ITmStockInDao;
import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.dao.ITmStockoutDetailDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
@Service("TmStockoutDetailService")
public class TmStockoutDetailServiceImpl implements ITmStockoutDetailService {

	@Autowired
	private ITmStockoutDetailDao tmStockoutDetailDao;
	
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	
	@Autowired
	private ITmStockinDetailDao tmStockinDetailDao;
	
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	@Autowired
	private ITmStockOutService tmStockOutService;
	
	@Autowired
	private ITmStockOutDao tmStockOutDao;
	@Autowired
	private ITmStockInDao tmStockInDao;
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	public TmStockoutDetail findById(Long id){
		return tmStockoutDetailDao.findById(id);
	}
	
	public void insert(TmStockoutDetail tmStockoutDetail){
		tmStockoutDetailDao.insert(tmStockoutDetail);
	}
	
	public void updateTmStockoutDetailNotBalance(Long stockoutId) {
		
		TmStockoutDetail td = new TmStockoutDetail();
		
		td.setStockoutId(stockoutId);
		
		List<TmStockoutDetail> list = this.findByEntity(td);
		
		if(null !=list && list.size() > 0) {
			
			for(TmStockoutDetail tl : list) {
				
				tl.setBalanceId(null);
				
				tmStockoutDetailDao.update(tl);
				
			}
		}
		
	}
	
	public List<TmStockoutDetail> findByEntity(TmStockoutDetail tmStockoutDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmStockoutDetail.class);
		if(null!=tmStockoutDetail){
			if(null!=tmStockoutDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmStockoutDetail.getId()));
			}
			if(null!=tmStockoutDetail.getPartinfoId()){
				detachedCriteria.add(Restrictions.eq("partinfoId", tmStockoutDetail.getPartinfoId()));
			}
		
			if(null!=tmStockoutDetail.getStockoutId()){
				detachedCriteria.add(Restrictions.eq("stockoutId",tmStockoutDetail.getStockoutId()));
			}
		} 
		return tmStockoutDetailDao.findByCriteria(detachedCriteria, tmStockoutDetail);
	}
	
	
	/**
	 * 批量添加出库详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但出库，并及时减少配件表的库存数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchStockOutDetail(TmStockOut tmStockOut, Long isConfirm ,String partCol) throws NumberFormatException, MinusException{
		//设置未结算状态
		tmStockOut.setSellBalance(Constants.NOTTRUE);
		//保存销售出库单
		tmStockOutService.insert(tmStockOut);
		//保存销售出库详细内容
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//出库数量
				String price  = parts.split(":")[2];						//出库单价(类型为：销售,领用,报损,调拨)
				
				
				TmStockoutDetail tsod = new TmStockoutDetail();
				tsod.setStockoutId(tmStockOut.getId());						//出库id
				tsod.setPartinfoId(new Long(tbPartInfoId));					//配件id
				tsod.setPrice(new Double(price));							//出库单价(类型为：销售,领用,报损,调拨)
				tsod.setQuantity(new Double(num));							//出库数量
				
				
				boolean hasFree  = false;									//是否有免费标识字段（销售出库）
				if(parts.split(":").length > 3  &&  (parts.split(":")[3].equals("true") || parts.split(":")[3].equals("false") ))
					hasFree = true;
				if(hasFree){
					//免费标志
					Long isFree = new Long(parts.split(":")[4].toString()) ;
					tsod.setIsFree(isFree);
					String projectTypeId = parts.split(":")[5];
					String zl = "";
					String xmlx = "";
					String projectType = "";
					if(!projectTypeId.equals("null")){
						
						TmProjectType tmProjectType = tmProjectTypeService.findById(new Long(projectTypeId));
						zl = tmProjectType.getZl();
						xmlx = tmProjectType.getXmlx();
						projectType = tmProjectType.getProjectType();
					}
					tsod.setZl(zl);
					tsod.setXmlx(xmlx);
					tsod.setProjectType(projectType);
					//详细出库
					tmStockoutDetailDao.insert(tsod);
					//销售出库结束
					
					//如果的确认状态，则减少配件库存数量
					if(isConfirm.equals(Constants.CONFIRM))
						updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
					continue;
				}
				
				
				if(parts.split(":").length > 3){							//采购退货特殊处理
					String stockInDetailId = parts.split(":")[3];
					tsod.setStockInDetailId(new Long(stockInDetailId));
					//保存采购退货
					tmStockoutDetailDao.insert(tsod);
					
					//确认状态
					if(tmStockOut.getIsConfirm().equals(Constants.CONFIRM)){
						//更新采购入库主表数量，金额  明细表数量
						this.updateStockReturnQuantity(tmStockOut, new Long(stockInDetailId),new Double(num));
						//更新库存
						this.updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
						
					}
					continue;
					
				}
				
				//详细出库
				tmStockoutDetailDao.insert(tsod);
				
				
				//如果的确认状态，则减少配件库存数量
				if(isConfirm.equals(Constants.CONFIRM))
					updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
			}
			
		}
	}
	
	/**
	 * 采购退货时 更新明细表数量， 主表数量，金额
	 * @param tmStockOut
	 * @param stockInDetailId
	 * @param num
	 * @throws MinusException
	 */
	public void updateStockReturnQuantity(TmStockOut tmStockOut,Long stockInDetailId,Double num) throws MinusException{
		//更新采购入库 时的配件数量(采购退货）
		TmStockinDetail tmSd = tmStockinDetailDao.findById(new Long(stockInDetailId));
		Double newQuantity = tmSd.getQuantity() - new Double(num);
		if(new Double(newQuantity) < 0 )
			throw new MinusException();
//		tmSd.setQuantity(newQuantity);
		Double hasQuantity = tmSd.getHasQuantity()!=null?tmSd.getHasQuantity():0D;
		tmSd.setHasQuantity(new Double(num)+hasQuantity);
		tmStockinDetailDao.update(tmSd);
	}
	
	
	/**
	 * 减少配件库存数量
	 * @param partInfoId			//配件id
	 * @param newQuantity			//配件出库数量
	 * @throws MinusException 
	 */
	public void updatePartInfoStorkOut(Long partInfoId , Double newQuantity) throws MinusException{
		TbPartInfo tpi = tbPartInfoDao.findById(partInfoId);
		//原库存量
		double oldStoreQuantity = tpi.getStoreQuantity() != null?tpi.getStoreQuantity():0D;
		//现库存数量
		double newStoreQuantity = oldStoreQuantity - newQuantity;
		//检查安全库存
		checkPartInfoStockQuantity(tpi, newStoreQuantity);
		//是否负出库
		boolean flag = tmDictionaryService.isMinusStockOnt();
		if(flag == false && newStoreQuantity < 0  )
			throw new MinusException();
		tpi.setStoreQuantity(newStoreQuantity);
		Double stockMoney = CommonMethod.convertRadixPoint(tpi.getCostPrice()*tpi.getStoreQuantity(),2);
		tpi.setStockMoney(Math.abs(stockMoney));
		//更新日期
		tpi.setLastModifyDate(new Date());
		tbPartInfoDao.update(tpi);
	}
	
	private void checkPartInfoStockQuantity(TbPartInfo tbPartInfo , Double newStoreQuantity) throws MinusException{
		Double minStoreQuantity = tbPartInfo.getMinStoreQuantity();
		Double maxStoreQuantity = tbPartInfo.getMaxStoreQuantity();
		
		if(minStoreQuantity!=null && minStoreQuantity !=0 ){
			
			if(newStoreQuantity < minStoreQuantity ){
				throw new MinusException("配件："+tbPartInfo.getPartName()+"最小安全库存为"+minStoreQuantity+",无法出库");
			}
		}
		
		if(maxStoreQuantity!=null && maxStoreQuantity !=0 ){
			
			if(newStoreQuantity > maxStoreQuantity ){
				throw new MinusException("配件："+tbPartInfo.getPartName()+"最大安全库存为"+maxStoreQuantity+",无法出库");
			}
		}
		
	}
	
	
	/**
	 * 更新出库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function  
	 * @param stockInId
	 * @throws MinusException 
	 */
	public void updatePartInfoQuantity(Long stockOutId) throws MinusException{
		TmStockoutDetail tsod = new TmStockoutDetail();
		tsod.setStockoutId(stockOutId);
		List<TmStockoutDetail> stockdetails = this.findByEntity(tsod);
		for(TmStockoutDetail stockoutDetail : stockdetails){
			this.updatePartInfoStorkOut(stockoutDetail.getPartinfoId(),stockoutDetail.getQuantity());
		}
	}
	
	
	
	public void updateBatchStockOutDetail(TmStockOut tmStockOut,String partCol) throws NumberFormatException, MinusException{
		
		//设置未结算状态
		tmStockOut.setSellBalance(Constants.NOTTRUE);
		//保存销售出库单
		tmStockOutService.update(tmStockOut);
		//先删除明细，在加入新的明细
		tmStockOutDao.deleteStockOutDetail(tmStockOut.getId());
		
		//保存销售出库详细内容
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//出库数量
				String price  = parts.split(":")[2];						//出库单价(类型为：销售,领用,报损,调拨)
				
				
				TmStockoutDetail tsod = new TmStockoutDetail();
				tsod.setStockoutId(tmStockOut.getId());						//出库id
				tsod.setPartinfoId(new Long(tbPartInfoId));					//配件id
				tsod.setPrice(new Double(price));							//出库单价(类型为：销售,领用,报损,调拨)
				tsod.setQuantity(new Double(num));							//出库数量
				
				boolean hasFree  = false;									//是否有免费标识字段（销售出库）
				if(parts.split(":").length > 3  &&  (parts.split(":")[3].equals("true") || parts.split(":")[3].equals("false") ))
					hasFree = true;
				if(hasFree){
					//免费标志
					Long isFree = new Long(parts.split(":")[4].toString()) ;
					tsod.setIsFree(isFree);
					String projectTypeId = parts.split(":")[5];
					String zl = "";
					String xmlx = "";
					String projectType = "";
					if(!projectTypeId.equals("null")){
						
						TmProjectType tmProjectType = tmProjectTypeService.findById(new Long(projectTypeId));
						zl = tmProjectType.getZl();
						xmlx = tmProjectType.getXmlx();
						projectType = tmProjectType.getProjectType();
					}
					tsod.setZl(zl);
					tsod.setXmlx(xmlx);
					tsod.setProjectType(projectType);
					//详细出库
					tmStockoutDetailDao.insert(tsod);
					//销售出库结束
					if(tmStockOut.getIsConfirm().equals(Constants.CONFIRM))
						updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
					continue;
				}
				
				
				
				if(parts.split(":").length > 3){							//采购退货特殊处理
					String stockInDetailId = parts.split(":")[3];
					tsod.setStockInDetailId(new Long(stockInDetailId));
					//保存采购退货
					tmStockoutDetailDao.insert(tsod);
					//确认状态
					if(tmStockOut.getIsConfirm().equals(Constants.CONFIRM)){
						//更新采购入库主表数量，金额  明细表数量
						this.updateStockReturnQuantity(tmStockOut, new Long(stockInDetailId),new Double(num));
						//更新库存
						this.updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
						
					}
					continue;
					
				}
				//详细出库
				tmStockoutDetailDao.insert(tsod);
				
				
				//如果的确认状态，则减少配件库存数量
				if(tmStockOut.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
			}
			
		}
	}
	
	
	/**
	 * 更新采购退货
	 * @throws MinusException 
	 */
	public void updateStockReturn(TmStockOut tmStockOut) throws MinusException{
		//更新主表
		tmStockOutDao.update(tmStockOut);
		TmStockoutDetail queryEntity = new TmStockoutDetail();
		queryEntity.setStockoutId(tmStockOut.getId());
		List<TmStockoutDetail> stockdetails = this.findByEntity(queryEntity);
		for(TmStockoutDetail stockoutDetail : stockdetails){
			this.updateStockReturnQuantity(tmStockOut, stockoutDetail.getStockInDetailId(), stockoutDetail.getQuantity());
		}
		
		//更新采购入库主表 的数量，金额
		TmStockIn tmStockIn  = tmStockInDao.findById(tmStockOut.getStockInId());
		tmStockIn.setTotalQuantity(tmStockIn.getTotalQuantity()-tmStockOut.getTotalQuantity());
		tmStockIn.setTotalPrice(tmStockIn.getTotalPrice() - tmStockOut.getTotalPrice());
		tmStockInDao.update(tmStockIn);
		this.updatePartInfoQuantity(tmStockOut.getId());
	}
	
	
	public void updateConfrimSellDetail(TmStockOut tmStockOut,String partCol) throws NumberFormatException, MinusException{
		Double totalPrice = tmStockOut.getTotalPrice();
		Double totalQuantity = tmStockOut.getTotalQuantity();
		tmStockOut = tmStockOutDao.findById(tmStockOut.getId());
		tmStockOut.setTotalPrice(totalPrice);
		tmStockOut.setTotalQuantity(totalQuantity);
		tmStockOutDao.update(tmStockOut);
		//保存销售出库详细内容
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//出库数量
				String price  = parts.split(":")[2];						//出库单价(类型为：销售,领用,报损,调拨)
				
				
				TmStockoutDetail tsod = new TmStockoutDetail();
				tsod.setStockoutId(tmStockOut.getId());						//出库id
				tsod.setPartinfoId(new Long(tbPartInfoId));					//配件id
				tsod.setPrice(new Double(price));							//出库单价(类型为：销售,领用,报损,调拨)
				tsod.setQuantity(new Double(num));							//出库数量
				
				boolean hasFree  = false;									//是否有免费标识字段（销售出库）
				if(parts.split(":").length > 3  &&  (parts.split(":")[3].equals("true") || parts.split(":")[3].equals("false") ))
					hasFree = true;
				if(hasFree){
					//免费标志
					Long isFree = new Long(parts.split(":")[4].toString()) ;
					tsod.setIsFree(isFree);
				}
				
				//详细出库
				tmStockoutDetailDao.insert(tsod);
				
				
				//如果的确认状态，则减少配件库存数量
				updatePartInfoStorkOut(new Long(tbPartInfoId), new Double(num));
			}
		}
		
	}
	
	
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * 若销售价为0时则取配件的默认销售价
	 * @param partId
	 * @param customerId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long customerId){
		
		
		Double result =  tmStockoutDetailDao.getSellPriceByCustomer(partId, customerId);
		
		if(result == 0D)
			result = tbPartInfoDao.findById(partId).getSellPrice();
		
		return result;
		
	}
	
	/**
	 * 得到多个配件的客户最近一次的销售价，并字符串形式返回，前台进行分割处理
	 * 输出格式：配件id:销售价,配件id:销售价,
	 * 参数格式：配件id,配件id,配件id,
	 * @param partIds
	 * @param customerId
	 * @return
	 */
	public String getCustomerSellPriceByPartIds(String partIds,Long customerId){
		String[] partidArr = partIds.split(",");
		
		String result = "";
		for(String partId : partidArr){
			
			if(StringUtils.isNotBlank(partId)){
				
				Double sellPrice = this.getCustomerSellPriceByPartId(new Long(partId), customerId);
				
				result += partId +":"+sellPrice+",";
			}
		}
		
		return result;
	}
	
	
	
	public void insertBatchStock(Map<String, String> formMap , TmStockOut tmStockOut){
		//保存入库主表
		tmStockOutDao.insert(tmStockOut);
	
		this.batchInsertStock(formMap, tmStockOut);
	}
	public void stockBatchUpdate(Map<String, String> formMap , TmStockOut tmStockOut){
		
		//保存入库主表
		tmStockOutDao.update(tmStockOut);
		//先删除明细，在加入新的明细
		tmStockOutDao.deleteStockOutDetail(tmStockOut.getId());
		
		this.batchInsertStock(formMap, tmStockOut);
		
	}
	public void batchInsertStock(Map<String, String> formMap , TmStockOut tmStockOut){
		//保存出库明细表
		for(String key : formMap.keySet()){
			if(StringUtils.isNotBlank(key)){
				//配件ID	
				int tbPartInfoId = new Integer(key.substring(3));
				
				String value = formMap.get(key);
				
				//数量
				double quantity = Double.valueOf(value.split("_")[0]);
				//成本价
				double price = Double.valueOf(value.split("_")[1]);
				
				TmStockoutDetail tsod = new TmStockoutDetail();
				tsod.setPartinfoId(new Long(tbPartInfoId));
				tsod.setStockoutId(tmStockOut.getId());
				tsod.setQuantity(quantity);
				tsod.setPrice(price);
				tsod.setIsFree(1L);
				tmStockoutDetailDao.insert(tsod);
				
			}
		}
	}
	
	
	public Map<Double,List<TbCustomerVo>> getStockOutCustomerTotalPriceByStat(TmStockIn tmStockIn){
		
		List<TbCustomerVo> result = new ArrayList<TbCustomerVo>();
		List<Object[]> objList = tmStockOutDao.getStockOutCustomerTotalPriceByStat(tmStockIn);
		Double supplierTotalPrice = 0D;
		for(Object[] obj : objList){
			Long customerId = Long.valueOf(obj[0].toString());
			String customerCode = obj[1].toString();
			String customerName = obj[2].toString();
			Double totalPrice = Double.valueOf(obj[3].toString());
			TbCustomerVo vo = new TbCustomerVo(customerId,customerCode,customerName,totalPrice);
			result.add(vo);
			
			supplierTotalPrice += totalPrice;
		}
		
		Map<Double , List<TbCustomerVo>> map = new HashMap<Double, List<TbCustomerVo>>();
		map.put(CommonMethod.convertRadixPoint(supplierTotalPrice, 2), result);
		
		return map;
	}
}
