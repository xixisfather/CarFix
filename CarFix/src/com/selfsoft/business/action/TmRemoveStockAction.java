package com.selfsoft.business.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmRemoveStockDetailService;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.vo.TmLianceRegDetailVo;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.business.vo.TmStockInDetailVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;
/**
 * 销售退货
 * @author Administrator
 *
 */
public class TmRemoveStockAction extends ActionSupport implements
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
	private ITmRemoveStockService tmRemoveStockService;
	
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	@Autowired
	private ITmRemoveStockDetailService tmRemoveStockDetailService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	private TmRemoveStock tmRemoveStock;
	
	private Double totalPrice;
	
	private Double totalQuantity;
	
	

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Double totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public TmRemoveStock getTmRemoveStock() {
		return tmRemoveStock;
	}

	public void setTmRemoveStock(TmRemoveStock tmRemoveStock) {
		this.tmRemoveStock = tmRemoveStock;
	}
	
	public String initRemoveStockPage(){
		List<TmStoreHouse> storeHouseList = tmStoreHouseService.findAll();
		
		request.setAttribute("storeHouseList", storeHouseList);
		request.setAttribute("insertAction", "insertTmRemoveStockAction");
		request.setAttribute("initPageAction", "initRemoveStockPageAction");
		request.setAttribute("updateAction", "updateTmRemoveStockAction");
		request.setAttribute("gtitle","移库出仓");
		return Constants.SUCCESS;
	}
	
	
	public String insertTmRemoveStock() throws IOException, NumberFormatException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		
		tmRemoveStock.setRemoveStockBill(tmDictionaryService.GenerateCode(StockTypeElements.REMOVESTOCK.getElementString()));
		tmRemoveStock.setRemovePrice(totalPrice);
		tmRemoveStock.setRemoveQuantity(totalQuantity);
		tmRemoveStock.setUserId(userId);
		tmRemoveStock.setCreateDate(new Date());
		tmRemoveStock.setIsConfirm(new Long(isConfirm));
		tmRemoveStock.setIsValid(0L);
		//保存移库出仓单据
		try {
			tmRemoveStockDetailService.insertBatchRemoveStockDetail(tmRemoveStock, partCol);
		} catch (MinusException e) {
			ActionContext.getContext().put("msg","移出数量不能大于库存量");
			return Constants.FAILURE;
		}
		
		
//		response.getWriter().println(tmRemoveStock.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成移库出仓单号:" + tmRemoveStock.getRemoveStockBill());
		return Constants.SUCCESS;
	}
	
	
	public void updateTmRemoveStock(){
		String id =request.getParameter("id");
		tmRemoveStock  = tmRemoveStockService.findById(new Long(id));
		tmRemoveStock.setIsConfirm(Constants.CONFIRM);
		tmRemoveStockService.update(tmRemoveStock);
		
	}
	
	
	public String findRemoveStock(){
		List<TmRemStockVo> removeStockVos = tmRemoveStockService.getAllRemStockVos(Constants.NOT_CONFIRM, Constants.NOTVALIDVALUE, tmRemoveStock,null);
		request.setAttribute("removeStockVos", removeStockVos);
		return Constants.SUCCESS;
	}
	
	public String initEditRemoveStockPage(){
		String id = request.getParameter("id");
		tmRemoveStock = tmRemoveStockService.findById(new Long(id));
		List<TmRemStockDetailVo> detVos = tmRemoveStockService.getRemStockDetVos(Constants.NOT_CONFIRM,Constants.NOTVALIDVALUE,tmRemoveStock.getId());
		request.setAttribute("detVos", detVos);
		List<TmStoreHouse> storeHouseList = tmStoreHouseService.findAll();
		request.setAttribute("storeHouseList", storeHouseList);
		request.setAttribute("insertAction", "updateTmRemStockAction");
		request.setAttribute("initPageAction", "findRemStockAction");
		request.setAttribute("updateAction", "updateTmRemoveStockAction");
		return Constants.EDITPAGE;
	}
	
	
	public String updateTmRemStock() throws IOException, NumberFormatException{
		String partCol = request.getParameter("partCol");
		String isConfirm = request.getParameter("isConfirm");
		tmRemoveStock.setIsConfirm(new Long(isConfirm));
		try {
			tmRemoveStockDetailService.updateBatchRemoveStockDetail(tmRemoveStock, partCol);
		} catch (MinusException e) {
			e.printStackTrace();
			ActionContext.getContext().put("msg","移出数量不能大于库存量");
			return Constants.FAILURE;
		}
//		response.getWriter().println(tmRemoveStock.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	public void deleteTmRemStock() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmRemoveStockService.deleteRemoveStock(new Long(id));
			if(flag)
				response.getWriter().print("tmRemoveStockTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tmRemoveStockTable," + Constants.FAILURE);
		}
	}
	
}