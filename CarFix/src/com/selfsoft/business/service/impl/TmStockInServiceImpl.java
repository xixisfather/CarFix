package com.selfsoft.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.business.dao.ITmStockInDao;
import com.selfsoft.business.dao.ITmStockOutDao;
import com.selfsoft.business.dao.ITmStockinDetailDao;
import com.selfsoft.business.dao.ITmStockoutDetailDao;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.model.TmStockinDetail;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;

@Service("tmStockInService")
public class TmStockInServiceImpl implements ITmStockInService {
	
	@Autowired
	private ITmStockInDao tmStockInDao;
	@Autowired
	private ITmStockinDetailDao tmStockinDetailDao;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	
	
	public void insert(TmStockIn tmStockIn){
		tmStockInDao.insert(tmStockIn);
	}
	
	public TmStockIn findById(Long id){
		return tmStockInDao.findById(id);
	}
	
	public void update(TmStockIn tmStockIn){
		tmStockInDao.update(tmStockIn);
	}
	
	public List<TmStockIn> findByEntity(TmStockIn tmStockIn) {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TmStockIn.class);
		if(null!=tmStockIn){
			if(null!=tmStockIn.getId()){
				detachedCriteria.add(Restrictions.eq("id", tmStockIn.getId()));
			}
			if(null!=tmStockIn.getStockInCode()){
				detachedCriteria.add(Restrictions.like("stockInCode","%"+ tmStockIn.getStockInCode()+"%"));
			}
		
			if(null!=tmStockIn.getInType()){
				detachedCriteria.add(Restrictions.eq("inType",tmStockIn.getInType()));
			}
			if(null!=tmStockIn.getIsConfirm()){
				detachedCriteria.add(Restrictions.eq("isConfirm",tmStockIn.getIsConfirm()));
			}
		} 
		return tmStockInDao.findByCriteria(detachedCriteria, tmStockIn);
	}
	
	public void updateBathTmStockInDetail(TmStockIn tmStockIn,String partCol) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			//更新入库主表
			tmStockInDao.update(tmStockIn);
			//先删除明细，在加入新的明细
			tmStockInDao.deleteStockInDetail(tmStockIn.getId());
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				if(StringUtils.isBlank(parts) || parts.split(":").length < 3) continue;
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String num = parts.split(":")[1];							//数量
				String price  = parts.split(":")[2];						//税前单价
				
				TmStockinDetail detial = new TmStockinDetail();
				detial.setPartinfoId(new Long(tbPartInfoId));
				detial.setPrice(new Double(price));
				detial.setQuantity(new Double(num));
				detial.setStockId(tmStockIn.getId());
				tmStockinDetailDao.insert(detial);
				
				if(parts.split(":").length > 3){							//销售退货特殊处理
					String stockOutDetailId = parts.split(":")[3];			//销售出库明细ID
					detial.setStockoutDetailId(new Long(stockOutDetailId));
					detial.setPrice(new Double(price));						//销售价
					
					//保存销售退货明细
					tmStockinDetailDao.insert(detial);
					
					
					//加配件库存，不计算配件的成本价(用于销售退货模块)
					if(tmStockIn.getIsConfirm().equals(Constants.CONFIRM)){
						//更新销售退货时 销售出库明细表，主表数量
						tmStockinDetailService.updateSellReturnQuantity(tmStockIn, new Long(stockOutDetailId),new Double(num));
						//更新配件数量
						tmStockinDetailService.updateTbPartInfoNocprice(new Long(tbPartInfoId),new Double(num));
					
						continue;
					}
				}
				
				
				//更新配件数量，更计算成本价
				if(tmStockIn.getIsConfirm().equals(Constants.CONFIRM))
					tmStockinDetailService.partInfoStorkIn(new Long(tbPartInfoId), new Double(num),new Double(price));
			}
			
		}
	}
	
	/**
	 * 删除所有入库类型单据
	 * @param stockInId
	 * @return
	 */
	public boolean deleteStockIn(Long stockInId){
		//删除入库明细表
		tmStockInDao.deleteStockInDetail(stockInId);
		//删除入库主表
		boolean flag = tmStockInDao.deleteById(stockInId);
		
		return flag;
	}
	
	
	/**
	 * 主单据查询列表
	 * @return
	 */
	public List<TmStockInVo> getMasterStockIn(Long stockInType,String isConfirms,TmStockIn tmStockIn){
		
		List<TmStockInVo> result = new ArrayList<TmStockInVo>();
		List<Object[]> objs = tmStockinDetailDao.getMasterStockIn(stockInType, isConfirms, tmStockIn);
		
		for(Object[] obj : objs){
			String stockInCode =  obj[0]!=null?obj[0].toString():null;
			String dateStr = obj[1]!=null?obj[1].toString():null;
			Date arriveDate = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			Double totalPrice = obj[2]!=null?Double.valueOf(obj[2].toString()):0D;
			Long payMent =  obj[3]!=null?Long.valueOf(obj[3].toString()):null;
			String payMentShow  = "";
			if(payMent!=null)
				payMentShow = Constants.getPayMentMap().get(payMent);
			
			String customerCode  = obj[4]!=null?obj[4].toString():null;
			String customerName  = obj[5]!=null?obj[5].toString():null;
			Long stockInId = obj[6]!=null?new Long(obj[6].toString()):null;
			
			TmStockInVo vo = new TmStockInVo();
			vo.setStockInCode(stockInCode);
			vo.setArriveDate(arriveDate);
			vo.setTotalPrice(totalPrice);
			vo.setSupplierName(customerName);
			vo.setSupplierCode(customerCode);
			vo.setPayMent(payMent);
			vo.setPayMentShow(payMentShow);
			vo.setStockInId(stockInId);
			result.add(vo);
		}
		
		return result;
	}
	
	public List<TmStockInVo> getMasterAllStock(TmStockIn tmStockIn){
		List<TmStockInVo> result = new ArrayList<TmStockInVo>();
		List<Object[]> objs = tmStockinDetailDao.getMasterAllStock(tmStockIn);
		
		for(Object[] obj : objs){
			String stockInCode =  obj[0]!=null?obj[0].toString():null;
			String dateStr = obj[1]!=null?obj[1].toString():null;
			Date arriveDate = CommonMethod.parseStringToDate(dateStr, "yyyy-MM-dd"); 
			Double totalPrice = obj[2]!=null?Double.valueOf(obj[2].toString()):0D;
			Long payMent =  obj[3]!=null?Long.valueOf(obj[3].toString()):null;
			String payMentShow  = "";
			if(payMent!=null)
				payMentShow = Constants.getPayMentMap().get(payMent);
			
			String customerCode  = obj[4]!=null?obj[4].toString():null;
			String customerName  = obj[5]!=null?obj[5].toString():null;
			Long stockInId = obj[6]!=null?new Long(obj[6].toString()):null;
			
			
			TmStockInVo vo = new TmStockInVo();
			vo.setStockInCode(stockInCode);
			vo.setArriveDate(arriveDate);
			vo.setTotalPrice(totalPrice);
			vo.setSupplierName(customerName);
			vo.setSupplierCode(customerCode);
			vo.setPayMent(payMent);
			vo.setPayMentShow(payMentShow);
			vo.setStockInId(stockInId);
			result.add(vo);
		}
			
		return result;
	}
	
	/**
	 * 更新采购入库支付方式
	 * @param stockInId
	 * @param payMent
	 */
	public void updateStockPanMent(Long stockInId,Long payMent){
		
		TmStockIn tmStockIn  = this.findById(stockInId);
		
		tmStockIn.setPayMent(payMent);
		
		this.update(tmStockIn);
	}
}
