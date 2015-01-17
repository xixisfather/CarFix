package com.selfsoft.baseinformation.service;

import com.selfsoft.baseinformation.model.TbSalePrice;


public interface ITbSalePriceService {

	public  TbSalePrice findAll();
	
	public boolean update(TbSalePrice tbSalePrice);
}
