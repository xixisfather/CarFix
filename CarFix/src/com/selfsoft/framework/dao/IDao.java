package com.selfsoft.framework.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IDao <T>{
	
	public boolean insert(T paramObj);
	
	public boolean update(T paramObj);
	
	public List<T> findAll();
	
	public T findById(Long id);
	
	public boolean deleteById(Long id);
	
	public boolean delete(Object paramObj);
	
	public List<T>findByCriteria(DetachedCriteria detachedCriteria,Object object);
	
	public boolean updateObjectNotValid(final Long id);
	
	public List<T> findBySQL(String queryString,Object[] values);
	
	public List findByOriginSql(final String originSql,final Object[] values);
}
