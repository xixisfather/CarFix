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
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.dao.ITmLianceRegisterDetailDao;
import com.selfsoft.business.dao.ITmLianceReturnDao;
import com.selfsoft.business.dao.ITmLianceReturnDetailDao;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceRegisterDetail;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLianceReturnDetail;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmLianceReturnDetailService;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
@Service("tmLianceReturnDetailService")
public class TmLianceReturnDetailServiceImpl implements
		ITmLianceReturnDetailService {

	@Autowired
	private ITmLianceReturnDetailDao tmLianceReturnDetailDao;
	@Autowired
	private ITmLianceRegisterDetailDao tmLianceRegisterDetailDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmLianceReturnDao tmLianceReturnDao;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITmLianceRegisterService tmLianceRegisterService;
	@Autowired
	private ITmLianceRegisterDetailService tmLianceRegisterDetailService;
	
	
	public List<TmLianceReturnDetail> findByEntity(TmLianceReturnDetail tmLianceReturnDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLianceReturnDetail.class);
		if(null!=tmLianceReturnDetail){
			if(null!=tmLianceReturnDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLianceReturnDetail.getId()));
			}
			
			if(null!=tmLianceReturnDetail.getIsReturn()){
				detachedCriteria.add(Restrictions.eq("isReturn",tmLianceReturnDetail.getIsReturn()));
			}
			
			if(null!=tmLianceReturnDetail.getTmLianceReturn() && null!=tmLianceReturnDetail.getTmLianceReturn().getId()){
				
				
				detachedCriteria.add(Restrictions.eq("tmLianceReturn.id", tmLianceReturnDetail.getTmLianceReturn().getId()));
			}
			
		} 
		return tmLianceReturnDetailDao.findByCriteria(detachedCriteria, tmLianceReturnDetail);
	}
	
	/**
	 * 批量添加借进归还详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时减少配件表的借进数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchLianceDetail(TmLianceReturn tmLianceReturn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//保存借进归还主表
			tmLianceReturnDao.insert(tmLianceReturn);
			
			String[] partArr = partCol.split(",");
			//保存借进归还明细表
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String lianceRegDtlId = parts.split(":")[0];					//借进登记详细ID
				String lianceBill = parts.split(":")[1];						//借进单号
				String lackReturnQuantity = parts.split(":")[2];				//未还数量
				String returnQuantity = parts.split(":")[3];					//本次归还数量
				String costPrice  = parts.split(":")[4];						//归还时的成本价
				//保存归还详细
				TmLianceReturnDetail lrd = new TmLianceReturnDetail();
				lrd.setTmLianceReturn(tmLianceReturn);
				TmLianceRegisterDetail tmLianceRegisterDetail = tmLianceRegisterDetailDao.findById(new Long(lianceRegDtlId));
				lrd.setTmLianceRegisterDetail(tmLianceRegisterDetail);
				lrd.setLianceBill(lianceBill);
				lrd.setLackReturnQuantity(new Double(lackReturnQuantity));
				lrd.setCostPrice(new Double(costPrice));
				lrd.setReturnQuantity(new Double(returnQuantity));
				lrd.setIsReturn(Constants.NOT_RETURN);
				
				//保存归还详细
				tmLianceReturnDetailDao.insert(lrd);
				
				//如果的确认状态，则减少配件借进量
				if(tmLianceReturn.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLiance(lrd,new Long(lianceRegDtlId), new Double(returnQuantity));
					
			}
			
		}
	}
	
	/**
	 * 更新配件借进量(更新借进详细单都已归还状态)
	 * @Date      2010-6-3
	 * @Function  
	 * @param isReturn					是否更新详细单归还状态
	 * @param lianceRegDtlId			借进登记详细Id
	 * @param currentQuantity		本次归还数量	
	 * @throws MinusException 
	 */
	public void updatePartInfoLiance(TmLianceReturnDetail returnDetail ,Long lianceRegDtlId,Double currentQuantity) throws MinusException{
		boolean isReturn = false;
		/**更新归还明细**/
		//计算未还数量
		Double lackQuantity = returnDetail.getLackReturnQuantity() - returnDetail.getReturnQuantity();
		returnDetail.setLackReturnQuantity(lackQuantity);
		tmLianceReturnDetailDao.update(returnDetail);
		
		if(lackQuantity == 0){//都已归还
			returnDetail.setIsReturn(Constants.RETURN);
			isReturn = true;
		}else{
			returnDetail.setIsReturn(Constants.NOT_RETURN);
		}
		/**更新归还明细**/
		
		
		
		/**更新借进登记明细**/
		TmLianceRegisterDetail regDetial = tmLianceRegisterDetailDao.findById( lianceRegDtlId);
		//已归还数
		Double returnQuantity  = regDetial.getReturnQuantity()!= null?regDetial.getReturnQuantity():0D;
		regDetial.setReturnQuantity(returnQuantity+currentQuantity);
		if(isReturn){
			//更新借进登记状态 ,该项配件都已归还
			regDetial.setIsReturn(Constants.RETURN);
		}
		tmLianceRegisterDetailDao.update(regDetial);
		/**更新借进登记明细**/
		
		
		
		/**更新借进明细表 是否都已归还状态**/
		boolean allReturn = true;
		TmLianceRegister lianceRegister = tmLianceRegisterService.findById(regDetial.getLianceId());
		TmLianceRegisterDetail queryEntity = new TmLianceRegisterDetail();
		queryEntity.setLianceId(lianceRegister.getId());
		List<TmLianceRegisterDetail> LianceRegDetials = tmLianceRegisterDetailService.findByEntity(queryEntity);
		for (TmLianceRegisterDetail detail : LianceRegDetials) {
			if(detail.getIsReturn().equals(Constants.NOT_RETURN)){
				allReturn = false ;
				break;
			}
		}
		
		if(allReturn)
			lianceRegister.setIsReturn(Constants.RETURN);
			tmLianceRegisterService.update(lianceRegister);
		/**更新借进明细表 是否都已归还状态**/
		
		
		
		/**更新配件借进值**/
		TbPartInfo partInfo = tbPartInfoDao.findById(regDetial.getTbPartInfo().getId());
		//减去本次归还数
		Double newLianceQ = partInfo.getLianceQuantity() - currentQuantity;
		boolean flag = tmDictionaryService.isMinusStockOnt();
		//不允许负出库
		if(flag == false && newLianceQ < 0  )
			throw new MinusException();
		
		partInfo.setLianceQuantity(newLianceQ);
		//更新日期
		partInfo.setLastModifyDate(new Date());
		tbPartInfoDao.update(partInfo);
		/**更新配件借进值**/ 
	}
	
	
	public void updateBathLRDetal(Long lianceReturnId ) throws MinusException{
		TmLianceReturnDetail tmLianceReturnDetail = new TmLianceReturnDetail();
		TmLianceReturn lianceReturn = tmLianceReturnDao.findById(lianceReturnId);
		tmLianceReturnDetail.setTmLianceReturn(lianceReturn);
		List<TmLianceReturnDetail> detaiList = this.findByEntity(tmLianceReturnDetail) ;
		
		for (TmLianceReturnDetail rd : detaiList){
			this.updatePartInfoLiance(rd , rd.getTmLianceRegisterDetail().getId(), rd.getReturnQuantity());
			
		}
	}
	
	/**
	 * 更新借进归还
	 */
	public void updateBatchLianceDetail(TmLianceReturn tmLianceReturn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//更新借进归还主表
			tmLianceReturnDao.update(tmLianceReturn);
			//删除修改前明细，增加修改后的明细
			tmLianceReturnDao.deleteLianceReturnDetail(tmLianceReturn.getId());
			
			String[] partArr = partCol.split(",");
			//保存借进归还明细表
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String lianceRegDtlId = parts.split(":")[0];					//借进登记详细ID
				String lianceBill = parts.split(":")[1];						//借进单号
				String lackReturnQuantity = parts.split(":")[2];				//未还数量
				String returnQuantity = parts.split(":")[3];					//本次归还数量
				String costPrice  = parts.split(":")[4];						//归还时的成本价
				//保存归还详细
				TmLianceReturnDetail lrd = new TmLianceReturnDetail();
				lrd.setTmLianceReturn(tmLianceReturn);
				TmLianceRegisterDetail tmLianceRegisterDetail = tmLianceRegisterDetailDao.findById(new Long(lianceRegDtlId));
				lrd.setTmLianceRegisterDetail(tmLianceRegisterDetail);
				lrd.setLianceBill(lianceBill);
				lrd.setLackReturnQuantity(new Double(lackReturnQuantity));
				lrd.setCostPrice(new Double(costPrice));
				lrd.setReturnQuantity(new Double(returnQuantity));
				lrd.setIsReturn(Constants.NOT_RETURN);
				
				
				//保存归还详细
				tmLianceReturnDetailDao.insert(lrd);
				
				//如果的确认状态，则减少配件借进量
				if(tmLianceReturn.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLiance(lrd,new Long(lianceRegDtlId), new Double(returnQuantity));
					
			}
			
		}
	}
}
