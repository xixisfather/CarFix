package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmStockOutDao")
public class TmStockOutDaoImpl extends BaseDaoImpl<TmStockOut> implements ITmStockOutDao {

	
	public List<TmStockOutDetVo>  getStockOutDetVos(String isConfirm ,Long stockOutId,Long stockOutType){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockOutDetVo(so.id,sod.id,so.stockOutCode,so.stockOutDate,sod.partinfoId,pi.partCode,pi.partName,pi.tmUnit.unitName");
		hql.append(" ,sod.quantity,sod.price,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,sod.quantity*sod.price,pi.costPrice,sod.stockInDetailId,sod.isFree,pi.storeLocation,sod.zl,sod.xmlx,sod.projectType)");
		hql.append(" from TmStockOut so , TmStockoutDetail sod , TbPartInfo pi");
		hql.append(" where so.id = sod.stockoutId and sod.partinfoId = pi.id");
		if(stockOutType != null)
			hql.append(" and so.outType = "+stockOutType);
		if(stockOutId != null)
			hql.append(" and sod.stockoutId = "+stockOutId);
		if(StringUtils.isNotBlank(isConfirm))
			hql.append(" and so.isConfirm in("+isConfirm+")");
		hql.append(" order by sod.id ");
		List<TmStockOutDetVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	
	public List<TmStockOutVo> getStockOutVos(String isConfirms , Long stockOutType,TmStockOut tmStockOut,Long stockOutId){
		StringBuilder hql = new StringBuilder();
//		hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
//		hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,c.customerName,st.id,st.soleName ,u.userRealName,so.outType)");
//		hql.append(" from TmStockOut so , TbCustomer c , TmSoleType st , TmUser u");
//		hql.append(" where so.customerBill = c.id and so.soleTypeId = st.id and u.id = so.drawPeople");
		
		
		if(stockOutType.equals(StockTypeElements.DRAWSTOCKOUT.getElementValue()) || stockOutType.equals(StockTypeElements.INNERSTOCKOUT.getElementValue()) ){		//领用
			hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
			hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,so.trustBill,so.customerBill,so.trustBill,u.userRealName,so.outType,so.isConfirm,'',u2.userRealName)");
			hql.append("from TmStockOut so ,TmUser u , TmUser u2 ");
			hql.append("where u.id = so.drawPeople and u2.id = so.userId");
		}
		if(stockOutType.equals(StockTypeElements.SELLSTOCKOUT.getElementValue())){		//销售
			hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
			hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,c.customerName,st.id,st.soleName ,st.soleName,so.outType,so.isConfirm,c.customerCode,u.userRealName)");
			hql.append(" from TmStockOut so , TbCustomer c , TmSoleType st ,TmUser u ");
			hql.append(" where so.customerBill = c.id and so.soleTypeId = st.id and u.id = so.userId");
		}
		if(stockOutType.equals(StockTypeElements.ALLOTSTOCKOUT.getElementValue())){		//调拨
			hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
			hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,c.customerName,c.id,c.customerName ,c.customerName,so.outType,so.isConfirm,c.customerCode,u.userRealName)");
			hql.append(" from TmStockOut so , TbCustomer c ,TmUser u ");
			hql.append(" where so.customerBill = c.id and  u.id = so.userId");
		}
		if(stockOutType.equals(StockTypeElements.SHATTERSTOCKOUT.getElementValue())){	//报损
			hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
			hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,so.trustBill,so.id,so.trustBill ,so.trustBill,so.outType,so.isConfirm,'',u.userRealName)");
			hql.append(" from TmStockOut so ,TmUser u ");
			hql.append(" where 1=1 and  u.id = so.userId ");
		}
		
		if(stockOutType.equals(StockTypeElements.STOCKRETURN.getElementValue())){	//采购退货
			hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
			hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,so.trustBill,so.customerBill,so.trustBill,so.trustBill,so.outType,si.id,si.stockInCode)");
			hql.append(" from TmStockOut so , TmStockIn si ");
			hql.append(" where so.stockInId = si.id ");
			
		}
	
		
		if(tmStockOut != null){
			if(tmStockOut.getDrawPeople()!= null)
				hql.append(" and so.drawPeople = "+tmStockOut.getDrawPeople());
			if(tmStockOut.getCustomerBill()!= null)	
				hql.append(" and so.customerBill = "+tmStockOut.getCustomerBill());	
			if(StringUtils.isNotBlank(tmStockOut.getStockOutCode()))
				hql.append(" and so.stockOutCode like '%").append(tmStockOut.getStockOutCode()).append("%'");
			if(StringUtils.isNotBlank(tmStockOut.getTrustBill()))
				hql.append(" and so.trustBill = '").append(tmStockOut.getTrustBill()).append("'");
			if(tmStockOut.getSellType() != null){
				//委托销售类型
				if(tmStockOut.getSellType() == Constants.SELLENTRUST)
					hql.append(" and so.trustBill is not null");
				//单独销售类型
				if(tmStockOut.getSellType() == Constants.SELLCUSTOMER)
					hql.append(" and so.trustBill = ''");
			}
			if(StringUtils.isNotBlank(tmStockOut.getBeginDate())){
				hql.append(" and so.stockOutDate >= '").append(tmStockOut.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockOut.getEndDate())){
				hql.append(" and so.stockOutDate <= '").append(tmStockOut.getEndDate()).append("'");
			}
				
		}
		if(stockOutType != null)
			hql.append(" and so.outType = "+stockOutType);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and so.isConfirm in ("+isConfirms+")");
		if(stockOutId != null)
			hql.append(" and so.id = ").append(stockOutId);
		hql.append(" order by so.createDate desc , so.id desc");
		
		List<TmStockOutVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	public void deleteStockOutDetail(Long stockOutId){
		String hql = "delete from TmStockoutDetail t where t.stockoutId = "+stockOutId;
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	
	public List<TmStockOutDetVo>  getStockOutDetVosBySell(String isConfirms ,Long stockOutType,String trustBill,Long customerId,Long balanceId,Long stockoutId ,Long balanceType ){
		StringBuilder hql = new StringBuilder();
		if(customerId != null){
			hql.append("select new com.selfsoft.business.vo.TmStockOutDetVo(so.id,sod.id,so.stockOutCode,so.stockOutDate,sod.partinfoId,pi.partCode,pi.partName,pi.tmUnit.unitName");
			hql.append(" ,sod.quantity,sod.price,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,sod.quantity*sod.price,pi.costPrice,sod.stockInDetailId,so.trustBill");
			hql.append(",c.id,c.customerName,sod.isFree,sod.balanceId,sod.zl,sod.xmlx,sod.projectType)");
			hql.append(" from TmStockOut so , TmStockoutDetail sod , TbPartInfo pi , TbCustomer c");
			hql.append(" where so.id = sod.stockoutId and sod.partinfoId = pi.id and so.customerBill = c.id");
			hql.append(" and so.trustBill = ''");
		}else{
			hql.append("select new com.selfsoft.business.vo.TmStockOutDetVo(so.id,sod.id,so.stockOutCode,so.stockOutDate,sod.partinfoId,pi.partCode,pi.partName,pi.tmUnit.unitName");
			hql.append(" ,sod.quantity,sod.price,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,sod.quantity*sod.price,pi.costPrice,sod.stockInDetailId,so.trustBill");
			hql.append(",so.id,so.stockOutCode,sod.isFree,sod.balanceId,sod.zl,sod.xmlx,sod.projectType)");
			hql.append(" from TmStockOut so , TmStockoutDetail sod , TbPartInfo pi");
			hql.append(" where so.id = sod.stockoutId and sod.partinfoId = pi.id");
		}
		if(stockOutType != null)
			hql.append(" and so.outType = "+stockOutType);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and so.isConfirm in ("+isConfirms+")");
		if(StringUtils.isNotBlank(trustBill))
			hql.append(" and so.trustBill = '").append(trustBill).append("'");
		if(stockoutId != null)
			hql.append(" and so.id = ").append(stockoutId);
		if(balanceId != null){
			hql.append(" and sod.balanceId = "+ balanceId);
		}
		
		if(balanceType != null){
			if(balanceType.equals(Constants.BALANCE_ALL)){
				//得到所有明细 无论是否结算都取出来
			}
			if(balanceType.equals(Constants.BALANCE_ISNULL)){
				//得到未结算的明细
				hql.append(" and sod.balanceId is null");
			}
			if(balanceType.equals(Constants.BALANCE_NOTNULL)){
				//得到已结算的明细
				hql.append(" and sod.balanceId is not null");
			}
		}
		hql.append(" order by so.id desc");
		
		List<TmStockOutDetVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	public void updateSellBalanceId(Long stockoutId , Long balanceId ){
		String hql = "update TmStockoutDetail t set t.balanceId ="+balanceId+" where " +
						" t.stockoutId = "+stockoutId  +
						" and t.balanceId is null";
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		
	    hql = "update TmStockOut t set  t.isConfirm = "+Constants.FINSH_BALANCE+" where  t.id = "+stockoutId ;
			
			
		this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	public String buildBalanceSellCondition(Long sellType , Long balanceType ,BalanceSellCountVo balanceSellCountVo ){
		StringBuilder hql = new StringBuilder();
		hql.append("select count(sod.id) from TmStockOut so ,TmStockoutDetail sod where so.id = sod.stockoutId and so.isConfirm not in(8000) ");
		if(sellType.equals(Constants.BALANCEFIX)){
			//委托书销售
			hql.append(" and so.trustBill != '' ");
			hql.append(" and exists (select fe.entrustCode from TbFixEntrust fe where fe.entrustCode = so.trustBill ");
			if(balanceSellCountVo!= null){
				if(balanceSellCountVo.getCarModelType() != null){
					hql.append(" and fe.tbCarInfo.tmCarModelType.id = ").append(balanceSellCountVo.getCarModelType());
				}
			}
			hql.append(")");
		}
		
		if(sellType.equals(Constants.BALANCEALONE)){
			//单独销售
			hql.append(" and so.trustBill = ''");
		}
		
		if(balanceType.equals(Constants.BALANCE_ALL)){
			//得到所有明细 无论是否结算都取出来
		}
		if(balanceType.equals(Constants.BALANCE_ISNULL)){
			//得到未结算的明细
			hql.append(" and sod.balanceId is null");
		}
		if(balanceType.equals(Constants.BALANCE_NOTNULL)){
			//得到已结算的明细
			hql.append(" and sod.balanceId is not null");
		}
		if(balanceSellCountVo!= null){
			if(StringUtils.isNotBlank(balanceSellCountVo.getBeginDate())){
				hql.append(" and so.stockOutDate >= '").append(balanceSellCountVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(balanceSellCountVo.getEndDate())){
				hql.append(" and so.stockOutDate <= '").append(balanceSellCountVo.getEndDate()).append("'");
			}
			
		}
		return hql.toString();
	}
	
	
	
	
	public Long getBalanceSellCount(Long sellType , Long balanceType,BalanceSellCountVo balanceSellCountVo){
		String hql = this.buildBalanceSellCondition(sellType, balanceType, balanceSellCountVo);
		List result = this.getHibernateTemplate().find(hql);
		return Long.valueOf(result.get(0).toString());
	}
	
	/**
	 * 维修发料已（未）结算数
	 * @param balanceType
	 * @return
	 */
	public Long getMaintainBalanceSellCount(Long balanceType){
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select count(distinct m.maintainCode) from TbMaintainPartContent m ");
		hql.append(" where exists (select fe.entrustCode from TbFixEntrust fe where fe.id = m.entrustId) ");
		hql.append(" and m.isConfirm not in(8000) ");
		if(balanceType.equals(Constants.BALANCE_ISNULL)){
			//得到未结算的明细
			hql.append(" and m.balanceId is null");
		}
		if(balanceType.equals(Constants.BALANCE_NOTNULL)){
			//得到已结算的明细
			hql.append(" and m.balanceId is not null");
		}
		
		List result = this.getHibernateTemplate().find(hql.toString());
		return Long.valueOf(result.get(0).toString());
	}
	
	
	
	public List<TmStockOutDetVo>  getStockOutDetVos(Object queryObj){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockOutDetVo(so.id,sod.id,so.stockOutCode,so.stockOutDate,sod.partinfoId,pi.partCode,pi.partName,pi.tmUnit.unitName");
		hql.append(" ,sod.quantity,sod.price,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,sod.quantity*sod.price,pi.costPrice,sod.stockInDetailId,sod.isFree,pi.storeLocation,sod.zl,sod.xmlx,sod.projectType)");
		hql.append(" from TmStockOut so , TmStockoutDetail sod , TbPartInfo pi");
		hql.append(" where so.id = sod.stockoutId and sod.partinfoId = pi.id");
		hql.append(" and so.outType = "+StockTypeElements.STOCKRETURN.getElementValue());
		hql.append(" and so.isConfirm = "+Constants.CONFIRM);
		
		
		if(queryObj instanceof TmStockIn){
			TmStockIn tmStockIn = (TmStockIn)queryObj;
			if(null != tmStockIn){
				
				if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
					hql.append(" and pi.partCode like '%").append(tmStockIn.getPartCode()).append("%'");
				}
				if(StringUtils.isNotBlank(tmStockIn.getPartName())){
					hql.append(" and pi.partName like '%").append(tmStockIn.getPartName()).append("%'");
				}
				
				if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
					hql.append(" and so.stockOutDate >= '").append(tmStockIn.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
					hql.append(" and so.stockOutDate <= '").append(tmStockIn.getEndDate()).append("'");
				}
				hql.append(" and sod.stockInDetailId in ( select sid.id from TbCustomer c,TmStockIn si ,TmStockinDetail sid where si.id = sid.stockId and c.id = si.supplierId and c.id = si.supplierId" );
				if(tmStockIn.getSupplierId()!=null){
					hql.append(" and si.supplierId = '").append(tmStockIn.getSupplierId()).append("'");
				}
				hql.append(" )");
			}
		}
		hql.append(" order by sod.id ");
		List<TmStockOutDetVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	public List<TmStockOutVo> getStockOutVos(Object queryObj){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockOutVo(so.id,so.stockOutCode,so.trustBill,so.stockOutDate,so.userId,so.createDate,");
		hql.append(" so.totalQuantity,so.totalPrice,so.customerBill,c.customerName,so.customerBill,so.trustBill,c.customerCode,so.outType,si.id,si.stockInCode)");
		hql.append(" from TmStockOut so , TmStockIn si, TmStockoutDetail sod, TbPartInfo pi , TbCustomer c");
		hql.append(" where so.stockInId = si.id and  so.id = sod.stockoutId and sod.partinfoId = pi.id and c.id = si.supplierId");
		hql.append(" and so.outType = "+StockTypeElements.STOCKRETURN.getElementValue());	
		hql.append(" and so.isConfirm = "+Constants.CONFIRM);
		if(queryObj instanceof TmStockIn){
			TmStockIn tmStockIn = (TmStockIn)queryObj;
			if(null != tmStockIn){
				if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
					hql.append(" and pi.partCode like '%").append(tmStockIn.getPartCode()).append("%'");
				}
				if(StringUtils.isNotBlank(tmStockIn.getPartName())){
					hql.append(" and pi.partName like '%").append(tmStockIn.getPartName()).append("%'");
				}
				
				if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
					hql.append(" and so.stockOutDate >= '").append(tmStockIn.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
					hql.append(" and so.stockOutDate <= '").append(tmStockIn.getEndDate()).append("'");
				}
				if(tmStockIn.getSupplierId()!=null){
					hql.append(" and si.supplierId = '").append(tmStockIn.getSupplierId()).append("'");
				}
			}
			
		}
		hql.append(" order by so.id ");
		List<TmStockOutVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
	
	
	public List<Object[]> getStockOutCustomerTotalPriceByStat(TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		hql.append("select t.id,t.customer_Code,t.customer_Name,tp from Tb_Customer  t , ");
		
		hql.append("( select sum(sod.quantity*sod.price) tp ,si.supplier_Id  sid ");
		hql.append(" from Tm_Stock_out so,Tb_Customer c , tm_stockout_detail sod , tb_part_info pi ,tm_stock_in si");
		hql.append(" where so.id = sod.stockout_id and pi.id = sod.partinfo_id and c.id = si.supplier_Id and si.id=so.stockIn_Id ");
		hql.append(" and so.is_confirm = ").append(Constants.CONFIRM);
		hql.append(" and so.out_Type = ").append(StockTypeElements.STOCKRETURN.getElementValue());
		if(tmStockIn != null){
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and so.stock_Out_Date >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and so.stock_Out_Date <= '").append(tmStockIn.getEndDate()).append("'");
			}
			
			 if(tmStockIn.getSupplierId() != null){
				 hql.append(" and si.supplier_Id = ").append(tmStockIn.getSupplierId());
			 }
				
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())  ||StringUtils.isNotBlank(tmStockIn.getPartName())){
				hql.append(" and so.stockIn_Id in ");
	            hql.append("( select tsi.id from tm_stockin_detail tsd , tm_stock_in tsi,tb_part_info tpi where tsi.id=tsd.stock_id and tpi.id = tsd.partinfo_id ");
				
	            if(StringUtils.isNotBlank(tmStockIn.getPartCode()))
	            	hql.append(" and tpi.part_Code like '%").append(tmStockIn.getPartCode()).append("%'");
	            if(StringUtils.isNotBlank(tmStockIn.getPartName()))
	            	hql.append(" and tpi.part_name like '%").append(tmStockIn.getPartName()).append("%'");
	            
	            hql.append(" )");
	            
			}
		}
		
		hql.append(" group by si.supplier_Id ) g");
		hql.append(" where g.sid = t.id ");
		
		
		List<Object[]> result = findByOriginSql(hql.toString(), null);
		return result;
	}
	
	
	public double sumStockoutDetailByEntrustCode(String entrustCode){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(sod.quantity*pi.costPrice) from TmStockOut so,TmStockoutDetail sod, TbPartInfo pi  where so.id = sod.stockoutId ");
		hql.append(" and pi.id=sod.partinfoId and so.trustBill = '"+entrustCode+"'");
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	
	public double sumSingleSellByBanalce(Long balanceId){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(sod.quantity*pi.costPrice) from TmStockoutDetail sod, TbPartInfo pi where pi.id=sod.partinfoId ");
		hql.append(" and sod.balanceId="+balanceId);
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	public double sumStockoutByBalanceId(Long balanceId){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(sod.quantity*sod.price) from TmStockOut so,TmStockoutDetail sod  where so.id = sod.stockoutId ");
		hql.append(" aand sod.balanceId = '"+balanceId+"'");
		List list = this.getHibernateTemplate().find(hql.toString());
		if(list != null && list.get(0) != null){
			return CommonMethod.convertRadixPoint(Double.valueOf(list.get(0).toString()),2);
		}
		return 0D;
	}
	
	public List<TmStockOutDetVo>  getStockOutDetVos(TmStockOut tmStockOut,Long stockOutId,Long stockOutType){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockOutDetVo(so.id,sod.id,so.stockOutCode,so.stockOutDate,sod.partinfoId,pi.partCode,pi.partName,pi.tmUnit.unitName");
		hql.append(" ,sod.quantity,sod.price,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,sod.quantity*sod.price,pi.costPrice,sod.stockInDetailId,sod.isFree,pi.storeLocation,sod.zl,sod.xmlx,sod.projectType)");
		hql.append(" from TmStockOut so , TmStockoutDetail sod , TbPartInfo pi");
		hql.append(" where so.id = sod.stockoutId and sod.partinfoId = pi.id and so.isConfirm not in (8000) ");
		if(stockOutType != null)
			hql.append(" and so.outType = "+stockOutType);
		if(stockOutId != null)
			hql.append(" and sod.stockoutId = "+stockOutId);
		if(tmStockOut != null){
			if(StringUtils.isNotBlank(tmStockOut.getPartCode())){
				hql.append(" and pi.partCode like '%").append(tmStockOut.getPartCode()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockOut.getPartName())){
				hql.append(" and pi.partName like '%").append(tmStockOut.getPartName()).append("%'");
			}
		}
		hql.append(" order by sod.id ");
		List<TmStockOutDetVo> result = this.getHibernateTemplate().find(hql.toString());
		return result;
	}
}
