package com.selfsoft.business.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import com.selfsoft.business.dao.ITmShiftinStockDao;
import com.selfsoft.business.model.TmRemoveStock;
import com.selfsoft.business.model.TmShiftinStock;
import com.selfsoft.business.service.ITmRemoveStockService;
import com.selfsoft.business.service.ITmShiftinStockService;
import com.selfsoft.business.vo.TmRemStockDetailVo;
import com.selfsoft.business.vo.TmRemStockVo;
import com.selfsoft.business.vo.TmShiftinStockVo;
import com.selfsoft.common.exception.MinusException;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmShiftinStockAction extends ActionSupport implements
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
	private ITmShiftinStockService tmShiftinStockService;
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	
	
	
	private Double totalPrice;
	
	private Double totalQuantity;
	
	private Long removeStockId;
	
	private TmShiftinStock tmShiftinStock;
	

	

	public TmShiftinStock getTmShiftinStock() {
		return tmShiftinStock;
	}

	public void setTmShiftinStock(TmShiftinStock tmShiftinStock) {
		this.tmShiftinStock = tmShiftinStock;
	}

	public Long getRemoveStockId() {
		return removeStockId;
	}

	public void setRemoveStockId(Long removeStockId) {
		this.removeStockId = removeStockId;
	}

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

	
	
	public String findRemoveStock(){
		List<TmStoreHouse> storeHouseList = tmStoreHouseService.findAll();
		request.setAttribute("storeHouseList", storeHouseList);
		List<TmRemStockDetailVo> detVoList = null;
		
		if(removeStockId != null)
			detVoList = tmRemoveStockService.getRemStockDetVos(Constants.CONFIRM,Constants.NOTVALIDVALUE,removeStockId);
		request.setAttribute("detVoList", detVoList);
		return Constants.SUCCESS;
	}
	
	public String insertTmShiftStock() throws IOException, MinusException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Long userId = (Long) request.getSession().getAttribute("userId");
		String rsId = request.getParameter("rsId");
		String isConfirm = request.getParameter("isConfirm");
		
		
		tmShiftinStock.setShiftinBill(tmDictionaryService.GenerateCode(StockTypeElements.SHIFTSTOCK.getElementString()));
		tmShiftinStock.setRemoveStockId(new Long(rsId));
		tmShiftinStock.setIsConfirm(new Long(isConfirm));
		tmShiftinStock.setUserId(new Long(userId));
		tmShiftinStock.setCreateDate(new Date());
		tmShiftinStock.setIsConfirm(new Long(isConfirm));
		
		tmShiftinStockService.insert(tmShiftinStock);
//		response.getWriter().println(tmShiftinStock.getId()+","+isConfirm);
		ActionContext.getContext().put("msg","生成移进入库单号:" + tmShiftinStock.getShiftinBill());
		return Constants.SUCCESS;
	}
	
	public void updateTmShiftStock() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, MinusException{
		String id = request.getParameter("id");
		TmShiftinStock tss = tmShiftinStockService.findById(new Long(id));
		tss.setIsConfirm(Constants.CONFIRM);
		tmShiftinStockService.update(tss);
	}
	
	
	public String findShiftinStock(){
		List<TmShiftinStockVo>  shifinVos = tmShiftinStockService.getTmshiftinStockVos(Constants.NOT_CONFIRM, tmShiftinStock,null);
		request.setAttribute("shifinVos", shifinVos);
		return Constants.SUCCESS;
	}
	
	public String initEditShiftinStockPage(){
		String id = request.getParameter("id");
		tmShiftinStock = tmShiftinStockService.findById(new Long(id));
		List<TmStoreHouse> storeHouseList = tmStoreHouseService.findAll();
		this.removeStockId = tmShiftinStock.getRemoveStockId();
		request.setAttribute("storeHouseList", storeHouseList);
		TmRemoveStock removeStock = tmRemoveStockService.findById(tmShiftinStock.getRemoveStockId());
		request.setAttribute("removeStock",removeStock);
		return Constants.EDITPAGE;
	}
	
	public String updateShift() throws IOException{
		String isConfirm = request.getParameter("isConfirm");
		tmShiftinStock.setIsConfirm(new Long(isConfirm));
		tmShiftinStockService.updateTmShiftinStock(tmShiftinStock);
//		response.getWriter().println(tmShiftinStock.getId()+","+isConfirm);
		return Constants.SUCCESS;
	}
	
	public void deleteTmShiftinStock() throws IOException{
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			boolean flag = tmShiftinStockService.deleteById(new Long(id));
			if(flag)
				response.getWriter().print("tmShiftinStockTable," + Constants.SUCCESS);
			else 
				response.getWriter().print("tmShiftinStockTable," + Constants.FAILURE);
		}
	}
	
	
}