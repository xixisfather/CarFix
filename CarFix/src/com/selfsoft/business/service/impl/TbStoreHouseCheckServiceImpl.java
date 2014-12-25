package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.dao.ITbStoreHouseCheckDao;
import com.selfsoft.business.model.TbStoreHouseCheck;
import com.selfsoft.business.model.TbStoreHouseCheckDetail;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITbStoreHouseCheckDetailService;
import com.selfsoft.business.service.ITbStoreHouseCheckService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
@Service("tbStoreHouseCheckService")
public class TbStoreHouseCheckServiceImpl implements ITbStoreHouseCheckService {

	@Autowired
	private ITbStoreHouseCheckDao tbStoreHouseCheckDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbStoreHouseCheckDetailService tbStoreHouseCheckDetailService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmStockoutDetailService tmStockoutDetailService;
	
	@Override
	public boolean deleteById(Long id) {
		
		tbStoreHouseCheckDetailService.deleteCheckDetail(id);

		return tbStoreHouseCheckDao.deleteById(id);
		
	}

	@Override
	public List<TbStoreHouseCheck> findByEntity(TbStoreHouseCheck tbStoreHouseCheck) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbStoreHouseCheck.class);
		
		if(null!=tbStoreHouseCheck){
			if(null!=tbStoreHouseCheck.getId()){
				detachedCriteria.add(Restrictions.eq("id",tbStoreHouseCheck.getId()));
			}
			if(StringUtils.isNotBlank(tbStoreHouseCheck.getCheckCode())){
				detachedCriteria.add(Restrictions.like("checkCode", "%"+tbStoreHouseCheck.getCheckCode()+"%"));
			}
			if(StringUtils.isNotBlank(tbStoreHouseCheck.getBeginCheckDate())){
				detachedCriteria.add(Restrictions.ge("checkDate", CommonMethod.parseStringToDate(tbStoreHouseCheck.getBeginCheckDate(),"yyyy-MM-dd")));
			}
			if(StringUtils.isNotBlank(tbStoreHouseCheck.getEndCheckDate())){
				detachedCriteria.add(Restrictions.le("checkDate", CommonMethod.parseStringToDate(tbStoreHouseCheck.getEndCheckDate(),"yyyy-MM-dd")));
			}
			if(null!=tbStoreHouseCheck.getIsConfirm()){
				detachedCriteria.add(Restrictions.eq("isConfirm",tbStoreHouseCheck.getIsConfirm()));
			}
			if(StringUtils.isNotBlank(tbStoreHouseCheck.getBeginZzDate())){
				detachedCriteria.add(Restrictions.ge("zzDate", CommonMethod.parseStringToDate(tbStoreHouseCheck.getBeginZzDate(),"yyyy-MM-dd")));
			}
			if(StringUtils.isNotBlank(tbStoreHouseCheck.getEndZzDate())){
				detachedCriteria.add(Restrictions.le("zzDate", CommonMethod.parseStringToDate(tbStoreHouseCheck.getEndZzDate(),"yyyy-MM-dd")));
			}
			if(null!=tbStoreHouseCheck.getTmUser()){
				
				if(null!=tbStoreHouseCheck.getTmUser().getId()){
					
					detachedCriteria.createAlias("tmUser","tmUser");
					
					detachedCriteria.add(Restrictions.eq("tmUser.id",tbStoreHouseCheck.getTmUser().getId()));
					
				}
			}
		}
		
		return tbStoreHouseCheckDao.findByCriteria(detachedCriteria, tbStoreHouseCheck);
	}

	@Override
	public TbStoreHouseCheck findById(Long id) {
		return tbStoreHouseCheckDao.findById(id);
	}

	@Override
	public void insertTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck) {
		tbStoreHouseCheckDao.insert(tbStoreHouseCheck);
	}

	@Override
	public void updateTbStoreHouseCheck(TbStoreHouseCheck tbStoreHouseCheck) {
		tbStoreHouseCheckDao.update(tbStoreHouseCheck);
	}
	
	
	public void insertBatchStoreHouseCheck(Map<String, String> formMap , TbStoreHouseCheck tbStoreHouseCheck){
		
		if(tbStoreHouseCheck.getIsConfirm().equals(Constants.CHECKINTO)){
			tbStoreHouseCheck.setZzDate(new Date());
		}
		
		tbStoreHouseCheck.setCheckCode(tmDictionaryService.GenerateCode(StockTypeElements.CHECK.getElementString()));
		tbStoreHouseCheckDao.insert(tbStoreHouseCheck);
		
		this.batchMapCheckInsert(formMap, tbStoreHouseCheck);
	}
	
	
	public void updateBatchStoreHouseCheck(Map<String, String> formMap , TbStoreHouseCheck tbStoreHouseCheck){
		
		if(tbStoreHouseCheck.getIsConfirm().equals(Constants.CHECKINTO)){
			tbStoreHouseCheck.setZzDate(new Date());
		}
		
		tbStoreHouseCheckDetailService.deleteCheckDetail(tbStoreHouseCheck.getId());
		
		tbStoreHouseCheckDao.update(tbStoreHouseCheck);
		
		this.batchMapCheckInsert(formMap, tbStoreHouseCheck);
	}
	
	public void batchMapCheckInsert(Map<String, String> formMap , TbStoreHouseCheck tbStoreHouseCheck){
		
		//保存入库明细表
		for(String key : formMap.keySet()){
			if(StringUtils.isNotBlank(key)){
				//配件ID	
				int tbPartInfoId = new Integer(key.substring(3));
				
				String value = formMap.get(key);
				
				TbPartInfo tbPartInfo = tbPartInfoService.findById(new Long(tbPartInfoId));
				
				//成本价
				double costPrice = tbPartInfo.getCostPrice();
				//数量
				double storeQuantity = tbPartInfo.getStoreQuantity();
				//盘点数量
				double checkQuantity = Double.valueOf(value.split("_")[0]);
				//差异数量
				double divQuantity = Double.valueOf(value.split("_")[1]);
				//备注
//				String memo = value.split("_")[2];
				
				
				
				TbStoreHouseCheckDetail shcd = new TbStoreHouseCheckDetail();
				shcd.setCheckPrice(CommonMethod.convertRadixPoint(checkQuantity*costPrice, 2));			//盘点金额
				shcd.setCheckQuantity(checkQuantity);
				shcd.setCostPrice(costPrice);
				shcd.setStoreQuantity(storeQuantity);
				shcd.setDivQuantity(divQuantity);
				shcd.setDivPrice(CommonMethod.convertRadixPoint(divQuantity*costPrice, 2));				//差异金额
				shcd.setJsPrice(CommonMethod.convertRadixPoint(storeQuantity*costPrice, 2));			//实际账面金额
				shcd.setTbPartInfo(tbPartInfo);
				shcd.setTbStoreHouseCheck(tbStoreHouseCheck);
				shcd.setTmStoreHouse(tbPartInfo.getTmStoreHouse());
				
				tbStoreHouseCheckDetailService.insertTbStoreHouseCheckDetail(shcd);
				
				if(tbStoreHouseCheck.getIsConfirm().equals(Constants.CHECKINTO)){
					this.detailCheck(shcd);
				}
				
			}
		}
		
		

	}
	
	
	private void detailCheck(TbStoreHouseCheckDetail tbStoreHouseCheckDetail){
		//差异为0 不做任何处理
		if(tbStoreHouseCheckDetail.getDivQuantity() == 0)
			return;
			
		Long userId = tbStoreHouseCheckDetail.getTbStoreHouseCheck().getTmUser().getId();
		
		//账面库存大于0
		if(tbStoreHouseCheckDetail.getStoreQuantity() > 0){
			
			//差异数量>0  走报溢
			if(tbStoreHouseCheckDetail.getDivQuantity() > 0){
				
				this.stockOverFlow(userId, tbStoreHouseCheckDetail);
			}
			
			//差异数量<0  走报损
			if(tbStoreHouseCheckDetail.getDivQuantity() < 0){
				this.stockShatter(userId, tbStoreHouseCheckDetail);
			}
		}
		
		
		//账面库存小于于0
		if(tbStoreHouseCheckDetail.getStoreQuantity() < 0){
			
			//差异数量<0  走报溢
			if(tbStoreHouseCheckDetail.getDivQuantity() < 0){
				this.stockOverFlow(userId, tbStoreHouseCheckDetail);
			}
			
			//差异数量>0  走报损
			if(tbStoreHouseCheckDetail.getDivQuantity() > 0){
				this.stockShatter(userId, tbStoreHouseCheckDetail);
			}
		}
	}
	
	
	private void stockOverFlow(Long userId,TbStoreHouseCheckDetail tbStoreHouseCheckDetail){
		TmStockIn tmStockIn = new TmStockIn();
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.OVERFLOW.getElementString()));
		tmStockIn.setUserId(userId);
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setArriveDate(new Date());
		tmStockIn.setInType(StockTypeElements.OVERFLOW.getElementValue());
		tmStockIn.setIsConfirm(Constants.CONFIRM);
		
		tmStockInService.insert(tmStockIn);
		
			
		TmStockinDetail tmStockinDetail = new TmStockinDetail();
		tmStockinDetail.setStockId(tmStockIn.getId());
		tmStockinDetail.setPartinfoId(tbStoreHouseCheckDetail.getTbPartInfo().getId());
		tmStockinDetail.setQuantity(Math.abs(tbStoreHouseCheckDetail.getDivQuantity()));
		tmStockinDetail.setPrice(tbStoreHouseCheckDetail.getCostPrice());
		
		tmStockinDetailService.insert(tmStockinDetail);
	}
	
	
	private void stockShatter(Long userId,TbStoreHouseCheckDetail tbStoreHouseCheckDetail){
		TmStockOut tmStockOut = new TmStockOut();
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.SHATTERSTOCKOUT.getElementString()));
		tmStockOut.setUserId(userId);
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setStockOutDate(new Date());
		tmStockOut.setOutType(StockTypeElements.SHATTERSTOCKOUT.getElementValue());	
		tmStockOut.setIsConfirm(Constants.CONFIRM);		
		tmStockOutService.insert(tmStockOut);
			
		TmStockoutDetail tmStockoutDetail = new TmStockoutDetail();
		tmStockoutDetail.setStockoutId(tmStockOut.getId());
		tmStockoutDetail.setPartinfoId(tbStoreHouseCheckDetail.getTbPartInfo().getId());
		tmStockoutDetail.setQuantity(Math.abs(tbStoreHouseCheckDetail.getDivQuantity()));
		tmStockoutDetail.setPrice(tbStoreHouseCheckDetail.getCostPrice());
		tmStockoutDetailService.insert(tmStockoutDetail);
			
	}
	
	
}
