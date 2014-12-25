package com.selfsoft.secrity.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.util.ResolverUtil.IsA;
import com.selfsoft.framework.dao.BaseDaoImpl;
import com.selfsoft.secrity.dao.ITmResourceDao;
import com.selfsoft.secrity.model.TmResource;

@Repository("tmResourceDao")
public class TmResourceDaoImpl extends BaseDaoImpl<TmResource> implements
		ITmResourceDao {

	public List<TmResource> getOneLevelChildrenTmResource(Long id) {

		String hql = "from TmResource t where t.parentId = " + id;

		List<TmResource> result = getHibernateTemplate().find(hql);

		return result;
	}

	public boolean hasChildren(Long id) {
		List<TmResource> children = this.getOneLevelChildrenTmResource(id);
		if (children != null && children.size() > 0)
			return true;
		return false;
	}

	public List<TmResource> getOneLevelChildren(Long id , Boolean isFather) {

		String hql = "from TmResource t where t.parentId = " + id;
		if(isFather !=  null  && isFather == true)
			hql += " or  t.id = "+id;

		List<TmResource> result = getHibernateTemplate().find(hql);

		return result;
	}
	
	public Boolean deleteTmResourceAndChildren(final Long id){
		Boolean result = (Boolean) this.getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "delete from TmResource t where t.id="+id+" or t.parentId="+id;
				Query query = session.createQuery(hql);
				int i =query.executeUpdate();
				if(i >0){
					return true;
				}
				return false;
			}
			
			
		});
		
		return result;
	}
	
	/**
	 * 得到资源表根节点
	 * @return
	 * @throws Exception
	 */
	public TmResource getRootNode() throws Exception{
		String hql = "from TmResource t where t.parentId is null ";
		List<TmResource> result = getHibernateTemplate().find(hql);
		if(result == null || result.size() == 0)
			throw new Exception("资源表尚未初始化");
		return result.get(0);
	}

}
