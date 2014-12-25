package com.selfsoft.business.action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.StPartReceiver;
import com.selfsoft.business.model.StStockin;
import com.selfsoft.business.model.StStockout;
import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.business.model.StStorehouseSurvey;
import com.selfsoft.business.service.IStPartReceiverService;
import com.selfsoft.business.service.IStStockinService;
import com.selfsoft.business.service.IStStockoutService;
import com.selfsoft.business.service.IStStorehouseReceiverService;
import com.selfsoft.business.service.IStStorehouseSurveyService;
import com.selfsoft.business.service.IStatisticsStockInOutService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;


public class StatXlsAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private StStockin stStockin;
	
	private StPartReceiver stPartReceiver;
	
	private StStorehouseSurvey stStorehouseSurvey;
	
	private StStockout stStockout;
	
	private StStorehouseReceiver stStorehouseReceiver;
	
	
	public StStorehouseReceiver getStStorehouseReceiver() {
		return stStorehouseReceiver;
	}

	public void setStStorehouseReceiver(StStorehouseReceiver stStorehouseReceiver) {
		this.stStorehouseReceiver = stStorehouseReceiver;
	}

	public StStockout getStStockout() {
		return stStockout;
	}

	public void setStStockout(StStockout stStockout) {
		this.stStockout = stStockout;
	}

	public StPartReceiver getStPartReceiver() {
		return stPartReceiver;
	}

	public void setStPartReceiver(StPartReceiver stPartReceiver) {
		this.stPartReceiver = stPartReceiver;
	}

	public StStorehouseSurvey getStStorehouseSurvey() {
		return stStorehouseSurvey;
	}

	public void setStStorehouseSurvey(StStorehouseSurvey stStorehouseSurvey) {
		this.stStorehouseSurvey = stStorehouseSurvey;
	}

	public StStockin getStStockin() {
		return stStockin;
	}

	public void setStStockin(StStockin stStockin) {
		this.stStockin = stStockin;
	}

	
	
	@Autowired
	private IStStockinService stStockinService;
	@Autowired
	private IStStorehouseSurveyService stStorehouseSurveyService;
	@Autowired
	private IStPartReceiverService stPartReceiverService;
	@Autowired
	private IStStockoutService stStockoutService;
	@Autowired
	private IStStorehouseReceiverService stStorehouseReceiverService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	
	/**
	 * 采购入库查询统计
	 * @return
	 */
	public String findStStockIn(){
		
		List<StStockin> stStockinList = stStockinService.findStStockin(stStockin);
		
		ActionContext.getContext().put("stStockinList", stStockinList);
		
		return Constants.SUCCESS;
	}
	
	/**
	 * 配件期间收发存统计
	 * @return
	 */
	public String findStPartReceiver(){
		
		List<StPartReceiver> stPartReceiverList = stPartReceiverService.findByStPartReceiver(stPartReceiver);
		
		StPartReceiver statTotal = stPartReceiverService.getStPartReceiverCount(stPartReceiverList);
		
		ActionContext.getContext().put("stPartReceiverList", stPartReceiverList);
		
		ActionContext.getContext().put("statTotal", statTotal);
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 仓库概貌统计
	 * @return
	 */
	public String findStStorehouseSurvey(){
		
		List<StStorehouseSurvey> stStorehouseSurveyList = stStorehouseSurveyService.findByStStorehouseSurvey(stStorehouseSurvey);
		
		ActionContext.getContext().put("stStorehouseSurveyList", stStorehouseSurveyList);
		
		return Constants.SUCCESS;
	}
	

	
	/**
	 * 配件每日出库统计
	 * @return
	 */
	public String findStStockout(){
		
		Map<String, String> elementsMap = this.getStockOutElementMap();
		
		ActionContext.getContext().put("elementsMap", elementsMap);
		
		List<StStockout> stStockoutList = stStockoutService.findByStStockout(stStockout);
		
		StStockout countVo = stStockoutService.getStStockoutCount(stStockoutList);
		
		ActionContext.getContext().put("stStockoutList", stStockoutList);
		
		ActionContext.getContext().put("countVo", countVo);
		
		return Constants.SUCCESS;
	}
	
	
	/**
	 * 得到所有出库类型
	 * @return
	 */
	private Map<String, String> getStockOutElementMap(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		map.put(StockTypeElements.MAINTAIN.getElementName(), StockTypeElements.MAINTAIN.getElementName());
		map.put(StockTypeElements.SELLSTOCKOUT.getElementName(), StockTypeElements.SELLSTOCKOUT.getElementName());
		map.put(StockTypeElements.DRAWSTOCKOUT.getElementName(), StockTypeElements.DRAWSTOCKOUT.getElementName());
		map.put(StockTypeElements.ALLOTSTOCKOUT.getElementName(), StockTypeElements.ALLOTSTOCKOUT.getElementName());
		map.put(StockTypeElements.SHATTERSTOCKOUT.getElementName(), StockTypeElements.SHATTERSTOCKOUT.getElementName());
		
		return map;
	}
	
	/**
	 * 仓库期间收发存统计
	 * @return
	 */
	public String findStoreHouseReceiver(){
		
		List<StStorehouseReceiver> stStoreHouseReceiverList = stStorehouseReceiverService.findByStStorehouseReceiver(stStorehouseReceiver);
		
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService.findAll();
		
		ActionContext.getContext().put("tmStoreHouseList",tmStoreHouseList);
		
		ActionContext.getContext().put("stStoreHouseReceiverList",stStoreHouseReceiverList);
		
		return Constants.SUCCESS;
	}
	
}
