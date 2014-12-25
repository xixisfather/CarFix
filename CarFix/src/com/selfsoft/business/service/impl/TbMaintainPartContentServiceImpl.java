package com.selfsoft.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfsoft.baseinformation.dao.ITbPartInfoDao;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseparameter.model.TmProjectType;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.business.dao.ITbMaintainPartContentDao;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmStockoutDetail;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

@Service("tbMaintainPartContentService")
public class TbMaintainPartContentServiceImpl implements
		ITbMaintainPartContentService {
	
	@Autowired
	private ITbMaintainPartContentDao tbMaintainPartContentDao;
	@Autowired
	private ITbPartInfoDao tbPartInfoDao;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;

	public Double getMaintainPartContentTotalPrice(String maintainCode){
		
		List<TbMaintianVo> maintianvos = this.getTbMaintianDetailVos(maintainCode);
		Double result = 0D;
		for(TbMaintianVo vo : maintianvos){
			result += vo.getPartQuantity() * vo.getPrice();
			
		}
		
		BigDecimal   b   =   new   BigDecimal(result);  
		result   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();  
		
		return result;
	}
	
	
	public List<TbMaintainPartContent> findByEntity(TbMaintainPartContent tbMaintainPartContent){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbMaintainPartContent.class);
		if(null!=tbMaintainPartContent){
			if(null!=tbMaintainPartContent.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbMaintainPartContent.getId()));
			}
			if(StringUtils.isNotBlank(tbMaintainPartContent.getMaintainCode())){
				detachedCriteria.add(Restrictions.eq("maintainCode", tbMaintainPartContent.getMaintainCode()));
			}
			if(null!=tbMaintainPartContent.getEntrustId()){
				detachedCriteria.add(Restrictions.eq("entrustId", tbMaintainPartContent.getEntrustId()));
			}
			
			if(tbMaintainPartContent.getBalanceId() != null){
				detachedCriteria.add(Restrictions.eq("balanceId", tbMaintainPartContent.getBalanceId()));
			}else{
				detachedCriteria.add(Restrictions.isNull("balanceId"));
			}
			
			
		} 
		return tbMaintainPartContentDao.findByCriteria(detachedCriteria, tbMaintainPartContent);
		
		
	}
	
	public List<TbMaintainPartContent> findByEntity(TbMaintainPartContent tbMaintainPartContent,Long balanceType){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbMaintainPartContent.class);
		if(null!=tbMaintainPartContent){
			if(null!=tbMaintainPartContent.getId()){
				detachedCriteria.add(Restrictions.eq("id", tbMaintainPartContent.getId()));
			}
			if(StringUtils.isNotBlank(tbMaintainPartContent.getMaintainCode())){
				detachedCriteria.add(Restrictions.eq("maintainCode", tbMaintainPartContent.getMaintainCode()));
			}
			if(null!=tbMaintainPartContent.getEntrustId()){
				detachedCriteria.add(Restrictions.eq("entrustId", tbMaintainPartContent.getEntrustId()));
			}
			
			if(tbMaintainPartContent.getBalanceId() != null){
				detachedCriteria.add(Restrictions.eq("balanceId", tbMaintainPartContent.getBalanceId()));
			}else{
				if(balanceType.equals(Constants.BALANCE_ALL)){
					//得到所有明细 无论是否结算都取出来
				}
				if(balanceType.equals(Constants.BALANCE_ISNULL)){
					//得到未结算的明细
					detachedCriteria.add(Restrictions.isNull("balanceId"));
				}
				if(balanceType.equals(Constants.BALANCE_NOTNULL)){
					//得到已结算的明细
					detachedCriteria.add(Restrictions.isNotNull("balanceId"));
				}
			}
			
			
		} 
		return tbMaintainPartContentDao.findByCriteria(detachedCriteria, tbMaintainPartContent);
		
		
	}
	
	/**
	 * 根据字符串格式批量插入维修发料 根据字符串格式批量插入维修发料
	 * @param partCol
	 * @param totalPrice
	 * @param flag 是否更新配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String batchInsertMaintain(String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException{
		if(StringUtils.isNotBlank(partCol)){
			
			
			
			String[] partArr = partCol.split(",");
			String code = tmDictionaryService.GenerateCode(StockTypeElements.MAINTAIN.getElementString());
			for (String parts : partArr) {
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String partQuantity = parts.split(":")[1];					//配件数量
				String price = parts.split(":")[2];							//发料单价
				String cliamPartPersonId = parts.split(":")[3];				//领用人
				String isFree = parts.split(":")[4];						//免费标志
				/*
				String zl = ""; 
				if(parts.split(":").length >=6){
					zl = parts.split(":")[5];							//账类
				}
						
				String xmlx = ""; 
				if(parts.split(":").length >=7){
					xmlx = parts.split(":")[6];//项目类型
				}
				*/
				String projectTypeId = parts.split(":")[5];
				String zl = "";
				String xmlx = "";
				String projectType = "";
				if(!projectTypeId.equals("null")){
					
					TmProjectType tmProjectType = tmProjectTypeService.findById(new Long(projectTypeId));
					zl = tmProjectType.getZl();
					xmlx = tmProjectType.getXmlx();
					projectType = tmProjectType.getProjectType();
				}
				String isPrint = parts.split(":")[6];
				
				TbMaintainPartContent maintainContent = new TbMaintainPartContent();
				maintainContent.setMaintainCode(code);
				maintainContent.setPartId(new Long(tbPartInfoId));
				maintainContent.setPartQuantity(new Double(partQuantity));
				maintainContent.setPrice(new Double(price));
				maintainContent.setCliamPartPersonId(StringUtils.isNotBlank(cliamPartPersonId)?new Long(cliamPartPersonId):userId);
				maintainContent.setEntrustId(entrustId);
				maintainContent.setTotalPrice(totalPrice);
				maintainContent.setIsConfirm(isConfirm);
				maintainContent.setIsFree(new Long(isFree));
				maintainContent.setStockOutDate(new Date());
				maintainContent.setProjectType(projectType);
				maintainContent.setXmlx(xmlx);
				maintainContent.setZl(zl);
				maintainContent.setIsPrint(isPrint);
				tbMaintainPartContentDao.insert(maintainContent);
				
			
				//减少库存数量
				if(isConfirm.equals(Constants.CONFIRM)){
					/**设置委托书已发料状态**/
					TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
					tbFixEntrustService.updateTbFixEntrustHasSendPart(tbFixEntrust.getEntrustCode());
					/**设置委托书已发料状态**/
					
					this.updatePartInfoQuantity(new Long(tbPartInfoId), new Double(partQuantity));
				}
			}
			
			return code;
			
		}
		
		return null;
	}
	
	
	/**
	 * 更新配件库存
	 * @param partId
	 * @param quantity
	 * @throws MinusException 
	 */
	public void updatePartInfoQuantity(Long partId,Double quantity) throws MinusException{
		TbPartInfo tbPartInfo = tbPartInfoDao.findById(partId);
		Double sourceQuantity = tbPartInfo.getStoreQuantity()!= null?tbPartInfo.getStoreQuantity():0D;
		Double newQuantity = sourceQuantity - quantity;
		tbPartInfo.setStoreQuantity(newQuantity);
		Double stockMoney = CommonMethod.convertRadixPoint(tbPartInfo.getCostPrice()*newQuantity,2);
		tbPartInfo.setStockMoney(Math.abs(stockMoney));
		//检查安全库存
		checkPartInfoStockQuantity(tbPartInfo, newQuantity);
		boolean flag = tmDictionaryService.isMinusStockOnt();
		if(flag == false && newQuantity < 0  )
			throw new MinusException();
		tbPartInfoDao.update(tbPartInfo);
		
	}
	
	/**
	 * 检查安全库存
	 * @param tbPartInfo
	 * @param newStoreQuantity
	 * @throws MinusException
	 */
	private void checkPartInfoStockQuantity(TbPartInfo tbPartInfo , Double newStoreQuantity) throws MinusException{
		Double minStoreQuantity = tbPartInfo.getMinStoreQuantity();
		Double maxStoreQuantity = tbPartInfo.getMaxStoreQuantity();
		
		if(minStoreQuantity!=null && minStoreQuantity !=0 ){
			
			if(newStoreQuantity < minStoreQuantity ){
				throw new MinusException("配件："+tbPartInfo.getPartName()+"最小安全库存为"+minStoreQuantity+",无法出库");
			}
		}
		
		if(maxStoreQuantity!=null && maxStoreQuantity !=0 ){
			
			if(newStoreQuantity > maxStoreQuantity ){
				throw new MinusException("配件："+tbPartInfo.getPartName()+"最大安全库存为"+maxStoreQuantity+",无法出库");
			}
		}
		
	}
	
	/**
	 * 更新维修配件的确认状态
	 * @Date      2010-6-22
	 * @Function  
	 * @param maintainCode
	 * @throws MinusException 
	 */
	public void updateMaintainState(String maintainCode) throws MinusException{
		TbMaintainPartContent queryEntity  = new TbMaintainPartContent();
		queryEntity.setMaintainCode(maintainCode);
		List<TbMaintainPartContent> mainContents = this.findByEntity(queryEntity);
		for(TbMaintainPartContent conent : mainContents){
			conent.setIsConfirm(Constants.CONFIRM);
			conent.setStockOutDate(new Date());
			tbMaintainPartContentDao.update(conent);
			this.updatePartInfoQuantity(conent.getPartId(), conent.getPartQuantity());
		}
	}

	public List<TbMaintianVo> getTbMaintianVos(String isConfirms,
			TbMaintainPartContent queryEntity) {
		return tbMaintainPartContentDao.getTbMaintianVos(isConfirms, queryEntity);
	}
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode){
		
		 List<TbMaintianVo> result = 
			 tbMaintainPartContentDao.getTbMaintianDetailVos(maintainCode,null,null,null);
		 
		 for(TbMaintianVo maintain: result){
			 TbMaintainPartContent maintainPartContent = tbMaintainPartContentDao.findById(maintain.getTbMaintainId());
			 TbFixEntrust  tbfixEntrust = tbFixEntrustService.findById(maintainPartContent.getEntrustId());
			 String userServiceName = tbfixEntrust.getTmUser().getUserName();
			 maintain.setUserServiceName(userServiceName);
			 maintain.setStockoutDate(maintainPartContent.getStockOutDate());
		 }
		 
		 return result;
	}
	
	/**
	 * 委托书ID 下维修发料明细
	 * @Date      2010-6-29
	 * @Function  
	 * @param entrustId
	 * @return
	 */
	public List<TbMaintianVo> getTbMaintianDetailVosByEntrustId(Long entrustId,Long balanceType){
		TbMaintainPartContent queryEntity = new TbMaintainPartContent();
		queryEntity.setEntrustId(entrustId);
		List<TbMaintainPartContent> list = this.findByEntity(queryEntity,balanceType);
		
		List<TbMaintianVo> result = new ArrayList<TbMaintianVo>();
		
		if(null!=list&&list.size()>0){
			result = tbMaintainPartContentDao.getTbMaintianDetailVos(list.get(0).getMaintainCode(),null,null,balanceType);
		
		}	
		return result;
	}
	
	
	public void updateMaintain(String maintainCode ,String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException{
		
		tbMaintainPartContentDao.deleteTbMaintain(maintainCode);
		
		if(StringUtils.isNotBlank(partCol)){
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String partQuantity = parts.split(":")[1];					//配件数量
				String price = parts.split(":")[2];							//发料单价
				String cliamPartPersonId = parts.split(":")[3];				//领用人
				String isFree = parts.split(":")[4];						//免费标志
				
				String projectTypeId = parts.split(":")[5];
				String zl = "";
				String xmlx = "";
				String projectType = "";
				if(!projectTypeId.equals("null")){
					
					TmProjectType tmProjectType = tmProjectTypeService.findById(new Long(projectTypeId));
					zl = tmProjectType.getZl();
					xmlx = tmProjectType.getXmlx();
					projectType = tmProjectType.getProjectType();
				}
				
				String isPrint = parts.split(":")[6];
				
				TbMaintainPartContent maintainContent = new TbMaintainPartContent();
				maintainContent.setMaintainCode(maintainCode);
				maintainContent.setPartId(new Long(tbPartInfoId));
				maintainContent.setPartQuantity(new Double(partQuantity));
				maintainContent.setPrice(new Double(price));
				maintainContent.setCliamPartPersonId(StringUtils.isNotBlank(cliamPartPersonId)?new Long(cliamPartPersonId):userId);
				maintainContent.setEntrustId(entrustId);
				maintainContent.setTotalPrice(totalPrice);
				maintainContent.setIsConfirm(isConfirm);
				maintainContent.setIsFree(new Long(isFree));
				maintainContent.setStockOutDate(new Date());
				maintainContent.setZl(zl);
				maintainContent.setXmlx(xmlx);
				maintainContent.setProjectType(projectType);
				maintainContent.setIsPrint(isPrint);
				tbMaintainPartContentDao.insert(maintainContent);
				
				
				//减少库存数量
				if(isConfirm.equals(Constants.CONFIRM)){
					/**设置委托书已发料状态**/
					TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
					tbFixEntrustService.updateTbFixEntrustHasSendPart(tbFixEntrust.getEntrustCode());
					/**设置委托书已发料状态**/
					
					this.updatePartInfoQuantity(new Long(tbPartInfoId), new Double(partQuantity));
				}
			}
			
			
		}
		
	}
	
	public boolean deleteTbMaintain(String maintainCode){
		TbMaintainPartContent queryEntity = new TbMaintainPartContent();
		queryEntity.setMaintainCode(maintainCode);
		List<TbMaintainPartContent> maintainContent = this.findByEntity(queryEntity);
		Long entrustId = maintainContent.get(0).getEntrustId();
		
		/**设置委托书未发料状态**/
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
		tbFixEntrust.setEntrustStatus(Constants.NOFINISH);
		tbFixEntrustService.update(tbFixEntrust);
		/**设置委托书未发料状态**/
		return tbMaintainPartContentDao.deleteTbMaintain(maintainCode);
	}
	
	
	public void updateConfirmMaintain(String maintainCode ,String partCol,Double totalPrice,Long entrustId,Long isConfirm,Long userId) throws NumberFormatException, MinusException{
		
		if(StringUtils.isNotBlank(partCol)){
			TbMaintainPartContent queryEntity = new TbMaintainPartContent();
			queryEntity.setMaintainCode(maintainCode);
			List<TbMaintainPartContent> oldTbMaintains = this.findByEntity(queryEntity);
			for(TbMaintainPartContent content : oldTbMaintains){
				content.setTotalPrice(totalPrice);
				tbMaintainPartContentDao.update(content);
			}
			
			
			String[] partArr = partCol.split(",");
			for (String parts : partArr) {
				
				if(parts.split(":")[1].equals("old")){
					updateOldMaintain(parts);
					continue;
				}
				
				String tbPartInfoId = parts.split(":")[0];					//配件id
				String partQuantity = parts.split(":")[1];					//配件数量
				String price = parts.split(":")[2];							//发料单价
				String cliamPartPersonId = parts.split(":")[3];				//领用人
				String isFree = parts.split(":")[4];						//免费标志
//				String zl = ""; 
//				if(parts.split(":").length >=6){
//					zl = parts.split(":")[5];							//账类
//				}
//						
//				String xmlx = ""; 
//				if(parts.split(":").length >=7){
//					xmlx = parts.split(":")[6];//项目类型
//				}
//				String projectType = parts.split(":").length>=8?parts.split(":")[7]:"";
				String projectTypeId = parts.split(":")[5];
				String zl = "";
				String xmlx = "";
				String projectType = "";
				if(!projectTypeId.equals("null")){
					
					TmProjectType tmProjectType = tmProjectTypeService.findById(new Long(projectTypeId));
					zl = tmProjectType.getZl();
					xmlx = tmProjectType.getXmlx();
					projectType = tmProjectType.getProjectType();
				}
				String isPrint = parts.split(":")[6];
				
				TbMaintainPartContent maintainContent = new TbMaintainPartContent();
				maintainContent.setMaintainCode(maintainCode);
				maintainContent.setPartId(new Long(tbPartInfoId));
				maintainContent.setPartQuantity(new Double(partQuantity));
				maintainContent.setPrice(new Double(price));
				maintainContent.setCliamPartPersonId(StringUtils.isNotBlank(cliamPartPersonId)?new Long(cliamPartPersonId):userId);
				maintainContent.setEntrustId(entrustId);
				maintainContent.setTotalPrice(totalPrice);
				maintainContent.setZl(zl);
				maintainContent.setXmlx(xmlx);
				maintainContent.setProjectType(projectType);
				maintainContent.setIsPrint(isPrint);
				maintainContent.setIsConfirm(isConfirm);
				/**
				 * add by chenchunrong
				 */
				if("false".equals(isFree)){
					isFree = Constants.FREESYMBOL_NO + "";
				}
				maintainContent.setIsFree(new Long(isFree));
				maintainContent.setStockOutDate(new Date());
				tbMaintainPartContentDao.insert(maintainContent);
				
				
				//减少库存数量
				/**设置委托书已发料状态**/
				TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(entrustId);
				tbFixEntrustService.updateTbFixEntrustHasSendPart(tbFixEntrust.getEntrustCode());
				/**设置委托书已发料状态**/
				
				this.updatePartInfoQuantity(new Long(tbPartInfoId), new Double(partQuantity));
			}
			
			
		}
		
	}



	/**
	 * 更新维修发料单状态
	 * @Date      2010-6-28
	 * @param entrustId
	 * @Function  
	 * @param status
	 */
	public void updateTbMaintainStatus(Long entrustId , Long status){
		
		TbMaintainPartContent queryEntity = new TbMaintainPartContent();
		
		queryEntity.setEntrustId(entrustId);
		
		List<TbMaintainPartContent> maintaincontnets = this.findByEntity(queryEntity);
		
		if(null!=maintaincontnets&&maintaincontnets.size()>0){
			
			for(TbMaintainPartContent maintainPartContent : maintaincontnets ){
				
				maintainPartContent.setIsConfirm(status);
				
				tbMaintainPartContentDao.update(maintainPartContent);
			}
			
		}
		
		
	}
	
	/**
	 * 更新维修发料单状态
	 * @Date      2010-6-28
	 * @param entrustId
	 * @Function  
	 * @param status
	 */
	public void updateTbMaintainStatus(Long entrustId ,Long balanceId , Long status){
		
		/*
		TbMaintainPartContent queryEntity = new TbMaintainPartContent();
		
		queryEntity.setEntrustId(entrustId);
		
//		queryEntity.setBalanceId(balanceId);
		
		List<TbMaintainPartContent> maintaincontnets = this.findByEntity(queryEntity);
		
		if(null!=maintaincontnets&&maintaincontnets.size()>0){
			
			for(TbMaintainPartContent maintainPartContent : maintaincontnets ){
				
				maintainPartContent.setIsConfirm(status);
				maintainPartContent.setBalanceId(balanceId);
				tbMaintainPartContentDao.update(maintainPartContent);
			}
			
		}
		*/
	
		
		tbMaintainPartContentDao.updateTbMaintainBalanceId(balanceId, status, entrustId);
	}
	
	/**
	 * 根据主单ID或者单号 来更新明细中BALANCEID为NULL的为参数BALANCEID
	 * @Date      2010-6-29
	 * @Function  
	 * @param maintainCode
	 * @param balance
	 */
	public void updateMaintainDetailBalance(Long entrustId , Long balanceId){
		
		tbMaintainPartContentDao.updateMaintainDetailBalance(entrustId, balanceId);
	}
	
	/**
	 * 根据委托书号，结算ID 查询明细中LIST
	 * @Date      2010-6-29
	 * @Function  
	 * @param maintainCode
	 * @param balanceId
	 * @return
	 */
	public List<TbMaintianVo> getMaintainContentsByBalanceId(Long entrustId ,Long balanceId){
		
		List<TbMaintianVo> result = tbMaintainPartContentDao.getTbMaintianDetailVos(null, entrustId, balanceId,null);
		
		return result;
		
	}
	/**
	 * 根据委托书号，结算ID 返回明细记录的总金额
	 * @Date      2010-6-29
	 * @Function  
	 * @param maintainCode
	 * @param balanceId
	 * @return
	 */
	public Double getTotalPriceByBalanceId(Long entrustId ,Long balanceId ){
		Double result = 0D ;
		//List<TbMaintianVo> contents = this.getMaintainContentsByBalanceId(entrustId, balanceId);
		List<TbMaintianVo> contents = tbMaintainPartContentDao.getTbMaintianDetailVos(null, entrustId, balanceId,Constants.BALANCE_ISNULL);
		for(TbMaintianVo content : contents){
			if(content.getIsFree().equals(Constants.FREESYMBOL_NO)){
				result += content.getPartQuantity() * content.getPrice();
			}
			
		}
		
		BigDecimal   b   =   new   BigDecimal(result);  
		result   =   b.setScale(2,  BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return result;
	}
	
	public List<TbMaintainPartContent> getViewEntrustMaintianContent(Long entrustId){
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(TbMaintainPartContent.class);
		TbMaintainPartContent tbMaintainPartContent = new TbMaintainPartContent();
		tbMaintainPartContent.setEntrustId(entrustId);
		if(null!=tbMaintainPartContent.getEntrustId()){
			detachedCriteria.add(Restrictions.eq("entrustId", tbMaintainPartContent.getEntrustId()));
		}
		return tbMaintainPartContentDao.findByCriteria(detachedCriteria, tbMaintainPartContent);
	}
	
	
	/**
	 * 如果委托书是未发料的情况，将修改委托书的状态
	 * @param entrustCode
	 * @return
	 */
	public void updateNoMaintainNoReBalance(String entrustCode){
		
		String sql = "select sum(m.part_quantity) from tb_fix_Entrust fe , tb_maintain_part_content m where fe.id = m.entrust_id and fe.entrust_code = '"+entrustCode+"'";
		
		List<Object> listRes =  tbMaintainPartContentDao.findByOriginSql(sql, null);
		
		if(listRes!=null && listRes.size()>0){
			
			//Double quantity = Double.valueOf(listRes.get(0).toString());
			
			Double quantity = Double.valueOf(listRes.get(0)==null?"0.00":listRes.get(0).toString());
			
			if(quantity == 0D){
				
				TbFixEntrust tbFixEntrust = tbFixEntrustService.findByEntrustCode(entrustCode);
				
				tbFixEntrust.setEntrustStatus(Constants.NOTMAINTAINREBALANCE);
				
				tbFixEntrustService.update(tbFixEntrust);
			}
		}
			
	}
	
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
	 * 若销售价为0时则取配件的默认销售价
	 * @param partId
	 * @return
	 */
	public Double getCustomerSellPriceByPartId(Long partId,Long entrustId){
		
		TbFixEntrust tbFixEntrust = this.tbFixEntrustService.findById(entrustId);
		
		Double result =  tbMaintainPartContentDao.getCustomerSellPriceByPartId(partId, tbFixEntrust.getTbCustomer().getId());
		
		if(result == 0D)
			result = tbPartInfoDao.findById(partId).getSellPrice();
		
		return result;
		
	}
	
	/**
	 * 维修发料是否能修改     已结算状态不能修改 return false  其余 return true
	 * @param maintainCode
	 * @return
	 * @throws Exception
	 */
	public boolean isMaintainEdit(String maintainCode){
		TbMaintainPartContent tbMaintainPartContent = new TbMaintainPartContent();
		tbMaintainPartContent.setMaintainCode(maintainCode);
		List<TbMaintianVo> maintianVos = this.getTbMaintianVos(null, tbMaintainPartContent);
		
		if(null != maintianVos && maintianVos.size() > 0){
			Long isconfirm = maintianVos.get(0).getIsConfirm();
			
			return !(isconfirm.equals(Constants.FINSH_BALANCE));
		}
		
		return true;
	}
	
	
	public List<TbMaintianVo> getTbMaintianDetailVos(String maintainCode,TbMaintainPartContent tbMaintainPartContent){
		
		List<TbMaintianVo> result = 
			 tbMaintainPartContentDao.getTbMaintianDetailVos(maintainCode,tbMaintainPartContent);
		 return result;
	}
	
	public void updateOldMaintain(String parts){
		Long maintainId = new Long(parts.split(":")[0]);
		String isPrint = parts.split(":")[2];
		TbMaintainPartContent tbMaintainPartContent = this.tbMaintainPartContentDao.findById(maintainId);
		tbMaintainPartContent.setIsPrint(isPrint);
		this.tbMaintainPartContentDao.update(tbMaintainPartContent);
	}


	@Override
	public double getAllStockOutNum(Long partId) {
		return tbMaintainPartContentDao.getAllStockOutNum(partId);
	}
}
