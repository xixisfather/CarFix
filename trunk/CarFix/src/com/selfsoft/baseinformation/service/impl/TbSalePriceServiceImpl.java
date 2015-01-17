package com.selfsoft.baseinformation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbSalePriceDao;
import com.selfsoft.baseinformation.model.TbSalePrice;
import com.selfsoft.baseinformation.service.ITbSalePriceService;

@Service("tbSalePriceService")
public class TbSalePriceServiceImpl implements ITbSalePriceService{

	@Autowired
	private ITbSalePriceDao tbSalePriceDao;

	@Override
	public TbSalePrice findAll() {
		return tbSalePriceDao.findAll().get(0);
	}

	@Override
	public boolean update(TbSalePrice tbSalePrice) {
		// TODO Auto-generated method stub
		return tbSalePriceDao.update(tbSalePrice);
	}
	
	
}
