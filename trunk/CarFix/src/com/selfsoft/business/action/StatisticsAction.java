package com.selfsoft.business.action;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseinformation.model.TbCustomer;
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseinformation.service.ITbPartInfoService;
import com.selfsoft.baseparameter.model.TmCarModelType;
import com.selfsoft.baseparameter.model.TmFixType;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmCarModelTypeService;
import com.selfsoft.baseparameter.service.ITmFixTypeService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TbMaintainPartContent;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmLianceReturn;
import com.selfsoft.business.model.TmLoanRegister;
import com.selfsoft.business.model.TmLoanReturn;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.business.service.ITbMaintainPartContentService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.business.vo.BalanceFixSellVo;
import com.selfsoft.business.vo.DailyStockOutVo;
import com.selfsoft.business.vo.TbCustomerVo;
import com.selfsoft.business.vo.TbMaintianVo;
import com.selfsoft.business.vo.TbPartInfoReFlowStatVo;
import com.selfsoft.business.vo.TbPartInfoStockOutVo;
import com.selfsoft.business.vo.TbPartReceiverStatVo;
import com.selfsoft.business.vo.TbStoreHouseSurveyVo;
import com.selfsoft.business.vo.TmDrawStatVo;
import com.selfsoft.business.vo.TmLianceRegVo;
import com.selfsoft.business.vo.TmLoanRegVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.business.vo.TmStoreHouseReceiverStatVo;
import com.selfsoft.business.xls.IStockXLSImportService;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.model.TmDepartment;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmCompanyService;
import com.selfsoft.secrity.service.ITmDepartmentService;
import com.selfsoft.secrity.service.ITmUserService;


public class StatisticsAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITbPartInfoService tbPartInfoService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmCarModelTypeService tmCarModelTypeService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private IStatisticsStockInOutService statisticsStockInOutService;
	@Autowired
	private ITmDepartmentService tmDepartmentService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITmFixTypeService tmFixTypeService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmStockoutDetailService tmStockoutDetailService;
	@Autowired
	private ITbMaintainPartContentService tbMaintainPartContentService;
	@Autowired
	private IStockXLSImportService stockXLSImportService;
	
	private TbPartInfo tbPartInfo;
	
	private TmStockIn tmStockIn;
	
	private TbCustomer tbCustomer;
	
	private Long elementValue;
	
	private TmStockOut tmStockOut;
	
	private TmLianceRegister tmLianceRegister;
	
	private TmLianceReturn tmLianceReturn;
	
	private TmLoanRegister tmLoanRegister;
	
	private TmLoanReturn tmLoanReturn;
	
	private TbPartReceiverStatVo tbPartReceiverStatVo;
	
	private TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo;
	
	private TbPartInfoStockOutVo tbPartInfoStockOutVo;
	
	private TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo;
	
	private TmDrawStatVo tmDrawStatVo;
	
	private DailyStockOutVo dailyStockOutVo;
	
	private BalanceFixSellVo balanceFixSellVo;
	
	private TbMaintainPartContent tbMaintainPartContent;
	
	private Map<Long,String> busTypes;
	
	{
		if(busTypes == null){
			busTypes = new HashMap<Long, String>();
			busTypes.put(1L, "采购入库");
			busTypes.put(2L, "采购退货");
			busTypes.put(3L, "所有");
		}
		
	}
	
	private String qryyear;
	
	private String qrymounth;
	
	public String getQryyear() {
		return qryyear;
	}

	public void setQryyear(String qryyear) {
		this.qryyear = qryyear;
	}

	public String getQrymounth() {
		return qrymounth;
	}

	public void setQrymounth(String qrymounth) {
		this.qrymounth = qrymounth;
	}

	public Map<Long, String> getBusTypes() {
		return busTypes;
	}

	public void setBusTypes(Map<Long, String> busTypes) {
		this.busTypes = busTypes;
	}

	public TbMaintainPartContent getTbMaintainPartContent() {
		return tbMaintainPartContent;
	}

	public void setTbMaintainPartContent(TbMaintainPartContent tbMaintainPartContent) {
		this.tbMaintainPartContent = tbMaintainPartContent;
	}

	public BalanceFixSellVo getBalanceFixSellVo() {
		return balanceFixSellVo;
	}

	public void setBalanceFixSellVo(BalanceFixSellVo balanceFixSellVo) {
		this.balanceFixSellVo = balanceFixSellVo;
	}

	public DailyStockOutVo getDailyStockOutVo() {
		return dailyStockOutVo;
	}

	public void setDailyStockOutVo(DailyStockOutVo dailyStockOutVo) {
		this.dailyStockOutVo = dailyStockOutVo;
	}

	public TmDrawStatVo getTmDrawStatVo() {
		return tmDrawStatVo;
	}

	public void setTmDrawStatVo(TmDrawStatVo tmDrawStatVo) {
		this.tmDrawStatVo = tmDrawStatVo;
	}

	public TmStoreHouseReceiverStatVo getTmStoreHouseReceiverStatVo() {
		return tmStoreHouseReceiverStatVo;
	}

	public void setTmStoreHouseReceiverStatVo(
			TmStoreHouseReceiverStatVo tmStoreHouseReceiverStatVo) {
		this.tmStoreHouseReceiverStatVo = tmStoreHouseReceiverStatVo;
	}

	public TbPartInfoStockOutVo getTbPartInfoStockOutVo() {
		return tbPartInfoStockOutVo;
	}

	public void setTbPartInfoStockOutVo(TbPartInfoStockOutVo tbPartInfoStockOutVo) {
		this.tbPartInfoStockOutVo = tbPartInfoStockOutVo;
	}

	public TbPartInfoReFlowStatVo getTbPartInfoReFlowStatVo() {
		return tbPartInfoReFlowStatVo;
	}

	public void setTbPartInfoReFlowStatVo(
			TbPartInfoReFlowStatVo tbPartInfoReFlowStatVo) {
		this.tbPartInfoReFlowStatVo = tbPartInfoReFlowStatVo;
	}

	public TmLoanRegister getTmLoanRegister() {
		return tmLoanRegister;
	}

	public void setTmLoanRegister(TmLoanRegister tmLoanRegister) {
		this.tmLoanRegister = tmLoanRegister;
	}

	public TmLoanReturn getTmLoanReturn() {
		return tmLoanReturn;
	}

	public void setTmLoanReturn(TmLoanReturn tmLoanReturn) {
		this.tmLoanReturn = tmLoanReturn;
	}

	public TmLianceReturn getTmLianceReturn() {
		return tmLianceReturn;
	}

	public void setTmLianceReturn(TmLianceReturn tmLianceReturn) {
		this.tmLianceReturn = tmLianceReturn;
	}

	public TmLianceRegister getTmLianceRegister() {
		return tmLianceRegister;
	}

	public void setTmLianceRegister(TmLianceRegister tmLianceRegister) {
		this.tmLianceRegister = tmLianceRegister;
	}

	public TmStockOut getTmStockOut() {
		return tmStockOut;
	}

	public void setTmStockOut(TmStockOut tmStockOut) {
		this.tmStockOut = tmStockOut;
	}

	public Long getElementValue() {
		return elementValue;
	}

	public void setElementValue(Long elementValue) {
		this.elementValue = elementValue;
	}

	public TbCustomer getTbCustomer() {
		return tbCustomer;
	}

	public void setTbCustomer(TbCustomer tbCustomer) {
		this.tbCustomer = tbCustomer;
	}

	public TmStockIn getTmStockIn() {
		return tmStockIn;
	}

	public void setTmStockIn(TmStockIn tmStockIn) {
		this.tmStockIn = tmStockIn;
	}

	public TbPartInfo getTbPartInfo() {
		return tbPartInfo;
	}

	public void setTbPartInfo(TbPartInfo tbPartInfo) {
		this.tbPartInfo = tbPartInfo;
	}

	public TbPartReceiverStatVo getTbPartReceiverStatVo() {
		return tbPartReceiverStatVo;
	}

	public void setTbPartReceiverStatVo(TbPartReceiverStatVo tbPartReceiverStatVo) {
		this.tbPartReceiverStatVo = tbPartReceiverStatVo;
	}
	
	
	/**
	 * 配件积压查询
	 * @Date      2010-7-5
	 * @Function  
	 * @return
	 */
	public String overStockTbPartInfoStat(){
		if(tbPartInfo == null)
			tbPartInfo = new TbPartInfo();
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		Double totalStockMoney = tbPartInfoService.getTotalStockMoney(tbPartInfo);
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("tbPartInfoList", tbPartInfoList);
		ActionContext.getContext().put("totalStockMoney", totalStockMoney);
		
		/**
		 * add by ccr
		 * uesd for report
		 */
		ActionContext.getContext().getSession().put("tbPartInfoReport", tbPartInfo);
		ActionContext.getContext().getSession().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().getSession().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().getSession().put("tbPartInfoList", tbPartInfoList);
		ActionContext.getContext().getSession().put("totalStockMoney", totalStockMoney);
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 采购入库查询 - 每个供应商的金额汇总
	 * @Date      2010-7-5
	 * @Function  
	 * @return
	 */
	public String stockInGroupCustomerByStat(){
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
			tmStockIn.setBusType(1L);
		}
		
		Map<Double,List<TbCustomerVo>> map = null;
		if(tmStockIn.getBusType() == 1L){
			//采购入库供应商汇总查询
			 map = tmStockinDetailService.getSellCustomerTotalPriceByStat(tmStockIn);
		}else if(tmStockIn.getBusType() == 2L){
			//采购退货供应商汇总查询
			map = tmStockoutDetailService.getStockOutCustomerTotalPriceByStat(tmStockIn);
		}else if(tmStockIn.getBusType() == 3L){
			 map = tmStockinDetailService.getSellCustomerTotalPriceByStat(tmStockIn);
			 
			 Map<Double,List<TbCustomerVo>> outmap = tmStockoutDetailService.getStockOutCustomerTotalPriceByStat(tmStockIn);
			 List<TbCustomerVo> rklist = null;
			 Double rkPrice = 0D;
			 for(Double key : map.keySet()){
				 rklist= map.get(key);
				 rkPrice = key;
			 }
			 List<TbCustomerVo> thlist = null;
			 Double thPrice = 0D;
			 for(Double key : outmap.keySet()){
				 thlist= outmap.get(key);
				 thPrice = key;
			 }
			 if(rklist != null && rklist.size()>0){
				 for(TbCustomerVo rkVo : rklist){
					 for(TbCustomerVo thVo : thlist){
						 if(rkVo.getCustomerId().equals(thVo.getCustomerId())){
							 rkVo.setThPrice(thVo.getTotalPrice());
							 rkVo.setCjPrice(CommonMethod.convertRadixPoint(rkVo.getTotalPrice() - rkVo.getThPrice(),2)); 
						 }
					 }
				 }
			 }else{
				 
				 rklist = thlist;
				 
			 }
			 map.clear();
			 map.put(rkPrice - thPrice, rklist);
			 
		}
		 
		for(Double key : map.keySet()){
			ActionContext.getContext().put("customerVos", map.get(key));
			ActionContext.getContext().put("supplierTotalPrice", CommonMethod.convertRadixPoint(key,2));
		} 
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 采购入库查询 - 统计所有采购入库明细
	 * @Date      2010-7-5
	 * @Function  
	 * @return
	 */
	public String stockInTbPartInfoByStat(){
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
			tmStockIn.setBusType(1L);
		}
		
		if(tmStockIn.getBusType() == 1L){
			//采购入库明细查询
			List<TmStockInDetailVo> stockInDetails = tmStockinDetailService.getSellDetailByStat(tmStockIn);
			ActionContext.getContext().put("stockDetails", stockInDetails);
			Double stockTotalPrice = tmStockinDetailService.getStockInTotalPriceByStat(tmStockIn);
			ActionContext.getContext().put("stockTotalPrice", stockTotalPrice);
		}else if(tmStockIn.getBusType() == 2L){
			List<TmStockInDetailVo> stockInDetails = new ArrayList<TmStockInDetailVo>();
			Double stockTotalPrice =  0D;
			//采购退货明细查询
			 List<TmStockOutDetVo> stockOutDetails = tmStockOutService.getStockOutDetVos(tmStockIn);
				for(TmStockOutDetVo outVo : stockOutDetails){
					TmStockInDetailVo  detVo = new TmStockInDetailVo();
					detVo.setPartinfoId(outVo.getPartinfoId());
					detVo.setPartCode(outVo.getPartCode());
					detVo.setPartName(outVo.getPartName());
					detVo.setHouseName(outVo.getHouseName());
					detVo.setCustomerId(outVo.getCustomerId());
					detVo.setCustomerCode(outVo.getCustomerCode());
					detVo.setCustomerName(outVo.getCustomerName());
					detVo.setArriveDate(outVo.getStockOutDate());
					detVo.setQuantity(outVo.getQuantity());
					detVo.setCostPrice(outVo.getPrice());
					detVo.setStockInCode(outVo.getStockOutCode());
					stockInDetails.add(detVo);
					stockTotalPrice += detVo.getQuantity()*detVo.getCostPrice();
				}
				ActionContext.getContext().put("stockDetails", stockInDetails);
				ActionContext.getContext().put("stockTotalPrice", CommonMethod.convertRadixPoint(stockTotalPrice, 2));
		}else if(tmStockIn.getBusType() == 3L){
			//采购入库+采购退货明细查询
			Double stockTotalPrice = tmStockinDetailService.getStockInTotalPriceByStat(tmStockIn);
			List<TmStockInDetailVo> stockInDetails  = tmStockinDetailService.getSellDetailByStat(tmStockIn);
			List<TmStockOutDetVo> stockOutDetails = tmStockOutService.getStockOutDetVos(tmStockIn);
			for(TmStockOutDetVo outVo : stockOutDetails){
				TmStockInDetailVo  detVo = new TmStockInDetailVo();
				detVo.setPartinfoId(outVo.getPartinfoId());
				detVo.setPartCode(outVo.getPartCode());
				detVo.setPartName(outVo.getPartName());
				detVo.setHouseName(outVo.getHouseName());
				detVo.setCustomerId(outVo.getCustomerId());
				detVo.setCustomerCode(outVo.getCustomerCode());
				detVo.setCustomerName(outVo.getCustomerName());
				detVo.setArriveDate(outVo.getStockOutDate());
				detVo.setQuantity(outVo.getQuantity());
				detVo.setCostPrice(outVo.getPrice());
				detVo.setStockInCode(outVo.getStockOutCode());
				stockInDetails.add(detVo);
				stockTotalPrice -= detVo.getQuantity()*detVo.getCostPrice();
			}
			ActionContext.getContext().put("stockDetails", stockInDetails);
			ActionContext.getContext().put("stockTotalPrice", CommonMethod.convertRadixPoint(stockTotalPrice, 2));
		}
		
		
		
		ActionContext.getContext().put("busTypes", busTypes);
		return Constants.SUCCESS;
	}
	
	
	
	/**
	 * 采购入库查询 - 主单据查询列表
	 * @Date      2010-7-5
	 * @Function  
	 * @return
	 */
	public String stockInMasterBillByStat(){
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
			tmStockIn.setBusType(1L);
		}
		
		if(tmStockIn.getBusType() == 1L){
			//采购入库主单查询
			List<TmStockInVo> stockDetailList = tmStockInService.getMasterStockIn(StockTypeElements.STOCK.getElementValue(), Constants.CONFIRM+"", tmStockIn);
			ActionContext.getContext().put("stockDetailList", stockDetailList);
			
		}else if(tmStockIn.getBusType() == 2L){
			//采购退货主单查询
			List<TmStockOutVo> stockDetailList = tmStockOutService.getStockOutVos(tmStockIn);
			ActionContext.getContext().put("stockDetailList", stockDetailList);
			
		}else if(tmStockIn.getBusType() == 3L){
			//采购入库+采购退货主单查询
			List<TmStockInVo> stockDetailList = tmStockInService.getMasterAllStock(tmStockIn);
			ActionContext.getContext().put("stockDetailList", stockDetailList);
			
		}
		ActionContext.getContext().put("busTypes", busTypes);
		return Constants.SUCCESS;
	}
	
	/**
	 * 更新采购入库单汇款方式
	 * @throws IOException 
	 */
	public void updateStockPanMent() throws IOException{
		
		
		String id = request.getParameter("id");
		String payMent = request.getParameter("payMent");
		if (null != id && !"".equals(id)) {
			tmStockInService.updateStockPanMent(new Long(id), new Long(payMent));
			// 回传时字符格式为 E3表的id,操作标示
			response.getWriter().print("stockMasterTable," + Constants.SUCCESS);
		} else {
			response.getWriter().print("stockMasterTable," + Constants.EXCEPTION);
		}
	}
	
	/**
	 * 采购入库查询
	 * @Date      2010-7-6
	 * @Function  
	 * @return
	 */
	public String stockInByStat(){
		String types = Constants.SUPPLIERVAL + "," + Constants.CUSTOMERANDSUPPLIERVAL;
		List<TbCustomer> tbCustomers = tbCustomerService.findByTbCustomer(tbCustomer, types);
		
		ActionContext.getContext().put("tbCustomers", tbCustomers);
		ActionContext.getContext().put("busTypes", busTypes);
		Map<Long,String> panMentMap = Constants.getPayMentMap();
		request.setAttribute("panMentMap", panMentMap);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 配件收发单据查询
	 * @Date      2010-7-6
	 * @Function  
	 * @return
	 */
	public String stockInOutBillStat(){
		Map<Long, String> elementsMap = statisticsStockInOutService.getStockInOutElementMap();
		ActionContext.getContext().put("elementsMap", elementsMap);
		
		if(elementValue == null){
			//默认为采购入库
//			List<TmStockInVo> results = statisticsStockInOutService.getStockInTypeBill(StockTypeElements.STOCK.getElementValue(),tmStockIn);
//			ActionContext.getContext().put("results", results);
			ActionContext.getContext().put("e3TableCaption", "入库单");
			ActionContext.getContext().put("stockType", 1);
			
			//修改成明显列表
			
			
			List<TmStockInDetailVo> stockInDetails = tmStockinDetailService.getSellDetailByStat(StockTypeElements.STOCK.getElementValue(),tmStockIn);
			ActionContext.getContext().put("results", stockInDetails);
			ActionContext.getContext().getSession().put("stockInDetails_session", stockInDetails);
			return Constants.SUCCESS;
		}
		
		
		if(elementValue.equals(StockTypeElements.STOCK.getElementValue()) ||  
				elementValue.equals(StockTypeElements.ALLOT.getElementValue()) || 
				elementValue.equals(StockTypeElements.OTHERSTOCKIN.getElementValue()) ||
				elementValue.equals(StockTypeElements.OVERFLOW.getElementValue()) ||
				elementValue.equals(StockTypeElements.SELLRETURN.getElementValue())){
			
			//采购，调拨，报溢，销售退货
//			List<TmStockInVo> results = statisticsStockInOutService.getStockInTypeBill(elementValue,tmStockIn);
//			ActionContext.getContext().put("results", results);
			List<TmStockInDetailVo> stockInDetails = tmStockinDetailService.getSellDetailByStat(elementValue,tmStockIn);
			ActionContext.getContext().put("results", stockInDetails);
			ActionContext.getContext().put("e3TableCaption", "入库单");
			ActionContext.getContext().put("stockType", 1);
			ActionContext.getContext().getSession().put("stockInDetails_session", stockInDetails);
			
		}else if(elementValue.equals(StockTypeElements.SELLSTOCKOUT.getElementValue()) || 
				elementValue.equals(StockTypeElements.ALLOTSTOCKOUT.getElementValue()) ||
				elementValue.equals(StockTypeElements.DRAWSTOCKOUT.getElementValue()) ||
				elementValue.equals(StockTypeElements.SHATTERSTOCKOUT.getElementValue()) ||
				elementValue.equals(StockTypeElements.STOCKRETURN.getElementValue()) ){
			
			//销售，调拨，报损，采购退货
			List<TmStockOutVo> results = statisticsStockInOutService.getStockOutTypeBill(elementValue,tmStockOut);
			
			
			List<TmStockOutDetVo> detvoList = new ArrayList<TmStockOutDetVo>();
		    for(TmStockOutVo outvo : results){
		    	
		    	TmStockOut tmstockout = this.tmStockOutService.findById(outvo.getStockOutId());
		    	if(tmstockout.getOutType().equals(StockTypeElements.DRAWSTOCKOUT.getElementValue())){
		    		if(tmstockout.getDrawPeople() != null){
		    			
		    			String userRealName = tmUserService.findById(tmstockout.getDrawPeople()).getUserRealName();
		    			outvo.setUserRealName(userRealName);
		    		}
		    	}else{
		    		outvo.setUserRealName(null);
		    	}
		    	List<TmStockOutDetVo> stockOutDetVos = 	tmStockOutService.getStockOutDetVos(tmStockOut, outvo.getStockOutId(), elementValue);
		    	
		    	for(TmStockOutDetVo detVo : stockOutDetVos){
		    		
		    		detVo.setTmStockOutVo(outvo);
		    		
		    		detvoList.add(detVo);
		    	}
		    	
		    	
		    }
		 
			ActionContext.getContext().put("e3TableCaption", "出库单");
			ActionContext.getContext().put("stockType", 2);
			ActionContext.getContext().put("results", detvoList);
			
		}else if(elementValue.equals(StockTypeElements.LIANCEREGISTER.getElementValue())){
			
			//借进登记
			List<TmLianceRegVo> results = statisticsStockInOutService.getLianceRegisterTypeBill(tmLianceRegister);
			ActionContext.getContext().put("e3TableCaption", "借进登记单");
			ActionContext.getContext().put("stockType",3);
			ActionContext.getContext().put("results", results);
			
		}else if(elementValue.equals(StockTypeElements.LIANCERETURN.getElementValue())){
			if(tmLianceReturn == null){
				tmLianceReturn = new TmLianceReturn();
				tmLianceReturn.setIsConfirm(Constants.CONFIRM);
			}
			//借进归还
			List<TmLianceReturn> results = statisticsStockInOutService.getLianceReturnTypeBill(tmLianceReturn);
			ActionContext.getContext().put("e3TableCaption", "借进归还单");
			ActionContext.getContext().put("stockType",4);
			ActionContext.getContext().put("results", results);
			
		}else if(elementValue.equals(StockTypeElements.LOANREGISTER.getElementValue())){
			
			//借出登记
			List<TmLoanRegVo> results = statisticsStockInOutService.getLoanRegisterTypeBill(tmLoanRegister);
			ActionContext.getContext().put("e3TableCaption", "借出登记单");
			ActionContext.getContext().put("stockType",5);
			ActionContext.getContext().put("results", results);
			
		}else if(elementValue.equals(StockTypeElements.LOANRETURN.getElementValue())){
			if(tmLoanReturn == null){
				tmLoanReturn = new TmLoanReturn();
				tmLoanReturn.setIsConfirm(Constants.CONFIRM);
			}
			//借出归还
			List<TmLoanReturn> results = statisticsStockInOutService.getLoanReturnTypeBill(tmLoanReturn);
			ActionContext.getContext().put("e3TableCaption", "借出归还单");
			ActionContext.getContext().put("stockType",6);
			ActionContext.getContext().put("results", results);
		}else if(elementValue.equals(StockTypeElements.MAINTAIN.getElementValue())){
			//维修发料
//			List<TbMaintianVo> results = statisticsStockInOutService.getMaintainTypeBill(null);
			List<TbMaintianVo> results =tbMaintainPartContentService.getTbMaintianDetailVos(null,tbMaintainPartContent);
			ActionContext.getContext().put("e3TableCaption", "维修发料单");
			ActionContext.getContext().put("stockType",7);
			ActionContext.getContext().put("results", results);
			ActionContext.getContext().getSession().put("maintainPartConents_session", results);
		}
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件期间收发存统计
	 * @Date      2010-7-6
	 * @Function  
	 * @return
	 */
	public String getPartReceiveListStat(){
		List<TbPartReceiverStatVo> results = statisticsStockInOutService.getPartReceiveListStat(tbPartReceiverStatVo);
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		
		TbPartReceiverStatVo statTotal = statisticsStockInOutService.getPartReceiveTotalStat(tbPartReceiverStatVo);
		
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("results",results);
		ActionContext.getContext().put("statTotal",statTotal);
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件月度期间收发存统计
	 * @Date      2010-7-6
	 * @Function  
	 * @return
	 */
	public String getPartMounthReceiveListStat(){
		Map<TbPartReceiverStatVo, List<TbPartReceiverStatVo>> mapResults = statisticsStockInOutService.getPartReceiveListStat(tbPartReceiverStatVo,qryyear,qrymounth);
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		
		setYearMounthMap();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		
		if(mapResults != null){
			for(TbPartReceiverStatVo key : mapResults.keySet()){
				ActionContext.getContext().put("results",mapResults.get(key));
				ActionContext.getContext().put("statTotal",key);
			}
		}
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 仓库概貌统计
	 * @Date      2010-7-8
	 * @Function  
	 * @return
	 */
	public String getStoreHouseSurveyStat(){
		
		String storeStatType = request.getParameter("storeStatType");
		
		if(StringUtils.isBlank(storeStatType)){
			List<TbStoreHouseSurveyVo> results = statisticsStockInOutService.getStoreHouseSurveyStat();
			TbStoreHouseSurveyVo countResult = statisticsStockInOutService.getStoreHouseSurveyTotalStat();
			
			ActionContext.getContext().put("results", results);
			ActionContext.getContext().put("countResult", countResult);
			ActionContext.getContext().put("storeStatType", 1);
			return Constants.SUCCESS;
		}
		
		if(storeStatType.equals("1")){
			
			List<TbStoreHouseSurveyVo> results = statisticsStockInOutService.getStoreHouseSurveyStat();
			TbStoreHouseSurveyVo countResult = statisticsStockInOutService.getStoreHouseSurveyTotalStat();
			
			ActionContext.getContext().put("results", results);
			ActionContext.getContext().put("countResult", countResult);
			ActionContext.getContext().put("storeStatType", 1);
			
		}else if(storeStatType.equals("2")){
			
			List<TbStoreHouseSurveyVo> results = statisticsStockInOutService.getCarModelTypeSurveyStat();
			TbStoreHouseSurveyVo countResult = statisticsStockInOutService.getCarModelTypeSurveyTotalStat();
			
			ActionContext.getContext().put("results", results);
			ActionContext.getContext().put("countResult", countResult);
			ActionContext.getContext().put("storeStatType", 2);
		}
		
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 配件信息查询
	 * @Date      2010-7-9
	 * @Function  
	 * @return
	 */
	public String findTbPartInfoReFlowStat(){
		
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		request.setAttribute("tmStoreHouseList", tmStoreHouseService.findAll());
		request.setAttribute("tbPartInfoList", tbPartInfoList);
		
//		List<TbPartInfoReFlowStatVo> typeResults = statisticsStockInOutService.getAllTypeSumReFlowStat(null);
//		ActionContext.getContext().put("typeResults", typeResults);
//		List<TbPartInfoReFlowStatVo> results = statisticsStockInOutService.getPartInfoReFlowDetailStat(null, null,tbPartInfoReFlowStatVo);
//		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件流向及流量查询 --   所有出入库类型数量统计
	 * @Date      2010-7-9
	 * @Function  
	 * @return
	 */
	public String getAllTypeSumReFlowStat(){
		request.setAttribute("tmStoreHouseList", tmStoreHouseService.findAll());
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		List<TbPartInfoReFlowStatVo> typeResults = statisticsStockInOutService.getAllTypeSumReFlowStat(tbPartInfoList);
		ActionContext.getContext().put("typeResults", typeResults);
		return Constants.SUCCESS;
	}

	/**
	 *  配件流向及流量统计 -- 所有配件流量明细
	 * @Date      2010-7-9
	 * @Function  
	 * @return
	 */
	public String getPartInfoReFlowDetailStat(){
		List<TbPartInfo> tbPartInfoList = tbPartInfoService.findByEntity(tbPartInfo);
		
		request.setAttribute("tmStoreHouseList", tmStoreHouseService.findAll());
		List<TbPartInfoReFlowStatVo> results = statisticsStockInOutService.getPartInfoReFlowDetailStat(tbPartInfoList, tbPartInfoReFlowStatVo.getTypeValue(),tbPartInfoReFlowStatVo);
		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件出库排行榜
	 * @Date      2010-7-12
	 * @Function  
	 * @return
	 */
	public String getToppartInfoStockOutStat(){
		//得到所有出库类型
		Map<Long, String> elementsMap = statisticsStockInOutService.getStockOutElementMap();
		ActionContext.getContext().put("elementsMap", elementsMap);
		//得到所有仓库
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		Map<TbPartInfoStockOutVo,List<TbPartInfoStockOutVo>> map = statisticsStockInOutService.getToppartInfoStockOut(tbPartInfoStockOutVo);
		for(TbPartInfoStockOutVo voKey : map.keySet()){
			ActionContext.getContext().put("results", map.get(voKey));
			ActionContext.getContext().put("countVo", voKey);
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 仓库期间收发统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public String getStoreHouseReceiverStat(){
		//得到所有仓库
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		List<TmStoreHouseReceiverStatVo> results = statisticsStockInOutService.getStoreHouseReceiverStat(tmStoreHouseReceiverStatVo);
		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
	}
	
	/**
	 * 仓库月度期间收发统计
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public String getStoreHouseMounthReceiverStat(){
		Map<String, String>  yearMap = new LinkedHashMap<String, String>();
		yearMap.put("2010", "2010");yearMap.put("2011", "2011");yearMap.put("2012", "2012");
		yearMap.put("2013", "2013");yearMap.put("2014", "2014");yearMap.put("2015", "2015");
		Map<String, String>  mounthMap = new LinkedHashMap<String, String>();
		mounthMap.put("01", "01");mounthMap.put("02", "02");mounthMap.put("03", "03");mounthMap.put("04", "04");mounthMap.put("05", "05");mounthMap.put("06", "06");
		mounthMap.put("07", "07");mounthMap.put("08", "08");mounthMap.put("09", "09");mounthMap.put("10", "10");mounthMap.put("11", "11");mounthMap.put("12", "12");
		
		//得到所有仓库
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		List<TmStoreHouseReceiverStatVo> results = statisticsStockInOutService.getStoreHouseReceiverStatByMonth(qryyear, qrymounth);
		ActionContext.getContext().put("results", results);
		ActionContext.getContext().put("yearMap", yearMap);
		ActionContext.getContext().put("mounthMap", mounthMap);
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件领用统计
	 * @return
	 */
	public String getDrawStockOutStat(){
		List<TmDrawStatVo> results = statisticsStockInOutService.getDrawStockOutStat(tmDrawStatVo);
		Map<TmDrawStatVo, List<TmDrawStatVo>> map = statisticsStockInOutService.getGroupDrawStockOutStat(tmDrawStatVo);
		
		ActionContext.getContext().put("results", results);
		for(TmDrawStatVo voKey : map.keySet()){
			ActionContext.getContext().put("groupResults", map.get(voKey));
			ActionContext.getContext().put("countVo", voKey);
		}
		
		List<TmUser> tmusers = tmUserService.findAll();
		List<TmDepartment> tmDepartments = tmDepartmentService.findAll();
		ActionContext.getContext().put("tmusers", tmusers);
		ActionContext.getContext().put("tmDepartments", tmDepartments);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 配件每日出库查询
	 * @Date      2010-7-21
	 * @Function  
	 * @return
	 */
	public String getDailyStockOutStat(){
		//得到所有出库类型
		Map<Long, String> elementsMap = statisticsStockInOutService.getStockOutElementMap();
		ActionContext.getContext().put("elementsMap", elementsMap);
		
		//得到所有仓库
		List<TmStoreHouse> stroeHouseList = tmStoreHouseService.findAll();
		ActionContext.getContext().put("stroeHouseList", stroeHouseList);
		
		Map<DailyStockOutVo, List<DailyStockOutVo>> map = statisticsStockInOutService.getDailyStockOutStat(dailyStockOutVo);
		for(DailyStockOutVo voKey : map.keySet()){
			ActionContext.getContext().put("results", map.get(voKey));
			ActionContext.getContext().put("countVo", voKey);
		}
		return Constants.SUCCESS;
	}
	/******************************************************************************/
	
	
	/**
	 * 未结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	
	public String getNotBalanceFixSellCostDetailStat(){
		
		List<BalanceFixSellVo> results = statisticsStockInOutService.getNotBalanceFixSellCostDetail(balanceFixSellVo);
		
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmFixType> tmFixTypeList  = tmFixTypeService.findAll();
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("tmFixTypeList", tmFixTypeList);
		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 已结算修理销售成本
	 * @param balanceFixSellVo
	 * @return
	 */
	
	public String getIsBalanceFixSellCostDetailStat(){
		
		List<BalanceFixSellVo> results = statisticsStockInOutService.getIsBalanceFixSellCostDetail(balanceFixSellVo);
		
		List<TmCarModelType> carModelTypeList = tmCarModelTypeService.findAll();
		List<TmFixType> tmFixTypeList  = tmFixTypeService.findAll();
		List<TmUser> tmUserList = tmUserService.findAll();
		
		ActionContext.getContext().put("carModelTypeList", carModelTypeList);
		ActionContext.getContext().put("tmFixTypeList", tmFixTypeList);
		ActionContext.getContext().put("tmUserList", tmUserList);
		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 打印维修发料汇总
	 * @return
	 */
	public String printStatMaintain(){
		List<TmCompany> tmCompanys = tmCompanyService.findAll();
		
		List<TbMaintianVo> results =tbMaintainPartContentService.getTbMaintianDetailVos(null,this.tbMaintainPartContent);
		ActionContext.getContext().put("companyName", tmCompanys.get(0).getCompanyName());
		ActionContext.getContext().put("results", results);
		return Constants.SUCCESS;
	}
	
	@Autowired
	private ITmCompanyService tmCompanyService;
	
	
	
	public String printStatStockOut(){
		List<TmStockOutDetVo> stockOutDetVos = 	tmStockOutService.getStockOutDetVos(Constants.CONFIRM, null, elementValue);
	    for(TmStockOutDetVo outvo : stockOutDetVos){
	    	
	    	TmStockOut tmstockout = this.tmStockOutService.findById(outvo.getStockOutId());
			outvo.setOutType(tmstockout.getOutType());
			outvo.setOutTypeName(StockTypeElements.getElementMap().get(outvo.getOutType()));
	    	if(tmstockout.getOutType().equals(StockTypeElements.DRAWSTOCKOUT.getElementValue())){
	    		if(tmstockout.getDrawPeople() != null){	    			
	    			String userRealName = tmUserService.findById(tmstockout.getDrawPeople()).getUserRealName();
	    			outvo.setUserRealName(userRealName);
	    		}
	    	}else{
	    		outvo.setUserRealName(null);
	    	}
	    }
		List<TmCompany> tmCompanys = tmCompanyService.findAll();
		ActionContext.getContext().put("companyName", tmCompanys.get(0).getCompanyName());
		ActionContext.getContext().put("results", stockOutDetVos);
		return Constants.SUCCESS;
	}
	
	
	
	/**
	 * 所有配件出库打印查询
	 * @Date      2010-7-21
	 * @Function  
	 * @return
	 */
	public String printAllStockOutStat(){
		Map<DailyStockOutVo, List<DailyStockOutVo>> map = statisticsStockInOutService.getDailyStockOutStat(dailyStockOutVo);
		for(DailyStockOutVo voKey : map.keySet()){
			ActionContext.getContext().put("results", map.get(voKey));
			ActionContext.getContext().put("countVo", voKey);
		}
		if(dailyStockOutVo != null ){
			
			String beginDate = dailyStockOutVo.getBeginDate();
			String endDate = dailyStockOutVo.getEndDate();
			ActionContext.getContext().put("beginDate", beginDate);
			ActionContext.getContext().put("endDate", endDate);
		}
		return Constants.SUCCESS;
	}
	
	private void setYearMounthMap(){
		
		Map<String, String>  yearMap = new LinkedHashMap<String, String>();
		yearMap.put("2010", "2010");yearMap.put("2011", "2011");yearMap.put("2012", "2012");
		yearMap.put("2013", "2013");yearMap.put("2014", "2014");yearMap.put("2015", "2015");
		Map<String, String>  mounthMap = new LinkedHashMap<String, String>();
		mounthMap.put("01", "01");mounthMap.put("02", "02");mounthMap.put("03", "03");mounthMap.put("04", "04");mounthMap.put("05", "05");mounthMap.put("06", "06");
		mounthMap.put("07", "07");mounthMap.put("08", "08");mounthMap.put("09", "09");mounthMap.put("10", "10");mounthMap.put("11", "11");mounthMap.put("12", "12");
		ActionContext.getContext().put("yearMap", yearMap);
		ActionContext.getContext().put("mounthMap", mounthMap);
	}
	
	
	/**
	 * 配件月度期间收发存打印
	 * @Date      2010-7-6
	 * @Function  
	 * @return
	 */
	public String printPartMounthReceiveList(){
		Map<TbPartReceiverStatVo, List<TbPartReceiverStatVo>> mapResults = statisticsStockInOutService.getPartReceiveListStat(tbPartReceiverStatVo,qryyear,qrymounth);
		
		if(mapResults != null){
			for(TbPartReceiverStatVo key : mapResults.keySet()){
				ActionContext.getContext().put("results",mapResults.get(key));
				ActionContext.getContext().put("statTotal",key);
			}
		}
		ActionContext.getContext().put("qryDate",qryyear+"-"+qrymounth);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 仓库月度期间收发打印
	 * @Date      2010-7-13
	 * @Function  
	 * @return
	 */
	public String printStoreHouseMounthReceiver(){
		
		List<TmStoreHouseReceiverStatVo> results = statisticsStockInOutService.getStoreHouseReceiverStatByMonth(qryyear, qrymounth);
		ActionContext.getContext().put("results", results);
		ActionContext.getContext().put("qryDate",qryyear+"-"+qrymounth);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 收发单据采购入库导出
	 * @return
	 * @throws Exception
	 */
	public String cgrkStatExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "采购入库";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();

		List<TmStockInDetailVo> stockInDetails = (List<TmStockInDetailVo>)ActionContext.getContext().getSession().get("stockInDetails_session");
		
		stockXLSImportService.cgrkStatisticsExportXls(os, "/stockxlsimport/TmStockIn_cg_export_xls_tpl.properties", stockInDetails);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
	/**
	 * 收发单据维修发料导出
	 * @return
	 * @throws Exception
	 */
	public String wxflStatExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "维修发料";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();

		List<TbMaintianVo> maintainDetails = (List<TbMaintianVo>)ActionContext.getContext().getSession().get("maintainPartConents_session");
		
		stockXLSImportService.wxflStatisticsExportXls(os, "/stockxlsimport/TbMaintain_export_xls_tpl.properties", maintainDetails);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
	/**
	 * 采购入库-采购明细导出
	 * @return
	 * @throws Exception
	 */
	public String stockInDetailsExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "采购入库";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();

		List<TmStockInDetailVo> stockInDetails = null;
		
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
			tmStockIn.setBusType(1L);
		}
		
		if(tmStockIn.getBusType() == 1L){
			//采购入库明细查询
			stockInDetails = tmStockinDetailService.getSellDetailByStat(tmStockIn);
		}else if(tmStockIn.getBusType() == 2L){
			stockInDetails = new ArrayList<TmStockInDetailVo>();
			Double stockTotalPrice =  0D;
			//采购退货明细查询
			 List<TmStockOutDetVo> stockOutDetails = tmStockOutService.getStockOutDetVos(tmStockIn);
				for(TmStockOutDetVo outVo : stockOutDetails){
					TmStockInDetailVo  detVo = new TmStockInDetailVo();
					detVo.setPartinfoId(outVo.getPartinfoId());
					detVo.setPartCode(outVo.getPartCode());
					detVo.setPartName(outVo.getPartName());
					detVo.setHouseName(outVo.getHouseName());
					detVo.setCustomerId(outVo.getCustomerId());
					detVo.setCustomerCode(outVo.getCustomerCode());
					detVo.setCustomerName(outVo.getCustomerName());
					detVo.setArriveDate(outVo.getStockOutDate());
					detVo.setQuantity(outVo.getQuantity());
					detVo.setCostPrice(outVo.getPrice());
					detVo.setStockInCode(outVo.getStockOutCode());
					stockInDetails.add(detVo);
					stockTotalPrice += detVo.getQuantity()*detVo.getCostPrice();
				}
		}else if(tmStockIn.getBusType() == 3L){
			//采购入库+采购退货明细查询
			Double stockTotalPrice = tmStockinDetailService.getStockInTotalPriceByStat(tmStockIn);
			stockInDetails  = tmStockinDetailService.getSellDetailByStat(tmStockIn);
			List<TmStockOutDetVo> stockOutDetails = tmStockOutService.getStockOutDetVos(tmStockIn);
			for(TmStockOutDetVo outVo : stockOutDetails){
				TmStockInDetailVo  detVo = new TmStockInDetailVo();
				detVo.setPartinfoId(outVo.getPartinfoId());
				detVo.setPartCode(outVo.getPartCode());
				detVo.setPartName(outVo.getPartName());
				detVo.setHouseName(outVo.getHouseName());
				detVo.setCustomerId(outVo.getCustomerId());
				detVo.setCustomerCode(outVo.getCustomerCode());
				detVo.setCustomerName(outVo.getCustomerName());
				detVo.setArriveDate(outVo.getStockOutDate());
				detVo.setQuantity(outVo.getQuantity());
				detVo.setCostPrice(outVo.getPrice());
				detVo.setStockInCode(outVo.getStockOutCode());
				stockInDetails.add(detVo);
				stockTotalPrice -= detVo.getQuantity()*detVo.getCostPrice();
			}
		}
		
		stockXLSImportService.stockInDetailsExportXls(os, "/stockxlsimport/TmStockInDetailVo_export_xls_tpl.properties", stockInDetails);
		
		os.flush();
		
		os.close();
		
		return null;
	}
	
	
	
	/**
	 * 配件出库排行榜 导出
	 * @Date      2010-7-12
	 * @Function  
	 * @return
	 */
	public String getTopPartStockoutExportXls() throws Exception{
		
		response.reset();
		
		response.setCharacterEncoding("UTF-8");
			
		String name = "配件出库排行榜";
			
		name = URLEncoder.encode(name, "UTF-8");
			
		response.setHeader("Content-Disposition", "attachment;filename="+ new String(name.getBytes("UTF-8"), "GBK") + ".xls");
			
		response.setContentType("application/vnd.ms-excel");
			
		OutputStream os = response.getOutputStream();
		
		//得到所有仓库
		Map<TbPartInfoStockOutVo,List<TbPartInfoStockOutVo>> map = statisticsStockInOutService.getToppartInfoStockOut(tbPartInfoStockOutVo);
		List<TbPartInfoStockOutVo> result = null;
		TbPartInfoStockOutVo tbPartInfoStockOutVo =null;
		for(TbPartInfoStockOutVo voKey : map.keySet()){
			result =  map.get(voKey);
			tbPartInfoStockOutVo = voKey;
		}
		
		stockXLSImportService.topPartStockoutExportXls(os, "/stockxlsimport/TbPartInfoStockOutVo_export_xls_tpl.properties", result);
		
		os.flush();
		
		os.close();
		
		return null;
	}
}
