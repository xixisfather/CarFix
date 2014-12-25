package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbBookDao;
import com.selfsoft.business.model.TbBook;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbBookDao")
public class TbBookDaoImpl extends BaseDaoImpl<TbBook> implements ITbBookDao{

}
