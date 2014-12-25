package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmShiftinStockDao;
import com.selfsoft.business.model.TmShiftinStock;
import com.selfsoft.business.vo.TmShiftinStockVo;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmShiftinStockDao")
public class TmShiftinStockDaoImpl extends BaseDaoImpl<TmShiftinStock>
		implements ITmShiftinStockDao {

	public List<TmShiftinStockVo> getTmshiftinStockVos(Long isConfirm , TmShiftinStock tmShiftinStock , Long shiftInId){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmShiftinStockVo( ");
		hql.append("st.id,st.shiftinBill,st.shiftinDate,st.storeHoseId,st.removeStockId,sh.houseCode,sh.houseName, ");
		hql.append("rs.removeStockBill,rs.removeDate,sh2.houseCode,sh2.houseName,u.userRealName)");
		hql.append(" from TmShiftinStock st ,TmRemoveStock rs , TmStoreHouse sh , TmStoreHouse sh2,TmUser u  ");
		hql.append(" where st.removeStockId = rs.id and st.storeHoseId = sh.id and u.id = st.userId ");
		hql.append(" and rs.storeHoseId = sh2.id");
		if(tmShiftinStock != null){
			if(StringUtils.isNotBlank(tmShiftinStock.getShiftinBill()))
				hql.append(" and st.shiftinBill like '%").append(tmShiftinStock.getShiftinBill()).append("%'");
			if(tmShiftinStock.getStoreHoseId() != null)
				hql.append(" and st.storeHoseId = "+tmShiftinStock.getStoreHoseId());
		}
		if(isConfirm != null)
			hql.append(" and st.isConfirm = "+isConfirm);
		if(shiftInId != null)
			hql.append(" and st.id = "+shiftInId);
		hql.append(" order by st.id desc");
		
		List<TmShiftinStockVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
}
