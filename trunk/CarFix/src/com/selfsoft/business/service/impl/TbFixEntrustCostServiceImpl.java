package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITbFixEntrustCostDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbFixEntrustCost;
import com.selfsoft.business.service.ITbFixEntrustCostService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.framework.common.CommonMethod;

@Service("tbFixEntrustCostService")
public class TbFixEntrustCostServiceImpl implements ITbFixEntrustCostService {

	@Autowired
	private ITbFixEntrustCostDao tbFixEntrustCostDao;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;

	public List<TbFixEntrustCost> findByTbFixEntrustCost(
			TbFixEntrustCost tbFixEntrustCost) {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(TbFixEntrustCost.class);

		if (null != tbFixEntrustCost) {

			if (null != tbFixEntrustCost.getTbFixEntrust()) {

				detachedCriteria.createAlias("tbFixEntrust", "tbFixEntrust");

				if (null != tbFixEntrustCost.getTbFixEntrust().getEntrustCode()
						&& !"".equals(tbFixEntrustCost.getTbFixEntrust()
								.getEntrustCode())) {
					detachedCriteria.add(Restrictions.like(
							"tbFixEntrust.entrustCode", "%"
									+ tbFixEntrustCost.getTbFixEntrust()
											.getEntrustCode() + "%"));
				}

				if (null != tbFixEntrustCost.getTbFixEntrust()
						.getFixDateStart()) {
					detachedCriteria.add(Restrictions.ge("tbFixEntrust.fixDate",
							tbFixEntrustCost.getTbFixEntrust()
									.getFixDateStart()));
				}
				if (null != tbFixEntrustCost.getTbFixEntrust().getFixDateEnd()) {
					detachedCriteria.add(Restrictions.le("tbFixEntrust.fixDate",
							CommonMethod.addDate(tbFixEntrustCost
									.getTbFixEntrust().getFixDateEnd(), 1)));
				}

				if (null != tbFixEntrustCost.getTbFixEntrust().getTbCarInfo()) {
					
					detachedCriteria.createAlias("tbFixEntrust.tbCarInfo", "tbCarInfo");

					if (null != tbFixEntrustCost.getTbFixEntrust().getTbCarInfo().getLicenseCode() && !"".equals(tbFixEntrustCost.getTbFixEntrust().getTbCarInfo().getLicenseCode())) {
						detachedCriteria.add(Restrictions.like(
								"tbCarInfo.licenseCode", "%"
										+ tbFixEntrustCost.getTbFixEntrust().getTbCarInfo()
												.getLicenseCode() + "%"));
					}
					
				}
			}

		}

		return tbFixEntrustCostDao.findByCriteria(detachedCriteria,
				tbFixEntrustCost);
	}
	
	public TbFixEntrustCost findById(Long id) {
		
		return tbFixEntrustCostDao.findById(id);
	}
	
	public void insertTbFixEntrustCost(TbFixEntrustCost tbFixEntrustCost){
		tbFixEntrustCostDao.insert(tbFixEntrustCost);
	}
	
	public void updateTbFixEntrustCost(TbFixEntrustCost tbFixEntrustCost){
		tbFixEntrustCostDao.update(tbFixEntrustCost);
	}
	
	public boolean deleteById(Long id){
		return tbFixEntrustCostDao.deleteById(id);
	}
	

	public BigDecimal sumTbFixEntrustCostByTbFixEntrustId(Long tbFixEntrustId){
		
		String sql = "select sum(cost_price) from tb_fix_entrust_cost where entrust_id =" + tbFixEntrustId;
		
		List list = tbFixEntrustCostDao.findByOriginSql(sql, null);
		
		if(null != list && list.size() > 0){
			
			if(null != list.get(0))
				return new BigDecimal(Double.valueOf(list.get(0).toString()));
		}
		
		
		
		return new BigDecimal("0.00");
	}
}
