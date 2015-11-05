package com.selfsoft.baseinformation.service;

import java.util.List;

import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.vo.TbPartInfoVo;
import com.selfsoft.common.exception.MinusException;

public interface ITbPartInfoService {

	public void insert(String soleTypes,TbPartInfo tbPartInfo);
	
	public TbPartInfo findById(Long Id);
	
	public void update(String soleTypes ,TbPartInfo tbPartInfo);
	
	public List<TbPartInfo> findByEntity(TbPartInfo tbPartInfo);
	
	public boolean deleteById(Long id);
	
	public List<TbPartInfo> getTbPartInfoByCollectionCode(String collectionCode);
	
	public void updateTbPartInfoStoreQuantity(Long tbPartInfoId , Double num);
	
	public Double getTbPartInfoCostPrice(Long tbPartInfoId);
	/**
	 * added by ccr
	 * 根据车辆类型ID来查找配件
	 * @param tmCarModelTypeId
	 * @return
	 */
	public List<TbPartInfo> findPartListByTmCarModelTypeId(Long tmCarModelTypeId);
	
	public TbPartInfo getPartInfoByCodeAndStore(String partCode,Long storeHoseId);
	
	public boolean updateCostPrice(Long id , Double newCostPrice);
	
	public List<TbPartInfoVo> getTbPartInfoVoByCustomerId(TbPartInfo tbPartInfo ,String customerId);
	
	public Double getTotalStockMoney(TbPartInfo tbPartInfo);
	
	public List<TbPartInfo> checkHousePartInfo(String parameters);
	
	public TbPartInfo findByCode(String partCode);
	
	public void checkPartInfoStockInQuantity(Long tbPartInfoId , Double newStoreQuantity)throws MinusException;

	public void updateAllNotRightStoreQuantity();
}
