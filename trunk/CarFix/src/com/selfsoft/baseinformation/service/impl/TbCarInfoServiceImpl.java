package com.selfsoft.baseinformation.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbCarInfoDao;
import com.selfsoft.baseinformation.model.TbCarInfo;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.service.ITbCarInfoService;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.service.ITmLostDayService;
import com.selfsoft.baseparameter.service.ITmMaintainAlertDayService;
import com.selfsoft.business.dao.ITbFixEntrustDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.CommonMethod;
@Service("tbCarInfoService")
public class TbCarInfoServiceImpl implements ITbCarInfoService{
	
	@Autowired
	private ITbCarInfoDao tbCarInfoDao;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	@Autowired
	private ITbFixEntrustDao tbFixEntrustDao;
	@Autowired
	private ITmLostDayService tmLostDayService;
	@Autowired
	private ITmMaintainAlertDayService tmMaintainAlertDayService;
	
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbCarInfoDao.deleteById(id);
	}

	public TbCarInfo findById(Long id) {
		// TODO Auto-generated method stub
		return tbCarInfoDao.findById(id);
	}

	public List<TbCarInfo> findByTbCarInfo(TbCarInfo tbCarInfo) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbCarInfo.class);
		
		if(null!=tbCarInfo){
			if(null!=tbCarInfo.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbCarInfo.getId()));
			}
			if(null!=tbCarInfo.getLicenseCode()&&!"".equals(tbCarInfo.getLicenseCode())){
				//牌照号
				detachedCriteria.add(Restrictions.like("licenseCode", "%"+tbCarInfo.getLicenseCode()+"%"));
			}
			if(null!=tbCarInfo.getInsureCardCode()&&!"".equals(tbCarInfo.getInsureCardCode())){
				detachedCriteria.add(Restrictions.like("insureCardCode", "%"+tbCarInfo.getInsureCardCode()+"%"));
			}
			if(null!=tbCarInfo.getTmCarModelType()){
				
				detachedCriteria.createAlias("tmCarModelType", "tmCarModelType");
				
				if(null!=tbCarInfo.getTmCarModelType().getId()){
					detachedCriteria.add(Restrictions.eq("tmCarModelType.id", tbCarInfo.getTmCarModelType().getId()));
				}
			}
			if(null!=tbCarInfo.getTbCustomer()){
				
				detachedCriteria.createAlias("tbCustomer", "tbCustomer");
				
				if(null!=tbCarInfo.getTbCustomer().getId()){
					detachedCriteria.add(Restrictions.eq("tbCustomer.id", tbCarInfo.getTbCustomer().getId()));
				}
				if(null!=tbCarInfo.getTbCustomer().getCustomerCode()){
					detachedCriteria.add(Restrictions.like("tbCustomer.customerCode", "%"+tbCarInfo.getTbCustomer().getCustomerCode()+"%"));
				}
				if(null!=tbCarInfo.getTbCustomer().getCustomerName()){
					detachedCriteria.add(Restrictions.like("tbCustomer.customerName", "%"+tbCarInfo.getTbCustomer().getCustomerName()+"%"));
				}
			}
			
			if(StringUtils.isNotBlank(tbCarInfo.getBeginLicenseDate())){
				detachedCriteria.add(Restrictions.ge("licenseDate", Date.valueOf(tbCarInfo.getBeginLicenseDate())));
			}
			
			if(StringUtils.isNotBlank(tbCarInfo.getEndLicenseDate())){
				detachedCriteria.add(Restrictions.le("licenseDate", Date.valueOf(tbCarInfo.getEndLicenseDate())));
			}
			
			if(StringUtils.isNotBlank(tbCarInfo.getBeginPurchaseDate())){
				detachedCriteria.add(Restrictions.ge("purchaseDate", Date.valueOf(tbCarInfo.getBeginPurchaseDate())));
			}
			
			if(StringUtils.isNotBlank(tbCarInfo.getEndPurchaseDate())){
				detachedCriteria.add(Restrictions.le("purchaseDate", Date.valueOf(tbCarInfo.getEndPurchaseDate())));
			}
			if(StringUtils.isNotBlank(tbCarInfo.getChassisCode())){
				detachedCriteria.add(Restrictions.like("chassisCode", "%"+tbCarInfo.getChassisCode()+"%"));
			}
			
			if(tbCarInfo.getCarProperty() != null){
				detachedCriteria.add(Restrictions.eq("carProperty", tbCarInfo.getCarProperty()));
			}
			
			if(StringUtils.isNotBlank(tbCarInfo.getLicenseMonth())){
				detachedCriteria.add(Restrictions.sqlRestriction("month(LICENSE_DATE)="+tbCarInfo.getLicenseMonth()));
			}
			if(tbCarInfo.getMaxKilo() != null && tbCarInfo.getMinKilo() != null){
				detachedCriteria.add(Restrictions.between("kilo", tbCarInfo.getMinKilo(), tbCarInfo.getMaxKilo()));
			}
		}
		
		
		return tbCarInfoDao.findByCriteria(detachedCriteria, tbCarInfo);
	}

	public void insertTbCarInfo(TbCarInfo tbCarInfo) {
		// TODO Auto-generated method stub
		tbCarInfoDao.insert(tbCarInfo);
	}

	public void updateTbCarInfo(TbCarInfo tbCarInfo) {
		// TODO Auto-generated method stub
		tbCarInfoDao.update(tbCarInfo);
	}
	
	/**
	 * 通过车牌号查找车辆信息，包含车主信息
	 */
	public TbCarInfo findTbCarInfoBylicenseCode(String licenseCode){
		
		List<TbCarInfo> list = tbCarInfoDao.findBySQL("SELECT tbCarInfo FROM TbCarInfo tbCarInfo WHERE tbCarInfo.licenseCode=?", new Object[]{licenseCode});
	
		if(null!=list&&list.size()>0){
			
			TbCarInfo tbcarInfo = list.get(0);
			
			if(null!=tbcarInfo.getTbCustomer()&&null!=tbcarInfo.getTbCustomer().getId()){
				
				TbCustomer tbCustomer = tbCustomerService.findById(tbcarInfo.getTbCustomer().getId());
			
				tbcarInfo.setTbCustomer(tbCustomer);
			}
			
			return tbcarInfo;
			
		}
		return null;
	}
	
	/**
	 * 通过车辆ID查找车辆信息，包括车主信息
	 */
	public TbCarInfo findTbCarInfoById(Long id){
		
		TbCarInfo tbCarInfo = this.findById(id);
		
		if(null!=tbCarInfo.getTbCustomer()&&null!=tbCarInfo.getTbCustomer().getId()){
			
			TbCustomer tbCustomer = tbCustomerService.findById(tbCarInfo.getTbCustomer().getId());
		
			tbCarInfo.setTbCustomer(tbCustomer);
		}
		
		return tbCarInfo;
	}
	
	/**
	 * 查询需要保养提醒的车辆
	 */
	public List<TbCarInfo> findMaintainCarInfo(){
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService.findMaintainCarFixEntrust();
		
		List<TbCarInfo> tbCarInfoList = null;
		
		if(null != tbFixEntrustList && tbFixEntrustList.size() > 0){
			
			tbCarInfoList = new ArrayList<TbCarInfo>();
			
			for(TbFixEntrust t : tbFixEntrustList){
				
				TbCarInfo tbCarInfo = t.getTbCarInfo();
				
				tbCarInfo.setRemindMaintainDate(t.getRemindMaintainDate());
				
				if(null!=tbCarInfo.getTbCustomer()&&null!=tbCarInfo.getTbCustomer().getId()){
					
					TbCustomer tbCustomer = tbCustomerService.findById(tbCarInfo.getTbCustomer().getId());
				
					tbCarInfo.setTbCustomer(tbCustomer);
				}
				
				tbCarInfoList.add(tbCarInfo);
				
			}
			
		}
		
		return tbCarInfoList;
	}
	
	/**
	 * 查询流失的车辆客户
	 * @return
	 */
	public List<TbCarInfo> findLostCar(TbCarInfo tbCarInfo){
		
		Integer lostDay = (tmLostDayService.findAll() == null ? 90 : tmLostDayService.findAll().get(0).getLostDay());
		
		//String hql = "select new TbFixEntrust(max(tbFixEntrust.id),max(tbFixEntrust.fixDate),tbFixEntrust.tbCarInfo) from TbFixEntrust tbFixEntrust where tbFixEntrust.fixDate < '"+ CommonMethod.parseDateToString(CommonMethod.addDate(new java.util.Date(), (0 - lostDay)), "yyy-MM-dd HH:mm:ss")+"' ";
		String hql = "select new TbFixEntrust(max(tbFixEntrust.id),max(tbFixEntrust.fixDate),tbFixEntrust.tbCarInfo) from TbFixEntrust tbFixEntrust where 1=1 ";
		
		if(null != tbCarInfo){
			
			if(null != tbCarInfo.getLicenseCode() && !"".equals(tbCarInfo.getLicenseCode())){
				
				hql = hql + "and tbFixEntrust.tbCarInfo.licenseCode = '" + tbCarInfo.getLicenseCode() + "' ";
				
			}
			
			if(null != tbCarInfo.getLastFixDayFrom()){
				
				hql = hql + "and tbFixEntrust.fixDate > '"+ CommonMethod.parseDateToString(tbCarInfo.getLastFixDayFrom(), "yyy-MM-dd HH:mm:ss")+"' ";
				
			}
			
			if(null != tbCarInfo.getLastFixDayTo()){
				
				hql = hql + "and tbFixEntrust.fixDate < '"+ CommonMethod.parseDateToString(CommonMethod.addDate(tbCarInfo.getLastFixDayTo(), 1), "yyy-MM-dd HH:mm:ss")+"' ";
				
			}
		}
		
		hql = hql + " group by tbFixEntrust.tbCarInfo"; 
		
		List<TbFixEntrust> list = tbFixEntrustDao.findBySQL(hql, null);
		
		List<TbCarInfo> tbCarInfoList = null;
		
		if(null != list && list.size() > 0){
			
			tbCarInfoList = new ArrayList<TbCarInfo>();
			
			for(TbFixEntrust tbFixEntrust : list){
				
				if(tbFixEntrust.getFixDate().before(CommonMethod.addDate(new java.util.Date(), (0 - lostDay)))){
					
					TbCarInfo _tbCarInfo = tbFixEntrust.getTbCarInfo();
					
					_tbCarInfo.setLastFixDay(tbFixEntrust.getFixDate());
					
					tbCarInfoList.add(_tbCarInfo);
					
				}
				
				
				
			}
			
		}
		
		return tbCarInfoList;
	}
	
	/**
	 * 需要维修回访列表
	 * @return
	 */
	public List<TbCarInfo> findMaintainHFCar(TbCarInfo tbCarInfo) {
		
		Integer alertDay = tmMaintainAlertDayService.findAll().get(0).getAlertDay();
		
		Integer continueDay = tmMaintainAlertDayService.findAll().get(0).getContinueDay();
		
		String hql = "select new TbFixEntrust(max(tbFixEntrust.id),max(tbFixEntrust.fixDate),tbFixEntrust.tbCarInfo) from TbFixEntrust tbFixEntrust where tbFixEntrust.fixDate < '"+ CommonMethod.parseDateToString(CommonMethod.addDate(new java.util.Date(), (0 - alertDay)), "yyy-MM-dd HH:mm:ss")+"' and tbFixEntrust.fixDate > '"+ CommonMethod.parseDateToString(CommonMethod.addDate(new java.util.Date(), (0 - alertDay - continueDay)), "yyy-MM-dd HH:mm:ss")+"'";
		
		if(null != tbCarInfo){
			
			if(null != tbCarInfo.getLicenseCode() && !"".equals(tbCarInfo.getLicenseCode())){
				
				hql = hql + "and tbFixEntrust.tbCarInfo.licenseCode = '" + tbCarInfo.getLicenseCode() + "' ";
				
			}
			
			if(null != tbCarInfo.getLastFixDayFrom()){
				
				hql = hql + "and tbFixEntrust.fixDate > '"+ CommonMethod.parseDateToString(tbCarInfo.getLastFixDayFrom(), "yyy-MM-dd HH:mm:ss")+"' ";
				
			}
			
			if(null != tbCarInfo.getLastFixDayTo()){
				
				hql = hql + "and tbFixEntrust.fixDate < '"+ CommonMethod.parseDateToString(CommonMethod.addDate(tbCarInfo.getLastFixDayTo(), 1), "yyy-MM-dd HH:mm:ss")+"' ";
				
			}
		}
		
		hql = hql + " group by tbFixEntrust.tbCarInfo"; 
		
		List<TbFixEntrust> list = tbFixEntrustDao.findBySQL(hql, null);
		
		List<TbCarInfo> tbCarInfoList = null;
		
		if(null != list && list.size() > 0){
			
			tbCarInfoList = new ArrayList<TbCarInfo>();
			
			for(TbFixEntrust tbFixEntrust : list){
				
				TbCarInfo _tbCarInfo = tbFixEntrust.getTbCarInfo();
				
				_tbCarInfo.setEntrustId(tbFixEntrust.getId());
				
				_tbCarInfo.setEntrustCode(tbFixEntrustDao.findById(tbFixEntrust.getId()).getEntrustCode());
				
				_tbCarInfo.setLastFixDay(tbFixEntrust.getFixDate());
				
				tbCarInfoList.add(_tbCarInfo);
				
			}
			
		}
		
		return tbCarInfoList;
	}

	
	public List<TbCarInfo> findInsuranceCarInfo() {
		
		List<TbFixEntrust> tbFixEntrustList = tbFixEntrustService.findInsuranceCarFixEntrust();
		
		List<TbCarInfo> tbCarInfoList = null;
		
		if(null != tbFixEntrustList && tbFixEntrustList.size() > 0){
			
			tbCarInfoList = new ArrayList<TbCarInfo>();
			
			for(TbFixEntrust t : tbFixEntrustList){
				
				TbCarInfo tbCarInfo = t.getTbCarInfo();
				
				tbCarInfo.setRemindInsuranceDate(t.getRemindInsuranceDate());
				
				if(null!=tbCarInfo.getTbCustomer()&&null!=tbCarInfo.getTbCustomer().getId()){
					
					TbCustomer tbCustomer = tbCustomerService.findById(tbCarInfo.getTbCustomer().getId());
				
					tbCarInfo.setTbCustomer(tbCustomer);
				}
				
				tbCarInfoList.add(tbCarInfo);
				
			}
			
		}
		
		return tbCarInfoList;
	}
	
	/**
	 * 通过底盘号查找车辆信息，包含车主信息
	 */
	public TbCarInfo findTbCarInfoByChassisCode(String chassisCode){
		
		List<TbCarInfo> list = tbCarInfoDao.findBySQL("SELECT tbCarInfo FROM TbCarInfo tbCarInfo WHERE tbCarInfo.chassisCode=?", new Object[]{chassisCode});
	
		if(null!=list&&list.size()>0){
			
			TbCarInfo tbcarInfo = list.get(0);
			
			if(null!=tbcarInfo.getTbCustomer()&&null!=tbcarInfo.getTbCustomer().getId()){
				
				TbCustomer tbCustomer = tbCustomerService.findById(tbcarInfo.getTbCustomer().getId());
			
				tbcarInfo.setTbCustomer(tbCustomer);
			}
			
			return tbcarInfo;
			
		}
		return null;
	}
}
