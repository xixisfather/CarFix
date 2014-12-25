package com.selfsoft.business.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.dao.ITmRemoveStockDao;
import com.selfsoft.business.dao.ITmRemoveStockDetailDao;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.model.TmRemoveStockDetail;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITmRemoveStockDetailService;
import com.selfsoft.common.exception.MinusException;
@Service("tmRemoveStockDetailService")
public class TmRemoveStockDetailServiceImpl implements
		ITmRemoveStockDetailService {

	@Autowired
	private ITmRemoveStockDetailDao tmRemoveStockDetailDao;
	@Autowired
	private ITmRemoveStockDao tmRemoveStockDao;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	
	
	
	public List<TmRemoveStockDetail> findByEntity(TmRemoveStockDetail tmRemoveStockDetail){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmRemoveStockDetail.class);
		if(null!=tmRemoveStockDetail){
			if(null!=tmRemoveStockDetail.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmRemoveStockDetail.getId()));
			}
			if(null!=tmRemoveStockDetail.getRemoveStockId()){
				detachedCriteria.add(Restrictions.eq("removeStockId", tmRemoveStockDetail.getRemoveStockId()));
			}
		
		} 
		return tmRemoveStockDetailDao.findByCriteria(detachedCriteria, tmRemoveStockDetail);
	}
	
	/**
	 * 批量添加移库出仓详细内容，传入String以逗号分隔为每个配件，以冒号分隔配件具体信息
	 * 每个配件一但出库，并及时减少配件表的库存数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public void insertBatchRemoveStockDetail(TmRemoveStock tmRemoveStock,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//保存移出配件出仓主表
			tmRemoveStockDao.insert(tmRemoveStock);
			//保存明细
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//出库数量
				String price  = parts.split(":")[2];						//成本价
				
				

				this.vaildPartStoreQuantity(new Long(tbPartInfoId), new Double(num));
				
				
				//详细移库出仓
				TmRemoveStockDetail trsd = new TmRemoveStockDetail();
				trsd.setPartinfoId(new Long(tbPartInfoId));;
				trsd.setQuantity(new Double(num));
				trsd.setCostPrice(new Double(price));
				trsd.setRemoveStockId(tmRemoveStock.getId());
				tmRemoveStockDetailDao.insert(trsd);
			}
		}
	}
	
	
	public void updateBatchRemoveStockDetail(TmRemoveStock tmRemoveStock,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//更新移出配件出仓主表
			tmRemoveStockDao.update(tmRemoveStock);
			//删除原先的明细，再添加新增的明细
			tmRemoveStockDao.deleteTmRemoveStockDetail(tmRemoveStock.getId());
			
			//保存明细
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//出库数量
				String price  = parts.split(":")[2];						//成本价
				
				this.vaildPartStoreQuantity(new Long(tbPartInfoId), new Double(num));
				
				//详细移库出仓
				TmRemoveStockDetail trsd = new TmRemoveStockDetail();
				trsd.setPartinfoId(new Long(tbPartInfoId));;
				trsd.setQuantity(new Double(num));
				trsd.setCostPrice(new Double(price));
				trsd.setRemoveStockId(tmRemoveStock.getId());
				tmRemoveStockDetailDao.insert(trsd);
			}
		}
	}
	
	/**
	 * 验证移出数量是否大于库存数量
	 * @Date      2010-6-21
	 * @Function  
	 * @param partInfoId
	 * @param currQuantity
	 * @throws MinusException
	 */
	public void vaildPartStoreQuantity(Long partInfoId,Double currQuantity) throws MinusException{
		TbPartInfo partInfo = tbPartInfoService.findById(partInfoId);
		Double partQuantity  = partInfo.getStoreQuantity() != null ? partInfo.getStoreQuantity() : 0D;
		Double newStoreQuantity = partQuantity- currQuantity;
		boolean flag = tmDictionaryService.isMinusStockOnt();
		if(flag == false && newStoreQuantity < 0  )
			throw new MinusException();
	}
}
