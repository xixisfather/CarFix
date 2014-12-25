package com.selfsoft.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbFixShareDao;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.vo.WorkTypeHourPriceVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbFixShareDao")
public class TbFixShareDaoImpl extends BaseDaoImpl<TbFixShare> implements ITbFixShareDao{

	public List<TbFixShare> getTbFixShareByGroupFixPerson(TbFixShare tbFixShare){
		StringBuilder hql = new StringBuilder();
		hql.append("select new TbFixShare(sum(fs.sendHour),max(fs.tmUser.userRealName),fs.tmUser.id, sum(fs.sendHour*fec.workingHourPrice),sum(fec.fixHourAll))");
		hql.append(" from TbFixShare fs , TbFixEntrustContent fec , TmUser u ");
		hql.append(" where fs.tbFixEntrustContent.id = fec.id and fs.tmUser.id = u.id and fs.tbFixEntrustContent.tbFixEntrust.isvalid != 0");
		
		if(null != tbFixShare){
			if(null != tbFixShare.getTbFixEntrustContent().getTbWorkingInfo()){
				if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode())){
					hql.append(" and fs.tbFixEntrustContent.tbWorkingInfo.stationCode like '%"+
							tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode().trim()
							+"%'");
				}
				
				
				if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName())){
					hql.append(" and fs.tbFixEntrustContent.tbWorkingInfo.stationName like '%"+
							tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName().trim()
							+"%'");
				}
				
				if(null != tbFixShare.getTmUser() && tbFixShare.getTmUser().getId() != null){
					hql.append(" and fs.tmUser.id = ").append(tbFixShare.getTmUser().getId());
				}
				
				if(null != tbFixShare.getTbFixEntrustContent().getTbFixEntrust()){
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart() != null){
						Date fixDate = tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart();
						hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.fixDate >= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
					
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd() != null){
						Date fixDate = CommonMethod.addDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd(),1);
						hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.fixDate <= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
					
					/* 添加结算日期查询 */
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin() != null ||
							tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd() != null){
								
						hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.id in (select tbb.tbFixEntrust.id from TbBusinessBalance tbb where 1=1 ");
						
						if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin() != null){
							Date fixDate = tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin();
							hql.append(" and tbb.bananceDate >= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
						}
						
						if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd() != null){
							Date fixDate = CommonMethod.addDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd(),1);
							hql.append(" and tbb.bananceDate <= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
						}
						
						hql.append(")");
					}
					/* 添加结算日期查询 */
				}
				
				
			}
		}
		hql.append(" group by fs.tmUser.id");
		
		List<TbFixShare> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	
	public List<WorkTypeHourPriceVo> getWorkTypeStat(TbFixShare tbFixShare){
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select new com.selfsoft.business.vo.WorkTypeHourPriceVo(");
		hql.append(" max(fs.tmUser.tmWorkType.workName),sum(fec.sendHour),sum(fec.sendHour)*sum(fec.workingHourPrice),sum(fec.fixHour),sum(fec.fixHour)*sum(fec.workingHourPrice))");
		hql.append(" from TbFixShare fs , TbFixEntrustContent fec , TmUser u ");
		hql.append(" where fs.tbFixEntrustContent.id = fec.id and fs.tmUser.id = u.id and fs.tbFixEntrustContent.tbFixEntrust.isvalid != 0");
//		hql.append(" and fs.tmUser.tmWorkType.id = ").append(workTypeId);
		
		if(null != tbFixShare){
			if(null != tbFixShare.getTbFixEntrustContent().getTbWorkingInfo()){
				if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode())){
					hql.append(" and fs.tbFixEntrustContent.tbWorkingInfo.stationCode like '%"+
							tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationCode().trim()
							+"%'");
				}
				
				
				if(StringUtils.isNotBlank(tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName())){
					hql.append(" and fs.tbFixEntrustContent.tbWorkingInfo.stationName like '%"+
							tbFixShare.getTbFixEntrustContent().getTbWorkingInfo().getStationName().trim()
							+"%'");
				}
				
				if(null != tbFixShare.getTmUser() && tbFixShare.getTmUser().getId() != null){
					hql.append(" and fs.tmUser.id = ").append(tbFixShare.getTmUser().getId());
				}
				
				if(null != tbFixShare.getTbFixEntrustContent().getTbFixEntrust()){
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart() != null){
						Date fixDate = tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateStart();
						hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.fixDate >= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
					
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd() != null){
						Date fixDate = CommonMethod.addDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getFixDateEnd(),1);
						hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.fixDate <= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
				}
				/* 添加结算日期查询 */
				if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin() != null ||
						tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd() != null){
							
					hql.append(" and fs.tbFixEntrustContent.tbFixEntrust.id in (select tbb.tbFixEntrust.id from TbBusinessBalance tbb where 1=1 ");
					
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin() != null){
						Date fixDate = tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateBegin();
						hql.append(" and tbb.bananceDate >= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
					
					if(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd() != null){
						Date fixDate = CommonMethod.addDate(tbFixShare.getTbFixEntrustContent().getTbFixEntrust().getBalanceDateEnd(),1);
						hql.append(" and tbb.bananceDate <= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
					}
					
					hql.append(")");
				}
				/* 添加结算日期查询 */
				
			}
		}
		hql.append(" group by fs.tmUser.tmWorkType.id");
		
		
		List<WorkTypeHourPriceVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
}
