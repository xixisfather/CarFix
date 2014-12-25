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
import com.selfsoft.baseparameter.model.TmDictionary;
import com.selfsoft.baseparameter.service.ITmDictionaryService;
import com.selfsoft.business.model.TmStockIn;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmStockInService;
import com.selfsoft.business.service.ITmStockinDetailService;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.framework.common.StockTypeElements;

public class TmLianceRegisterDetailAction extends ActionSupport implements
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
	private ITmLianceRegisterDetailService tmLianceRegisterDetailService;
}