package com.selfsoft.secrity.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseparameter.dao.ITmStoreHouseDao;
import com.selfsoft.secrity.dao.ITmUserDao;
import com.selfsoft.secrity.dao.ITmUserRoleDao;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;
@Service("tmUserService")
public class TmUserServiceImpl implements ITmUserService{
	@Autowired
	private ITmUserDao tmUserDao;
	@Autowired
	private ITmStoreHouseDao tmStoreHouseDao;
	@Autowired
	private ITmUserRoleDao tmUserRoleDao;
	
	public List<TmUser> findAll() {
		
		return tmUserDao.findAll();
	}
	
	public TmUser findById(Long id) {
		
		return tmUserDao.findById(id);
	}
	
	public boolean delete(TmUser tmUser) {
		
		return tmUserDao.delete(tmUser);
	}
	public boolean deleteById(Long id) {
		tmUserRoleDao.deleteById(id);
		return tmUserDao.deleteById(id);
	}
	public boolean insert(TmUser tmUser) {
		
		if(tmUser.getTmWorkType() == null || tmUser.getTmWorkType().getId() == null ){
			tmUser.setTmWorkType(null);
		}
		if(tmUser.getTmDepartment() == null || tmUser.getTmDepartment().getId() == null ){
			tmUser.setTmDepartment(null);
		}
		
		tmUser.setUserStatus("正常");
		
		return tmUserDao.insert(tmUser);
	}
	public boolean update(TmUser tmUser) {
		
		if(tmUser.getTmWorkType() == null || tmUser.getTmWorkType().getId() == null ){
			tmUser.setTmWorkType(null);
		}
		if(tmUser.getTmDepartment() == null || tmUser.getTmDepartment().getId() == null ){
			tmUser.setTmDepartment(null);
		}
		
		
		return tmUserDao.update(tmUser);
	}
	public List<TmUser> findByTmUser(TmUser tmUser) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmUser.class);
		if(null!=tmUser){
			if(null!=tmUser.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmUser.getId()));
			}
			if(null!=tmUser.getUserName()&&!"".equals(tmUser.getUserName())){
				detachedCriteria.add(Restrictions.eq("userName", tmUser.getUserName()));
			}
			if(null!=tmUser.getPassword()&&!"".equals(tmUser.getPassword())){
				detachedCriteria.add(Restrictions.eq("password", tmUser.getPassword()));
			}
			if(null!=tmUser.getUserStatus()&&!"".equals(tmUser.getUserStatus())){
				detachedCriteria.add(Restrictions.eq("userStatus", tmUser.getUserStatus()));
			}
			

			if(null!=tmUser.getTmDepartment()){
				
				detachedCriteria.createAlias("tmDepartment","tmDepartment");
				if(null!=tmUser.getTmDepartment().getId()){
					detachedCriteria.add(Restrictions.eq("tmDepartment.id",tmUser.getTmDepartment().getId() ));
				}
			}
			
			if(null!=tmUser.getTmWorkType()){
				
				detachedCriteria.createAlias("tmWorkType","tmWorkType");
				if(null!=tmUser.getTmWorkType().getId()){
					detachedCriteria.add(Restrictions.eq("tmWorkType.id",tmUser.getTmWorkType().getId() ));
				}
			}
		}
		return tmUserDao.findByCriteria(detachedCriteria,tmUser);
	}

	public List<TmUser> findBySQL(TmUser tmUser) {
		
		return tmUserDao.findBySQL("FROM TmUser where userName like ? ", new Object[]{"%"+tmUser.getUserName()+"%"});
	}
	
	public Map<Long,String> findAllTmUserMap(){
		
		Map map = new LinkedHashMap<Long,String>();
	
		List<TmUser> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmUser tmUser : list){
				map.put(tmUser.getId(), tmUser.getUserRealName());
			}
		}
		
		return map;
	}
	
	public List<TmUser> findValidUser(TmUser tmUser) {
		
		String  sql = "FROM TmUser tmUser where tmUser.userStatus = ?";
		
		if(null != tmUser.getTmDepartment() && null != tmUser.getTmDepartment().getId()){
			sql += " and tmUser.tmDepartment.id="+ tmUser.getTmDepartment().getId();
			
		}
		if(null != tmUser.getTmWorkType() && null != tmUser.getTmWorkType().getId()){
			sql += " and tmUser.tmWorkType.id="+ tmUser.getTmWorkType().getId();
			
		}
		if(StringUtils.isNotBlank(tmUser.getUserName())){
			sql += " and tmUser.userName like '%"+ tmUser.getUserName()+"%'";
		}
		return tmUserDao.findBySQL(sql, new Object[]{""+tmUser.getUserStatus()+""});
	}
	
	public TmUser findByUserName(String userName){
		
		List<TmUser> tmUserList = this.tmUserDao.findBySQL("from TmUser t where t.userName = ?", new String[]{userName});
		
		if(null != tmUserList && tmUserList.size() > 0){
			return tmUserList.get(0);
		}
		
		return null;
	}
	
	public List<TmUser> findValidFixUser(TmUser tmUser) {
		
		return tmUserDao.findBySQL("FROM TmUser where userStatus = ? AND isFixPerson = 1", new Object[]{""+tmUser.getUserStatus()+""});
	}
}
