package com.selfsoft.baseparameter.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.framework.common.Constants;

public class MinusAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2258181983244668493L;

	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Autowired
	private ITmDictionaryService tmDictionaryService;
	
	private TmDictionary tmDictionary;
	
	
	public TmDictionary getTmDictionary() {
		return tmDictionary;
	}

	public void setTmDictionary(TmDictionary tmDictionary) {
		this.tmDictionary = tmDictionary;
	}

	public String minusConfig(){
		
		tmDictionary = tmDictionaryService.getMinusVal();
		Map<Long, String> minusMap = new HashMap<Long,String>();
		minusMap.put(Constants.ISMINUS,"允许负出库");
		minusMap.put(Constants.NOMINUS,"不允许负出库");
		ActionContext.getContext().put("tmDictionary", tmDictionary);
		ActionContext.getContext().put("minusMap", minusMap);
		return Constants.SUCCESS;
	}
	
	
	public String updateTmDictionary(){
		tmDictionaryService.updateTmDictionary(tmDictionary);
		
		
		tmDictionary = tmDictionaryService.getMinusVal();
		Map<Long, String> minusMap = new HashMap<Long,String>();
		minusMap.put(Constants.ISMINUS,"允许负出库");
		minusMap.put(Constants.NOMINUS,"不允许负出库");
		ActionContext.getContext().put("tmDictionary", tmDictionary);
		ActionContext.getContext().put("minusMap", minusMap);
		return Constants.SUCCESS;
	}
}
