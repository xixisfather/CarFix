package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbWorkingInfoDao;
import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbWorkingInfoDao")
public class TbWorkingInfoDaoImpl extends BaseDaoImpl<TbWorkingInfo>implements ITbWorkingInfoDao{

}
