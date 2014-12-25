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
import com.selfsoft.business.model.TmLianceRegister;
import com.selfsoft.business.service.ITmLianceRegisterDetailService;
import com.selfsoft.business.service.ITmLianceRegisterService;
import com.selfsoft.business.service.ITmStockOutService;
import com.selfsoft.business.service.ITmStockoutDetailService;
import com.selfsoft.framework.common.Constants;

public class TmStockoutDetailAction extends ActionSupport implements
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
	private ITmStockoutDetailService tmStockoutDetailService;
}