package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.business.dao.ITbBusinessBalanceItemDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbBusinessBalanceService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbBusinessBalanceItemService")
public class TbBusinessBalanceItemServiceImpl implements ITbBusinessBalanceItemService{

	@Autowired
	private ITbBusinessBalanceItemDao tbBusinessBalanceItemDao;
	
	@Autowired
	private ITmBalanceItemService tmBalanceItemService;
	
	@Autowired
	private ITbBusinessBalanceService tbBusinessBalanceService;

	public void insert(TbBusinessBalanceItem tbBusinessBalanceItem) {
		// TODO Auto-generated method stub
		tbBusinessBalanceItemDao.insert(tbBusinessBalanceItem);
	}

	public void update(TbBusinessBalanceItem tbBusinessBalanceItem) {
		// TODO Auto-generated method stub
		tbBusinessBalanceItemDao.update(tbBusinessBalanceItem);
	}
	
	public List<TbBusinessBalanceItem> findTbBusinessBalanceItemListByTbBusinessBalanceId(Long tbBusinessBalanceId){
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList= new ArrayList<TbBusinessBalanceItem>();
		
		List<Object[]> list = tbBusinessBalanceItemDao.findByOriginSql("select * from tb_business_balance_item where balance_id = ?", new Object[]{tbBusinessBalanceId});
		
		if(null != list && list.size() > 0){
			
			for(Object[] o : list){
				
				TbBusinessBalanceItem t = new TbBusinessBalanceItem();
				
				t.setId(Long.valueOf(o[0].toString()));
				
				t.setTbBusinessBalance(tbBusinessBalanceService.findById(Long.valueOf(o[1].toString())));
				
				t.setBalanceItemCode(o[2].toString());
				
				t.setBalanceItemName(o[3] == null? null : o[3].toString());
				
				t.setBalanceItemTotal(Double.valueOf(o[4].toString()));
				
				tbBusinessBalanceItemList.add(t);
				
			}
			
		}
		
		//return tbBusinessBalanceItemDao.findBySQL("SELECT tbBusinessBalanceItem FROM TbBusinessBalanceItem tbBusinessBalanceItem WHERE tbBusinessBalanceItem.tbBusinessBalance.id=?",new Object[]{tbBusinessBalanceId});
	
		return tbBusinessBalanceItemList;
	}
	
	public List<TbBusinessBalanceItem> findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long tbBusinessBalanceId){
		
		List<TbBusinessBalanceItem> list = this.findTbBusinessBalanceItemListByTbBusinessBalanceId(tbBusinessBalanceId);
		
		List<TbBusinessBalanceItem> listAll = tbBusinessBalanceItemDao.findBySQL("SELECT tbBusinessBalanceItem FROM TbBusinessBalanceItem tbBusinessBalanceItem WHERE tbBusinessBalanceItem.tbBusinessBalance.id IN (SELECT tbBusinessBalance from TbBusinessBalance tbBusinessBalance where tbBusinessBalance.balanceCode=(select tbBusinessBalance2.balanceCode from TbBusinessBalance tbBusinessBalance2 where tbBusinessBalance2.id=?))", new Object[]{tbBusinessBalanceId});
		
		List<TbBusinessBalanceItem> listGroup = new ArrayList<TbBusinessBalanceItem>();
		
		if(null!=list&&list.size()>0){
			
			for(int i = 0 ; i < list.size() ; i++){
				
				TbBusinessBalanceItem ti = list.get(i);
				
				BigDecimal d = new BigDecimal(String.valueOf(ti.getBalanceItemTotal()));
				
				for(int j = 0 ; j < listAll.size() ; j++){
					
					TbBusinessBalanceItem tj = listAll.get(j);
					
					if(ti.getBalanceItemCode().equals(tj.getBalanceItemCode())&&!ti.getId().equals(tj.getId())){
						
						d = d.add(new BigDecimal(String.valueOf(tj.getBalanceItemTotal())));
						
						ti.setBalanceItemTotal(d.doubleValue());
						
					}
				}
				
				TmBalanceItem tmBalanceItem = tmBalanceItemService.findTmBalanceItemByTmBalanceItemCode(ti.getBalanceItemCode());
				
				if(null!=tmBalanceItem){
					
					ti.setBalanceItemName(tmBalanceItem.getItemName());
				
				}
				
				listGroup.add(ti);
			}
		}
		return listGroup;
	}
	
	//经营状况统计-结算情况
	public List<StatisticsTbFixBusinessVo> staticsBalanceItem(TbBusinessBalance tbBusinessBalance){
		
		String sql = "select balance_item_code,sum(balance_item_total) as total from tb_business_balance_item a ";
	
		String sql_db = "";
		
		String sql_db_join = "";
		
		String sql_condition = " where 1 = 1 ";
		
		if(null!=tbBusinessBalance){
			
			sql_db += ",tb_business_balance b ";
			
			sql_db_join += " and a.balance_id = b.id ";
			
			if(null!=tbBusinessBalance.getBananceDateStart_s()&&!"".equals(tbBusinessBalance.getBananceDateStart_s())){
				
				sql_condition += " and convert(varchar(10),b.BANANCE_DATE,120)>="+ "'"+tbBusinessBalance.getBananceDateStart_s()+"'";
				
			}
			if(null!=tbBusinessBalance.getBananceDateEnd_s()&&!"".equals(tbBusinessBalance.getBananceDateEnd_s())){
				
				sql_condition += " and convert(varchar(10),b.BANANCE_DATE,120)<="+ "'" +tbBusinessBalance.getBananceDateEnd_s()+"'";
				
			}
			
			if(null!=tbBusinessBalance.getTmModelTypeId()){
				
				sql_db += ",tb_fix_entrust c,tb_car_info d,TM_CAR_MODEL_TYPE e ";
				
				sql_db_join += " and b.entrust_id = c.id and c.CAR_INFO_ID = d.id and  d.MODEL_TYPE_ID= e.id ";
				
				sql_condition += " and e.id = " + tbBusinessBalance.getTmModelTypeId();
			
			}
			
		}
				
		String sql_group = " group by balance_item_code ";
		
		List<Object[]> list = tbBusinessBalanceItemDao.findByOriginSql(sql+sql_db+sql_condition+sql_db_join+sql_group, null);
		
		List<StatisticsTbFixBusinessVo> statisticsTbFixBusinessVoList = new ArrayList<StatisticsTbFixBusinessVo>();
		
		Map<String,Double> map = new HashMap<String, Double>(); 
		
		if(null!=list&&list.size()>0){
			
			BigDecimal d = new BigDecimal("0.00");
			
			for(int i = 0 ; i < list.size() ; i++){
			
				Object[] o = list.get(i);
				
				String itemCode = String.valueOf(o[0]);
				
				Double itemTotal = Double.valueOf(String.valueOf(o[1]));
				
				StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();
				
				if("XLGSF".equals(itemCode)){
					
					statisticsTbFixBusinessVo.setBalanceItemName("修理工时费");
					
					statisticsTbFixBusinessVo.setBalanceItemAmount(itemTotal);
					
				}
				else if("XLCLF".equals(itemCode)){
					
					statisticsTbFixBusinessVo.setBalanceItemName("修理材料费");
					
					statisticsTbFixBusinessVo.setBalanceItemAmount(itemTotal);
					
				}
				else if("XSJE".equals(itemCode)){
					
					statisticsTbFixBusinessVo.setBalanceItemName("销售金额");
					
					statisticsTbFixBusinessVo.setBalanceItemAmount(itemTotal);
				}
				else if("ZJE".equals(itemCode)){
					
					continue;
				
				}
				else {
					
					statisticsTbFixBusinessVo.setBalanceItemName("其他收入");
				
					d = d.add(new BigDecimal(String.valueOf(itemTotal)));
					
					statisticsTbFixBusinessVo.setBalanceItemAmount(d.doubleValue());
					
					map.put(statisticsTbFixBusinessVo.getBalanceItemName(), statisticsTbFixBusinessVo.getBalanceItemAmount());
					
					continue;
				}
				
				statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo);

			}
			
			StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();
			
			statisticsTbFixBusinessVo.setBalanceItemName("其他收入");
			
			statisticsTbFixBusinessVo.setBalanceItemAmount(map.get(statisticsTbFixBusinessVo.getBalanceItemName()));
			
			statisticsTbFixBusinessVoList.add(statisticsTbFixBusinessVo);
		}
		
		return statisticsTbFixBusinessVoList;
	}
}
