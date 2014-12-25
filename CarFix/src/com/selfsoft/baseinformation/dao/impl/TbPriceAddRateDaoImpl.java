package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbPriceAddRateDao;
import com.selfsoft.baseinformation.model.TbPriceAddRate;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbPriceAddRateDao")
public class TbPriceAddRateDaoImpl extends BaseDaoImpl<TbPriceAddRate> implements
		ITbPriceAddRateDao {

}
