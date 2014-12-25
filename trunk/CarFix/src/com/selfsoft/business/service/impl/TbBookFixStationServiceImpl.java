package com.selfsoft.business.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbWorkingInfo;
import com.selfsoft.baseinformation.service.ITbWorkingInfoService;
import com.selfsoft.business.dao.ITbBookFixStationDao;
import com.selfsoft.business.model.TbBookFixStation;
import com.selfsoft.business.service.ITbBookFixStationService;

@Service("tbBookFixStationService")
public class TbBookFixStationServiceImpl implements ITbBookFixStationService{

	@Autowired
	private ITbBookFixStationDao tbBookFixStationDao;
	@Autowired
	private ITbWorkingInfoService tbWorkingInfoService;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbBookFixStationDao.deleteById(id);
	}

	public TbBookFixStation findById(Long id) {
		// TODO Auto-generated method stub
		return tbBookFixStationDao.findById(id);
	}

	public List<TbBookFixStation> findByTbBookFixStation(
			TbBookFixStation tbBookFixStation) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbBookFixStation.class);
		
		return tbBookFixStationDao.findByCriteria(detachedCriteria, tbBookFixStation);
	}

	public void insert(TbBookFixStation tbBookFixStation) {
		// TODO Auto-generated method stub
		tbBookFixStationDao.insert(tbBookFixStation);
	}

	public void update(TbBookFixStation tbBookFixStation) {
		// TODO Auto-generated method stub
		tbBookFixStationDao.update(tbBookFixStation);
	}

	//根据预约单ID来查找修理工位LIST
	public List<TbBookFixStation> findTbBookFixStationListByTbBookId(Long tbBookId){
		
		return tbBookFixStationDao.findBySQL("SELECT tbBookFixStation FROM TbBookFixStation tbBookFixStation where tbBookFixStation.tbBook.id=?", new Object[]{tbBookId});
	}
	
	//根据预约单ID查找修理工位MAP<String,String>
	public Map<String,String> findTbBookFixStationMapByTbBookId(Long tbBookId){
		
		Map map = new LinkedHashMap<String,String>();
		
		List<TbBookFixStation>list = this.findTbBookFixStationListByTbBookId(tbBookId);
		
		if(null!=list&&list.size()>0){
			
			for(TbBookFixStation tbBookFixStation : list){
				
				TbWorkingInfo tbWorkingInfo = tbWorkingInfoService.findById(tbBookFixStation.getTbWorkingInfo().getId());
				
				map.put(tbBookFixStation.getTbWorkingInfo().getId()+","+tbBookFixStation.getFreeSymbol(),tbBookFixStation.getFreeSymbolShow()+" " +tbWorkingInfo.getStationCode()+" "+tbWorkingInfo.getStationName());
			}
		}
		
		return map;
	}
	
}
