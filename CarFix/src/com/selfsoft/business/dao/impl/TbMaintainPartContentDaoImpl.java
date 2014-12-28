package com.selfsoft.business.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITbMaintainPartContentDao;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbMaintainPartContentDao")
public class TbMaintainPartContentDaoImpl extends BaseDaoImpl<TbMaintainPartContent> implements
		ITbMaintainPartContentDao {


	public List<TbMaintianVo> getTbMaintianVos(String isConfirms, TbMaintainPartContent queryEntity){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TbMaintianVo(t.maintainCode,max(t.entrustId),max(b.entrustCode),max(t.isConfirm),max(b.tbCarInfo.licenseCode),max(b.tbCustomer.customerName),max(t.totalPrice))");
		hql.append(" from TbMaintainPartContent t , TbFixEntrust b ");
		hql.append(" where t.entrustId = b.id ");
		if(queryEntity != null){
			if(StringUtils.isNotBlank(queryEntity.getMaintainCode())){
				hql.append(" and t.maintainCode like '%").append(queryEntity.getMaintainCode()).append("%'");
			}
		}
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and t.isConfirm in ("+isConfirms+")");
		hql.append(" group by t.maintainCode ");
		hql.append(" order by t.maintainCode desc");
		List<TbMaintianVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,Long entrustId,Long balanceId,Long balanceType){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TbMaintianVo(t.partId,t.partQuantity,t.price,");
		hql.append("pi.partCode,pi.partName,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,pi.tmUnit.unitName,");
		hql.append("t.cliamPartPersonId,u.userRealName,t.maintainCode,t.isFree,fe.tbCustomer.customerName,");
		hql.append("fe.tbCarInfo.licenseCode,fe.tmFixType.fixType,fe.fixDate,t.balanceId,fe.entrustCode,pi.storeLocation,t.totalPrice,t.zl,t.xmlx,t.projectType,t.isPrint,t.id)");
		hql.append(" from TbMaintainPartContent t , TbPartInfo pi , TmUser u,TbFixEntrust fe");
		hql.append(" where t.partId = pi.id and t.cliamPartPersonId = u.id and t.entrustId = fe.id");
		if(StringUtils.isNotBlank(maintainCode))
			hql.append(" and t.maintainCode = '").append(maintainCode).append("'");
		if(entrustId != null)
			hql.append(" and t.entrustId = "+ entrustId);
		if(balanceId != null)
			hql.append(" and t.balanceId = "+ balanceId);
		//else 
		
		if(balanceType != null){
			if(balanceType.equals(Constants.BALANCE_ALL)){
				//得到所有明细 无论是否结算都取出来
			}
			if(balanceType.equals(Constants.BALANCE_ISNULL)){
				//得到未结算的明细
				hql.append(" and t.balanceId is null");
			}
			if(balanceType.equals(Constants.BALANCE_NOTNULL)){
				//得到已结算的明细
				hql.append(" and t.balanceId is not null");
			}
		}
		
		hql.append(" order by t.id ");
		List<TbMaintianVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
		
		
		/*StringBuilder hql = new StringBuilder();
		hql.append("select m.part_id,m.part_Quantity,m.price,pi.part_Code,pi.part_Name,sh.house_Code,sh.house_Name,un.unit_name,");
		hql.append("m.cliam_Part_Person_Id,u.user_Real_Name,m.maintain_Code,m.is_Free,c.customer_name,");
		hql.append("ci.license_Code,ft.fix_type,fe.fix_date,m.balance_Id,fe.entrust_Code,pi.store_Location,");
		hql.append("m.total_Price,m.zl,m.xmlx,m.project_Type,m.is_Print,m.id ");
		hql.append("from tb_maintain_part_content m , tb_part_info pi ,tm_Store_House sh , tm_user u ,tb_fix_entrust fe,");
		hql.append("tm_unit un,tb_car_info ci,tb_customer c,tm_fix_type ft ");
		hql.append("where m.part_id = pi.id and m.cliam_Part_Person_Id=u.id and m.entrust_id=fe.id and fe.fix_type_id = ft.id ");
		hql.append("and pi.store_house_id=sh.id and pi.unit_id = un.id and ci.id = fe.car_info_id and c.id = fe.customer_id ");
		
		if(StringUtils.isNotBlank(maintainCode))
			hql.append(" and m.maintain_Code = '").append(maintainCode).append("'");
		if(entrustId != null)
			hql.append(" and m.entrust_Id = "+ entrustId);
		if(balanceId != null)
			hql.append(" and m.balance_Id = "+ balanceId);
		
		if(balanceType != null){
			if(balanceType.equals(Constants.BALANCE_ALL)){
				//得到所有明细 无论是否结算都取出来
			}
			if(balanceType.equals(Constants.BALANCE_ISNULL)){
				//得到未结算的明细
				hql.append(" and m.balance_Id is null");
			}
			if(balanceType.equals(Constants.BALANCE_NOTNULL)){
				//得到已结算的明细
				hql.append(" and m.balance_Id is not null");
			}
		}
		
		hql.append(" order by m.id ");
		List<TbMaintianVo> result = this.findTbMaintainBySql(hql.toString());
		return result;*/
		
		
	}
	
	public List<TbMaintianVo> getTbMaintianDetailVosByPrint(String maintainCode,Long entrustId,Long balanceId,Long balanceType){

		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TbMaintianVo(t.partId,t.partQuantity,t.price,");
		hql.append("pi.partCode,pi.partName,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,pi.tmUnit.unitName,");
		hql.append("t.cliamPartPersonId,u.userRealName,t.maintainCode,t.isFree,fe.tbCustomer.customerName,");
		hql.append("fe.tbCarInfo.licenseCode,fe.tmFixType.fixType,fe.fixDate,t.balanceId,fe.entrustCode,pi.storeLocation,t.totalPrice,t.zl,t.xmlx,t.projectType,t.isPrint,t.id)");
		hql.append(" from TbMaintainPartContent t , TbPartInfo pi , TmUser u,TbFixEntrust fe");
		hql.append(" where t.partId = pi.id and t.cliamPartPersonId = u.id and t.entrustId = fe.id and t.isPrint='Y'");
		if(StringUtils.isNotBlank(maintainCode))
			hql.append(" and t.maintainCode = '").append(maintainCode).append("'");
		if(entrustId != null)
			hql.append(" and t.entrustId = "+ entrustId);
		if(balanceId != null)
			hql.append(" and t.balanceId = "+ balanceId);
		//else 
		
		if(balanceType != null){
			if(balanceType.equals(Constants.BALANCE_ALL)){
				//得到所有明细 无论是否结算都取出来
			}
			if(balanceType.equals(Constants.BALANCE_ISNULL)){
				//得到未结算的明细
				hql.append(" and t.balanceId is null");
			}
			if(balanceType.equals(Constants.BALANCE_NOTNULL)){
				//得到已结算的明细
				hql.append(" and t.balanceId is not null");
			}
		}
		
		hql.append(" order by t.id ");
		List<TbMaintianVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	public List<TbMaintianVo> findTbMaintainBySql(final String sql){
		List<TbMaintianVo> result  = (List<TbMaintianVo>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						List<TbMaintianVo> result = new ArrayList<TbMaintianVo>();
						List list =  query.list();
						for(Object object : list){
							Object[] objs = (Object[]) object;
							Long maiantainPartId = objs[0]!=null?new Long(objs[0].toString()):null;
							Double partQuantity = objs[1]!=null?new Double(objs[1].toString()):null;
							Double price = objs[2]!=null?new Double(objs[2].toString()):null;
							String partCode = objs[3]!=null?objs[3].toString():null;
							String partName = objs[4]!=null?objs[4].toString():null;
							String houseCode = objs[5]!=null?objs[5].toString():null;
							String houseName = objs[6]!=null?objs[6].toString():null;
							String unitname = objs[7]!=null?objs[7].toString():null;
							Long cliamPartPersonId = objs[8]!=null?new Long(objs[8].toString()):null;
							String userRealName = objs[9]!=null?objs[9].toString():null;
							String maintainCode = objs[10]!=null?objs[10].toString():null;
							Long isFree = objs[11]!=null?new Long(objs[11].toString()):null;
							String customerName	 = objs[12]!=null?objs[12].toString():null;
							String licenseCode = objs[13]!=null?objs[13].toString():null;
							String fixType = objs[14]!=null?objs[14].toString():null;		
							Date fixDate = objs[15]!=null?CommonMethod.parseStringToDate(objs[15].toString(), "yyyy-MM-dd"):null;
							Long balanceId = objs[16]!=null?new Long(objs[16].toString()):null;
							String entrustCode = objs[17]!=null?objs[17].toString():null;
							String storeLocation = objs[18]!=null?objs[18].toString():null;
							Double totalPrice = objs[19]!=null?new Double(objs[19].toString()):null;
							String zl = objs[20]!=null?objs[20].toString():null;
							String xmlx = objs[21]!=null?objs[21].toString():null;
							String project_Type = objs[22]!=null?objs[22].toString():null;
							String is_Print = objs[23]!=null?objs[23].toString():null;
							Long tbMaintainId = objs[24]!=null?new Long(objs[24].toString()):null;
							
							TbMaintianVo tbMaintianVo = new TbMaintianVo(maiantainPartId,partQuantity,price,partCode,partName,houseCode,houseName,
								unitname,cliamPartPersonId,userRealName,maintainCode,isFree,customerName,licenseCode,fixType,fixDate,balanceId,
								entrustCode,storeLocation,totalPrice,zl,xmlx,project_Type,is_Print,tbMaintainId
							);
							result.add(tbMaintianVo);
						}
						return result;
					}	
				});
		
		return null;
	}
	
	
	public boolean deleteTbMaintain(String maintainCode){
		String hql = "delete from TbMaintainPartContent t where t.maintainCode = '"+maintainCode+"'";
		int i = this.getHibernateTemplate().bulkUpdate(hql);
		
		if(i>0)
			return true;
		
		return false;
	}
	
	
	public void updateMaintainDetailBalance(Long entrustId , Long balanceId){
		String hql = "update TbMaintainPartContent t set  t.balanceId = "+balanceId+ "  where " +
				" t.entrustId = "+entrustId  + " and t.balanceId is null";
		
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		 hql = "update TbMaintainPartContent t set  t.isConfirm = "+Constants.FINSH_BALANCE+" where  t.entrustId = "+entrustId ;
		
		
		this.getHibernateTemplate().bulkUpdate(hql);

	}
	
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * @param partId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long customerId){
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select m.price from TbMaintainPartContent m , TbFixEntrust fe ");
		hql.append(" where m.entrustId = fe.id ");
		hql.append(" and m.partId = ").append(partId);
		hql.append(" and fe.tbCustomer.id = ").append(customerId);
		hql.append(" order by m.stockOutDate desc");
		
		
		List<Object> result = this.getHibernateTemplate().find(hql.toString());
		
		if(null != result && result.size() > 0)
		{
			if( result.get(0)== null )
				return 0D;
			else
				return Double.valueOf(result.get(0).toString());
		}
		
		
		return 0D;
	}
	
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,TbMaintainPartContent tbMaintainPartContent){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TbMaintianVo(t.partId,t.partQuantity,t.price,");
		hql.append("pi.partCode,pi.partName,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,pi.tmUnit.unitName,");
		hql.append("t.cliamPartPersonId,u.userRealName,t.maintainCode,t.isFree,fe.tbCustomer.customerName,");
		hql.append("fe.tbCarInfo.licenseCode,fe.tmFixType.fixType,fe.fixDate,t.balanceId,fe.entrustCode,pi.storeLocation,t.totalPrice,");
		hql.append("t.zl,t.xmlx,t.projectType,t.isPrint,t.id)");
		hql.append(" from TbMaintainPartContent t , TbPartInfo pi , TmUser u,TbFixEntrust fe");
		hql.append(" where t.partId = pi.id and t.cliamPartPersonId = u.id and t.entrustId = fe.id");
		if(null != tbMaintainPartContent){
			
			if(StringUtils.isNotBlank(maintainCode))
				hql.append(" and t.maintainCode = '").append(maintainCode).append("'");
			if(StringUtils.isNotBlank(tbMaintainPartContent.getBeginDate())){
				hql.append(" and t.stockOutDate >= '").append(tbMaintainPartContent.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbMaintainPartContent.getEndDate())){
				hql.append(" and t.stockOutDate <= '").append(tbMaintainPartContent.getEndDate()).append("'");
			}
			
			if(StringUtils.isNotBlank(tbMaintainPartContent.getUserRealName())){
				hql.append(" and u.userRealName like '%"+tbMaintainPartContent.getUserRealName()+"%'");
			}
			if(StringUtils.isNotBlank(tbMaintainPartContent.getMaintainCode())){
				hql.append(" and t.maintainCode like '%"+tbMaintainPartContent.getMaintainCode()+"%'");
			}
			
			if(StringUtils.isNotBlank(tbMaintainPartContent.getPartCode())){
				hql.append(" and pi.partCode like '%").append(tbMaintainPartContent.getPartCode()).append("%'");
			}
			if(StringUtils.isNotBlank(tbMaintainPartContent.getPartName())){
				hql.append(" and pi.partName like '%").append(tbMaintainPartContent.getPartName()).append("%'");
			}
		}
		hql.append(" order by t.id ");
		List<TbMaintianVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	public void updateTbMaintainBalanceId(Long balanceId,Long status ,Long entrustId){
		String hql = "update TbMaintainPartContent set balanceId = "+balanceId+",isConfirm="+status+" where entrustId = "+ entrustId ;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	
	public double sumMaintainByEntrustId(Long entrustId){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(m.partQuantity*pi.costPrice) from TbMaintainPartContent m , TbPartInfo pi where  pi.id=m.partId ");
		hql.append(" and m.entrustId = "+entrustId);
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	public double sumMaintainCostPriceByBalanceId(Long balanceId){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(m.partQuantity*pi.costPrice) from TbMaintainPartContent m , TbPartInfo pi where  pi.id=m.partId ");
		hql.append(" and m.balanceId = "+balanceId);
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	public double sumMaintainPriceByBalanceId(Long balanceId){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(m.partQuantity*m.price) from TbMaintainPartContent m where  ");
		hql.append(" m.balanceId = "+balanceId);
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	public double getAllStockOutNum(Long partId){
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(m.part_quantity) mq from tb_maintain_part_content m where m.is_confirm not in (8000) and m.part_id = "+partId);
		sql.append(" union all ");
		sql.append("select sum(sod.quantity) sq from tm_stock_out so, tm_stockout_detail sod where so.is_confirm not in (8000) and  so.id = sod.stockout_id and sod.partinfo_id= "+partId);
		List list  = this.findByOriginSql(sql.toString(), null);
		Double maintainQuantity = list.get(0)!= null ? Double.valueOf(list.get(0).toString()):0D;
		Double stockoutQauntity = list.get(1)!= null ? Double.valueOf(list.get(1).toString()):0D;
		
		return maintainQuantity+stockoutQauntity;
	}
}
