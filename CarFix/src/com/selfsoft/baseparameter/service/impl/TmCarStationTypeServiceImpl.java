package com.selfsoft.baseparameter.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.dao.ITmCarStationTypeDao;
import com.selfsoft.baseparameter.model.TmCarStationType;
import com.selfsoft.baseparameter.service.ITmCarStationTypeService;
import com.selfsoft.framework.common.Constants;
@Service("tmCarStationTypeService")
public class TmCarStationTypeServiceImpl implements ITmCarStationTypeService {
	@Autowired
	private ITmCarStationTypeDao tmCarStationTypeDao;
	
	public Boolean deleteById(Long id) {
		return tmCarStationTypeDao.deleteById(id);
	}

	public TmCarStationType findById(Long id) {
		return tmCarStationTypeDao.findById(id);
	}

	public List<TmCarStationType> findTmCarStationTypeList(TmCarStationType tmCarStationType) {
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmCarStationType.class);
		//detachedCriteria.add(Restrictions.or(Restrictions.eq("isvalid",Constants.ISTRUE),Restrictions.isNull("isvalid")));
		if(tmCarStationType == null) return tmCarStationTypeDao.findByCriteria(detachedCriteria, tmCarStationType);
		
		if(null!=tmCarStationType.getId()){
			detachedCriteria.add(Restrictions.eq("id", tmCarStationType.getId()));
		}
		if(null != tmCarStationType.getStationCode() && !"".equals(tmCarStationType.getStationCode())){
			detachedCriteria.add(Restrictions.like("stationCode", "%"+tmCarStationType.getStationCode()+"%"));
		}
		if(null != tmCarStationType.getStationRemark() && !"".equals(tmCarStationType.getStationRemark())){
			detachedCriteria.add(Restrictions.like("stationRemark", "%"+tmCarStationType.getStationRemark()+"%"));
		}
		
		return tmCarStationTypeDao.findByCriteria(detachedCriteria, tmCarStationType);
	}

	public void saveTmCarStationType(TmCarStationType tmCarStationType) {
		tmCarStationTypeDao.insert(tmCarStationType);
	}

	public void updateTmCarStationType(TmCarStationType tmCarStationType) {
		tmCarStationTypeDao.update(tmCarStationType);
	}

	public List<TmCarStationType> findAll() {
		// TODO Auto-generated method stub
		return tmCarStationTypeDao.findAll();
	}

	public Map<Long, String> findAllTmCarStationTypeMap() {
		Map<Long, String> map = new LinkedHashMap<Long, String>();

		List<TmCarStationType> list = this.findAll();
		
		if(null!=list&&list.size()>0){
			
			for(TmCarStationType t: list){
				
				map.put(t.getId(), t.getStationCode() + "  "+ t.getStationRemark());
			
			}
		}
		return map;
	}

	//根据车型ID来获取车型工位
	public TmCarStationType findTmCarStationTypeByTmCarModelTypeId(Long tmCarModelTypeId){
		
		List<TmCarStationType> list = tmCarStationTypeDao.findBySQL("SELECT tmCarStationType FROM TmCarStationType tmCarStationType inner join tmCarStationType.tmCarModelTypes tmCarModelType where tmCarModelType.id=?",new Object[]{tmCarModelTypeId});
	
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
