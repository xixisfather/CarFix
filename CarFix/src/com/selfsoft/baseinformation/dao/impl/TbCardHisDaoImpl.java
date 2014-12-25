package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbCardHisDao;
import com.selfsoft.baseinformation.model.TbCardHis;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbCardHisDao")
public class TbCardHisDaoImpl extends BaseDaoImpl<TbCardHis> implements ITbCardHisDao{

}
