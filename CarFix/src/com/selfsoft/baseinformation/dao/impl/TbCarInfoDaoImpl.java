package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbCarInfoDao;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbCarInfoDao")
public class TbCarInfoDaoImpl extends BaseDaoImpl<TbCarInfo> implements ITbCarInfoDao{

}
