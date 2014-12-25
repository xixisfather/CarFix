package com.selfsoft.baseparameter.action;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseparameter.model.TmBalance;
import com.selfsoft.baseparameter.model.TmBalanceItem;
import com.selfsoft.baseparameter.service.ITmBalanceItemService;
import com.selfsoft.baseparameter.service.ITmBalanceService;
import com.selfsoft.business.model.TbBusinessBalanceItem;
import com.selfsoft.business.model.TbFixEntrust;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITbBusinessBalanceItemService;
import com.selfsoft.business.service.ITbFixEntrustContentService;
import com.selfsoft.business.service.ITbFixEntrustService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmBalanceItemAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {
	
	private static final long serialVersionUID = -1625160730825128350L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmBalanceItemService tmBalanceItemService;
	
	private TmBalanceItem tmBalanceItem;
	
	@Autowired
	private ITmBalanceService tmBalanceService;
	
	@Autowired
	private ITbFixEntrustContentService tbFixEntrustContentService;
	
	@Autowired
	private ITmStockOutService tmStockOutService;
	
	@Autowired
	private ITbFixEntrustService tbFixEntrustService;
	
	@Autowired
	private ITbBusinessBalanceItemService tbBusinessBalanceItemService;
 
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	
	public TmBalanceItem getTmBalanceItem() {
		return tmBalanceItem;
	}

	public void setTmBalanceItem(TmBalanceItem tmBalanceItem) {
		this.tmBalanceItem = tmBalanceItem;
	}
	
	public String findTmBalanceItem() {
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findByTmBalanceItem(tmBalanceItem);

		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}

	public String insertTmBalanceItem() throws Exception{
		tmBalanceItemService.insert(tmBalanceItem);

		return Constants.SUCCESS;
	}

	public String updateTmBalanceItem() throws Exception{
		
		TmBalanceItem t = tmBalanceItemService.findById(tmBalanceItem.getId());
		
		tmBalanceItem.setItemName(t.getItemName());
		
		tmBalanceItemService.update(tmBalanceItem);

		return Constants.SUCCESS;
		
	}
	
	public String deleteTmBalanceItem() throws Exception{
		String id = request.getParameter("id");

		if (null != id && !"".equals(id)) {

			boolean flag = tmBalanceItemService.deleteById(Long.valueOf(id));
			// 回传时字符格式为 E3表的id,操作标示
			if (flag) {
				response.getWriter().print("tmBalanceItemTable," + Constants.SUCCESS);
			} else {
				response.getWriter().print("tmBalanceItemTable," + Constants.FAILURE);
			}
		} else {
			response.getWriter().print("tmBalanceItemTable," + Constants.EXCEPTION);
		}
		return null;
	}

	public String forwardPage() throws Exception {

		String id = request.getParameter("id");

		String tmBalanceId = request.getParameter("tmBalanceId");
		
		ActionContext.getContext().put("isNoMap", Constants.getIsNoMap());
		
		if (null != id && !"".equals(id)) {

			tmBalanceItem = tmBalanceItemService.findById(Long.valueOf(id));
			
			TmBalance tmBalance = tmBalanceService.findById(tmBalanceItem.getTmBalance().getId());

			tmBalanceItem.setTmBalance(tmBalance);
			//结算明细MAP
			ActionContext.getContext().put("tmBalanceItemMap", tmBalanceItemService.findTmBalanceItemMapByTmBalanceId(tmBalanceItem.getTmBalance().getId()));
			
			return Constants.EDITPAGE;
		}
		else{
			tmBalanceItem = new TmBalanceItem();
			
			TmBalance tmBalance = tmBalanceService.findById(Long.valueOf(tmBalanceId));
			
			tmBalanceItem.setTmBalance(tmBalance);
			//结算明细MAP
			ActionContext.getContext().put("tmBalanceItemMap", tmBalanceItemService.findTmBalanceItemMapByTmBalanceId(Long.valueOf(tmBalanceId)));
			
		}
		
		return Constants.ADDPAGE;
	}
	
	public String validateFormula() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String formula = CommonMethod.TransferToEncoding(request.getParameter("formula"), "ISO-8859-1", "GBK");
		
		formula = CommonMethod.replaceAll(formula, '@', '+');
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		List<TmBalanceItem>tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		Map<String,BigDecimal> map = new LinkedHashMap<String, BigDecimal>();
		
		for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
			
			map.put(tmBalanceItem.getItemName().trim(), new BigDecimal("1"));
		
		}
		
		try{
			
			tmBalanceItemService.formulaCalculateByFormula(formula,map);
			
			response.getWriter().print("success,公式正确");
			
		}catch(Exception e){
			
			response.getWriter().print("fail,公式错误");
		
		}
		
		return null;
	}
	
	public String validateItemName() throws Exception{
		
		response.setCharacterEncoding("UTF-8");
		
		String itemName =CommonMethod.TransferToEncoding(request.getParameter("itemName").trim(), "ISO-8859-1", "GBK");
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		TmBalanceItem tmBalanceItem = tmBalanceItemService.findTmBalanceItemByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId), itemName);
		
		if(null==tmBalanceItem){
			response.getWriter().print("success,该项目名称下对应的结算明细["+itemName+"]不存在");
		}
		else{
			response.getWriter().print("fail,该项目名称下对应的结算明细["+itemName+"]已存在");
		}
		
		return null;
	}
	
	public String calcTmBalanceItem() throws Exception{
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		String workingHourFavourRate = request.getParameter("workingHourFavourRate");
		
		String fixPartFavourRate = request.getParameter("fixPartFavourRate");
		
		String solePartFavourRate = request.getParameter("solePartFavourRate");
		
		//String trustBill = request.getParameter("trustBill");
		
		TbFixEntrust tbFixEntrust = tbFixEntrustService.findById(Long.valueOf(tbFixEntrustId));
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		//工时总价
		Double workingHourTotalAll = calcFavour(tbFixEntrustContentService.countTbFixEnTrustContentByTbFixEntrustIdAndBalanceIdIsNull(Long.valueOf(tbFixEntrustId)),workingHourFavourRate);
		
		//修理材料总价
		
		Double fixPartTotalAll = calcFavour(tbMaintainPartContentService.getTotalPriceByBalanceId(Long.valueOf(Long.valueOf(tbFixEntrustId)),null),fixPartFavourRate);
			
		//销售材料总价
		Double solePartTotalAll = calcFavour(tmStockOutService.getSellTotalPriceByBalance(tbFixEntrust.getEntrustCode(),null),solePartFavourRate);
		
		Map<String,BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		if(null!=tmBalanceItemList&&tmBalanceItemList.size()>0){
			
			//初始化金额
			if(null!=ActionContext.getContext().getSession().get("calcSecondMapName")){
				
				calcMap = (Map<String, BigDecimal>) ActionContext.getContext().getSession().get("calcSecondMapName");
			
				calcMap = itemTotalChangeMap(calcMap,workingHourTotalAll,fixPartTotalAll,solePartTotalAll);
			}
			else{
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					
					if(Constants.XLGSF.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XLGSF, new BigDecimal(String.valueOf(workingHourTotalAll)));
					
					}
					else if(Constants.XLCLF.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XLCLF, new BigDecimal(String.valueOf(fixPartTotalAll)));
						
					}
					else if(Constants.XSJE.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XSJE, new BigDecimal(String.valueOf(solePartTotalAll)));
						
					}
					else{
						
						initMap.put(tmBalanceItem.getItemName().trim(), new BigDecimal("0.00"));
						
					}
				
				}
				
				//计算各项明细金额
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),initMap).get(0))); 
				
					calcMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
				}
				
				
				
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcMap).get(0))); 
			
				calcSecondMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcSecondMap).get(0))); 
			
				calcMapReturn.put(tmBalanceItem.getItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().getSession().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().getSession().put("calcSecondMapName", calcSecondMap);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	
	public String viewCalcTmBalanceItem() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
			
				BigDecimal itemValue = new BigDecimal(tbBusinessBalanceItem.getBalanceItemTotal());
				
				calcMapReturn.put(tbBusinessBalanceItem.getBalanceItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	
	
	public String viewCalcTmBalanceItemGroup() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
			
				BigDecimal itemValue = new BigDecimal(tbBusinessBalanceItem.getBalanceItemTotal());
				
				calcMapReturn.put(tbBusinessBalanceItem.getBalanceItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	
	//Ajax结算各个项目明细
	public String calcTmBalanceItemAjax() throws Exception{
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		String tbFixEntrustId = request.getParameter("tbFixEntrustId");
		
		String itemCode = request.getParameter("itemCode");
		
		String itemVal = request.getParameter("itemVal");
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		Map<String,BigDecimal> calcMap = (LinkedHashMap<String, BigDecimal>) request.getSession().getAttribute("calcMapReturn");
		
		Map<String,BigDecimal> calcMapName = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcSecondMapName = new LinkedHashMap<String, BigDecimal>();
		
		if(null!=tmBalanceItemList&&tmBalanceItemList.size()>0){
			
			//初始化金额
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				
				if(itemCode.equals(tmBalanceItem.getItemCode().trim())){
					
					calcMap.put(itemCode, new BigDecimal(itemVal));
				
				}
				
				calcMapName.put(tmBalanceItem.getItemName().trim(), calcMap.get(tmBalanceItem.getItemCode()));
				
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcMapName).get(0))); 
			
				calcSecondMapName.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcSecondMapName).get(0))))); 
			
				calcMap.put(tmBalanceItem.getItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		String str = "";
		
		for(Object object : calcMap.keySet()){
			
			String itemCodeReturn = (String)object;
			
			String itemValueReturn = new BigDecimal(String.valueOf(calcMap.get(itemCodeReturn))).toString();
			
			str = str + itemCodeReturn + ":" + itemValueReturn + ",";
			
		}
		
		ActionContext.getContext().getSession().put("calcSecondMapName", calcSecondMapName);
		
		response.getWriter().print(str);
		
		return null;
	}
	
	//计算优惠金额
	private Double calcFavour(Double total , String favour){
		
		Double favourVal = 0D;
		
		if(null!=favour&&!"".equals(favour)){

			favourVal  = Double.valueOf(favour);
			
		}
		
		return total*(1 - favourVal);
	}
	
	//金额变化的项目明细MAP
	private Map<String,BigDecimal> itemTotalChangeMap(Map<String,BigDecimal> map,Double workingHourTotalAll,Double fixPartTotalAll,Double solePartTotalAll){
		
		map.put(Constants.XLGSF, new BigDecimal(String.valueOf(workingHourTotalAll)));
		
		map.put(Constants.XLCLF, new BigDecimal(String.valueOf(fixPartTotalAll)));
		
		map.put(Constants.XSJE, new BigDecimal(String.valueOf(solePartTotalAll)));
		
		return map;
	} 
	
	
	public String calcXsdTmBalanceItem() throws Exception{
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		ActionContext.getContext().put("tmBalanceId", tmBalanceId);
		
		String stockOutId = request.getParameter("tmStockOutId");
		
		String workingHourFavourRate = request.getParameter("workingHourFavourRate");
		
		String fixPartFavourRate = request.getParameter("fixPartFavourRate");
		
		String solePartFavourRate = request.getParameter("solePartFavourRate");
		
		TmStockOut tmStockOut = tmStockOutService.findById(new Long(stockOutId));
		
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, tmStockOut.getId(), StockTypeElements.SELLSTOCKOUT.getElementValue());
		
		//工时总价
		Double workingHourTotalAll = 0D;
		
		//修理材料总价
		Double fixPartTotalAll = 0D;
		
		//销售材料总价
		Double solePartTotalAll = tmStockOutService.getCustomerSellTotalPriceByBalance(Long.valueOf(stockOutId), null);
		
		solePartTotalAll = new BigDecimal(solePartTotalAll).multiply(new BigDecimal(1 - Double.valueOf(solePartFavourRate))).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		Map<String,BigDecimal> initMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcSecondMap = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		if(null!=tmBalanceItemList&&tmBalanceItemList.size()>0){
			
			//初始化金额
			if(null!=ActionContext.getContext().getSession().get("calcSecondMapName")){
				
				calcMap = (Map<String, BigDecimal>) ActionContext.getContext().getSession().get("calcSecondMapName");
			
				calcMap = itemTotalChangeMap(calcMap,workingHourTotalAll,fixPartTotalAll,solePartTotalAll);
			}
			else{
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					
					if(Constants.XLGSF.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XLGSF, new BigDecimal(String.valueOf(workingHourTotalAll)));
					
					}
					else if(Constants.XLCLF.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XLCLF, new BigDecimal(String.valueOf(fixPartTotalAll)));
						
					}
					else if(Constants.XSJE.equals(tmBalanceItem.getItemName().trim())){
						
						initMap.put(Constants.XSJE, new BigDecimal(String.valueOf(solePartTotalAll)));
						
					}
					else{
						
						initMap.put(tmBalanceItem.getItemName().trim(), new BigDecimal("0.00"));
						
					}
				
				}
				
				//计算各项明细金额
				for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
					
					BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),initMap).get(0))); 
				
					calcMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
				}
				
				
				
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcMap).get(0))); 
			
				calcSecondMap.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcSecondMap).get(0))); 
			
				calcMapReturn.put(tmBalanceItem.getItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().getSession().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().getSession().put("calcSecondMapName", calcSecondMap);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	
	public String viewCalcXsdTmBalanceItem() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
			
				BigDecimal itemValue = new BigDecimal(tbBusinessBalanceItem.getBalanceItemTotal());
				
				calcMapReturn.put(tbBusinessBalanceItem.getBalanceItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	
	public String viewCalcXsdTmBalanceItemGroup() throws Exception{
		
		String tbBusinessBalanceId = request.getParameter("tbBusinessBalanceId");
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		List<TbBusinessBalanceItem> tbBusinessBalanceItemList = tbBusinessBalanceItemService.findGroupTbBusinessBalanceItemListByTbBusinessBalanceId(Long.valueOf(tbBusinessBalanceId));
		
		Map<String,BigDecimal> calcMapReturn = new LinkedHashMap<String, BigDecimal>();
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		if(null!=tbBusinessBalanceItemList&&tbBusinessBalanceItemList.size()>0){
			
			for(TbBusinessBalanceItem tbBusinessBalanceItem : tbBusinessBalanceItemList){
			
				BigDecimal itemValue = new BigDecimal(tbBusinessBalanceItem.getBalanceItemTotal());
				
				calcMapReturn.put(tbBusinessBalanceItem.getBalanceItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		ActionContext.getContext().put("calcMapReturn", calcMapReturn);
		
		ActionContext.getContext().put("tmBalanceItemList", tmBalanceItemList);
		
		return Constants.SUCCESS;
	}
	//Ajax结算各个项目明细
	public String calcXsdTmBalanceItemAjax() throws Exception{
		
		String tmBalanceId = request.getParameter("tmBalanceId");
		
		String stockOutId = request.getParameter("stockOutId");
		
		String itemCode = request.getParameter("itemCode");
		
		String itemVal = request.getParameter("itemVal");
		
		List<TmBalanceItem> tmBalanceItemList = tmBalanceItemService.findTmBalanceItemByTmBalanceId(Long.valueOf(tmBalanceId));
		
		Map<String,BigDecimal> calcMap = (LinkedHashMap<String, BigDecimal>) request.getSession().getAttribute("calcMapReturn");
		
		Map<String,BigDecimal> calcMapName = new LinkedHashMap<String, BigDecimal>();
		
		Map<String,BigDecimal> calcSecondMapName = new LinkedHashMap<String, BigDecimal>();
		
		if(null!=tmBalanceItemList&&tmBalanceItemList.size()>0){
			
			//初始化金额
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				
				if(itemCode.equals(tmBalanceItem.getItemCode().trim())){
					
					calcMap.put(itemCode, new BigDecimal(itemVal));
				
				}
				
				calcMapName.put(tmBalanceItem.getItemName().trim(), calcMap.get(tmBalanceItem.getItemCode()));
				
			}
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcMapName).get(0))); 
			
				calcSecondMapName.put(tmBalanceItem.getItemName(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
			
			
			for(TmBalanceItem tmBalanceItem : tmBalanceItemList){
				
				BigDecimal itemValue = new BigDecimal(String.valueOf(new BigDecimal(String.valueOf(tmBalanceItemService.formulaCalculateByTmBalanceIdAndTmBalanceItemName(Long.valueOf(tmBalanceId),tmBalanceItem.getItemName().trim(),calcSecondMapName).get(0))))); 
			
				calcMap.put(tmBalanceItem.getItemCode(),itemValue.setScale(2,BigDecimal.ROUND_HALF_UP));
			}
		}
		
		String str = "";
		
		for(Object object : calcMap.keySet()){
			
			String itemCodeReturn = (String)object;
			
			String itemValueReturn = new BigDecimal(String.valueOf(calcMap.get(itemCodeReturn))).toString();
			
			str = str + itemCodeReturn + ":" + itemValueReturn + ",";
			
		}
		
		ActionContext.getContext().getSession().put("calcSecondMapName", calcSecondMapName);
		
		response.getWriter().print(str);
		
		return null;
	}
	
	
	
	
}
