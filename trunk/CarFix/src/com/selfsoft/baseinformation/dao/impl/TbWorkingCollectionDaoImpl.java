package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbWorkingCollectionDao;
import com.selfsoft.baseinformation.model.TbWorkingCollection;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbWorkingCollectionDao")
public class TbWorkingCollectionDaoImpl extends BaseDaoImpl<TbWorkingCollection> implements ITbWorkingCollectionDao {

}
