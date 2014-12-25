package com.selfsoft.baseparameter.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmStoreHouseDao;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmUser;
@Service("tmStoreHouseService")
public class TmStoreHouseServiceImpl implements ITmStoreHouseService{
	@Autowired
	private ITmStoreHouseDao tmStoreHouseDao;
	
	public boolean deleteById(Long id) {
		
		return tmStoreHouseDao.deleteById(id);
	}

	public TmStoreHouse findById(Long id) {
		 
		return tmStoreHouseDao.findById(id);
	}

	public List<TmStoreHouse> findByTmStoreHouse(TmStoreHouse tmStoreHouse) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmStoreHouse.class);
		//detachedCriteria.add(Restrictions.or(Restrictions.eq("isValid",Constants.ISTRUE),Restrictions.isNull("isValid")));
		if(null!=tmStoreHouse){
			if(null!=tmStoreHouse.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmStoreHouse.getId()));
			}
			else if(null!=tmStoreHouse.getHouseCode()&&!"".equals(tmStoreHouse.getHouseCode())){
				detachedCriteria.add(Restrictions.like("houseCode", "%"+tmStoreHouse.getHouseCode()+"%"));
			}
			else if(null!=tmStoreHouse.getHouseName()&&!"".equals(tmStoreHouse.getHouseName())){
				detachedCriteria.add(Restrictions.like("houseName", "%"+tmStoreHouse.getHouseName()+"%"));
			}
			else if(null!=tmStoreHouse.getHouseRemark()&&!"".equals(tmStoreHouse.getHouseRemark())){
				detachedCriteria.add(Restrictions.like("houseRemark", "%"+tmStoreHouse.getHouseRemark()+"%"));
			}
			if(null!=tmStoreHouse.getIsMixed()){
				detachedCriteria.add(Restrictions.eq("isMixed", tmStoreHouse.getIsMixed()));
			}
		} 
		return tmStoreHouseDao.findByCriteria(detachedCriteria, tmStoreHouse);
	}

	public boolean insert(TmStoreHouse tmStoreHouse) {
		 
		return tmStoreHouseDao.insert(tmStoreHouse);
	}

	public boolean update(TmStoreHouse tmStoreHouse) {
		 
		return tmStoreHouseDao.update(tmStoreHouse);
	}
	
	public List<TmStoreHouse> findAll(){
		return tmStoreHouseDao.findAll();
	}
	
	
	public TmStoreHouse findByHouseCode(String houseCode){
		
		String hql = "from TmStoreHouse t where t.houseCode = ?";
		
		List<TmStoreHouse> storeHouseList = tmStoreHouseDao.findBySQL(hql, new Object[]{houseCode});
		
		if(storeHouseList!=null && storeHouseList.size() >0)
			return storeHouseList.get(0);
		
		return null;
	}

}
