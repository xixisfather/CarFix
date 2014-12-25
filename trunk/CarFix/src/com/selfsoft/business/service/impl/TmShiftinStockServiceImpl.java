package com.selfsoft.business.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.dao.ITmRemoveStockDao;
import com.selfsoft.business.dao.ITmShiftinStockDao;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.model.TmRemoveStockDetail;
import com.selfsoft.business.model.TmShiftinStock;
import com.selfsoft.business.service.ITmRemoveStockDetailService;
import com.selfsoft.business.service.ITmShiftinStockService;
import com.selfsoft.business.vo.TmShiftinStockVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
@Service("tmShiftinStockService")
public class TmShiftinStockServiceImpl implements ITmShiftinStockService {

	@Autowired
	private ITmShiftinStockDao tmShiftinStockDao;
	@Autowired
	private ITmRemoveStockDao tmRemoveStockDao;
	@Autowired
	private ITmRemoveStockDetailService tmRemoveStockDetailService;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	
	public void insert(TmShiftinStock tmShiftinStock) throws MinusException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		tmShiftinStockDao.insert(tmShiftinStock);
		
		if(tmShiftinStock.getIsConfirm().equals(Constants.CONFIRM)){
			//更新出仓状态
			updateRemStockState(tmShiftinStock.getRemoveStockId());
			//更新配件数量
			updatePartInfoQuantity(tmShiftinStock);
		}
	}
	
	public void update(TmShiftinStock tmShiftinStock) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, MinusException{
		tmShiftinStockDao.update(tmShiftinStock);
		updateRemStockState(tmShiftinStock.getRemoveStockId());
		updatePartInfoQuantity(tmShiftinStock);
	}
	
	public TmShiftinStock findById(Long id){
		return tmShiftinStockDao.findById(id);
	}
	
	/**
	 * 更新出仓状态(把移库出仓状态设为入账)
	 * @param removeStockId
	 */
	public void updateRemStockState(Long removeStockId){
		TmRemoveStock removeStock = tmRemoveStockDao.findById(removeStockId);
		removeStock.setIsValid(Constants.ISVALIDVALUE);
		tmRemoveStockDao.update(removeStock);
		
	}
	
	public List<TmShiftinStockVo> getTmshiftinStockVos(Long isConfirm , TmShiftinStock tmShiftinStock , Long shiftInId){
		return tmShiftinStockDao.getTmshiftinStockVos(isConfirm, tmShiftinStock,shiftInId);
	}
	
	public void updatePartInfoQuantity(TmShiftinStock tmShiftinStock) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, MinusException{
		//更新配件库存量
		TmRemoveStockDetail queryEntity = new TmRemoveStockDetail();
		queryEntity.setRemoveStockId(tmShiftinStock.getRemoveStockId());
		List<TmRemoveStockDetail> removeStockDetails = tmRemoveStockDetailService.findByEntity(queryEntity);
		for (TmRemoveStockDetail detail : removeStockDetails) {
			//源仓库的配件减少数量， 如果目标仓库有此配件则新增数量，没有的话则在目标仓库中新增源仓库中的配件
			TbPartInfo sourcePartInfo = tbPartInfoService.findById(detail.getPartinfoId());
			
			TbPartInfo targetPartInfo = tbPartInfoService.getPartInfoByCodeAndStore(sourcePartInfo.getPartCode(), tmShiftinStock.getStoreHoseId());
			if(targetPartInfo != null){
				Double newQuantity = targetPartInfo.getStoreQuantity() + detail.getQuantity();
				targetPartInfo.setStoreQuantity(newQuantity);
				targetPartInfo.setStockMoney(targetPartInfo.getStoreQuantity()* targetPartInfo.getCostPrice());
				//更新日期
				targetPartInfo.setLastModifyDate(new Date());
				tbPartInfoDao.update(targetPartInfo);
			}else{
				TmStoreHouse storeHouse = tmStoreHouseService.findById(tmShiftinStock.getStoreHoseId());
				TbPartInfo newPartInfo = new TbPartInfo();
				PropertyUtils.copyProperties(newPartInfo, sourcePartInfo);
				newPartInfo.setId(null);
				newPartInfo.setTmStoreHouse(storeHouse);
				newPartInfo.setLianceQuantity(0D);
				newPartInfo.setLoanQuantity(0D);
				newPartInfo.setStoreQuantity(detail.getQuantity());
				newPartInfo.setStockMoney(newPartInfo.getStoreQuantity()* newPartInfo.getCostPrice());
				newPartInfo.setTbBookFixParts(null);
				newPartInfo.setLianceRegDets(null);
				newPartInfo.setLoanRegDets(null);
				newPartInfo.setTbBespokePartContents(null);
				//更新日期
				newPartInfo.setLastModifyDate(new Date());
				tbPartInfoDao.insert(newPartInfo);
			}
			
			Double newStoreQuantity =  sourcePartInfo.getStoreQuantity()- detail.getQuantity();
			boolean flag = tmDictionaryService.isMinusStockOnt();
			if(flag == false && newStoreQuantity < 0  )
				throw new MinusException();
			sourcePartInfo.setStoreQuantity(newStoreQuantity);
			sourcePartInfo.setStockMoney(sourcePartInfo.getStoreQuantity()* sourcePartInfo.getCostPrice());
			//更新日期
			sourcePartInfo.setLastModifyDate(new Date());
			tbPartInfoDao.update(sourcePartInfo);
		}
	}
	
	public void updateTmShiftinStock(TmShiftinStock tmShiftinStock){
		tmShiftinStockDao.update(tmShiftinStock);
	}
	
	public boolean deleteById(Long id){
		return tmShiftinStockDao.deleteById(id);
	}

}
