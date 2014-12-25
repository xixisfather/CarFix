package com.selfsoft.business.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.model.TmSoleType;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmProjectTypeService;
import com.selfsoft.baseparameter.service.ITmSoleTypeService;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.business.vo.BalanceSellCountVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockOutDetVo;
import com.selfsoft.business.vo.TmStockOutVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TmStockOutAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	private static final long serialVersionUID = 506216537328796527L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITmStockoutDetailService tmStockoutDetailService;
	@Autowired
	private ITmSoleTypeService tmSoleTypeService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmProjectTypeService tmProjectTypeService;
	
	private TmStockOut tmStockOut;
	
	private Double totalQuantity;
	 
	private Double totalPrice;
	
	private Map<String,String> formMap = new HashMap<String,String>();
	
	
	public Map<String, String> getFormMap() {
		return formMap;
	}

	public void setFormMap(Map<String, String> formMap) {
		this.formMap = formMap;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public TmStockOut getTmStockOut() {
		return tmStockOut;
	}

	public void setTmStockOut(TmStockOut tmStockOut) {
		this.tmStockOut = tmStockOut;
	}

	/**
	 * 进入销售出库页面
	 * @return
	 */
	public String initSellPage(){
		request.setAttribute("insertAction", "insertSellAction");
		request.setAttribute("initPageAction", "initSellPageAction");
		request.setAttribute("updateAction", "updateSellStateAction");
		request.setAttribute("gtitle", "销售出库单");
		request.setAttribute("stockOutTypeName", "销售");

		//默认销售类型
		TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
		List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
		tsyList.add(tsy);
		request.setAttribute("tsyList", tsyList);
		request.setAttribute("zlMap", Constants.zlMap());
		request.setAttribute("xmlxMap", Constants.xmlxMap());
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 进入配件领用页面
	 * @return
	 */
	public String initDrawPage(){
		request.setAttribute("insertAction", "insertDrawAction");
		request.setAttribute("initPageAction", "initDrawPageAction");
		request.setAttribute("updateAction", "updateDrawStateAction");
		request.setAttribute("gtitle", "配件领用单");
		request.setAttribute("stockOutTypeName", "领用");
		return Constants.SUCCESS;
	}
	
	
	
	/**
	 * 进入配件报损页面
	 * @return
	 */
	public String initShatterPage(){
		request.setAttribute("insertAction", "insertShatterAction");
		request.setAttribute("initPageAction", "initShatterPageAction");
		request.setAttribute("updateAction", "updateShatterStateAction");
		request.setAttribute("gtitle", "配件报损单");
		request.setAttribute("stockOutTypeName", "报损");
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入调拨出库页面
	 * @return
	 */
	public String initAllotPage(){
		request.setAttribute("insertAction", "insertAllotAction");
		request.setAttribute("initPageAction", "initAllotPageAction");
		request.setAttribute("updateAction", "updateAllotStateAction");
		request.setAttribute("gtitle", "调拨出库单");
		request.setAttribute("stockOutTypeName", "调出");
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入采购退货页面
	 * @return
	 */
	public String initStockReturnPage(){
		request.setAttribute("insertAction", "");
		request.setAttribute("initPageAction", "");
		request.setAttribute("updateAction", "");
		request.setAttribute("gtitle", "采购退货单");
		request.setAttribute("stockOutTypeName", "调出");
		return Constants.SUCCESS;
	}
	
	/**
	 * 新增销售出库基本信息 以及销售出库明细
	 * @throws IOException
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String insertSell() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		
		
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.SELLSTOCKOUT.getElementString()));
		tmStockOut.setUserId(new Long(userId));
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setTotalPrice(totalPrice);										//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);									//总数量
		tmStockOut.setOutType(StockTypeElements.SELLSTOCKOUT.getElementValue());	//出库类型为销售
		tmStockOut.setIsConfirm(new Long(isConfirm));								//保存或确认
		//保存销售出库单
		//保存销售出库详细内容
		try {
			tmStockoutDetailService.insertBatchStockOutDetail(tmStockOut,new Long(isConfirm), partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成采购单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 更新销售出库单下所有配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateSellState() throws NumberFormatException, MinusException{
		String id =request.getParameter("id");
		tmStockOut  = tmStockOutService.findById(new Long(id));
		tmStockOut.setIsConfirm(8002L);
		tmStockOutService.update(tmStockOut);
		tmStockoutDetailService.updatePartInfoQuantity(new Long(id));
	}
	
	
	/**
	 * 新增领用出库基本信息 以及领用出库明细
	 * @throws IOException
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String insertDraw() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		
		
		
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.DRAWSTOCKOUT.getElementString()));
		tmStockOut.setUserId(new Long(userId));
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setTotalPrice(totalPrice);										//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);									//总数量
		tmStockOut.setOutType(StockTypeElements.DRAWSTOCKOUT.getElementValue());	//出库类型为领用
		tmStockOut.setIsConfirm(new Long(isConfirm));								//保存或确认
		//保存领用出库单
		//保存领用出库和详细内容
		try {
			tmStockoutDetailService.insertBatchStockOutDetail(tmStockOut,new Long(isConfirm),  partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成领用单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 更新销售出库单下所有配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateDrawState() throws NumberFormatException, MinusException{
		String id =request.getParameter("id");
		tmStockOut  = tmStockOutService.findById(new Long(id));
		tmStockOut.setIsConfirm(8002L);
		tmStockOutService.update(tmStockOut);
		tmStockoutDetailService.updatePartInfoQuantity(new Long(id));
	}
	
	
	/**
	 * 新增报损出库基本信息 以及报损出库明细
	 * @throws IOException
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String insertShatter() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		
		
		
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.SHATTERSTOCKOUT.getElementString()));
		tmStockOut.setUserId(new Long(userId));
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setTotalPrice(totalPrice);										//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);									//总数量
		tmStockOut.setOutType(StockTypeElements.SHATTERSTOCKOUT.getElementValue());	//出库类型为报损
		tmStockOut.setIsConfirm(new Long(isConfirm));								//保存或确认
		//保存报损出库单
		//保存报损出库详细内容
		try {
			tmStockoutDetailService.insertBatchStockOutDetail(tmStockOut,new Long(isConfirm), partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		
		ActionContext.getContext().put("msg","生成报损单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 更新报损出库单下所有配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateShatterState() throws NumberFormatException, MinusException{
		String id =request.getParameter("id");
		tmStockOut  = tmStockOutService.findById(new Long(id));
		tmStockOut.setIsConfirm(8002L);
		tmStockOutService.update(tmStockOut);
		tmStockoutDetailService.updatePartInfoQuantity(new Long(id));
	}
	
	
	/**
	 * 新增调拨出库基本信息 以及调拨出库明细
	 * @throws IOException
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String insertAllot() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		
		
		
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.ALLOTSTOCKOUT.getElementString()));
		tmStockOut.setUserId(new Long(userId));
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setTotalPrice(totalPrice);										//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);									//总数量
		tmStockOut.setOutType(StockTypeElements.ALLOTSTOCKOUT.getElementValue());	//出库类型为调拨
		tmStockOut.setIsConfirm(new Long(isConfirm));								//保存或确认
		//保存调拨出库单
		//保存调拨出库详细内容
		try {
			tmStockoutDetailService.insertBatchStockOutDetail(tmStockOut,new Long(isConfirm), partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成调拨单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 更新调拨出库单下所有配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateAllotState() throws NumberFormatException, MinusException{
		String id =request.getParameter("id");
		tmStockOut  = tmStockOutService.findById(new Long(id));
		tmStockOut.setIsConfirm(8002L);
		tmStockOutService.update(tmStockOut);
		tmStockoutDetailService.updatePartInfoQuantity(new Long(id));
	}
	
	/**
	 * 新增采购退货，及其退货明细
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String insertStockReturn() throws IOException, NumberFormatException{
		
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		
		
		
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.SELLRETURN.getElementString()));
		tmStockOut.setUserId(new Long(userId));
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setOutType(StockTypeElements.STOCKRETURN.getElementValue());		//出库类型为采购退货
		tmStockOut.setIsConfirm(new Long(isConfirm));								//保存或确认
		//保存采购退货单
		//保存采购退货详细内容
		try {
			tmStockoutDetailService.insertBatchStockOutDetail(tmStockOut,new Long(isConfirm), partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
		
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		
		ActionContext.getContext().put("msg","生成采购退货单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 更新采购退货单下所有配件数量
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateStockReturnState() throws NumberFormatException, MinusException{
		String id =request.getParameter("id");
		tmStockOut  = tmStockOutService.findById(new Long(id));
		tmStockOut.setIsConfirm(Constants.CONFIRM);
		
		tmStockoutDetailService.updateStockReturn(tmStockOut);
	}
	
	
	private Long stockOutId ;
	
	
	public Long getStockOutId() {
		return stockOutId;
	}

	public void setStockOutId(Long stockOutId) {
		this.stockOutId = stockOutId;
	}

	/**
	 * 销售退货
	 * @Date      2010-6-7
	 * @Function  
	 * @return
	 */
	public String findSellReturn(){
		List<TmStockOutDetVo>  detVos = null;
		if(stockOutId != null)
			detVos = tmStockOutService.getStockOutDetVos(Constants.CONFIRM,stockOutId,StockTypeElements.SELLSTOCKOUT.getElementValue());
		request.setAttribute("detVos", detVos);
		
		return Constants.SUCCESS;
	}
	
	
	/************************************修改 操作*****************************************************/
	
	/**
	 * 查询销售出库单
	 */
	public String findSell(){
		String isConfirms = Constants.CONFIRM+","+Constants.NOT_CONFIRM+","+Constants.RE_BALANCE;
		List<TmStockOutVo> stockOutList = tmStockOutService.getAllTypeStockOutDetVo(isConfirms, StockTypeElements.SELLSTOCKOUT.getElementValue(),tmStockOut);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 进入修改销售出库页面
	 * @return
	 */
	public String initEditSellPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM, tmStockOut.getId(), StockTypeElements.SELLSTOCKOUT.getElementValue());
		
		request.setAttribute("detVos", detVos);
		request.setAttribute("customer", customer);
		//默认销售类型
		TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
		List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
		tsyList.add(tsy);
		request.setAttribute("tsyList", tsyList);
		
		request.setAttribute("insertAction", "updateSellAction");
		request.setAttribute("initPageAction", "findSellAction");
		request.setAttribute("updateAction", "updateSellStateAction");
		
		request.setAttribute("freeFlagTypes", tmStockOutService.getAllFreeFlagMapType());
		request.setAttribute("zlMap", Constants.zlMap());
		request.setAttribute("xmlxMap", Constants.xmlxMap());
		request.setAttribute("tmprojectTypes", tmProjectTypeService.findAll());
		return Constants.EDITPAGE;
	}
	
	
	
	/**
	 * 更新销售出库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateSell() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockOut.setIsConfirm(new Long(isConfirm));
		try {
			tmStockoutDetailService.updateBatchStockOutDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	
	/**
	 * 查询调拨出库单
	 */
	public String findAllotOut(){
		List<TmStockOutVo> stockOutList = tmStockOutService.getStockOutVos(Constants.NOT_CONFIRM+"", StockTypeElements.ALLOTSTOCKOUT.getElementValue(),tmStockOut,null);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 进入修改调拨出库页面
	 * @return
	 */
	public String initEditAllotOutPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM,  tmStockOut.getId(), StockTypeElements.ALLOTSTOCKOUT.getElementValue());
		
		request.setAttribute("detVos", detVos);
		request.setAttribute("customer", customer);
		
		request.setAttribute("insertAction", "updateAllotOutAction");
		request.setAttribute("initPageAction", "findAllotOutAction");
		request.setAttribute("updateAction", "updateAllotStateAction");
		return Constants.EDITPAGE;
	}
	
	
	/**
	 * 更新调拨出库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateAllotOut() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockOut.setIsConfirm(new Long(isConfirm));
		try {
			tmStockoutDetailService.updateBatchStockOutDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询报损出库单
	 */
	public String findShatter(){
		List<TmStockOutVo> stockOutList = tmStockOutService.getStockOutVos(Constants.NOT_CONFIRM+"", StockTypeElements.SHATTERSTOCKOUT.getElementValue(),tmStockOut,null);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 进入修改报损出库页面
	 * @return
	 */
	public String initEditShatterPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM,  tmStockOut.getId(), StockTypeElements.SHATTERSTOCKOUT.getElementValue());
		
		request.setAttribute("detVos", detVos);
		
		request.setAttribute("insertAction", "updateShatterAction");
		request.setAttribute("initPageAction", "findShatterAction");
		request.setAttribute("updateAction", "updateShatterStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新报损出库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateShatter() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockOut.setIsConfirm(new Long(isConfirm));
		try {
			tmStockoutDetailService.updateBatchStockOutDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询领用出库单
	 */
	public String findDraw(){
		List<TmStockOutVo> stockOutList = tmStockOutService.getStockOutVos(Constants.NOT_CONFIRM+"", StockTypeElements.DRAWSTOCKOUT.getElementValue(),tmStockOut,null);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 进入修改领用出库页面
	 * @return
	 */
	public String initEditDrawPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM,  tmStockOut.getId(), StockTypeElements.DRAWSTOCKOUT.getElementValue());
		TmUser tmUser = tmUserService.findById(tmStockOut.getDrawPeople());
		request.setAttribute("detVos", detVos);
		request.setAttribute("tmUser", tmUser);
		
		request.setAttribute("insertAction", "updateDrawAction");
		request.setAttribute("initPageAction", "findDrawAction");
		request.setAttribute("updateAction", "updateDrawStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新领用出库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateDraw() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockOut.setIsConfirm(new Long(isConfirm));
		try {
			tmStockoutDetailService.updateBatchStockOutDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询采购退货库单
	 */
	public String findStockReturn(){
		List<TmStockOutVo> stockOutList = tmStockOutService.getStockOutVos(Constants.NOT_CONFIRM+"", StockTypeElements.STOCKRETURN.getElementValue(),tmStockOut,null);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 进入修改采购退货页面
	 * @return
	 */
	public String initEditStockReturnPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM, tmStockOut.getId(), StockTypeElements.STOCKRETURN.getElementValue());
		List<TmStockInDetailVo> detInVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.STOCK.getElementValue(),tmStockOut.getStockInId(),Constants.CONFIRM);
		TmStockIn tmStockIn = tmStockInService.findById(tmStockOut.getStockInId());
		
		request.setAttribute("tmStockIn", tmStockIn);
		request.setAttribute("detVos", detVos);
		request.setAttribute("detInVos", detInVos);
		request.setAttribute("insertAction", "updateStockReturnAction");
		request.setAttribute("initPageAction", "findStockReturnAction");
		request.setAttribute("updateAction", "updateStockReturnStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新采购退货
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateStockReturn() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockOut.setIsConfirm(new Long(isConfirm));
		try {
			tmStockoutDetailService.updateBatchStockOutDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockOut.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	/********************************删除 出库************************************************/
	
	/**
	 * 删除所有入库类型的单据
	 */
	public void deleteStockOut() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmStockOutService.deleteStockOut(new Long(id));
			if(flag)
				response.getWriter().print("tmStockOutTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tmStockOutTable," + Constants.FAILURE);
		}
	} 
	
	
	/*************************/
	
	
	/**
	 * 进入修改销售出库页面(针对8002已出库,8006再结算)
	 * @return
	 */
	public String initEditConfirmSellPage(){
		String id = request.getParameter("id");
		tmStockOut = tmStockOutService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmStockOut.getCustomerBill());
		List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.CONFIRM, tmStockOut.getId(), StockTypeElements.SELLSTOCKOUT.getElementValue());
		
		request.setAttribute("detVos", detVos);
		request.setAttribute("customer", customer);
		//默认销售类型
		TmSoleType tsy = tmSoleTypeService.getDefaultSoleType();
		List<TmSoleType> tsyList = new ArrayList<TmSoleType>();
		tsyList.add(tsy);
		request.setAttribute("tsyList", tsyList);
		
		request.setAttribute("insertAction", "updateSellAction");
		request.setAttribute("initPageAction", "findSellAction");
		request.setAttribute("updateAction", "updateSellStateAction");
		request.setAttribute("freeFlagTypes", tmStockOutService.getAllFreeFlagMapType());
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新销售出库(针对8002已出库,8006再结算)
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateConfirmSell() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		try {
			tmStockoutDetailService.updateConfrimSellDetail(tmStockOut, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","出库数量不能大于库存量");
			return Constants.FAILURE;
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 得到某个配件卖个客户最近一次的销售价
 	 * 若销售价为0时则取配件的默认销售价
	 * @throws IOException
	 */
	public void getStockOutCustomerSellprice() throws IOException{
	
		String partIds = request.getParameter("partIds");
		
		String customerId = request.getParameter("customerId");
		
		String  sellPrices = tmStockoutDetailService.getCustomerSellPriceByPartIds(partIds, new Long(customerId));
		
		response.getWriter().print(sellPrices);
	}
	
	
	
	/**
	 * 查询内部领用出库单
	 */
	public String findInnerStockOut(){
		String isConfirms = Constants.NOT_CONFIRM+"";
		List<TmStockOutVo> stockOutList = tmStockOutService.getAllTypeStockOutDetVo(isConfirms, StockTypeElements.INNERSTOCKOUT.getElementValue(),tmStockOut);
		request.setAttribute("stockOutList", stockOutList);
		return Constants.SUCCESS;
		
	}
	
	public String insertInnerStockOut() throws IOException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		tmStockOut.setStockOutCode(tmDictionaryService.GenerateCode(StockTypeElements.INNERSTOCKOUT.getElementString()));
		tmStockOut.setUserId(userId);
		tmStockOut.setCreateDate(new Date());
		tmStockOut.setTotalPrice(totalPrice);												//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);											//总数量
		tmStockOut.setOutType(StockTypeElements.INNERSTOCKOUT.getElementValue());			//入库类型为采购
		tmStockOut.setIsConfirm(8000L);														//保存或确认
		
		tmStockoutDetailService.insertBatchStock(formMap, tmStockOut);
		
		ActionContext.getContext().put("msg","生成采购单号:" + tmStockOut.getStockOutCode());
		return Constants.SUCCESS;
		
	}

	public String forwardInnerStockOutPage() throws Exception {
		
		String id = request.getParameter("id");
		

		if (StringUtils.isNotBlank(id)) {
			tmStockOut = tmStockOutService.findById(new Long(id));
			TmUser drawPeople = tmUserService.findById(tmStockOut.getDrawPeople());
			List<TmStockOutDetVo> detVos = tmStockOutService.getStockOutDetVos(Constants.NOT_CONFIRM, tmStockOut.getId(), StockTypeElements.INNERSTOCKOUT.getElementValue());
			request.setAttribute("detVos", detVos);
			request.setAttribute("drawPeople", drawPeople);
			return Constants.EDITPAGE;

		}
		return Constants.ADDPAGE;
	}
	
	/**
	 * 更新内部领用
	 * @return
	 * @throws IOException 
	 */
	public String updateInnerStockOut() throws IOException{
		tmStockOut.setTotalPrice(totalPrice);												//税前金额合计
		tmStockOut.setTotalQuantity(totalQuantity);											//总数量
		tmStockOut.setIsConfirm(8000L);
		tmStockoutDetailService.stockBatchUpdate(formMap, tmStockOut);
		return Constants.SUCCESS;
	}
}