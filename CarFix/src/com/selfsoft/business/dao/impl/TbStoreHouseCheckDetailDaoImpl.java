package com.selfsoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbStoreHouseCheckDetailDao;
import com.selfsoft.business.model.TbStoreHouseCheckDetail;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbStoreHouseCheckDetailDao")
public class TbStoreHouseCheckDetailDaoImpl extends BaseDaoImpl<TbStoreHouseCheckDetail> implements
		ITbStoreHouseCheckDetailDao {

	public void deleteCheckDetail(Long checkId){
		String hql = "delete from TbStoreHouseCheckDetail t where t.tbStoreHouseCheck.id = "+checkId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
