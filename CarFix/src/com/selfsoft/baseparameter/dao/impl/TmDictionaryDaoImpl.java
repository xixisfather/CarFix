package com.selfsoft.baseparameter.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.selfsoft.baseparameter.dao.ITmDictionaryDao;
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmDictionaryDao")
public class TmDictionaryDaoImpl extends BaseDaoImpl<TmDictionary> implements ITmDictionaryDao {


	public List<TmDictionary> findByDictionaryType(String type){
		String hql = "from TmDictionary t where t.paramtype = '"+type+"'";
		return this.getHibernateTemplate().find(hql);
	}

	
	public void resetParamValueByType(String types){
		String hql = "update TmDictionary t set t.paramvalue = 0 where t.paramtype in ("+types+")";
		Query quan = this.getSession(false).createQuery(hql);
		quan.executeUpdate();
	}
	
	
	public List<TmDictionary> findByZeroMounth(){
		String currDateString = CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd");
		String hql = "from TmDictionary t where  datediff(month,t.zeroMonth,' "+currDateString+"') = 0";
		return this.getHibernateTemplate().find(hql);
	}
	
	public void updateCurrMonthStatus(){
		String hql = "update TmDictionary t set t.zeroMonth = ? , t.zeroStatus = ? ";
		this.getHibernateTemplate().bulkUpdate(hql, new Object[]{new Date(),1L});
	}
	
	public void resetParamValue(){
		System.out.println("所有单号序号清零");
		String types = StringUtils.join(StockTypeElements.getQueryString(), ",");
		String hql = "update TmDictionary t set t.paramvalue = 0 where t.paramtype in ("+types+")";
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	public List<TmDictionary> findNullStatus(){
		String currDateString = CommonMethod.parseDateToString(new Date(), "yyyy-MM-dd");
		String hql = "from TmDictionary t where t.zeroMonth is null and t.zeroStatus is null";
		return this.getHibernateTemplate().find(hql);
		
	}
	
}
