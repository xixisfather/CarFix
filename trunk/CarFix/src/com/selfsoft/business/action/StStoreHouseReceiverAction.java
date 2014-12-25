package com.selfsoft.business.action;

import java.util.Collection;
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
import com.selfsoft.baseparameter.model.TmStoreHouse;
import com.selfsoft.baseparameter.service.ITmStoreHouseService;
import com.selfsoft.business.model.StStorehouseReceiver;
import com.selfsoft.business.service.IStStorehouseReceiverService;
import com.selfsoft.framework.common.Constants;

public class StStoreHouseReceiverAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -912064364077865419L;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private Map<String,String> formMap = new HashMap<String,String>();
	
	private StStorehouseReceiver stStorehouseReceiver;
	
	
	
    public StStorehouseReceiver getStStorehouseReceiver() {
		return stStorehouseReceiver;
	}

	public void setStStorehouseReceiver(StStorehouseReceiver stStorehouseReceiver) {
		this.stStorehouseReceiver = stStorehouseReceiver;
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
    
   
	@Autowired
	private IStStorehouseReceiverService stStorehouseReceiverService;
	@Autowired
	private ITmStoreHouseService tmStoreHouseService;
	
	public String forwardPage() throws Exception {
		String id = request.getParameter("id");
		
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService.findAll();
		
		ActionContext.getContext().put("tmStoreHouseList",tmStoreHouseList);
		
		if (StringUtils.isNotBlank(id)) {
			
			formMap =  stStorehouseReceiverService.initEditStoreHouseReceiver(id);
			
			return Constants.EDITPAGE;
		}
		
		
		
		return Constants.ADDPAGE;
	}
	
	
	public String addStoreHouseReceiver(){
		stStorehouseReceiverService.batchInsert(formMap,null);
		return Constants.SUCCESS;
	}
	
	
	public String findStStoreHouseReceiver(){
		
		List<StStorehouseReceiver> stStoreHouseReceiverList = stStorehouseReceiverService.findByStStorehouseReceiver(stStorehouseReceiver);
		
		List<TmStoreHouse> tmStoreHouseList = tmStoreHouseService.findAll();
		
		ActionContext.getContext().put("tmStoreHouseList",tmStoreHouseList);
		
		ActionContext.getContext().put("stStoreHouseReceiverList",stStoreHouseReceiverList);
		
		return Constants.SUCCESS;
	}
	
	
	public String hasAddStStoreHouseReceiver() throws Exception{

		boolean flag = stStorehouseReceiverService.hasAdd();
		
		if (flag) {
			response.getWriter().print("stStoreHouseReceiverTable," + Constants.SUCCESS);
		} else {
			response.getWriter().print("stStoreHouseReceiverTable," + Constants.FAILURE);
		} 
		return null;
	} 
	
	public String updateStStoreHouseReceiver(){
		
		String dateStr = request.getParameter("dateStr");
		System.out.println(dateStr);
		stStorehouseReceiverService.batchUpdate(dateStr, formMap);
		
		return Constants.SUCCESS;
	}
}
