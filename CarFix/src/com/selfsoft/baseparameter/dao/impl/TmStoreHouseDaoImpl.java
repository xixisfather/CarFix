package com.selfsoft.baseparameter.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmStoreHouseDao;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmStoreHouseDao")
public class TmStoreHouseDaoImpl extends BaseDaoImpl<TmStoreHouse> implements ITmStoreHouseDao{

}
