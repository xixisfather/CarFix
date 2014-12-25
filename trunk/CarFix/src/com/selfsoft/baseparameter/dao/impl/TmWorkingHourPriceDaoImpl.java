package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmWorkingHourPriceDao;
import com.selfsoft.baseparameter.model.TmWorkingHourPrice;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmWorkingHourPriceDao")
public class TmWorkingHourPriceDaoImpl extends BaseDaoImpl<TmWorkingHourPrice> implements ITmWorkingHourPriceDao{

}
