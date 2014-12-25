package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmLianceRegisterDao;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmLianceRegisterDao")
public class TmLianceRegisterDaoImpl extends BaseDaoImpl<TmLianceRegister> implements
		ITmLianceRegisterDao {


	public List<TmLianceRegDetailVo> getLianceRegDetailVo(Long isConfirm , Long isReturn , Long supplierId,Long lianceRegId){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmLianceRegDetailVo ");
		hql.append("(lr.id,lrd.id,lr.lianceBill,lr.lianceDate , pi.partCode,pi.partName,pi.tmUnit.unitName,lrd.lianceQuantity,");
		hql.append("lrd.liancePrice,lrd.oldCostPrice,lrd.returnQuantity,pi.tmStoreHouse.houseName,pi.costPrice,pi.id,pi.storeLocation) ");
		hql.append("from TmLianceRegisterDetail lrd , TmLianceRegister lr , TbPartInfo pi  ");
		hql.append("where lrd.lianceId = lr.id and pi.id = lrd.tbPartInfo.id ");
		if(supplierId != null)
			hql.append(" and lr.supplierId ="+supplierId);
		if(lianceRegId != null)
			hql.append(" and lr.id ="+lianceRegId);
		if(isConfirm != null)
			hql.append(" and lr.isConfirm = "+isConfirm);
		if(isReturn != null)
			hql.append(" and lr.isReturn = "+isReturn);
		List<TmLianceRegDetailVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	
	public List<TmLianceRegVo> getLianceRegVo(Long isConfirm , Long isReturn , TmLianceRegister tmLianceRegister , Long lianceRegId){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmLianceRegVo ");
		hql.append("(lr.id,lr.lianceBill,lr.lianceDate,lr.userId,lr.createDate,lr.totalPrice,lr.totalQuantity,lr.dutyPeople, ");
		hql.append("lr.supplierId,t.customerCode,t.customerName,u.userRealName,u2.userRealName) ");
		hql.append("from TmLianceRegister lr , TbCustomer t ,TmUser u ,TmUser u2 ");
		hql.append("where lr.supplierId = t.id and lr.dutyPeople = u.id  and lr.userId = u2.id");
		if(tmLianceRegister != null){
			if(StringUtils.isNotBlank(tmLianceRegister.getLianceBill()))
				hql.append(" and lr.lianceBill like '%").append(tmLianceRegister.getLianceBill()).append("%'");
			if(tmLianceRegister.getSupplierId() != null)
				hql.append(" and lr.supplierId = "+tmLianceRegister.getSupplierId());
			if(tmLianceRegister.getDutyPeople() != null)
				hql.append(" and lr.dutyPeople = "+tmLianceRegister.getDutyPeople());
			if(StringUtils.isNotBlank(tmLianceRegister.getBeginDate())){
				hql.append(" and lr.lianceDate >= '").append(tmLianceRegister.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmLianceRegister.getEndDate())){
				hql.append(" and lr.lianceDate <= '").append(tmLianceRegister.getEndDate()).append("'");
			}
		}
		if(isConfirm != null)
			hql.append(" and lr.isConfirm = "+isConfirm);
		if(isReturn != null)
			hql.append(" and lr.isReturn = "+isReturn);
		if(lianceRegId != null)
			hql.append(" and lr.id = ").append(lianceRegId);
		
		hql.append(" order by lr.id desc");
		List<TmLianceRegVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	public void deleteLianceRegDetail(Long lianceRegId){
		String hql = "delete from TmLianceRegisterDetail t where t.lianceId = "+lianceRegId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
}
