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
import com.selfsoft.business.dao.ITmLianceRegisterDao;
import com.selfsoft.business.dao.ITmLianceRegisterDetailDao;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceRegisterDetail;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.framework.common.Constants;
@Service("tmLianceRegisterDetailService")
public class TmLianceRegisterDetailServiceImpl implements
		ITmLianceRegisterDetailService {
	
	@Autowired
	private ITmLianceRegisterDetailDao tmLianceRegisterDetailDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmLianceRegisterDao tmLianceRegisterDao;
	
	public List<TmLianceRegisterDetail> findByEntity(TmLianceRegisterDetail tmLianceRegisterDetail) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmLianceRegisterDetail.class);
		if(null!=tmLianceRegisterDetail){
			if(null!=tmLianceRegisterDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmLianceRegisterDetail.getId()));
			}
			if(null!=tmLianceRegisterDetail.getLianceId()){
				detachedCriteria.add(Restrictions.eq("lianceId", tmLianceRegisterDetail.getLianceId()));
			}
		
			if(null!=tmLianceRegisterDetail.getIsReturn()){
				detachedCriteria.add(Restrictions.eq("isReturn",tmLianceRegisterDetail.getIsReturn()));
			}
		} 
		return tmLianceRegisterDetailDao.findByCriteria(detachedCriteria, tmLianceRegisterDetail);
	}
	
	/**
	 * 批量添加借进登记详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但入库，并及时增加配件表的借进数量
	 */
	public void insertBatchLianceDetail(Long isConfirm ,Long lianceRegId,String partCol){
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String lianceQuantity = parts.split(":")[1];				//数量
				String oldCostPrice = parts.split(":")[2];					//原成本价
				String price  = parts.split(":")[3];						//进货价
				//保存借进详细
				TmLianceRegisterDetail tlrd = new TmLianceRegisterDetail();
				tlrd.setIsReturn(0L);										//第一次登记借进，一定是未归还
				tlrd.setLianceId(lianceRegId);								//借进单ID
				TbPartInfo tbPartInfo = tbPartInfoDao.findById(new Long(tbPartInfoId));
				tlrd.setTbPartInfo(tbPartInfo);								//配件
				tlrd.setLiancePrice(new Double(price));
				tlrd.setLianceQuantity(new Double(lianceQuantity));
				tlrd.setOldCostPrice(new Double(oldCostPrice));
				tlrd.setReturnQuantity(0D);									//第一次登记借进，一定没有归还数量
				tmLianceRegisterDetailDao.insert(tlrd);
				
				//如果的确认状态，则增加配件借进量
				if(isConfirm == 8002)
					updatePartInfoLinance(new Long(tbPartInfoId), new Double(lianceQuantity));
			}
			
		}
	}
	
	
	/**
	 * 更新配件的借进量
	 * @Date      2010-6-2
	 * @Function  
	 * @param partInfoId
	 * @param lianceQuantity
	 */
	public void updatePartInfoLinance(Long partInfoId ,Double lianceQuantity ){
		TbPartInfo tpi = tbPartInfoDao.findById(partInfoId);
		//原借进量
		Double oldlianceQuantity = tpi.getLianceQuantity()!=null?tpi.getLianceQuantity():0D;
		//借进量累加
		tpi.setLianceQuantity(oldlianceQuantity+lianceQuantity);
		//更新日期
		tpi.setLastModifyDate(new Date());
		tbPartInfoDao.update(tpi);
	}
	
	
	/**
	 * 更新借进登记单下所有配件借进量
	 * @Date      2010-6-1
	 * @Function  
	 * @param stockInId
	 */
	public void updateBatchPartInfoLiance(Long lianceId){
		
		TmLianceRegisterDetail lrDetail = new TmLianceRegisterDetail();
		lrDetail.setLianceId(lianceId);
		List<TmLianceRegisterDetail> lianceRegisterDetails = this.findByEntity(lrDetail);
		for(TmLianceRegisterDetail detail : lianceRegisterDetails){
			this.updatePartInfoLinance(detail.getTbPartInfo().getId(), detail.getLianceQuantity());
		}
		
	}
	
	
	
	public void updateBatchLianceDetail(TmLianceRegister tmLianceRegister,String partCol){
		if(StringUtils.isNotBlank(partCol)){
			//更新借进登记主表
			tmLianceRegisterDao.update(tmLianceRegister);
			//删除修改前明细，增加修改后的明细
			tmLianceRegisterDao.deleteLianceRegDetail(tmLianceRegister.getId());
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String lianceQuantity = parts.split(":")[1];				//数量
				String oldCostPrice = parts.split(":")[2];					//原成本价
				String price  = parts.split(":")[3];						//进货价
				//保存借进详细
				TmLianceRegisterDetail tlrd = new TmLianceRegisterDetail();
				tlrd.setIsReturn(0L);										//第一次登记借进，一定是未归还
				tlrd.setLianceId(tmLianceRegister.getId());					//借进单ID
				TbPartInfo tbPartInfo = tbPartInfoDao.findById(new Long(tbPartInfoId));
				tlrd.setTbPartInfo(tbPartInfo);
				tlrd.setLiancePrice(new Double(price));
				tlrd.setLianceQuantity(new Double(lianceQuantity));
				tlrd.setOldCostPrice(new Double(oldCostPrice));
				tlrd.setReturnQuantity(0D);									//第一次登记借进，一定没有归还数量
				tmLianceRegisterDetailDao.insert(tlrd);
				
				//如果的确认状态，则增加配件借进量
				if(tmLianceRegister.getIsConfirm().equals(Constants.CONFIRM))
					updatePartInfoLinance(new Long(tbPartInfoId), new Double(lianceQuantity));
			}
			
		}
	}
}
