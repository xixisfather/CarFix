package com.selfsoft.secrity.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmCompany;
import com.selfsoft.secrity.service.ITmCompanyService;

public class TmCompanyAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8307441385562922762L;

	/**
	 * 获取request,response对象
	 */
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Autowired
	private ITmCompanyService tmCompanyService;
	
	private TmCompany tmCompany;
	
	
	public TmCompany getTmCompany() {
		return tmCompany;
	}

	public void setTmCompany(TmCompany tmCompany) {
		this.tmCompany = tmCompany;
	}

	
	public String editTmCompanyInitPage(){
		
		tmCompany = tmCompanyService.acquireUniqueTmCompany();
		
		return Constants.EDITPAGE;
		
	}
	
	
	public String updateTmCompany(){
		
		tmCompanyService.update(tmCompany);
		
		return Constants.SUCCESS;
	}
}
