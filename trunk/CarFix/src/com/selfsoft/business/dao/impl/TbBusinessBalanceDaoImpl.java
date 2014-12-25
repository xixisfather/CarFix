package com.selfsoft.business.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbBusinessBalanceDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixShare;
import com.selfsoft.business.vo.BusinessBalanceCostPriceVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.dao.BaseDaoImpl;

@Repository("tbBusinessBalanceDao")
public class TbBusinessBalanceDaoImpl extends BaseDaoImpl<TbBusinessBalance> implements ITbBusinessBalanceDao{

	public List<Object> getTotalCostPriceBusinessBalance(BusinessBalanceCostPriceVo businessBalanceCostPriceVo){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(sod.quantity*pi.cost_price) cost_price from tb_business_balance bb,");
		sql.append(" tm_stock_out so, tm_stockout_detail sod , tb_part_info pi ,tb_fix_entrust fe,tm_user u , tb_car_info ci");
		sql.append(" where so.id = sod.stockout_id and sod.balance_id = bb.id and fe.entrust_code = so.trust_bill and ci.id = fe.car_info_id");
		sql.append(" and pi.id = sod.partinfo_id and u.id = fe.user_id and sod.balance_id is not null and so.is_confirm not in (8000)");
		if(businessBalanceCostPriceVo != null){
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getBalanceCode())){
				sql.append(" and bb.balance_code like '%").append(businessBalanceCostPriceVo.getBalanceCode()).append("%'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getEntrustCode())){
				sql.append(" and fe.entrust_code like '%").append(businessBalanceCostPriceVo.getEntrustCode()).append("%'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getBeginBalanceDeadTime())){
				sql.append(" and convert(varchar(10),bb.banance_date,120) >= '").append(businessBalanceCostPriceVo.getBeginBalanceDeadTime()).append("'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getEndBalanceDeadTime())){
				sql.append(" and convert(varchar(10),bb.banance_date,120) <= '").append(businessBalanceCostPriceVo.getEndBalanceDeadTime()).append("'");
			}
			if(businessBalanceCostPriceVo.getUserId() != null){
				sql.append(" and bb.user_id = ").append(businessBalanceCostPriceVo.getUserId());
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getLicenseCode())){
				sql.append(" and ci.license_code like '%").append(businessBalanceCostPriceVo.getLicenseCode()).append("%'");
			}
			if(businessBalanceCostPriceVo.getServiceUserId() != null){
				sql.append(" and fe.user_id="+businessBalanceCostPriceVo.getServiceUserId());
			}
			
		}
		sql.append(" union all ");
		sql.append(" select sum(m.part_quantity*pi.cost_price) wx_cp from tb_business_balance bb,tb_maintain_part_content m  , ");
		sql.append(" tb_part_info pi ,tb_fix_entrust fe,tm_user u , tb_car_info ci ");
		sql.append(" where m.balance_id = bb.id and m.entrust_id = fe.id and pi.id = m.part_id and ci.id = fe.car_info_id and  ");
		sql.append(" u.id = fe.user_id and m.balance_id is not null  and m.is_confirm not in (8000) ");
		if(businessBalanceCostPriceVo != null){
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getBalanceCode())){
				sql.append(" and bb.balance_code like '%").append(businessBalanceCostPriceVo.getBalanceCode()).append("%'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getEntrustCode())){
				sql.append(" and fe.entrust_code like '%").append(businessBalanceCostPriceVo.getEntrustCode()).append("%'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getBeginBalanceDeadTime())){
				sql.append(" and convert(varchar(10),bb.banance_date,120) >= '").append(businessBalanceCostPriceVo.getBeginBalanceDeadTime()).append("'");
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getEndBalanceDeadTime())){
				sql.append(" and convert(varchar(10),bb.banance_date,120) <= '").append(businessBalanceCostPriceVo.getEndBalanceDeadTime()).append("'");
			}
			if(businessBalanceCostPriceVo.getUserId() != null){
				sql.append(" and bb.user_id = ").append(businessBalanceCostPriceVo.getUserId());
			}
			if(StringUtils.isNotBlank(businessBalanceCostPriceVo.getLicenseCode())){
				sql.append(" and ci.license_code like '%").append(businessBalanceCostPriceVo.getLicenseCode()).append("%'");
			}
			if(businessBalanceCostPriceVo.getServiceUserId() != null){
				sql.append(" and fe.user_id="+businessBalanceCostPriceVo.getServiceUserId());
			}
		}
		
		sql.append(" union all ");
		sql.append(" select sum(sod.quantity*pi.cost_price) cost_price");
		sql.append(" from tm_stock_out so, tm_stockout_detail sod , tb_part_info pi");
		sql.append(" where so.id = sod.stockout_id and pi.id = sod.partinfo_id and sod.balance_id is not null and so.is_confirm not in (8000) and so.trust_bill = ''");
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	public List<Long> getTbEntrustByBalanceDate(TbFixEntrust tbFixEntrust){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select tbb.tbFixEntrust.id from TbBusinessBalance tbb where 1=1");
		if(tbFixEntrust.getBalanceDateBegin() != null || tbFixEntrust.getBalanceDateEnd() != null){
					
			if(tbFixEntrust.getBalanceDateBegin() != null){
				Date fixDate = tbFixEntrust.getBalanceDateBegin();
				hql.append(" and tbb.bananceDate >= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
			}
			
			if(tbFixEntrust.getBalanceDateEnd() != null){
				Date fixDate = CommonMethod.addDate(tbFixEntrust.getBalanceDateEnd(),1);
				hql.append(" and tbb.bananceDate <= '"+ CommonMethod.parseDateToString(fixDate, "yyyy-MM-dd HH:mm:ss")).append("'");
			}
			
		}		

		List<Long> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}

}
