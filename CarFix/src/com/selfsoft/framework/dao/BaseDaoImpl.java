package com.selfsoft.framework.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.selfsoft.framework.common.Constants;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IDao<T> {
	protected final Log log = LogFactory.getLog(getClass());

	protected Class<T> entityClz;

	@Autowired
	public void setHibernateSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public BaseDaoImpl() {
		Type[] te = ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments();
		if (te != null && te.length >= 1) {
			this.entityClz = (Class<T>) te[0];
		} else {
			throw new RuntimeException(
					"invalide initail, need generic type parameter!");
		}
	}

	/**
	 * Description:查询出所有结果
	 * 
	 * @return 对象的集合
	 */
	public List<T> findAll() {

		return this.getHibernateTemplate().find(
				"From " + this.entityClz.getSimpleName());
	}

	/**
	 * Description:插入对象
	 * 
	 * @param paramObj
	 *            对象参数
	 * @return 操作成功与否
	 */
	public boolean insert(T paramObj) {
		try {
			this.getHibernateTemplate().save(paramObj);
			return true;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Description:更新对象
	 * 
	 * @param paramObj
	 *            对象参数
	 * @return 操作成功与否
	 */
	public boolean update(T paramObj) {
		try {
			this.getHibernateTemplate().update(paramObj);
			return true;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Description:通过ID查找对象 如果没有对应的结果 将返回NULL
	 * 
	 * @param id
	 *            主键
	 * @return 对象
	 */
	public T findById(Long id) {

		return (T) this.getHibernateTemplate().get(this.entityClz, id);
	}

	/**
	 * Description:通过ID删除对象
	 * 
	 * @return 操作成功与否
	 */
	public boolean deleteById(final Long id) {

		try {

			final String entityName = this.entityClz.getSimpleName();

			Boolean result = (Boolean) getHibernateTemplate().execute(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createQuery("DELETE FROM "
									+ entityName + " WHERE "
									+ Constants.PRIMARY_KEY + " = " + id);
							if (query.executeUpdate() > 0) {
								return true;
							}
							return false;
						}

					});
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Description:删除指定对象 对象须有ID值
	 * 
	 * @param paramObj
	 *            对象参数
	 * @return 操作成功与否
	 */
	public boolean delete(Object paramObj) {
		try {
			this.getHibernateTemplate().delete(paramObj);
			return true;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/**
	 * Description:动态查询对象
	 * 
	 * @param detachedCriteria DetachedCriteria对象
	 * @param Object object 需要查询的对象       
	 * @return 结果集
	 */
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria,Object object) {
		return (List<T>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						
						//按照主键降序排序
						detachedCriteria.addOrder(Order.desc(Constants.PRIMARY_KEY.toLowerCase()));
						
						Criteria criteria = detachedCriteria
								.getExecutableCriteria(session);
						return criteria.list();
					}
				}, true);

	}
	/**
	 * Description:对象失效
	 * 
	 * @param id 对象主键      
	 * @return 成功与否
	 */
	public boolean updateObjectNotValid(final Long id){
		try {

			final String entityName = this.entityClz.getSimpleName();

			Boolean result = (Boolean) getHibernateTemplate().execute(
					new HibernateCallback() {

						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							Query query = session.createQuery("UPDATE "
									+ entityName + " SET " + Constants.ISVALID +"="+Constants.NOTVALIDVALUE+" WHERE "
									+ Constants.PRIMARY_KEY + " = " + id);
							if (query.executeUpdate() > 0) {
								return true;
							}
							return false;
						}

					});
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<T> findBySQL(String queryString,Object[] values){
		
		return this.getHibernateTemplate().find(queryString, values);
	}
	
	@SuppressWarnings("unchecked")
	public List findByOriginSql(final String originSql,final Object[] values){
		
		@SuppressWarnings("unused")
		List list =(List) this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(originSql);
						if(values != null){
							for(int i = 0 ; i < values.length ; i++){
								query.setParameter(i,values[i]);
							}
						}
						return query.list();
					}	
				});
		return list;
	}
}
