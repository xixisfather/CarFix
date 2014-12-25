package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbAnvancePayDao;
import com.selfsoft.business.model.TbAnvancePay;
import com.selfsoft.business.service.ITbAnvancePayService;
@Service("tbAnvancePayService")
public class TbAnvancePayServiceImpl implements ITbAnvancePayService{

	@Autowired
	private ITbAnvancePayDao tbAnvancePayDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbAnvancePayDao.deleteById(id);
	}

	public List<TbAnvancePay> findAll() {
		// TODO Auto-generated method stub
		return tbAnvancePayDao.findAll();
	}

	public TbAnvancePay findById(Long id) {
		// TODO Auto-generated method stub
		return tbAnvancePayDao.findById(id);
	}

	public void insert(TbAnvancePay tbAnvancePay) {
		// TODO Auto-generated method stub
		tbAnvancePayDao.insert(tbAnvancePay);
	}

	public void update(TbAnvancePay tbAnvancePay) {
		// TODO Auto-generated method stub
		tbAnvancePayDao.update(tbAnvancePay);
	}

	public List<TbAnvancePay> findByTbAnvancePay(TbAnvancePay tbAnvancePay) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbAnvancePay.class);
		
		if(null!=tbAnvancePay){
			if(null!=tbAnvancePay.getAnvanceCode()){
				detachedCriteria.add(Restrictions.like("anvanceCode", "%"+tbAnvancePay.getAnvanceCode()+"%"));
			}
			if(null!=tbAnvancePay.getTbCarInfo()){
				detachedCriteria.createAlias("tbCarInfo","tbCarInfo");
				
				if(null!=tbAnvancePay.getTbCarInfo().getLicenseCode()&&!"".equals(tbAnvancePay.getTbCarInfo().getLicenseCode())){
					detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode", "%"+tbAnvancePay.getTbCarInfo().getLicenseCode()+"%"));
				}
			}
		}
		return tbAnvancePayDao.findByCriteria(detachedCriteria, tbAnvancePay);
	}
	
	//根据车辆ID查找预付款LIST
	public List<TbAnvancePay> findTbAnvancePayListByCarInfoId(Long carInfoId){
		
		return tbAnvancePayDao.findBySQL("SELECT tbAnvancePay FROM TbAnvancePay tbAnvancePay WHERE tbAnvancePay.tbCarInfo.id=?", new Object[]{carInfoId});
	}
	
	//返回客户的预付款总金额
	public Double acquireCustomerTotalAnvancePay(Long carInfoId)
	{
		
		Double payAmount = 0D;
		
		List<TbAnvancePay> tbAnvancePayList = this.findTbAnvancePayListByCarInfoId(carInfoId);
		
		if(null!=tbAnvancePayList&&tbAnvancePayList.size()>0){
			for(TbAnvancePay tbAnvancePay : tbAnvancePayList){
				payAmount = payAmount + tbAnvancePay.getPayAmount();
			}
		}
		
		return payAmount;
		
	}
	//暂时废弃
	//扣除客户的预付款总金额--按第一个预付款扣除，直到扣光为止    比如预付款  100 200 300 需要支付350 那么100-100 200-200 300-50 
	public void subStractAnvancePay(Long carInfoId,Double usedSaveAmount){
		//支付金额
		BigDecimal b_usedSaveAmount = new BigDecimal(String.valueOf(usedSaveAmount));
		
		List<TbAnvancePay> tbAnvancePayList = this.findTbAnvancePayListByCarInfoId(carInfoId);
		
		if(null!=tbAnvancePayList&&tbAnvancePayList.size()>0){
			
			for(TbAnvancePay tbAnvancePay : tbAnvancePayList){
				
				Double payAmount = tbAnvancePay.getPayAmount();
			
				//预收款
				BigDecimal b_payAmount = new BigDecimal(String.valueOf(payAmount));
				
				BigDecimal b_surplus = b_payAmount.subtract(b_usedSaveAmount);
				
				if(b_surplus.doubleValue()< 0){
					
					
					
				}
			}
		}
	}
}
