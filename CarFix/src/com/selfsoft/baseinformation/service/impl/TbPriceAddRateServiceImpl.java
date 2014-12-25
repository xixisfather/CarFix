package com.selfsoft.baseinformation.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.dao.ITbPriceAddRateDao;
import com.selfsoft.baseinformation.model.TbCarStationWorkingRelation;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.baseinformation.model.TbPriceAdd;
import com.selfsoft.baseinformation.model.TbPriceAddRate;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseinformation.service.ITbPartSolePriceService;
import com.selfsoft.baseinformation.service.ITbPriceAddRateService;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbPriceAddRateService")
public class TbPriceAddRateServiceImpl implements ITbPriceAddRateService {

	@Autowired
	private ITbPriceAddRateDao tbPriceAddRateDao;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITbPartSolePriceService tbPartSolePriceService;
	
	@Override
	public boolean deleteById(Long id) {
		return tbPriceAddRateDao.deleteById(id);
	}

	@Override
	public List<TbPriceAddRate> findByEntity(TbPriceAddRate tbPriceAddRate) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbPriceAddRate.class);
		
		if(null!=tbPriceAddRate){
			if(null!=tbPriceAddRate.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbPriceAddRate.getId()));
			}
		}
		return tbPriceAddRateDao.findByCriteria(detachedCriteria, tbPriceAddRate);

	}

	@Override
	public TbPriceAddRate findById(Long id) {
		return tbPriceAddRateDao.findById(id);
	}

	@Override
	public void insert(TbPriceAddRate tbPriceAddRate) {
		tbPriceAddRateDao.insert(tbPriceAddRate);
		
	}

	@Override
	public void update(TbPriceAddRate tbPriceAddRate) {
		tbPriceAddRateDao.update(tbPriceAddRate);		
	}
	
	
	public void synTbPartInfoSellPrice(Long id) {
		TbPriceAddRate tbPartAddRate = this.findById(id);
		TbPartInfo tbPartInfo = new TbPartInfo();
		tbPartInfo.setMinPrice(tbPartAddRate.getMinPrice());
		tbPartInfo.setMaxPrice(tbPartAddRate.getMaxPrice());
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		Double rate = 1 + (tbPartAddRate.getRate() / 100);
		for(TbPartInfo partInfo : tbPartInfoList){
			Double sellPrice = CommonMethod.convertRadixPoint(partInfo.getSellPrice()*rate, 2);
			partInfo.setSellPrice(sellPrice);
			tbPartInfoDao.update(partInfo);

			
			TbPartSolePrice tbPartSolePrice = new TbPartSolePrice();
			tbPartSolePrice.setPartInfoId(partInfo.getId());
			List<TbPartSolePrice> partSolePriceList = tbPartSolePriceService.findByEntity(tbPartSolePrice);
			for(TbPartSolePrice partSolePrice : partSolePriceList){
				Double solePrice = CommonMethod.convertRadixPoint(partSolePrice.getSolePrice()*rate, 2);
				partSolePrice.setSolePrice(solePrice);
				tbPartSolePriceService.update(partSolePrice);
			}
		}
	}
}
