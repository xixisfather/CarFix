package com.selfsoft.baseinformation.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbMembershipCardDao;
import com.selfsoft.baseinformation.model.TbMembershipCard;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbMembershipCardDao")
public class TbMembershipCardDaoImpl extends BaseDaoImpl<TbMembershipCard> implements ITbMembershipCardDao{

}
