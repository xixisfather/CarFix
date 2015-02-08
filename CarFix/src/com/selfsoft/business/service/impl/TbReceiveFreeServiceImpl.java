package com.selfsoft.business.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbReceiveFreeDao;
import com.selfsoft.business.model.TbBusinessBalance;
import com.selfsoft.business.model.TbReceiveFree;
import com.selfsoft.business.service.ITbReceiveFreeService;
import com.selfsoft.business.vo.StatisticsTbFixBusinessVo;
@Service("tbReceiveFreeService")
public class TbReceiveFreeServiceImpl implements ITbReceiveFreeService{
	
	@Autowired
	private ITbReceiveFreeDao tbReceiveFreeDao;

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		tbReceiveFreeDao.deleteById(id);
	}

	public TbReceiveFree findById(Long id) {
		// TODO Auto-generated method stub
		return tbReceiveFreeDao.findById(id);
	}

	public void insert(TbReceiveFree tbReceiveFree) {
		// TODO Auto-generated method stub
		tbReceiveFreeDao.insert(tbReceiveFree);
	}

	public void update(TbReceiveFree tbReceiveFree) {
		// TODO Auto-generated method stub
		tbReceiveFreeDao.update(tbReceiveFree);
	}
	
	public List<TbReceiveFree> findByTbReceiveFree(TbReceiveFree tbReceiveFree){
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TbReceiveFree.class);
		
		if(null!=tbReceiveFree){
			
			if(null!=tbReceiveFree.getBalanceCode()&&!"".equals(tbReceiveFree.getBalanceCode())){
				
				detachedCriteria.createAlias("tbBusinessBalance","tbBusinessBalance");
			
				detachedCriteria.add(Restrictions.like("tbBusinessBalance.balanceCode", "%"+tbReceiveFree.getBalanceCode()+"%"));
			}
			
			if(null!=tbReceiveFree.getAmountType()){
				
				detachedCriteria.add(Restrictions.eq("amountType",tbReceiveFree.getAmountType()));
			
			}
			
			if(null!=tbReceiveFree.getTbCustomer()){
				
				detachedCriteria.createAlias("tbCustomer","tbCustomer");
				
				if(null!=tbReceiveFree.getTbCustomer().getCustomerCode()&&!"".equals(tbReceiveFree.getTbCustomer().getCustomerCode())){
					detachedCriteria.add(Restrictions.like("tbCustomer.customerCode", "%"+tbReceiveFree.getTbCustomer().getCustomerCode()+"%"));
				}
				if(null!=tbReceiveFree.getTbCustomer().getCustomerName()&&!"".equals(tbReceiveFree.getTbCustomer().getCustomerName())){
					detachedCriteria.add(Restrictions.like("tbCustomer.customerName", "%"+tbReceiveFree.getTbCustomer().getCustomerName()+"%"));
				}
			}
		}
		
		return tbReceiveFreeDao.findByCriteria(detachedCriteria, tbReceiveFree);
		
	}
	
	public StatisticsTbFixBusinessVo staticsTbReceiveFree(List<TbBusinessBalance> tbBusinessBalanceList){
		
		if(null!=tbBusinessBalanceList&&tbBusinessBalanceList.size()>0){
			
			String id = "";
			
			for(int i = 0 ; i < tbBusinessBalanceList.size() ; i++){
				
				if(i==tbBusinessBalanceList.size()-1){
					
					id = id + tbBusinessBalanceList.get(i).getId();
				
				}
				else{
					
					id = id + tbBusinessBalanceList.get(i).getId() + ",";
				
				}
			}
			
			String sql = "select count(*) as countNum,sum(FEE_AMOUNT) as total from TB_RECEIVE_FREE where amount_type = 2 and balance_id in ("+id+")";
			
			List<Object[]> list = tbReceiveFreeDao.findByOriginSql(sql, null);
			
			StatisticsTbFixBusinessVo statisticsTbFixBusinessVo = new StatisticsTbFixBusinessVo();
			
			if(null!=list&&list.size()>0){
				
				Object[] o = list.get(0);
				
				statisticsTbFixBusinessVo.setFreeBalance(Integer.valueOf(String.valueOf(o[0]==null?"0":o[0])));
				
				statisticsTbFixBusinessVo.setBalanceItemFree(Double.valueOf(String.valueOf(o[1]==null?"0.00":o[1])));

			}
			
			return statisticsTbFixBusinessVo;
			
		}
		
		return null;
	}
	
	public List<TbReceiveFree> findByBalanceId(Long balanceId){
		
		String hql = "from TbReceiveFree tbReceiveFree where tbReceiveFree.tbBusinessBalance.id =" + balanceId;
		
		return tbReceiveFreeDao.findBySQL(hql, null);
	}
	
	public void deleteByBalanceId(Long balanceId) {
		
		List<TbReceiveFree> tbReceiveFreeList = this.findByBalanceId(balanceId);
		
		if(null != tbReceiveFreeList && tbReceiveFreeList.size() > 0) {
			
			for(TbReceiveFree tr : tbReceiveFreeList) {
				
				this.deleteById(tr.getId());
			}
		}
		
	}
}
