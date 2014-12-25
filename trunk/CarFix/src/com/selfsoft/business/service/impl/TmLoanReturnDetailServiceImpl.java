package com.selfsoft.business.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.dao.ITmLoanRegisterDetailDao;
import com.selfsoft.business.dao.ITmLoanReturnDao;
import com.selfsoft.business.dao.ITmLoanReturnDetailDao;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanRegisterDetail;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmLoanReturnDetail;
import com.selfsoft.business.service.ITmLoanRegisterDetailService;
import com.selfsoft.business.service.ITmLoanRegisterService;
import com.selfsoft.business.service.ITmLoanReturnDetailService;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
@Service("tmLoanReturnDetailService")
public class TmLoanReturnDetailServiceImpl implements
		ITmLoanReturnDetailService {

	@Autowired
	private ITmLoanReturnDetailDao tmLoanReturnDetailDao;
	@Autowired
	private ITmLoanRegisterDetailDao tmLoanRegisterDetailDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITmLoanReturnDao tmLoanReturnDao;
	@Autowired
	private ITmLoanRegisterService tmLoanRegisterService;
	@Autowired
	private ITmLoanRegisterDetailService tmLoanRegisterDetailService;
	
	public List<TmLoanReturnDetail> findByEntity(TmLoanReturnDetail tmLoanReturnDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLoanReturnDetail.class);
		if(null!=tmLoanReturnDetail){
			if(null!=tmLoanReturnDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLoanReturnDetail.getId()));
			}
			if(null!=tmLoanReturnDetail.getTmLoanReturn()){
				
				detachedCriteria.add(Restrictions.eq("tmLoanReturn.id", tmLoanReturnDetail.getTmLoanReturn().getId()));
			}
		
			if(null!=tmLoanReturnDetail.getIsReturn()){
				detachedCriteria.add(Restrictions.eq("isReturn",tmLoanReturnDetail.getIsReturn()));
			}
			
		} 
		return tmLoanReturnDetailDao.findByCriteria(detachedCriteria, tmLoanReturnDetail);
	}
	
	/**
	 * 批量添加借出归还详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时减少配件表的借出数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchloanDetail(TmLoanReturn tmLoanReturn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//保存归还主表
			tmLoanReturnDao.insert(tmLoanReturn);
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String loanRegDtlId = parts.split(":")[0];						//借出登记详细ID
				String loanBill = parts.split(":")[1];							//借出单号
				String lackReturnQuantity = parts.split(":")[2];				//未还数量
				String returnQuantity = parts.split(":")[3];					//本次归还数量
				String costPrice  = parts.split(":")[4];						//归还时的成本价
				//保存归还详细
				TmLoanReturnDetail lrd = new TmLoanReturnDetail();
				lrd.setTmLoanReturn(tmLoanReturn);
				TmLoanRegisterDetail loanRegisterDetail = tmLoanRegisterDetailDao.findById(new Long(loanRegDtlId));
				lrd.setTmLoanRegisterDetail(loanRegisterDetail);
				lrd.setLoanRegBill(loanBill);
				lrd.setLackReturnQuantity(new Double(lackReturnQuantity));
				lrd.setCostPrice(new Double(costPrice));
				lrd.setReturnQuantity(new Double(returnQuantity));
				lrd.setIsReturn(Constants.NOT_RETURN);
				
				//保存归还详细
				tmLoanReturnDetailDao.insert(lrd);
				
				//如果的确认状态，则减少配件借出量
				if(tmLoanReturn.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLoan(lrd,new Long(loanRegDtlId), new Double(returnQuantity));
					
			}
			
		}
	}
	
	/**
	 * 更新配件借出量(更新借出详细单都已归还状态)
	 * @Date      2010-6-3
	 * @Function  
	 * @param isReturn					是否更新详细单归还状态
	 * @param loanRegDtlId				借出登记详细Id
	 * @param lackReturnQuantity		本次归还数量	
	 * @throws MinusException 
	 */
	public void updatePartInfoLoan(TmLoanReturnDetail returnDetail,Long loanRegDtlId,Double currentQuantity) throws MinusException{
		boolean isReturn = false;
		/**更新归还明细**/
		//计算未还数量
		Double lackQuantity = returnDetail.getLackReturnQuantity() - returnDetail.getReturnQuantity();
		returnDetail.setLackReturnQuantity(lackQuantity);
		tmLoanReturnDetailDao.update(returnDetail);
		
		if(lackQuantity == 0){//都已归还
			returnDetail.setIsReturn(Constants.RETURN);
			isReturn = true;
		}else{
			returnDetail.setIsReturn(Constants.NOT_RETURN);
		}
		/**更新归还明细**/
		
		
		/**更新借出登记明细**/
		TmLoanRegisterDetail regDetial = tmLoanRegisterDetailDao.findById( loanRegDtlId);
		//设置归还数
		Double returnQuantity  = regDetial.getReturnQuantity()!= null?regDetial.getReturnQuantity():0D;
		regDetial.setReturnQuantity(returnQuantity+currentQuantity);
		//归还数量等于借出数量
		if(regDetial.getReturnQuantity().equals(regDetial.getLoanQuantity())){
			//更新借出登记状态 ,该项配件都已归还
			regDetial.setIsReturn(Constants.RETURN);
		}
		tmLoanRegisterDetailDao.update(regDetial);
		/**更新借出登记明细**/
		
		
		
		/**更新借出明细表 是否都已归还状态**/
		boolean allReturn = true;
		TmLoanRegister loanRegister = tmLoanRegisterService.findById(regDetial.getLoanId());
		TmLoanRegisterDetail queryEntity = new TmLoanRegisterDetail();
		queryEntity.setLoanId(loanRegister.getId());
		List<TmLoanRegisterDetail> loanRegDetials = tmLoanRegisterDetailService.findByEntity(queryEntity);
		for (TmLoanRegisterDetail detail : loanRegDetials) {
			if(detail.getIsReturn().equals(Constants.NOT_RETURN)){
				allReturn = false ;
				break;
			}
		}
		
		if(allReturn)
			loanRegister.setIsReturn(Constants.RETURN);
			tmLoanRegisterService.update(loanRegister);
		/**更新借出明细表 是否都已归还状态**/
			
			
		
		/**更新配件借出值**/
		TbPartInfo partInfo = tbPartInfoDao.findById(regDetial.getTbPartInfo().getId());
		//减去本次归还数
		Double newloanQ = partInfo.getLoanQuantity() - currentQuantity;
		partInfo.setLoanQuantity(newloanQ);
		boolean flag = tmDictionaryService.isMinusStockOnt();
		//不允许负出库
		if(flag == false && newloanQ < 0  )
			throw new MinusException();
		//更新日期
		partInfo.setLastModifyDate(new Date());
		tbPartInfoDao.update(partInfo);
		/**更新配件借出值**/
	}
	
	public void updateBathLRDetal(Long loanReturnId ) throws MinusException{
		TmLoanReturnDetail tmLoanReturnDetail = new TmLoanReturnDetail();
		TmLoanReturn tmLoanReturn = tmLoanReturnDao.findById(loanReturnId);
		tmLoanReturnDetail.setTmLoanReturn(tmLoanReturn);
		
		List<TmLoanReturnDetail> detaiList = this.findByEntity(tmLoanReturnDetail) ;
		
		for (TmLoanReturnDetail rd : detaiList){
			
			this.updatePartInfoLoan(rd, rd.getTmLoanRegisterDetail().getId(), rd.getReturnQuantity());
			
		}
	}
	
	
	public void updateBatchloanDetail(TmLoanReturn tmLoanReturn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//保存归还主表
			tmLoanReturnDao.update(tmLoanReturn);
			//删除修改前明细，增加修改后的明细
			tmLoanReturnDao.deleteLoanReturnDetail(tmLoanReturn.getId());
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String loanRegDtlId = parts.split(":")[0];						//借出登记详细ID
				String loanBill = parts.split(":")[1];							//借出单号
				String lackReturnQuantity = parts.split(":")[2];				//未还数量
				String returnQuantity = parts.split(":")[3];					//本次归还数量
				String costPrice  = parts.split(":")[4];						//归还时的成本价
				//保存归还详细
				TmLoanReturnDetail lrd = new TmLoanReturnDetail();
				lrd.setTmLoanReturn(tmLoanReturn);
				TmLoanRegisterDetail loanRegisterDetail = tmLoanRegisterDetailDao.findById(new Long(loanRegDtlId));
				lrd.setTmLoanRegisterDetail(loanRegisterDetail);
				lrd.setLoanRegBill(loanBill);
				lrd.setLackReturnQuantity(new Double(lackReturnQuantity));
				lrd.setCostPrice(new Double(costPrice));
				lrd.setReturnQuantity(new Double(returnQuantity));
				lrd.setIsReturn(Constants.NOT_RETURN);
				
				//保存归还详细
				tmLoanReturnDetailDao.insert(lrd);
				
				//如果的确认状态，则减少配件借出量
				if(tmLoanReturn.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLoan(lrd,new Long(loanRegDtlId), new Double(returnQuantity));
					
			}
			
		}
	}

	
}
