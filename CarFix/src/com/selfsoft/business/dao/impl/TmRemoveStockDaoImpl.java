package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmRemoveStockDao;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmRemoveStockDao")
public class TmRemoveStockDaoImpl extends BaseDaoImpl<TmRemoveStock> implements
		ITmRemoveStockDao {
	
	
	public List<TmRemStockDetailVo> getRemStockDetVos(Long isConfirm , Long isValid ,Long removeStockId){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmRemStockDetailVo(rsd.id,rsd.removeStockId,rsd.partinfoId,");
		hql.append("rsd.quantity,rsd.costPrice,pi.partCode,pi.partName,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,");
		hql.append("pi.tmStoreHouse.houseName,pi.tmUnit.unitName,rsd.quantity*rsd.costPrice,pi.storeQuantity,pi.storeLocation)");
		hql.append("from TmRemoveStock rs,TmRemoveStockDetail rsd ,TbPartInfo pi ");
		hql.append("where rs.id = rsd.removeStockId and pi.id = rsd.partinfoId ");
		if(isConfirm != null)
			hql.append(" and rs.isConfirm = "+isConfirm);
		if(isValid != null)
			hql.append(" and rs.isValid = "+isValid);
		if(removeStockId!=null)
			hql.append(" and rsd.removeStockId = "+removeStockId);
		List<TmRemStockDetailVo> resultVoList = this.getHibernateTemplate().find(hql.toString());
		return resultVoList;
	}
	
	
	public List<TmRemStockVo> getAllRemStockVos(Long isConfirm , Long isValid , TmRemoveStock tmRemoveStock , Long remStockId){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmRemStockVo(rs.id,rs.removeDate,rs.userId,rs.createDate,rs.removeStockBill,sh.id,sh.houseCode,sh.houseName,u.userRealName) ");
		hql.append("from TmRemoveStock rs,TmStoreHouse sh , TmUser u ");
		hql.append("where rs.storeHoseId = sh.id and u.id = rs.userId ");
		if(tmRemoveStock != null){
			if(StringUtils.isNotBlank(tmRemoveStock.getRemoveStockBill())){
				hql.append(" and rs.removeStockBill like '%").append(tmRemoveStock.getRemoveStockBill()).append("%'");
			}
			
		}
		if(isConfirm != null)
			hql.append(" and rs.isConfirm = "+isConfirm);
		if(isValid != null)
			hql.append(" and rs.isValid = "+isValid);
		if(remStockId != null)
			hql.append(" and rs.id = "+remStockId);
		hql.append(" order by rs.id desc");
		List<TmRemStockVo> resultVoList = this.getHibernateTemplate().find(hql.toString());
		return resultVoList;
	}
	
	public void deleteTmRemoveStockDetail(Long removeStockId){
		String hql = "delete from TmRemoveStockDetail t where t.removeStockId = "+removeStockId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}

}
