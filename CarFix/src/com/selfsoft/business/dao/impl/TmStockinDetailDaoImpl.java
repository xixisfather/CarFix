package com.selfsoft.business.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tmStockinDetailDao")
public class TmStockinDetailDaoImpl  extends BaseDaoImpl<TmStockinDetail> implements ITmStockinDetailDao {
	
	
	public List<TmStockInDetailVo> getStockInDetVo(Long stockInType,Long tmStockInId,Long isConfirm){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockInDetailVo( ");
		hql.append("si.id,sid.id,si.stockInCode,sid.partinfoId,sid.quantity,sid.price,pi.partCode,");
		hql.append("pi.partName,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,");
		hql.append("pi.tmUnit.unitName,pi.costPrice,sid.stockoutDetailId,pi.storeLocation,pi.tmCarModelType.modelName,sid.productArea) ");
		hql.append("from TmStockIn si , TmStockinDetail sid , TbPartInfo pi ");
		hql.append(" where si.id = sid.stockId and sid.partinfoId = pi.id ");
		if(stockInType != null)
			hql.append(" and si.inType = "+stockInType);
		if(tmStockInId != null)
			hql.append(" and si.id ="+tmStockInId);
		if(isConfirm != null)
			hql.append(" and si.isConfirm = "+isConfirm);
		hql.append(" order by sid.id");
		List<TmStockInDetailVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	public List<TmStockInVo> getStockInVo(Long stockInType,Long isConfirm , Long stockInId , TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		//if(stockInType.equals(StockTypeElements.OVERFLOW.getElementValue())){
		if(StockTypeElements.OVERFLOW.getElementValue().equals(stockInType)){
			hql.append("select new com.selfsoft.business.vo.TmStockInVo( ");
			hql.append(" ti.id,ti.stockInCode,ti.totalQuantity,ti.totalPrice,ti.indent,ti.oucherCode,ti.arriveDate,ti.inType,0L,'',u.userRealName,'')");
			hql.append(" from TmStockIn ti ,  TmUser u ");
			hql.append(" where ti.userId = u.id ");
		}else{
			hql.append("select new com.selfsoft.business.vo.TmStockInVo( ");
			hql.append(" ti.id,ti.stockInCode,ti.totalQuantity,ti.totalPrice,ti.indent,ti.oucherCode,ti.arriveDate,ti.inType,c.id,c.customerName,u.userRealName,c.customerCode)");
			hql.append(" from TmStockIn ti , TbCustomer c , TmUser u ");
			hql.append(" where ti.supplierId = c.id and ti.userId = u.id ");
		}
		if(stockInType != null)
			hql.append(" and ti.inType = "+stockInType);
		if(isConfirm != null)
			hql.append(" and ti.isConfirm = "+isConfirm);
		if(stockInId != null)
			hql.append(" and ti.id = "+stockInId);
		
		if(null != tmStockIn){
			
			if(StringUtils.isNotBlank(tmStockIn.getStockInCode())){
				hql.append(" and ti.stockInCode like '%"+tmStockIn.getStockInCode()+"%'");
			}
			
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and ti.arriveDate >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and ti.arriveDate <= '").append(tmStockIn.getEndDate()).append("'");
			}
			
			if(null != tmStockIn.getSupplierId()){
				hql.append(" and ti.supplierId = "+tmStockIn.getSupplierId());
			}
			
			if(StringUtils.isNotBlank(tmStockIn.getOucherCode())){
				hql.append(" and ti.oucherCode like '%"+tmStockIn.getOucherCode()+"%'");
			}
			
		}
		List<TmStockInVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	
	public List<TmStockInDetailVo> getStockInDetailVoByStat(Long stockInType,Long tmStockInId,String isConfirms,TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockInDetailVo( ");
		if(stockInType.equals(StockTypeElements.OVERFLOW.getElementValue())){
			hql.append("si.id,sid.id,si.stockInCode,sid.partinfoId,sid.quantity,sid.price,pi.partCode,pi.partName,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,pi.tmUnit.unitName,pi.costPrice,sid.stockoutDetailId,");
			hql.append(" 0L,'','',si.arriveDate,si.indent)");
			hql.append("from TmStockIn si , TmStockinDetail sid , TbPartInfo pi ");
			hql.append(" where si.id = sid.stockId and sid.partinfoId = pi.id ");
		}else{
			
			hql.append("si.id,sid.id,si.stockInCode,sid.partinfoId,sid.quantity,sid.price,pi.partCode,pi.partName,pi.tmStoreHouse.id,pi.tmStoreHouse.houseCode,pi.tmStoreHouse.houseName,pi.tmUnit.unitName,pi.costPrice,sid.stockoutDetailId,");
			hql.append(" si.supplierId,'','',si.arriveDate,si.indent)");
			hql.append("from TmStockIn si , TmStockinDetail sid , TbPartInfo pi  ");
			hql.append(" where si.id = sid.stockId and sid.partinfoId = pi.id ");
		}
		if(stockInType != null)
			hql.append(" and si.inType = "+stockInType);
		if(tmStockInId != null)
			hql.append(" and si.id ="+tmStockInId);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and si.isConfirm in ("+ isConfirms + ")");
		
		if(tmStockIn != null){
			
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and pi.partCode like '%").append(tmStockIn.getPartCode()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getPartName())){
				hql.append(" and pi.partName like '%").append(tmStockIn.getPartName()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and si.arriveDate >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and si.arriveDate <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId() != null){
				hql.append(" and si.supplierId = ").append(tmStockIn.getSupplierId());
			}
			if(StringUtils.isNotBlank(tmStockIn.getStockInCode())){
				hql.append(" and si.stockInCode like '%").append(tmStockIn.getStockInCode()).append("%'");
			}
		}
		hql.append(" order by si.arriveDate desc , sid.id ");
		List<TmStockInDetailVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	public List<Object[]> getStockInCustomerTotalPriceByStat(Long stockInType,String isConfirms,TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		hql.append("select t.id,t.customer_Code,t.customer_Name,tp from Tb_Customer  t , ");
		
		hql.append("( select sum(sid.quantity*sid.price) tp ,si.supplier_Id  sid ");
		hql.append(" from Tm_Stock_In si,Tb_Customer c , tm_stockin_detail sid , tb_part_info pi ");
		hql.append(" where si.id = sid.stock_id and pi.id = sid.partinfo_id and c.id = si.supplier_Id");
		if(tmStockIn != null){
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and si.arrive_Date >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and si.arrive_Date <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId() != null){
				hql.append(" and si.supplier_Id = ").append(tmStockIn.getSupplierId());
			}
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and si.id in ");
	            hql.append("( select tsi.id from tm_stockin_detail tsd , tm_stock_in tsi,tb_part_info tpi where tsi.id=tsd.stock_id and tpi.id = tsd.partinfo_id ");
				hql.append(" and tpi.part_Code like '%").append(tmStockIn.getPartCode()).append("%')");
			}
			if(StringUtils.isNotBlank(tmStockIn.getPartName())){
				hql.append(" and si.id in ");
	            hql.append("( select tsi.id from tm_stockin_detail tsd , tm_stock_in tsi,tb_part_info tpi where tsi.id=tsd.stock_id and tpi.id = tsd.partinfo_id ");
				hql.append(" and tpi.part_name like '%").append(tmStockIn.getPartName()).append("%')");
			}
		}
		
		if(stockInType != null)
			hql.append(" and si.in_Type = "+stockInType);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and si.is_Confirm in ("+ isConfirms + ")");
		hql.append(" group by si.supplier_Id ) g");
		hql.append(" where g.sid = t.id ");
		
		
		List<Object[]> result = findByOriginSql(hql.toString(), null);
		return result;
	}
	
	public Double getStockInTotalPriceByStat(Long stockInType,Long tmStockInId,String isConfirms,TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		hql.append(" select sum(sid.quantity * sid.price) ");
		hql.append("from TmStockIn si , TmStockinDetail sid , TbPartInfo pi ,TbCustomer c ");
		hql.append(" where si.id = sid.stockId and sid.partinfoId = pi.id and si.supplierId = c.id");
		if(stockInType != null)
			hql.append(" and si.inType = "+stockInType);
		if(tmStockInId != null)
			hql.append(" and si.id ="+tmStockInId);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and si.isConfirm in ("+ isConfirms + ")");
		
		if(tmStockIn != null){
			
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and pi.partCode like '%").append(tmStockIn.getPartCode()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getPartName())){
				hql.append(" and pi.partName like '%").append(tmStockIn.getPartName()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and si.arriveDate >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and si.arriveDate <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId() != null){
				hql.append(" and si.supplierId = ").append(tmStockIn.getSupplierId());
			}
				
		}
		List result = this.getHibernateTemplate().find(hql.toString());
		if(result.get(0) == null)
			return 0.0D;
		return Double.valueOf(result.get(0).toString());
	}
	
	
	public List<TmStockInVo> getStockInVoStat(Long stockInType,Long isConfirm,TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		
		if(stockInType.equals(StockTypeElements.OVERFLOW.getElementValue())){
			hql.append("select new com.selfsoft.business.vo.TmStockInVo( ");
			hql.append(" ti.id,ti.stockInCode,ti.totalQuantity,ti.totalPrice,ti.indent,ti.oucherCode,ti.arriveDate,ti.inType,0L,'',u.userRealName,'')");
			hql.append(" from TmStockIn ti ,  TmUser u ");
			hql.append(" where ti.userId = u.id ");
		}else{
			hql.append("select new com.selfsoft.business.vo.TmStockInVo( ");
			hql.append(" ti.id,ti.stockInCode,ti.totalQuantity,ti.totalPrice,ti.indent,ti.oucherCode,ti.arriveDate,ti.inType,c.id,c.customerName,");
			hql.append(" u.id,u.userRealName,c.customerCode)");
			hql.append(" from TmStockIn ti , TbCustomer c ,TmUser u ");
			hql.append(" where ti.supplierId = c.id and u.id = ti.userId ");
		}
		if(stockInType != null)
			hql.append(" and ti.inType = "+stockInType);
		if(isConfirm != null)
			hql.append(" and ti.isConfirm = "+isConfirm);
		if(null != tmStockIn){
			if(tmStockIn.getSupplierId()!= null){
				hql.append(" and ti.supplierId = ").append(tmStockIn.getSupplierId());
			}
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and ti.arriveDate >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and ti.arriveDate <= '").append(tmStockIn.getEndDate()).append("'");
			}
			
		}
		
		hql.append(" order by ti.id");
		List<TmStockInVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
	
	/**
	 * 主单据查询列表
	 */
	public List<Object[]> getMasterStockIn(Long stockInType,String isConfirms,TmStockIn tmStockIn){
		StringBuilder hql = new StringBuilder();
		/**
		 * edit by chenchunrong 
		 * add a word 'distinct'
		 */
		hql.append(" select distinct si.stock_in_code, si.arrive_date,si.total_price,si.payment,c.customer_code,c.customer_name,si.id ");
		hql.append(" from Tm_Stock_In si , Tb_Customer c ,tm_stockin_detail sid , tb_part_info pi");
		hql.append(" where  si.id = sid.stock_id and pi.id = sid.partinfo_id and si.supplier_Id = c.id");
		if(stockInType != null)
			hql.append(" and si.in_Type = "+stockInType);
		if(StringUtils.isNotBlank(isConfirms))
			hql.append(" and si.is_Confirm in ("+ isConfirms + ")");
		
		if(tmStockIn != null){
			
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and si.id in ");
	            hql.append("( select tsi.id from tm_stockin_detail tsd , tm_stock_in tsi,tb_part_info tpi where tsi.id=tsd.stock_id and tpi.id = tsd.partinfo_id ");
				hql.append(" and tpi.part_Code like '%").append(tmStockIn.getPartCode()).append("%')");
			}
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and si.arrive_Date >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and si.arrive_Date <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId() != null){
				hql.append(" and si.supplier_Id = ").append(tmStockIn.getSupplierId());
			}
				
		}
		hql.append(" order by si.arrive_date desc");
		List<Object[]> result = findByOriginSql(hql.toString(), null);
		
		return result;
	}
	
	
	public List<Object[]> getMasterAllStock(TmStockIn tmStockIn){
		
		StringBuilder hql = new StringBuilder();
		hql.append(" select distinct si.stock_in_code, si.arrive_date,si.total_price,si.payment,c.customer_code,c.customer_name,si.id ");
		hql.append(" from Tm_Stock_In si , Tb_Customer c ,tm_stockin_detail sid , tb_part_info pi");
		hql.append(" where  si.id = sid.stock_id and pi.id = sid.partinfo_id and si.supplier_Id = c.id");
		hql.append(" and si.in_Type = "+StockTypeElements.STOCK.getElementValue());
		hql.append(" and si.is_Confirm = 8002");
		
		if(tmStockIn != null){
			
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and si.id in ");
	            hql.append("( select tsi.id from tm_stockin_detail tsd , tm_stock_in tsi,tb_part_info tpi where tsi.id=tsd.stock_id and tpi.id = tsd.partinfo_id ");
				hql.append(" and tpi.part_Code like '%").append(tmStockIn.getPartCode()).append("%')");
			}
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and si.arrive_Date >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and si.arrive_Date <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId() != null){
				hql.append(" and si.supplier_Id = ").append(tmStockIn.getSupplierId());
			}
				
		}
		hql.append(" union all");
		hql.append(" select distinct so.stock_out_code,so.stock_out_date,so.total_price,null,c.customer_code,c.customer_name,so.id");
		hql.append(" from Tm_Stock_Out so , Tm_Stock_In si, Tm_Stockout_Detail sod, Tb_Part_Info pi , Tb_Customer c ");
		hql.append(" where so.stockIn_Id = si.id and  so.id = sod.stockout_Id and sod.partinfo_Id = pi.id and c.id = si.supplier_Id"); 
		hql.append(" and so.out_Type = 15 and so.is_Confirm = 8002 ");
		if(null != tmStockIn){
			if(StringUtils.isNotBlank(tmStockIn.getPartCode())){
				hql.append(" and pi.part_Code like '%").append(tmStockIn.getPartCode()).append("%'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getPartName())){
				hql.append(" and pi.part_Name like '%").append(tmStockIn.getPartName()).append("%'");
			}
			
			if(StringUtils.isNotBlank(tmStockIn.getBeginDate())){
				hql.append(" and so.stock_Out_Date >= '").append(tmStockIn.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStockIn.getEndDate())){
				hql.append(" and so.stock_Out_Date <= '").append(tmStockIn.getEndDate()).append("'");
			}
			if(tmStockIn.getSupplierId()!=null){
				hql.append(" and si.supplier_Id = '").append(tmStockIn.getSupplierId()).append("'");
			}
		}
		
		List<Object[]> result = findByOriginSql(hql.toString(), null);
		
		return result;
	}
	
	public List<TmStockInDetailVo> getStockInDetVoNew(Long stockInType,Long tmStockInId,Long isConfirm){
		StringBuilder hql = new StringBuilder();
		hql.append("select new com.selfsoft.business.vo.TmStockInDetailVo( ");
		hql.append("si.id,sid.id,si.stockInCode,sid.partinfoId,sid.quantity,sid.price,");
		hql.append("sid.stockoutDetailId,sid.productArea,sid.hasQuantity) ");
		hql.append("from TmStockIn si , TmStockinDetail sid  ");
		hql.append(" where si.id = sid.stockId");
		if(stockInType != null)
			hql.append(" and si.inType = "+stockInType);
		if(tmStockInId != null)
			hql.append(" and si.id ="+tmStockInId);
		if(isConfirm != null)
			hql.append(" and si.isConfirm = "+isConfirm);
		hql.append(" order by sid.id");
		List<TmStockInDetailVo> result = this.getHibernateTemplate().find(hql.toString());
		
		return result;
	}
}
