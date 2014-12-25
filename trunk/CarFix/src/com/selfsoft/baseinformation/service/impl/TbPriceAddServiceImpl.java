package com.selfsoft.baseinformation.service.impl;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.dao.ITbPriceAddDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.baseinformation.model.TbPriceAdd;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseinformation.service.ITbPartSolePriceService;
import com.selfsoft.baseinformation.service.ITbPriceAddService;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbPriceAddService")
public class TbPriceAddServiceImpl implements ITbPriceAddService {

	@Autowired
	private ITbPriceAddDao tbPriceAddDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITbPartSolePriceService tbPartSolePriceService;
	
	@Override
	public boolean deleteById(Long id) {
		return tbPriceAddDao.deleteById(id);
	}

	@Override
	public List<TbPriceAdd> findByEntity(TbPriceAdd tbPriceAdd) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbPriceAdd.class);
		
		if(null!=tbPriceAdd){
			if(null!=tbPriceAdd.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbPriceAdd.getId()));
			}
		}
		return tbPriceAddDao.findByCriteria(detachedCriteria, tbPriceAdd);
	}

	@Override
	public TbPriceAdd findById(Long id) {
		return tbPriceAddDao.findById(id);
	}

	@Override
	public void insert(TbPriceAdd tbPriceAdd) {
		tbPriceAddDao.insert(tbPriceAdd);
	}

	@Override
	public void update(TbPriceAdd tbPriceAdd) {
		tbPriceAddDao.update(tbPriceAdd);
	}

	public void synTbPartInfoSellPrice(Long id) {
		TbPriceAdd tbPartAdd = this.findById(id);
		TbPartInfo tbPartInfo = new TbPartInfo();
		tbPartInfo.setMinPrice(tbPartAdd.getMinPrice());
		tbPartInfo.setMaxPrice(tbPartAdd.getMaxPrice());
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		for(TbPartInfo partInfo : tbPartInfoList){
			Double sellPrice = CommonMethod.convertRadixPoint(partInfo.getSellPrice()+tbPartAdd.getPrice(), 2);
			partInfo.setSellPrice(sellPrice);
			tbPartInfoDao.update(partInfo);

			
			TbPartSolePrice tbPartSolePrice = new TbPartSolePrice();
			tbPartSolePrice.setPartInfoId(partInfo.getId());
			List<TbPartSolePrice> partSolePriceList = tbPartSolePriceService.findByEntity(tbPartSolePrice);
			for(TbPartSolePrice partSolePrice : partSolePriceList){
				Double solePrice = CommonMethod.convertRadixPoint(partSolePrice.getSolePrice()+tbPartAdd.getPrice(), 2);
				partSolePrice.setSolePrice(solePrice);
				tbPartSolePriceService.update(partSolePrice);
			}
		}
	}
}
