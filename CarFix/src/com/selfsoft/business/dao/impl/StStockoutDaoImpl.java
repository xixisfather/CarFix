package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.IStStockoutDao;
import com.selfsoft.business.model.StStockout;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("stStockoutDao")
public class StStockoutDaoImpl extends BaseDaoImpl<StStockout> implements IStStockoutDao {

}
