package com.selfsoft.baseinformation.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.vo.TbPartInfoVo;
import com.selfsoft.business.vo.BalanceFixSellVo;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbPartInfoReFlowStatVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TmDrawStatVo;
import com.selfsoft.business.vo.TmStoreHouseReceiverStatVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.framework.dao.BaseDaoImpl;
@Repository("tbPartInfoDao")
public class TbPartInfoDaoImpl extends BaseDaoImpl<TbPartInfo> implements ITbPartInfoDao {


	public List<TbPartInfo> getTbPartInfoByCollectionCode(String collectionCode){
		
		String hql = "from TbPartInfo t where t.id in ( select tc.partInfoId from TbPartCollection tc where tc.collectionCode = '"+collectionCode+"' )";
		
		return this.getHibernateTemplate().find(hql);
	}
	
	
	public TbPartInfo getPartInfoByCodeAndStore(String partCode,Long storeHoseId){
		String hql = "from TbPartInfo t where t.partCode = '"+partCode+"' and t.tmStoreHouse.id = "+storeHoseId;
		List<TbPartInfo> result = this.getHibernateTemplate().find(hql);
		
		if(result != null && result.size()>0)
			return result.get(0);
		
		return null;
	}
	
	
	private String buildReceiverCondition(TbPartReceiverStatVo tbPartReceiverStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append(" from  tb_part_info p, tm_store_house h ,(");
		sql.append(" select aa.pid pid , sum(aa.sumq) sumq from (");
		sql.append(" select sum(o.quantity) sumq ,o.partinfo_id pid from  TM_STOCKOUT_DETAIL O where"); 
		sql.append(" exists (select * from TM_STOCK_OUT zo where zo.id = o.stockout_id and zo.is_confirm not in (8000)");
		if(tbPartReceiverStatVo != null){
			if(StringUtils.isNotBlank(tbPartReceiverStatVo.getBeginDate())){
				sql.append(" and zo.STOCK_OUT_DATE >= '").append(tbPartReceiverStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartReceiverStatVo.getEndDate())){
				sql.append(" and zo.STOCK_OUT_DATE <= '").append(tbPartReceiverStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" )");
		sql.append(" GROUP BY o.partinfo_id  union all select  sum(bc.part_quantity) summ,bc.part_id from TB_MAINTAIN_PART_CONTENT bc where bc.is_confirm not in (8000)  ");
		if(tbPartReceiverStatVo != null){
			if(StringUtils.isNotBlank(tbPartReceiverStatVo.getBeginDate())){
				sql.append(" and bc.STOCK_OUT_DATE >= '").append(tbPartReceiverStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartReceiverStatVo.getEndDate())){
				sql.append(" and bc.STOCK_OUT_DATE <= '").append(tbPartReceiverStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" group by bc.part_id) aa");
		sql.append(" group by aa.pid");
		sql.append(" ) r full OUTER JOIN");
		sql.append(" (select sum(o.quantity) sumqt ,o.partinfo_id pid ,avg(o.price) avp from TM_STOCKIN_DETAIL O GROUP BY o.partinfo_id ) t ON r.pid = t.pid"); 
		sql.append(" where p.id = r.pid and h.id = p.store_house_id ");
		
		if(tbPartReceiverStatVo != null){
			if(tbPartReceiverStatVo.getStoreHouseId() != null){
				sql.append(" and p.store_house_id = ").append(tbPartReceiverStatVo.getStoreHouseId());
			}
			if(tbPartReceiverStatVo.getCarModelTypeId() != null){
				sql.append(" and p.car_model_type_id = ").append(tbPartReceiverStatVo.getCarModelTypeId());
			}
		}
		
		return sql.toString();
		
	}
	
	
	/**
	 * 配件期间收发存统计 所有列表
	 */
	public List getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select h.house_name,p.part_code ,p.store_quantity , p.store_quantity*p.cost_price store_price ,p.part_name, t.sumqt sumIn ,r.sumq sumOut ,t.sumqt* t.avp in_avp_price, r.sumq*p.cost_price out_price,p.id");
		String conditionSql = buildReceiverCondition(tbPartReceiverStatVo);
		sql.append(conditionSql);
		
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	/**
	 * 配件期间收发存统计 数量合计
	 */
	public List getPartReceiveQuantityStat(TbPartReceiverStatVo tbPartReceiverStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(t.sumqt) totalIn ,sum(r.sumq) totalOut ");
		String conditionSql = buildReceiverCondition(tbPartReceiverStatVo);
		sql.append(conditionSql);
		
		List result  = this.findByOriginSql(sql.toString(), null);
		
		return result;
	}
	
	/**
	 * 配件期间收发存统计 金额合计
	 */
	public List getPartReceivePriceStat(TbPartReceiverStatVo tbPartReceiverStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select  sum(t.sumqt* t.avp) in_price , sum(r.sumq*p.cost_price) out_price ");
		String conditionSql = buildReceiverCondition(tbPartReceiverStatVo);
		sql.append(conditionSql);
		
		List result  = this.findByOriginSql(sql.toString(), null);
		
		return result;
	}
	
	/**
	 * 仓库概貌统计  -- 仓库分组
	 * @Date      2010-7-8
	 * @Function  
	 * @return
	 */
	public List getStoreHouseSurveyStat(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select h.house_name , aa.category , aa.cost_price , aa.sell_price,aa.liance_price, aa.loan_price , bb.b_count  from"); 
		sql.append(" (select pi.store_house_id,count(pi.id) category , sum(pi.cost_price*pi.store_quantity ) cost_price,");
		sql.append(" sum(pi.sell_price*pi.store_quantity ) sell_price ,sum(pi.cost_price*pi.liance_quantity) liance_price, sum(pi.cost_price*pi.loan_quantity) loan_price");
		sql.append(" from tb_part_info pi group by pi.store_house_id ) aa  full OUTER join");
		sql.append(" (select count(*) b_count , pi.store_house_id b_storeId ");
		sql.append(" from tb_part_info pi where pi.store_quantity=0  group by pi.store_house_id  ) bb  on  aa.store_house_id = bb.b_storeId , tm_store_house h  where h.id = aa.store_house_id");
		
		
		List result  = this.findByOriginSql(sql.toString(), null);
		
		return result;
	}

	/**
	 * 仓库概貌统计  -- 车型分组
	 * @Date      2010-7-8
	 * @Function  
	 * @return
	 */
	public List getCarModelTypeSurveyStat(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select c.model_name , aa.category , aa.cost_price  , aa.sell_price,aa.liance_price, aa.loan_price , bb.b_count  from"); 
		sql.append(" (select pi.car_model_type_id,count(pi.id) category , sum(pi.cost_price*pi.store_quantity ) cost_price,");
		sql.append(" sum(pi.sell_price*pi.store_quantity ) sell_price ,sum(pi.cost_price*pi.liance_quantity) liance_price, sum(pi.cost_price*pi.loan_quantity) loan_price"); 
		sql.append(" from tb_part_info pi where pi.car_model_type_id is not null group by pi.car_model_type_id ) aa  full OUTER join");
		sql.append(" (select count(*) b_count , pi.car_model_type_id b_carId");
		sql.append(" from tb_part_info pi where pi.store_quantity=0 and pi.car_model_type_id is not null  group by pi.car_model_type_id  ) bb");  
		sql.append(" on  aa.car_model_type_id = bb.b_carId , TM_CAR_MODEL_TYPE c  where c.id = aa.car_model_type_id");
		
		List result  = this.findByOriginSql(sql.toString(), null);
		
		return result;
	}
	
	/**
	 * 配件流量及流向统计 -- 所有类型配件流量统计
	 * @Date      2010-7-9
	 * @Function  
	 * @param partInfoId
	 * @return
	 */
	public List getAllTypeSumReFlowStat(String partInfoId){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select si.in_type tp , sum(sid.quantity) in_sum from tm_stock_in si , tm_stockin_detail sid where si.id = sid.stock_id and si.is_confirm not in (8000) ");
		if(StringUtils.isNotBlank(partInfoId))
			sql.append(" and sid.partinfo_id in ("+ partInfoId+")" );
		sql.append(" group by si.in_type");
		sql.append(" UNION ");
		sql.append("  select so.out_type tp , sum(sod.quantity) out_sum  from tm_stock_out so , tm_stockout_detail sod where so.id = sod.stockout_id and so.is_confirm not in (8000) ");
		if(StringUtils.isNotBlank(partInfoId))
			sql.append(" and sod.partinfo_id in ("+ partInfoId +")");
		sql.append(" group by so.out_type");
		sql.append(" UNION");
		sql.append(" select 20 tp ,sum(m.part_quantity) ma_sum  from TB_MAINTAIN_PART_CONTENT m where m.is_confirm not in (8000) ");
		if(StringUtils.isNotBlank(partInfoId))
			sql.append(" and m.part_id in ("+ partInfoId+")");
		
		List result  = this.findByOriginSql(sql.toString(), null);

		return result;
	}
	
	
	/**
	 * 配件流量及流向统计 -- 所有配件流量明细
	 */
	public List getPartInfoReFlowDetailStat(String partInfoIds , Long elementType ,TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo ){
		StringBuilder sql = new StringBuilder();
		if(elementType == null ||elementType.equals(20L) ){
			sql.append("select m.stock_out_date , 20 tp ,m.part_quantity , m.price ,part_quantity*m.price , c.customer_Name , null , ci.id,ci.license_Code,ci.chassis_Code");
			sql.append(" from TB_MAINTAIN_PART_CONTENT m  ,TB_FIX_ENTRUST fe , tb_customer c , TB_CAR_INFO ci");
			sql.append(" where fe.CUSTOMER_ID = c.id and fe.id = m.entrust_id and m.is_confirm not in (8000) and fe.CAR_INFO_ID = ci.id");
			if(StringUtils.isNotBlank(partInfoIds))
				sql.append(" and m.part_id in (").append(partInfoIds+")");
			if(tbPartInfoReFlowStatVo != null){
				if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getBeginDate())){
					sql.append(" and m.stock_out_date >= '").append(tbPartInfoReFlowStatVo.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getEndDate())){
					sql.append(" and m.stock_out_date <= '").append(tbPartInfoReFlowStatVo.getEndDate()).append("'");
				}
				if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getLicenseCode())){
					sql.append(" and ci.license_Code like '%").append(tbPartInfoReFlowStatVo.getLicenseCode()).append("%'");
				}
			}
			sql.append(" union all");
		}
		sql.append(" select si.arrive_date,si.in_type tp,sid.quantity,sid.price,sid.quantity*sid.price , c.customer_Name , u.user_real_name,null,null,null");	 
		sql.append(" from  tm_stockin_detail sid ,tm_user u , tm_stock_in si left join tb_customer c on c.id = si.supplier_id ");
		sql.append(" where si.id = sid.stock_id and u.id = si.user_id and si.is_confirm not in (8000) ");
		if(StringUtils.isNotBlank(partInfoIds))
			sql.append(" and sid.partinfo_id in (").append(partInfoIds+")");
		if(elementType!=null )
			sql.append(" and si.in_type = ").append(elementType);
		if(tbPartInfoReFlowStatVo != null){
			if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getBeginDate())){
				sql.append(" and si.arrive_date >= '").append(tbPartInfoReFlowStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getEndDate())){
				sql.append(" and si.arrive_date <= '").append(tbPartInfoReFlowStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" union all");
		sql.append(" select so.stock_out_date , so.out_type , sod.quantity ,sod.price ,sod.quantity*sod.price , c.customer_Name , u.user_real_name,null,null,null");	 
		sql.append(" from tm_stockout_detail sod , tm_user u , tm_stock_out so left join  tb_customer c on c.id = so.customer_bill ");
		sql.append(" where so.id = sod.stockout_id and  u.id = so.user_id and so.is_confirm not in (8000) "); 
		if(StringUtils.isNotBlank(partInfoIds))
			sql.append(" and sod.partinfo_id in (").append(partInfoIds+")");
		if(elementType!=null )
			sql.append(" and so.out_type = ").append(elementType);
		if(tbPartInfoReFlowStatVo != null){
			if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getBeginDate())){
				sql.append(" and so.stock_out_date >= '").append(tbPartInfoReFlowStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartInfoReFlowStatVo.getEndDate())){
				sql.append(" and so.stock_out_date <= '").append(tbPartInfoReFlowStatVo.getEndDate()).append("'");
			}
		}
		List result  = this.findByOriginSql(sql.toString(), null);

		return result;
	}
	
	
	
	private String buildStockOutPartInfoCondition(TbPartInfoStockOutVo tbPartInfoStockOutVo){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select tpi.part_name , cmt.model_name , tpi.store_Location, tpi.cost_price cp , tpi.store_quantity, aa.sell_quantity , bb.sell_price , aa.cost_price ,  bb.sell_price - aa.cost_price lr"); 
		sql.append(" from tb_part_info tpi left join TM_CAR_MODEL_TYPE cmt on  cmt.id = tpi.car_model_type_id,");
		sql.append(" (select cc.partinfo_id ,sum(sell_quantity) sell_quantity ,sum(cost_price) cost_price from"); 
		sql.append(" (select sod.partinfo_id  partinfo_id , sum(sod.quantity) sell_quantity ,sum( p.cost_price * sod.quantity) cost_price");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tb_part_info p");
		sql.append(" where so.id = sod.stockout_id and p.id = sod.partinfo_id and so.is_confirm not in (8000) and so.out_type != 15 "); 
		if(tbPartInfoStockOutVo != null){
			if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(tbPartInfoStockOutVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(tbPartInfoStockOutVo.getEndDate()).append("'");
			}
			if(tbPartInfoStockOutVo.getStockOutType() != null){
				sql.append(" and so.out_type = ").append(tbPartInfoStockOutVo.getStockOutType());
			}
		}
		sql.append(" group by sod.partinfo_id");
		
		if(tbPartInfoStockOutVo == null || tbPartInfoStockOutVo.getStockOutType() == null ||
					tbPartInfoStockOutVo.getStockOutType().equals(StockTypeElements.MAINTAIN.getElementValue())){
			sql.append(" union all");
			sql.append(" select m.part_id partinfo_id ,sum(m.part_quantity) sell_quantity , sum( p.cost_price * m.part_quantity) cost_price");
			sql.append(" from tb_maintain_part_content m ,  tb_part_info p");
			sql.append(" where m.part_id = p.id and m.is_confirm not in (8000) ");
			if(tbPartInfoStockOutVo != null){
				if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getBeginDate())){
					sql.append(" and m.STOCK_OUT_DATE >= '").append(tbPartInfoStockOutVo.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getEndDate())){
					sql.append(" and m.STOCK_OUT_DATE <= '").append(tbPartInfoStockOutVo.getEndDate()).append("'");
				}
			}
			sql.append(" group by part_id");
		}
		
		sql.append(" ) cc");
		sql.append(" group by cc.partinfo_id) aa left join");
		

		sql.append(" (select dd.partinfo_id ,sum(sell_price) sell_price from");
		sql.append(" (select sod.partinfo_id partinfo_id ,avg(sod.price) * sum(sod.quantity) sell_price");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tb_part_info p ");
		sql.append(" where so.id = sod.stockout_id and p.id = sod.partinfo_id and sod.is_free = 1 and so.is_confirm not in (8000) and so.out_type != 15 ");
		if(tbPartInfoStockOutVo != null){
			if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(tbPartInfoStockOutVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(tbPartInfoStockOutVo.getEndDate()).append("'");
			}
			if(tbPartInfoStockOutVo.getStockOutType() != null){
				sql.append(" and so.out_type = ").append(tbPartInfoStockOutVo.getStockOutType());
			}
		}
		sql.append(" group by sod.partinfo_id");
		
		if(tbPartInfoStockOutVo == null ||  tbPartInfoStockOutVo.getStockOutType() == null ||
					tbPartInfoStockOutVo.getStockOutType().equals(StockTypeElements.MAINTAIN.getElementValue())){

			sql.append(" union all");
			sql.append(" select m.part_id partinfo_id ,avg(m.price) * sum(m.part_quantity) sell_price");
			sql.append(" from tb_maintain_part_content m ,  tb_part_info p");
			sql.append(" where m.part_id = p.id and m.is_free = 1 and m.is_confirm not in (8000)");
			if(tbPartInfoStockOutVo != null){
				if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getBeginDate())){
					sql.append(" and m.STOCK_OUT_DATE >= '").append(tbPartInfoStockOutVo.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(tbPartInfoStockOutVo.getEndDate())){
					sql.append(" and m.STOCK_OUT_DATE <= '").append(tbPartInfoStockOutVo.getEndDate()).append("'");
				}
			}
			sql.append(" group by part_id ");
		}
		sql.append(") dd group by dd.partinfo_id ");
		sql.append(" ) bb");
		sql.append(" on aa.partinfo_id = bb.partinfo_id");
		sql.append(" where tpi.id = aa.partinfo_id ");
		if(tbPartInfoStockOutVo != null){
			if(tbPartInfoStockOutVo.getStoreHouseId() != null){
				sql.append(" and tpi.store_house_id = ").append(tbPartInfoStockOutVo.getStoreHouseId());
			}
			if(tbPartInfoStockOutVo.getCarModelTypeId() != null){
				sql.append(" and tpi.car_model_type_id = ").append(tbPartInfoStockOutVo.getCarModelTypeId());
			}
			
			if(tbPartInfoStockOutVo.getOrderByType()!= null){
				if(tbPartInfoStockOutVo.getOrderByType().equals(1L)){
					//销售量排序
					sql.append(" order by aa.sell_quantity desc");
				}
				if(tbPartInfoStockOutVo.getOrderByType().equals(2L)){
					//利润排序
					sql.append(" order by lr desc,aa.sell_quantity desc");
				}
				if(tbPartInfoStockOutVo.getOrderByType().equals(3L)){
					//销售金额排序
					sql.append(" order by bb.sell_price desc , aa.sell_quantity desc");
				}
				if(tbPartInfoStockOutVo.getOrderByType().equals(4L)){
					//成本金额排序
					sql.append(" order by aa.cost_price desc , aa.sell_quantity desc");
				}
				
			}
		}else {
			//默认销售量排序
			sql.append(" order by sell_quantity desc");
		}
		return sql.toString();
	}
	
	
	/**
	 * 配件出库排行榜
	 * @Date      2010-7-12
	 * @Function  
	 * @param tbPartInfoStockOutVo
	 * @return
	 */
	public List getToppartInfoStockOut(TbPartInfoStockOutVo tbPartInfoStockOutVo){
		String sql = this.buildStockOutPartInfoCondition(tbPartInfoStockOutVo);
		List result  = this.findByOriginSql(sql, null);
		return result;
	}
	
	
	/**
	 * 仓库期间收发统计
	 * @Date      2010-7-13
	 * @Function  
	 * @param tmStoreHouseReceiverStatVo
	 * @return
	 */
	public List getStoreHouseReceiverStat(TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select sh.house_name , aa.*   from"); 
		sql.append(" (select sh.id house_id ,so.out_type , 0 type , sum(sod.quantity * pi.cost_price) cost_stockout , sum(sod.quantity * sod.price) sell_stockout");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tm_store_house sh , tb_part_info pi"); 
		sql.append(" where so.id = sod.stockout_id  and sh.id = pi.store_house_id and pi.id = sod.partinfo_id");
		sql.append(" and so.is_confirm not in (8000) and (sod.is_free = 1 or sod.is_free is null)");
		if(tmStoreHouseReceiverStatVo != null){
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(tmStoreHouseReceiverStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(tmStoreHouseReceiverStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" group by sh.id , so.out_type");
		sql.append(" union all");
		sql.append(" select sh.id , 20 out_type, 0 type , sum(m.part_quantity * pi.cost_price) cost_stockout , sum(m.part_quantity * m.price) sell_stockout");
		sql.append(" from tb_maintain_part_content m ,tm_store_house sh , tb_part_info pi");
		sql.append(" where sh.id = pi.store_house_id and pi.id = m.part_id");
		sql.append(" and m.is_confirm not in (8000) and (m.is_free = 1 or m.is_free is null)");
		if(tmStoreHouseReceiverStatVo != null){
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getBeginDate())){
				sql.append(" and m.STOCK_OUT_DATE >= '").append(tmStoreHouseReceiverStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getEndDate())){
				sql.append(" and m.STOCK_OUT_DATE <= '").append(tmStoreHouseReceiverStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" group by sh.id"); 
		sql.append(" union all");
		sql.append(" select sh.id ,si.in_type , 1 type , sum(pi.cost_price * sid.quantity) cost_stockin , null");
		sql.append(" from tm_stock_in si , tm_stockin_detail sid ,tm_store_house sh , tb_part_info pi"); 
		sql.append(" where si.id =sid.stock_id and sh.id = pi.store_house_id and pi.id = sid.partinfo_id");
		sql.append(" and si.is_confirm not in (8000)");
		if(tmStoreHouseReceiverStatVo != null){
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getBeginDate())){
				sql.append(" and si.arrive_date >= '").append(tmStoreHouseReceiverStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmStoreHouseReceiverStatVo.getEndDate())){
				sql.append(" and si.arrive_date <= '").append(tmStoreHouseReceiverStatVo.getEndDate()).append("'");
			}
		}
		sql.append(" group by sh.id ,si.in_type) aa , tm_store_house sh");
		sql.append(" where aa.house_id = sh.id");
		if(tmStoreHouseReceiverStatVo != null){
			if(tmStoreHouseReceiverStatVo.getStoreHouseId() != null){
				sql.append(" and sh.id = ").append(tmStoreHouseReceiverStatVo.getStoreHouseId());
			}
			
		}
		sql.append(" order by aa.house_id");
		

		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	/**
	 * 配件领用统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public List getDrawStockOutStat(TmDrawStatVo tmDrawStatVo){
		
		StringBuilder sql = new StringBuilder();
		sql.append("select so.stock_out_code , so.stock_out_date , sh.house_name , pi.part_code , pi.part_name , u.unit_name ,pi.cost_price , sod.price ,sod.quantity , us.user_real_name , d.dept_name");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod ,tm_store_house sh , tb_part_info pi , tm_unit u , tm_user us , tm_department d");
		sql.append(" where so.out_type = 12 and so.is_confirm not in (8000) and  so.id = sod.stockout_id and");
		sql.append(" sh.id = pi.store_house_id and pi.id = sod.partinfo_id and u.id = pi.unit_id and us.id = so.draw_people and d.id = us.dept_id");
		if(tmDrawStatVo != null){
			if(StringUtils.isNotBlank(tmDrawStatVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(tmDrawStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmDrawStatVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(tmDrawStatVo.getEndDate()).append("'");
			}
			if(tmDrawStatVo.getDrawPeopleId() != null && tmDrawStatVo.getQuerySelect() != null && tmDrawStatVo.getQuerySelect().equals(0L) ){
				sql.append(" and so.draw_people = ").append(tmDrawStatVo.getDrawPeopleId());
			}
			if(tmDrawStatVo.getDeptId() != null && tmDrawStatVo.getQuerySelect() != null && tmDrawStatVo.getQuerySelect().equals(1L)){
				sql.append(" and d.id = ").append(tmDrawStatVo.getDeptId());
			}
			
		}
		
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	/**
	 * 配件领用人分组统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public List getGroupDrawStockOutStat(TmDrawStatVo tmDrawStatVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select d.dept_name,us.user_real_name , aa.sell_price , aa.cost_price from  tm_user us , tm_department d ,");
		sql.append(" (select so.draw_people , sum(sod.price * sod.quantity) sell_price , sum(pi.cost_price* sod.quantity) cost_price");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi");
		sql.append(" where so.out_type = 12 and so.is_confirm not in (8000) and so.id = sod.stockout_id and pi.id = sod.partinfo_id");
		if(tmDrawStatVo != null){
			if(StringUtils.isNotBlank(tmDrawStatVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(tmDrawStatVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(tmDrawStatVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(tmDrawStatVo.getEndDate()).append("'");
			}
			if(tmDrawStatVo.getDrawPeopleId() != null && tmDrawStatVo.getQuerySelect() != null && tmDrawStatVo.getQuerySelect().equals(0L) ){
				sql.append(" and so.draw_people = ").append(tmDrawStatVo.getDrawPeopleId());
			}
		}
		sql.append(" group by so.draw_people) aa");
		sql.append(" where d.id = us.dept_id  and aa.draw_people = us.id");
		if(tmDrawStatVo != null){
			if(tmDrawStatVo.getDeptId() != null && tmDrawStatVo.getQuerySelect() != null && tmDrawStatVo.getQuerySelect().equals(1L)){
				sql.append(" and d.id = ").append(tmDrawStatVo.getDeptId());
			}
		}
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	public List getDailyStockOutStat(DailyStockOutVo dailyStockOutVo){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		
		sql.append(" select sh.house_name,pi.part_code , pi.part_name, so.stock_out_code code, so.out_type , sod.quantity , pi.cost_price , sod.price ,so.stock_out_date,sod.is_free,pi.id,so.user_Id,so.draw_People,null usname");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tb_part_info pi , tm_store_house sh");
		sql.append(" where so.id = sod.stockout_id and pi.id = sod.partinfo_id and sh.id = pi.store_house_id and so.is_confirm not in (8000)");
		if(dailyStockOutVo != null){
			if(StringUtils.isNotBlank(dailyStockOutVo.getBeginDate())){
				sql.append(" and so.STOCK_OUT_DATE >= '").append(dailyStockOutVo.getBeginDate()).append("'");
			}
			if(StringUtils.isNotBlank(dailyStockOutVo.getEndDate())){
				sql.append(" and so.STOCK_OUT_DATE <= '").append(dailyStockOutVo.getEndDate()).append("'");
			}
			if(dailyStockOutVo.getHouseId()!=null){
				sql.append(" and sh.id = ").append(dailyStockOutVo.getHouseId());
			}
			if(dailyStockOutVo.getOutType() != null){
				sql.append(" and so.out_type =").append(dailyStockOutVo.getOutType());
			}
			
			if(StringUtils.isNotBlank(dailyStockOutVo.getPartCode())){
				sql.append(" and pi.part_code like '%").append(dailyStockOutVo.getPartCode()).append("%'");
			}
		}
			
		if(dailyStockOutVo != null && dailyStockOutVo.getOutType() != null && dailyStockOutVo.getOutType().equals(StockTypeElements.MAINTAIN.getElementValue())){
			sql.append(" union all");
			sql.append(" select sh.house_name,pi.part_code , pi.part_name,m.maintain_code code, 20 out_type , m.part_quantity quantity ,pi.cost_price , m.price , m.stock_out_date ,m.is_free,pi.id,null,m.CLIAM_PART_PERSON_ID,feu.user_real_name");
			sql.append(" from tb_maintain_part_content m , tb_part_info pi , tm_store_house sh,TB_FIX_ENTRUST fe,tm_user feu");
			sql.append(" where  pi.id = m.part_id and sh.id = pi.store_house_id and m.is_confirm not in (8000)  and fe.id = m.entrust_id and feu.id = fe.user_id");
			if(dailyStockOutVo != null){
				if(StringUtils.isNotBlank(dailyStockOutVo.getBeginDate())){
					sql.append(" and m.STOCK_OUT_DATE >= '").append(dailyStockOutVo.getBeginDate()).append("'");
				}
				if(StringUtils.isNotBlank(dailyStockOutVo.getEndDate())){
					Date endDate = CommonMethod.parseStringToDate(dailyStockOutVo.getEndDate(), "yyyy-MM-dd");
					Date addEndDate =CommonMethod.addDate(endDate, 1);
					sql.append(" and m.STOCK_OUT_DATE <= '").append(CommonMethod.parseDateToString(addEndDate, "yyyy-MM-dd")).append("'");
				}
				if(dailyStockOutVo.getHouseId()!=null){
					sql.append(" and sh.id = ").append(dailyStockOutVo.getHouseId());
				}
				if(StringUtils.isNotBlank(dailyStockOutVo.getPartCode())){
					sql.append(" and pi.part_code like '%").append(dailyStockOutVo.getPartCode()).append("%'");
				}
			}
		}
		if(dailyStockOutVo == null || dailyStockOutVo.getOutType()==null){
			sql.append(" union all");
			sql.append(" select sh.house_name,pi.part_code , pi.part_name,m.maintain_code code, 20 out_type , m.part_quantity quantity ,pi.cost_price , m.price , m.stock_out_date,m.is_free,pi.id,null,m.CLIAM_PART_PERSON_ID,feu.user_real_name");
			sql.append(" from tb_maintain_part_content m , tb_part_info pi , tm_store_house sh,TB_FIX_ENTRUST fe,tm_user feu");
			sql.append(" where  pi.id = m.part_id and sh.id = pi.store_house_id and m.is_confirm not in (8000) and fe.id = m.entrust_id and feu.id = fe.user_id ");
			if(dailyStockOutVo != null && StringUtils.isNotBlank(dailyStockOutVo.getBeginDate())){
				sql.append(" and m.STOCK_OUT_DATE >= '").append(dailyStockOutVo.getBeginDate()).append("'");
			}
			if(dailyStockOutVo != null && StringUtils.isNotBlank(dailyStockOutVo.getEndDate())){
				Date endDate = CommonMethod.parseStringToDate(dailyStockOutVo.getEndDate(), "yyyy-MM-dd");
				Date addEndDate =CommonMethod.addDate(endDate, 1);
				sql.append(" and m.STOCK_OUT_DATE <= '").append(CommonMethod.parseDateToString(addEndDate, "yyyy-MM-dd")).append("'");
			}else{
				//sql.append(" and DateDiff(m,stock_out_date,getdate())=0");
			}
			if(dailyStockOutVo!= null && StringUtils.isNotBlank(dailyStockOutVo.getPartCode())){
				sql.append(" and pi.part_code like '%").append(dailyStockOutVo.getPartCode()).append("%'");
			}
			if(dailyStockOutVo!= null && dailyStockOutVo.getHouseId()!=null){
				sql.append(" and sh.id = ").append(dailyStockOutVo.getHouseId());
			}
		}
		
		sql.append(") aa");
		
		
		
		sql.append(" order by aa.STOCK_OUT_DATE desc,aa.out_type ");

		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List getNotBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo){
		StringBuilder sql = new StringBuilder();
		sql.append("Select entrust_id , max(entrust_code) entrust_code , max(fix_date) fix_date,  max(mcode) maintain_code , max(scode) stockout_code ,max(license_code) license_code,");
		sql.append("max(fix_type) fix_type , max(customer_name) customer_name , max(user_real_name) user_real_name ,");
		sql.append("      isnull(sum(xj),0) as '总成本',");
		sql.append("      isnull(sum(case when out_type = 20 then xj end),0) as '修理成本',");
		sql.append("      isnull(sum(case when out_type = 11 then xj end),0) as '销售成本',");
		sql.append("      isnull(sum(case when is_free = 1 then xj end),0) as '无',");
		sql.append("      isnull(sum(case when is_free = 2 then xj end),0) as '首保',");
		sql.append("      isnull(sum(case when is_free = 3 then xj end),0) as '索赔',");
		sql.append("      isnull(sum(case when is_free = 4 then xj end),0) as '返工',");
		sql.append("      isnull(sum(case when is_free = 5 then xj end),0) as '服务活动'");
		sql.append("      from (");
		sql.append(" select fe.id entrust_id,fe.entrust_code,fe.fix_date,m.maintain_code mcode,null scode,ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , m.is_free ,m.part_quantity * pi.cost_price xj,20 out_type");
		sql.append(" from tb_fix_entrust fe ,tb_maintain_part_content m ,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ");
		sql.append(" where fe.id = m.entrust_id and fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and m.part_id = pi.id and cmt.id = ci.model_type_id");
		sql.append(" and m.is_confirm = 8002 and m.balance_id is null ");
		if(balanceFixSellVo != null){
			if(StringUtils.isNotBlank(balanceFixSellVo.getBeginFixDate())){
				sql.append(" and fe.fix_date >= '").append(balanceFixSellVo.getBeginFixDate()).append("'");
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getEndFixDate())){
				sql.append(" and fe.fix_date <= '").append(balanceFixSellVo.getEndFixDate()).append("'");
			}
			if(balanceFixSellVo.getFixTypeId()!=null){
				sql.append(" and fe.fix_type_id = ").append(balanceFixSellVo.getFixTypeId());
			}
			if(balanceFixSellVo.getModelTypeId()!=null){
				sql.append(" and cmt.id = ").append(balanceFixSellVo.getModelTypeId());
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getLianceseCode())){
				sql.append(" and ci.license_code like '%").append(balanceFixSellVo.getLianceseCode()).append("%'");
			}
		}
		sql.append(" union all");
		sql.append(" select  fe.id entrust_id,fe.entrust_code,fe.fix_date ,null mcode, so.stock_out_code scode, ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , sod.is_free,sod.quantity*pi.cost_price xj,11 out_type");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod ,tb_fix_entrust fe,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ");
		sql.append(" where so.id = sod.stockout_id and  fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and sod.partinfo_id = pi.id and cmt.id = ci.model_type_id");
		sql.append(" and so.out_type = 11 and so.trust_bill != '' and so.trust_bill = fe.entrust_code");
		sql.append(" and so.is_confirm = 8002 and sod.balance_id is null ");
		if(balanceFixSellVo != null){
			if(StringUtils.isNotBlank(balanceFixSellVo.getBeginFixDate())){
				sql.append(" and fe.fix_date >= '").append(balanceFixSellVo.getBeginFixDate()).append("'");
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getEndFixDate())){
				sql.append(" and fe.fix_date <= '").append(balanceFixSellVo.getEndFixDate()).append("'");
			}
			if(balanceFixSellVo.getFixTypeId()!=null){
				sql.append(" and fe.fix_type_id = ").append(balanceFixSellVo.getFixTypeId());
			}
			if(balanceFixSellVo.getModelTypeId()!=null){
				sql.append(" and cmt.id = ").append(balanceFixSellVo.getModelTypeId());
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getLianceseCode())){
				sql.append(" and ci.license_code like '%").append(balanceFixSellVo.getLianceseCode()).append("%'");
			}
		}
		sql.append(") aa");
		sql.append(" group by aa.entrust_id");
		
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	
	/**
	 * 已结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	public List getIsBalanceFixSellCostDetail(BalanceFixSellVo balanceFixSellVo){
		StringBuilder sql = new StringBuilder();
		sql.append("Select entrust_id , max(entrust_code) entrust_code , max(fix_date) fix_date,  max(mcode) maintain_code , max(scode) stockout_code ,max(license_code) license_code,");
		sql.append("max(fix_type) fix_type , max(customer_name) customer_name , max(user_real_name) user_real_name ,");
		sql.append("      isnull(sum(xj),0) as '总成本',");
		sql.append("      isnull(sum(case when out_type = 20 then xj end),0) as '修理成本',");
		sql.append("      isnull(sum(case when out_type = 11 then xj end),0) as '销售成本',");
		sql.append("      isnull(sum(case when is_free = 1 then xj end),0) as '无',");
		sql.append("      isnull(sum(case when is_free = 2 then xj end),0) as '首保',");
		sql.append("      isnull(sum(case when is_free = 3 then xj end),0) as '索赔',");
		sql.append("      isnull(sum(case when is_free = 4 then xj end),0) as '返工',");
		sql.append("      isnull(sum(case when is_free = 5 then xj end),0) as '服务活动',");
		sql.append(" 	  max(banance_Date) banance_Date,max(balance_Code) balance_Code");
		sql.append("      from (");
		sql.append(" select fe.id entrust_id,fe.entrust_code,fe.fix_date,m.maintain_code mcode,null scode,ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , m.is_free ,m.part_quantity * pi.cost_price xj,20 out_type,bb.banance_Date ,bb.balance_Code");
		sql.append(" from tb_fix_entrust fe ,tb_maintain_part_content m ,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ,Tb_Business_Balance bb");
		sql.append(" where fe.id = m.entrust_id and fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and m.part_id = pi.id and cmt.id = ci.model_type_id and bb.id = m.balance_id");
		sql.append(" and m.is_confirm = 8002 and m.balance_id != null ");
		if(balanceFixSellVo != null){
			if(StringUtils.isNotBlank(balanceFixSellVo.getBeginFixDate())){
				sql.append(" and bb.banance_Date >= '").append(balanceFixSellVo.getBeginFixDate()).append("'");
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getEndFixDate())){
				sql.append(" and bb.banance_Date <= '").append(balanceFixSellVo.getEndFixDate()).append("'");
			}
			if(balanceFixSellVo.getFixTypeId()!=null){
				sql.append(" and fe.fix_type_id = ").append(balanceFixSellVo.getFixTypeId());
			}
			if(balanceFixSellVo.getModelTypeId()!=null){
				sql.append(" and cmt.id = ").append(balanceFixSellVo.getModelTypeId());
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getLianceseCode())){
				sql.append(" and ci.license_code like '%").append(balanceFixSellVo.getLianceseCode()).append("%'");
			}
			if(balanceFixSellVo.getUserId() != null){
				sql.append(" and u.id = ").append(balanceFixSellVo.getUserId());
			}
		}
		sql.append(" union all");
		sql.append(" select  fe.id entrust_id,fe.entrust_code,fe.fix_date ,null mcode, so.stock_out_code scode, ci.license_code,ft.fix_type , c.customer_name , u.user_real_name , sod.is_free,sod.quantity*pi.cost_price xj,11 out_type,bb.banance_Date ,bb.balance_Code");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod ,tb_fix_entrust fe,tb_car_info ci,tm_fix_type ft , tb_customer c , tm_user u , tb_part_info pi,tm_car_model_type cmt ,Tb_Business_Balance bb");
		sql.append(" where so.id = sod.stockout_id and  fe.car_info_id = ci.id and fe.fix_type_id = ft.id and fe.customer_id = c.id and fe.user_id = u.id and sod.partinfo_id = pi.id and cmt.id = ci.model_type_id and bb.id = sod.balance_id" );
		sql.append(" and so.out_type = 11 and so.trust_bill != '' and so.trust_bill = fe.entrust_code");
		sql.append(" and so.is_confirm = 8002 and sod.balance_id != null ");
		if(balanceFixSellVo != null){
			if(StringUtils.isNotBlank(balanceFixSellVo.getBeginFixDate())){
				sql.append(" and bb.banance_Date >= '").append(balanceFixSellVo.getBeginFixDate()).append("'");
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getEndFixDate())){
				sql.append(" and bb.banance_Date <= '").append(balanceFixSellVo.getEndFixDate()).append("'");
			}
			if(balanceFixSellVo.getFixTypeId()!=null){
				sql.append(" and fe.fix_type_id = ").append(balanceFixSellVo.getFixTypeId());
			}
			if(balanceFixSellVo.getModelTypeId()!=null){
				sql.append(" and cmt.id = ").append(balanceFixSellVo.getModelTypeId());
			}
			if(StringUtils.isNotBlank(balanceFixSellVo.getLianceseCode())){
				sql.append(" and ci.license_code like '%").append(balanceFixSellVo.getLianceseCode()).append("%'");
			}
			if(balanceFixSellVo.getUserId() != null){
				sql.append(" and u.id = ").append(balanceFixSellVo.getUserId());
			}
		}
		sql.append(") aa");
		sql.append(" group by aa.entrust_id");
		
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	public List<Object[]> getTbPartInfoVoByCustomerId(TbPartInfo tbPartInfo ,String customerId){
		
		StringBuilder sql = new StringBuilder();
		

		sql.append("select aa.price , sh.id shId,sh.house_code,sh.house_name,u.unit_name, ");
		sql.append("tpi.id partID,tpi.part_code,tpi.part_name,tpi.pinyin_code,tpi.store_quantity,tpi.cost_price,tpi.sell_price ,pt.type_name");
		sql.append(" from  tb_part_info tpi left join ");
		sql.append(" (select sod.price , sod.partinfo_id from tm_stock_out so , tm_stockout_detail sod , tb_customer c ,tb_part_info  tp");
		sql.append(" where so.id = sod.stockout_id and c.id = so.customer_bill and tp.id = sod.partinfo_id");
		if(StringUtils.isNotBlank(customerId)){
			sql.append(" and so.customer_bill = ").append(customerId);
		}else{
			sql.append(" and so.id = null ");
		}
		sql.append(" ) aa");
		sql.append(" on tpi.id = aa.partinfo_id , tm_store_house sh ,tm_unit u,");
		sql.append(" tb_part_info tpi2 left join tm_part_type pt");
		sql.append(" on pt.id = tpi2.part_type_id");

		sql.append(" where sh.id = tpi.store_house_id and u.id = tpi.unit_id and tpi2.id = tpi.id");
		
		if(null != tbPartInfo){
			if(tbPartInfo.getTmStoreHouse()!= null && tbPartInfo.getTmStoreHouse().getId() != null){
				sql.append(" and tpi.STORE_HOUSE_ID = "+ tbPartInfo.getTmStoreHouse().getId());
			}
			
			if(tbPartInfo.getTmPartType() != null && tbPartInfo.getTmPartType().getId() != null){
				sql.append(" and tpi.PART_TYPE_ID = "+ tbPartInfo.getTmPartType().getId());
			}
			
			if(tbPartInfo.getTmCarModelType() != null && tbPartInfo.getTmCarModelType().getId() != null){
				sql.append(" and tpi.CAR_MODEL_TYPE_ID = "+ tbPartInfo.getTmCarModelType().getId());
			}
			
			if(StringUtils.isNotBlank(tbPartInfo.getPartCode())){
				sql.append(" and tpi.part_code like '%"+tbPartInfo.getPartCode()+"%'");
			}
			
			if(StringUtils.isNotBlank(tbPartInfo.getPartName())){
				sql.append(" and tpi.part_name like '%"+tbPartInfo.getPartName()+"%'");
			}
			
			if(StringUtils.isNotBlank(tbPartInfo.getPinyinCode())){
				sql.append(" and tpi.pinyin_Code like '%"+tbPartInfo.getPinyinCode()+"%'");
			}
		}
		sql.append(" order by tpi.id desc");
		List<Object[]> result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	
	public Double getOutTbPartInfoByMonth(Long partInfoId){
		
		StringBuilder sql = new StringBuilder();
		
		String monthCode = CommonMethod.parseDateToString(new Date(), "yyyy-MM");
		
		sql.append("select sum(aa.so_quantity) from (");
		sql.append("select sum(sod.quantity) so_quantity from tm_stock_out so , tm_stockout_detail sod where so.id = sod.stockout_id  and so.is_confirm not in(8000) and so.stock_out_code like '%"+monthCode+"%' and partinfo_id = "+partInfoId+" ");
		sql.append("union all "); 
		sql.append("select sum(m.part_quantity) so_quantity from tb_maintain_part_content m where  m.is_confirm not in(8000) and m.maintain_code like '%"+monthCode+"%' and m.part_id = "+partInfoId+" ");
		sql.append(") aa");
		
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		
		Object[] obj = list.get(0);
		if(null == obj) return 0D;
		
		Double result = obj[0]!=null ? Double.valueOf(obj[0].toString()) : 0D;
		return result;
	}
	
	
	public Double getInTbPartInfoByMonth(Long partInfoId){
		StringBuilder sql = new StringBuilder();
		
		String monthCode = CommonMethod.parseDateToString(new Date(), "yyyy-MM");
		
		sql.append("select sum(sid.quantity) quantity from tm_stock_in si , tm_stockin_detail sid where sid.stock_id = si.id and si.is_confirm not in(8000) and si.stock_in_code like '%"+monthCode+"%' and partinfo_id = "+partInfoId+" ");

		
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		Object[] obj = list.get(0);
		if(null == obj) return 0D;
		Double result = obj[0]!=null ? Double.valueOf(obj[0].toString()) : 0D;
		return result;
	}
	
	
	public List<TbPartInfo> checkHousePartInfo(Map<Long, Long> paramMap) {
		
		StringBuilder hql =  new StringBuilder();
		
		hql.append(" from TbPartInfo t where 1=1 ");
		String whereStr = "";
		if(null != paramMap){
			
			hql.append(" and ");
			
			int idx = 0;
			for(Long houseId : paramMap.keySet()){
				Long checkType = paramMap.get(houseId);
				 
				whereStr += "  (t.tmStoreHouse.id = "+houseId;
				
				if(checkType.equals(Constants.GREATERZERO)){
					whereStr += " and t.storeQuantity > 0)";
				}else if(checkType.equals(Constants.EQUALZERO)){
					whereStr += " and t.storeQuantity = 0)";
				}else if(checkType.equals(Constants.LESSZERO)){
					whereStr += " and t.storeQuantity < 0)";
				}
				
				if((idx+1) != paramMap.keySet().size())
					whereStr += " or ";
				
				idx ++ ;
				
			}
		}
		hql.append(whereStr);
		return this.getHibernateTemplate().find(hql.toString());
		
	}
	
	
	
	public Double getOutTmStoreHouseAsBegin(Long storeHouseId,String year,String mounth,Long outType){
		
		StringBuilder sql = new StringBuilder();
		
		String stockoutWhereStr = " and (";
		
		String maintainWhereStr = " and (";
		
		if(mounth.equals("01")){
			int lastyear = Integer.valueOf(year)-1;
			
			stockoutWhereStr += " so.stock_out_code like '%"+lastyear + "12"+"%'";
			maintainWhereStr += " m.maintain_code like '%"+lastyear + "12"+"%'";
		}else{
			
			for(int i=1 ; i<Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				
				
				if(i==1){
					stockoutWhereStr += " so.stock_out_code like '%"+yearhMonth+"%'";
					
					maintainWhereStr += " m.maintain_code like '%"+yearhMonth+"%'";
				}else{
					
					stockoutWhereStr += " or so.stock_out_code like '%"+yearhMonth+"%'";
					
					maintainWhereStr += " or m.maintain_code like '%"+yearhMonth+"%'";
				}
			}
		}
		
		stockoutWhereStr +=")";
		maintainWhereStr +=")";
		
		sql.append("select sum(aa.so_quantity) from (");
		if(outType != null && outType.equals(StockTypeElements.MAINTAIN.getElementValue())){
			sql.append("select sum(m.part_quantity*pi.cost_price) so_quantity from tb_maintain_part_content m  ,tb_part_info pi ");
			sql.append("where pi.id = m.part_id and  m.is_confirm not in(8000) and m.is_free = 1");
			sql.append(" and pi.store_house_id = "+storeHouseId + " "+ maintainWhereStr);
		}else{
		//sql.append(" union all ");
		
			sql.append("select sum(sod.quantity*pi.cost_price) so_quantity from tm_stock_out so , tm_stockout_detail sod ,tb_part_info pi ");
			sql.append("where pi.id = sod.partinfo_Id and so.id = sod.stockout_id  and so.is_confirm not in(8000) and (sod.is_free = 1 or sod.is_free is null)");
			sql.append(" and pi.store_house_id = "+storeHouseId + " "+ stockoutWhereStr);
			sql.append(" and so.out_Type="+outType);
		}
		sql.append(") aa");
		
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		
		Object obj = list.get(0);
		if(null == obj) return 0D;
		
		Double result = obj!=null ? Double.valueOf(obj.toString()) : 0D;
		return result;
	}
	
	
	public Double getOutTmStoreHouseAsFinal(Long storeHouseId,String year,String mounth,Long outType){
		
		StringBuilder sql = new StringBuilder();
		
		String stockoutWhereStr = " and (";
		
		String maintainWhereStr = " and (";
		
		for(int i=1 ; i<=Integer.valueOf(mounth) ; i++){
			
			String mounthStr = i<10?"0"+i:i+"";
			String yearhMonth = year + "" + mounthStr;
			
			
			
			if(i==1){
				stockoutWhereStr += " so.stock_out_code like '%"+yearhMonth+"%'";
				
				maintainWhereStr += " m.maintain_code like '%"+yearhMonth+"%'";
			}else{
				
				stockoutWhereStr += " or so.stock_out_code like '%"+yearhMonth+"%'";
				
				maintainWhereStr += " or m.maintain_code like '%"+yearhMonth+"%'";
			}
		}
		stockoutWhereStr +=")";
		maintainWhereStr +=")";
		sql.append("select sum(aa.so_quantity) from (");
		if(outType != null && outType.equals(StockTypeElements.MAINTAIN.getElementValue())){
			sql.append("select sum(m.part_quantity*pi.cost_price) so_quantity from tb_maintain_part_content m  ,tb_part_info pi ");
			sql.append("where pi.id = m.part_id and  m.is_confirm not in(8000) and m.is_free = 1");
			sql.append(" and pi.store_house_id = "+storeHouseId + " "+ maintainWhereStr);
		}else{
			
			sql.append("select sum(sod.quantity*pi.cost_price) so_quantity from tm_stock_out so , tm_stockout_detail sod ,tb_part_info pi ");
			sql.append("where pi.id = sod.partinfo_Id and so.id = sod.stockout_id  and so.is_confirm not in(8000) and (sod.is_free = 1 or sod.is_free is null)");
			sql.append(" and pi.store_house_id = "+storeHouseId + " "+ stockoutWhereStr);
			sql.append(" and so.out_Type="+outType);
		}
//		sql.append(" union all ");
		sql.append(") aa");
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		
		Object obj = list.get(0);
		if(null == obj) return 0D;
		
		Double result = obj!=null ? Double.valueOf(obj.toString()) : 0D;
		return result;
	}
	
	
	
	public Double getInTmStoreHouseAsBegin(Long storeHouseId,String year,String mounth,Long inType){
		StringBuilder sql = new StringBuilder();
		
		String stockoutWhereStr = " and (";
		if(mounth.equals("01")){
			int lastyear = Integer.valueOf(year)-1;
			
			stockoutWhereStr += " si.stock_in_code like '%"+lastyear + "12"+"%'";
		}else{
			
			for(int i=1 ; i<Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				if(i==1){
					stockoutWhereStr += " si.stock_in_code like '%"+yearhMonth+"%'";
				}else{
					
					stockoutWhereStr += " or si.stock_in_code like '%"+yearhMonth+"%'";
				}
				
			}
		}
		stockoutWhereStr +=")";
		sql.append("select sum(sid.quantity*pi.cost_price) quantity from tm_stock_in si , tm_stockin_detail sid  ,tb_part_info pi");
		sql.append(" where pi.id = sid.partinfo_id  and sid.stock_id = si.id and si.is_confirm not in(8000) ");
		sql.append(" and pi.store_house_id = "+storeHouseId + " "+ stockoutWhereStr);
		if(inType != null){
			sql.append(" and si.in_Type="+inType);
		}
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		Object obj = list.get(0);
		if(null == obj) return 0D;
		Double result = obj!=null ? Double.valueOf(obj.toString()) : 0D;
		return result;
	}
	
	
	public Double getInTmStoreHouseAsFinal(Long storeHouseId,String year,String mounth,Long inType){
		StringBuilder sql = new StringBuilder();
		
		String stockoutWhereStr = " and (";
		
		for(int i=1 ; i<=Integer.valueOf(mounth) ; i++){
			
			String mounthStr = i<10?"0"+i:i+"";
			String yearhMonth = year + "" + mounthStr;
			
			if(i==1){
				stockoutWhereStr += " si.stock_in_code like '%"+yearhMonth+"%'";
			}else{
				
				stockoutWhereStr += " or si.stock_in_code like '%"+yearhMonth+"%'";
			}
			
		}
		
		stockoutWhereStr +=")";
		sql.append("select sum(sid.quantity*pi.cost_price) quantity from tm_stock_in si , tm_stockin_detail sid  ,tb_part_info pi");
		sql.append(" where pi.id = sid.partinfo_id  and sid.stock_id = si.id and si.is_confirm not in(8000) ");
		sql.append(" and pi.store_house_id = "+storeHouseId + " "+ stockoutWhereStr);
		if(inType != null){
			sql.append(" and si.in_Type="+inType);
		}
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		Object obj = list.get(0);
		if(null == obj) return 0D;
		Double result = obj!=null ? Double.valueOf(obj.toString()) : 0D;
		return result;
	}
	
	
	/**
	 * 仓库月度期间收发统计
	 * @Date      2010-7-13
	 * @Function  
	 * @param tmStoreHouseReceiverStatVo
	 * @return
	 */
	public List getStoreHouseReceiverStat(String year,String mounth){
		
		String stockoutWhereStr = "";
		String maintainWhereStr = "";
		String stockinWhereStr = "";
			
		String yearhMonth = year + "" + mounth;
			
			
		stockoutWhereStr += " and so.stock_out_code like '%"+yearhMonth+"%'";
		
		maintainWhereStr += " and m.maintain_code like '%"+yearhMonth+"%'";
		
		stockinWhereStr += " and si.stock_in_code like '%"+yearhMonth+"%'";
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("select sh.house_name , aa.*   from"); 
		sql.append(" (select sh.id house_id ,so.out_type , 0 type , sum(sod.quantity * pi.cost_price) cost_stockout , sum(sod.quantity * sod.price) sell_stockout");
		sql.append(" from tm_stock_out so , tm_stockout_detail sod , tm_store_house sh , tb_part_info pi"); 
		sql.append(" where so.id = sod.stockout_id  and sh.id = pi.store_house_id and pi.id = sod.partinfo_id");
		sql.append(" and so.is_confirm not in (8000) and (sod.is_free = 1 or sod.is_free is null) ");
		sql.append(stockoutWhereStr);
		
		sql.append(" group by sh.id , so.out_type");
		sql.append(" union all");
		sql.append(" select sh.id , 20 out_type, 0 type , sum(m.part_quantity * pi.cost_price) cost_stockout , sum(m.part_quantity * m.price) sell_stockout");
		sql.append(" from tb_maintain_part_content m ,tm_store_house sh , tb_part_info pi");
		sql.append(" where sh.id = pi.store_house_id and pi.id = m.part_id");
		sql.append(" and m.is_confirm not in (8000) and (m.is_free = 1 or m.is_free is null) ");
		sql.append(maintainWhereStr);
		
		sql.append(" group by sh.id"); 
		sql.append(" union all");
		sql.append(" select sh.id ,si.in_type , 1 type , sum(pi.cost_price * sid.quantity) cost_stockin , null");
		sql.append(" from tm_stock_in si , tm_stockin_detail sid ,tm_store_house sh , tb_part_info pi"); 
		sql.append(" where si.id =sid.stock_id and sh.id = pi.store_house_id and pi.id = sid.partinfo_id");
		sql.append(" and si.is_confirm not in (8000)");
		sql.append(stockinWhereStr);
		
		sql.append(" group by sh.id ,si.in_type) aa , tm_store_house sh");
		sql.append(" where aa.house_id = sh.id");
		sql.append(" order by aa.house_id");
		

		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
	
	public List getOutTbPartInfoByMonth(Long partInfoId,String year,String mounth,int bRfType){
		
		StringBuilder sql = new StringBuilder();
		
		String stockoutWhereStr = " and (";;
		String maintainWhereStr = " and (";
		
		if(bRfType == 1){
			for(int i=1 ; i<=Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				if(i==1){
					stockoutWhereStr += " so.stock_out_code like '%"+yearhMonth+"%'";
					maintainWhereStr += " m.maintain_code like '%"+yearhMonth+"%'";
				}else{
					stockoutWhereStr += " or so.stock_out_code like '%"+yearhMonth+"%'";
					maintainWhereStr += " or m.maintain_code like '%"+yearhMonth+"%'";
				}
			}
		}else{
			for(int i=1 ; i<Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<=10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				if(i==1){
					stockoutWhereStr += " so.stock_out_code like '%"+yearhMonth+"%'";
					maintainWhereStr += " m.maintain_code like '%"+yearhMonth+"%'";
				}else{
					stockoutWhereStr += " or so.stock_out_code like '%"+yearhMonth+"%'";
					maintainWhereStr += " or m.maintain_code like '%"+yearhMonth+"%'";
				}
			}
			
		}
		stockoutWhereStr +=")";
		maintainWhereStr +=")";
		
		sql.append("select sum(aa.so_quantity) aa_quantity ,sum(aa.jsprice) aa_price from (");
		sql.append("select sum(sod.quantity) so_quantity , sum(sod.quantity*sod.price) jsprice from tm_stock_out so , tm_stockout_detail sod where so.id = sod.stockout_id  and so.is_confirm not in(8000)");
		sql.append(" and partinfo_id = "+partInfoId+" ");
		sql.append(stockoutWhereStr);
		sql.append("union all "); 
		sql.append("select sum(m.part_quantity) so_quantity, sum(m.part_quantity*m.price) jsprice from tb_maintain_part_content m where  m.is_confirm not in(8000) ");
		sql.append("and m.part_id = "+partInfoId+" ");
		sql.append(maintainWhereStr);
		sql.append(") aa");
		
		List<Object[]> list  = this.findByOriginSql(sql.toString(), null);
		
		return list;
	}
	
	
	public List getInTbPartInfoByMonth(Long partInfoId,String year,String mounth,int bRfType){
		StringBuilder sql = new StringBuilder();
		
		String stockinWhereStr = " and (";;
		
		if(bRfType == 1){
			for(int i=1 ; i<=Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				if(i==1){
					stockinWhereStr += " si.stock_in_code like '%"+yearhMonth+"%'";
				}else{
					stockinWhereStr += " or si.stock_in_code like '%"+yearhMonth+"%'";
				}
			}
		}else{
			for(int i=1 ; i<Integer.valueOf(mounth) ; i++){
				
				String mounthStr = i<=10?"0"+i:i+"";
				String yearhMonth = year + "" + mounthStr;
				
				if(i==1){
					stockinWhereStr += " si.stock_in_code like '%"+yearhMonth+"%'";
				}else{
					stockinWhereStr += " or si.stock_in_code like '%"+yearhMonth+"%'";
				}
			}
			
		}
		stockinWhereStr +=")";
		
		sql.append("select sum(sid.quantity) quantity ,sum(sid.quantity*sid.price) price ");
		sql.append("from tm_stock_in si , tm_stockin_detail sid where sid.stock_id = si.id and si.is_confirm not in(8000) and partinfo_id = "+partInfoId+" ");
		sql.append(stockinWhereStr);
		
		List list  = this.findByOriginSql(sql.toString(), null);
		return list;
	}
	
	
	
	private String buildReceiverCondition(TbPartReceiverStatVo tbPartReceiverStatVo,String year,String mounth){
		
		String stockoutWhereStr = "";
		String maintainWhereStr = "";
		String stockinWhereStr = "";
			
		String yearhMonth = year + "" + mounth;
			
			
		stockoutWhereStr += " and zo.stock_out_code like '%"+yearhMonth+"%'";
		
		maintainWhereStr += " and bc.maintain_code like '%"+yearhMonth+"%'";
		
		stockinWhereStr += " and xo.stock_in_code like '%"+yearhMonth+"%'";
		
		StringBuilder sql = new StringBuilder();
		sql.append(" from  tb_part_info p, tm_store_house h ,(");
		sql.append(" select aa.pid pid , sum(aa.sumq) sumq , sum(aa.outprice) sumoutp from (");
		sql.append(" select sum(o.quantity) sumq ,o.partinfo_id pid ,sum(o.quantity*o.price) outprice from  TM_STOCKOUT_DETAIL O where"); 
		sql.append(" exists (select * from TM_STOCK_OUT zo where zo.id = o.stockout_id and zo.is_confirm not in (8000)");
		sql.append(stockoutWhereStr);
		
		sql.append(" )");
		sql.append(" GROUP BY o.partinfo_id  union all select  sum(bc.part_quantity) summ,bc.part_id ,sum(bc.part_quantity*bc.price) mprice  from TB_MAINTAIN_PART_CONTENT bc where bc.is_confirm not in (8000)  ");
		sql.append(maintainWhereStr);
		sql.append(" group by bc.part_id) aa");
		sql.append(" group by aa.pid");
		sql.append(" ) r full OUTER JOIN");
		sql.append(" (select sum(o.quantity) sumqt ,o.partinfo_id pid ,avg(o.price) avp from TM_STOCKIN_DETAIL O ");
		sql.append(" where exists (select * from TM_STOCK_IN xo where xo.id = O.stock_id and xo.is_confirm not in (8000) "+stockinWhereStr+" )");
		sql.append(" GROUP BY o.partinfo_id ) t ON r.pid = t.pid"); 
		sql.append(" where p.id = r.pid and h.id = p.store_house_id ");
		if(tbPartReceiverStatVo != null){
			if(tbPartReceiverStatVo.getStoreHouseId() != null){
				sql.append(" and p.store_house_id = ").append(tbPartReceiverStatVo.getStoreHouseId());
			}
		}
		
		return sql.toString();
		
	}
		
	
	/**
	 * 配件月度期间收发存统计 所有列表
	 */
	public List getPartReceiveListStat(TbPartReceiverStatVo tbPartReceiverStatVo,String year,String mounth){
		StringBuilder sql = new StringBuilder();
		sql.append("select h.house_name,p.part_code ,p.store_quantity , p.store_quantity*p.cost_price store_price ,p.part_name, t.sumqt sumIn ,r.sumq sumOut ,t.sumqt* t.avp in_avp_price, r.sumq*p.cost_price out_price,p.id,r.sumoutp");
		String conditionSql = buildReceiverCondition(tbPartReceiverStatVo,year,mounth);
		sql.append(conditionSql);
		
		List result  = this.findByOriginSql(sql.toString(), null);
		return result;
	}
	
	
}



