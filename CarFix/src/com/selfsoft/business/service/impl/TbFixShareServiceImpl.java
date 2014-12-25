package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbBusinessBalanceDao;
import com.selfsoft.business.dao.ITbFixShareDao;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.service.ITbFixShareService;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbFixShareService")
public class TbFixShareServiceImpl implements ITbFixShareService{

	@Autowired
	private ITbFixShareDao tbFixShareDao;
	@Autowired
	private ITbBusinessBalanceDao tbBusinessBalanceDao;

	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return tbFixShareDao.deleteById(id);
	}

	public List<TbFixShare> findAll() {
		// TODO Auto-generated method stub
		return tbFixShareDao.findAll();
	}

	public TbFixShare findById(Long id) {
		// TODO Auto-generated method stub
		return tbFixShareDao.findById(id);
	}

	public void insert(TbFixShare tbFixShare) {
		// TODO Auto-generated method stub
		tbFixShareDao.insert(tbFixShare);
	}

	public void update(TbFixShare tbFixShare) {
		// TODO Auto-generated method stub
		tbFixShareDao.update(tbFixShare);
	}
	//通过修理内容ID查找分配信息
	public List<TbFixShare> findTbFixShareListByTbFixEntrustContentId(Long tbFixEntrustContentId){
		
		return tbFixShareDao.findBySQL("SELECT tbFixShare FROM TbFixShare tbFixShare inner join tbFixShare.tbFixEntrustContent where tbFixShare.tbFixEntrustContent.id=?", new Object[]{tbFixEntrustContentId});
	}
	
	public List<TbFixShare> findByEntity(TbFixShare tbFixShare){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbFixShare.class);
		if(null!=tbFixShare){
			if(null!=tbFixShare.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbFixShare.getId()));
			}
			if(tbFixShare.getTmUser()!=null){
				detachedCriteria.createAlias("tmUser","tmUser");
				if(StringUtils.isNotBlank(tbFixShare.getTmUser().getUserRealName())){
					detachedCriteria.add(Restrictions.like("tmUser.userRealName", 
							"%"+tbFixShare.getTmUser().getUserRealName()+"%"));
				}
				
				if(tbFixShare.getTmUser().getId()!= null){
					detachedCriteria.add(Restrictions.eq("tmUser.id", tbFixShare.getTmUser().getId()));
				}
			}
			
			
			if(tbFixShare.getTbFixEntrustContent()!= null){
				
				detachedCriteria.createAlias("tbFixEntrustContent","tbFixEntrustContent");
				
				if(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo() != null){
					
					detachedCriteria.createAlias("tbFixEntrustContent.tbWorkingInfo","tbWorkingInfo");
					
					if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode())){
						detachedCriteria.add(Restrictions.like("tbWorkingInfo.stationCode", 
											"%"+tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode()+"%"));
					}
					
					if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName())){
						detachedCriteria.add(Restrictions.like("tbWorkingInfo.stationName", 
											"%"+tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName()+"%"));
					}
					
				}
				
				if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust() != null){
					detachedCriteria.createAlias("tbFixEntrustContent.tbFixEntrust","tbFixEntrust");
					detachedCriteria.add(Restrictions.ne("tbFixEntrust.isvalid", 0L));
					if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getEntrustCode())){
						detachedCriteria.add(Restrictions.like("tbFixEntrust.entrustCode", 
											"%"+tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getEntrustCode()+"%"));
					}
					
					if(null!=tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart()){
						detachedCriteria.add(Restrictions.ge("tbFixEntrust.fixDate", tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart()));
					}	
					if(null!=tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd()){
						detachedCriteria.add(Restrictions.le("tbFixEntrust.fixDate", CommonMethod.addDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd(),1)));
					}
					
					
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getTbCarInfo() != null){
						detachedCriteria.createAlias("tbFixEntrustContent.tbFixEntrust.tbCarInfo","tbCarInfo");
						
						if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getTbCarInfo().getLicenseCode())){
							detachedCriteria.add(Restrictions.like("tbCarInfo.licenseCode", 
									"%"+tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getTbCarInfo().getLicenseCode()+"%"));
						}
					}
					
					/* 添加结算日期查询 */
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin() != null ||
							tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd() != null){
						List entrustIdList = tbBusinessBalanceDao.getTbEntrustByBalanceDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust());
						if(entrustIdList != null && entrustIdList.size()==0){
							return null;
						}
						detachedCriteria.add(Restrictions.in("tbFixEntrust.id",entrustIdList));
					}
					/* 添加结算日期查询 */
				}
			}
		}else{
			detachedCriteria.createAlias("tbFixEntrustContent","tbFixEntrustContent");
			detachedCriteria.createAlias("tbFixEntrustContent.tbFixEntrust","tbFixEntrust");
			detachedCriteria.add(Restrictions.ne("tbFixEntrust.isvalid", 0L));
			
		}
		detachedCriteria.addOrder(Order.asc("tmUser.id"));
		return tbFixShareDao.findByCriteria(detachedCriteria, tbFixShare);
	}
}
