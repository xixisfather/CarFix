package com.selfsoft.baseinformation.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbPartCollectionDao;
import com.selfsoft.baseinformation.model.TbPartCollection;
import com.selfsoft.baseinformation.vo.TbPartCollectionVo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbPartCollectionDao")
public class TbPartCollectionDaoImpl extends BaseDaoImpl<TbPartCollection> implements ITbPartCollectionDao {

	public List<TbPartCollectionVo> getTbPartCollection(){
		StringBuilder hql = new StringBuilder();
		
		//hql.append("select new com.selfsoft.baseinformation.vo.TbPartCollectionVo(t.collectionCode)");
		hql.append(" select  t.collectionCode , t.createDate from TbPartCollection t group by t.collectionCode , t.createDate");
		
		
		List<Object[]> result = this.getHibernateTemplate().find(hql.toString());
		List<TbPartCollectionVo> voList =  new ArrayList<TbPartCollectionVo>();
		for(Object[] objects : result){
			String collectionCode = objects[0].toString();
			Timestamp dateString = (Timestamp) objects[1];
			Date date = new Date(dateString.getTime());
			TbPartCollectionVo vo = new TbPartCollectionVo(collectionCode,date);
			voList.add(vo);
		}
		return voList;
		
	}
	
	
	public int deleteByCollectionCode(String collectionCode){
		String hql = "delete from TbPartCollection t where t.collectionCode='"+collectionCode+"'";
		Query query = this.getSession(false).createQuery(hql);
		int row = query.executeUpdate();
		return row;
	}
	
	
	 
}
