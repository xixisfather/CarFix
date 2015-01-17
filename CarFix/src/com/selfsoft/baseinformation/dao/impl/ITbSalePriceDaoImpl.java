package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbSalePriceDao;
import com.selfsoft.baseinformation.model.TbSalePrice;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbSalePriceDao")
public class ITbSalePriceDaoImpl extends BaseDaoImpl<TbSalePrice> implements ITbSalePriceDao{

}
