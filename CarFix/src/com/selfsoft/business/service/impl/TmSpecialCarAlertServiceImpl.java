package com.selfsoft.business.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.business.dao.ITmSpecialCarAlertDao;
import com.selfsoft.business.model.TmSpecialCarAlert;
import com.selfsoft.business.service.ITmSpecialCarAlertService;
import com.selfsoft.framework.common.Constants;
@Service("tmSpecialCarAlertService")
public class TmSpecialCarAlertServiceImpl implements ITmSpecialCarAlertService {

	@Autowired
	private ITmSpecialCarAlertDao tmSpecialCarAlertDao;

	public boolean deleteById(Long id) {
		return tmSpecialCarAlertDao.deleteById(id);
	}

	public void findAll() {
		tmSpecialCarAlertDao.findAll();
	}

	public TmSpecialCarAlert findById(Long id) {
		return tmSpecialCarAlertDao.findById(id);
	}

	public void insert(TmSpecialCarAlert tmSpecialCarAlert) {
		tmSpecialCarAlertDao.insert(tmSpecialCarAlert);
	}

	public void update(TmSpecialCarAlert tmSpecialCarAlert) {
		tmSpecialCarAlertDao.update(tmSpecialCarAlert);
	}
	
	public List<TmSpecialCarAlert> findByEntity(TmSpecialCarAlert tmSpecialCarAlert){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TmSpecialCarAlert.class);
		
		if(null!=tmSpecialCarAlert){
			if(null!=tmSpecialCarAlert.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmSpecialCarAlert.getId()));
			}
			if(null!=tmSpecialCarAlert.getTbCarInfo()){
				detachedCriteria.createAlias("tbCarInfo","tbCarInfo");
				
				if(StringUtils.isNotBlank(tmSpecialCarAlert.getTbCarInfo().getLicenseCode())){
					detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode", "%"+tmSpecialCarAlert.getTbCarInfo().getLicenseCode()+"%"));
				}
				
				if(tmSpecialCarAlert.getTbCarInfo().getId() != null){
					detachedCriteria.add(Restrictions.eq("tbCarInfo.id", tmSpecialCarAlert.getTbCarInfo().getId()));
				}
			}
			if(tmSpecialCarAlert.getAlertCount() != null){
				detachedCriteria.add(Restrictions.eq("alertCount", tmSpecialCarAlert.getAlertCount()));
			}
			
			
		}
		return tmSpecialCarAlertDao.findByCriteria(detachedCriteria, tmSpecialCarAlert);
		
	}
	
	
	public Map<Long, String> getAllAlertRange(){
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		map.put(Constants.ENTRIST_RANGE_ALERT, "开委托书");
		
		map.put(Constants.BALANCE_RANGE_ALERT, "结算时");
		
		map.put(Constants.FINISH_RANGE_ALERT, "竣工时");
		
		return map;
		
	}
	
	
	public Map<Long , String> getAllAlertCount(){
		
		Map<Long, String> map = new LinkedHashMap<Long, String>();
		
		map.put(Constants.SINGLEALERT, "单次提醒");
		
		map.put(Constants.MULTIALERT, "反复提醒");

		return map;
		
	}
	
	
	public String alertContentByCarInfoId(Long carInfoId,Long alertType){
		
		TbCarInfo carInfo = new TbCarInfo();
		carInfo.setId(carInfoId);
		TmSpecialCarAlert queryEntity = new TmSpecialCarAlert();
		queryEntity.setTbCarInfo(carInfo);
		
		List<TmSpecialCarAlert> list = this.findByEntity(queryEntity);

		TmSpecialCarAlert carAlert = null;
		if(list!= null && list.size()>0)
			 carAlert = list.get(0); 
		else
			return null;
		
		
		Date toDay = new Date();
		
		//提示区间判断
		if(carAlert.getBeginDate().getTime() <= toDay.getTime() && toDay.getTime() <= carAlert.getEndDate().getTime() ){
			//提醒类型判断
			if(carAlert.getAlertRange().indexOf(Constants.ENTRIST_RANGE_ALERT+"") != -1){
				//委托书提醒,如果是单次提醒，提醒过就不在提醒
				if(carAlert.getAlertCount().equals(Constants.SINGLEALERT) && carAlert.getIsAlert().equals(Constants.ISALERT)  )
					return null;
				else{
					carAlert.setIsAlert(Constants.ISALERT);
					this.update(carAlert);
					return carAlert.getAlertContent();
				}
			}
			if(carAlert.getAlertRange().indexOf(Constants.BALANCE_RANGE_ALERT+"") != -1 ){
				//结算时提醒
				if(carAlert.getAlertCount().equals(Constants.SINGLEALERT) && carAlert.getIsAlert().equals(Constants.ISALERT)  )
					return null;
				else{
					carAlert.setIsAlert(Constants.ISALERT);
					this.update(carAlert);
					return carAlert.getAlertContent();
				}
			}
			if(carAlert.getAlertRange().indexOf(Constants.FINISH_RANGE_ALERT+"") != -1 ){
				//竣工时提醒
				if(carAlert.getAlertCount().equals(Constants.SINGLEALERT) && carAlert.getIsAlert().equals(Constants.ISALERT)  )
					return null;
				else{
					carAlert.setIsAlert(Constants.ISALERT);
					this.update(carAlert);
					return carAlert.getAlertContent();
				}
			}
			
		} 
		
		return null;
	}
	
	public boolean hasCarAlertContent(Long carInfoId,Long alertType){
		String alertContent = this.alertContentByCarInfoId(carInfoId, alertType);
		if(StringUtils.isNotBlank(alertContent))
			return true;
		
		return false;
	}
	
	
	public TmSpecialCarAlert getTmSpecialCarAlertByCarId(Long carInfoId){
		TmSpecialCarAlert queryEntity = new TmSpecialCarAlert();
		TbCarInfo tbCarInfo = new TbCarInfo();
		tbCarInfo.setId(carInfoId);
		queryEntity.setTbCarInfo(tbCarInfo);
		List<TmSpecialCarAlert> tmspList =  this.findByEntity(queryEntity);
		
		TmSpecialCarAlert result = null;
		if(tmspList != null && tmspList.size() > 0)
			result = tmspList.get(0);
		
		return result;
			
	}
}
