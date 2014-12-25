package com.selfsoft.secrity.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.selfsoft.framework.common.Constants;
import com.selfsoft.secrity.model.TmBackMenu;
import com.selfsoft.secrity.service.ITmBackMenuService;
import com.selfsoft.secrity.service.impl.ExtTreeBuilderService;

public class TmBackMenuAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {
	
	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private TmBackMenu tmBackMenu;
	
	@Autowired
	private ITmBackMenuService backMenuService;
	
	@Autowired
	private ExtTreeBuilderService extTreeBuilderService;

	public TmBackMenu getTmBackMenu() {
		return tmBackMenu;
	}

	public void setTmBackMenu(TmBackMenu tmBackMenu) {
		this.tmBackMenu = tmBackMenu;
	}
	
	
	public String getTmBackMenuTree() throws Exception{
		List<TmBackMenu> list = backMenuService.getTmBackMenuTree(1L);
		String treeScript = extTreeBuilderService.buildBackMenuOutLookTree(request, list);
		request.setAttribute("treeScript", treeScript);
		return Constants.SUCCESS;
	}
}
