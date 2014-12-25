package com.selfsoft.business.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.business.dao.ITmLoanRegisterDao;
import com.selfsoft.business.dao.ITmLoanRegisterDetailDao;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanRegisterDetail;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.framework.common.Constants;
@Service("tmLoanRegisterDetailService")
public class TmLoanRegisterDetailServiceImpl implements ITmLoanRegisterDetailService {
	
	@Autowired
	private ITmLoanRegisterDetailDao tmLoanRegisterDetailDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmLoanRegisterDao tmLoanRegisterDao;
	

	public List<TmLoanRegisterDetail> findByEntity(TmLoanRegisterDetail tmLoanRegisterDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLoanRegisterDetail.class);
		if(null!=tmLoanRegisterDetail){
			if(null!=tmLoanRegisterDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLoanRegisterDetail.getId()));
			}
			if(null!=tmLoanRegisterDetail.getLoanId()){
				detachedCriteria.add(Restrictions.eq("loanId", tmLoanRegisterDetail.getLoanId()));
			}
		
			if(null!=tmLoanRegisterDetail.getIsReturn()){
				detachedCriteria.add(Restrictions.eq("isReturn",tmLoanRegisterDetail.getIsReturn()));
			}
		} 
		return tmLoanRegisterDetailDao.findByCriteria(detachedCriteria, tmLoanRegisterDetail);
	}
	
	/**
	 * 批量添加借出登记详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时增加配件表的借出数量
	 */
	public void insertBatchLoanDetail(TmLoanRegister tmLoanRegister,String partCol){
		if(StringUtils.isNotBlank(partCol)){
			//更新借出登记主表
			tmLoanRegisterDao.insert(tmLoanRegister);
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String lianceQuantity = parts.split(":")[1];				//借出数量
				String oldCostPrice = parts.split(":")[2];					//原成本价
				String price  = parts.split(":")[3];						//借出价
				//保存借出详细
				TmLoanRegisterDetail tlrd = new TmLoanRegisterDetail();
				tlrd.setIsReturn(0L);										//第一次登记借出，一定是未归还
				tlrd.setLoanId(tmLoanRegister.getId());						//借出单ID
				TbPartInfo partInfo = tbPartInfoDao.findById(new Long(tbPartInfoId));
				tlrd.setTbPartInfo(partInfo);
				tlrd.setLoanPrice(new Double(price));
				tlrd.setLoanQuantity(new Double(lianceQuantity));
				tlrd.setOldCostPrice(new Double(oldCostPrice));
				tlrd.setReturnQuantity(0D);									//第一次登记借进，一定没有归还数量
				tmLoanRegisterDetailDao.insert(tlrd);
				
				//如果的确认状态，则增加配件借进量
				if(tmLoanRegister.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLoan(new Long(tbPartInfoId), new Double(lianceQuantity));
			}
			
		}
	}
	
	/**
	 * 更新配件的借出量
	 * @Date      2010-6-2
	 * @Function  
	 * @param partInfoId
	 * @param lianceQuantity			现借出量
	 */
	public void updatePartInfoLoan(Long partInfoId ,Double lianceQuantity ){
		TbPartInfo tpi = tbPartInfoDao.findById(partInfoId);
		//原借出量
		Double oldloanQuantity = tpi.getLoanQuantity()!=null?tpi.getLoanQuantity():0D;
		//借出量累加
		tpi.setLoanQuantity(oldloanQuantity+lianceQuantity);
		//更新日期
		tpi.setLastModifyDate(new Date());
		tbPartInfoDao.update(tpi);
	}
	
	/**
	 * 更新借出登记单下所有配件借进量
	 * @Date      2010-6-1
	 * @Function  
	 * @param stockInId
	 */
	public void updateBatchPartInfoLoan(Long loanId){
		
		TmLoanRegisterDetail lrDetail = new TmLoanRegisterDetail();
		lrDetail.setLoanId(loanId);
		List<TmLoanRegisterDetail> loanRegisterDetails = this.findByEntity(lrDetail);
		for(TmLoanRegisterDetail detail : loanRegisterDetails){
			this.updatePartInfoLoan(detail.getTbPartInfo().getId(), detail.getLoanQuantity());
		}
		
	}
	
	

	public void updateBatchLoanDetail(TmLoanRegister tmLoanRegister,String partCol){
		if(StringUtils.isNotBlank(partCol)){
			//更新借出登记主表
			tmLoanRegisterDao.update(tmLoanRegister);
			//删除修改前明细，增加修改后的明细
			tmLoanRegisterDao.deleteLoanRegDetail(tmLoanRegister.getId());
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String loanQuantity = parts.split(":")[1];					//借出数量
				String oldCostPrice = parts.split(":")[2];					//原成本价
				String price  = parts.split(":")[3];						//借出价
				//保存借出详细
				TmLoanRegisterDetail tlrd = new TmLoanRegisterDetail();
				tlrd.setIsReturn(0L);										//第一次登记借出，一定是未归还
				tlrd.setLoanId(tmLoanRegister.getId());									//借出单ID
				TbPartInfo partInfo = tbPartInfoDao.findById(new Long(tbPartInfoId));
				tlrd.setTbPartInfo(partInfo);
				tlrd.setLoanPrice(new Double(price));
				tlrd.setLoanQuantity(new Double(loanQuantity));
				tlrd.setOldCostPrice(new Double(oldCostPrice));
				tlrd.setReturnQuantity(0D);									//第一次登记借进，一定没有归还数量
				tmLoanRegisterDetailDao.insert(tlrd);
				
				//如果的确认状态，则增加配件借进量
				if(tmLoanRegister.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLoan(new Long(tbPartInfoId), new Double(loanQuantity));
			}
			
		}
	}
}
