package com.selfsoft.baseinformation.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartSolePriceDao;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartSolePrice;
import com.selfsoft.baseinformation.service.ITbPartSolePriceService;
import com.selfsoft.baseparameter.dao.ITmSoleTypeDao;
import com.selfsoft.baseparameter.model.TmSoleType;
@Service("tbPartSolePriceService")
public class TbPartSolePriceServiceImpl implements ITbPartSolePriceService {

	@Autowired
	private ITbPartSolePriceDao tbPartSolePriceDao;
	@Autowired
	private ITmSoleTypeDao tmSoleTypeDao;
	
	public List<TbPartSolePrice> findAll(){
		return tbPartSolePriceDao.findAll();
	}
	
	public TbPartSolePrice findById(Long id){
		return tbPartSolePriceDao.findById(id);
	}

	public List<TbPartSolePrice> findByEntity(TbPartSolePrice tbPartSolePrice){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbPartSolePrice.class);
		
		if(null!=tbPartSolePrice){
			if(null!=tbPartSolePrice.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbPartSolePrice.getId()));
			}
			
			if(null!=tbPartSolePrice.getPartInfoId()){
				detachedCriteria.add(Restrictions.eq("partInfoId", tbPartSolePrice.getPartInfoId()));
			}
			if(null!=tbPartSolePrice.getSoleTypeId()){
				detachedCriteria.add(Restrictions.eq("soleTypeId", tbPartSolePrice.getSoleTypeId()));
			}
		}
		return tbPartSolePriceDao.findByCriteria(detachedCriteria, tbPartSolePrice);
		
		
	}
	
	public Map<TbPartSolePrice, String> getSolePriceMap(Long partInfoId ){
		Map<TbPartSolePrice, String> result = new LinkedHashMap<TbPartSolePrice, String>();
		TbPartSolePrice tpsp = new TbPartSolePrice();
		tpsp.setPartInfoId(partInfoId);
		List<TbPartSolePrice> tpspList = this.findByEntity(tpsp);
		
		for(TbPartSolePrice tbsp : tpspList){
			TmSoleType soleType = tmSoleTypeDao.findById(tbsp.getSoleTypeId());
			result.put(tbsp, soleType.getSoleName());
		}
		
		return result;
	}

	@Override
	public void update(TbPartSolePrice tbPartSolePrice) {
		tbPartSolePriceDao.update(tbPartSolePrice);
	}
}
