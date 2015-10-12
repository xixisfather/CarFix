package com.selfsoft.business.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.selfsoft.baseinformation.model.TbPartInfo;
import com.selfsoft.baseinformation.service.ITbCustomerService;
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.model.TmStockOut;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.business.vo.TmStockInVo;
import com.selfsoft.business.xls.IStockXLSImportService;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.CommonMethod;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
import com.selfsoft.secrity.model.TmUser;
import com.selfsoft.secrity.service.ITmUserService;

public class TmStockInAction extends ActionSupport implements
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
	private ITmStockInService tmStockInService;
	@Autowired
	private ITmStockinDetailService tmStockinDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	@Autowired
	private ITmStockOutService tmStockOutService;
	@Autowired
	private ITbCustomerService tbCustomerService;
	@Autowired
	private ITmUserService tmUserService;
	@Autowired
	private IStockXLSImportService stockXLSImportService;
	
	
	private TmStockIn tmStockIn;
	
	private Double totalQuantity;
	 
	private Double totalPrice;
	
	private Map<String,String> formMap = new HashMap<String,String>();
	
	private Long viewId;
	
	private File fileXls;
	
	public File getFileXls() {
		return fileXls;
	}

	public void setFileXls(File fileXls) {
		this.fileXls = fileXls;
	}
	public Long getViewId() {
		return viewId;
	}

	public void setViewId(Long viewId) {
		this.viewId = viewId;
	}

	public Map<String,String> getFormMap(){
      return formMap;
    }
    public void setFormMap(Map<String,String> _map){
      this.formMap = _map;
    }
    public void setFormValue(String key, String value){
      formMap.put(key, value);
    }
    public Object getFormValue(String key){
      return formMap.get(key);
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

	public TmStockIn getTmStockIn() {
		return tmStockIn;
	}

	public void setTmStockIn(TmStockIn tmStockIn) {
		this.tmStockIn = tmStockIn;
	}
	
	/**
	 * 进入采购入库页面
	 * @return
	 */
	public String initStockInPage(){
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("rates", rates);
		request.setAttribute("insertAction", "insertStockInAction");
		request.setAttribute("initPageAction", "initStockInPageAction");
		request.setAttribute("updateAction", "updateStockInStateAction");
		request.setAttribute("gtitle", "采购入库单");
		
		Map<Long,String> panMentMap = Constants.getPayMentMap();
		request.setAttribute("panMentMap", panMentMap);
		Long userId = (Long) request.getSession().getAttribute("userId");
		TmUser tmUser = tmUserService.findById(userId);
		if(tmStockIn ==  null)
			tmStockIn = new TmStockIn();
		tmStockIn.setUserId(userId);
		tmStockIn.setUserRealName(tmUser.getUserName());
		
		handlerCalendar();
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入调拨入库页面
	 * @return
	 */
	public String initAllotPage(){
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("rates", rates);
		request.setAttribute("insertAction", "insertAllotInAction");
		request.setAttribute("initPageAction", "initAllotInPageAction");
		request.setAttribute("updateAction", "updateAllotStateAction");
		request.setAttribute("gtitle", "调拨入库单");
		handlerCalendar();
		return Constants.SUCCESS;
	}

	/**
	 * 进入其他入库页面
	 * @return
	 */
	public String initOtherStockPage(){
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("rates", rates);
		request.setAttribute("insertAction", "insertOtherStockInAction");
		request.setAttribute("initPageAction", "initOtherStockPageAction");
		request.setAttribute("updateAction", "updateOtherStockStateAction");
		request.setAttribute("gtitle", "其他入库单");
		handlerCalendar();
		return Constants.SUCCESS;
	}
	
	/**
	 * 进入报溢入库页面
	 * @return
	 */
	public String initOverFlowPage(){
		request.setAttribute("insertAction", "insertOverFlowAction");
		request.setAttribute("initPageAction", "initOverFlowPageAction");
		request.setAttribute("updateAction", "updateOverFlowStateAction");
		request.setAttribute("gtitle", "报溢入库单");
		handlerCalendar();
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 添加采购入库单
	 * @return
	 * @throws IOException 
	 */
	public String insertStockIn() throws IOException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		if(null != isConfirm){
			
			if(isConfirm.contains("?")) {
				isConfirm = isConfirm.split("\\?")[0];
			}
			
		}
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.STOCK.getElementString()));
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setTotalPrice(totalPrice);									//税前金额合计
		tmStockIn.setTotalQuantity(totalQuantity);								//总数量
		tmStockIn.setInType(StockTypeElements.STOCK.getElementValue());			//入库类型为采购
		tmStockIn.setIsConfirm(new Long(isConfirm));							//保存或确认
		
		//保存采购入库详细内容
//		tmStockinDetailService.insertBatchStockinDetail(tmStockIn, partCol);
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		
		try {
			tmStockinDetailService.insertBatchStock(formMap, tmStockIn);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
		ActionContext.getContext().put("msg","生成采购单号:" + tmStockIn.getStockInCode());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 添加其他入库单
	 * @return
	 * @throws IOException 
	 */
	public String insertOtherStockIn() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.OTHERSTOCKIN.getElementString()));
		tmStockIn.setUserId(new Long(userId));
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setTotalPrice(totalPrice);											//税前金额合计
		tmStockIn.setTotalQuantity(totalQuantity);										//总数量
		tmStockIn.setInType(StockTypeElements.OTHERSTOCKIN.getElementValue());			//入库类型为其他
		tmStockIn.setIsConfirm(new Long(isConfirm));									//保存或确认
		
	
		//保存其他入库详细内容
		try {
			tmStockinDetailService.insertBatchStockinDetail(tmStockIn, partCol);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成其他入库单号:" + tmStockIn.getStockInCode());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 添加调拨入库单
	 * @return
	 * @throws IOException 
	 */
	public String insertAllotIn() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.ALLOT.getElementString()));
		tmStockIn.setUserId(new Long(userId));
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setTotalPrice(totalPrice);									//税前金额合计
		tmStockIn.setTotalQuantity(totalQuantity);								//总数量
		tmStockIn.setInType(StockTypeElements.ALLOT.getElementValue());			//入库类型为调拨
		tmStockIn.setIsConfirm(new Long(isConfirm));							//保存或确认
		
	
		//保存调拨入库详细内容
		try {
			tmStockinDetailService.insertBatchStockinDetail(tmStockIn, partCol);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成调拨入库单号:" + tmStockIn.getStockInCode());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 添加报溢入库单
	 * @return
	 * @throws IOException 
	 */
	public String insertOverFlow() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.OVERFLOW.getElementString()));
		tmStockIn.setUserId(new Long(userId));
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setTotalPrice(totalPrice);									//税前金额合计
		tmStockIn.setTotalQuantity(totalQuantity);								//总数量
		tmStockIn.setInType(StockTypeElements.OVERFLOW.getElementValue());		//入库类型为报溢
		tmStockIn.setIsConfirm(new Long(isConfirm));							//保存或确认
		
		//保存报溢入库单
		tmStockInService.insert(tmStockIn);
		//保存报溢入库详细内容
		try {
			tmStockinDetailService.insertBatchOverFlowDetail(new Long(isConfirm),tmStockIn.getId(), partCol);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成报溢入库单号:" + tmStockIn.getStockInCode());
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 更新采购入库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateStockInState(){
		String id =request.getParameter("id");
		tmStockIn  = tmStockInService.findById(new Long(id));
		tmStockIn.setIsConfirm(8002L);
		tmStockInService.update(tmStockIn);
		
		try {
			tmStockinDetailService.updatePartInfoQuantity(new Long(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
		}
	}
	
	/**
	 * 更新调拨入库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateAllotInState(){
		String id =request.getParameter("id");
		tmStockIn  = tmStockInService.findById(new Long(id));
		tmStockIn.setIsConfirm(8002L);
		tmStockInService.update(tmStockIn);
		
		try {
			tmStockinDetailService.updatePartInfoQuantity(new Long(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
		}
	}
	
	
	/**
	 * 更新其他入库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateOtherStockState(){
		String id =request.getParameter("id");
		tmStockIn  = tmStockInService.findById(new Long(id));
		tmStockIn.setIsConfirm(8002L);
		tmStockInService.update(tmStockIn);
		
		try {
			tmStockinDetailService.updatePartInfoQuantity(new Long(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
		}
	}
	
	/**
	 * 更新报溢入库单下所有配件数量
	 * @Date      2010-6-1
	 * @Function
	 */
	public void updateOverFlowState(){
		String id =request.getParameter("id");
		tmStockIn  = tmStockInService.findById(new Long(id));
		tmStockIn.setIsConfirm(8002L);
		tmStockInService.update(tmStockIn);
		
		try {
			tmStockinDetailService.updatePartInfoQuantity(new Long(id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
		}
	}
	
	private Long tmStockInId;
	
	public Long getTmStockInId() {
		return tmStockInId;
	}

	public void setTmStockInId(Long tmStockInId) {
		this.tmStockInId = tmStockInId;
	}

	/**
	 * 销售退货
	 * @return
	 */
	public String findStock(){
		List<TmStockInDetailVo> detVos = null;
		if(tmStockInId != null)
			//EDIT BY CCR
			//detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.STOCK.getElementValue(),tmStockInId,8002L);
			detVos = tmStockinDetailService.getStockInDetVo(null,tmStockInId,8002L);
		
		request.setAttribute("detVos", detVos);
		return Constants.SUCCESS;
	}
	
	/**
	 * 添加销售退货入库单
	 * @return
	 * @throws IOException 
	 */
	public String insertSellReturn() throws IOException{
		String partCol = request.getParameter("partCol");
		Long userId = (Long) request.getSession().getAttribute("userId");
		String isConfirm = request.getParameter("isConfirm");
		
		tmStockIn.setStockInCode(tmDictionaryService.GenerateCode(StockTypeElements.SELLRETURN.getElementString()));
		tmStockIn.setUserId(new Long(userId));
		tmStockIn.setCreateDate(new Date());
		tmStockIn.setInType(StockTypeElements.SELLRETURN.getElementValue());		//入库类型为销售退货
		tmStockIn.setIsConfirm(new Long(isConfirm));								//保存或确认
		tmStockIn.setTotalPrice(totalPrice);
		tmStockIn.setTotalQuantity(totalQuantity);
		
		
		//保存销售退货详细内容
		try {
			tmStockinDetailService.insertBatchStockinDetail(tmStockIn, partCol);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MinusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
		}
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成采购单号:" + tmStockIn.getStockInCode());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 更新销售退货入库单下所有配件数量
	 * @Date      2010-6-7
	 * @Function
	 */
	public void updateSellReturnState(){
		String id =request.getParameter("id");
		tmStockIn  = tmStockInService.findById(new Long(id));
		tmStockIn.setIsConfirm(Constants.CONFIRM);
		tmStockinDetailService.updateSellReturn(tmStockIn);
	}
	
	/************************************修改 操作*****************************************************/
	/**
	 * 查询采购入库单
	 */
	public String findStockIn(){
		//List<TmStockInVo> stockInVos = tmStockinDetailService.getStockInVo(null,8000L);
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
		}
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.STOCK.getElementValue());
		List<TmStockIn> stockInList = tmStockInService.findByEntity(tmStockIn);
		request.setAttribute("stockInList", stockInList);
		request.setAttribute("stockTypeMap", StockTypeElements.getElementMap());
		return Constants.SUCCESS;
		
	}
	/**
	 * 进入修改采购入库页面
	 * @return
	 */
	public String initEditStockInPage(){
		String id = request.getParameter("id");
		tmStockIn = tmStockInService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmStockIn.getSupplierId());
		String userRealName = tmUserService.findById(tmStockIn.getUserId()).getUserRealName();
		tmStockIn.setUserRealName(userRealName);
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.STOCK.getElementValue(),tmStockIn.getId(),8000L);
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("detVos", detVos);
		request.setAttribute("rates", rates);
		request.setAttribute("customer", customer);
		
		request.setAttribute("insertAction", "updateStockInAction");
		request.setAttribute("initPageAction", "findStockInAction");
		request.setAttribute("updateAction", "updateStockInStateAction");
		
		Map<Long,String> panMentMap = Constants.getPayMentMap();
		request.setAttribute("panMentMap", panMentMap);
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新采购入库
	 * @return
	 * @throws IOException 
	 */
	public String updateStockIn() throws IOException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockIn.setIsConfirm(new Long(isConfirm));
//		tmStockInService.updateBathTmStockInDetail(tmStockIn, partCol);
		
		try {
			tmStockinDetailService.stockBatchUpdate(formMap, tmStockIn);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg",e.getMessage());
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询调拨入库单
	 */
	public String findAllotIn(){
		//List<TmStockInVo> stockInVos = tmStockinDetailService.getStockInVo(null,8000L);
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
		}
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.ALLOT.getElementValue());
		List<TmStockIn> stockInList = tmStockInService.findByEntity(tmStockIn);
		request.setAttribute("stockInList", stockInList);
		request.setAttribute("stockTypeMap", StockTypeElements.getElementMap());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 进入修改调拨入库页面
	 * @return
	 */
	public String initEditAllotPage(){
		String id = request.getParameter("id");
		tmStockIn = tmStockInService.findById(new Long(id));
		TbCustomer customer = tbCustomerService.findById(tmStockIn.getSupplierId());
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.ALLOT.getElementValue(),tmStockIn.getId(),8000L);
		List<TmDictionary> rates = tmDictionaryService.getRateTypes();
		request.setAttribute("detVos", detVos);
		request.setAttribute("rates", rates);
		request.setAttribute("customer", customer);
		
		request.setAttribute("insertAction", "updateAllotInAction");
		request.setAttribute("initPageAction", "findStockInAction");
		request.setAttribute("updateAction", "updateAllotInStateAction");
		return Constants.EDITPAGE;
	} 	
	
	/**
	 * 更新调拨入库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateAllotIn() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockIn.setIsConfirm(new Long(isConfirm));
		tmStockInService.updateBathTmStockInDetail(tmStockIn, partCol);
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	/**
	 * 查询其它入库单
	 */
	public String findOtherStockIn(){
		//List<TmStockInVo> stockInVos = tmStockinDetailService.getStockInVo(null,8000L);
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
		}
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.OTHERSTOCKIN.getElementValue());
		List<TmStockIn> stockInList = tmStockInService.findByEntity(tmStockIn);
		request.setAttribute("stockInList", stockInList);
		request.setAttribute("stockTypeMap", StockTypeElements.getElementMap());
		return Constants.SUCCESS;
		
	}
	
	
	/**
	 * 进入修改其它入库页面
	 * @return
	 */
	public String initEditOtherStockInPage(){
		String id = request.getParameter("id");
		tmStockIn = tmStockInService.findById(new Long(id));
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.OTHERSTOCKIN.getElementValue(),tmStockIn.getId(),8000L);
		TbCustomer customer = tbCustomerService.findById(tmStockIn.getSupplierId());

		request.setAttribute("customer", customer);
		request.setAttribute("detVos", detVos);
		request.setAttribute("insertAction", "updateOtherStockInAction");
		request.setAttribute("initPageAction", "findOtherStockInAction");
		request.setAttribute("updateAction", "updateOtherStockStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新其它入库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateOtherStockIn() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockIn.setIsConfirm(new Long(isConfirm));
		tmStockInService.updateBathTmStockInDetail(tmStockIn, partCol);
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询报溢入库单
	 */
	public String findOverFlow(){
		//List<TmStockInVo> stockInVos = tmStockinDetailService.getStockInVo(null,8000L);
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
		}
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.OVERFLOW.getElementValue());
		List<TmStockIn> stockInList = tmStockInService.findByEntity(tmStockIn);
		request.setAttribute("stockInList", stockInList);
		request.setAttribute("stockTypeMap", StockTypeElements.getElementMap());
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 进入修改报溢入库页面
	 * @return
	 */
	public String initEditOverFlowPage(){
		String id = request.getParameter("id");
		tmStockIn = tmStockInService.findById(new Long(id));
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.OVERFLOW.getElementValue(),tmStockIn.getId(),Constants.NOT_CONFIRM);
		request.setAttribute("detVos", detVos);
		
		request.setAttribute("insertAction", "updateOverFlowAction");
		request.setAttribute("initPageAction", "findOverFlowAction");
		request.setAttribute("updateAction", "updateOverFlowStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新报溢入库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateOverFlow() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockIn.setIsConfirm(new Long(isConfirm));
		tmStockInService.updateBathTmStockInDetail(tmStockIn, partCol);
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 查询销售退货单
	 */
	public String findSellReturn(){
		//List<TmStockInVo> stockInVos = tmStockinDetailService.getStockInVo(null,8000L);
		if(tmStockIn == null){
			tmStockIn = new TmStockIn();
		}
		tmStockIn.setIsConfirm(Constants.NOT_CONFIRM);
		tmStockIn.setInType(StockTypeElements.SELLRETURN.getElementValue());
		List<TmStockIn> stockInList = tmStockInService.findByEntity(tmStockIn);
		request.setAttribute("stockInList", stockInList);
		return Constants.SUCCESS;
		
	}
	
	/**
	 * 进入修改销售退货页面
	 * @return
	 */
	public String initEditSellReturnPage(){
		String id = request.getParameter("id");
		tmStockIn = tmStockInService.findById(new Long(id));
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.SELLRETURN.getElementValue(),tmStockIn.getId(),Constants.NOT_CONFIRM);
		request.setAttribute("detVos", detVos);
		
		request.setAttribute("insertAction", "updateSellReturnAction");
		request.setAttribute("initPageAction", "findSellRtnAction");
		request.setAttribute("updateAction", "updateSellReturnStateAction");
		return Constants.EDITPAGE;
	}
	
	/**
	 * 更新报溢入库
	 * @return
	 * @throws IOException 
	 * @throws MinusException 
	 * @throws NumberFormatException 
	 */
	public String updateSellReturn() throws IOException, NumberFormatException, MinusException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmStockIn.setIsConfirm(new Long(isConfirm));
		tmStockInService.updateBathTmStockInDetail(tmStockIn, partCol);
//		response.getWriter().println(tmStockIn.getId()+","+isConfirm);
		
		return Constants.SUCCESS;
	}
	
	
	/*****************************删除 操作*******************************************/
	
	
	/**
	 * 删除所有入库类型的单据
	 */
	public void deleteStockIn() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmStockInService.deleteStockIn(new Long(id));
			if(flag)
				response.getWriter().print("tmStockInTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tmStockInTable," + Constants.FAILURE);
		}
	}
	
	
	
	/**
	 * 查看采购入库单据
	 * @return
	 */
	public String viewStockIn(){
		
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			tmStockIn = tmStockInService.findById(new Long(id));
			viewId = new Long(id);
		}else{
			tmStockIn = tmStockInService.findById(viewId);
		}
		List<TmStockInDetailVo> detVos = tmStockinDetailService.getStockInDetVo(StockTypeElements.STOCK.getElementValue(),tmStockIn.getId(),8002L);
		request.setAttribute("detVos", detVos);
		
		Map<Long,String> panMentMap = Constants.getPayMentMap();
		request.setAttribute("panMentMap", panMentMap);
		return Constants.VIEWPAGE;
	}
	
	
	/**
	 * 配件信息xls导入
	 * @return
	 * @throws Exception
	 */
	public String cgrkV2ImportXls() throws Exception{
		
		InputStream in = new FileInputStream(fileXls);
		
		String flag = stockXLSImportService.cgrkV2ImportXls(in, "/stockxlsimport/TmStockInImportVo_import_xls_tpl.properties");
		
		if(null!=flag){
			
			String xlsMessage[] = flag.split("-");
			List<String> xlsMessageList = null;
			if("success".equals(xlsMessage[0])){
				
				String[] successMessage = xlsMessage[1].split(",");
				
				
				ActionContext.getContext().put("xlsMessage", successMessage[0]);
				
				if(successMessage.length==2){
					
					xlsMessageList = new ArrayList<String>();
					
					String[] repeatMessage = successMessage[1].split(";");
					
					for(int i = 0 ; i < repeatMessage.length ; i++){
						xlsMessageList.add(repeatMessage[i]);
					}
					ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				}
				return Constants.SUCCESS;
			}
			else if("failError".equals(xlsMessage[0])){
				xlsMessageList = new ArrayList<String>();
				String[] errorMessage = xlsMessage[1].split(",");
				for(int i = 0 ; i < errorMessage.length ; i++){
					xlsMessageList.add(errorMessage[i]);
				}
				ActionContext.getContext().put("xlsMessageList", xlsMessageList);
				return Constants.FAILURE;
			}
			else{
				ActionContext.getContext().put("xlsMessage", xlsMessage[1]);
				return Constants.FAILURE;
			}
			
			
		}
		
		ActionContext.getContext().put("xlsMessage", "文件中数据类型不符合要求");
		
		return Constants.FAILURE;
		
	}
	
	
	public String initcgrk2ImportXlsPage(){
		return Constants.SUCCESS;
	}
	
	private void handlerCalendar(){
		String tq = CommonMethod.getPropertiesValue(this.getClass().getResourceAsStream("/system.properties"), "tq");
		tq = tq!=""?tq:"true";
		request.setAttribute("tq", tq);
	}
}

