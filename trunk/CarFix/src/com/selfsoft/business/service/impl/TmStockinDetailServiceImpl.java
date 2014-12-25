package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
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
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.business.dao.ITmStockInDao;
import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.dao.ITmStockoutDetailDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.dao.ITmUserDao;

@Service("tmStockinDetailService")
public class TmStockinDetailServiceImpl implements ITmStockinDetailService {
	@Autowired
	private ITmStockinDetailDao tmStockinDetailDao;
	@Autowired
	private ITmStockInDao tmStockInDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmStockoutDetailDao tmStockoutDetailDao;
	@Autowired
	private ITmStockOutDao tmStockOutDao;
	@Autowired
	private ITmUserDao tmUserDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	
	public TmStockinDetail findById(Long id){
		return tmStockinDetailDao.findById(id);
	}
	
	public void insert(TmStockinDetail tmStockinDetail){
		tmStockinDetailDao.insert(tmStockinDetail);
	}
	
	public List<TmStockinDetail> findByEntity(TmStockinDetail tmStockinDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmStockinDetail.class);
		if(null!=tmStockinDetail){
			if(null!=tmStockinDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmStockinDetail.getId()));
			}
			if(null!=tmStockinDetail.getPartinfoId()){
				detachedCriteria.add(Restrictions.eq("partinfoId", tmStockinDetail.getPartinfoId()));
			}
		
			if(null!=tmStockinDetail.getStockId()){
				detachedCriteria.add(Restrictions.eq("stockId",tmStockinDetail.getStockId()));
			}
		} 
		return tmStockinDetailDao.findByCriteria(detachedCriteria, tmStockinDetail);
	}
	
	
	/**更新·
	 * 批量添加采购入库详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时增加配件表的库存数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchStockinDetail(TmStockIn tmStockIn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//保存入库主表
			tmStockInDao.insert(tmStockIn);
			//保存入库明细表
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String price  = parts.split(":")[1];						//税前单价
				String num = parts.split(":")[2];							//数量
				TmStockinDetail tsd = new TmStockinDetail();
				tsd.setPartinfoId(new Long(tbPartInfoId));
				tsd.setStockId(tmStockIn.getId());
				tsd.setQuantity(new Double(num));
				tsd.setPrice(new Double(price));
				
				if(parts.split(":").length > 3){							//销售退货特殊处理
					String stockOutDetailId = parts.split(":")[3];			//销售出库明细ID
					tsd.setStockoutDetailId(new Long(stockOutDetailId));
					tsd.setPrice(new Double(price));						//销售价
					//保存销售退货明细
					tmStockinDetailDao.insert(tsd);
					
					
					//加配件库存，不计算配件的成本价(用于销售退货模块)
					if(tmStockIn.getIsConfirm().equals(Constants.CONFIRM)){
						//更新销售退货时 销售出库明细表，主表数量
						this.updateSellReturnQuantity(tmStockIn, new Long(stockOutDetailId),new Double(num));
						//更新配件库存
						this.updateTbPartInfoNocprice(new Long(tbPartInfoId),new Double(num));
					}
					
					continue;
				}
				
				
				tmStockinDetailDao.insert(tsd);
				
				//如果的确认状态，则增加配件库存数量
				if(tmStockIn.getIsConfirm().equals(Constants.CONFIRM))
					this.partInfoStorkIn(new Long(tbPartInfoId), new Double(num),new Double(price));
			}
			
		}
	}
	
	/**
	 * 增加配件库存数量
	 * @param partInfoId			//配件id
	 * @param newQuantity			//配件数量
	 * @param userprice				//配件价格
	 * @throws MinusException 
	 */
	public void partInfoStorkIn(Long partInfoId , Double newQuantity,Double userprice) throws MinusException{
		TbPartInfo tpi = tbPartInfoDao.findById(partInfoId);
		//修改最后一次的进货价
		tpi.setStockPrice(userprice);
		double storeQuantity = tpi.getStoreQuantity() != null?tpi.getStoreQuantity():0D;
		//库存数量
		tpi.setStoreQuantity(  storeQuantity+newQuantity);
		Double lstockMoney = tpi.getStockMoney() != null?tpi.getStockMoney():0D;
		Double cstockMoney = userprice * newQuantity;
		
		/**
		 * 库存数量可能为0
		 * 之前是负数 如-1 后来是正数入了一个 然后正好是1了
		 * 这样就是出现了 除以0的情况
		 * 就会出现异常
		 * 这样的话之前的操作事务没有回滚
		 * 比如入了 A配件  B配件 C配件 D配件
		 * A B C 入库正常
		 * D配件处有异常
		 * A B C的地方数量增加了
		 * D的地方因为出现异常没有增加
		 * 导致整个入库单失败
		 * 这样就不对了
		 * 数量增加了
		 * 单子是没有的
		 */
		
		//add by ccr 2010-12-30
		BigDecimal d_lstockMoney = new BigDecimal(lstockMoney==null?"0.00":lstockMoney.toString());
		
		BigDecimal d_cstockMoney = new BigDecimal(cstockMoney==null?"0.00":cstockMoney.toString());
		
		BigDecimal d_add = d_lstockMoney.add(d_cstockMoney);
		
		BigDecimal absStoreQuantity =  new BigDecimal((Math.abs(storeQuantity)+newQuantity)) ;
		
		//BigDecimal d_storeQuantity = new BigDecimal(tpi.getStoreQuantity()==null?"0.00":tpi.getStoreQuantity().toString());
		
		BigDecimal result = d_add.divide(absStoreQuantity,BigDecimal.ROUND_HALF_UP,2).setScale(2,BigDecimal.ROUND_HALF_UP);
		
		//Double sm = (lstockMoney+cstockMoney)/tpi.getStoreQuantity();
		
		Double sm = result.doubleValue();
		
		//验证安全库存
		this.tbPartInfoService.checkPartInfoStockInQuantity(partInfoId, storeQuantity+newQuantity);
		
		//成本价
		tpi.setCostPrice(sm);
		//库存金额
		tpi.setStockMoney(tpi.getStoreQuantity()*tpi.getCostPrice());
		//更新日期
		tpi.setLastModifyDate(new Date());
		tbPartInfoDao.update(tpi);
	}
	
	/**
	 * 更新采购入库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function  
	 * @param stockInId
	 * @throws MinusException 
	 */
	public void updatePartInfoQuantity(Long stockInId) throws MinusException{
		TmStockinDetail tsd = new TmStockinDetail();
		tsd.setStockId(stockInId);
		List<TmStockinDetail> stockdetails = this.findByEntity(tsd);
		for(TmStockinDetail stockinDetail : stockdetails){
			this.partInfoStorkIn(stockinDetail.getPartinfoId(), stockinDetail.getQuantity(),stockinDetail.getPrice());
		}
	}
	
	/**更新·
	 * 批量添加采购入库详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时增加配件表的库存数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchOverFlowDetail(Long isConfirm ,Long stockInId,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];
				String num = parts.split(":")[1];
				String price  = parts.split(":")[2];
				TmStockinDetail tsd = new TmStockinDetail();
				tsd.setPartinfoId(new Long(tbPartInfoId));
				tsd.setStockId(stockInId);
				tsd.setQuantity(new Double(num));
				tsd.setPrice(new Double(price));
				tmStockinDetailDao.insert(tsd);
				
				//如果的确认状态，则增加配件库存数量
				if(isConfirm == 8002)
					this.partInfoStorkIn(new Long(tbPartInfoId), new Double(num),new Double(price));
			}
			
		}
	}

	public List<TmStockInDetailVo> getStockInDetVo(Long stockInType,Long tmStockInId,Long isConfirm) {
		List<TmStockInDetailVo> detailList = tmStockinDetailDao.getStockInDetVoNew(stockInType,tmStockInId,isConfirm);
		Double rate = 0D;
		TmStockIn tmStockIn = tmStockInDao.findById(tmStockInId);
		rate = tmStockIn.getRate()==null?1.17:tmStockIn.getRate();
		for(TmStockInDetailVo detail : detailList){
			TbPartInfo partInfo = tbPartInfoDao.findById(detail.getPartinfoId());
			detail.setHouseId(partInfo.getTmStoreHouse().getId());
			detail.setHouseCode(partInfo.getTmStoreHouse().getHouseCode());
			detail.setHouseName(partInfo.getTmStoreHouse().getHouseName());
			if(null != partInfo.getTmCarModelType())
				detail.setModelName(partInfo.getTmCarModelType().getModelName());
			detail.setPartCode(partInfo.getPartCode());
			detail.setPartName(partInfo.getPartName());
			detail.setStoreLocation(partInfo.getStoreLocation());
			detail.setUnitName(partInfo.getTmUnit().getUnitName());
			detail.setRatePrice(CommonMethod.convertRadixPoint(detail.getCostPrice()*rate,2));
		}
		
		return detailList;
	}
	
	/**
	 * 增加配件库存，不计算配件的成本价(用于销售退货模块)
	 * @param tbPartInfoId
	 * @param num
	 */
	public void updateTbPartInfoNocprice(Long tbPartInfoId , Double num){
		TbPartInfo tpi = tbPartInfoDao.findById(tbPartInfoId);
		double storeQuantity = tpi.getStoreQuantity() != null?tpi.getStoreQuantity():0D;
		tpi.setStoreQuantity(storeQuantity+num);
		tpi.setStockMoney(tpi.getStoreQuantity() * tpi.getCostPrice());
		//更新日期
		tpi.setLastModifyDate(new Date());
		tbPartInfoDao.update(tpi);
	}
	
	
	/**
	 * 批量增加销售退货明细下所对应得配件库存
	 * @param stockInId
	 */
	public void updateBatchTbPartQuan(Long stockInId){
		TmStockinDetail tsd = new TmStockinDetail();
		tsd.setStockId(stockInId);
		List<TmStockinDetail> stockdetails = this.findByEntity(tsd);
		for(TmStockinDetail stockinDetail : stockdetails){
			this.updateTbPartInfoNocprice(stockinDetail.getPartinfoId(), stockinDetail.getQuantity());
		}
		
	}

	public List<TmStockInVo> getStockInVo(Long stockInType,Long isConfirm, Long stockInId) {
		return tmStockinDetailDao.getStockInVo(stockInType,isConfirm,stockInId,null);
	}
	
	public List<TmStockInVo> getStockInVoStat(Long stockInType,Long isConfirm,TmStockIn tmStockIn) {
		return tmStockinDetailDao.getStockInVoStat(stockInType,isConfirm,tmStockIn);
	}
	
	/**
	 * 更新销售退货时 销售出库明细数量
	 */
	public void updateSellReturnQuantity(TmStockIn tmStockIn ,Long stockOutDetailId , Double num){
		//更新销售出库明细表的数量
		TmStockoutDetail tmStockoutDetail = tmStockoutDetailDao.findById(new Long(stockOutDetailId));
		Double newQuantity = tmStockoutDetail.getQuantity() - new Double(num);
		tmStockoutDetail.setQuantity(newQuantity);
		tmStockoutDetailDao.update(tmStockoutDetail);
	}
	
	/**
	 * 更新销售退货
	 */
	public void updateSellReturn(TmStockIn tmStockIn){
		tmStockInDao.update(tmStockIn);
		TmStockinDetail queryEntity = new TmStockinDetail();
		queryEntity.setStockId(tmStockIn.getId());
		List<TmStockinDetail> details = this.findByEntity(queryEntity);
		for(TmStockinDetail detail : details){
			this.updateSellReturnQuantity(tmStockIn, detail.getStockoutDetailId(), detail.getQuantity());
		}
		
		//更新销售出库主表总数，总金额
		TmStockOut stockOut = tmStockOutDao.findById(tmStockIn.getStockOutId());
		stockOut.setTotalQuantity(stockOut.getTotalQuantity() - tmStockIn.getTotalQuantity() );
		stockOut.setTotalPrice(stockOut.getTotalPrice() - tmStockIn.getTotalPrice());
		tmStockOutDao.update(stockOut);
		this.updateBatchTbPartQuan(tmStockIn.getId());
	}
	

	/**
	 * 根据入库类型查询入库单
	 * @param tmStockIn
	 * @return
	 */
	public List<TmStockInDetailVo> getSellDetailByStat(Long intype,TmStockIn tmStockIn){
		
		List<TmStockInDetailVo> detailVos =  tmStockinDetailDao.getStockInDetailVoByStat(intype, null, Constants.CONFIRM+"", tmStockIn);
		
		for(TmStockInDetailVo detVo : detailVos){
			Long userId = tmStockInDao.findById(detVo.getStockInId()).getUserId();
			String userName = tmUserDao.findById(userId).getUserName();
			TbCustomer tbCustomer  = tbCustomerService.findById(detVo.getCustomerId());
			if(null != tbCustomer){
				detVo.setCustomerName(tbCustomer.getCustomerName());
				detVo.setCustomerCode(tbCustomer.getCustomerCode());
			}
			detVo.setUserId(userId);
			detVo.setUserName(userName);
			
		}
		
		return detailVos;
	}
	
	/**
	 * 查询统计所有已入库采购单
	 * @param tmStockIn
	 * @return
	 */
	public List<TmStockInDetailVo> getSellDetailByStat(TmStockIn tmStockIn){
		
		List<TmStockInDetailVo> detailVos = 
				tmStockinDetailDao.getStockInDetailVoByStat(StockTypeElements.STOCK.getElementValue(), null, Constants.CONFIRM+"", tmStockIn);
		
		return detailVos;
	}
	
	/**
	 * 查询所有采购入库下供应商的金额汇总
	 */
	public Map<Double,List<TbCustomerVo>> getSellCustomerTotalPriceByStat(TmStockIn tmStockIn){
		List<TbCustomerVo> result = new ArrayList<TbCustomerVo>();
		List<Object[]> objList = tmStockinDetailDao.getStockInCustomerTotalPriceByStat(StockTypeElements.STOCK.getElementValue(), Constants.CONFIRM+"",tmStockIn);
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
	
	
	/**
	 * 查询统计所有已入库采购单的总金额
	 * @param tmStockIn
	 * @return
	 */
	public Double getStockInTotalPriceByStat(TmStockIn tmStockIn){
		
		return tmStockinDetailDao.getStockInTotalPriceByStat(StockTypeElements.STOCK.getElementValue(), null, Constants.CONFIRM+"", tmStockIn);
	}
	
	
	public void insertBatchStock(Map<String, String> formMap , TmStockIn tmStockIn) throws MinusException{
		//保存入库主表
		tmStockInDao.insert(tmStockIn);
	
		this.batchInsertStock(formMap, tmStockIn);
	}
	
	
	
	public void stockBatchUpdate(Map<String, String> formMap , TmStockIn tmStockIn) throws MinusException{
		
		//保存入库主表
		tmStockInDao.update(tmStockIn);
		//先删除明细，在加入新的明细
		tmStockInDao.deleteStockInDetail(tmStockIn.getId());
		
		this.batchInsertStock(formMap, tmStockIn);
		
	}
	
	
	public void batchInsertStock(Map<String, String> formMap , TmStockIn tmStockIn) throws MinusException{
		//保存入库明细表
		for(String key : formMap.keySet()){
			if(StringUtils.isNotBlank(key)){
				//配件ID	
				int tbPartInfoId = new Integer(key.substring(3));
				
				String value = formMap.get(key);
				
				//数量
				double quantity = Double.valueOf(value.split("_")[0]);
				//成本价
				double price = Double.valueOf(value.split("_")[1]);
				//产地
				String productArea = value.split("_").length > 2 ? value.split("_")[2]:null;
				
				TmStockinDetail tsd = new TmStockinDetail();
				tsd.setPartinfoId(new Long(tbPartInfoId));
				tsd.setStockId(tmStockIn.getId());
				tsd.setQuantity(quantity);
				tsd.setPrice(price);
				tsd.setProductArea(productArea);
				tmStockinDetailDao.insert(tsd);
				
				
				//如果的确认状态，则增加配件库存数量
				if(tmStockIn.getIsConfirm().equals(Constants.CONFIRM))
					this.partInfoStorkIn(new Long(tbPartInfoId),quantity,price);
			}
			
		}
		
	}
	
}
