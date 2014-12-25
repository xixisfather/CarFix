package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbPriceAddDao;
import com.selfsoft.baseinformation.model.TbPriceAdd;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbPriceAddDao")
public class TbPriceAddDaoImpl extends BaseDaoImpl<TbPriceAdd> implements
		ITbPriceAddDao {

}
